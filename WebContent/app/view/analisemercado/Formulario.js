Ext.define('Liproma.view.analisemercado.Formulario', {
	extend : 'Ext.window.Window',
	alias : 'widget.analisemercadoform',

	requires : [ 'Ext.form.Panel', 'Ext.form.field.Text' ],

	title : 'Editar/Criar Análise de Mercado',
	layout : 'fit',
	autoShow : true,
	width : 280,
	height : 400,
	iconCls : 'icon-grid',
	autoScroll: true,

	initComponent : function() {
		
		this.items = [ {
			xtype : 'form',
			padding : '5 5 0 5',
			border : false,
			style : 'background-color: #fff',

			fieldDefaults : {
				anchor : '100%',
				labelAlign : 'left',
				allowBlank : true,
				combineErros : true,
				msgTarget : 'side'
			},

			items : [ {
				xtype: 'textfield',
				name: 'id',
				fieldLabel:'id',
				hidden: true
			}, {
				xtype: 'textareafield',
				name: 'estrategiaMarketing',
				fieldLabel:'Estrategidas de Marketing',
				height:80,
				labelAlign: 'top'
			}, {
				xtype: 'textareafield',
				name: 'necessidadeMercado',
				fieldLabel:'Necessidades de Mercado',
				height:80,
				labelAlign: 'top'
			}, {
				xtype: 'textareafield',
				name: 'concorrencia',
				fieldLabel:'Concorrência',
				height:80,
				labelAlign: 'top'
			}, {
				xtype: 'textareafield',
				name: 'tecnologiaDesenvolvimento',
				fieldLabel:'Tecnologias de Desenvolvimento',
				height:80,
				labelAlign: 'top'
			}, {
				xtype: 'textareafield',
				name: 'ambienteComputacional',
				fieldLabel:'Ambiente Computacional',
				height:80,
				labelAlign: 'top'
			}, {
				xtype: 'textareafield',
				name: 'perfilCliente',
				fieldLabel:'Perfil dos Clientes',
				height:80,
				labelAlign: 'top'
			}, {
				xtype: 'textareafield',
				name: 'nivelHabilidade',
				fieldLabel:'Nivel de Habilidade',
				height:80,
				labelAlign: 'top'
			}, {
				xtype: 'textareafield',
				name: 'restricaoCultural',
				fieldLabel:'Restrições Culturais',
				height:80,
				labelAlign: 'top'
			}, {
				xtype: 'textareafield',
				name: 'tempoEntrega',
				fieldLabel:'Tempo de Entrega',
				height:80,
				labelAlign: 'top'
			}, {
				xtype: 'textareafield',
				name: 'objetivoNegocio',
				fieldLabel:'Objetivo de Negocio',
				height:80,
				labelAlign: 'top'
			},{
				xtype: 'textareafield',
				name: 'objetivoReuso',
				fieldLabel:'Objetivo de Reuso',
				height:80,
				labelAlign: 'top'
			}]
		} ];
		
		this.dockedItems = [{
			xtype: 'toolbar',
			dock: 'bottom',
			id: 'buttons',
			ui: 'footer',
			items:[ {
				iconCls:'icon-save',
				text: 'Salvar',
				action:'save'
				
			},{
				iconCls: 'icon-reset',
				text: 'Cancelar',
				scope: this,
				handler: this.close
			}]
		}];
		
		this.callParent(arguments);
	}
});