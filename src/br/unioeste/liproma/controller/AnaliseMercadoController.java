package br.unioeste.liproma.controller;

import java.util.ArrayList;
import java.util.List;

import br.unioeste.liproma.model.entidade.AnaliseMercado;
import br.unioeste.liproma.model.entidade.Dominio;
import br.unioeste.liproma.model.entidade.DominioAnaliseMercado;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.store.dao.AnaliseMercadoDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;

public class AnaliseMercadoController {

	public AnaliseMercadoController() {

	}

	public void gravar(AnaliseMercado analiseMercado, boolean novo)
			throws Exception {
		AnaliseMercadoDao dao = AbstractDaoFactory.getDaoFactory()
				.getAnaliseMercadoDao();
		if (novo)
			dao.insert(analiseMercado);
		else
			dao.update(analiseMercado);
	}

	// ------------------------------------------------------------------------
	// BUSCAS
	// ------------------------------------------------------------------------
	public List<AnaliseMercado> buscarAnaliseMercadosPorId(String campo,
			String text) throws Exception {

		ArrayList<AnaliseMercado> analiseMercados = new ArrayList<>();
		AnaliseMercadoDao dao = AbstractDaoFactory.getDaoFactory()
				.getAnaliseMercadoDao();
		List<IEntidade> results;
		if (text.trim().length() == 0) {
			results = dao.findAll();
		} else {
			results = dao.findWhere(campo, text);
		}

		for (IEntidade e : results) {
			AnaliseMercado am = (AnaliseMercado) e;
			List<IEntidade> dams = AbstractDaoFactory.getDaoFactory()
					.getDominioAnaliseMercadoDao()
					.findWhere("idAnaliseMercado", String.valueOf(am.getId()));
			List<Dominio> dominios = new ArrayList<>();
//			//Adicionando dominios vinculados
			for (IEntidade iEntidade : dams) {
				DominioAnaliseMercado dam = (DominioAnaliseMercado) iEntidade;
				List<Dominio> dResult = new DominioController().buscarDominiosPor("id", String.valueOf(dam.getIdDominio()));
				dominios.addAll(dResult);
			}
			am.setDominios(dominios);
			analiseMercados.add(am);
		}

		return analiseMercados;
	}

	// ------------------------------------------------------------------------
	// Excluir
	// ------------------------------------------------------------------------
	public boolean excluir(AnaliseMercado entidade) {
		AnaliseMercadoDao dao = AbstractDaoFactory.getDaoFactory()
				.getAnaliseMercadoDao();
		try {
			dao.delete(entidade);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
