Ext.define('Liproma.controller.Produto', {
	extend : 'Ext.app.Controller',

	stores : [ 'Produto' ],

	models : [ 'Produto' ],

	views : [ 'produto.Formulario', 'produto.Grid' ],

	refs : [ {
		ref : 'produtoPanel',
		selector : 'panel'
	}, {
		ref : 'produtoGrid',
		selector : 'grid'
	} ],

	init : function() {
		this.control({
			'produtogrid dataview' : {
				itemdblclick : this.editarProduto
			},
			'produtogrid button[action=add]' : {
				click : this.editarProduto
			},
			'produtogrid button[action=delete]' : {
				click : this.deletarProduto
			},

			'produtogrid button[action=edit]' : {
				click : this.editarProdutoButton
			},
			'produtoform button[action=save]' : {
				click : this.atualizarProdutoForm
			}
		});
	},

	editarProduto : function(grid, record) {
		var edit = Ext.create('Liproma.view.produto.Formulario').show();
		if (record && record.getData) {
			edit.atualizandoProdutoDominios(record.getData().id);
			edit.atualizandoFeatureProdutos(record.getData().id);
			edit.down('form').loadRecord(record);
		}
	},

	editarProdutoButton : function(button) {
		var grid = this.getProdutoGrid();
		var record = grid.getSelectionModel().getSelection();

		var edit = Ext.create('Liproma.view.produto.Formulario').show();
		if (record && record.getData) {
			edit.down('form').loadRecord(record);
		}
	},

	atualizarProdutoForm : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var record = form.getRecord();
		var values = form.getValues();
		var novo = false;
		//-- Dominio
		var dominiosSelecionados = new Array();
		var domckb = form.getChildByElement('ckbprodutodominio').down('checkboxgroup');
		for ( var i = 0; i < domckb.items.length; i++) {
			if (domckb.items.items[i].checked) {
				dominiosSelecionados.push(domckb.items.items[i].inputValue);
			}
		}
		values.dominioValores = dominiosSelecionados;
		//---
		//--Feature
		var featureSelecionados = new Array();
		var fetckb = form.getChildByElement('ckbfeatureproduto').down('checkboxgroup');
		for ( var i = 0; i < fetckb.items.length; i++) {
			if (fetckb.items.items[i].checked) {
				featureSelecionados.push(fetckb.items.items[i].inputValue);
			}
		}
		values.featureValores = featureSelecionados;
		//---
		if (values.id > 0) {
			record.set(values);
		} else {
			record = Ext.create('Liproma.model.Produto');
			record.set(values);
			this.getProdutoStore().add(record);
			novo = true;
		}

		win.close();
		this.getProdutoStore().sync();

		if (novo) {// faz reload para atualizar
			this.getProdutoStore().load();
		}
	},

	deletarProduto : function(button) {
		var grid = this.getProdutoGrid();
		var record = grid.getSelectionModel().getSelection();
		var store = this.getProdutoStore();
		var me = this;

		Ext.MessageBox.confirm('Confirmação',
				'Quer mesmo deletar o Produto ?', function(btn) {
					if (btn == 'yes') {
						store.remove(record);
						me.getProdutoStore().sync();

						//  faz reload para atualizar
						//me.getAnaliseMercadoStore().load();
						Ext.MessageBox.alert('Mensagem',
								'Produto deletado com sucesso!');
					}
				});
	}
});