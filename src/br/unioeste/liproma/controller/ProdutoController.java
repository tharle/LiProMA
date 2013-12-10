package br.unioeste.liproma.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.unioeste.liproma.model.entidade.Dominio;
import br.unioeste.liproma.model.entidade.Feature;
import br.unioeste.liproma.model.entidade.FeatureDominio;
import br.unioeste.liproma.model.entidade.FeatureProduto;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.model.entidade.Produto;
import br.unioeste.liproma.model.entidade.ProdutoDominio;
import br.unioeste.liproma.store.dao.ProdutoDao;
import br.unioeste.liproma.store.dao.SimpleDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;

public class ProdutoController {

	public ProdutoController() {

	}

	public void gravar(Produto produto, boolean novo) throws Exception {
		ProdutoDao dao = AbstractDaoFactory.getDaoFactory().getProdutoDao();
		carregarDominios(produto);
		if (novo) {
			IEntidade entidade = dao.insert(produto);
			produto.setId(entidade.getId());
		} else {
			dao.update(produto);
		}
		gravarProdutoDominio(produto, novo);
		gravarFeatureProduto(produto, novo);
	}

	private void gravarProdutoDominio(Produto pNova, boolean novo)
			throws Exception {
		ArrayList<Dominio> inserir = new ArrayList<>(pNova.getDominios());
		SimpleDao pdDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"ProdutoDominio");
		if (!novo) {
			List<IEntidade> pdAntigos = pdDao.findWhere("id_produto",
					String.valueOf(pNova.getId()));
			List<IEntidade> remover = new ArrayList<>(pdAntigos);
			for (Dominio dNovo : pNova.getDominios()) {
				for (IEntidade ent : pdAntigos) {
					ProdutoDominio pd = (ProdutoDominio) ent;
					if (dNovo.getId() == pd.getDominio().getId()) {
						inserir.remove(dNovo);
						remover.remove(pd);
					}
				}
			}

			for (IEntidade ent : remover) {
				pdDao.delete(ent);
			}
		}

		// gravar os novos dominios
		for (Dominio dNovo : inserir) {
			pdDao.insert(new ProdutoDominio(null, pNova, dNovo));
		}
	}

	private void gravarFeatureProduto(Produto pNova, boolean novo)
			throws Exception {
		ArrayList<Feature> inserir = new ArrayList<>(pNova.getFeatures());
		SimpleDao pdDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"FeatureProduto");
		if (!novo) {
			List<IEntidade> fpAntigos = pdDao.findWhere("id_feature",
					String.valueOf(pNova.getId()));
			List<IEntidade> remover = new ArrayList<>(fpAntigos);
			for (Feature fNovo : pNova.getFeatures()) {
				for (IEntidade ent : fpAntigos) {
					FeatureProduto pd = (FeatureProduto) ent;
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

		// gravar os novos dominios
		for (Feature fNovo : inserir) {
			pdDao.insert(new FeatureProduto(null, fNovo, pNova));
		}
	}

	private void carregarDominios(Produto produtos) throws Exception {
		DominioController dc = new DominioController();
		HashSet<Dominio> dominios = new HashSet<>();
		for (Dominio d : produtos.getDominios()) {
			d = dc.buscarDominiosPor("id", String.valueOf(d.getId())).get(0);
			dominios.add(d);
		}
		produtos.setDominios(dominios);

	}

	// ------------------------------------------------------------------------
	// BUSCAS
	// ------------------------------------------------------------------------
	public List<Produto> buscarProdutosPorCampo(String campo, String text)
			throws Exception {

		ArrayList<Produto> produtos = new ArrayList<>();
		ProdutoDao dao = AbstractDaoFactory.getDaoFactory().getProdutoDao();
		List<IEntidade> results;
		if (text.trim().length() == 0) {
			results = dao.findAll();
		} else {
			results = dao.findWhere(campo, text);
		}

		SimpleDao pdDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"ProdutoDominio");
		SimpleDao fpDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"FeatureProduto");
		for (IEntidade e : results) {
			Produto p = (Produto) e;
			List<IEntidade> pds = pdDao.findWhere("id_produto",
					String.valueOf(e.getId()));
			
			List<IEntidade> fps = fpDao.findWhere("id_produto",
					String.valueOf(e.getId()));

			for (IEntidade ePD : pds) {
				ProdutoDominio pd = (ProdutoDominio) ePD;
				p.getDominios().add(pd.getDominio());
			}
			
			for (IEntidade eFP : fps) {
				FeatureProduto fp = (FeatureProduto) eFP;
				p.getFeatures().add(fp.getFeature());
			}

			produtos.add((Produto) e);
		}

		return produtos;
	}

	// ------------------------------------------------------------------------
	// Excluir
	// ------------------------------------------------------------------------
	public boolean excluir(Produto entidade) {
		ProdutoDao dao = AbstractDaoFactory.getDaoFactory().getProdutoDao();
		try {
			dao.delete(entidade);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
