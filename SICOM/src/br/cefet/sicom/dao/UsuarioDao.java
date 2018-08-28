package br.cefet.sicom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.cefet.sicom.modelo.TipoUsuario;
import br.cefet.sicom.modelo.Usuario;

public class UsuarioDao {
	
	public static void cadastrar(Usuario u){
		
		String sql = "INSERT INTO usuario(nome,siape,senha,tipo) VALUES (?,?,?,?)";
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);  
            
			stmt.setString(1, u.getNome().toUpperCase());
            stmt.setInt(2, u.getSiape()); 
            stmt.setString(3, u.getSenha()); 
            stmt.setString(4, u.getTipo().toString().toUpperCase()); 
            
            stmt.execute();  
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	public static void excluirPorId(int idUsuario){
		
		String sql = "DELETE FROM usuario WHERE idUsuario = ?";
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);  
			stmt.setInt(1, idUsuario);
            stmt.execute();                      
            stmt.close();
	        
		} 
		
		catch(MySQLIntegrityConstraintViolationException e){
			
			JOptionPane.showMessageDialog(null, "O usuário não poderá ser excluído, pois autorizou solicitações!");
		
		}
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	public static void atualizar(Usuario u){
		
		String sql = "UPDATE usuario SET nome = ?, siape = ?, senha = ?, tipo = ?  WHERE idUsuario = " + u.getIdUsuario();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, u.getNome().toUpperCase());
            stmt.setInt(2, u.getSiape()); 
            stmt.setString(3, u.getSenha()); 
            stmt.setString(4, u.getTipo().toString()); 
            stmt.execute();                      
            stmt.close();
	        
		} 
		
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	public static Usuario listarPorId(int idUsuario){
		
		String sql = "SELECT * FROM usuario WHERE idUsuario = " + idUsuario;
		Usuario u = null;
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);  
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            	
            	u = new Usuario();
            	u.setIdUsuario(rs.getInt("idUsuario"));
            	u.setNome(rs.getString("nome"));
            	u.setSiape(rs.getInt("siape"));
            	u.setSenha(rs.getString("senha"));
            	u.setTipo(TipoUsuario.valueOf(rs.getString("tipo")));
            		
            	
            }            
                       
            stmt.close();
	        
		} 
		
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return u;
		
	}
	
	public static ArrayList<Usuario> listarTodos(){
		
		String sql = "SELECT * FROM usuario";
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			Usuario u = null;
			
			while(rs.next()){
				
				u = new Usuario();
            	u.setIdUsuario(rs.getInt("idUsuario"));
            	u.setNome(rs.getString("nome"));
            	u.setSiape(rs.getInt("siape"));
            	u.setSenha(rs.getString("senha"));
            	u.setTipo(TipoUsuario.valueOf(rs.getString("tipo")));
            	
            	usuarios.add(u);
				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return usuarios;
		
	}
	
	public static Usuario autenticar(int siape, char [] senha){
		
		String sql = "SELECT * FROM usuario where siape = ? AND senha like ?";
		Usuario u = null;
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, siape);
			stmt.setString(2, new String(senha));
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				
				u = new Usuario(rs.getInt("idUsuario"), rs.getString("nome"), rs.getInt("siape"), rs.getString("senha"), TipoUsuario.valueOf(rs.getString("tipo")));
				stmt.close();
				return u;
			
			}
						
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			
		}
		
		return u;
		
	}
	
	
	
	

}
