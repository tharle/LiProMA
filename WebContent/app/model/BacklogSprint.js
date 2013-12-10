Ext.define('Liproma.model.BacklogSprint', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	},{
		name : 'descricao'
	},{
		name : 'idBacklogEscopo'
	}, {
		name : 'responsavelNomes'
	}, {
		name : 'responsavelValores'
	},{
		name : 'featureNomes'
	}, {
		name : 'featureValores'
	}, {
		name : 'dataInicio'
	}, {
		name : 'dataFim'
	}, {
		name : 'localReuniao'
	}],
	idProperty: 'id'
});