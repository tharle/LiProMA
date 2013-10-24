Ext.define('Liproma.store.Feature', {
	extend: 'Ext.data.Store',
	model: 'Liproma.model.Feature',
	autoLoad: true,
	pageSize: 35,
	autoload: {start:0, limit:35},
	
	proxy: {
		type:'ajax',
		api: {
			create: 'CriarFeature.form',
			read: 'ListarFeature.form',
			update: 'AtualizarFeature.form',
			destroy: 'DeletarFeature.form'
		},
		reader:{
			type: 'json',
			root: 'features',
			sucessProperty: 'sucess'
		},
		writer: {
			type: 'json',
			writeAllFields: true,
			root: 'features'
		}
	}
	
});