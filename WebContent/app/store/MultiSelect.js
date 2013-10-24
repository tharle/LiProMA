Ext.define('Liproma.store.MultiSelect', {
    extend: 'Ext.data.ArrayStore',
    model: 'Liproma.model.MultiSelect',
 
    data: [
        ['1', 'Tharle'],
        ['2', 'Henrique'],
        ['3', 'Julio'],
    ],
 
    sortInfo: {
        field: 'value',
        direction: 'ASC'
    }
 });