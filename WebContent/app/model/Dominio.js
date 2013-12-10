Ext.define('Liproma.model.Dominio', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'String'
	}, {
		name : 'nome',
		type : 'String'
	}, {
		name : 'descricao',
		type : 'String'
	}
	, {
		name : 'selecionado',
		type : 'Boolean'
	}]
});