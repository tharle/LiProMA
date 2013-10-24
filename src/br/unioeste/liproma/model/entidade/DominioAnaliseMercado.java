package br.unioeste.liproma.model.entidade;

import java.util.Map;

import org.json.JSONObject;

public class DominioAnaliseMercado implements IEntidade {
	private Long id;
	private Long idAnaliseMercado;
	private Long idDominio;

	public DominioAnaliseMercado() {
		this.id = 0l;
		this.idAnaliseMercado = 0l;
		this.idDominio = 0l;
	}

	public DominioAnaliseMercado(Long id, Long analiseMercado, Long dominio) {
		this.id = id;
		this.idAnaliseMercado = analiseMercado;
		this.idDominio = dominio;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAnaliseMercado() {
		return idAnaliseMercado;
	}

	public void setIdAnaliseMercado(Long analiseMercado) {
		this.idAnaliseMercado = analiseMercado;
	}

	public Long getIdDominio() {
		return idDominio;
	}

	public void setIdDominio(Long dominio) {
		this.idDominio = dominio;
	}

	@Override
	public Map<String, String> toMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processJsonObject(JSONObject jsonObject, boolean novo) {
		// TODO Auto-generated method stub

	}

}
