var featureDominioArray = new Array();

var featureDominioStore = new Ext.data.JsonStore({
	// url : '/ListarDominio.form',
	root : 'results',
	baseParams : {
		action : 'mgr/myModel/getList'
	},
	fields : [ "id", "nome", "selecionado" ],
	autoLoad : false,
	listeners : {
		load : function(t, records, options) {
			featureDominioArray = new Array();
			for ( var i = 0; i < records.length; i++) {
				featureDominioArray.push({
					name : "dominioValores[" + i + "]",
					inputValue : records[i].data.id,
					boxLabel : records[i].data.nome
				});
			}
		}
	},
	proxy : {
		type : 'ajax',
		api : {
			read : 'ListarFeatureDominio.form',
		},
		reader : {
			type : 'json',
			root : 'featureDominios',
			sucessProperty : 'sucess'
		}
	}
});

featureDominioStore.load();

Ext.define('Liproma.view.feature.Formulario', {
	extend : 'Ext.window.Window',
	alias : 'widget.featureform',

	requires : [ 'Ext.form.Panel', 'Ext.form.field.Text' ],

	title : 'Editar/Criar Feature',
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

			items : [
					
					{

						xtype : 'checkboxfield',
						boxLabel : 'Principal',
						inputValue : true,
						id : 'cbPrincipal',
						name : 'principal'

					},
					{

						xtype : 'checkboxfield',
						boxLabel : 'Obrigatoria',
						inputValue : true,
						id : 'cbObrigatoria',
						name : 'obrigatoria'

					},
					{
						xtype : 'panel',
						collapsible : true,
						collapsed : true,
						title : 'Dominios',
						items : [ {
							xtype : 'checkboxgroup',
							// fieldLabel : 'Dominios',
							id : 'ckbfeaturedominio',
							name : 'dominioValores',
							columns : 2,
							vertical : true,
							items : featureDominioArray
						} ]
					},
					{

						xtype : 'checkboxfield',
						boxLabel : 'Feature Pai:',
						inputValue : true,
						name : 'possuiFeaturePai',
						id : 'cbFeaturePai',
						listeners : {
							change : function(me, newValue, oldValue, eOpts) {
								var combo = me.up("form").getChildByElement(
										'cmbfeature');
								if (newValue) {
									combo.enable(true);
								} else {
									combo.disable(true);
								}
							}
						}
					// name : 'featurePai'
					},
					{
						xtype : 'combo',
						id : 'cmbfeature',
						name : 'idFeaturePai',
						disabled : true,
						displayField : 'nome',
						valueField : 'id',
						store : Ext.create('Liproma.store.Feature'),
						triggerAction : 'all',
						queryMode : 'local',
					},
					{
						xtype : 'checkboxfield',
						boxLabel : 'Ponto Variação:',
						inputValue : true,
						name : 'possuiPontoVariacao',
						id : 'cbPontoVariacao',
						listeners : {
							change : function(me, newValue, oldValue, eOpts) {
								var combo = me.up("form").getChildByElement(
										'cmbPontoVariacao');
								if (newValue) {
									combo.enable(true);
								} else {
									combo.disable(true);
								}
							}
						}
					}, {
						xtype : 'combo',
						id : 'cmbPontoVariacao',
						name : 'pontoVariacao',
						disabled : true,
						displayField : 'descricao',
						valueField : 'id',
						store : Ext.create('Liproma.store.PontoVariacao'),
						triggerAction : 'all',
						queryMode : 'local',
					}, {
						xtype : 'textfield',
						name : 'id',
						fieldLabel : 'id',
						hidden : true
					}, {
						xtype : 'textfield',
						name : 'nome',
						fieldLabel : 'Nome',
					}, {
						xtype : 'textareafield',
						name : 'descricao',
						fieldLabel : 'Descrição',
						height : 80
					}, {
						xtype : 'combo',
						id : 'cmbPrioridade',
						name : 'prioridade',
						displayField : 'descricao',
						fieldLabel: 'Prioridade',
						valueField : 'id',
						store : Ext.create('Liproma.store.Prioridade'),
						triggerAction : 'all',
						queryMode : 'local',
					}, {
						xtype : 'textfield',
						name : 'bindingTime',
						fieldLabel : 'Binding Time',
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
	atualizandoFeatureDominios : function(id) {
		// var idAm =
		// Ext.util.Cookies.get('analiseMercadoSelecionada');

		featureDominioStore.load({
			params : {
				featureId : id
			},
			callback : function(records, op, success) {
				var domckb = [];
				var ckbs = Ext.ComponentQuery.query('checkboxgroup');
				var idTeste = "ckbfeaturedominio";
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