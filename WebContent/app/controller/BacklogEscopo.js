Ext.define('Liproma.controller.BacklogEscopo', {
	extend : 'Ext.app.Controller',

	stores : [ 'BacklogEscopo' ],

	models : [ 'BacklogEscopo' ],

	views : [ 'backlogescopo.Formulario', 'backlogescopo.Grid'],

	refs : [ {
		ref : 'backlogescopoPanel',
		selector : 'panel'
	}, {
		ref : 'backlogescopoGrid',
		selector : 'grid'
	} ],

	init : function() {
		this.control({
			'backlogescopogrid dataview' : {
				itemdblclick : this.editarBacklogEscopo
			},
			'backlogescopogrid button[action=add]' : {
				click : this.editarBacklogEscopo
			},
			'backlogescopogrid button[action=delete]' : {
				click : this.deletarBacklogEscopo
			},

			'backlogescopogrid button[action=edit]' : {
				click : this.editarBacklogEscopoButton
			},
			'backlogescopoform button[action=save]' : {
				click : this.atualizarBacklogEscopoForm
			},'backlogescopoespecificotela #cmbfeaturexfeature' : {
				select : this.carregarDesenho
			}
		});
	},

	editarBacklogEscopo : function(grid, record) {
		var edit = Ext.create('Liproma.view.backlogescopo.Formulario').show();
		if (record && record.getData) {
			// edit.atualizandoBEProduto(record.getData().id);
			edit.atualizandoBEFeature(record.getData().id);
			edit.down('form').loadRecord(record);
			Ext.getCmp('panelckbbeproduto').hide(true);
		}
	},

	editarBacklogEscopoButton : function(button) {
		var grid = this.getBacklogEscopoGrid();
		var record = grid.getSelectionModel().getSelection();
		var edit = Ext.create('Liproma.view.backlogescopo.Formulario').show();
		if (record && record.getData) {
			edit.down('form').loadRecord(record);
			Ext.getCmp('panelckbbeproduto').hide(true);
		}
	},

	atualizarBacklogEscopoForm : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var record = form.getRecord();
		var values = form.getValues();
		var novo = false;
		// -- Produto
		var produtoSelecionados = new Array();
		var domckb = form.getChildByElement('ckbbeproduto').down(
				'checkboxgroup');
		for ( var i = 0; i < domckb.items.length; i++) {
			if (domckb.items.items[i].checked) {
				produtoSelecionados.push(domckb.items.items[i].inputValue);
			}
		}
		values.produtoValores = produtoSelecionados;
		// ---
		// --Feature
		var featureSelecionados = new Array();
		var fetckb = form.getChildByElement('ckbbefeature').down(
				'checkboxgroup');
		for ( var i = 0; i < fetckb.items.length; i++) {
			if (fetckb.items.items[i].checked) {
				featureSelecionados.push(fetckb.items.items[i].inputValue);
			}
		}
		values.featureValores = featureSelecionados;
		// ---

		if (values.id > 0) {
			record.set(values);
		} else {
			record = Ext.create('Liproma.model.BacklogEscopo');
			record.set(values);
			this.getBacklogEscopoStore().add(record);
			novo = true;
		}

		win.close();
		this.getBacklogEscopoStore().sync();

		 if (novo) {// faz reload para atualizar
		 this.getBacklogEscopoStore().load();
		 }
	},

	deletarBacklogEscopo : function(button) {
		var grid = this.getBacklogescopoGrid();
		var record = grid.getSelectionModel().getSelection();
		var store = this.getBacklogEscopoStore();
		var me = this;

		Ext.MessageBox.confirm('Confirmação',
				'Quer mesmo deletar o BacklogEscopo ?', function(btn) {
					if (btn == 'yes') {
						store.remove(record);
						me.getBacklogEscopoStore().sync();

						// faz reload para atualizar
						// me.getAnaliseMercadoStore().load();
						Ext.MessageBox.alert('Mensagem',
								'BacklogEscopo deletado com sucesso!');
					}
				});
	}
});