var _backlogEscopoEspecificoStore = new Ext.data.JsonStore({
	// url : '/ListarDominio.form',
	root : 'results',
	baseParams : {
		action : 'mgr/myModel/getList'
	},
	fields : [ "id", "nome", "dominioNomes", "prioridadeNome", "descricao",
			"estimativa" ],
	autoLoad : false,
//	 listeners : {
//		load : function(t, records, options) {
////			featureDominioArray = new Array();
////			for ( var i = 0; i < records.length; i++) {
////				featureDominioArray.push({
////					name : "featureValores[" + i + "]",
////					inputValue : records[i].data.id,
////					boxLabel : records[i].data.nome
////				});
////			}
//		}
//	},
	proxy : {
		type : 'ajax',
		api : {
			read : 'ListarFeature.form',
		},
		reader : {
			type : 'json',
			root : 'featureBacklogEscopos',
			sucessProperty : 'sucess'
		}
	}
});

//featureBacklogEscopo.load({
//	params : {
//		idBacklogEscopo : _backlogEscopoSelecionada
//	}
//});

Ext.define('Liproma.view.backlogescopoespecifico.Grid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.backlogescopoespecificogrid',

	requires : [ 'Ext.toolbar.Paging' ],

	iconCls : 'icon-grid',
	title : 'Features do Backlog de Escopo',
	store : _backlogEscopoEspecificoStore,
	autoShow : false,
	renderTo : Ext.getBody(),

	columns : [ {
		header : 'FEATURE',
		width : 170,
		flex : 1,
		dataIndex : 'nome'
	}, {
		header : 'DOMINIOS',
		width : 170,
		flex : 1,
		dataIndex : 'dominioNomes'
	},  {
		header : 'PRIORIDADE',
		width : 170,
		flex : 1,
		dataIndex : 'prioridadeNome'
	}, {
		header : 'ESTIMATIVA',
		width : 170,
		flex : 1,
		dataIndex : 'estimativa'
	} ],

	initComponent : function() {
		this.dockedItems = [ {
			xtype : 'toolbar',
			items : [
			// {
			// iconCls : 'icon-add',
			// text : 'Adicionar',
			// action : 'add'
			// }
			// , {
			// iconCls : 'icon-edit',
			// text : 'Editar',
			// action : 'edit'
			// }
			]
		}, {
			xtype : 'pagingtoolbar',
			dock : 'top',
			// store : 'BacklogEscopo',

			store : _backlogEscopoEspecificoStore,

			displayInfo : true,
			displayMsg : 'Mostrando Feature(s) do Backlog Escopo {0} - {1} de {2}',
			emptyMsg : 'Nenhum BacklogEscopo encontrado.'
		} ];
		this.callParent(arguments);
	}
});