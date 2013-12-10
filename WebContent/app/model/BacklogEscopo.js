Ext.define('Liproma.model.BacklogEscopo', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'featureNomes'
	}, {
		name : 'featureValores'
	}, {
		name : 'produtoValores',
		defaultValue : []
	}, {
		name : 'descricao'
	} ],
	idProperty : 'id'
});