package br.unioeste.liproma.model.entidade;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Entity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Produto implements IEntidade {

	private Long id;
	private String nome;
	private String descricao;
	private Set<Dominio> dominios;
	private Set<Feature> features;

	public Produto() {
		this.id = 0l;
		this.nome = "";
		this.descricao = "";
		dominios = new HashSet<>();
		features = new HashSet<>();
	}
	
	

	public Produto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
		dominios = new HashSet<>();
		features = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		if (!(obj instanceof Produto)) {
			return false;
		}
		Produto other = (Produto) obj;
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

	public Set<Dominio> getDominios() {
		return dominios;
	}

	public void setDominios(Set<Dominio> dominios) {
		this.dominios = dominios;
	}
	
	public Set<Feature> getFeatures() {
		return features;
	}



	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	public void fromJsonObject(JSONObject jsonObj, boolean novo) {
		try {
			this.id = novo ? 0l : jsonObj.getLong("id");
			this.nome = jsonObj.getString("nome");
			this.descricao = jsonObj.getString("descricao");
			
			JSONArray dominioValores = jsonObj.getJSONArray("dominioValores");
			this.dominios = new HashSet<>();
			for (int i = 0; i < dominioValores.length(); i++) {
				Dominio d = new Dominio();
				d.setId(dominioValores.getLong(i));
				this.dominios.add(d);
			}
			
			JSONArray featureValores = jsonObj.getJSONArray("featureValores");
			this.features = new HashSet<>();
			for (int i = 0; i < featureValores.length(); i++) {
				Feature f = new Feature();
				f.setId(featureValores.getLong(i));
				this.features.add(f);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public JSONObject toJsonObject() {
		JSONObject json = new JSONObject();
		try {
			json.put("id", String.valueOf(this.id));
			json.put("nome", this.nome);
			json.put("descricao", this.descricao);
			json.put("dominioValores", toArrayIdDominios());
			json.put("dominioNomes", toStringDominios());
			json.put("featureValores", toArrayIdFeatures());
			json.put("featureNomes", toStringFeatures());

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}


	private Long[] toArrayIdFeatures() {
		if (features != null) {
			Long[] valores = new Long[features.size()];
			int i = 0;
			for (Feature d : features) {
				valores[i++] = d.getId();
			}
			return valores;
		}
		return new Long[0];
	}



	private Long[] toArrayIdDominios() {
		if (dominios != null) {
			Long[] dominioValores = new Long[dominios.size()];
			int i = 0;
			for (Dominio d : dominios) {
				dominioValores[i++] = d.getId();
			}
			return dominioValores;
		}
		return new Long[0];
	}



	private String toStringDominios() {

		StringBuilder sb = new StringBuilder("[");
		if (dominios != null) {
			for (Dominio d : dominios) {
				sb.append(d.getNome());
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	private String toStringFeatures() {

		StringBuilder sb = new StringBuilder("[");
		if (features != null) {
			for (Feature f : features) {
				sb.append(f.getNome());
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
}
