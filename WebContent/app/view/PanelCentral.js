Ext.define('Liproma.view.PanelCentral', {
	extend : 'Ext.tab.Panel',
	alias : 'widget.panelcentral',
	//layout : 'fit',
	itemId: 'panelCentral',
	activeTab: 0,
	
	items: [ {
        xtype: 'panel',
        title: 'Home'
    }]
});