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

			'analisemercadogrid button[action=edit]' : {
				click : this.editarAnaliseMercadoButton
			},
			'analisemercadoform button[action=save]' : {
				click : this.atualizarAnaliseMercadoForm
			}
		});
	},

	editarAnaliseMercado : function(grid, record) {
		var edit = Ext.create('Liproma.view.analisemercado.Formulario').show();
		if (record && record.getData) {
			edit.down('form').loadRecord(record);
		}
	},

	editarAnaliseMercadoButton : function(button) {
		var grid = this.getAnaliseMercadoGrid();
		var record = grid.getSelectionModel().getSelection();

		var edit = Ext.create('Liproma.view.analisemercado.Formulario').show();
		if (record && record.getData) {
			edit.down('form').loadRecord(record);
		}
	},

	atualizarAnaliseMercadoForm : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var record = form.getRecord();
		var values = form.getValues();

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

	deletarAnaliseMercado : function(button) {
		var grid = this.getAnaliseMercadoGrid();
		var record = grid.getSelectionModel().getSelection();
		var store = this.getAnaliseMercadoStore();

		store.remove(record);
		this.getAnaliseMercadoStore().sync();

		// faz reload para atualizar
		this.getAnaliseMercadoStore().load();
	}
});