package br.unioeste.liproma.store.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.model.entidade.Responsavel;

public class ResponsavelDao extends SimpleDao {

	public ResponsavelDao(SessionFactory sessionFactory) {
		super(sessionFactory, "Responsavel");
	}

	@Override
	public void delete(IEntidade entidate) throws Exception {
		Responsavel objeto = (Responsavel) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(objeto);
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
