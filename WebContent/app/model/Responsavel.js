Ext.define('Liproma.model.Responsavel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'String'
	}, {
		name : 'nome',
		type : 'String'
	}, {
		name : 'selecionado',
		type : 'Boolean'
	}]
});