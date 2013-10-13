Ext.define('Liproma.store.AnaliseMercado', {
	extend:'Ext.data.Store',
	model: 'Liproma.model.AnaliseMercado',
	autoLoad: true,
	pageSize: 35,
	autoload: {start: 0, limit: 35},
	
	proxy: {
		type:'ajax',
		api: {
			create: 'CriarAnaliseMercado.frm',
			read: 'ListarAnaliseMercado.frm',
			update: 'AtualizarAnaliseMercado.frm',
			destroy: 'DeletarAnaliseMercado.frm'
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