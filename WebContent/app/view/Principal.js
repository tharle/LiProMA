Ext.define('Liproma.view.Principal', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.principal',
	layout : 'border',
	
	requires : [ 'Liproma.view.MenuPrincipal', 'Liproma.view.PanelCentral' ],

	items : [ {
		// Principal
//		xtype : 'analisemercadogrid',
		xtype : 'panelcentral',
		region : 'center',
		border : 0,
		visible: false,
	}, {
		// Menus
		xtype : 'menuprincipal',
		region : 'west',
		width : 150,
		split : true,
		collapsible : true
	}, {
		// Titulo
		html: 'LiProMA',
		id: 'PanelCabecalho',
		xtype : 'panel',
		region : 'north',
		height : 40,
		border : 0,
	}, {
		// Adicionar rodapé com os direitos
		xtype : 'panel',
		region : 'south',
		height : 0,
		border : 0,
	}

	]
});