package br.unioeste.liproma.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import br.unioeste.liproma.model.entidade.IEntidade;

public class AdapterUtils {
		
	
	public static ArrayList<JSONObject> toJSONArrayAdapter(List lista){
		ArrayList<JSONObject> result = new ArrayList<JSONObject>();
		for (Object obj : lista) {
			IEntidade entidade = (IEntidade) obj;
			result.add(entidade.toJsonObject());
		}
		
		return result;
	}
}
