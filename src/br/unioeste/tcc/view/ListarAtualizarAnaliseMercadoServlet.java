package br.unioeste.tcc.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.tcc.modelo.entidade.AnaliseMercado;
import br.unioeste.tcc.persistencia.dao.AnaliseMercadoDao;
import br.unioeste.tcc.persistencia.factory.PostgresqlDaoFactory;

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
		// String json = request.getParameter("json");
		// JSONObject jsonData = (JSONObject) JSONValue.parse(json);
		// String action = request.getParameter("action");

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		JSONObject result = new JSONObject();
		try {

			AnaliseMercadoDao dao = PostgresqlDaoFactory.getDaoFactory()
					.getAnaliseMercadoDao();
			List<AnaliseMercado> analiseMercados = dao.findAll();

			
			Gson gson = new Gson();
			result.put("analiseMercados", analiseMercados);
			result.put("total", analiseMercados.size());
			result.put("sucess", true);
			
			out.println(gson.toJson(result));
		} catch (Exception e) {
			result.put("total", 0);
			result.put("sucess", false);
			out.println(JSONObject.toJSONString(result));

			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}

	}

}
