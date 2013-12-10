package br.unioeste.liproma.store.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unioeste.liproma.model.entidade.Dominio;
import br.unioeste.liproma.model.entidade.IEntidade;

public class DominioDao extends Dao {

	public DominioDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public IEntidade insert(IEntidade entidate) throws Exception {
		Dominio dominio = (Dominio) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(dominio);
			tx.commit();
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
			List<Object> dominios = session.createQuery(condicao).list();
			tx.commit();
			return dominios;
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
		return getList("FROM Dominio");
	}
	
	@Override
	public void update(IEntidade entidate) throws Exception {
		Dominio dominio = (Dominio) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			 session.update(dominio); 
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
		Dominio dominio = (Dominio) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(dominio);
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
		return getList("FROM Dominio WHERE "+campo+" = "+text);
	}
}
