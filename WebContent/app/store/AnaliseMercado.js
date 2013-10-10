Ext.define('Liproma.store.AnaliseMercado', {
	extend:'Ext.data.Store',
	model: 'Liproma.model.AnaliseMercado',
	autoLoad: true,
	pageSize: 35,
	autoload: {start: 0, limit: 35},
	
	data: {
        'analiseMercados': [{
        	"id":1,
        	"estrategiaMarketing ": "Estrategia",
        	"necessidadeMercado ": "perfil cliente",
        	"concorrencia ": "necessidade",
        	"tecnologiaDesenvolvimento ": "concorrencia",
        	"ambienteComputacional ": "tecnologia",
        	"perfilCliente ": "ambiente",
        	"nivelHabilidade ": "nivel hab.",
        	"restricaoCultural ": "restricao cultural",
        	"tempoEntrega ": "tempo entrega",
        	"objetivoNegocio ": "objetivo negocio",
        	"objetivoReuso ": "objetivo reuso"
        }]
    },
	
	proxy: {
		type:'ajax',
		api: {
			create: 'jsp/criarAnaliseMercado.jsp',
			//read: 'jsp/listarAnaliseMercado.jsp',
			read: 'ListarAnaliseMercado.frm',
			update: 'jsp/atualizarAnaliseMercado.jsp',
			destroy: 'jps/deletarAnaliseMercado.jsp'
		},
		reader:{
			type: 'json',
			root: 'analiseMercados',
			sucessProperty: 'sucess'
		},
		writer: {
			type: 'json',
			writeAllFields: true,
			encode: true,
			root: 'analiseMercados'
		}
	}
});