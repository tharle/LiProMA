package br.unioeste.liproma.model.entidade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class BacklogEscopo implements IEntidade{
	private Long id;
	private String dominioNomes;
	private Set<Dominio> dominios;
	private Set<Feature> features;
	private ArrayList<Produto> produtos;
	private String descricao;
	
	public BacklogEscopo() {
		this.id = 0l;
		this.dominioNomes = "";
		this.dominios = new HashSet<>();
		this.features = new HashSet<>();
		this.produtos = new ArrayList<>();
		this.descricao = "";
	}
	
	public BacklogEscopo(long id, String descricao) {
		this.id = id;
		this.dominioNomes = "";
		this.dominios = new HashSet<>();
		this.produtos = new ArrayList<>();
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDominiosNomes() {
		return dominioNomes;
	}
	public void setDominios(String dominios) {
		this.dominioNomes = dominios;
	}
	private String toStringDominios() {
		StringBuilder sb = new StringBuilder("[");
		for (Dominio d : dominios) {
			sb.append(d.getNome());
			sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public void setDominios(Set<Dominio> dominios) {
		this.dominios = dominios;
		this.dominioNomes = toStringDominios();
	}
	
	public Set<Dominio> getDominios() {
		return this.dominios;
		
	}



	@Override
	public void fromJsonObject(JSONObject jsonObj, boolean novo) {
		try {
			this.id = novo ? 0l : jsonObj.getLong("id");
			this.descricao = jsonObj.getString("descricao");
			
			JSONArray featureValores = jsonObj.getJSONArray("featureValores");
			this.features = new HashSet<>();
			for (int i = 0; i < featureValores.length(); i++) {
				Feature f = new Feature();
				f.setId(featureValores.getLong(i));
				this.features.add(f);
			}
			
			JSONArray produtoValores = jsonObj.getJSONArray("produtoValores");
			this.produtos = new ArrayList<>();
			for (int i = 0; i < produtoValores.length(); i++) {
				Produto p = new Produto();
				p.setId(produtoValores.getLong(i));
				this.produtos.add(p);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}



	@Override
	public JSONObject toJsonObject() {
		JSONObject json = new JSONObject();
		try {
			json.put("id", String.valueOf(this.id));
			json.put("descricao", this.descricao);
			json.put("featureValores", toArrayIdFeatures());
			json.put("featureNomes", toStringFeatures());

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	private Long[] toArrayIdFeatures() {
		if (features != null) {
			Long[] valores = new Long[features.size()];
			int i = 0;
			for (Feature d : features) {
				valores[i++] = d.getId();
			}
			return valores;
		}
		return new Long[0];
	}

	private String toStringFeatures() {

		StringBuilder sb = new StringBuilder("[");
		if (features != null) {
			for (Feature f : features) {
				sb.append(f.getNome());
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}



	public Set<Feature> getFeatures() {
		return features;
	}



	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public void setFeaturePrincipaisDosProdutosSelecionados(HashSet<Feature> featureProdutos) {
		for (Feature fp : featureProdutos) {
			if(fp.isPrincipal() && Arrays.binarySearch(this.features.toArray(), fp) < 0){
				this.features.add(fp);
			}
		}
	}
	
	
}
