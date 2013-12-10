package br.unioeste.liproma.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.unioeste.liproma.model.entidade.BacklogEscopo;
import br.unioeste.liproma.model.entidade.BacklogSprint;
import br.unioeste.liproma.model.entidade.Dominio;
import br.unioeste.liproma.model.entidade.DominioAnaliseMercado;
import br.unioeste.liproma.model.entidade.Feature;
import br.unioeste.liproma.model.entidade.FeatureDominio;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.model.entidade.Produto;
import br.unioeste.liproma.store.dao.AnaliseMercadoDao;
import br.unioeste.liproma.store.dao.FeatureDao;
import br.unioeste.liproma.store.dao.SimpleDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;
import br.unioeste.liproma.store.factory.DaoFactory;

public class FeatureController {
	public static final int dimX = 300;
	public static final int dimY = 75;

	public FeatureController() {

	}

	public void gravar(Feature feature, boolean novo) throws Exception {
		FeatureDao dao = AbstractDaoFactory.getDaoFactory().getFeatureDao();
		carregarDominios(feature);
		if (novo) {
			IEntidade entidade = dao.insert(feature);
			feature.setId(entidade.getId());
		} else {
			dao.update(feature);
		}

		gravarFeatureDominio(feature, novo);
	}

	private void gravarFeatureDominio(Feature fNova, boolean novo)
			throws Exception {
		ArrayList<Dominio> inserir = new ArrayList<>(fNova.getDominios());
		SimpleDao fdDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"FeatureDominio");
		if (!novo) {
			List<IEntidade> fdAntigos = fdDao.findWhere("id_feature",
					String.valueOf(fNova.getId()));
			List<IEntidade> remover = new ArrayList<>(fdAntigos);
			for (Dominio dNovo : fNova.getDominios()) {
				for (IEntidade ent : fdAntigos) {
					FeatureDominio fd = (FeatureDominio) ent;
					if (dNovo.getId() == fd.getDominio().getId()) {
						inserir.remove(dNovo);
						remover.remove(fd);
					}
				}
			}

			for (IEntidade ent : remover) {
				fdDao.delete(ent);
			}
		}

		// gravar os novos
		for (Dominio dNovo : inserir) {
			fdDao.insert(new FeatureDominio(null, fNova, dNovo));
		}
	}

	private void carregarDominios(Feature feature) throws Exception {
		DominioController dc = new DominioController();
		HashSet<Dominio> dominios = new HashSet<>();
		for (Dominio d : feature.getDominios()) {
			d = dc.buscarDominiosPor("id", String.valueOf(d.getId())).get(0);
			dominios.add(d);
		}
		feature.setDominios(dominios);

	}

	// ------------------------------------------------------------------------
	// BUSCAS
	// ------------------------------------------------------------------------
	public List<Feature> buscarFeaturesPorCampo(String campo, String text)
			throws Exception {

		
		FeatureDao dao = AbstractDaoFactory.getDaoFactory().getFeatureDao();
		List<IEntidade> results;
		if (text.trim().length() == 0) {
			results = dao.findAll();
		} else {
			results = dao.findWhere(campo, text);
		}

		
		return configurarFeatures(results);
	}
	
	public List<Feature> configurarFeatures(List<IEntidade> ientidades) throws Exception{
		SimpleDao fdDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"FeatureDominio");
		ArrayList<Feature> features = new ArrayList<>();
		for (IEntidade e : ientidades) {
			Feature f = (Feature) e;
			List<IEntidade> dams = fdDao.findWhere("id_feature",
					String.valueOf(e.getId()));

			for (IEntidade eDam : dams) {
				FeatureDominio dam = (FeatureDominio) eDam;
				f.getDominios().add(dam.getDominio());
			}

			features.add(f);
		}
		return features;
	}

	public List<Feature> buscarFeatureXFeature(Long idFeatureSelecionada)
			throws Exception {
		ArrayList<Feature> result = new ArrayList<>();

		List<Feature> featuresFilhos = buscarFeaturesPorCampo("id",
				String.valueOf(idFeatureSelecionada));
		ArrayList<Feature> filaExecucao = new ArrayList<>();
		int linha = 1;
		ArrayList<Integer> quantidadePorLinha = new ArrayList<>();
		quantidadePorLinha.add(1);
		int naLinha = 0;// Quantidade de features ja processados na linha
		if (featuresFilhos.size() > 0) {// Significa que a feature existe
			filaExecucao.add(featuresFilhos.get(0));// Então joga ela na fila de
													// exeção
			while (!filaExecucao.isEmpty()) {
				int q = quantidadePorLinha.get(linha - 1);
				Feature f = filaExecucao.remove(0);// Remove da fila
				f.setX(Math.round(dimX / q));
				f.setY(dimY * linha);

				featuresFilhos = buscarFeaturesPorCampo("id_feature_pai",
						String.valueOf(f.getId()));// Busca os filhos pra
													// feature atual
				filaExecucao.addAll(featuresFilhos);// Adiciona todos os filhos
													// a fila de execução

				if (linha == quantidadePorLinha.size()) {
					quantidadePorLinha.add(featuresFilhos.size());
				} else if (linha < quantidadePorLinha.size()) {
					quantidadePorLinha.set(linha, quantidadePorLinha.get(linha)
							+ featuresFilhos.size());
				}

				if (++naLinha == linha) {

					linha++;
					naLinha = 0;
				}
				result.add(f);
			}
		}

		return result;
	}

	public Set<Feature> buscarFeaturesPorProduto(String idProduto) throws Exception {
		ProdutoController controle = new ProdutoController();

		ArrayList<Produto> produtos = (ArrayList<Produto>) controle
				.buscarProdutosPorCampo("id", idProduto);
		return produtos.size() > 0 ? produtos.get(0)
				.getFeatures() : new HashSet<Feature>();
	}
	
	public List<Feature> buscarFeaturesPorIdBacklogEscopo(
			String idBacklogEscopo) throws Exception {
		BacklogEscopoController controle = new BacklogEscopoController();

		ArrayList<BacklogEscopo> bkEscopos = (ArrayList<BacklogEscopo>) controle
				.buscarBacklogEscoposPorCampo("id", idBacklogEscopo);
		if(bkEscopos.size() > 0){
			return configurarFeatures(new ArrayList<IEntidade>(bkEscopos.get(0).getFeatures()));
		}
		
		return new ArrayList<Feature>();
	}

	public Set<Feature> buscarFeaturesPorBacklogSprint(String idSprint) throws Exception {
		BacklogSprintController controle = new BacklogSprintController();

		ArrayList<BacklogSprint> sprints = (ArrayList<BacklogSprint>) controle
				.buscarBacklogSprintsPorCampo("id", idSprint);
		return sprints.size() > 0 ? sprints.get(0)
				.getFeatures() : new HashSet<Feature>();
	}
	// ------------------------------------------------------------------------
	// Excluir
	// ------------------------------------------------------------------------
	public boolean excluir(Feature entidade) {
		FeatureDao dao = AbstractDaoFactory.getDaoFactory().getFeatureDao();
		try {
			dao.delete(entidade);
			return true;
		} catch (Exception e) {
			return false;
		}

	}


	

}
