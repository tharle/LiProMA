package br.unioeste.liproma.model.entidade;

import javax.persistence.Entity;

import org.json.JSONObject;

@Entity
public class FeatureDominio implements IEntidade{
	private Long id;
	private Feature feature;
	private Dominio dominio;	

	public FeatureDominio(Long id, Feature feature, Dominio dominio) {
		this.id = id;
		this.feature = feature;
		this.dominio = dominio;
	}
	
	public FeatureDominio() {
		// TODO Auto-generated constructor stub
	}

	public Dominio getDominio() {
		return dominio;
	}

	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
