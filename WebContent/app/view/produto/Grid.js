Ext.define('Liproma.view.produto.Grid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.produtogrid',

	requires : [ 'Ext.toolbar.Paging' ],

	iconCls : 'icon-grid',
	title : 'Produtos',
	store : 'Produto',
	renderTo : Ext.getBody(),

	columns : [ {
		header : 'DOMINIOS',
		width : 170,
		flex : 1,
		dataIndex : 'dominios'
	}, {
		header : 'NOME',
		width : 170,
		flex : 1,
		dataIndex : 'nome'
	}, {
		header : 'DESCRICAO',
		width : 170,
		flex : 1,
		dataIndex : 'descricao'
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
			store : 'Produto',
			displayInfo : true,
			displayMsg : 'Mostrando Produto(s) {0} - {1} de {2}',
			emptyMsg : 'Nenhum Produto encontrado.'
		} ];
		this.callParent(arguments);
	}
});