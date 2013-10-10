Ext.define('Liproma.model.MenuItem', {
    extend: 'Ext.data.Model',
 
    uses: [
        'Liproma.model.MenuRaiz'
    ],
 
    idProperty: 'id',
 
    fields: [
        {
            name: 'text'
        },
        {
            name: 'iconCls'
        },
        {
            name: 'className'
        },
        {
            name: 'id'
        },
        {
            name: 'menu_id'
        }
    ],
 
    belongsTo: {
        model: 'Liproma.model.MenuRoot',
        foreignKey: 'menu_id'
    }
});
