package br.unioeste.liproma.view.backlogescopo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.BacklogEscopoController;
import br.unioeste.liproma.model.entidade.BacklogEscopo;
import br.unioeste.liproma.model.entidade.FeatureBacklogEscopo;

/**
 * Servlet implementation class BacklogEscopoServlet
 */
@WebServlet("/AtualizarFeatureBacklogEscopo.form")
public class AtualizarFeatureBacklogEscopoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BacklogEscopoController controlador;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AtualizarFeatureBacklogEscopoServlet() {
		super();
		controlador = new BacklogEscopoController();
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
			FeatureBacklogEscopo fBacklogEscopo = new FeatureBacklogEscopo();
			org.json.JSONObject jsonObj = new org.json.JSONObject(linha);
			
			fBacklogEscopo.fromJsonObject(
					(org.json.JSONObject) jsonObj.get("features"),false);
			controlador.gravarFeatureBacklogEscopo(fBacklogEscopo, false);

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
