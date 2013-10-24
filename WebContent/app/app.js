Ext.Loader.setConfig({
	enable : true
});

Ext.Loader.setPath('Ext.ux', 'app/ux');

Ext.application({
	name : 'Liproma',
	controllers : ['Menu','AnaliseMercado', 'Produto', 'Dominio', 'Feature', 'FeatureXFeature', 'BacklogEscopo', 'BacklogSprint'],
	autoCreateViewport : true
});