Ext.define('Liproma.view.dominio.Formulario', {
	extend : 'Ext.window.Window',
	alias : 'widget.dominioform',

	requires : [ 'Ext.form.Panel', 'Ext.form.field.Text' ],

	title : 'Editar/Criar Dominio',
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
				xtype : 'textfield',
				name : 'nome',
				fieldLabel : 'Nome',
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