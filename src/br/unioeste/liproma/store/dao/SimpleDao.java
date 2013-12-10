package br.unioeste.liproma.store.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unioeste.liproma.model.entidade.IEntidade;

public class SimpleDao extends Dao {

	private String classe;

	public SimpleDao(SessionFactory sessionFactory, String classe) {
		super(sessionFactory);
		this.classe = classe;
	}

	@Override
	public IEntidade insert(IEntidade entidate) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			IEntidade result = (IEntidade) session.merge(entidate);
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
			List<Object> entidades = session.createQuery(condicao).list();
			tx.commit();
			return entidades;
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
		return getList("FROM "+classe);
	}
	
	@Override
	public void update(IEntidade entidate) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			 session.update(entidate); 
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
		return getList("FROM "+classe+" WHERE " + campo + " = " + text);
	}

	@Override
	public void delete(IEntidade entidate) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(entidate);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
}
