package br.unioeste.liproma.model.entidade;

import javax.persistence.Entity;

import org.json.JSONObject;

@Entity
public class BacklogSprintResponsavel implements IEntidade{
	private Long id;
	private BacklogSprint backlogSprint;
	private Responsavel responsavel;
	
	public BacklogSprintResponsavel(Long id, BacklogSprint backlogSprint,
			Responsavel responsavel) {
		this.id = id;
		this.backlogSprint = backlogSprint;
		this.responsavel = responsavel;
	}
	
	public BacklogSprintResponsavel() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BacklogSprint getBacklogSprint() {
		return backlogSprint;
	}
	public void setBacklogSprint(BacklogSprint backlogSprint) {
		this.backlogSprint = backlogSprint;
	}
	public Responsavel getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
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
