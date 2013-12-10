Ext.define('Liproma.store.StatusTarefa', {
	model : 'Liproma.model.StatusTarefa',
	// autoLoad: true,

	data : [ [ '0', 'Nova' ], [ '1', 'A Fazer' ], [ '2', 'Em Andamento' ],
			[ '3', 'Feita' ], [ '4', 'Cancelada' ]],

	sortInfo : {
		field : 'id',
		direction : 'ASC'
	}
});