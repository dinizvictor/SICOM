package br.cefet.sicom.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import br.cefet.sicom.dao.Propriedades;
import br.cefet.sicom.telas.TelaLogin;

public class Main {

	public static void main(String[] args) {
				
		TelaLogin tl = new TelaLogin();
		tl.setVisible(true);
		
	}

}
