package br.unioeste.liproma.model.entidade;

import javax.persistence.Entity;

import org.json.JSONObject;

@Entity
public class TarefaResponsavel implements IEntidade{
	private Long id;
	private Tarefa tarefa;
	private Responsavel responsavel;
	
	
	public TarefaResponsavel(Long id, Tarefa tarefa, Responsavel responsavel) {
		this.id = id;
		this.tarefa = tarefa;
		this.responsavel = responsavel;
	}
	
	public TarefaResponsavel() {
		// TODO Auto-generated constructor stub
	}
	public Tarefa getTarefa() {
		return tarefa;
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	public Responsavel getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void fromJsonObject(JSONObject jsonObject, boolean novo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public JSONObject toJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
