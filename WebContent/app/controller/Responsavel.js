Ext.define('Liproma.controller.Responsavel', {
	extend : 'Ext.app.Controller',

	stores : [ 'Responsavel' ],

	models : [ 'Responsavel' ],

	views : [ 'responsavel.Formulario', 'responsavel.Grid' ],

	refs : [ {
		ref : 'responsavelPanel',
		selector : 'panel'
	}, {
		ref : 'responsavelGrid',
		selector : 'grid'
	} ],

	init : function() {
		this.control({
			'responsavelgrid dataview' : {
				itemdblclick : this.editarResponsavel
			},
			'responsavelgrid button[action=add]' : {
				click : this.editarResponsavel
			},
			'responsavelgrid button[action=delete]' : {
				click : this.deletarResponsavel
			},

			'responsavelgrid button[action=edit]' : {
				click : this.editarResponsavelButton
			},
			'responsavelform button[action=save]' : {
				click : this.atualizarResponsavelForm
			}
		});
	},

	editarResponsavel : function(grid, record) {
		var edit = Ext.create('Liproma.view.responsavel.Formulario').show();
		if (record && record.getData) {
			edit.down('form').loadRecord(record);
		}
	},

	editarResponsavelButton : function(button) {
		var grid = this.getResponsavelGrid();
		var record = grid.getSelectionModel().getSelection();

		var edit = Ext.create('Liproma.view.responsavel.Formulario').show();
		if (record && record.getData) {
			edit.down('form').loadRecord(record);
		}
	},

	atualizarResponsavelForm : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var record = form.getRecord();
		var values = form.getValues();
		var novo = false;


		if (values.id > 0) {
			record.set(values);
		} else {
			record = Ext.create('Liproma.model.Responsavel');
			record.set(values);
			this.getResponsavelStore().add(record);
			novo = true;
		}

		win.close();
		this.getResponsavelStore().sync();

		if (novo) {// faz reload para atualizar
			this.getResponsavelStore().load();
		}
	},

	deletarResponsavel : function(button) {
		var grid = this.getResponsavelGrid();
		var record = grid.getSelectionModel().getSelection();
		var store = this.getResponsavelStore();
		var me = this;

		Ext.MessageBox.confirm('Confirmação',
				'Quer mesmo deletar o Responsavel ?', function(btn) {
					if (btn == 'yes') {
						store.remove(record);
						me.getResponsavelStore().sync();

						//  faz reload para atualizar
						//me.getAnaliseMercadoStore().load();
						Ext.MessageBox.alert('Mensagem',
								'Responsavel deletado com sucesso!');
					}
				});
	}
});