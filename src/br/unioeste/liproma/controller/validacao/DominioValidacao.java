package br.unioeste.liproma.controller.validacao;

import br.unioeste.liproma.model.entidade.AnaliseMercado;
import br.unioeste.liproma.model.execao.ValidacaoException;

public class DominioValidacao extends Validacao {
	public static final String CHAVE_CAMPO_ID = "id";

	@Override
	public void validar(Object obj) throws ValidacaoException {
		if (obj == null) {
            throw new ValidacaoException("", "Analise de Mercado nulo");
        }

        if (!(obj instanceof AnaliseMercado)) {
            throw new ValidacaoException("", "Não é do tipo Analise Mercado");
        }

		
	}

}
