//-- Dominio
var _produtoDominioArray = new Array();
var produtoDominioStore = new Ext.data.JsonStore({
	// url : '/ListarDominio.form',
	root : 'results',
	baseParams : {
		action : 'mgr/myModel/getList'
	},
	fields : [ "id", "nome", "selecionado" ],
	autoLoad : false,
	listeners : {
		load : function(t, records, options) {
			_produtoDominioArray = new Array();
			if (records) {
				for ( var i = 0; i < records.length; i++) {
					_produtoDominioArray.push({
						name : "dominioValores[" + i + "]",
						inputValue : records[i].data.id,
						boxLabel : records[i].data.nome
					});
				}
			}
		}
	},
	proxy : {
		type : 'ajax',
		api : {
			read : 'ListarProdutoDominio.form',
		},
		reader : {
			type : 'json',
			root : 'produtoDominios',
			sucessProperty : 'sucess'
		}
	}
});
// --Dominio
// -- Feature
var _featureProdutoArray = new Array();
var featureProdutoStore = new Ext.data.JsonStore({
	// url : '/ListarFeature.form',
	root : 'results',
	baseParams : {
		action : 'mgr/myModel/getList'
	},
	fields : [ "id", "nome", "selecionado" ],
	autoLoad : false,
	listeners : {
		load : function(t, records, options) {
			_featureProdutoArray = new Array();
			if (records) {
				for ( var i = 0; i < records.length; i++) {
					_featureProdutoArray.push({
						name : "featureValores[" + i + "]",
						inputValue : records[i].data.id,
						boxLabel : records[i].data.nome
					});
				}
			}
		}
	},
	proxy : {
		type : 'ajax',
		api : {
			read : 'ListarFeatureProduto.form',
		},
		reader : {
			type : 'json',
			root : 'produtoFeatures',
			sucessProperty : 'sucess'
		}
	}
});
// --Feature

produtoDominioStore.load();
featureProdutoStore.load();

Ext.define('Liproma.view.produto.Formulario', {
	extend : 'Ext.window.Window',
	alias : 'widget.produtoform',

	requires : [ 'Ext.form.Panel', 'Ext.form.field.Text' ],

	title : 'Editar/Criar Produto',
	iconCls : 'icon-grid',

	initComponent : function() {

		this.items = [ {
			xtype : 'form',
			width : 400,
			autoShow : true,
			padding : '5 5 0 5',
			border : false,
			style : 'background-color: #fff',

			fieldDefaults : {
				anchor : '100%',
				labelAlign : 'left',
				allowBlank : true,
				combineErros : true,
				msgTarget : 'side',
				labelAlign : 'top',
			},

			items : [ {
				xtype : 'textfield',
				name : 'id',
				fieldLabel : 'id',
				hidden : true
			}, {
				xtype : 'panel',
				collapsible : true,
				collapsed : true,
				title : 'Dominios',
				items : [ {
					xtype : 'checkboxgroup',
					// fieldLabel : 'Dominios',
					id : 'ckbprodutodominio',
					name : 'dominioValores',
					columns : 2,
					vertical : true,
					items : _produtoDominioArray
				} ]
			},{
				xtype : 'panel',
				collapsible : true,
				collapsed : true,
				title : 'Features',
				items : [ {
					xtype : 'checkboxgroup',
					// fieldLabel : 'Dominios',
					id : 'ckbfeatureproduto',
					name : 'featureValores',
					columns : 2,
					vertical : true,
					items : _featureProdutoArray
				} ]
			}, {
				xtype : 'textfield',
				name : 'nome',
				fieldLabel : 'Nome',
			}, {
				xtype : 'textareafield',
				name : 'descricao',
				fieldLabel : 'Descrição',
				height : 80
			} ]
		} ];

		this.dockedItems = [ {
			xtype : 'toolbar',
			dock : 'bottom',
			id : 'buttons',
			ui : 'footer',
			items : [ {
				iconCls : 'icon-save',
				text : 'Salvar',
				action : 'save'

			}, {
				iconCls : 'icon-reset',
				text : 'Cancelar',
				scope : this,
				handler : this.close
			} ]
		} ];

		this.callParent(arguments);
	},
	atualizandoProdutoDominios : function(id) {
		// var idAm =
		// Ext.util.Cookies.get('analiseMercadoSelecionada');

		produtoDominioStore.load({
			params : {
				produtoId : id
			},
			callback : function(records, op, success) {
				var domckb = [];
				var ckbs = Ext.ComponentQuery.query('checkboxgroup');
				var idTeste = "ckbprodutodominio";
				for ( var i = 0; i < ckbs.length; i++) {
					if (ckbs[i].id == idTeste) {
						domckb = ckbs[i];
					}
				}
				for ( var j = 0; j < domckb.items.length; j++) {
					for ( var k = 0; k < records.length; k++) {
						var valor = records[k].getData();
						if (domckb.items.items[j].inputValue == valor.id) {
							domckb.items.items[j].setValue(valor.selecionado);
						}
					}
				}

			},
			scope : this

		});
	},
	atualizandoFeatureProdutos : function(id) {
		// var idAm =
		// Ext.util.Cookies.get('analiseMercadoSelecionada');

		featureProdutoStore.load({
			params : {
				produtoId : id
			},
			callback : function(records, op, success) {
				var domckb = [];
				var ckbs = Ext.ComponentQuery.query('checkboxgroup');
				var idTeste = "ckbfeatureproduto";
				for ( var i = 0; i < ckbs.length; i++) {
					if (ckbs[i].id == idTeste) {
						domckb = ckbs[i];
					}
				}
				for ( var j = 0; j < domckb.items.length; j++) {
					for ( var k = 0; k < records.length; k++) {
						var valor = records[k].getData();
						if (domckb.items.items[j].inputValue == valor.id) {
							domckb.items.items[j].setValue(valor.selecionado);
						}
					}
				}

			},
			scope : this

		});
	}
});