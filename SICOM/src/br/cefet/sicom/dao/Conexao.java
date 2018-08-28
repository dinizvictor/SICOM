package br.cefet.sicom.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;


public class Conexao {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
 
    // Conectar ao banco
    public static Connection abrir() throws Exception {
        
    	String USUARIO = "";
        String SENHA = "";
        String HOST = "";
    	
    	Properties p = Propriedades.getProp();
    	
    	HOST = p.getProperty("prop.servidorDeDados.host");
    	SENHA = p.getProperty("prop.servidorDeDados.senha");
    	USUARIO = p.getProperty("prop.servidorDeDados.usuario");
    	    			
    	String URL = "jdbc:mysql://"+HOST+"/sepatbd?useSSL=false";
				
    	// Registrar o driver
        Class.forName(DRIVER);
        
        // Capturar a conexão
        Connection con = DriverManager.getConnection(URL, USUARIO, SENHA);
        
        // Retorna a conexao aberta
        return con;
        
             
    }
    
    public static boolean isConectado(){
    	
    	try {
    		
			abrir();
			return true;
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
			
			
		}
    	
    }
    
    public boolean isExistente(){
        ArrayList<String> list = new ArrayList<String>();
        String database = "sepatbd";
        Connection con;
        boolean existente = false;
		try {
			con = abrir();
			Statement st = con.createStatement();
	        DatabaseMetaData meta = con.getMetaData();
	        ResultSet rs = meta.getCatalogs();
	        while (rs.next()) {
	            String listofDatabases = rs.getString("TABLE_CAT");
	            list.add(listofDatabases);
	        }
	        if (list.contains(database)) {
	            existente = true;
	        }
	        
		} catch (Exception e) {
			
			return existente;
			
		}
		
		return existente;
        
    }
    

}
