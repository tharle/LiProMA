package br.unioeste.liproma.controller;

import java.util.ArrayList;
import java.util.List;

import br.unioeste.liproma.model.entidade.Dominio;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.store.dao.DominioDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;

public class DominioController {

	public DominioController() {

	}

	public void gravar(Dominio dominio, boolean novo)
			throws Exception {
		DominioDao dao = AbstractDaoFactory.getDaoFactory()
				.getDominioDao();
		if (novo)
			dao.insert(dominio);
		else
			dao.update(dominio);
	}

	// ------------------------------------------------------------------------
	// BUSCAS
	// ------------------------------------------------------------------------
	public List<Dominio> buscarDominiosPor(String campo,
			String text) throws  Exception {

		ArrayList<Dominio> dominios = new ArrayList<>();
		DominioDao dao = AbstractDaoFactory.getDaoFactory()
				.getDominioDao();
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
		DominioDao dao = AbstractDaoFactory.getDaoFactory()
				.getDominioDao();
		try {
			dao.delete(entidade);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	
}
