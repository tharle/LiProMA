package br.unioeste.liproma.store.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.model.entidade.Produto;

public class ProdutoDao extends Dao {

	public ProdutoDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public IEntidade insert(IEntidade entidate) throws Exception {
		Produto produto = (Produto) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Produto result = (Produto) session.merge(produto);
			tx.commit();
			return result;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getList(String condicao) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			List<Object> produtos = session.createQuery(condicao).list();
			tx.commit();
			return produtos;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ArrayList<>();
	}

	@Override
	public List findAll() throws Exception {
		return getList("FROM Produto");
	}
	
	@Override
	public void update(IEntidade entidate) throws Exception {
		Produto produto = (Produto) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			 session.update(produto); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(IEntidade entidate) throws Exception {
		Produto produto = (Produto) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(produto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public List<IEntidade> findWhere(String campo, String text) throws Exception {
		return getList("FROM Produto WHERE "+campo+" = "+text);
	}
}
