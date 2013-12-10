package br.unioeste.liproma.model.entidade;

import java.util.Map;

import org.json.JSONObject;

public interface IEntidade {

	public Long getId();

	public void fromJsonObject(JSONObject jsonObject, boolean novo);
	public JSONObject toJsonObject();
}
