Ext.define('Liproma.view.analisemercado.Formulario', {
	extend : 'Ext.window.Window',
	alias : 'widget.analisemercadoform',

	requires : [ 'Ext.form.Panel', 'Ext.form.field.Text' ],

	title : 'Editar/Criar Análise de Mercado',
	iconCls : 'icon-grid',
	height : 500,

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
				height : 80
			},

			items : [ {
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