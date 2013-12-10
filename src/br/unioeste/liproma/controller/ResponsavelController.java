package br.unioeste.liproma.controller;

import java.util.ArrayList;
import java.util.List;

import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.model.entidade.Responsavel;
import br.unioeste.liproma.store.dao.ResponsavelDao;
import br.unioeste.liproma.store.dao.SimpleDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;

public class ResponsavelController {

	public ResponsavelController() {

	}

	public void gravar(Responsavel responsavel, boolean novo) throws Exception {
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao("Responsavel");
		if (novo)
			dao.insert(responsavel);
		else
			dao.update(responsavel);
	}

	// ------------------------------------------------------------------------
	// BUSCAS
	// ------------------------------------------------------------------------
	public List<Responsavel> buscarResponsavelsPor(String campo, String text)
			throws Exception {

		ArrayList<Responsavel> responsaveis = new ArrayList<>();
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao("Responsavel");
		List<IEntidade> results;
		if (text.trim().length() == 0) {
			results = dao.findAll();
		} else {
			results = dao.findWhere(campo, text);
		}

		for (IEntidade e : results) {
			responsaveis.add((Responsavel) e);
		}

		return responsaveis;
	}

	// ------------------------------------------------------------------------
	// Excluir
	// ------------------------------------------------------------------------
	public boolean excluir(Responsavel entidade) {
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao("Responsavel");
		try {
			dao.delete(entidade);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

//	public Set<Responsavel> buscarResponsavelsPorTarefa(String idTarefa)
//			throws Exception {
//		TarefaController controle = new TarefaController();
//
//		ArrayList<Tarefa> tarefas = (ArrayList<Tarefa>) controle
//				.buscarTarefasPorId("id", idTarefa);
//		return tarefas.size() > 0 ? tarefas.get(0)
//				.getResponsavels() : new HashSet<Responsavel>();
//	}

//	public Set<Responsavel> buscarResponsavelsPorBacklogSprint(String idBacklogSprint) throws Exception {
//		BacklogSprintController controle = new BacklogSprintController();
//
//		ArrayList<BacklogSprint> backlogSprints = (ArrayList<BacklogSprint>) controle
//				.buscarBacklogSprintsPorCampo("id", idBacklogSprint);
//		return backlogSprints.size() > 0 ? backlogSprints.get(0)
//				.getResponsavels() : new HashSet<Responsavel>();
//	}

}
