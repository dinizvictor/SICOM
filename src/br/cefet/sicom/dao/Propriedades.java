package br.cefet.sicom.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import br.cefet.sicom.telas.TelaLogin;
public class Propriedades {
	

	public static Properties getProp() throws IOException, URISyntaxException {
		
		Properties props = new Properties();
		InputStream file = Propriedades.class.getResourceAsStream("/br/cefet/sicom/arquivos/dados.properties");
		props.load(file);
		return props;

	}

	/*public static void  main(String args[]) throws IOException, URISyntaxException {
		String login; //Variavel que guardar� o login do servidor.
		String host; //Variavel que guardar� o host do servidor.
		String password; //Vari�vel que guardar� o password do us�ario.
		System.out.println("************Teste de leitura do arquivo de propriedades************");
		
		Properties prop = getProp();
				
		host = prop.getProperty("prop.servidorDeDados.host");
		password = prop.getProperty("prop.servidorDeDados.senha");
				
		System.out.println("Host = " + host);
		System.out.println("Password = " + password);
	}*/
	
}