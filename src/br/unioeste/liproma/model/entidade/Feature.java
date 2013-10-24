package br.unioeste.liproma.model.entidade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Feature implements IEntidade {

	class Prioridade {
		public final static int MUITO_ALTA = 0;
		public final static int ALTA = 1;
		public final static int MEDIA = 2;
		public final static int BAIXA = 3;
		public final static int MUITO_BAIXA = 4;
	}

	private Long id;
	private Long idFeaturePai;
	private String featurePaiNome;
	private String nome;
	private String descricao;
	private Integer prioridade;
	private String prioridadeNome;
	private String bindingTime;
	private int x, y;
	private boolean principal;

	public Feature() {
		this.id = 0l;
		this.idFeaturePai = 0l;
		this.nome = "";
		this.descricao = "";
		this.prioridade = Prioridade.MEDIA;
		this.bindingTime = "";
		this.x = y = 0;
	}

	@Override
	public Long getId() {

		return id;
	}

	public Long getIdFeaturePai() {
		return idFeaturePai;
	}

	public String getFeaturePaiNome() {
		return featurePaiNome;
	}

	public void setFeaturePaiNome(String featurePaiNome) {
		this.featurePaiNome = featurePaiNome;
	}

	public void setIdFeaturePai(Long idFeaturePai) {
		this.idFeaturePai = idFeaturePai;
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

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;

		switch (prioridade) {
		case Prioridade.MUITO_ALTA:
			this.prioridadeNome = "Muito Alta";
			break;
		case Prioridade.ALTA:
			this.prioridadeNome = "Alta";
			break;
		case Prioridade.MEDIA:
			this.prioridadeNome = "Média";
			break;
		case Prioridade.BAIXA:
			this.prioridadeNome = "Baixa";
			break;
		case Prioridade.MUITO_BAIXA:
			this.prioridadeNome = "Muito Baixa";
			break;

		default:
			this.prioridadeNome = "Média";
			break;
		}
	}

	public String getPrioridadeNome() {
		return prioridadeNome;
	}

	public void setPrioridadeNome(String prioridadeNome) {
		this.prioridadeNome = prioridadeNome;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public String getBindingTime() {
		return bindingTime;
	}

	public void setBindingTime(String bindingTime) {
		this.bindingTime = bindingTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Map<String, String> toMap() {
		HashMap<String, String> map = new HashMap<>();

		map.put("id", String.valueOf(this.id));
		map.put("idFeaturePai", String.valueOf(this.idFeaturePai));
		map.put("nome", this.nome);
		map.put("descricao", this.descricao);
		map.put("prioridade", String.valueOf(this.prioridade));
		map.put("bindingTime", this.bindingTime);

		return map;
	}

	public void processJsonObject(JSONObject jsonObj, boolean novo) {

		try {
			this.id = novo ? 0l : jsonObj.getLong("id");
			this.idFeaturePai = jsonObj.getLong("idFeaturePai");
			this.nome = jsonObj.getString("nome");
			this.descricao = jsonObj.getString("descricao");
			this.prioridade = jsonObj.getInt("prioridade");
			this.bindingTime = jsonObj.getString("bindingTime");

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
