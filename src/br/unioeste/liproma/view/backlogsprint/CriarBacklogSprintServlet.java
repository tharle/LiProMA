package br.unioeste.liproma.view.backlogsprint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.BacklogSprintController;
import br.unioeste.liproma.model.entidade.BacklogSprint;

/**
 * Servlet implementation class BacklogEscopoServlet
 */
@WebServlet("/CriarBacklogSprint.form")
public class CriarBacklogSprintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BacklogSprintController controlador;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CriarBacklogSprintServlet() {
		super();
		controlador = new BacklogSprintController();
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
			BacklogSprint backlogSprint = new BacklogSprint();
			org.json.JSONObject jsonObj = new org.json.JSONObject(linha);
			
			backlogSprint.fromJsonObject(
					(org.json.JSONObject) jsonObj.get("backlogSprints"),true);
			controlador.gravar(backlogSprint, true);

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
