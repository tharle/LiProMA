package br.unioeste.liproma.view.produto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.ProdutoController;
import br.unioeste.liproma.model.entidade.Produto;

import com.google.gson.Gson;

/**
 * Servlet implementation class ProdutoServlet
 */
@WebServlet("/CriarProduto.form")
public class CriarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProdutoController controlador;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CriarProdutoServlet() {
		super();
		controlador = new ProdutoController();
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
			Produto produto = new Produto();
			org.json.JSONObject jsonObj = new org.json.JSONObject(linha);
			
			produto.fromJsonObject(
					(org.json.JSONObject) jsonObj.get("produtos"),true);
			controlador.gravar(produto, true);

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
