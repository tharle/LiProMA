Ext.define('Liproma.view.featurexfeature.Tela', {
	extend : 'Ext.form.Panel',
	alias : 'widget.featurexfeaturetela',

	// frame : true,
	requires : [ 'Ext.form.Panel', 'Ext.form.field.Text' ],
	title : 'Feature x Feature',
	style : 'margin:16px',
	bodyStyle : 'padding:10px',

	initComponent : function() {

		this.items = [ {
			xtype : 'form',
			width : 400,
			autoShow : true,
			padding : '5 5 0 5',
			border : false,
			style : 'background-color: #fff',

			fieldDefaults : {
				anchor : '100%',
				labelAlign : 'left',
				allowBlank : true,
				combineErros : true,
				msgTarget : 'side',
				labelAlign : 'top',
			},

			items : [ {
				xtype : 'combo',
				id : 'cmbfeaturexfeature',
				name : 'idFeaturePai',
				fieldLabel : 'Selecione Feature',
				displayField : 'nome',
				valueField : 'id',
				store : Ext.create('Liproma.store.Feature'),
				triggerAction : 'all',
				queryMode : 'local'
			} ]
		}, {
			xtype : 'featurexfeaturedesenho'
		} ];

		this.callParent(arguments);
	}

});