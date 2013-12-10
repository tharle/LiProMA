Ext.Loader.setConfig({
	enable : true
});

Ext.Loader.setPath('Ext.ux', 'app/ux');

Ext.application({
	name : 'Liproma',
	controllers : [ 'Menu', 'AnaliseMercado', 'Produto', 'Dominio', 'Feature',
			'FeatureXFeature', 'BacklogEscopo', 'BacklogEscopoEspecifico',
			'BacklogSprint', 'Responsavel', 'Tarefa'],
	autoCreateViewport : true
});