Ext.define('Liproma.view.responsavel.Grid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.responsavelgrid',

	requires : [ 'Ext.toolbar.Paging' ],

	iconCls : 'icon-grid',
	title : 'Responsaveis',
	store : 'Responsavel',
	renderTo : Ext.getBody(),

	columns : [ {
		header : 'NOME',
		width : 170,
		flex : 1,
		dataIndex : 'nome'
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
			store : 'Responsavel',
			displayInfo : true,
			displayMsg : 'Mostrando Responsavel(is) {0} - {1} de {2}',
			emptyMsg : 'Nenhum Responsavel encontrado.'
		} ];
		this.callParent(arguments);
	}
});