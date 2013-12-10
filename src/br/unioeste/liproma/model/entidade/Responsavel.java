package br.unioeste.liproma.model.entidade;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Entity;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Responsavel implements IEntidade {

	private Long id;
	private String nome;
	private boolean selecionado;
	private Set<Tarefa> tarefas;
	private Set<BacklogSprint> backlogSprints;

	public Responsavel() {
		this.id = 0l;
		this.nome = "";
		this.selecionado =false;
		this.tarefas = new HashSet<>();
		this.backlogSprints = new HashSet<>();
	}

	public Responsavel(String nome, String descricao) {
		this.nome = nome;
		this.selecionado =false;
		this.tarefas = new HashSet<>();
		this.backlogSprints = new HashSet<>();
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


	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		// Warning - this method won't work in the case the id fields are not
		// set
		if (!(obj instanceof Responsavel)) {
			return false;
		}
		Responsavel other = (Responsavel) obj;
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

	public Set<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(Set<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public void fromJsonObject(JSONObject jsonObj, boolean novo) {
		try {
			this.id = novo ? 0l : jsonObj.getLong("id");
			this.nome = jsonObj.getString("nome");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Set<BacklogSprint> getBacklogSprints() {
		return backlogSprints;
	}

	public void setBacklogSprints(Set<BacklogSprint> backlogSprints) {
		this.backlogSprints = backlogSprints;
	}

	@Override
	public JSONObject toJsonObject() {
		JSONObject json = new JSONObject();
		try {
			json.put("id", String.valueOf(this.id));
			json.put("nome", this.nome);
			json.put("selecionado",this.selecionado);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
}
