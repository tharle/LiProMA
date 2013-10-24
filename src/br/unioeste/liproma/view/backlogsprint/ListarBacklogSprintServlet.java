package br.unioeste.liproma.view.backlogsprint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.FeatureController;
import br.unioeste.liproma.model.entidade.BacklogEscopo;
import br.unioeste.liproma.model.entidade.BacklogSprint;
import br.unioeste.liproma.model.entidade.Feature;

import com.google.gson.Gson;

/**
 * Servlet implementation class FeatureServlet
 */
@WebServlet("/ListarBacklogSprint.form")
public class ListarBacklogSprintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FeatureController controle;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarBacklogSprintServlet() {
		super();
		controle = new FeatureController();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	@SuppressWarnings("unchecked")
	private void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		JSONObject result = new JSONObject();
		try {
			ArrayList<BacklogSprint> sprints = new ArrayList<>();
			sprints.add(new BacklogSprint(1,"Backlog de Escopo para Feature \"f1\"", "Especificar sub-features", "[Tharle, Henrique, Julio,]", "Em andamento", "[f1,]"));
			sprints.add(new BacklogSprint(2,"Backlog de Escopo para Feature \"f1\"", "Refinar Features", "[Tharle, ]", "Cancelada", "[f1,]"));
			sprints.add(new BacklogSprint(3,"Backlog de Escopo para Feature \"f1\"", "Identificar Sub-features dos experts do dominio", "[Tharle, Henrique,]", "Feita", "[f1,]"));
			Gson gson = new Gson();
			result.put("backlogsprints", sprints);
			result.put("total", sprints.size());
			result.put("sucess", true);
			out.println(gson.toJson(result));
		} catch (Exception e) {
			Gson gson = new Gson();
			result.put("total", 0);
			result.put("sucess", false);
			out.println(gson.toJson(result));

			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}

	}
}
