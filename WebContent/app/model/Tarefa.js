Ext.define('Liproma.model.Tarefa', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
	}, {
		name : 'status',
		defaultValue : 0
	}, {
		name : 'statusNome',
		type : 'String'
	}, {
		name : 'descricao',
		type : 'String'
	}, {
		name : 'backlogSprintNome',
	}, {
		name : 'idBacklogSprint',
		type : 'String'
	}, {
		name : 'idBacklogEscopo',
	}, {
		name : 'backlogEscopoNome',
		type : 'String'
	}, {
		name : 'responsavelValores',
		defaultValue : []
	}, {
		name : 'responsavelNomes',
		type : 'String'
	} ]
});