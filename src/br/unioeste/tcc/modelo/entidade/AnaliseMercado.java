package br.unioeste.tcc.modelo.entidade;

import java.util.HashMap;
import java.util.Map;

public class AnaliseMercado implements IEntidade{
	private long id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	
//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("{");
//		sb.append("id:"); 
//		sb.append(this.id);
//		sb.append(",");
//		sb.append("estrategiaMarketing : ");
//		sb.append(this.estrategiaMarketing);
//		sb.append(",");
//		sb.append("necessidadeMercado : "); 
//		sb.append(this.necessidadeMercado);
//		sb.append(",");
//		sb.append("concorrencia : "); 
//		sb.append(this.concorrencia);
//		sb.append(",");
//		sb.append("tecnologiaDesenvolvimento : "); 
//		sb.append(this.tecnologiaDesenvolvimento);
//		sb.append(",");
//		sb.append("ambienteComputacional : ");
//		sb.append(this.ambienteComputacional);
//		sb.append(",");
//		sb.append("perfilCliente : ");
//		sb.append(this.perfilCliente);
//		sb.append(",");
//		sb.append("nivelHabilidade : ");
//		sb.append(this.nivelHabilidade);
//		sb.append(",");
//		sb.append("restricaoCultural : ");
//		sb.append(this.restricaoCultural);
//		sb.append(",");
//		sb.append("tempoEntrega : ");
//		sb.append(this.tempoEntrega);
//		sb.append(",");
//		sb.append("objetivoNegocio : ");
//		sb.append(this.objetivoNegocio);
//		sb.append(",");
//		sb.append("objetivoReuso : ");
//		sb.append(this.objetivoReuso);
//		sb.append("}");
//		return sb.toString();
//	}
	
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
		
		
		return map;
	}
}
