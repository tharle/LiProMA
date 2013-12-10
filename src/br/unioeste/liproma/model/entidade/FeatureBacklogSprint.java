package br.unioeste.liproma.model.entidade;

import javax.persistence.Entity;

import org.json.JSONObject;

@Entity
public class FeatureBacklogSprint implements IEntidade{
	private Long id;
	private BacklogSprint backlogSprint;
	private Feature feature;
	
	public FeatureBacklogSprint(Long id, BacklogSprint backlogSprint,
			Feature feature) {
		this.id = id;
		this.backlogSprint = backlogSprint;
		this.feature = feature;
	}
	
	public FeatureBacklogSprint() {
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
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
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
