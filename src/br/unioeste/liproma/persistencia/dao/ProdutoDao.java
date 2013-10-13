package br.unioeste.liproma.persistencia.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unioeste.liproma.model.entidade.AnaliseMercado;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.model.entidade.Produto;

public class ProdutoDao extends Dao {

	public ProdutoDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public void insert(IEntidade entidate) throws Exception {
		Produto produto = (Produto) entidate;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(produto);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getList(String condicao) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			List<Object> produtos = session.createQuery(condicao).list();
			for (Iterator<Object> iterator = produtos.iterator(); iterator
					.hasNext();) {
				Produto p = (Produto) iterator.next();
				System.out.println("Nome: " + p.getNome());
				System.out.println("Descri��o:  " + p.getDescricao() + "\n");
			}
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
}
