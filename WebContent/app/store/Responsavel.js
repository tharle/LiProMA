Ext.define('Liproma.store.Responsavel', {
	extend: 'Ext.data.Store',
	model: 'Liproma.model.Responsavel',
	autoLoad: false,
	pageSize: 35,
	//autoload: {start:0, limit:35},
	
	proxy: {
		type:'ajax',
		api: {
			create: 'CriarResponsavel.form',
			read: 'ListarResponsavel.form',
			update: 'AtualizarResponsavel.form',
			destroy: 'DeletarResponsavel.form'
		},
		reader:{
			type: 'json',
			root: 'responsaveis',
			sucessProperty: 'sucess'
		},
		writer: {
			type: 'json',
			writeAllFields: true,
			root: 'responsaveis'
		}
	}
	
});