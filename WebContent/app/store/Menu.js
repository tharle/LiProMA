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
                //Configuração do menu lateral
                url: 'data/menu.json',
                reader: {
                    type: 'json',
                    root: 'items'
                }
            }
        }, cfg)]);
    }
});