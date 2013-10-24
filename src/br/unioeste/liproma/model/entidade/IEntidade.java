package br.unioeste.liproma.model.entidade;

import java.util.Map;

import org.json.JSONObject;

public interface IEntidade {

	public Map<String, String> toMap();

	public Long getId();

	void processJsonObject(JSONObject jsonObject, boolean novo);

}
