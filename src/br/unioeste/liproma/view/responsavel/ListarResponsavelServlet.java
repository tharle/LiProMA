package br.unioeste.liproma.view.responsavel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.ResponsavelController;
import br.unioeste.liproma.model.entidade.Responsavel;
import br.unioeste.liproma.utils.AdapterUtils;

/**
 * Servlet implementation class ResponsavelServlet
 */
@WebServlet("/ListarResponsavel.form")
public class ListarResponsavelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ResponsavelController controle;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarResponsavelServlet() {
		super();
		controle = new ResponsavelController();
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
			ArrayList<Responsavel> responsavels = (ArrayList<Responsavel>) controle.buscarResponsavelsPor("", "");

			result.put("responsaveis", AdapterUtils.toJSONArrayAdapter(responsavels));
			result.put("total", responsavels.size());
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
