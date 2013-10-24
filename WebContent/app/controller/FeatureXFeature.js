Ext.define('Liproma.controller.FeatureXFeature', {
	extend : 'Ext.app.Controller',

	stores : [ 'Feature' ],

	models : [ 'Feature' ],

	views : [ 'featurexfeature.Tela', 'featurexfeature.Desenho' ],

	// refs : [ {
	// ref : 'featurexfeaturePanel',
	// selector : 'panel'
	// }],

	init : function() {
		this.control({
			'featurexfeaturetela #cmbfeaturexfeature' : {
				select : this.carregarDesenho
			}
		});
	},

	carregarDesenho : function(combo, records) {

		this.getFeatureStore().load(
				{
					params : {
						id : combo.getValue()
					},
					callback : function(records, op, success) {

						var panelDesenho = Ext.ComponentQuery
								.query('featurexfeaturedesenho')[0];
						panelDesenho.drawFigura(records, op, success);
					},
					scope : this

				});
	}
});
