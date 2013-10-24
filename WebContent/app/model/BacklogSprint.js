Ext.define('Liproma.model.BacklogSprint', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	},{
		name : 'descricaoEscopo'
	},{
		name : 'featuresSelecionadas'
	}, {
		name : 'responsaveis'
	}, {
		name : 'statusTarefa'
	} , {
		name : 'tarefa'
	}],
	idProperty: 'id'
});