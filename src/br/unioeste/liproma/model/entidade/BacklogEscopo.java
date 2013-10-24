package br.unioeste.liproma.model.entidade;

public class BacklogEscopo {
	private long id;
	private String dominios;
	private String feature;
	private String prioridade;
	private String estimativa;
	private String descricao;
	
	public BacklogEscopo() {
		this.id = 0;
		this.dominios = "";
		this.feature = "";
		this.prioridade = "";
		this.estimativa = "";
		this.setDescricao("");
	}
	
	
	
	public BacklogEscopo(long id, String dominios, String features,
			String prioridade, String estimativa, String descricao) {
		this.id = id;
		this.dominios = dominios;
		this.feature = features;
		this.prioridade = prioridade;
		this.estimativa = estimativa;
		this.setDescricao(descricao);
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDominios() {
		return dominios;
	}
	public void setDominios(String dominios) {
		this.dominios = dominios;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	public String getEstimativa() {
		return estimativa;
	}
	public void setEstimativa(String estimativa) {
		this.estimativa = estimativa;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
