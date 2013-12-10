package br.unioeste.liproma.model.entidade;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Entity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.unioeste.liproma.model.entidade.Feature.Prioridade;

@Entity
public class Tarefa implements IEntidade {

	public class Status {
		public final static int NOVA = 0;
		public final static int A_FAZER = 1;
		public final static int EM_ANDAMENTA = 2;
		public final static int FEITA = 3;
		public final static int CANCELADA = 4;
	}

	private Long id;
	private String descricao;
	private Set<Responsavel> responsaveis;
	private BacklogSprint backlogSprint;
	private BacklogSprintFeatureBacklogEscopo backlogSprintFeatureBacklogEscopo;
	private Integer status;

	public Tarefa() {
		this.id = 0l;
		this.descricao = "";
		responsaveis = new HashSet<>();
		backlogSprint = new BacklogSprint();
		this.backlogSprintFeatureBacklogEscopo = new BacklogSprintFeatureBacklogEscopo();
		this.status = Status.NOVA;
	}

	public Tarefa(String nome, String descricao,
			BacklogSprintFeatureBacklogEscopo backlogSprintFeatureBacklogEscopo) {
		this.descricao = descricao;
		this.backlogSprintFeatureBacklogEscopo = backlogSprintFeatureBacklogEscopo;
		responsaveis = new HashSet<>();
		this.status = Status.NOVA;
		backlogSprint = new BacklogSprint();
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
		if (!(obj instanceof Tarefa)) {
			return false;
		}
		Tarefa other = (Tarefa) obj;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Beans.Tarefa[ id=" + id + " ]";
	}

	public Set<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(Set<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void fromJsonObject(JSONObject jsonObj, boolean novo) {
		try {
			this.id = novo ? 0l : jsonObj.getLong("id");
			this.descricao = jsonObj.getString("descricao");
			String status = jsonObj.getString("status");
			this.status = status.trim().length() > 0 ? Integer.parseInt(status)
					: Status.NOVA;
			Long idBacklogSprint = jsonObj.getLong("idBacklogSprint");
			this.backlogSprint.setId(idBacklogSprint);
			JSONArray responsavelValores = jsonObj
					.getJSONArray("responsavelValores");
			this.responsaveis = new HashSet<>();
			for (int i = 0; i < responsavelValores.length(); i++) {
				Responsavel r = new Responsavel();
				r.setId(responsavelValores.getLong(i));
				this.responsaveis.add(r);
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
			json.put("descricao", this.descricao);
			json.put("status", this.status);
			json.put("statusNome", getStatusNome());
			json.put("backlogSprintNome", this.backlogSprint.getDescricao());
			json.put("idBacklogSprint", this.backlogSprint.getId());
			json.put("backlogEscopoNome", this.backlogSprint.getBacklogEscopo()
					.getDescricao());
			json.put("idBacklogEscopo", this.backlogSprint.getBacklogEscopo()
					.getId());
			json.put("responsavelValores", toArrayIdResponsaveis());
			json.put("responsavelNomes", toStringResponsaveis());

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	private String getStatusNome() {

		switch (status) {
		case Status.NOVA:
			return "Nova";
		case Status.A_FAZER:
			return "A Fazer";
		case Status.CANCELADA:
			return "Cancelada";
		case Status.EM_ANDAMENTA:
			return "Em Andamento";
		case Status.FEITA:
			return "Feita";

		default:
			return "Média";
		}
	}

	private Long[] toArrayIdResponsaveis() {
		if (responsaveis != null) {
			Long[] responsavelValores = new Long[responsaveis.size()];
			int i = 0;
			for (Responsavel d : responsaveis) {
				responsavelValores[i++] = d.getId();
			}
			return responsavelValores;
		}
		return new Long[0];
	}

	private String toStringResponsaveis() {

		StringBuilder sb = new StringBuilder("[");
		if (responsaveis != null) {
			for (Responsavel d : responsaveis) {
				sb.append(d.getNome());
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public BacklogSprintFeatureBacklogEscopo getBacklogSprintFeatureBacklogEscopo() {
		return backlogSprintFeatureBacklogEscopo;
	}

	public void setBacklogSprintFeatureBacklogEscopo(
			BacklogSprintFeatureBacklogEscopo backlogSprintFeatureBacklogEscopo) {
		this.backlogSprintFeatureBacklogEscopo = backlogSprintFeatureBacklogEscopo;
	}

	public BacklogSprint getBacklogSprint() {
		return backlogSprint;
	}

	public void setBacklogSprint(BacklogSprint backlogSprint) {
		this.backlogSprint = backlogSprint;
	}
}
