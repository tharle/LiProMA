package br.unioeste.liproma.model.entidade;

import java.util.Set;

import org.hibernate.annotations.Entity;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class FeatureCriterio implements IEntidade {
	
	public class GrauImpacto {
		public final static int MUITO_ALTO= 0;
		public final static int ALTO = 1;
		public final static int MEDIO = 2;
		public final static int BAIXO = 3;
		public final static int MUITO_BAIXO = 4;
	}

	private Long id;
	private Feature feature;
	private Criterio criterio;
	private String comentario;
	private Integer grauImpacto;
	
	public FeatureCriterio() {
		this.id = 0l;
		this.feature = new Feature();
		this.criterio = new Criterio();
		this.comentario = "";
		this.grauImpacto = GrauImpacto.MEDIO;
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
		if (!(obj instanceof FeatureCriterio)) {
			return false;
		}
		FeatureCriterio other = (FeatureCriterio) obj;
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
	public Criterio getCriterio() {
		return criterio;
	}

	public void setCriterio(Criterio criterio) {
		this.criterio = criterio;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getGrauImpacto() {
		return grauImpacto;
	}

	public void setGrauImpacto(Integer grauImpacto) {
		this.grauImpacto = grauImpacto;
	}

	@Override
	public JSONObject toJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
