package br.unioeste.liproma.store.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.model.entidade.TarefaResponsavel;

public class TarefaResponsavelDao extends SimpleDao {

	public TarefaResponsavelDao(SessionFactory sessionFactory) {
		super(sessionFactory, "TarefaReponsavel");
	}

	@Override
	public void delete(IEntidade entidate) throws Exception {
		TarefaResponsavel objeto = (TarefaResponsavel) entidate;
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
