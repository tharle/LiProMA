package br.unioeste.liproma.controller;

import java.util.ArrayList;
import java.util.List;

import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.model.entidade.Produto;
import br.unioeste.liproma.store.dao.ProdutoDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;

public class ProdutoController {

	public ProdutoController() {

	}

	public void gravar(Produto produto, boolean novo)
			throws Exception {
		ProdutoDao dao = AbstractDaoFactory.getDaoFactory()
				.getProdutoDao();
		if (novo)
			dao.insert(produto);
		else
			dao.update(produto);
	}

	// ------------------------------------------------------------------------
	// BUSCAS
	// ------------------------------------------------------------------------
	public List<Produto> buscarProdutosPorId(String campo,
			String text) throws  Exception {

		ArrayList<Produto> produtos = new ArrayList<>();
		ProdutoDao dao = AbstractDaoFactory.getDaoFactory()
				.getProdutoDao();
		List<IEntidade> results;
		if (text.trim().length() == 0) {
			results = dao.findAll();
		} else {
			results = dao.findWhere(campo, text);
		}

		for (IEntidade e : results) {
			produtos.add((Produto) e);
		}

		return produtos;
	}
	
	
	// ------------------------------------------------------------------------
	// Excluir
	// ------------------------------------------------------------------------
	public boolean excluir(Produto entidade) {
		ProdutoDao dao = AbstractDaoFactory.getDaoFactory()
				.getProdutoDao();
		try {
			dao.delete(entidade);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	
}
