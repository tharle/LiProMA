Ext.define('Liproma.store.AnaliseMercado', {
	extend:'Ext.data.Store',
	model: 'Liproma.model.AnaliseMercado',
	//autoLoad: true,
	pageSize: 35,
	autoload: {start: 0, limit: 35},
	
	proxy: {
		type:'ajax',
		api: {
			create: 'CriarAnaliseMercado.form',
			read: 'ListarAnaliseMercado.form',
			update: 'AtualizarAnaliseMercado.form',
			destroy: 'DeletarAnaliseMercado.form'
		},
		reader:{
			type: 'json',
			root: 'analiseMercados',
			sucessProperty: 'sucess'
		},
		writer: {
			type: 'json',
			writeAllFields: true,
			root: 'analiseMercados'
		}
	}
});