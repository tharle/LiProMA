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
/**
 * Servlet implementation class BacklogEscopoServlet
 */
@WebServlet("/DeletarBacklogEscopo.form")
public class DeletarBacklogEscopoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BacklogEscopoController controlador;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletarBacklogEscopoServlet() {
        super();
        controlador = new BacklogEscopoController();
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
			BacklogEscopo backlogEscopo = new BacklogEscopo();
			org.json.JSONObject jsonObj = new org.json.JSONObject(linha);
			
			backlogEscopo.fromJsonObject(
					(org.json.JSONObject) jsonObj.get("backlogEscopos"), false);
			controlador.excluir(backlogEscopo);

			result.put("sucess", true);
			out.println(result);
		} catch (Exception e) {
				result.put("sucess", false);
			e.printStackTrace();
		}
	}

}
