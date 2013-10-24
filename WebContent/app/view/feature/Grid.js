Ext.define('Liproma.view.feature.Grid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.featuregrid',

	requires : [ 'Ext.toolbar.Paging' ],

	iconCls : 'icon-grid',
	title : 'Features',
	store : 'Feature',
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
		header : 'PRIORIDADE',
		width : 170,
		flex : 1,
		dataIndex : 'prioridadeNome'
	}, {
		header : 'DESCRIÇÃO',
		width : 170,
		flex : 1,
		dataIndex : 'descricao'
	}, {
		header : 'FEATURE PAI',
		width : 170,
		flex : 1,
		dataIndex : 'featurePaiNome'
	},{
		header : 'BINDING TIME',
		width : 170,
		flex : 1,
		dataIndex : 'bindingTime'
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
			store : 'Feature',
			displayInfo : true,
			displayMsg : 'Mostrando Feature(s) {0} - {1} de {2}',
			emptyMsg : 'Nenhum Feature encontrado.'
		} ];
		this.callParent(arguments);
	}
});