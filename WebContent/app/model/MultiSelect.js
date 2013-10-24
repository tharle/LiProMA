Ext.define('Liproma.model.MultiSelect',{
    extend: 'Ext.data.Model',
    fields: [
       {name: 'value'},
       {name: 'text'}
    ],
    idProperty: 'value'
});