Ext.define('Liproma.store.Menu', {
    extend: 'Ext.data.Store',
 
    requires: [
        'Liproma.model.MenuRaiz'
    ],
 
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            storeId: 'MenuStore',
            model: 'Liproma.model.MenuRaiz',
            proxy: {
                type: 'ajax',
                //Configura��o do menu lateral
                url: 'data/menu.json',
                reader: {
                    type: 'json',
                    root: 'items'
                }
            }
        }, cfg)]);
    }
});