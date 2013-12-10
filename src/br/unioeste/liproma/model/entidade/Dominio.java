package br.unioeste.liproma.model.entidade;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Entity;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Dominio implements IEntidade {

	private Long id;
	private String nome;
	private String descricao;
	private boolean selecionado;
	private Set<AnaliseMercado> analiseMercados;
	private Set<Produto> produtos;
	private Set<Feature> features;
	private Set<BacklogEscopo> backlogEscopos;

	public Dominio() {
		this.id = 0l;
		this.nome = "";
		this.descricao = "";
		this.selecionado = false;
		produtos = new HashSet<>();
		features = new HashSet<>();
		backlogEscopos = new HashSet<>();
		analiseMercados = new HashSet<>();
	}

	public Dominio(Long id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.selecionado = false;
		produtos = new HashSet<>();
		features = new HashSet<>();
		analiseMercados = new HashSet<>();
		backlogEscopos = new HashSet<>();
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
		if (!(obj instanceof Dominio)) {
			return false;
		}
		Dominio other = (Dominio) obj;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Beans.Dominio[ id=" + id + " ]";
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}

	public Set<AnaliseMercado> getAnaliseMercados() {
		return analiseMercados;
	}

	public void setAnaliseMercados(Set<AnaliseMercado> analiseMercados) {
		this.analiseMercados = analiseMercados;
	}

	public void fromJsonObject(JSONObject jsonObj, boolean novo) {
		try {
			this.id = novo ? 0l : jsonObj.getLong("id");
			this.nome = jsonObj.getString("nome");
			this.descricao = jsonObj.getString("descricao");
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
			json.put("selecionado", this.selecionado);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	public Set<BacklogEscopo> getBacklogEscopos() {
		return backlogEscopos;
	}

	public void setBacklogEscopos(Set<BacklogEscopo> backlogEscopos) {
		this.backlogEscopos = backlogEscopos;
	}

}
