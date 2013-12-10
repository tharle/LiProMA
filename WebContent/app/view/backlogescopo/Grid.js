Ext.define('Liproma.view.backlogescopo.Grid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.backlogescopogrid',

	requires : [ 'Ext.toolbar.Paging' ],

	iconCls : 'icon-grid',
	title : 'Backlog Escopos',
	store : 'BacklogEscopo',
	renderTo : Ext.getBody(),

	columns : [ {
		header : 'DESCRIÇÃO',
		width : 170,
		flex : 1,
		dataIndex : 'descricao'
	}, {
		header : 'FEATURE',
		width : 170,
		flex : 1,
		dataIndex : 'featureNomes'
	}],

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
			}
			]
		}, {
			xtype : 'pagingtoolbar',
			dock : 'top',
			store : 'BacklogEscopo',
			displayInfo : true,
			displayMsg : 'Mostrando Backlog Escopo(s) {0} - {1} de {2}',
			emptyMsg : 'Nenhum Backlog Escopo encontrado.'
		} ];
		this.callParent(arguments);
	}
});