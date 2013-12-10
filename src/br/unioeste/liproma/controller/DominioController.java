package br.unioeste.liproma.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.unioeste.liproma.model.entidade.AnaliseMercado;
import br.unioeste.liproma.model.entidade.Dominio;
import br.unioeste.liproma.model.entidade.Feature;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.model.entidade.Produto;
import br.unioeste.liproma.store.dao.DominioDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;

public class DominioController {

	public DominioController() {

	}

	public void gravar(Dominio dominio, boolean novo) throws Exception {
		DominioDao dao = AbstractDaoFactory.getDaoFactory().getDominioDao();
		if (novo)
			dao.insert(dominio);
		else
			dao.update(dominio);
	}

	// ------------------------------------------------------------------------
	// BUSCAS
	// ------------------------------------------------------------------------
	public List<Dominio> buscarDominiosPor(String campo, String text)
			throws Exception {

		ArrayList<Dominio> dominios = new ArrayList<>();
		DominioDao dao = AbstractDaoFactory.getDaoFactory().getDominioDao();
		List<IEntidade> results;
		if (text.trim().length() == 0) {
			results = dao.findAll();
		} else {
			results = dao.findWhere(campo, text);
		}

		for (IEntidade e : results) {
			dominios.add((Dominio) e);
		}

		return dominios;
	}

	// ------------------------------------------------------------------------
	// Excluir
	// ------------------------------------------------------------------------
	public boolean excluir(Dominio entidade) {
		DominioDao dao = AbstractDaoFactory.getDaoFactory().getDominioDao();
		try {
			dao.delete(entidade);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	//
	// public List<Dominio> buscarDominiosPorAnaliseMercado(long
	// idAnaliseMercado)
	// throws Exception {
	// AnaliseMercadoController amControle = new AnaliseMercadoController();
	// List<AnaliseMercado> analiseMercados = amControle
	// .buscarAnaliseMercadosPorId("id",
	// String.valueOf(idAnaliseMercado));
	// // ArrayList<Dominio> dominios = new ArrayList<>();
	// // DominioDao dao = AbstractDaoFactory.getDaoFactory()
	// // .getDominioDao();
	// // List<IEntidade> results;
	// // if (text.trim().length() == 0) {
	// // results = dao.findAll();
	// // } else {
	// // results = dao.findWhere(campo, text);
	// // }
	// //
	// // for (IEntidade e : results) {
	// // dominios.add((Dominio) e);
	// // }
	// if (analiseMercados.size() > 0)
	// return analiseMercados.get(0).getDominiosList();
	// else
	// return new ArrayList<>();
	// }

	public Set<Dominio> buscarDominiosPorAnaliseMercado(String idAnaliseMercado)
			throws Exception {
		AnaliseMercadoController controle = new AnaliseMercadoController();

		ArrayList<AnaliseMercado> analiseMercados = (ArrayList<AnaliseMercado>) controle
				.buscarAnaliseMercadosPorId("id", idAnaliseMercado);
		return analiseMercados.size() > 0 ? analiseMercados.get(0)
				.getDominios() : new HashSet<Dominio>();
	}

	public Set<Dominio> buscarDominiosPorFeature(String idFeature) throws Exception {
		FeatureController controle = new FeatureController();

		ArrayList<Feature> features = (ArrayList<Feature>) controle
				.buscarFeaturesPorCampo("id", idFeature);
		return features.size() > 0 ? features.get(0)
				.getDominios() : new HashSet<Dominio>();
	}

	public Set<Dominio> buscarDominiosPorProduto(String idProduto) throws Exception {
		ProdutoController controle = new ProdutoController();

		ArrayList<Produto> produtos = (ArrayList<Produto>) controle
				.buscarProdutosPorCampo("id", idProduto);
		return produtos.size() > 0 ? produtos.get(0)
				.getDominios() : new HashSet<Dominio>();
	}

}
