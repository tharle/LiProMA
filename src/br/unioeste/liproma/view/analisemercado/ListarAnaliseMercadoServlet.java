package br.unioeste.liproma.view.analisemercado;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.AnaliseMercadoController;
import br.unioeste.liproma.model.entidade.AnaliseMercado;
import br.unioeste.liproma.model.entidade.DominioAnaliseMercado;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;

import com.google.gson.Gson;

/**
 * Servlet implementation class AnaliseMercadoServlet
 */
@WebServlet("/ListarAnaliseMercado.form")
public class ListarAnaliseMercadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AnaliseMercadoController controle;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarAnaliseMercadoServlet() {
		super();
		controle = new AnaliseMercadoController();
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

			
			ArrayList<AnaliseMercado> analiseMercados = (ArrayList<AnaliseMercado>) controle.buscarAnaliseMercadosPorId("", "");

			HashMap<String, String> dominios = new HashMap<>();
			dominios.put("numero 1", "numero1");
			
			Gson gson = new Gson();
			result.put("analiseMercados", analiseMercados);
			result.put("value", "Valor locao");
			result.put("total", analiseMercados.size());
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
