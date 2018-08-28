package br.cefet.sicom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.cefet.sicom.modelo.Material;
import br.cefet.sicom.modelo.Solicitante;

public class SolicitanteDao {
	
	
	public static void excluirPorId(int idSolicitante){
		
		String sql = "DELETE FROM solicitante WHERE idSolicitante = ?";
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);  
			stmt.setInt(1, idSolicitante);
            stmt.execute();                      
            stmt.close();
	        
		} 
		
		catch(MySQLIntegrityConstraintViolationException e){
			
			JOptionPane.showMessageDialog(null, "O solicitante não poderá ser excluído, pois possui solicitações!");
		
		}
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	
	public static void cadastrar(Solicitante s){
		
		String sql = "INSERT INTO solicitante(nome,setor,ocupacao,siape) VALUES (?,?,?,?)";
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);  
            stmt.setString(1, s.getNome().toUpperCase());  
            stmt.setString(2, s.getSetor().toUpperCase());  
            stmt.setString(3, s.getOcupacao().toUpperCase()); 
            stmt.setInt(4, s.getSiape());
            stmt.execute();  
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	public static ArrayList<Solicitante> listarTodos(){
		
		String sql = "SELECT * FROM solicitante";
		ArrayList<Solicitante> solicitantes = new ArrayList<Solicitante>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			Solicitante s = null;
			
			while(rs.next()){
				
				s = new Solicitante();
				s.setIdSolicitante(rs.getInt("idSolicitante"));
            	s.setNome(rs.getString("nome"));
            	s.setSetor(rs.getString("setor"));
            	s.setOcupacao(rs.getString("ocupacao"));
            	s.setSiape(rs.getInt("siape"));
            	
            	
            	solicitantes.add(s);
				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return solicitantes;
		
	}
	
	public static void atualizar(Solicitante s){
		
		String sql = "UPDATE solicitante SET nome = ?, setor = ?, ocupacao = ?, siape = ?  WHERE idMaterial = " + s.getIdSolicitante();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, s.getNome());
			stmt.setString(2, s.getSetor());
			stmt.setString(3, s.getOcupacao());
			stmt.setInt(4, s.getSiape());
            stmt.execute();                      
            stmt.close();
	        
		} 
		
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	public static Solicitante listarPorId(int idSolicitante){
		
		String sql = "SELECT * FROM solicitante WHERE idSolicitante = " + idSolicitante;
		Solicitante s = null;
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);  
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            	
            	s = new Solicitante();
            	s.setIdSolicitante(rs.getInt("idSolicitante"));
            	s.setNome(rs.getString("nome"));
            	s.setSetor(rs.getString("setor"));
            	s.setOcupacao(rs.getString("ocupacao"));
            	s.setSiape(rs.getInt("siape"));
            	
            	
            }            
                       
            stmt.close();
	        
		} 
		
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return s;
		
	}
	
	public static ArrayList<Solicitante> listarPorDescricao(String nome){
		
		String sql = "SELECT * FROM solicitante where nome like '%"+nome.toUpperCase()+"%'";
		ArrayList<Solicitante> solicitantes = new ArrayList<Solicitante>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Solicitante s = null;
			
			while(rs.next()){
				
				s = new Solicitante();
				s.setIdSolicitante(rs.getInt("idSolicitante"));
            	s.setNome(rs.getString("nome"));
            	s.setSetor(rs.getString("setor"));
            	s.setOcupacao(rs.getString("ocupacao"));
            	s.setSiape(rs.getInt("siape"));
            	
            	
            	solicitantes.add(s);
				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return solicitantes;
		
	}
	
	

}
