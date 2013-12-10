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
 * Servlet implementation class BacklogSprintServlet
 */
@WebServlet("/DeletarBacklogSprint.form")
public class DeletarBacklogSprintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BacklogSprintController controlador;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletarBacklogSprintServlet() {
        super();
        controlador = new BacklogSprintController();
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
			BacklogSprint backlogSprint = new BacklogSprint();
			org.json.JSONObject jsonObj = new org.json.JSONObject(linha);
			
			backlogSprint.fromJsonObject(
					(org.json.JSONObject) jsonObj.get("backlogSprints"), false);
			controlador.excluir(backlogSprint);

			result.put("sucess", true);
			out.println(result);
		} catch (Exception e) {
				result.put("sucess", false);
			e.printStackTrace();
		}
	}

}
