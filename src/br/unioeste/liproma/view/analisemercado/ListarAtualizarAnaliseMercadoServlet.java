package br.unioeste.liproma.view.analisemercado;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.model.entidade.AnaliseMercado;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.persistencia.factory.AbstractDaoFactory;

import com.google.gson.Gson;

/**
 * Servlet implementation class AnaliseMercadoServlet
 */
@WebServlet("/ListarAnaliseMercado.frm")
public class ListarAtualizarAnaliseMercadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarAtualizarAnaliseMercadoServlet() {
		super();
		// TODO Auto-generated constructor stub
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

			List<IEntidade> results = AbstractDaoFactory.getDaoFactory()
					.getAnaliseMercadoDao().findAll();
			ArrayList<AnaliseMercado> analiseMercados = new ArrayList<>();

			for (IEntidade e : results) {
				analiseMercados.add((AnaliseMercado) e);
			}

			Gson gson = new Gson();
			result.put("analiseMercados", analiseMercados);
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
