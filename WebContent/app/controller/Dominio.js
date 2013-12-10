Ext.define('Liproma.controller.Dominio', {
	extend : 'Ext.app.Controller',

	stores : [ 'Dominio' ],

	models : [ 'Dominio' ],

	views : [ 'dominio.Formulario', 'dominio.Grid' ],

	refs : [ {
		ref : 'dominioPanel',
		selector : 'panel'
	}, {
		ref : 'dominioGrid',
		selector : 'grid'
	} ],

	init : function() {
		this.control({
			'dominiogrid dataview' : {
				itemdblclick : this.editarDominio
			},
			'dominiogrid button[action=add]' : {
				click : this.editarDominio
			},
			'dominiogrid button[action=delete]' : {
				click : this.deletarDominio
			},

			'dominiogrid button[action=edit]' : {
				click : this.editarDominioButton
			},
			'dominioform button[action=save]' : {
				click : this.atualizarDominioForm
			}
		});
	},

	editarDominio : function(grid, record) {
		var edit = Ext.create('Liproma.view.dominio.Formulario').show();
		if (record && record.getData) {
			edit.down('form').loadRecord(record);
		}
	},

	editarDominioButton : function(button) {
		var grid = this.getDominioGrid();
		var record = grid.getSelectionModel().getSelection();

		var edit = Ext.create('Liproma.view.dominio.Formulario').show();
		if (record && record.getData) {
			edit.down('form').loadRecord(record);
		}
	},

	atualizarDominioForm : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var record = form.getRecord();
		var values = form.getValues();
		var novo = false;


		if (values.id > 0) {
			record.set(values);
		} else {
			record = Ext.create('Liproma.model.Dominio');
			record.set(values);
			this.getDominioStore().add(record);
			novo = true;
		}

		win.close();
		this.getDominioStore().sync();

		if (novo) {// faz reload para atualizar
			this.getDominioStore().load();
		}
	},

	deletarDominio : function(button) {
		var grid = this.getDominioGrid();
		var record = grid.getSelectionModel().getSelection();
		var store = this.getDominioStore();
		var me = this;

		Ext.MessageBox.confirm('Confirmação',
				'Quer mesmo deletar o Dominio ?', function(btn) {
					if (btn == 'yes') {
						store.remove(record);
						me.getDominioStore().sync();

						//  faz reload para atualizar
						//me.getAnaliseMercadoStore().load();
						Ext.MessageBox.alert('Mensagem',
								'Dominio deletado com sucesso!');
					}
				});
	}
});