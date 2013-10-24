package br.unioeste.liproma.view.feature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import br.unioeste.liproma.controller.FeatureController;
import br.unioeste.liproma.model.entidade.Feature;
import br.unioeste.liproma.store.dao.FeatureDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;
/**
 * Servlet implementation class FeatureServlet
 */
@WebServlet("/DeletarFeature.form")
public class DeletarFeatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FeatureController controlador;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletarFeatureServlet() {
        super();
        controlador = new FeatureController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	@SuppressWarnings("unchecked")
	private void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		JSONObject result = new JSONObject();
		try {
			BufferedReader rd = request.getReader();
			String linha = "";
			linha = rd.readLine();
			Feature feature = new Feature();
			org.json.JSONObject jsonObj = new org.json.JSONObject(linha);
			
			feature.processJsonObject(
					(org.json.JSONObject) jsonObj.get("features"), false);
			controlador.excluir(feature);

			Gson gson = new Gson();
			result.put("sucess", true);
			out.println(gson.toJson(result));
		} catch (Exception e) {
				result.put("sucess", false);
			e.printStackTrace();
		}
	}

}
