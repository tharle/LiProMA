Ext.define('Liproma.model.MenuRaiz', {
    extend: 'Ext.data.Model',
 
    uses: [
        'Liproma.model.MenuItem'
    ],
 
    idProperty: 'id',
 
    fields: [
        {
            name: 'title'
        },
        {
            name: 'iconCls'
        },
        {
            name: 'id'
        }
    ],
 
    hasMany: {
        model: 'Liproma.model.MenuItem',
        foreignKey: 'menu_id',
        name: 'items'
    }
});