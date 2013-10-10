package br.unioeste.tcc.modelo.execao;

public class DaoException extends Exception {
	private static final long serialVersionUID = 2289881425704117641L;
	
	protected Throwable throwable;

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

    @Override
    public Throwable getCause() {
        return throwable;
    }

}
