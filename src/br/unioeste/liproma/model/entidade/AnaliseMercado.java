package br.unioeste.liproma.model.entidade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import org.json.JSONException;
import org.json.JSONObject;




@Entity
public class AnaliseMercado implements IEntidade{
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
	private List<Dominio> dominios;
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

	public List<Dominio> getDominiosList() {
		return dominios;
	}

	public void setDominios(List<Dominio> dominios) {
		this.dominios = dominios;
		this.dominioNomes = toStringDominios();
	}
	
	public Map<String, String> toMap(){
		HashMap<String, String> map = new HashMap<>();
		
		map.put("id", String.valueOf( this.id)); 
		map.put("estrategiaMarketing",this.estrategiaMarketing);
		map.put("necessidadeMercado", this.necessidadeMercado);
		map.put("concorrencia", this.concorrencia);
		map.put("tecnologiaDesenvolvimento", this.tecnologiaDesenvolvimento);
		map.put("ambienteComputacional",this.ambienteComputacional);
		map.put("perfilCliente",this.perfilCliente);
		map.put("nivelHabilidade",this.nivelHabilidade);
		map.put("restricaoCultural",this.restricaoCultural);
		map.put("tempoEntrega",this.tempoEntrega);
		map.put("objetivoNegocio",this.objetivoNegocio);
		map.put("objetivoReuso",this.objetivoReuso);
		//map.put("dominios", toStringDominios());
		
		
		return map;
	}

	private String toStringDominios() {
		StringBuilder sb = new StringBuilder("[");
		for (Dominio d : dominios) {
			sb.append(d.getNome());
			sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}

	public void processJsonObject(JSONObject jsonObj, boolean novo) {
		
		try {
			this.id = novo? 0l:jsonObj.getLong("id");
			this.estrategiaMarketing = jsonObj.getString("estrategiaMarketing");
			this.necessidadeMercado = jsonObj.getString("necessidadeMercado");
			this.concorrencia = jsonObj.getString("concorrencia");
			this.tecnologiaDesenvolvimento = jsonObj.getString("tecnologiaDesenvolvimento");
			this.ambienteComputacional = jsonObj.getString("ambienteComputacional");
			this.perfilCliente = jsonObj.getString("perfilCliente");
			this.nivelHabilidade = jsonObj.getString("nivelHabilidade");
			this.restricaoCultural = jsonObj.getString("restricaoCultural");
			this.tempoEntrega = jsonObj.getString("tempoEntrega");
			this.objetivoNegocio = jsonObj.getString("objetivoNegocio");
			this.objetivoReuso = jsonObj.getString("objetivoReuso");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	
}
