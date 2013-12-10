//-- Produto
var _beProdutoArray = new Array();
var beProdutoStore = new Ext.data.JsonStore({
	// url : '/ListarDominio.form',
	root : 'results',
	baseParams : {
		action : 'mgr/myModel/getList'
	},
	fields : [ "id", "nome", "selecionado" ],
	autoLoad : false,
	listeners : {
		load : function(t, records, options) {
			_beProdutoArray = new Array();
			if (records) {
				for ( var i = 0; i < records.length; i++) {
					_beProdutoArray.push({
						name : "produtoValores[" + i + "]",
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
			read : 'ListarProduto.form',
		},
		reader : {
			type : 'json',
			root : 'produtos',
			sucessProperty : 'sucess'
		}
	}
});
// -- Produto
// -- Feature
var _beFeatureArray = new Array();
var befeatureStore = new Ext.data.JsonStore({
	// url : '/ListarFeature.form',
	root : 'results',
	baseParams : {
		action : 'mgr/myModel/getList'
	},
	fields : [ "id", "nome", "selecionado" ],
	autoLoad : false,
	listeners : {
		load : function(t, records, options) {
			_beFeatureArray = new Array();
			if (records) {
				for ( var i = 0; i < records.length; i++) {
					_beFeatureArray.push({
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
			read : 'ListarFeature.form',
		},
		reader : {
			type : 'json',
			root : 'features',
			sucessProperty : 'sucess'
		}
	}
});
// --Feature

befeatureStore.load({
	params : {
		featurePrincipal : "true",
	}
});
beProdutoStore.load();

Ext.define('Liproma.view.backlogescopo.Formulario', {
	extend : 'Ext.window.Window',
	alias : 'widget.backlogescopoform',

	requires : [ 'Ext.form.Panel', 'Ext.form.field.Text' ],

	title : 'Criar Backlog Escopo',
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
				xtype : 'textareafield',
				name : 'descricao',
				fieldLabel : 'Descrição',
				height : 80
			}, {
				xtype : 'panel',
				collapsible : true,
				collapsed : true,
				id : 'panelckbbeproduto',
				title : 'Produtos',
				items : [ {
					xtype : 'checkboxgroup',
					// fieldLabel : 'Dominios',
					id : 'ckbbeproduto',
					name : 'produtoValores',
					columns : 2,
					vertical : true,
					items : _beProdutoArray
				} ]
			}, {
				xtype : 'panel',
				collapsible : true,
				collapsed : true,
				title : 'Features',
				items : [ {
					xtype : 'checkboxgroup',
					// fieldLabel : 'Dominios',
					id : 'ckbbefeature',
					name : 'featureValores',
					columns : 2,
					vertical : true,
					items : _beFeatureArray
				} ]
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
	atualizandoBEFeature : function(id) {

		beProdutoStore.load();

		befeatureStore.load({
			params : {
				featurePrincipal : "true",
			}
		});

		// var idAm =
		// Ext.util.Cookies.get('analiseMercadoSelecionada');

		// befeatureStore.load({
		// params : {
		// produtoId : id
		// },
		// callback : function(records, op, success) {
		// var domckb = [];
		// var ckbs = Ext.ComponentQuery.query('checkboxgroup');
		// var idTeste = "ckbfeatureproduto";
		// for ( var i = 0; i < ckbs.length; i++) {
		// if (ckbs[i].id == idTeste) {
		// domckb = ckbs[i];
		// }
		// }
		// for ( var j = 0; j < domckb.items.length; j++) {
		// for ( var k = 0; k < records.length; k++) {
		// var valor = records[k].getData();
		// if (domckb.items.items[j].inputValue == valor.id) {
		// domckb.items.items[j].setValue(valor.selecionado);
		// }
		// }
		// }
		//
		// },
		// scope : this
		//
		// });
	}
});