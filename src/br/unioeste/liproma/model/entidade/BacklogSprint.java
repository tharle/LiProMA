package br.unioeste.liproma.model.entidade;

public class BacklogSprint {
	private long id;
	private String descricaoEscopo;
	private String tarefa;
	private String featuresSelecionadas;
	private String responsaveis;
	private String statusTarefa;

	public BacklogSprint() {
		this.id = 0;
		this.tarefa = "";
		this.responsaveis = "";
		this.statusTarefa = "";
		descricaoEscopo = "";
		this.featuresSelecionadas = "";
	}

	public BacklogSprint(long id, String descricaoEscopo, String tarefa, String responsaveis,
			String status, String featuresSelecionadas) {
		this.id = id;
		this.tarefa = tarefa;
		this.responsaveis = responsaveis;
		this.statusTarefa = status;
		this.descricaoEscopo = descricaoEscopo;
		this.featuresSelecionadas = featuresSelecionadas;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricaoEscopo() {
		return descricaoEscopo;
	}

	public void setDescricaoEscopo(String descricaoEscopo) {
		this.descricaoEscopo = descricaoEscopo;
	}

	public String getTarefa() {
		return tarefa;
	}

	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}

	public String getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(String responsaveis) {
		this.responsaveis = responsaveis;
	}

	public String getStatusTarefa() {
		return statusTarefa;
	}

	public void setStatusTarefa(String status) {
		this.statusTarefa = status;
	}

	public String getFeaturesSelecionadas() {
		return featuresSelecionadas;
	}

	public void setFeaturesSelecionadas(String featuresSelecionadas) {
		this.featuresSelecionadas = featuresSelecionadas;
	}

}
