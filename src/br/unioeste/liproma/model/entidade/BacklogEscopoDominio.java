package br.unioeste.liproma.model.entidade;

import javax.persistence.Entity;

import org.json.JSONObject;

@Entity
public class BacklogEscopoDominio implements IEntidade {
	private Long id;
	private BacklogEscopo backlogEscopo;
	private Dominio dominio;
	
	public BacklogEscopoDominio() {
		this.id = 0l;
		this.backlogEscopo = new BacklogEscopo();
		this.dominio = new Dominio();
	}
	
	
	public BacklogEscopoDominio(Long id, BacklogEscopo backlogEscopo,
			Dominio dominio) {
		this.id = id;
		this.backlogEscopo = backlogEscopo;
		this.dominio = dominio;
	}

	public BacklogEscopo getBacklogEscopo() {
		return backlogEscopo;
	}

	public void setBacklogEscopo(BacklogEscopo backlogEscopo) {
		this.backlogEscopo = backlogEscopo;
	}

	public Dominio getDominio() {
		return dominio;
	}

	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
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
