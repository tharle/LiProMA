Ext.define('Liproma.view.Viewport', {
	extend : 'Ext.container.Viewport',
	layout : 'fit',
	requires : [ 'Liproma.view.Principal' ],

	initComponent : function() {
		var me = this;

		Ext.apply(me, {
			items : {
				xtype : 'principal'
			}
		});
		me.callParent(arguments);
	}
});