package br.unioeste.liproma.view.produto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import br.unioeste.liproma.controller.FeatureController;
import br.unioeste.liproma.model.entidade.Feature;
import br.unioeste.liproma.utils.AdapterUtils;

@WebServlet("/ListarFeatureProduto.form")
public class ListarFeatureProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FeatureController controle;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarFeatureProdutoServlet() {
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
			Set<Feature> featuressSelecionados = new HashSet<Feature>();
			Map<String, String[]> parameterMap = request.getParameterMap();
			String root;
			if (parameterMap != null
					&& parameterMap.containsKey("produtoId")) {
				String idProduto= request
						.getParameter("produtoId");
				featuressSelecionados = controle
						.buscarFeaturesPorProduto(idProduto);
			}
			features = (ArrayList<Feature>) controle.buscarFeaturesPorCampo("", "");

			for (Feature fs : featuressSelecionados) {
				for (Feature f : features) {
					if (fs.getId() == f.getId()) {
						f.setSelecionado(true);
						break;
					}
				}
			}

			result.put("produtoFeatures", AdapterUtils.toJSONArrayAdapter(features));
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
