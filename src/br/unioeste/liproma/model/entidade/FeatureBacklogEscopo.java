package br.unioeste.liproma.model.entidade;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.unioeste.liproma.controller.FeatureController;

@Entity
public class FeatureBacklogEscopo implements IEntidade{
	private Long id;
	private Feature feature;
	private BacklogEscopo backlogEscopo;
	private Set<BacklogSprint> backlogSprints;
	private String estimativa;
	
	
	public FeatureBacklogEscopo() {
		this.id = 0l;
		feature = new Feature();
		backlogEscopo = new BacklogEscopo();
		backlogSprints = new HashSet<>();
		estimativa = "";
	}
	
	
	
	
	public FeatureBacklogEscopo(Long id, Feature feature,
			BacklogEscopo backlogEscopo) {
		this.id = id;
		this.feature = feature;
		this.backlogEscopo = backlogEscopo;
		this.backlogSprints = new HashSet<>();
		estimativa = "";
	}




	public FeatureBacklogEscopo(long id, String descricao) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}



	@Override
	public void fromJsonObject(JSONObject jsonObj, boolean novo) {
		try {
			this.id = novo ? 0l : jsonObj.getLong("id");
			Long idBacklogEscopo = jsonObj.getLong("idBacklogEscopo");
			this.backlogEscopo = new BacklogEscopo(idBacklogEscopo, "");
			this.feature = new Feature();
			Long idFeature = jsonObj.getLong("idFeature");
			this.feature.setId(idFeature);
			this.estimativa = jsonObj.getString("estimativa");

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}



	public BacklogEscopo getBacklogEscopo() {
		return backlogEscopo;
	}



	public void setBacklogEscopo(BacklogEscopo backlogEscopo) {
		this.backlogEscopo = backlogEscopo;
	}



	public String getEstimativa() {
		return estimativa;
	}



	public void setEstimativa(String estimativa) {
		this.estimativa = estimativa;
	}



	public Set<BacklogSprint> getBacklogSprints() {
		return backlogSprints;
	}



	public void setBacklogSprints(Set<BacklogSprint> backlogSprints) {
		this.backlogSprints = backlogSprints;
	}



	@Override
	public JSONObject toJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}



	public Feature getFeature() {
		return feature;
	}



	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	
	
}
