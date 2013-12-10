var dominioArray = new Array();

var dominioStore = new Ext.data.JsonStore({
	// url : '/ListarDominio.form',
	root : 'results',
	baseParams : {
		action : 'mgr/myModel/getList'
	},
	fields : [ "id", "nome", "selecionado" ],
	autoLoad : false,
	listeners : {
		load : function(t, records, options) {
			dominioArray = new Array();
			for ( var i = 0; i < records.length; i++) {
				dominioArray.push({
					name : "dominioValores[" + i + "]",
					inputValue : records[i].data.id,
					boxLabel : records[i].data.nome
				});
			}
		}
	},sortInfo : {
		field : 'nome',
		direction : 'ASC'
	},
	proxy : {
		type : 'ajax',
		api : {
			create : 'CriarDominio.form',
			read : 'ListarDominioAnaliseMercado.form',
			update : 'AtualizarDominio.form',
			destroy : 'DeletarDominio.form'
		},
		reader : {
			type : 'json',
			root : 'dominios',
			sucessProperty : 'sucess'
		},
		writer : {
			type : 'json',
			writeAllFields : true,
			root : 'dominios'
		}
	}
});

dominioStore.load();

Ext.define('Liproma.view.analisemercado.Formulario', {
	extend : 'Ext.window.Window',
	alias : 'widget.analisemercadoform',

	requires : [ 'Ext.form.Panel', 'Ext.form.field.Text' ],

	title : 'Editar/Criar Análise de Mercado',
	iconCls : 'icon-grid',
	id : 'analisemercadoformulariodialog',

	initComponent : function() {

		this.items = [ {
			xtype : 'form',
			// id:'analisemercadocampos',
			width : 400,
			height : 500,
			autoShow : true,
			border : false,
			style : 'background-color: #fff',
			overflowY : 'auto',

			fieldDefaults : {
				anchor : '100%',
				labelAlign : 'left',
				allowBlank : true,
				combineErros : true,
				msgTarget : 'side',
				labelAlign : 'top',
			},

			items : [ {
				xtype : 'panel',
				border : 0,
				columnWidth : 0.5,

				items : [{
					xtype : 'panel',
					collapsible: true,
					collapsed: true,
					title: 'Dominios',
					items : [ {
						xtype : 'checkboxgroup',
						id : 'ckbdominioanalisemercado',
						name : 'dominioValores',
						columns : 2,
						vertical : true,
						items : dominioArray
					} ]
				}, {
					xtype : 'textfield',
					name : 'id',
					fieldLabel : 'id',
					hidden : true
				}, {
					xtype : 'textareafield',
					name : 'estrategiaMarketing',
					fieldLabel : 'Estrategidas de Marketing',
					width : 490
				}, {
					xtype : 'textareafield',
					name : 'necessidadeMercado',
					fieldLabel : 'Necessidades de Mercado',
					width : 490
				}, {
					xtype : 'textareafield',
					name : 'concorrencia',
					fieldLabel : 'Concorrência',
					width : 490
				}, {
					xtype : 'textareafield',
					name : 'tecnologiaDesenvolvimento',
					fieldLabel : 'Tecnologias de Desenvolvimento',
					width : 490
				}, {
					xtype : 'textareafield',
					name : 'ambienteComputacional',
					fieldLabel : 'Ambiente Computacional',
					width : 490
				}, {
					xtype : 'textareafield',
					name : 'perfilCliente',
					fieldLabel : 'Perfil dos Clientes',
					width : 490
				}, {
					xtype : 'textareafield',
					name : 'nivelHabilidade',
					fieldLabel : 'Nivel de Habilidade',
					width : 490
				}, {
					xtype : 'textareafield',
					name : 'restricaoCultural',
					fieldLabel : 'Restrições Culturais',
					width : 490
				}, {
					xtype : 'textareafield',
					name : 'tempoEntrega',
					fieldLabel : 'Tempo de Entrega',
					width : 490
				}, {
					xtype : 'textareafield',
					name : 'objetivoNegocio',
					fieldLabel : 'Objetivo de Negocio',
					height : 80,
					width : 490
				}, {
					xtype : 'textareafield',
					name : 'objetivoReuso',
					fieldLabel : 'Objetivo de Reuso',
					width : 490
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
	atualizandoDominios : function(idAm) {
		// var idAm =
		// Ext.util.Cookies.get('analiseMercadoSelecionada');

		dominioStore.load({
			params : {
				analiseMercadoId : idAm
			},
			callback : function(records, op, success) {
				var domckb = [];
				var ckbs = Ext.ComponentQuery.query('checkboxgroup');
				// ckbdominioanalisemercado
				 var idTeste = "ckbdominioanalisemercado";
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