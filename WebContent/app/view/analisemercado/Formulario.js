Ext.define('Liproma.view.analisemercado.Formulario', {
	extend : 'Ext.window.Window',
	alias : 'widget.analisemercadoform',

	requires : [ 'Ext.form.Panel', 'Ext.form.field.Text',
			'Ext.ux.form.MultiSelect' ],

	title : 'Editar/Criar Análise de Mercado',
	iconCls : 'icon-grid',

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
				columnWidth: 0.5,
				items : [ 
				           {
					anchor : '100%',
					xtype : 'multiselect',
					msgTarget : 'side',
					fieldLabel : 'Dominios',
					name : 'dominiosmult',
					id : 'dominiosmult',
					allowBlank : false,
					store : Ext.create('Liproma.store.MultiSelect'),
					displayField : 'text',
					valueField : 'value',
					value : [ '1' ],
					ddReorder : true
				},{
					xtype : 'textfield',
					name : 'id',
					fieldLabel : 'id',
					hidden : true
				}, {
					xtype : 'textareafield',
					name : 'estrategiaMarketing',
					fieldLabel : 'Estrategidas de Marketing',
				}, {
					xtype : 'textareafield',
					name : 'necessidadeMercado',
					fieldLabel : 'Necessidades de Mercado',
				}, {
					xtype : 'textareafield',
					name : 'concorrencia',
					fieldLabel : 'Concorrência',
				}, {
					xtype : 'textareafield',
					name : 'tecnologiaDesenvolvimento',
					fieldLabel : 'Tecnologias de Desenvolvimento',
				}, {
					xtype : 'textareafield',
					name : 'ambienteComputacional',
					fieldLabel : 'Ambiente Computacional',
				}, {
					xtype : 'textareafield',
					name : 'perfilCliente',
					fieldLabel : 'Perfil dos Clientes',
				}, {
					xtype : 'textareafield',
					name : 'nivelHabilidade',
					fieldLabel : 'Nivel de Habilidade',
				}, {
					xtype : 'textareafield',
					name : 'restricaoCultural',
					fieldLabel : 'Restrições Culturais',
				}, {
					xtype : 'textareafield',
					name : 'tempoEntrega',
					fieldLabel : 'Tempo de Entrega',
				}, {
					xtype : 'textareafield',
					name : 'objetivoNegocio',
					fieldLabel : 'Objetivo de Negocio',
					height : 80,
				}, {
					xtype : 'textareafield',
					name : 'objetivoReuso',
					fieldLabel : 'Objetivo de Reuso',
				} ]

			}]
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