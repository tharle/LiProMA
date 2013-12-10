Ext.define('Liproma.view.analisemercado.Grid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.analisemercadogrid',

	requires : [ 'Ext.toolbar.Paging' ],

	iconCls : 'icon-grid',
	title : 'Análise de Mercado',
	store : 'AnaliseMercado',
	renderTo : Ext.getBody(),
id: 'analisemercadogridid',

	columns : [ {
		header : 'DOMINIOS',
		width : 170,
		flex : 1,
		dataIndex : 'dominioNomes',
		// dataIndex : 'value'
		valueField : 'descricao'
	}, {
		header : 'ESTRATEGIAS DE MARKETING',
		width : 170,
		flex : 1,
		dataIndex : 'estrategiaMarketing'
	}, {
		header : 'NECESSIDADES DE MERCADO',
		width : 170,
		flex : 1,
		dataIndex : 'necessidadeMercado'
	}, {
		header : 'CONCORRÊNCIAS',
		width : 170,
		flex : 1,
		dataIndex : 'concorrencia'
	}, {
		text : 'TECNOLOGIAS EM DESENVOLVIMENTO',
		width : 170,
		flex : 1,
		dataIndex : 'tecnologiaDesenvolvimento'
	},

	{
		text : 'CARACTERISTICAS DOS USUÁRIOS',
		columns : [ {
			text : 'AMBIENTE COMPUTACIONAL',
			width : 170,
			flex : 1,
			dataIndex : 'ambienteComputacional'
		}, {
			text : 'PERFIL DOS CLIENTES',
			width : 170,
			flex : 1,
			dataIndex : 'perfilCliente'
		}, {
			text : 'NÍVEL DAS HABILIDADES',
			width : 170,
			flex : 1,
			dataIndex : 'nivelHabilidade'
		} ]
	}, {
		header : 'RESTRIÇÕES CULTURAL',
		width : 170,
		flex : 1,
		dataIndex : 'restricaoCultural'
	}, {
		header : 'TEMPO DE ENTREGA',
		width : 170,
		flex : 1,
		dataIndex : 'tempoEntrega'
	}, {
		header : 'OBJETIVO DE NEGOCIO',
		width : 170,
		flex : 1,
		dataIndex : 'objetivoNegocio'
	}, {
		header : 'OBJETIVO DE REUSO',
		width : 170,
		flex : 1,
		dataIndex : 'objetivoReuso'
	} ],

	initComponent : function() {
		this.dockedItems = [ {
			xtype : 'toolbar',
			items : [ {
				iconCls : 'icon-add',
				text : 'Adicionar',
				action : 'add'
			}, {
				iconCls : 'icon-delete',
				text : 'Excluir',
				action : 'delete'
			}, {
				iconCls : 'icon-edit',
				text : 'Editar',
				action : 'edit'
			} ]
		}, {
			xtype : 'pagingtoolbar',
			dock : 'top',
			store : 'AnaliseMercado',
			displayInfo : true,
			displayMsg : 'Mostrando Análise(s) de Mercado {0} - {1} de {2}',
			emptyMsg : 'Nenhuma Análise de Mercado encontrada.'
		} ];
		this.callParent(arguments);
	}
});