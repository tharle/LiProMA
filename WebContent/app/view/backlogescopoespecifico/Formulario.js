Ext.define('Liproma.view.backlogescopoespecifico.Formulario', {
	extend : 'Ext.window.Window',
	alias : 'widget.backlogescopoespecificoform',

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

			items : [{
				xtype : 'textareafield',
				name : 'id',
				fieldLabel : 'Feature',
				height : 80,
				hidden:true
			},{
				xtype : 'textareafield',
				name : 'idBacklogEscopo',
				fieldLabel : 'backlog escopo',
				height : 80,
				hidden:true
			}, {
				xtype : 'textareafield',
				name : 'estimativa',
				fieldLabel : 'Estimativa',
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
	}
});