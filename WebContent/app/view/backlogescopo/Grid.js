Ext.define('Liproma.view.backlogescopo.Grid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.backlogescopogrid',

	requires : [ 'Ext.toolbar.Paging' ],

	iconCls : 'icon-grid',
	title : 'BacklogEscopos',
	store : 'BacklogEscopo',
	renderTo : Ext.getBody(),

	columns : [ {
		header : 'DESCRI��O',
		width : 170,
		flex : 1,
		dataIndex : 'descricao'
	}, {
		header : 'DOMINIOS',
		width : 170,
		flex : 1,
		dataIndex : 'dominios'
	}, {
		header : 'FEATURE',
		width : 170,
		flex : 1,
		dataIndex : 'feature'
	}, {
		header : 'PRIORIDADE',
		width : 170,
		flex : 1,
		dataIndex : 'prioridade'
	}, {
		header : 'ESTIMATIVA',
		width : 170,
		flex : 1,
		dataIndex : 'estimativa'
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
			}
//			, {
//				iconCls : 'icon-edit',
//				text : 'Editar',
//				action : 'edit'
//			}  
			]
		}, {
			xtype : 'pagingtoolbar',
			dock : 'top',
			store : 'BacklogEscopo',
			displayInfo : true,
			displayMsg : 'Mostrando BacklogEscopo(s) {0} - {1} de {2}',
			emptyMsg : 'Nenhum BacklogEscopo encontrado.'
		} ];
		this.callParent(arguments);
	}
});