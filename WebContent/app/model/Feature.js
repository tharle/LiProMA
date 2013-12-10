Ext.define('Liproma.model.Feature', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'idFeaturePai',
		type : 'int',
		defaultValue : 0

	}, {
		name : 'possuiFeaturePai',
		type : 'boolean',
		defaultValue : false
	},{
		name : 'featurePaiNome'
	}, {
		name : 'nome'
	}, {
		name : 'descricao'
	}, {
		name : 'prioridade',
		type : 'int',
		defaultValue : 2
	}, {
		name : 'prioridadeNome'
	}, {
		name : 'bindingTime'
	}, {
		name : 'principal',
		type : 'boolean',
		defaultValue : false
	}, {
		name : 'obrigatoria',
		type : 'boolean',
		defaultValue : false
	}, {
		name : 'x'
	}, {
		name : 'y'
	}, {
		name : 'dominioNomes'
	}, {
		name : 'dominioValores'
	}, {
		name : 'pontoVariacao',
		type : 'int',
		defaultValue : 0

	}, {
		name : 'possuiPontoVariacao',
		type : 'boolean',
		defaultValue : false
	}, {
		name : 'estimativa',
		defaultValue : ""
	}, {
		name : 'idBacklogEscopo',
	}],
	idProperty : 'id',
// validations : [ {
// type : 'format',
// field : 'username',
// matcher : /([a-z]+)[0-9]{2,3}/
// } ]
});