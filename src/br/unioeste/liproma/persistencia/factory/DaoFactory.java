package br.unioeste.liproma.persistencia.factory;

import org.hibernate.cfg.Configuration;

import br.unioeste.liproma.persistencia.dao.AnaliseMercadoDao;
import br.unioeste.liproma.persistencia.dao.ProdutoDao;

public class DaoFactory extends AbstractDaoFactory{

	private ProdutoDao produtoDao;
	private AnaliseMercadoDao analiseMercadoDao;
	
	
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

}
