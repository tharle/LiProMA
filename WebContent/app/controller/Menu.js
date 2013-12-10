var _menuJaRenderizado = false;
Ext.define('Liproma.controller.Menu', {
	extend : 'Ext.app.Controller',

	models : [ 'MenuRaiz', 'MenuItem' ],
	stores : [ 'Menu' ],
	views : [ 'Menu', 'MenuItem' ],
	refs : [ {
		ref : 'panelCentral',
		selector : 'principal #panelCentral',
	} ],

	onPanelRender : function(abstractcomponent, options) {
		if (!_menuJaRenderizado) {
			this.getMenuStore().load(function(records, op, success) {

				var menuPanel = Ext.ComponentQuery.query('menu')[0];

				Ext.each(records, function(root) {

					var menu = Ext.create('Liproma.view.MenuItem', {
						title : root.get('title'),
						iconCls : root.get('iconCls')
					});

					Ext.each(root.items(), function(itens) {

						Ext.each(itens.data.items, function(item) {

							menu.getRootNode().appendChild({
								text : item.get('text'),
								leaf : true,
								iconCls : item.get('iconCls'),
								id : item.get('id'),
								className : item.get('className'),

								referencia : item.get('referencia')
							});
						});
					});

					menuPanel.add(menu);
				});
			});
			_menuJaRenderizado = true;
		}
	},

	// Seleção do item de menu, ele abre de acordo com o data/menu.json.
	onTreepanelSelect : function(selModel, record, index, options) {
		// Ext.Msg.alert('You selected the following menu item',
		// record.get('text'));
		var panelCentral = this.getPanelCentral();

		var newTab = panelCentral.items.findBy(function(tab) {
			return tab.title === record.get('text');
		});

		if (!newTab) {
			newTab = panelCentral.add({
				xtype : record.raw.className,
				closable : true,
				iconCls : record.get('iconCls'),
				title : record.get('text')
			});
		}

		panelCentral.setActiveTab(newTab);
	},

	init : function(application) {
		this.control({
			"menu" : {
				render : this.onPanelRender
			},
			"treepanel" : {
				select : this.onTreepanelSelect
			}
		});
	}

});