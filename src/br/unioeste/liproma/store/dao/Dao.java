package br.unioeste.liproma.store.dao;

import java.util.List;


import org.hibernate.SessionFactory;

import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.model.entidade.Produto;

public abstract class Dao {
	protected SessionFactory sessionFactory;
	
	public Dao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public abstract IEntidade insert(IEntidade entidate) throws Exception;
	
	public abstract List<IEntidade> findAll() throws Exception;

	public abstract List getList(String condicao) throws Exception;

	public abstract void delete(IEntidade entidate)  throws Exception;

	public abstract void update(IEntidade entidate) throws Exception;

	public abstract List<IEntidade> findWhere(String campo, String text) throws Exception;
}
