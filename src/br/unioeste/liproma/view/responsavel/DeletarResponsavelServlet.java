package br.unioeste.liproma.view.responsavel;

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

import br.unioeste.liproma.controller.ResponsavelController;
import br.unioeste.liproma.model.entidade.Responsavel;
import br.unioeste.liproma.store.dao.ResponsavelDao;
import br.unioeste.liproma.store.factory.AbstractDaoFactory;
/**
 * Servlet implementation class ResponsavelServlet
 */
@WebServlet("/DeletarResponsavel.form")
public class DeletarResponsavelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ResponsavelController controlador;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletarResponsavelServlet() {
        super();
        controlador = new ResponsavelController();
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
			Responsavel responsavel = new Responsavel();
			org.json.JSONObject jsonObj = new org.json.JSONObject(linha);
			
			responsavel.fromJsonObject(
					(org.json.JSONObject) jsonObj.get("responsaveis"), false);
			controlador.excluir(responsavel);

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
