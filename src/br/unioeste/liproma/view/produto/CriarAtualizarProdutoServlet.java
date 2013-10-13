package br.unioeste.liproma.view.produto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.simple.JSONObject;

import br.unioeste.liproma.persistencia.dao.AnaliseMercadoDao;
import br.unioeste.liproma.persistencia.factory.AbstractDaoFactory;
/**
 * Servlet implementation class AnaliseMercadoServlet
 */
@WebServlet("/CriarProduto.frm")
public class CriarAtualizarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriarAtualizarProdutoServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		JSONObject result = new JSONObject();
		try {
			AnaliseMercadoDao dao = AbstractDaoFactory.getDaoFactory().getAnaliseMercadoDao();
			//dao.insert(analiseMercado);
			result.put("sucess", true);
//			result.put("analiseMercados",
//					new AnaliseMercado[] { analiseMercado });
		} catch (Exception e) {
				result.put("sucess", false);
			e.printStackTrace();
		}
	}

}
