package br.cefet.sicom.main;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import br.cefet.sicom.documentos.GeradorDeTexto;
import br.cefet.sicom.modelo.Usuario;

public class Teste {
	
	public static void main(String [] args){
		
		/*Calendar c = new GregorianCalendar();
		Usuario u = new Usuario();
		GeradorDeTexto.gerarRelatorioElanConsumo(c, u, 0, 0);*/
		Scanner ler = new Scanner(System.in);
		double acm = 0;
		while(true) {
			System.out.println("Horas extras:");
			acm += ler.nextDouble();
			System.out.println(acm);
		}
		
		
	}

}
