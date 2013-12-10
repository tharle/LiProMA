Ext.define('Liproma.controller.AnaliseMercado', {
	extend : 'Ext.app.Controller',

	stores : [ 'AnaliseMercado', 'Dominio' ],

	models : [ 'AnaliseMercado', 'Dominio' ],

	views : [ 'analisemercado.Formulario', 'analisemercado.Grid' ],

	refs : [ {
		ref : 'analiseMercadoPanel',
		selector : 'panel'
	}, {
		ref : 'analiseMercadoGrid',
		selector : 'grid'
	} ],

	init : function() {
		this.control({
			'analisemercadogrid dataview' : {
				itemdblclick : this.editarAnaliseMercado,
				itemclick : this.alterandoItensGrid
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
			},
			'analisemercadoform dataview' : {
				afterrender : this.alterandoItensGrid,

			}

		});
	},

	editarAnaliseMercado : function(grid, record) {
		//Salvando cookie da analise de mercado selecionada
		var edit = Ext.create('Liproma.view.analisemercado.Formulario').show();
		if (record && record.getData) {
			Ext.util.Cookies.set('analiseMercadoSelecionada', record.getData().id);
			edit.atualizandoDominios(record.getData().id);
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
		var novo = false;
		var dominiosSelecionados = new Array();
		var domckb = form.getChildByElement('ckbdominioanalisemercado').down('checkboxgroup');
		// /domckb.items.items[1].checked
		for ( var i = 0; i < domckb.items.length; i++) {
			if (domckb.items.items[i].checked) {
				dominiosSelecionados.push(domckb.items.items[i].inputValue);
			}
		}
		values.dominioValores = dominiosSelecionados;
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

		 if (novo) {// faz reload para atualizar
			this.getAnaliseMercadoStore().load();
		}
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

	alterandoItensGrid : function(grid, record, item, index) {
//		var domckb;
//		var ckbs = Ext.ComponentQuery.query('checkboxgroup');
//		// ckbdominioanalisemercado
//
//		for ( var i = 0; i < ckbs.items.length; i++) {
//			if (ckbs.id == 'ckbdominioanalisemercado') {
//				domckb = ckbs[i];
//				for ( var i = 0; i < domckb.items.length; i++) {
//					if (domckb.items.items[i].checked) {
//						dominiosSelecionados
//								.push(domckb.items.items[i].inputValue);
//					}
//				}
//				break;
//			}
//		}
		

	}
});