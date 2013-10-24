package br.unioeste.liproma.store.factory;

import org.hibernate.cfg.Configuration;

import br.unioeste.liproma.store.dao.AnaliseMercadoDao;
import br.unioeste.liproma.store.dao.DominioAnaliseMercadoDao;
import br.unioeste.liproma.store.dao.DominioDao;
import br.unioeste.liproma.store.dao.FeatureDao;
import br.unioeste.liproma.store.dao.ProdutoDao;

public class DaoFactory extends AbstractDaoFactory{

	private ProdutoDao produtoDao;
	private AnaliseMercadoDao analiseMercadoDao;
	private DominioDao dominioDao;
	private DominioAnaliseMercadoDao analiseDominioAnaliseMercadoDao;
	private FeatureDao featureDao;
	
	@Override
	public ProdutoDao getProdutoDao() {
		if (produtoDao == null) {
			produtoDao = new ProdutoDao(new Configuration().configure().buildSessionFactory());
        }
        return produtoDao;
	}


	@Override
	public AnaliseMercadoDao getAnaliseMercadoDao() {
		if (analiseMercadoDao == null) {
			analiseMercadoDao = new AnaliseMercadoDao(new Configuration().configure().buildSessionFactory());
        }
        return analiseMercadoDao;
	}
	
	@Override
	public DominioDao getDominioDao() {
		if (dominioDao == null) {
			dominioDao = new DominioDao(new Configuration().configure().buildSessionFactory());
        }
        return dominioDao;
	}


	@Override
	public DominioAnaliseMercadoDao getDominioAnaliseMercadoDao() {
		if (analiseDominioAnaliseMercadoDao == null) {
			analiseDominioAnaliseMercadoDao = new DominioAnaliseMercadoDao(new Configuration().configure().buildSessionFactory());
        }
        return analiseDominioAnaliseMercadoDao;
	}


	@Override
	public FeatureDao getFeatureDao() {
		if (featureDao == null) {
			featureDao = new FeatureDao(new Configuration().configure().buildSessionFactory());
        }
        return featureDao;
	}

}
