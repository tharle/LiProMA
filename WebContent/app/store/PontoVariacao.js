Ext.define('Liproma.store.PontoVariacao', {
	model : 'Liproma.model.PontoVariacao',
	// autoLoad: true,

	data : [ [ '0', 'Nenhum' ], [ '1', 'Or' ], [ '2', 'Xor' ] ],

	sortInfo : {
		field : 'id',
		direction : 'ASC'
	}
});