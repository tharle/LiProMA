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

			items : [ {
				xtype : 'fieldcontainer',
				defaultType : 'checkboxfield',
				items : [ {
					boxLabel : 'Principal',
					name : 'topping',
					inputValue : '1',
					id : 'checkbox1'
				} ]
			}, {
				anchor : '100%',
				xtype : 'multiselect',
				msgTarget : 'side',
				fieldLabel : 'Dominios',
				name : 'dominiosmultfeature',
				id : 'dominiosmultfeature',
				allowBlank : false,
				store : Ext.create('Liproma.store.MultiSelect'),
				displayField : 'text',
				valueField : 'value',
				value : [ '1', '2' ],
				ddReorder : true
			}, {
				xtype : 'combo',
				id : 'cmbfeature',
				name : 'idFeaturePai',
				fieldLabel : 'Selecione o Backlog de Escopo:',
				displayField : 'nome',
				valueField : 'id',
				store : Ext.create('Liproma.store.Feature'),
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
				xtype : 'textfield',
				name : 'prioridade',
				fieldLabel : 'Prioridade',
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
	}
});