package br.unioeste.liproma.store.factory;

import br.unioeste.liproma.store.dao.AnaliseMercadoDao;
import br.unioeste.liproma.store.dao.DominioAnaliseMercadoDao;
import br.unioeste.liproma.store.dao.DominioDao;
import br.unioeste.liproma.store.dao.FeatureDao;
import br.unioeste.liproma.store.dao.ProdutoDao;


public abstract class AbstractDaoFactory {
	private static AbstractDaoFactory daoFactory;
	
	
	public static AbstractDaoFactory getDaoFactory() {

        try {
            if(daoFactory == null)
                daoFactory = (AbstractDaoFactory) Class.forName("br.unioeste.liproma.store.factory.DaoFactory").newInstance();
            
            return daoFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public abstract ProdutoDao getProdutoDao();
	public abstract AnaliseMercadoDao getAnaliseMercadoDao();
	public abstract DominioDao getDominioDao();
	public abstract DominioAnaliseMercadoDao getDominioAnaliseMercadoDao();
	public abstract FeatureDao getFeatureDao();
}
