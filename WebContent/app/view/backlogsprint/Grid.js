var _sprintStore =  Ext.create('Liproma.store.BacklogSprint');

Ext.define('Liproma.view.backlogsprint.Grid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.backlogsprintgrid',

	requires : [ 'Ext.toolbar.Paging' ],

	iconCls : 'icon-grid',
	title : 'Backlog Sprint',
	store : _sprintStore,
	renderTo : Ext.getBody(),

	columns : [ {
		header : 'DESCRIÇÃO',
		width : 170,
		flex : 1,
		dataIndex : 'descricao'
	},{
		header : 'FEATURES PRINCIPAIS',
		width : 170,
		flex : 1,
		dataIndex : 'featureNomes'
	},{
		header : 'RESPONSAVEIS',
		width : 170,
		flex : 1,
		dataIndex : 'featureNomes'
	}, {
		header : 'LOCAL REUNIÃO',
		width : 170,
		flex : 1,
		dataIndex : 'localReuniao'
	}, {
		header : 'DATA INICIO',
		width : 170,
		flex : 1,
		dataIndex : 'dataInicio'
	}, {
		header : 'DATA FIM',
		width : 170,
		flex : 1,
		dataIndex : 'dataFim'
	},
//	{
//		header : 'STATUS',
//		width : 170,
//		flex : 1,
//		dataIndex : 'statusTarefa'
//	}
	],

	initComponent : function() {
		this.dockedItems = [ {
			xtype : 'toolbar',
			items : [ {
				iconCls : 'icon-add',
				text : 'Adicionar',
				action : 'add'
			},
//			{
//				iconCls : 'icon-delete',
//				text : 'Excluir',
//				action : 'delete'
//			}, 
			{
				iconCls : 'icon-edit',
				text : 'Editar',
				action : 'edit'
			} ]
		}, {
			xtype : 'pagingtoolbar',
			dock : 'top',
			store : _sprintStore,
			displayInfo : true,
			displayMsg : 'Mostrando Tarefa(s)  {0} - {1} de {2}',
			emptyMsg : 'Nenhuma Tarefa encontrada.'
		} ];
		this.callParent(arguments);
	}
});