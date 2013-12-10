package br.unioeste.liproma.model.entidade;

import javax.persistence.Entity;

import org.json.JSONObject;
@Entity
public class ProdutoDominio implements IEntidade{
	private Long id;
	private Produto produto;
	private Dominio dominio;
	
	public ProdutoDominio(Long id, Produto produto, Dominio dominio) {
		this.id = id;
		this.produto = produto;
		this.dominio = dominio;
	}
	
	public ProdutoDominio() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Dominio getDominio() {
		return dominio;
	}
	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
	}
	



	@Override
	public void fromJsonObject(JSONObject jsonObject, boolean novo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public JSONObject toJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
