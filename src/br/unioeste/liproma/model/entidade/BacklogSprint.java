package br.unioeste.liproma.model.entidade;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class BacklogSprint implements IEntidade {
	private Long id;
	private BacklogEscopo backlogEscopo;
	private String descricao;
	private String localReuniao;
	private Date dataInicio;
	private Date dataFim;

	private Set<Responsavel> responsaveis;
	private Set<Tarefa> tarefas;
	private Set<Feature> features;

	public BacklogSprint() {
		this.id = 0l;
		descricao = "";
		this.backlogEscopo = new BacklogEscopo();
		this.descricao = "";
		this.localReuniao = "";
		this.dataInicio = new Date();
		this.dataFim = new Date();
		this.responsaveis = new HashSet<>();
		this.tarefas = new HashSet<>();
		this.features = new HashSet<>();
	}

	public BacklogSprint(Long id, BacklogEscopo backlogEscopo,
			String descricao, String localReuniao, Date dataInicio, Date dataFim) {
		this.id = id;
		this.backlogEscopo = backlogEscopo;
		this.descricao = descricao;
		this.localReuniao = localReuniao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.responsaveis = new HashSet<>();
		this.tarefas = new HashSet<>();
		this.features = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BacklogEscopo getBacklogEscopo() {
		return backlogEscopo;
	}

	public void setBacklogEscopo(BacklogEscopo backlogEscopo) {
		this.backlogEscopo = backlogEscopo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocalReuniao() {
		return localReuniao;
	}

	public void setLocalReuniao(String localReuniao) {
		this.localReuniao = localReuniao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	@Override
	public void fromJsonObject(JSONObject jsonObj, boolean novo) {
		try {
			this.id = novo ? 0l : jsonObj.getLong("id");
			this.descricao = jsonObj.getString("descricao");
			this.localReuniao = jsonObj.getString("localReuniao");
			Long idBacklogEscopo = jsonObj.getLong("idBacklogEscopo");
			this.backlogEscopo = new BacklogEscopo(idBacklogEscopo, "");
			//this.dataInicio = jsonObj.getString("dataInicio");
			//this.dataFim = jsonObj.getString("dataFim");
			
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

	public Set<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(Set<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public Set<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(Set<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Set<Feature> getFeatures() {
		return features;
	}
	
	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}
	
	@Override
	public JSONObject toJsonObject() {
		JSONObject json = new JSONObject();
		try {
			json.put("id", String.valueOf(this.id));
			json.put("descricao", this.descricao);
			json.put("idBacklogEscopo", this.backlogEscopo.getId());
			json.put("dataInicio", this.dataInicio);
			json.put("dataFim", this.dataFim);
			json.put("localReuniao", this.localReuniao);
			// json.put("responsavelValores", toArrayIdDominios());
			// json.put("responsavelNomes", toStringDominios());
			json.put("features",
					toArrayIdFeatures());
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
