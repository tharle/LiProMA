var drawPanel = Ext.create('Ext.form.Panel', {
	// width : 0,
	// height : 00,
	border : false,
	layout : 'absolute',
	overflowY : 'auto',
	overflowX : 'auto'
});

Ext.define('Liproma.view.featurexfeature.Desenho', {
	extend : 'Ext.form.Panel',
	alias : 'widget.featurexfeaturedesenho',

	requires : [ 'Ext.draw.Component' ],
	style : 'margin:16px',
	bodyStyle : 'padding:10px',

	initComponent : function() {

		this.items = [ {
			xtype : 'form',
			id : 'featurexfeatureform',
			autoShow : true,
			padding : '5 5 0 5',
			border : 0,
			style : 'background-color: #fff',

			items : [ {
				xtype : drawPanel,
				id : 'drawfeaturexfeature',
			} ]
		} ];

		this.callParent(arguments);
	},
	drawFigura : function(records, op, success) {
		// Para cada feature achada na consulta
		drawPanel.removeAll(false);
		Ext.each(records, function(root) {
			// cria-se uma bolinha na tela
			var idPrioridade = root.get('prioridade');
			var xis = root.get('x');
			var yis = root.get('y');
			var nomeFeature = root.get('nome');
			var tamanho = 50;
			var color;
			if (idPrioridade == 0) {// MUITO ALTA
				color = "#000";
			} else if (idPrioridade == 1) {// ALTA
				color = "#f00";
			} else if (idPrioridade == 3) {// BAIXA
				color = "#ff0";
			} else if (idPrioridade == 4) {// MUITO BAIXA
				color = "#00f";
			} else {// MÉDIA
				color = "#d9d";
			}

			var drawFigura = Ext.create('Ext.draw.Component', {
				width : tamanho,
				height : tamanho,
				
				items : [ {
					type : 'circle',
					stroke : color,
					'stroke-width' : 2,
					radius : (tamanho / 2) - 4,
					x : tamanho / 2,
					y : tamanho / 2
				} ],
				x : xis,
				y : yis
			});

			var drawTexto = Ext.create('Ext.draw.Component', {
				width : tamanho,
				height : tamanho,
				items : [ {
					type : "text",
					text : "("+nomeFeature+")",
					fill : color,
					font : "18px monospace",
					x : tamanho / 2,
					y : tamanho / 2
				} ],
				x : xis,
				y : yis
			});

			drawPanel.add(drawFigura);
			drawPanel.add(drawTexto);
		});

	}

});