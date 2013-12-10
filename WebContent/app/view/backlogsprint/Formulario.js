// -- Feature
var _featureBSprintArray = new Array();
var _featureBS = new Ext.data.JsonStore({
	// url : '/ListarFeature.form',
	root : 'results',
	baseParams : {
		action : 'mgr/myModel/getList'
	},
	fields : [ "id", "nome", "selecionado" ],
	autoLoad : false,
	listeners : {
		load : function(t, records, options) {
			
			_featureBSprintArray = new Array();
			if (records) {
				for ( var i = 0; i < records.length; i++) {
					_featureBSprintArray.push({
						name : "featureValores[" + i + "]",
						inputValue : records[i].data.id,
						boxLabel : records[i].data.nome
					});
				}
			}
		}
	},
	params : {
		idBacklogEscopo:  Ext.util.Cookies.get('sprintEscopo'),
	},
	proxy : {
		type : 'ajax',
		api : {
			read : 'ListarFeatureBacklogSprint.form',
		},
		reader : {
			type : 'json',
			root : 'sprintFeatures',
			sucessProperty : 'sucess'
		}
	}
});
// --Feature
_featureBS.load();
Ext.define('Liproma.view.backlogsprint.Formulario', {
	extend : 'Ext.window.Window',
	alias : 'widget.backlogsprintform',

	requires : [ 'Ext.form.Panel', 'Ext.form.field.Text' ],

	title : 'Criar Backlog Sprint',
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
				name : 'idBacklogEscopo',
				fieldLabel : 'idBacklogEscopo',
				hidden:true
			},{
				xtype : 'textfield',
				name : 'descricao',
				fieldLabel : 'Descrição',
			},{
				xtype : 'textfield',
				name : 'localReuniao',
				fieldLabel : 'Local Reunião',
			},{
		        xtype: 'datefield',
		        fieldLabel: 'Data Inicio',
		        name: 'dataInicio',
		        //maxValue: new Date()  // limited to the current date or prior
		    },{
		        xtype: 'datefield',
		        fieldLabel: 'Data Fim',
		        name: 'dataFim',
		      //  maxValue: new Date()  // limited to the current date or prior
		    },{
				xtype : 'panel',
				collapsible : true,
				collapsed : true,
				title : 'Features',
				items : [ {
					xtype : 'checkboxgroup',
					// fieldLabel : 'Dominios',
					id : 'ckbbsfeature',
					name : 'featureValores',
					columns : 2,
					vertical : true,
					items : _featureBSprintArray
				} ]
			},
			]
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
	},atualizandoBS : function(idSprint) {
		// var idAm =
		// Ext.util.Cookies.get('analiseMercadoSelecionada');
		var idBacklogEscopo = Ext.util.Cookies.get('sprintEscopo');
		if (!idBacklogEscopo)
			idBacklogEscopo = 0;

		_featureBS.load({
			params : {
				idSprint : idSprint,
				idBacklogEscopo: idBacklogEscopo,
			},
			callback : function(records, op, success) {
				var domckb = [];
				var ckbs = Ext.ComponentQuery.query('checkboxgroup');
				var idTeste = "ckbbsfeature";
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