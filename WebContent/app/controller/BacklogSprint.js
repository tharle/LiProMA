Ext.define('Liproma.controller.BacklogSprint', {
	extend : 'Ext.app.Controller',

	stores : [ 'BacklogSprint' ],

	models : [ 'BacklogSprint' ],

	views : [ 'backlogsprint.Formulario', 'backlogsprint.Grid', 'backlogsprint.Tela' ],

	refs : [ {
		ref : 'backlogsprintPanel',
		selector : 'panel'
	}, {
		ref : 'backlogsprintGrid',
		selector : 'grid'
	} ],

	init : function() {
		this.control({
			'backlogsprintgrid dataview' : {
				itemdblclick : this.editarBacklogSprint
			},
			'backlogsprintgrid button[action=add]' : {
				click : this.editarBacklogSprint
			},
			'backlogsprintgrid button[action=delete]' : {
				click : this.deletarBacklogSprint
			},

			'backlogsprintgrid button[action=edit]' : {
				click : this.editarBacklogSprintButton
			},
			'backlogsprintform button[action=save]' : {
				click : this.atualizarBacklogSprintForm
			},'backlogsprinttela #cmbsprintbacklogescopo' : {
				select : this.selecionarBacklogEscopo
			}
		});
	},

	editarBacklogSprint : function(grid, record) {
		var edit = Ext.create('Liproma.view.backlogsprint.Formulario').show();
		if (record && record.getData) {
			edit.atualizandoBS(record.getData().id);
			edit.down('form').loadRecord(record);
		}
	},

	editarBacklogSprintButton : function(button) {
		var grid = this.getBacklogSprintGrid();
		var record = grid.getSelectionModel().getSelection();

		var edit = Ext.create('Liproma.view.backlogsprint.Formulario').show();
		if (record && record.getData) {
			record.data.idBacklogEscopo = Ext.getCmp('cmbsprintbacklogescopo').getValue();
			edit.down('form').loadRecord(record);
		}
	},

	atualizarBacklogSprintForm : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var record = form.getRecord();
		var values = form.getValues();
		var novo = false;
		// --Feature
		var featureSelecionados = new Array();
		var fetckb = form.getChildByElement('ckbbsfeature').down(
				'checkboxgroup');
		for ( var i = 0; i < fetckb.items.length; i++) {
			if (fetckb.items.items[i].checked) {
				featureSelecionados.push(fetckb.items.items[i].inputValue);
			}
		}
		values.featureValores = featureSelecionados;
		// ---


		values.idBacklogEscopo = Ext.getCmp('cmbsprintbacklogescopo').getValue();
		
		if (values.id > 0) {
			record.set(values);
		} else {
			record = Ext.create('Liproma.model.BacklogSprint');
			record.set(values);
			this.getBacklogSprintStore().add(record);
			novo = true;
		}

		win.close();
		this.getBacklogSprintStore().sync();

		if (novo) {// faz reload para atualizar
			this.getBacklogSprintStore().load();
		}
	},

	deletarBacklogSprint : function(button) {
		var grid = this.getBacklogSprintGrid();
		var record = grid.getSelectionModel().getSelection();
		var store = this.getBacklogSprintStore();
		var me = this;

		Ext.MessageBox.confirm('Confirmação',
				'Quer mesmo deletar o BacklogSprint ?', function(btn) {
					if (btn == 'yes') {
						store.remove(record);
						me.getBacklogSprintStore().sync();

						//  faz reload para atualizar
						//me.getAnaliseMercadoStore().load();
						Ext.MessageBox.alert('Mensagem',
								'BacklogSprint deletado com sucesso!');
					}
				});
	},
	selecionarBacklogEscopo : function(combo, records) {
		Ext.util.Cookies.set('sprintEscopo',  combo.getValue());
		_sprintStore.load({
			params : {
				idBacklogEscopo :  combo.getValue()
			}
		});
	}
});