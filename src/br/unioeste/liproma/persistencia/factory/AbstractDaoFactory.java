package br.unioeste.liproma.persistencia.factory;

import br.unioeste.liproma.persistencia.dao.AnaliseMercadoDao;
import br.unioeste.liproma.persistencia.dao.ProdutoDao;


public abstract class AbstractDaoFactory {
	private static AbstractDaoFactory daoFactory;
	
	
	public static AbstractDaoFactory getDaoFactory() {

        try {
            if(daoFactory == null)
                daoFactory = (AbstractDaoFactory) Class.forName("br.unioeste.liproma.persistencia.factory.DaoFactory").newInstance();
            
            return daoFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public abstract ProdutoDao getProdutoDao();
	public abstract AnaliseMercadoDao getAnaliseMercadoDao();
}
