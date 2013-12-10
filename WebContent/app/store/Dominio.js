Ext.define('Liproma.store.Dominio', {
	extend: 'Ext.data.Store',
	model: 'Liproma.model.Dominio',
	autoLoad: false,
	pageSize: 35,
	//autoload: {start:0, limit:35},
	
	proxy: {
		type:'ajax',
		api: {
			create: 'CriarDominio.form',
			read: 'ListarDominio.form',
			update: 'AtualizarDominio.form',
			destroy: 'DeletarDominio.form'
		},
		reader:{
			type: 'json',
			root: 'dominios',
			sucessProperty: 'sucess'
		},
		writer: {
			type: 'json',
			writeAllFields: true,
			root: 'dominios'
		}
	}
	
});