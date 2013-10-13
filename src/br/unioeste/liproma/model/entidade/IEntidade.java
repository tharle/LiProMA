package br.unioeste.liproma.model.entidade;

import java.util.Map;

public interface IEntidade {

	public Map<String, String> toMap();

	public Long getId();

}
