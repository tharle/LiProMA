var _tarefaSprintStore = Ext.create('Liproma.store.BacklogSprint');
// -- Dominio
var _tarefaResponsavelArray = new Array();
var _tarefaResponsavelStore = new Ext.data.JsonStore({
	// url : '/ListarDominio.form',
	root : 'results',
	baseParams : {
		action : 'mgr/myModel/getList'
	},
	fields : [ "id", "nome", "selecionado" ],
	autoLoad : false,
	listeners : {
		load : function(t, records, options) {
			_tarefaResponsavelArray = new Array();
			if (records) {
				for ( var i = 0; i < records.length; i++) {
					_tarefaResponsavelArray.push({
						name : "responsavelValores[" + i + "]",
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
			read : 'ListarResponsavel.form',
		},
		reader : {
			type : 'json',
			root : 'responsaveis',
			sucessProperty : 'sucess'
		}
	}
});
// --Dominio
_tarefaResponsavelStore.load();

Ext.define('Liproma.view.tarefa.Formulario', {
	extend : 'Ext.window.Window',
	alias : 'widget.tarefaform',

	requires : [ 'Ext.form.Panel', 'Ext.form.field.Text' ],

	title : 'Editar/Criar Tarefa',
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
				xtype : 'textareafield',
				id: 'txttarefadescricao',
				name : 'descricao',
				fieldLabel : 'Descrição',
				height : 80
			}, {
				xtype : 'combo',
				id : 'cmbtarefaescopo',
				name : 'idBacklogEscopo',
				fieldLabel : 'Backlog Escopo',
				displayField : 'descricao',
				valueField : 'id',
				store : Ext.create('Liproma.store.BacklogEscopo'),
				triggerAction : 'all',
				queryMode : 'local',
			}, {
				xtype : 'combo',
				id : 'cmbtarefasprint',
				name : 'idBacklogSprint',
				fieldLabel : 'Backlog Sprint',
				disabled : true,
				displayField : 'descricao',
				valueField : 'id',
				store : _tarefaSprintStore,
				triggerAction : 'all',
				queryMode : 'local',
			}, {
				xtype : 'panel',
				collapsible : true,
				collapsed : true,
				title : 'Responsaveis',
				id : 'panelckbtarefaresponsavel',
				items : [ {
					xtype : 'checkboxgroup',
					// fieldLabel : 'Dominios',
					id : 'ckbtarefaresponsavel',
					name : 'responsavelValores',
					columns : 4,
					vertical : true,
					items : _tarefaResponsavelArray
				} ]
			}, {
				xtype : 'combo',
				id : 'cmbtarefastatus',
				name : 'status',
				fieldLabel : 'Status',
				hidden : true,
				displayField : 'descricao',
				valueField : 'id',
				store : Ext.create('Liproma.store.StatusTarefa'),
				triggerAction : 'all',
				queryMode : 'local',
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
	atualizandoReponsaveis : function(idTarefa) {
		// var idAm =
		// Ext.util.Cookies.get('analiseMercadoSelecionada');
		// if (!idEscopo)
		// idEscopo = 0;
		_tarefaResponsavelStore.load({
			params : {
				idTarefa : idTarefa,
			// idBacklogEscopo : idEscopo
			},
			callback : function(records, op, success) {
				var domckb = form.getChildByElement('ckbtarefaresponsavel')
						.down('checkboxgroup');
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