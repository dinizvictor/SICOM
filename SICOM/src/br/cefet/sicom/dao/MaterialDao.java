package br.cefet.sicom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.cefet.sicom.modelo.Material;

public class MaterialDao {
	
	public static void cadastrar(Material m){
		
		String sql = "INSERT INTO material(descricao,tipoUnid,saldo,categoria,localizacao) VALUES (?,?,?,?,?)";
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);  
            stmt.setString(1, m.getDescricao().toUpperCase());  
            stmt.setString(2, m.getTipoUnid().toString().toUpperCase());  
            stmt.setInt(3, m.getSaldo());
            stmt.setString(4, m.getCategoria().toString());
            stmt.setString(5, m.getLocalizacao().toString());
            stmt.execute();  
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	public static void excluirPorId(int idMaterial){
		
		String sql = "DELETE FROM material WHERE idMaterial = ?";
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);  
			stmt.setInt(1, idMaterial);
            stmt.execute();                      
            stmt.close();
	        
		} 
		
		catch(MySQLIntegrityConstraintViolationException e){
			
			JOptionPane.showMessageDialog(null, "O material não poderá ser excluído, pois existem solicitações ou entradas relacionadas ao mesmo!");
		
		}
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	public static void atualizar(Material m){
		
		String sql = "UPDATE material SET descricao = ?, tipoUnid = ?, saldo = ?, categoria = ?, localizacao = ?  WHERE idMaterial = " + m.getIdMaterial();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, m.getDescricao());
			stmt.setString(2, m.getTipoUnid().toString());
			stmt.setInt(3, m.getSaldo());
			stmt.setString(4, m.getCategoria().toString());
			stmt.setString(5, m.getLocalizacao().toString());
            stmt.execute();                      
            stmt.close();
	        
		} 
		
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	public static Material listarPorId(int idMaterial){
		
		String sql = "SELECT * FROM material WHERE idMaterial = " + idMaterial;
		Material m = null;
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);  
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            	
            	m = new Material();
				m.setIdMaterial(rs.getInt("idMaterial"));
            	m.setDescricao(rs.getString("descricao"));
            	m.setTipoUnid(rs.getString("tipoUnid"));
            	m.setSaldo(rs.getInt("saldo"));  
            	m.setCategoria(rs.getString("categoria"));
            	m.setLocalizacao(rs.getString("localizacao"));
              	
            	
            }            
                       
            stmt.close();
	        
		} 
		
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return m;
		
	}
	
	public static ArrayList<Material> listarTodos(){
		
		String sql = "SELECT * FROM material";
		ArrayList<Material> materiais = new ArrayList<Material>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			Material m = null;
			
			while(rs.next()){
				
				m = new Material();
				m.setIdMaterial(rs.getInt("idMaterial"));
            	m.setDescricao(rs.getString("descricao"));
            	m.setTipoUnid(rs.getString("tipoUnid"));
            	m.setSaldo(rs.getInt("saldo"));  
            	m.setCategoria(rs.getString("categoria"));
            	m.setLocalizacao(rs.getString("localizacao"));
              	
            	            	
            	materiais.add(m);
				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return materiais;
		
	}
	
	public static ArrayList<Material> listarPorDescricaoSubString(String desc, String ordenacao){
		
		String sql = "SELECT * FROM material where descricao like '%"+desc.toUpperCase()+"%' ORDER BY "+ordenacao;
		ArrayList<Material> materiais = new ArrayList<Material>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Material m = null;
			
			while(rs.next()){
				
				m = new Material();
				m.setIdMaterial(rs.getInt("idMaterial"));
            	m.setDescricao(rs.getString("descricao"));
            	m.setTipoUnid(rs.getString("tipoUnid"));
            	m.setSaldo(rs.getInt("saldo"));  
            	m.setCategoria(rs.getString("categoria"));
            	m.setLocalizacao(rs.getString("localizacao"));
            	            	
            	materiais.add(m);
				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return materiais;
		
	}
	
	public static ArrayList<Material> listarPorDescricao(String desc){
		
		String sql = "SELECT * FROM material where descricao like '"+desc.toUpperCase()+"'";
		ArrayList<Material> materiais = new ArrayList<Material>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Material m = null;
			
			while(rs.next()){
				
				m = new Material();
				m.setIdMaterial(rs.getInt("idMaterial"));
            	m.setDescricao(rs.getString("descricao"));
            	m.setTipoUnid(rs.getString("tipoUnid"));
            	m.setSaldo(rs.getInt("saldo"));  
            	m.setCategoria(rs.getString("categoria"));
            	m.setLocalizacao(rs.getString("localizacao"));
              	            	
            	materiais.add(m);
				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return materiais;
		
	}
	
	

}
