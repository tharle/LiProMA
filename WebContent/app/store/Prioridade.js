Ext.define('Liproma.store.Prioridade', {
	model : 'Liproma.model.Prioridade',
	// autoLoad: true,

	data : [ [ '0', 'Muito Alta' ], [ '1', 'Alta' ], [ '2', 'Média' ],
			[ '3', 'Baixa' ], [ '4', 'Muito Baixa' ] ],

	sortInfo : {
		field : 'id',
		direction : 'ASC'
	}
});