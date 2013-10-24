Ext.define('Liproma.view.backlogescopo.Formulario', {
	extend : 'Ext.window.Window',
	alias : 'widget.featureform',

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
				anchor : '100%',
				xtype : 'multiselect',
				msgTarget : 'side',
				fieldLabel : 'Features',
				name : 'dominiosmultdominio2',
				id : 'dominiosmultdominio2',
				allowBlank : false,
				store : Ext.create('Liproma.store.MultiSelectFeature'),
				displayField : 'text',
				valueField : 'value',
				value : [ '1' ],
				ddReorder : true
			}, {
				xtype : 'textareafield',
				name : 'descricao',
				fieldLabel : 'Descri��o',
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