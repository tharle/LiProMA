Ext.define('Liproma.view.dominio.Grid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.dominiogrid',

	requires : [ 'Ext.toolbar.Paging' ],

	iconCls : 'icon-grid',
	title : 'Dominios',
	store : 'Dominio',
	renderTo : Ext.getBody(),

	columns : [ {
		header : 'NOME',
		width : 170,
		flex : 1,
		dataIndex : 'nome'
	}, {
		header : 'DESCRICAO',
		width : 170,
		flex : 1,
		dataIndex : 'descricao'
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
			}  ]
		}, {
			xtype : 'pagingtoolbar',
			dock : 'top',
			store : 'Dominio',
			displayInfo : true,
			displayMsg : 'Mostrando Dominio(s) {0} - {1} de {2}',
			emptyMsg : 'Nenhum Dominio encontrado.'
		} ];
		this.callParent(arguments);
	}
});