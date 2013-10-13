package br.unioeste.liproma.view.produto;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import br.unioeste.liproma.model.entidade.AnaliseMercado;
import br.unioeste.liproma.persistencia.dao.AnaliseMercadoDao;
import br.unioeste.liproma.persistencia.factory.AbstractDaoFactory;

/**
 * Servlet implementation class AnaliseMercadoServlet
 */
@WebServlet("/AtualizarProduto.frm")
public class AtualizarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AtualizarProdutoServlet() {
		super();
		// TODO Auto-generated constructor stub
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getSession();
			BufferedReader rd = request.getReader();
			String linha = "";
			AnaliseMercadoDao dao = AbstractDaoFactory.getDaoFactory()
					.getAnaliseMercadoDao();
			while ((linha = rd.readLine()) != null) {
				System.out.println(linha);
				AnaliseMercado analiseMercado = new AnaliseMercado();
				org.json.JSONObject jsonObj = new org.json.JSONObject(linha);
				
				analiseMercado.processJsonObject((org.json.JSONObject)jsonObj.get("analiseMercados"));
				dao.update(analiseMercado);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
