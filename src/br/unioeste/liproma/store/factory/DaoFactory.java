package br.unioeste.liproma.store.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.unioeste.liproma.store.dao.AnaliseMercadoDao;
import br.unioeste.liproma.store.dao.DominioAnaliseMercadoDao;
import br.unioeste.liproma.store.dao.DominioDao;
import br.unioeste.liproma.store.dao.FeatureDao;
import br.unioeste.liproma.store.dao.ProdutoDao;
import br.unioeste.liproma.store.dao.SimpleDao;

public class DaoFactory extends AbstractDaoFactory {

	private static AnaliseMercadoDao analiseMercadoDao;
	private static DominioDao dominioDao;
	private static DominioAnaliseMercadoDao dominioAnaliseMercadoDao;
	private static FeatureDao featureDao;
	private static ProdutoDao produtoDao;
	private static SimpleDao simpleDao;
	private SessionFactory sessionFactory = new Configuration().configure()
			.buildSessionFactory();

	@Override
	public ProdutoDao getProdutoDao() {
		if (produtoDao == null) {
			produtoDao = new ProdutoDao(sessionFactory);
		}
		return produtoDao;
	}

	@Override
	public AnaliseMercadoDao getAnaliseMercadoDao() {
		if (analiseMercadoDao == null) {
			analiseMercadoDao = new AnaliseMercadoDao(sessionFactory);
		}
		return analiseMercadoDao;
	}

	@Override
	public DominioDao getDominioDao() {
		if (dominioDao == null) {
			dominioDao = new DominioDao(sessionFactory);
		}
		return dominioDao;
	}

	@Override
	public FeatureDao getFeatureDao() {
		if (featureDao == null) {
			featureDao = new FeatureDao(sessionFactory);
		}
		return featureDao;
	}

	@Override
	public DominioAnaliseMercadoDao getDominioAnaliseMercadoDao() {
		if (dominioAnaliseMercadoDao == null) {
			dominioAnaliseMercadoDao = new DominioAnaliseMercadoDao(sessionFactory);
		}
		return dominioAnaliseMercadoDao;
	}
	
	@Override
	public SimpleDao getSimpleDao(String classe) {
		simpleDao = new SimpleDao(sessionFactory, classe);
		return simpleDao;
	}
}
