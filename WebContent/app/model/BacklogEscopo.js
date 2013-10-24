Ext.define('Liproma.model.BacklogEscopo', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	},{
		name : 'dominios'
	},{
		name : 'feature'
	}, {
		name : 'prioridade'
	}, {
		name : 'estimativa'
	} , {
		name : 'descricao'
	}],
	idProperty: 'id'
});