Ext.define('Liproma.view.tarefa.Grid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.tarefagrid',

	requires : [ 'Ext.toolbar.Paging' ],

	iconCls : 'icon-grid',
	title : 'Tarefas',
	store : 'Tarefa',
	renderTo : Ext.getBody(),

	columns : [ {
		header : 'BACKLOG ESCOPO',
		width : 170,
		flex : 1,
		dataIndex : 'backlogEscopoNome'
	},{
		header : 'BACKLOG SPRINT',
		width : 170,
		flex : 1,
		dataIndex : 'backlogSprintNome'
	}, {
		header : 'DESCRICAO',
		width : 170,
		flex : 1,
		dataIndex : 'descricao'
	}, {
		header : 'RESPONSAVEIS',
		width : 170,
		flex : 1,
		dataIndex : 'responsavelNomes'
	}, {
		header : 'STATUS',
		width : 170,
		flex : 1,
		dataIndex : 'statusNome'
	}],

	initComponent : function() {
		this.dockedItems = [ {
			xtype : 'toolbar',
			items : [ {
				iconCls : 'icon-add',
				text : 'Adicionar',
				action : 'add'
			}, {
				iconCls : 'icon-edit',
				text : 'Editar',
				action : 'edit'
			}  ]
		}, {
			xtype : 'pagingtoolbar',
			dock : 'top',
			store : 'Tarefa',
			displayInfo : true,
			displayMsg : 'Mostrando Tarefa(s) {0} - {1} de {2}',
			emptyMsg : 'Nenhum Tarefa encontrado.'
		} ];
		this.callParent(arguments);
	}
});