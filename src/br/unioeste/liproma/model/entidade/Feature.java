package br.unioeste.liproma.model.entidade;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.unioeste.liproma.controller.FeatureController;

@Entity
public class Feature implements IEntidade, Comparable{

	public class PontoVariacao {
		public final static int NENHUM = 0;
		public final static int OR = 1;
		public final static int XOR = 2;
	}

	public class Prioridade {
		public final static int MUITO_ALTA = 0;
		public final static int ALTA = 1;
		public final static int MEDIA = 2;
		public final static int BAIXA = 3;
		public final static int MUITO_BAIXA = 4;
	}

	private Long id;
	private String nome;
	private String descricao;
	private Integer prioridade;
	private String prioridadeNome;
	private String bindingTime;
	private int x, y;
	private boolean principal;
	private Integer pontoVariacao;
	private boolean obrigatoria;
	private Feature featurePai;
	private Set<Dominio> dominios;
	private boolean selecionado;
	private String estimativa;

	public Feature() {
		this.id = 0l;
		this.nome = "";
		this.descricao = "";
		this.prioridade = Prioridade.MEDIA;
		this.pontoVariacao = PontoVariacao.NENHUM;
		this.bindingTime = "";
		this.x = y = 0;
		// this.featurePai = this;
		this.dominios = new HashSet<>();
		this.selecionado = false;
	}

	@Override
	public Long getId() {

		return id;
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

	public void fromJsonObject(JSONObject jsonObj, boolean novo) {

		try {
			this.id = novo ? 0l : jsonObj.getLong("id");
			boolean possuiFeaturePai = jsonObj.getBoolean("possuiFeaturePai");
			Long idFeaturePai = jsonObj.getLong("idFeaturePai");
			if (possuiFeaturePai && idFeaturePai != 0 && idFeaturePai != id) {
				this.featurePai = new Feature();
				
				FeatureController fc = new FeatureController();
				try {
					List<Feature> fs = fc.buscarFeaturesPorCampo("id", String.valueOf(idFeaturePai));
					if(fs.size() > 0){						
						this.featurePai = fs.get(0);
					}else{
						this.featurePai = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				featurePai = null;
			}
			this.nome = jsonObj.getString("nome");
			this.descricao = jsonObj.getString("descricao");
			this.prioridade = jsonObj.getInt("prioridade");
			this.bindingTime = jsonObj.getString("bindingTime");
			this.principal = jsonObj.getBoolean("principal");
			this.obrigatoria = jsonObj.getBoolean("obrigatoria");
			this.estimativa = jsonObj.getString("estimativa");
			JSONArray dominioValores = jsonObj.getJSONArray("dominioValores");
			this.dominios = new HashSet<>();
			for (int i = 0; i < dominioValores.length(); i++) {
				Dominio d = new Dominio();
				d.setId(dominioValores.getLong(i));
				this.dominios.add(d);
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
			json.put("prioridade", this.prioridade);
			json.put("prioridadeNome", this.prioridadeNome);
			json.put("bindingTime", this.bindingTime);
			json.put("x", x);
			json.put("y", this.y);
			json.put("principal", this.principal);
			json.put("pontoVariacao", this.pontoVariacao);
			json.put("obrigatoria", this.obrigatoria);
			json.put("dominioValores", toArrayIdDominios());
			json.put("dominioNomes", toStringDominios());
			json.put("estimativa", this.estimativa);

			boolean possuiPai = featurePai != null;
			json.put("idFeaturePai", possuiPai ? this.featurePai.getId() : null);
			json.put("featurePaiNome", possuiPai ? featurePai.nome
					: "Não possui feature pai.");
			json.put("possuiFeaturePai", possuiPai);
			json.put("selecionado", selecionado);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	public Set<Dominio> getDominios() {
		return dominios;
	}

	public void setDominios(Set<Dominio> dominios) {
		this.dominios = dominios;
	}

	public Feature getFeaturePai() {
		return featurePai;
	}

	public void setFeaturePai(Feature featurePai) {
		this.featurePai = featurePai;
	}

	public Integer getPontoVariacao() {
		return pontoVariacao;
	}

	public void setPontoVariacao(Integer pontoVariacao) {
		this.pontoVariacao = pontoVariacao;
	}

	public boolean isObrigatoria() {
		return obrigatoria;
	}

	public void setObrigatoria(boolean obrigatoria) {
		this.obrigatoria = obrigatoria;
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

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		// Warning - this method won't work in the case the id fields are not
		// set
		if (!(obj instanceof Feature)) {
			return false;
		}
		Feature other = (Feature) obj;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Object arg0) {
		return this.equals(arg0)? 0: -1;
	}

	public String getEstimativa() {
		return estimativa;
	}

	public void setEstimativa(String estimativa) {
		this.estimativa = estimativa;
	}

}
