Ext.define('Liproma.store.MultiSelectFeature', {
    extend: 'Ext.data.ArrayStore',
    model: 'Liproma.model.MultiSelect',
 
    data: [
        ['1', 'f1']
//        ['2', 'f2'],
//        ['3', 'f3'],
//        ['4', 'f4'],
    ],
 
    sortInfo: {
        field: 'value',
        direction: 'ASC'
    }
 });