package br.unioeste.liproma.model.entidade;

import org.hibernate.annotations.Entity;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Criterio implements IEntidade {

	private Long id;
	private String descricao;
	private Criterio criterioGrupo;

	public Criterio() {
		this.id = 0l;
		this.descricao = "";
	}

	public Criterio(Long id, String descricao, Criterio criterio) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		// Warning - this method won't work in the case the id fields are not
		// set
		if (!(obj instanceof Criterio)) {
			return false;
		}
		Criterio other = (Criterio) obj;
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

	public void fromJsonObject(JSONObject jsonObj, boolean novo) {
		try {
			this.id = novo ? 0l : jsonObj.getLong("id");
			this.descricao = jsonObj.getString("descricao");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Criterio getCriterioGrupo() {
		return criterioGrupo;
	}

	public void setCriterioGrupo(Criterio criterioGrupo) {
		this.criterioGrupo = criterioGrupo;
	}

	@Override
	public JSONObject toJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
