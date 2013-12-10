package br.unioeste.liproma.store.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unioeste.liproma.model.entidade.AnaliseMercado;
import br.unioeste.liproma.model.entidade.Dominio;
import br.unioeste.liproma.model.entidade.DominioAnaliseMercado;
import br.unioeste.liproma.model.entidade.IEntidade;

public class AnaliseMercadoDao extends Dao {

	public AnaliseMercadoDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public IEntidade insert(IEntidade entidate) throws Exception {
		AnaliseMercado analiseMercado = (AnaliseMercado) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		session.clear();

		try {
			tx = session.beginTransaction();

			AnaliseMercado result = (AnaliseMercado) session
					.merge(analiseMercado);
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
		return getList("FROM AnaliseMercado");
	}

	@Override
	public void update(IEntidade entidate) throws Exception {
		AnaliseMercado analiseMercado = (AnaliseMercado) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			session.flush();
			tx = session.beginTransaction();
			session.merge(analiseMercado);
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
		AnaliseMercado analiseMercado = (AnaliseMercado) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(analiseMercado);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public List<IEntidade> findWhere(String campo, String text)
			throws Exception {
		return getList("FROM AnaliseMercado WHERE " + campo + " = " + text);
	}

	public void removeDominioAnaliseMercado(AnaliseMercado analiseMercado, List<Dominio> dominios) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			for (Dominio d : dominios) {
				List dams = session.createQuery(
						"FROM DominioAnaliseMercado WHERE AnaliseMercado.id = "
								+ analiseMercado.getId() + " AND Dominio.id = "
								+ d.getId()).list();
				if (dams.size() > 0)
					session.delete(dams.get(0));
			}
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
