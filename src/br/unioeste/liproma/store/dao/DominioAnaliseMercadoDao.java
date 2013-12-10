package br.unioeste.liproma.store.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unioeste.liproma.model.entidade.DominioAnaliseMercado;
import br.unioeste.liproma.model.entidade.IEntidade;

public class DominioAnaliseMercadoDao extends SimpleDao {

	public DominioAnaliseMercadoDao(SessionFactory sessionFactory) {
		super(sessionFactory, "DominioAnaliseMercado");
	}

	@Override
	public void delete(IEntidade entidate) throws Exception {
		DominioAnaliseMercado objeto = (DominioAnaliseMercado) entidate;
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
