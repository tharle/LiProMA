package br.unioeste.liproma.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.sun.jmx.remote.internal.ArrayQueue;

import br.unioeste.liproma.model.entidade.AnaliseMercado;
import br.unioeste.liproma.model.entidade.Dominio;
import br.unioeste.liproma.model.entidade.DominioAnaliseMercado;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.model.entidade.Feature;
import br.unioeste.liproma.store.dao.FeatureDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;

public class FeatureController {
	public static final int dimX = 300;
	public static final int dimY = 75;

	public FeatureController() {

	}

	public void gravar(Feature feature, boolean novo) throws Exception {
		FeatureDao dao = AbstractDaoFactory.getDaoFactory().getFeatureDao();
		if (novo)
			dao.insert(feature);
		else
			dao.update(feature);
	}

	// ------------------------------------------------------------------------
	// BUSCAS
	// ------------------------------------------------------------------------
	public List<Feature> buscarFeaturesPorCampo(String campo, String text)
			throws Exception {

		ArrayList<Feature> features = new ArrayList<>();
		FeatureDao dao = AbstractDaoFactory.getDaoFactory().getFeatureDao();
		List<IEntidade> results;
		if (text.trim().length() == 0) {
			results = dao.findAll();
		} else {
			results = dao.findWhere(campo, text);
		}

		for (IEntidade e : results) {
			Feature f = (Feature) e;
			List<IEntidade> resultFeaturePai = AbstractDaoFactory
					.getDaoFactory().getFeatureDao()
					.findWhere("id", String.valueOf(f.getIdFeaturePai()));
			if (resultFeaturePai.size() > 0) {
				// //Adicionando feature pai descrição
				Feature featurePai = (Feature) resultFeaturePai.get(0);

				f.setFeaturePaiNome(featurePai.getNome());
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
		int naLinha = 0;//Quantidade de features ja processados na linha
		if (featuresFilhos.size() > 0) {// Significa que a feature existe
			filaExecucao.add(featuresFilhos.get(0));// Então joga ela na fila de
													// exeção
			while (!filaExecucao.isEmpty()) {
				int q = quantidadePorLinha.get(linha-1);
				Feature f = filaExecucao.remove(0);// Remove da fila
				f.setX(Math.round(dimX / q));
				f.setY(dimY * linha);

				featuresFilhos = buscarFeaturesPorCampo("idFeaturePai",
						String.valueOf(f.getId()));//Busca os filhos pra feature atual
				filaExecucao.addAll(featuresFilhos);//Adiciona todos os filhos a fila de execução
				
				if(linha == quantidadePorLinha.size()){
					quantidadePorLinha.add(featuresFilhos.size());
				}else if(linha < quantidadePorLinha.size()){
					quantidadePorLinha.set(linha, quantidadePorLinha.get(linha)+featuresFilhos.size());
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
