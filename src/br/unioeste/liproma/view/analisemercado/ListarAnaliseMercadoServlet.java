package br.unioeste.liproma.view.analisemercado;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.AnaliseMercadoController;
import br.unioeste.liproma.model.entidade.AnaliseMercado;
import br.unioeste.liproma.model.entidade.Dominio;
import br.unioeste.liproma.model.entidade.DominioAnaliseMercado;
import br.unioeste.liproma.model.entidade.IEntidade;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;
import br.unioeste.liproma.utils.AdapterUtils;

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

			
			ArrayList<AnaliseMercado> analiseMercados;
			
			
			
			
			Map<String, String[]> parameterMap = request.getParameterMap();
			String root;
			if (parameterMap != null
					&& parameterMap.containsKey("analiseMercadoId")) {
				String idAnaliseMercado = request
						.getParameter("analiseMercadoId");
				analiseMercados = (ArrayList<AnaliseMercado>) controle.buscarAnaliseMercadosPorId("id", String.valueOf(idAnaliseMercado));
			}else{
				analiseMercados = (ArrayList<AnaliseMercado>) controle.buscarAnaliseMercadosPorId("", "");
			}
			
			
			
			
			//TODO transformar essa lista em json object antes de pssar no result
			result.put("analiseMercados", AdapterUtils.toJSONArrayAdapter(analiseMercados));
			result.put("value", "Valor locao");
			result.put("total", analiseMercados.size());
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
