package br.unioeste.liproma.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.unioeste.liproma.model.entidade.IEntidade;

public class AdapterUtils {
		
	@SuppressWarnings("rawtypes")
	public static List<Map> toJsonArrayAdapter(List<IEntidade> entidades){
		List<Map> result = new ArrayList<>();
		for (IEntidade e : entidades) {
			result.add(e.toMap());
		}
		return result;
	}
}
