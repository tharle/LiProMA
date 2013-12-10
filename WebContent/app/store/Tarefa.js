Ext.define('Liproma.store.Tarefa', {
	extend: 'Ext.data.Store',
	model: 'Liproma.model.Tarefa',
	autoLoad: false,
	pageSize: 35,
	autoLoad: {start:0, limit:35},
	
	proxy: {
		type:'ajax',
		api: {
			create: 'CriarTarefa.form',
			read: 'ListarTarefa.form',
			update: 'AtualizarTarefa.form',
			destroy: 'DeletarTarefa.form'
		},
		reader:{
			type: 'json',
			root: 'tarefas',
			sucessProperty: 'sucess'
		},
		writer: {
			type: 'json',
			writeAllFields: true,
			root: 'tarefas'
		}
	}
	
});