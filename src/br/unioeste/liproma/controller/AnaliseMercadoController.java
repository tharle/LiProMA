package br.unioeste.liproma.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.unioeste.liproma.model.entidade.AnaliseMercado;
import br.unioeste.liproma.model.entidade.Dominio;
import br.unioeste.liproma.model.entidade.DominioAnaliseMercado;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.store.dao.AnaliseMercadoDao;
import br.unioeste.liproma.store.dao.SimpleDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;
import br.unioeste.liproma.store.factory.DaoFactory;

public class AnaliseMercadoController {

	public AnaliseMercadoController() {

	}

	public void gravar(AnaliseMercado amNovo, boolean novo) throws Exception {
		AnaliseMercadoDao dao = AbstractDaoFactory.getDaoFactory()
				.getAnaliseMercadoDao();
		// carregarAnaliseMercado(amNovo);
		carregarDominios(amNovo);
		if (novo) {
			IEntidade entidade = dao.insert(amNovo);
			amNovo.setId(entidade.getId());
		} else {
			dao.update(amNovo);
		}
		gravarDominioAnaliseMercado(amNovo, novo);

	}

	private void gravarDominioAnaliseMercado(AnaliseMercado amNovo, boolean novo)
			throws Exception {
		AnaliseMercadoDao dao = DaoFactory.getDaoFactory()
				.getAnaliseMercadoDao();
		ArrayList<Dominio> inserir = new ArrayList<>(amNovo.getDominios());
		// DominioAnaliseMercadoDao damDao = AbstractDaoFactory.getDaoFactory()
		// .getDominioAnaliseMercadoDao();
		SimpleDao damDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"DominioAnaliseMercado");
		if (!novo) {
			List<IEntidade> damAntigos = damDao.findWhere("id_analise_mercado",
					String.valueOf(amNovo.getId()));
			List<IEntidade> remover = new ArrayList<>(damAntigos);
			for (Dominio dNovo : amNovo.getDominios()) {
				for (IEntidade ent : damAntigos) {
					DominioAnaliseMercado dam = (DominioAnaliseMercado) ent;
					if (dNovo.getId() == dam.getDominio().getId()) {
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
		for (Dominio dNovo : inserir) {
			damDao.insert(new DominioAnaliseMercado(null, amNovo, dNovo));
		}
	}

	private void carregarDominios(AnaliseMercado am) throws Exception {
		DominioController dc = new DominioController();
		HashSet<Dominio> dominios = new HashSet<>();
		for (Dominio d : am.getDominios()) {
			d = dc.buscarDominiosPor("id", String.valueOf(d.getId())).get(0);
			dominios.add(d);
		}
		am.setDominios(dominios);

	}

	// ------------------------------------------------------------------------
	// BUSCAS
	// ------------------------------------------------------------------------
	public List<AnaliseMercado> buscarAnaliseMercadosPorId(String campo,
			String text) throws Exception {

		ArrayList<AnaliseMercado> analiseMercados = new ArrayList<>();
		AnaliseMercadoDao dao = AbstractDaoFactory.getDaoFactory()
				.getAnaliseMercadoDao();
		SimpleDao damDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"DominioAnaliseMercado");
		List<IEntidade> results;
		if (text.trim().length() == 0) {
			results = dao.findAll();
		} else {
			results = dao.findWhere(campo, text);
		}

		for (IEntidade e : results) {
			
			
			AnaliseMercado am = (AnaliseMercado) e;
			List<IEntidade> dams = damDao.findWhere("id_analise_mercado",
					String.valueOf(e.getId()));
			
			for (IEntidade eDam : dams) {
				DominioAnaliseMercado dam = (DominioAnaliseMercado) eDam;
				am.getDominios().add(dam.getDominio());
			}
			
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
