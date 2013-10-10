Ext.define('Liproma.view.Menu', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.menu',
 
    height: 432,
    width: 251,
    layout: {
        type: 'accordion'
    },
    iconCls: 'home',
    title: 'Menu dinâmico',
 
    initComponent: function() {
        var me = this;
 
        me.callParent(arguments);
    }
 
});

