package br.unioeste.liproma.view.tarefa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.TarefaController;
import br.unioeste.liproma.model.entidade.Tarefa;
import br.unioeste.liproma.utils.AdapterUtils;

/**
 * Servlet implementation class TarefaServlet
 */
@WebServlet("/ListarTarefa.form")
public class ListarTarefaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TarefaController controle;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarTarefaServlet() {
		super();
		controle = new TarefaController();
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
			ArrayList<Tarefa> tarefas = (ArrayList<Tarefa>) controle.buscarTarefasPor("", "");

			result.put("tarefas", AdapterUtils.toJSONArrayAdapter(tarefas));
			result.put("total", tarefas.size());
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
