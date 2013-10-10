Ext.define('Liproma.controller.AnaliseMercado', {
	extend : 'Ext.app.Controller',

	stores : [ 'AnaliseMercado' ],

	models : [ 'AnaliseMercado' ],

	views : [ 'analisemercado.Formulario', 'analisemercado.Grid' ],

	refs : [ {
		ref : 'analiseMercadoPanel',
		selector : 'panel'
	}, {
		ref : 'analiseMercadoGrid',
		selector : 'grid'
	} ],

	init : function() {
		this.control({
			'analisemercadogrid dataview' : {
				itemdblclick : this.editarAnaliseMercado
			},
			'analisemercadogrid button[action=add]' : {
				click : this.editarAnaliseMercado
			},
			'analisemercadogrid button[action=delete]' : {
				click : this.deletarAnaliseMercado
			},
			'analisemercadogrid button[action=save]' : {
				click : this.atualizarAnaliseMercado
			}
		});
	},

	editarAnaliseMercado : function(grid, record) {
		var edit = Ext.create('Liproma.view.analisemercado.Formulario').show();
		if (record && record.getData) {
			edit.down('form').loadRecord(record);
		}
	},

	atualizarAnaliseMercado : function(button) {
		var win = button.up('window'), form = win.down('form'), record = form
				.getRecord(), values = form.getValues();

		var novo = false;

		if (values.id > 0) {
			record.set(values);
		} else {
			record = Ext.create('Liproma.model.AnaliseMercado');
			record.set(values);
			this.getAnaliseMercadoStore().add(record);
			novo = true;
		}

		win.close();
		this.getAnaliseMercadoStore().sync();

		if (novo) {// faz reload para atualizar
			this.getAnaliseMercadoStore().load();
		}
	},

	deletarContato : function(button) {
		var grid = this.getAnaliseMercadoGrid(), record = grid
				.getSelectionModel().getSelection(), store = this
				.getAnaliseMercadoStore();
		
		store.remove(record);
		this.getAnaliseMercadoStore().sync();
		
		//faz reload para atualizar
		this.getAnaliseMercadoStore().load();
	}
});