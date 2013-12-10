package br.unioeste.liproma.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.unioeste.liproma.model.entidade.AnaliseMercado;
import br.unioeste.liproma.model.entidade.BacklogEscopo;
import br.unioeste.liproma.model.entidade.BacklogSprint;
import br.unioeste.liproma.model.entidade.DominioAnaliseMercado;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.model.entidade.Responsavel;
import br.unioeste.liproma.model.entidade.Tarefa;
import br.unioeste.liproma.model.entidade.TarefaResponsavel;
import br.unioeste.liproma.store.dao.SimpleDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;

public class TarefaController {

	public TarefaController() {

	}

	public void gravar(Tarefa tarefa, boolean novo) throws Exception {
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"Tarefa");
		if (novo) {
			carregarResponsaveis(tarefa);
			carregarEscpopo(tarefa);
			IEntidade entidade = dao.insert(tarefa);
			tarefa.setId(entidade.getId());
			gravarTarefaResponsavel(tarefa, novo);
		} else {
			dao.update(tarefa);
		}

	}

	private void carregarEscpopo(Tarefa tarefaNovo) throws Exception {
		BacklogSprintController sprintController = new BacklogSprintController();
		List<BacklogSprint> escopos = sprintController
				.buscarBacklogSprintsPorCampo("id",
						String.valueOf(tarefaNovo.getBacklogSprint().getId()));
		if (escopos.size() > 0) {
			tarefaNovo.setBacklogSprint(escopos.get(0));
		}
	}

	private void gravarTarefaResponsavel(Tarefa pNova, boolean novo)
			throws Exception {
		ArrayList<Responsavel> inserir = new ArrayList<>(
				pNova.getResponsaveis());
		SimpleDao pdDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"TarefaResponsavel");
		if (!novo) {
			List<IEntidade> pdAntigos = pdDao.findWhere("id_tarefa",
					String.valueOf(pNova.getId()));
			List<IEntidade> remover = new ArrayList<>(pdAntigos);
			for (Responsavel dNovo : pNova.getResponsaveis()) {
				for (IEntidade ent : pdAntigos) {
					TarefaResponsavel pd = (TarefaResponsavel) ent;
					if (dNovo.getId() == pd.getResponsavel().getId()) {
						inserir.remove(dNovo);
						remover.remove(pd);
					}
				}
			}

			for (IEntidade ent : remover) {
				pdDao.delete(ent);
			}
		}

		// gravar os novos responsavels
		for (Responsavel dNovo : inserir) {
			pdDao.insert(new TarefaResponsavel(null, pNova, dNovo));
		}
	}

	private void carregarResponsaveis(Tarefa tarefas) throws Exception {
		ResponsavelController dc = new ResponsavelController();
		HashSet<Responsavel> responsavels = new HashSet<>();
		for (Responsavel d : tarefas.getResponsaveis()) {
			d = dc.buscarResponsavelsPor("id", String.valueOf(d.getId()))
					.get(0);
			responsavels.add(d);
		}
		tarefas.setResponsaveis(responsavels);

	}

	// ------------------------------------------------------------------------
	// BUSCAS
	// ------------------------------------------------------------------------
	public List<Tarefa> buscarTarefasPor(String campo, String text)
			throws Exception {

		ArrayList<Tarefa> tarefas = new ArrayList<>();
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"Tarefa");
		SimpleDao trDao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"TarefaResponsavel");
		List<IEntidade> results;
		if (text.trim().length() == 0) {
			results = dao.findAll();
		} else {
			results = dao.findWhere(campo, text);
		}

		for (IEntidade e : results) {

			Tarefa tarefa = (Tarefa) e;
			List<IEntidade> tarefaResponsals = trDao.findWhere("id_tarefa",
					String.valueOf(e.getId()));

			for (IEntidade etr : tarefaResponsals) {
				TarefaResponsavel tarefaResponsavel = (TarefaResponsavel) etr;
				tarefa.getResponsaveis().add(tarefaResponsavel.getResponsavel());
			}

			tarefas.add(tarefa);
		}

		return tarefas;
	}

	// ------------------------------------------------------------------------
	// Excluir
	// ------------------------------------------------------------------------
	public boolean excluir(Tarefa entidade) {
		SimpleDao dao = AbstractDaoFactory.getDaoFactory().getSimpleDao(
				"Tarefa");
		try {
			dao.delete(entidade);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// public Set<Tarefa> buscarTarefasPorTarefa(String idTarefa)
	// throws Exception {
	// TarefaController controle = new TarefaController();
	//
	// ArrayList<Tarefa> tarefas = (ArrayList<Tarefa>) controle
	// .buscarTarefasPorId("id", idTarefa);
	// return tarefas.size() > 0 ? tarefas.get(0)
	// .getTarefas() : new HashSet<Tarefa>();
	// }

	// public Set<Tarefa> buscarTarefasPorBacklogSprint(String idBacklogSprint)
	// throws Exception {
	// BacklogSprintController controle = new BacklogSprintController();
	//
	// ArrayList<BacklogSprint> backlogSprints = (ArrayList<BacklogSprint>)
	// controle
	// .buscarBacklogSprintsPorCampo("id", idBacklogSprint);
	// return backlogSprints.size() > 0 ? backlogSprints.get(0)
	// .getTarefas() : new HashSet<Tarefa>();
	// }

}
