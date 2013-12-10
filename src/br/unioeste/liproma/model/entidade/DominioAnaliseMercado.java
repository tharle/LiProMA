package br.unioeste.liproma.model.entidade;

import javax.persistence.Entity;

import org.json.JSONObject;
@Entity
public class DominioAnaliseMercado implements IEntidade {
	private Long id;
	private Dominio dominio;
	private AnaliseMercado analiseMercado;

	public DominioAnaliseMercado() {
		this.id = 0l;
		dominio = new Dominio();
		analiseMercado = new AnaliseMercado();
	}

	public DominioAnaliseMercado(Long id, AnaliseMercado analiseMercado, Dominio dominio) {
		this.id = id;
		this.analiseMercado = analiseMercado;
		this.dominio = dominio;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public void fromJsonObject(JSONObject jsonObject, boolean novo) {
		// TODO Auto-generated method stub

	}

	public Dominio getDominio() {
		return dominio;
	}

	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
	}

	@Override
	public JSONObject toJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}

	public AnaliseMercado getAnaliseMercado() {
		return analiseMercado;
	}

	public void setAnaliseMercado(AnaliseMercado analiseMercado) {
		this.analiseMercado = analiseMercado;
	}

}
