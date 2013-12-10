Ext.define('Liproma.controller.Tarefa', {
	extend : 'Ext.app.Controller',

	stores : [ 'Tarefa', 'StatusTarefa', 'Responsavel' ],

	models : [ 'Tarefa', 'StatusTarefa', 'Responsavel' ],

	views : [ 'tarefa.Formulario', 'tarefa.Grid' ],

	refs : [ {
		ref : 'tarefaPanel',
		selector : 'panel'
	}, {
		ref : 'tarefaGrid',
		selector : 'grid'
	} ],

	init : function() {
		this.control({
			'tarefagrid dataview' : {
				itemdblclick : this.editarTarefa
			},
			'tarefagrid button[action=add]' : {
				click : this.editarTarefa
			},
			'tarefagrid button[action=delete]' : {
				click : this.deletarTarefa
			},

			'tarefagrid button[action=edit]' : {
				click : this.editarTarefaButton
			},
			'tarefaform button[action=save]' : {
				click : this.atualizarTarefaForm
			},
			'tarefaform #cmbtarefaescopo' : {
				select : this.selecionarBacklogEscopo
			}
		});
	},

	editarTarefa : function(grid, record) {
		var edit = Ext.create('Liproma.view.tarefa.Formulario').show();
		if (record && record.getData) {
			edit.down('form').loadRecord(record);
			//edit.atualizandoReponsaveis(record.getData().id);
			Ext.getCmp('panelckbtarefaresponsavel').hide(true);
			Ext.getCmp('cmbtarefaescopo').hide(true);
			Ext.getCmp('cmbtarefasprint').hide(true);
			Ext.getCmp('txttarefadescricao').hide(true);
			
			Ext.getCmp('cmbtarefastatus').show(true);
		}
	},

	editarTarefaButton : function(button) {
		var grid = this.getTarefaGrid();
		var record = grid.getSelectionModel().getSelection();

		var edit = Ext.create('Liproma.view.tarefa.Formulario').show();
		if (record && record.getData) {
			edit.down('form').loadRecord(record);
		}
	},

	atualizarTarefaForm : function(button) {
		var win = button.up('window');
		var form = win.down('form');
		var record = form.getRecord();
		var values = form.getValues();
		var novo = false;
		
		//-- Responsavel
		var responsavelSelecionados = new Array();
		var domckb = form.getChildByElement('ckbtarefaresponsavel').down('checkboxgroup');
		for ( var i = 0; i < domckb.items.length; i++) {
			if (domckb.items.items[i].checked) {
				responsavelSelecionados.push(domckb.items.items[i].inputValue);
			}
		}
		values.responsavelValores = responsavelSelecionados;
		//---
		if (values.id > 0) {
			record.set(values);
		} else {
			record = Ext.create('Liproma.model.Tarefa');
			record.set(values);
			this.getTarefaStore().add(record);
			novo = true;
		}

		win.close();
		this.getTarefaStore().sync();

		if (novo) {// faz reload para atualizar
			this.getTarefaStore().load();
		}
	},

	deletarTarefa : function(button) {
		var grid = this.getTarefaGrid();
		var record = grid.getSelectionModel().getSelection();
		var store = this.getTarefaStore();
		var me = this;

		Ext.MessageBox.confirm('Confirmação', 'Quer mesmo deletar a Tarefa ?',
				function(btn) {
					if (btn == 'yes') {
						store.remove(record);
						me.getTarefaStore().sync();

						// faz reload para atualizar
						// me.getAnaliseMercadoStore().load();
						Ext.MessageBox.alert('Mensagem',
								'Tarefa deletado com sucesso!');
					}
				});
	},
	selecionarBacklogEscopo : function(combo, records) {
		
		var cmbSprint = Ext.getCmp("cmbtarefasprint");
		
		cmbSprint.value = "";
		
		_tarefaSprintStore.load({
			params : {
				idBacklogEscopo : combo.getValue()
			},callback : function(records, op, success) {
				if(records.length > 0)
					cmbSprint.enable(true);
				else
					cmbSprint.disable(true);
			}
		});
	}
});