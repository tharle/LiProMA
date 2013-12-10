package br.unioeste.liproma.view.feature;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.FeatureController;
import br.unioeste.liproma.model.entidade.Feature;
import br.unioeste.liproma.utils.AdapterUtils;

import com.google.gson.Gson;

/**
 * Servlet implementation class FeatureServlet
 */
@WebServlet("/ListarFeature.form")
public class ListarFeatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FeatureController controle;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarFeatureServlet() {
		super();
		controle = new FeatureController();
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
			ArrayList<Feature> features = new ArrayList<>();
			Map<String, String[]> parameterMap = request.getParameterMap();
			String root;
			if (parameterMap != null && parameterMap.containsKey("id")) {
				root = "features";
				String idFeatureSelecionada = request.getParameter("id");
				features = (ArrayList<Feature>) controle.buscarFeatureXFeature(Long.parseLong(idFeatureSelecionada));
			}else if (parameterMap != null && parameterMap.containsKey("featurePrincipal")) {
				root = "features";
				String featurePrincipal = request.getParameter("featurePrincipal");
				if(featurePrincipal.equals("true")){
					features = (ArrayList<Feature>) controle
							.buscarFeaturesPorCampo("principal", "true");
				}
				
			} else if (parameterMap != null && parameterMap.containsKey("idBacklogEscopo")) {
				root = "featureBacklogEscopos";
				String idBacklogEscopo = request.getParameter("idBacklogEscopo");
					features =  new ArrayList<>( controle
							.buscarFeaturesPorIdBacklogEscopo(idBacklogEscopo));
			} else {
				root = "features";
				features = (ArrayList<Feature>) controle
						.buscarFeaturesPorCampo("", "");
			}
			result.put(root, AdapterUtils.toJSONArrayAdapter( features));
			result.put("total", features.size());
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
