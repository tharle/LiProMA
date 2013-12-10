package br.unioeste.liproma.model.entidade;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class AnaliseMercado implements IEntidade {
	private Long id;
	private String estrategiaMarketing;
	private String necessidadeMercado;
	private String concorrencia;
	private String tecnologiaDesenvolvimento;
	private String ambienteComputacional;
	private String perfilCliente;
	private String nivelHabilidade;
	private String restricaoCultural;
	private String tempoEntrega;
	private String objetivoNegocio;
	private String objetivoReuso;
	private Set<Dominio> dominios;
	private String dominioNomes;

	public AnaliseMercado() {
		this.id = 0l;
		this.estrategiaMarketing = "";
		this.necessidadeMercado = "";
		this.concorrencia = "";
		this.tecnologiaDesenvolvimento = "";
		this.ambienteComputacional = "";
		this.perfilCliente = "";
		this.nivelHabilidade = "";
		this.restricaoCultural = "";
		this.tempoEntrega = "";
		this.objetivoNegocio = "";
		this.objetivoReuso = "";
		this.dominioNomes = "";
		dominios = new HashSet<>();
	}

	public AnaliseMercado(long id, String estrategiaMercado,
			String necessidadeMercado, String concorrencia,
			String tecnologiaDesenvolvimento, String ambienteComputacional,
			String perfilCliente, String nivelHabilidade,
			String restricaoCultural, String tempoEntrega,
			String objetivoNegocio, String objetivoReuso) {
		this.id = id;
		this.estrategiaMarketing = estrategiaMercado;
		this.necessidadeMercado = necessidadeMercado;
		this.concorrencia = concorrencia;
		this.tecnologiaDesenvolvimento = tecnologiaDesenvolvimento;
		this.ambienteComputacional = ambienteComputacional;
		this.perfilCliente = perfilCliente;
		this.nivelHabilidade = nivelHabilidade;
		this.restricaoCultural = restricaoCultural;
		this.tempoEntrega = tempoEntrega;
		this.objetivoNegocio = objetivoNegocio;
		this.objetivoReuso = objetivoReuso;
		dominios = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstrategiaMercado() {
		return estrategiaMarketing;
	}

	public void setEstrategiaMercado(String estrategiaMercado) {
		this.estrategiaMarketing = estrategiaMercado;
	}

	public String getNecessidadeMercado() {
		return necessidadeMercado;
	}

	public void setNecessidadeMercado(String necessidadeMercado) {
		this.necessidadeMercado = necessidadeMercado;
	}

	public String getConcorrencia() {
		return concorrencia;
	}

	public void setConcorrencia(String concorrencia) {
		this.concorrencia = concorrencia;
	}

	public String getTecnologiaDesenvolvimento() {
		return tecnologiaDesenvolvimento;
	}

	public void setTecnologiaDesenvolvimento(String tecnologiaDesenvolvimento) {
		this.tecnologiaDesenvolvimento = tecnologiaDesenvolvimento;
	}

	public String getAmbienteComputacional() {
		return ambienteComputacional;
	}

	public void setAmbienteComputacional(String ambienteComputacional) {
		this.ambienteComputacional = ambienteComputacional;
	}

	public String getPerfilCliente() {
		return perfilCliente;
	}

	public void setPerfilCliente(String perfilCliente) {
		this.perfilCliente = perfilCliente;
	}

	public String getNivelHabilidade() {
		return nivelHabilidade;
	}

	public void setNivelHabilidade(String nivelHabilidade) {
		this.nivelHabilidade = nivelHabilidade;
	}

	public String getRestricaoCultural() {
		return restricaoCultural;
	}

	public void setRestricaoCultural(String restricaoCultural) {
		this.restricaoCultural = restricaoCultural;
	}

	public String getTempoEntrega() {
		return tempoEntrega;
	}

	public void setTempoEntrega(String tempoEntrega) {
		this.tempoEntrega = tempoEntrega;
	}

	public String getObjetivoNegocio() {
		return objetivoNegocio;
	}

	public void setObjetivoNegocio(String objetivoNegocio) {
		this.objetivoNegocio = objetivoNegocio;
	}

	public String getObjetivoReuso() {
		return objetivoReuso;
	}

	public void setObjetivoReuso(String objetivoReuso) {
		this.objetivoReuso = objetivoReuso;
	}

	public String getDominioNomes() {
		return dominioNomes;
	}

	public void setDominioNomes(String dominioNomes) {
		this.dominioNomes = dominioNomes;
	}

	public Set<Dominio> getDominios() {
		return dominios;
	}

	public void setDominios(Set<Dominio> dominios) {
		this.dominios = dominios;
		//this.dominioNomes = toStringDominios();
	}

	public JSONObject toJsonObject() {
		JSONObject json = new JSONObject();
		try {
			json.put("id", String.valueOf(this.id));
			json.put("estrategiaMarketing", this.estrategiaMarketing);
			json.put("necessidadeMercado", this.necessidadeMercado);
			json.put("concorrencia", this.concorrencia);
			json.put("tecnologiaDesenvolvimento",
					this.tecnologiaDesenvolvimento);
			json.put("ambienteComputacional", this.ambienteComputacional);
			json.put("perfilCliente", this.perfilCliente);
			json.put("nivelHabilidade", this.nivelHabilidade);
			json.put("restricaoCultural", this.restricaoCultural);
			json.put("tempoEntrega", this.tempoEntrega);
			json.put("objetivoNegocio", this.objetivoNegocio);
			json.put("objetivoReuso", this.objetivoReuso);
			json.put("dominioNomes", toStringDominios());
			json.put("dominioValores", toArrayIdDominios());
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public HashMap<String, String> toMap() {
//		HashMap<String, String> map = new HashMap<String, String>();
//		try {
//			map.put("id", String.valueOf(this.id));
//			map.put("estrategiaMarketing", this.estrategiaMarketing);
//			map.put("necessidadeMercado", this.necessidadeMercado);
//			map.put("concorrencia", this.concorrencia);
//			map.put("tecnologiaDesenvolvimento",
//					this.tecnologiaDesenvolvimento);
//			map.put("ambienteComputacional", this.ambienteComputacional);
//			map.put("perfilCliente", this.perfilCliente);
//			map.put("nivelHabilidade", this.nivelHabilidade);
//			map.put("restricaoCultural", this.restricaoCultural);
//			map.put("tempoEntrega", this.tempoEntrega);
//			map.put("objetivoNegocio", this.objetivoNegocio);
//			map.put("objetivoReuso", this.objetivoReuso);
//			map.put("dominioNomes", toStringDominios());
//		//	map.put("dominioValores", toArrayIdDominios());
//			
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return map;
		return new HashMap<>();
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

	public void fromJsonObject(JSONObject jsonObj, boolean novo) {

		try {
			this.id = novo ? 0l : jsonObj.getLong("id");
			this.estrategiaMarketing = jsonObj.getString("estrategiaMarketing");
			this.necessidadeMercado = jsonObj.getString("necessidadeMercado");
			this.concorrencia = jsonObj.getString("concorrencia");
			this.tecnologiaDesenvolvimento = jsonObj
					.getString("tecnologiaDesenvolvimento");
			this.ambienteComputacional = jsonObj
					.getString("ambienteComputacional");
			this.perfilCliente = jsonObj.getString("perfilCliente");
			this.nivelHabilidade = jsonObj.getString("nivelHabilidade");
			this.restricaoCultural = jsonObj.getString("restricaoCultural");
			this.tempoEntrega = jsonObj.getString("tempoEntrega");
			this.objetivoNegocio = jsonObj.getString("objetivoNegocio");
			this.objetivoReuso = jsonObj.getString("objetivoReuso");
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
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		// Warning - this method won't work in the case the id fields are not
		// set
		if (!(obj instanceof AnaliseMercado)) {
			return false;
		}
		AnaliseMercado other = (AnaliseMercado) obj;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

}
