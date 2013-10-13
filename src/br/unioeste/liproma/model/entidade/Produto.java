package br.unioeste.liproma.model.entidade;

import java.util.Map;

import org.hibernate.annotations.Entity;

@Entity
public class Produto implements IEntidade{

	private Long id;
	

	private String nome;
	

	private String descricao;
	public Produto() {
		this.id = 0l;
		this.nome = "";
		this.descricao = "";
	}
	


	public Produto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public int hashCode() {
		return id!=null? id.hashCode(): 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		 // Warning - this method won't work in the case the id fields are not set
        if (!(obj instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) obj;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
	}
	
	 @Override
	    public String toString() {
	        return "Beans.Produto[ id=" + id + " ]";
	    }



	@Override
	public Map<String, String> toMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
