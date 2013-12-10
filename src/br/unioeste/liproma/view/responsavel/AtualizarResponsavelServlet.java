package br.unioeste.liproma.view.responsavel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.ResponsavelController;
import br.unioeste.liproma.model.entidade.Responsavel;

/**
 * Servlet implementation class ResponsavelServlet
 */
@WebServlet("/AtualizarResponsavel.form")
public class AtualizarResponsavelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ResponsavelController controlador;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AtualizarResponsavelServlet() {
		super();
		controlador = new ResponsavelController();
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
			Responsavel responsavel = new Responsavel();
			org.json.JSONObject jsonObj = new org.json.JSONObject(linha);
			
			responsavel.fromJsonObject(
					(org.json.JSONObject) jsonObj.get("responsaveis"),false);
			controlador.gravar(responsavel, false);

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
