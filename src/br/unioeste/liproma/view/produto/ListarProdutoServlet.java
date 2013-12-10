package br.unioeste.liproma.view.produto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.ProdutoController;
import br.unioeste.liproma.model.entidade.Produto;
import br.unioeste.liproma.utils.AdapterUtils;

import com.google.gson.Gson;

/**
 * Servlet implementation class ProdutoServlet
 */
@WebServlet("/ListarProduto.form")
public class ListarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdutoController controle;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarProdutoServlet() {
		super();
		controle = new ProdutoController();
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

			
			ArrayList<Produto> produtos = (ArrayList<Produto>) controle.buscarProdutosPorCampo("", "");

			result.put("produtos", AdapterUtils.toJSONArrayAdapter( produtos));
			result.put("total", produtos.size());
			result.put("sucess", true);
			out.println(result);
		} catch (Exception e) {
			Gson gson = new Gson();
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
