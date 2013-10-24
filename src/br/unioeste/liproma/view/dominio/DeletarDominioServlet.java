package br.unioeste.liproma.view.dominio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import br.unioeste.liproma.controller.DominioController;
import br.unioeste.liproma.model.entidade.Dominio;
import br.unioeste.liproma.store.dao.DominioDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;
/**
 * Servlet implementation class DominioServlet
 */
@WebServlet("/DeletarDominio.form")
public class DeletarDominioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DominioController controlador;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletarDominioServlet() {
        super();
        controlador = new DominioController();
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
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		JSONObject result = new JSONObject();
		try {
			BufferedReader rd = request.getReader();
			String linha = "";
			linha = rd.readLine();
			Dominio dominio = new Dominio();
			org.json.JSONObject jsonObj = new org.json.JSONObject(linha);
			
			dominio.processJsonObject(
					(org.json.JSONObject) jsonObj.get("dominios"), false);
			controlador.excluir(dominio);

			Gson gson = new Gson();
			result.put("sucess", true);
			out.println(gson.toJson(result));
		} catch (Exception e) {
				result.put("sucess", false);
			e.printStackTrace();
		}
	}

}
