Ext.define('Liproma.store.BacklogEscopo', {
	extend: 'Ext.data.Store',
	model: 'Liproma.model.BacklogEscopo',
	//autoLoad: true,
	pageSize: 35,
	autoload: {start:0, limit:35},
	
	proxy: {
		type:'ajax',
		api: {
			create: 'CriarBacklogEscopo.form',
			read: 'ListarBacklogEscopo.form',
			update: 'AtualizarBacklogEscopo.form',
			destroy: 'DeletarBacklogEscopo.form'
		},
		reader:{
			type: 'json',
			root: 'backlogescopos',
			sucessProperty: 'sucess'
		},
		writer: {
			type: 'json',
			writeAllFields: true,
			root: 'backlogescopos'
		}
	}
	
});