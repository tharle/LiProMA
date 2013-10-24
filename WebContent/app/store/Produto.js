Ext.define('Liproma.store.Produto', {
	extend: 'Ext.data.Store',
	model: 'Liproma.model.Produto',
	//autoLoad: true,
	pageSize: 35,
	autoload: {start:0, limit:35},
	
	proxy: {
		type:'ajax',
		api: {
			create: 'CriarProduto.form',
			read: 'ListarProduto.form',
			update: 'AtualizarProduto.form',
			destroy: 'DeletarProduto.form'
		},
		reader:{
			type: 'json',
			root: 'produtos',
			sucessProperty: 'sucess'
		},
		writer: {
			type: 'json',
			writeAllFields: true,
			root: 'produtos'
		}
	}
	
});