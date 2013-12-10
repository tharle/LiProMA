package br.unioeste.liproma.model.entidade;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Entity;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class FeatureProduto implements IEntidade {

	private Long id;
	private Feature feature;
	private Produto produto;
	
	
	
	public FeatureProduto(Long id, Feature feature, Produto produto) {
		this.id = id;
		this.feature = feature;
		this.produto = produto;
	}

	public FeatureProduto() {
		this.id = 0l;
		this.feature = new Feature();
		this.produto = new Produto();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		// Warning - this method won't work in the case the id fields are not
		// set
		if (!(obj instanceof FeatureProduto)) {
			return false;
		}
		FeatureProduto other = (FeatureProduto) obj;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Beans.Produto[ id=" + id + " ]";
	}


	public void setDominios(Set<Dominio> dominios) {
	}

	public void fromJsonObject(JSONObject jsonObj, boolean novo) {
		try {
			this.id = novo ? 0l : jsonObj.getLong("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	@Override
	public JSONObject toJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
