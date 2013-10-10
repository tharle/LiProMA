Ext.define('Liproma.view.MenuItem', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.menuitem',
 
    border: 0,
    title: '',
    rootVisible: false,
 
    initComponent: function() {
        var me = this;
 
        me.callParent(arguments);
    }
 
});