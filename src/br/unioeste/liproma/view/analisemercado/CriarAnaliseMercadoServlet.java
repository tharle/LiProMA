package br.unioeste.liproma.view.analisemercado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.AnaliseMercadoController;
import br.unioeste.liproma.model.entidade.AnaliseMercado;

import com.google.gson.Gson;

/**
 * Servlet implementation class AnaliseMercadoServlet
 */
@WebServlet("/CriarAnaliseMercado.form")
public class CriarAnaliseMercadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AnaliseMercadoController controlador;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CriarAnaliseMercadoServlet() {
		super();
		controlador = new AnaliseMercadoController();
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
			AnaliseMercado analiseMercado = new AnaliseMercado();
			org.json.JSONObject jsonObj = new org.json.JSONObject(linha);
			
			analiseMercado.fromJsonObject(
					(org.json.JSONObject) jsonObj.get("analiseMercados"),true);
			controlador.gravar(analiseMercado, true);

			result.put("sucess", true);
			out.println(result);

		} catch (Exception e) {
			result.put("sucess", false);
			out.println(result);
		} finally {
			out.flush();
			out.close();
		}
	}

}
