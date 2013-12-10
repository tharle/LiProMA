package br.unioeste.liproma.model.entidade;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import org.json.JSONObject;

@Entity
public class BacklogSprintFeatureBacklogEscopo implements IEntidade {
	private Long id;
	private BacklogSprint backlogSprint;
	private FeatureBacklogEscopo featureBacklogEscopo;
	
	
	
	public BacklogSprintFeatureBacklogEscopo(Long id,
			BacklogSprint backlogSprint,
			FeatureBacklogEscopo featureBacklogEscopo) {
		this.id = id;
		this.backlogSprint = backlogSprint;
		this.featureBacklogEscopo = featureBacklogEscopo;
	}

	public BacklogSprintFeatureBacklogEscopo() {
		this.id = 0l;
		backlogSprint = new BacklogSprint();
		featureBacklogEscopo = new FeatureBacklogEscopo();
	}

	@Override
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

	@Override
	public void fromJsonObject(JSONObject jsonObject, boolean novo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONObject toJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}

	public FeatureBacklogEscopo getFeatureBacklogEscopo() {
		return featureBacklogEscopo;
	}

	public void setFeatureBacklogEscopo(FeatureBacklogEscopo featureBacklogEscopo) {
		this.featureBacklogEscopo = featureBacklogEscopo;
	}

}
