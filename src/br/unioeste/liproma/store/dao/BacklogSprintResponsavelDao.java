package br.unioeste.liproma.store.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unioeste.liproma.model.entidade.BacklogSprintResponsavel;
import br.unioeste.liproma.model.entidade.IEntidade;

public class BacklogSprintResponsavelDao extends SimpleDao {

	public BacklogSprintResponsavelDao(SessionFactory sessionFactory) {
		super(sessionFactory, "BacklogSprintResponsavel");
	}

	@Override
	public void delete(IEntidade entidate) throws Exception {
		BacklogSprintResponsavel objeto = (BacklogSprintResponsavel) entidate;
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
