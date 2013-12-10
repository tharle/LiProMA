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

import br.unioeste.liproma.controller.BacklogSprintController;
import br.unioeste.liproma.controller.FeatureController;
import br.unioeste.liproma.model.entidade.BacklogSprint;
import br.unioeste.liproma.model.entidade.Dominio;
import br.unioeste.liproma.model.entidade.Feature;
import br.unioeste.liproma.utils.AdapterUtils;

/**
 * Servlet implementation class FeatureServlet
 */
@WebServlet("/ListarBacklogSprint.form")
public class ListarBacklogSprintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BacklogSprintController controle;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarBacklogSprintServlet() {
		super();
		controle = new BacklogSprintController();
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
			ArrayList<BacklogSprint> sprints = new ArrayList<>() ;
			Map<String, String[]> parameterMap = request.getParameterMap();
			if (parameterMap != null && parameterMap.containsKey("idBacklogEscopo")) {
				String idBacklogEscopo = request.getParameter("idBacklogEscopo");
				sprints =  (ArrayList<BacklogSprint>) controle.buscarBacklogSprintsPorCampo("id_backlog_escopo", idBacklogEscopo);
			} else {
				sprints = (ArrayList<BacklogSprint>) controle.buscarBacklogSprintsPorCampo("", "");
			}
			
			result.put("backlogSprints", AdapterUtils.toJSONArrayAdapter(sprints));
			result.put("total", sprints.size());
			result.put("sucess", true);
			out.println(result);
		} catch (Exception e) {
			result.put("total", 0);
			result.put("sucess", false);
			out.println(result);

			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}

	}
}
