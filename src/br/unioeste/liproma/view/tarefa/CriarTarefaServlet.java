package br.unioeste.liproma.view.tarefa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.TarefaController;
import br.unioeste.liproma.model.entidade.Tarefa;

/**
 * Servlet implementation class TarefaServlet
 */
@WebServlet("/CriarTarefa.form")
public class CriarTarefaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TarefaController controlador;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CriarTarefaServlet() {
		super();
		controlador = new TarefaController();
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
			Tarefa tarefa = new Tarefa();
			org.json.JSONObject jsonObj = new org.json.JSONObject(linha);
			
			tarefa.fromJsonObject(
					(org.json.JSONObject) jsonObj.get("tarefas"),true);
			controlador.gravar(tarefa, true);

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
