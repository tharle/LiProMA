package br.unioeste.tcc.persistencia.factory;

import br.unioeste.tcc.persistencia.dao.AnaliseMercadoDao;



public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public static DaoFactory getDaoFactory() {

        try {
            if(daoFactory == null)
                daoFactory = (DaoFactory) Class.forName("br.unioeste.tcc.persistencia.factory.PostgresqlDaoFactory").newInstance();
            return daoFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract AnaliseMercadoDao getAnaliseMercadoDao();
    
  
}

