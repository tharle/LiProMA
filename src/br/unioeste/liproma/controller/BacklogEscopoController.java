package br.unioeste.liproma.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.unioeste.liproma.model.entidade.BacklogEscopo;
import br.unioeste.liproma.model.entidade.Dominio;
import br.unioeste.liproma.model.entidade.Feature;
import br.unioeste.liproma.model.entidade.FeatureBacklogEscopo;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.model.entidade.Produto;
import br.unioeste.liproma.store.dao.BacklogEscopoDao;
import br.unioeste.liproma.store.dao.SimpleDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;

public class BacklogEscopoController {

	public BacklogEscopoController() {

	}

	public void gravar(BacklogEscopo backlogEscopo, boolean novo) throws Exception {
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao("BacklogEscopo");
		carregarFeatures(backlogEscopo);
		carregarFeatureProdutos(backlogEscopo);
		if (novo) {
			IEntidade entidade = dao.insert(backlogEscopo);
			backlogEscopo.setId(entidade.getId());
		} else {
			dao.update(backlogEscopo);
		}
		gravarFeatureBacklogEscopo(backlogEscopo, novo);
	}
	
	public void gravarFeatureBacklogEscopo(FeatureBacklogEscopo fBacklogEscopo,
			boolean novo) throws Exception {
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao("FeatureBacklogEscopo");
		if (novo) {
			IEntidade entidade = dao.insert(fBacklogEscopo);
			fBacklogEscopo.setId(entidade.getId());
		} else {
			dao.update(fBacklogEscopo);
		}
	}

	private void gravarFeatureBacklogEscopo(BacklogEscopo bkEscopoNova, boolean novo)
			throws Exception {
		ArrayList<Feature> inserir = new ArrayList<>(bkEscopoNova.getFeatures());
		SimpleDao pdDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"FeatureBacklogEscopo");
		if (!novo) {
			List<IEntidade> fpAntigos = pdDao.findWhere("id_feature",
					String.valueOf(bkEscopoNova.getId()));
			List<IEntidade> remover = new ArrayList<>(fpAntigos);
			for (Feature fNovo : bkEscopoNova.getFeatures()) {
				for (IEntidade ent : fpAntigos) {
					FeatureBacklogEscopo pd = (FeatureBacklogEscopo) ent;
					if (fNovo.getId() == pd.getFeature().getId()) {
						inserir.remove(fNovo);
						remover.remove(pd);
					}
				}
			}

			for (IEntidade ent : remover) {
				pdDao.delete(ent);
			}
		}

		// gravar os novos produtos
		for (Feature fNovo : inserir) {
			pdDao.insert(new FeatureBacklogEscopo(null, fNovo, bkEscopoNova));
		}
	}

	private void carregarFeatureProdutos(BacklogEscopo backlogEscopos) throws Exception {
		ProdutoController dc = new ProdutoController();
		HashSet<Feature> features = new HashSet<>();
		for (Produto p : backlogEscopos.getProdutos()) {
			p = dc.buscarProdutosPorCampo("id", String.valueOf(p.getId())).get(0);
			features.addAll(p.getFeatures());
		}
		backlogEscopos.setFeaturePrincipaisDosProdutosSelecionados(features);

	}
	
	private void carregarFeatures(BacklogEscopo backlogEscopos) throws Exception {
		FeatureController fc = new FeatureController();
		HashSet<Feature> features = new HashSet<>();
		for (Feature f : backlogEscopos.getFeatures()) {
			f = fc.buscarFeaturesPorCampo("id", String.valueOf(f.getId())).get(0);
			features.add(f);
		}
		backlogEscopos.setFeatures(features);

	}

	// ------------------------------------------------------------------------
	// BUSCAS
	// ------------------------------------------------------------------------
	public List<BacklogEscopo> buscarBacklogEscoposPorCampo(String campo, String text)
			throws Exception {

		ArrayList<BacklogEscopo> backlogEscopos = new ArrayList<>();
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao("BacklogEscopo");
		List<IEntidade> results;
		if (text.trim().length() == 0) {
			results = dao.findAll();
		} else {
			results = dao.findWhere(campo, text);
		}

		SimpleDao fBkEscopoDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"FeatureBacklogEscopo");
		for (IEntidade e : results) {
			BacklogEscopo bkEscopo = (BacklogEscopo) e;
			
			List<IEntidade> fBkEscopos = fBkEscopoDao.findWhere("id_backlog_escopo",
					String.valueOf(e.getId()));
			
			for (IEntidade eFBS : fBkEscopos) {
				FeatureBacklogEscopo fBkEscopo = (FeatureBacklogEscopo) eFBS;
				Feature feature = fBkEscopo.getFeature();
				feature.setEstimativa(fBkEscopo.getEstimativa());
				bkEscopo.getFeatures().add(feature);
			}

			backlogEscopos.add((BacklogEscopo) e);
		}

		return backlogEscopos;
	}

	// ------------------------------------------------------------------------
	// Excluir
	// ------------------------------------------------------------------------
	public boolean excluir(BacklogEscopo entidade) {
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao("BacklogEscopo");
		try {
			dao.delete(entidade);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	

}
