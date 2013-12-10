Ext.define('Liproma.view.backlogescopoespecifico.Tela', {
	extend : 'Ext.form.Panel',
	alias : 'widget.backlogescopoespecificotela',

	// frame : true,
	requires : [ 'Ext.form.Panel', 'Ext.form.field.Text' ],
	title : 'Backlog Escopo Especifico',
	style : 'margin:16px',
	bodyStyle : 'padding:10px',

	initComponent : function() {

		this.items = [ {
			xtype : 'form',
			width : 400,
			autoShow : true,
			padding : '5 5 0 5',
			border : false,
			//style : 'background-color: #fff',

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
				id : 'cmbbacklogescopo',
				name : 'idBacklogEscopo',
				fieldLabel : 'Selecione Backlog Escopo',
				displayField : 'descricao',
				valueField : 'id',
				store : Ext.create('Liproma.store.BacklogEscopo'),
				triggerAction : 'all',
				queryMode : 'local',
			} ]
		}
		 , {
			xtype : 'backlogescopoespecificogrid'
		}
		];

		this.callParent(arguments);
	}

});