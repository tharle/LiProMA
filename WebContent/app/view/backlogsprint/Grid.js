Ext.define('Liproma.view.backlogsprint.Grid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.backlogsprintgrid',

	requires : [ 'Ext.toolbar.Paging' ],

	iconCls : 'icon-grid',
	title : 'Backlog Sprint',
	store : 'BacklogSprint',
	renderTo : Ext.getBody(),

	columns : [ {
		header : 'FEATURES PRINCIPAIS',
		width : 170,
		flex : 1,
		dataIndex : 'featuresSelecionadas'
	}, {
		header : 'TAREFAS',
		width : 170,
		flex : 1,
		dataIndex : 'tarefa'
	}, {
		header : 'FEATURE',
		width : 170,
		flex : 1,
		dataIndex : 'feature'
	}, {
		header : 'RESPONSÁVEIS',
		width : 170,
		flex : 1,
		dataIndex : 'responsaveis'
	}, {
		header : 'STATUS',
		width : 170,
		flex : 1,
		dataIndex : 'statusTarefa'
	}],

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
			store : 'BacklogSprint',
			displayInfo : true,
			displayMsg : 'Mostrando Tarefa(s)  {0} - {1} de {2}',
			emptyMsg : 'Nenhuma Tarefa encontrada.'
		} ];
		this.callParent(arguments);
	}
});