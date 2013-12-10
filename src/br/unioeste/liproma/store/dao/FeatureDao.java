package br.unioeste.liproma.store.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unioeste.liproma.model.entidade.Feature;
import br.unioeste.liproma.model.entidade.IEntidade;

public class FeatureDao extends Dao {

	public FeatureDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public IEntidade insert(IEntidade entidate) throws Exception {
		Feature feature = (Feature) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Feature result = (Feature) session.merge(feature);
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
		return getList("FROM Feature");
	}
	
	@Override
	public void update(IEntidade entidate) throws Exception {
		Feature feature = (Feature) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			 session.update(feature); 
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
		Feature feature = (Feature) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(feature);
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
		return getList("FROM Feature WHERE "+campo+" = "+text);
	}
}
