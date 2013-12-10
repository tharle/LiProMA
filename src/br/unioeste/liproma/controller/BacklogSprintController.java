package br.unioeste.liproma.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.unioeste.liproma.model.entidade.BacklogEscopo;
import br.unioeste.liproma.model.entidade.BacklogSprint;
import br.unioeste.liproma.model.entidade.BacklogSprint;
import br.unioeste.liproma.model.entidade.BacklogSprintFeatureBacklogEscopo;
import br.unioeste.liproma.model.entidade.Feature;
import br.unioeste.liproma.model.entidade.FeatureBacklogSprint;
import br.unioeste.liproma.model.entidade.FeatureBacklogEscopo;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.store.dao.BacklogSprintDao;
import br.unioeste.liproma.store.dao.SimpleDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;
import br.unioeste.liproma.store.factory.DaoFactory;

public class BacklogSprintController {
	public BacklogSprintController() {

	}

	public void gravar(BacklogSprint sprintNovo, boolean novo) throws Exception {
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"BacklogSprint");
		// carregarBacklogSprint(amNovo);
		carregarFeatures(sprintNovo);
		carregarEscpopo(sprintNovo);
		if (novo) {
			IEntidade entidade = dao.insert(sprintNovo);
			sprintNovo.setId(entidade.getId());
		} else {
			dao.update(sprintNovo);
		}
		gravarFeatureBacklogSprint(sprintNovo, novo);

	}

	private void carregarEscpopo(BacklogSprint sprintNovo) throws Exception {
		BacklogEscopoController escopoController = new BacklogEscopoController();
		List<BacklogEscopo> escopos = escopoController
				.buscarBacklogEscoposPorCampo("id",
						String.valueOf(sprintNovo.getBacklogEscopo().getId()));
		if (escopos.size() > 0) {
			sprintNovo.setBacklogEscopo(escopos.get(0));
		}
	}

	private void gravarFeatureBacklogSprint(BacklogSprint amNovo, boolean novo)
			throws Exception {
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"BacklogSprint");
		ArrayList<Feature> inserir = new ArrayList<>(amNovo.getFeatures());
		// FeatureBacklogSprintDao damDao = AbstractDaoFactory.getDaoFactory()
		// .getFeatureBacklogSprintDao();
		SimpleDao damDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"FeatureBacklogSprint");
		if (!novo) {
			List<IEntidade> damAntigos = damDao.findWhere("id_analise_mercado",
					String.valueOf(amNovo.getId()));
			List<IEntidade> remover = new ArrayList<>(damAntigos);
			for (Feature dNovo : amNovo.getFeatures()) {
				for (IEntidade ent : damAntigos) {
					FeatureBacklogSprint dam = (FeatureBacklogSprint) ent;
					if (dNovo.getId() == dam.getFeature().getId()) {
						inserir.remove(dNovo);
						remover.remove(dam);
					}
				}
			}

			for (IEntidade ent : remover) {
				damDao.delete(ent);
			}
		}

		// gravar os novos
		for (Feature dNovo : inserir) {
			damDao.insert(new FeatureBacklogSprint(null, amNovo, dNovo));
		}
	}

	private void carregarFeatures(BacklogSprint am) throws Exception {
		FeatureController dc = new FeatureController();
		HashSet<Feature> features = new HashSet<>();
		for (Feature d : am.getFeatures()) {
			d = dc.buscarFeaturesPorCampo("id", String.valueOf(d.getId())).get(
					0);
			features.add(d);
		}
		am.setFeatures(features);

	}

	// ------------------------------------------------------------------------
	// BUSCAS
	// ------------------------------------------------------------------------
	public List<BacklogSprint> buscarBacklogSprintsPorCampo(String campo,
			String text) throws Exception {

		ArrayList<BacklogSprint> backlogSprints = new ArrayList<>();
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"BacklogSprint");
		SimpleDao damDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"FeatureBacklogSprint");
		List<IEntidade> results;
		if (text.trim().length() == 0) {
			results = dao.findAll();
		} else {
			results = dao.findWhere(campo, text);
		}

		for (IEntidade e : results) {

			BacklogSprint bs = (BacklogSprint) e;
			List<IEntidade> fbss = damDao.findWhere("id_backlog_sprint",
					String.valueOf(e.getId()));

			for (IEntidade efbs : fbss) {
				FeatureBacklogSprint fbs = (FeatureBacklogSprint) efbs;
				bs.getFeatures().add(fbs.getFeature());
			}

			backlogSprints.add(bs);
		}

		return backlogSprints;
	}

	// ------------------------------------------------------------------------
	// Excluir
	// ------------------------------------------------------------------------
	public boolean excluir(BacklogSprint entidade) {
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"BacklogSprint");
		try {
			dao.delete(entidade);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
