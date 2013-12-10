Ext
		.define(
				'Liproma.controller.Feature',
				{
					extend : 'Ext.app.Controller',

					stores : [ 'Feature', 'Prioridade', 'PontoVariacao' ],

					models : [ 'Feature', 'Prioridade', 'PontoVariacao' ],

					views : [ 'feature.Formulario', 'feature.Grid' ],

					refs : [ {
						ref : 'featurePanel',
						selector : 'panel'
					}, {
						ref : 'featureGrid',
						selector : 'grid'
					} ],

					init : function() {
						this.control({
							'featuregrid dataview' : {
								itemdblclick : this.editarFeature
							},
							'featuregrid button[action=add]' : {
								click : this.editarFeature
							},
							'featuregrid button[action=delete]' : {
								click : this.deletarFeature
							},

							'featuregrid button[action=edit]' : {
								click : this.editarFeatureButton
							},
							'featureform button[action=save]' : {
								click : this.atualizarFeatureForm
							}
						});
					},

					editarFeature : function(grid, record) {
						var edit = Ext
								.create('Liproma.view.feature.Formulario')
								.show();
						if (record && record.getData) {
							edit
									.atualizandoFeatureDominios(record
											.getData().id);
							edit.down('form').loadRecord(record);
						}
					},

					editarFeatureButton : function(button) {
						var grid = this.getFeatureGrid();
						var record = grid.getSelectionModel().getSelection();

						var edit = Ext
								.create('Liproma.view.feature.Formulario')
								.show();
						if (record && record.getData) {
							edit.down('form').loadRecord(record);
						}
					},

					atualizarFeatureForm : function(button) {
						var win = button.up('window');
						var form = win.down('form');
						var record = form.getRecord();
						var values = form.getValues();
						var novo = false;
						var dominiosSelecionados = new Array();
						// var domckb =
						// Ext.ComponentQuery.query('checkboxgroup')[0];
						if (form.getChildByElement('ckbfeaturedominio') != null) {
							var domckb = form.getChildByElement(
									'ckbfeaturedominio').down('checkboxgroup');
							for ( var i = 0; i < domckb.items.length; i++) {
								if (domckb.items.items[i].checked) {
									dominiosSelecionados
											.push(domckb.items.items[i].inputValue);
								}
							}
							values.dominioValores = dominiosSelecionados;
						}
						if (values.id > 0) {
							record.set(values);
						} else {
							record = Ext.create('Liproma.model.Feature');
							record.set(values);
							this.getFeatureStore().add(record);
							novo = true;
						}

						win.close();
						this.getFeatureStore().sync();

						if (novo) {// faz reload para atualizar
							this.getFeatureStore().load();
						}
					},

					deletarFeature : function(button) {
						var grid = this.getFeatureGrid();
						var record = grid.getSelectionModel().getSelection();
						var store = this.getFeatureStore();
						var me = this;

						Ext.MessageBox
								.confirm(
										'Confirmação',
										'Quer mesmo deletar o Feature ?',
										function(btn) {
											if (btn == 'yes') {
												store.remove(record);
												me.getFeatureStore().sync();

												// faz reload para atualizar
												// me.getAnaliseMercadoStore().load();
												Ext.MessageBox
														.alert('Mensagem',
																'Feature deletado com sucesso!');
											}
										});
					}
				});