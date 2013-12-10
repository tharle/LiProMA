package br.unioeste.liproma.utils;

import java.util.ArrayList;

import br.unioeste.liproma.controller.AnaliseMercadoController;
import br.unioeste.liproma.model.entidade.AnaliseMercado;

public class MainClassTeste {
	public static void main(String[] args){
		
		AnaliseMercadoController controle = new AnaliseMercadoController();
		try {
			ArrayList<AnaliseMercado> analiseMercados = (ArrayList<AnaliseMercado>) controle.buscarAnaliseMercadosPorId("", "");
			for (AnaliseMercado analiseMercado : analiseMercados) {
				System.out.print(analiseMercado.getDominioNomes());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
