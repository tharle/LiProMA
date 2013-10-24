Ext.define('Liproma.model.Feature', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	},{
		name : 'idFeaturePai'
	},{
		name : 'featurePaiNome'
	}, {
		name : 'nome'
	}, {
		name : 'descricao'
	}, {
		name : 'prioridade'
	}, {
		name : 'prioridadeNome'
	}, {
		name : 'bindingTime'
	}, {
		name : 'principal'
	}, {
		name : 'x'
	}, {
		name : 'y'
	} ],
	idProperty: 'id'
});