package br.unioeste.liproma.view.feature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.FeatureController;
import br.unioeste.liproma.model.entidade.Feature;

import com.google.gson.Gson;

/**
 * Servlet implementation class FeatureServlet
 */
@WebServlet("/CriarFeature.form")
public class CriarFeatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FeatureController controlador;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CriarFeatureServlet() {
		super();
		controlador = new FeatureController();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
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
			
			feature.fromJsonObject(
					(org.json.JSONObject) jsonObj.get("features"),true);
			controlador.gravar(feature, true);

			result.put("sucess", true);
			out.println(result);

		} catch (Exception e) {
			result.put("sucess", false);
			out.println(result);
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

}
