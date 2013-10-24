Ext.define('Liproma.controller.AnaliseMercado', {
	extend : 'Ext.app.Controller',

	stores : [ 'AnaliseMercado' ],

	models : [ 'AnaliseMercado' ],

	views : [ 'analisemercado.Formulario', 'analisemercado.Grid' ],

	refs : [ {
		ref : 'analiseMercadoPanel',
		selector : 'panel'
	}, {
		ref : 'analiseMercadoGrid',
		selector : 'grid'
	} ],

	init : function() {
		var ultimoItemSelecionado;
		this.control({
			'analisemercadogrid dataview' : {
				itemdblclick : this.editarAnaliseMercado,
				itemclick: this.alterandoItensGrid
			},
			'analisemercadogrid button[action=add]' : {
				click : this.editarAnaliseMercado
			},
			'analisemercadogrid button[action=delete]' : {
				click : this.deletarAnaliseMercado
			},

			'analisemercadogrid button[action=edit]' : {
				click : this.editarAnaliseMercadoButton
			},
			'analisemercadoform button[action=save]' : {
				click : this.atualizarAnaliseMercadoForm
			}

		});
	},

	editarAnaliseMercado : function(grid, record) {
		var edit = Ext.create('Liproma.view.analisemercado.Formulario').show();
		if (record && record.getData) {
			edit.down('form').loadRecord(record);
			// edit.down('form').up('panel').down('multiselect').store = define;
		}
	},

	editarAnaliseMercadoButton : function(button) {
		var grid = this.getAnaliseMercadoGrid();
		var record = grid.getSelectionModel().getSelection();

		var edit = Ext.create('Liproma.view.analisemercado.Formulario').show();
		if (record && record.getData) {
			edit.down('form').loadRecord(record);
		}
	},

	atualizarAnaliseMercadoForm : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var record = form.getRecord();
		var values = form.getValues();

		if (values.id > 0) {
			record.set(values);
		} else {
			record = Ext.create('Liproma.model.AnaliseMercado');
			record.set(values);
			this.getAnaliseMercadoStore().add(record);
			novo = true;
		}

		win.close();
		this.getAnaliseMercadoStore().sync();

		// if (novo) {// faz reload para atualizar
		// this.getAnaliseMercadoStore().load();
		// }
	},

	deletarAnaliseMercado : function(button) {
		var grid = this.getAnaliseMercadoGrid();
		var record = grid.getSelectionModel().getSelection();
		var store = this.getAnaliseMercadoStore();
		var me = this;

		Ext.MessageBox.confirm('Confirmação',
				'Quer mesmo deletar a Análise de Mercado?', function(btn) {
					if (btn == 'yes') {
						store.remove(record);
						me.getAnaliseMercadoStore().sync();

						// faz reload para atualizar
						// me.getAnaliseMercadoStore().load();
						Ext.MessageBox.alert('Mensagem',
								'Registro deletado com sucesso!');
					}
				});

	},
	
	alterandoItensGrid: function(grid, record, item, index){
		var itemSelecionado = grid.getStore().getAt(index);
		 //Ext.ComponentQuery.query('analisemercadoform #cityCombo')[0]
		//Ext.Msg.alert('Status', 'Changes saved successfully.');
	}
});