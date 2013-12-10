package br.unioeste.liproma.view.dominio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.DominioController;
import br.unioeste.liproma.model.entidade.Dominio;

/**
 * Servlet implementation class DominioServlet
 */
@WebServlet("/AtualizarDominio.form")
public class AtualizarDominioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DominioController controlador;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AtualizarDominioServlet() {
		super();
		controlador = new DominioController();
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
			Dominio dominio = new Dominio();
			org.json.JSONObject jsonObj = new org.json.JSONObject(linha);
			
			dominio.fromJsonObject(
					(org.json.JSONObject) jsonObj.get("dominios"),false);
			controlador.gravar(dominio, false);

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
