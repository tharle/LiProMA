package br.unioeste.tcc.persistencia.factory;

import br.unioeste.tcc.persistencia.dao.AnaliseMercadoDao;


public class PostgresqlDaoFactory extends DaoFactory {

    private AnaliseMercadoDao analiseMercadoDao;


    @Override
    public AnaliseMercadoDao getAnaliseMercadoDao() {
        if (analiseMercadoDao == null) {
            analiseMercadoDao = new AnaliseMercadoDao();
        }
        return analiseMercadoDao;
    }
}
