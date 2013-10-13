Ext.Loader.setConfig({
	enable : true
});

Ext.application({
	name : 'Liproma',
	controllers : ['Menu','AnaliseMercado', 'Produto'],
	autoCreateViewport : true
});