
Ext.define('Liproma.controller.BacklogEscopoEspecifico', {
	extend : 'Ext.app.Controller',

	stores : [ 'BacklogEscopoFeature' ],

	models : [ 'BacklogEscopo', 'Feature' ],

	views : [ 'backlogescopoespecifico.Formulario',
				'backlogescopoespecifico.Grid', 'backlogescopoespecifico.Tela'],

	refs : [ {
		ref : 'backlogescopoespecificoPanel',
		selector : 'panel'
	}, {
		ref : 'backlogescopoespecificoGrid',
		selector : 'grid'
	} , {
		ref : 'backlogescopoespecificoTela',
		selector : 'tela'
	}],

	init : function() {
		this.control({
			'backlogescopoespecificogrid dataview' : {
				itemdblclick : this.editarBacklogEscopoEspecifico
			}, 'backlogescopoespecificogrid button[action=delete]' : {
				click : this.deletarBacklogEscopoEspecifico
			},
			'backlogescopoespecificoform button[action=save]' : {
				click : this.atualizarBacklogEscopoEspecificoForm
			},'backlogescopoespecificotela #cmbbacklogescopo' : {
				select : this.selecionarBacklogEscopo
			}
		});
	},

	editarBacklogEscopoEspecifico : function(grid, record) {
		var edit = Ext.create('Liproma.view.backlogescopoespecifico.Formulario').show();
		if (record && record.getData) {
			record.data.idBacklogEscopo = Ext.getCmp('cmbbacklogescopo').getValue();
			edit.down('form').loadRecord(record);
		}
	},

	atualizarBacklogEscopoEspecificoForm : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var record = form.getRecord();
		var values = form.getValues();
		var novo = false;

		if (values.id > 0) {
			record.set(values);
		} else {
			record = Ext.create('Liproma.model.Feature');
			record.set(values);
			this.getBacklogEscopoFeatureStore().add(record);
			novo = true;
		}

		win.close();
		this.getBacklogEscopoFeatureStore().sync();

		if (novo) {// faz reload para atualizar
			this.getBacklogEscopoFeatureStore().load();
		}
	},

	deletarBacklogEscopoEspecifico : function(button) {
		var grid = this.getBacklogEscopoEspecificoGrid();
		var record = grid.getSelectionModel().getSelection();
		var store = this.getBacklogEscopoEspecificoStore();
		var me = this;

		Ext.MessageBox.confirm('Confirmação',
				'Quer mesmo deletar o BacklogEscopoEspecifico ?', function(btn) {
					if (btn == 'yes') {
						store.remove(record);
						me.getBacklogEscopoEspecificoStore().sync();

						//  faz reload para atualizar
						//me.getAnaliseMercadoStore().load();
						Ext.MessageBox.alert('Mensagem',
								'Backlog Escopo Especifico deletado com sucesso!');
					}
				});
	},
	selecionarBacklogEscopo : function(combo, records) {
		_backlogEscopoEspecificoStore.load({
			params : {
				idBacklogEscopo :  combo.getValue()
			}
		});
	}
});