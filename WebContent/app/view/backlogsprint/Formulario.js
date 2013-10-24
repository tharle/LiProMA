var statusTarefa = Ext.create('Ext.data.Store', {
	fields : [ 'id', 'nome' ],
	data : [ {
		"id" : "1",
		"nome" : "Em andamento"
	}, {
		"id" : "2",
		"nome" : "Cancelada"
	}, {
		"id" : "3",
		"nome" : "Pronto"
	}]
});

Ext.define('Liproma.view.backlogsprint.Formulario', {
	extend : 'Ext.window.Window',
	alias : 'widget.backlogsprintgridform',

	requires : [ 'Ext.form.Panel', 'Ext.form.field.Text' ],

	title : 'Criar/Atualizar Tarefa',
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

			items : [{
				xtype : 'textfield',
				name : 'tarefa',
				fieldLabel : 'Descrição',
			}, 

			{
				xtype : 'combo',
				id : 'cmbfeature1',
				name : 'responsaveis1',
				fieldLabel : 'Status',
				displayField : 'nome',
				valueField : 'id',
				store : statusTarefa,
				triggerAction : 'all',
				queryMode : 'local',
			},

			{
				anchor : '100%',
				xtype : 'multiselect',
				msgTarget : 'side',
				fieldLabel : 'Features do Backlog de Escopo',
				name : 'dominiosmultdominio322',
				id : 'dominiosmultdominio322',
				allowBlank : false,
				store : Ext.create('Liproma.store.MultiSelectFeature'),
				displayField : 'text',
				valueField : 'value',
				value : [ '1' ],
				ddReorder : true
			}, {
				anchor : '100%',
				xtype : 'multiselect',
				msgTarget : 'side',
				fieldLabel : 'Responsáveis',
				name : 'dominiosmultdominio2333',
				id : 'dominiosmultdominio3332',
				allowBlank : false,
				store : Ext.create('Liproma.store.MultiSelect'),
				displayField : 'text',
				valueField : 'value',
				value : [ '1' ],
				ddReorder : true
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
	}
});