Ext.define('Liproma.store.BacklogSprint', {
	extend: 'Ext.data.Store',
	model: 'Liproma.model.BacklogSprint',
	//autoLoad: true,
	pageSize: 35,
	autoload: {start:0, limit:35},
	
	proxy: {
		type:'ajax',
		api: {
			create: 'CriarBacklogSprint.form',
			read: 'ListarBacklogSprint.form',
			update: 'AtualizarBacklogSprint.form',
			destroy: 'DeletarBacklogSprint.form'
		},
		reader:{
			type: 'json',
			root: 'backlogsprints',
			sucessProperty: 'sucess'
		},
		writer: {
			type: 'json',
			writeAllFields: true,
			root: 'backlogsprints'
		}
	}
	
});