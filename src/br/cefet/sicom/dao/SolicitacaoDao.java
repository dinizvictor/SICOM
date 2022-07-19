package br.cefet.sicom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import br.cefet.sicom.modelo.Entrada;
import br.cefet.sicom.modelo.Material;
import br.cefet.sicom.modelo.Solicitacao;
import br.cefet.sicom.modelo.Solicitante;
import br.cefet.sicom.modelo.Usuario;

public class SolicitacaoDao {
	
	
	public static void excluirPorId(int idSolicitacao){
		
		String sql = "DELETE FROM solicitacao WHERE idSolicitacao = ?";
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);  
			stmt.setInt(1, idSolicitacao);
            stmt.execute();                      
            stmt.close();
	        
		} 
		
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	
	public static void cadastrar(Solicitacao s){
		String data = s.getData().get(Calendar.YEAR) +"-"+ (s.getData().get(Calendar.MONTH) + 1) +"-"+ s.getData().get(Calendar.DAY_OF_MONTH) +" "+
				      s.getData().get(Calendar.HOUR_OF_DAY) +":"+ s.getData().get(Calendar.MINUTE) +":"+ s.getData().get(Calendar.SECOND);
		String sql = "INSERT INTO solicitacao(dataSolicitacao,qtdFornecida,idSolicitante,idMaterial,idUsuario) VALUES (?,?,?,?,?)";
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql); 
			stmt.setString(1, data);
            stmt.setInt(2, s.getQtdFornecida());  
            stmt.setInt(3, s.getSolicitante().getIdSolicitante());  
            stmt.setInt(4, s.getMaterial().getIdMaterial()); 
            stmt.setInt(5, s.getUsuario().getIdUsuario());
            stmt.execute();  
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
						
		
	}
	
	public static ArrayList<Solicitacao> listarTodos(){
		
		String sql = "SELECT * FROM solicitacao";
		ArrayList<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			Solicitacao solicitacao = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			while(rs.next()){
				
				solicitacao = new Solicitacao();
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setQtdFornecida(rs.getInt("qtdFornecida"));
				Calendar calendar = Calendar.getInstance();
				String dataHoraString = rs.getString("dataSolicitacao");
				Date dataHora = format.parse(dataHoraString);
				calendar.setTime(dataHora);
				solicitacao.setData(calendar);
            	solicitacao.setSolicitante(SolicitanteDao.listarPorId(rs.getInt("idSolicitante")));
            	solicitacao.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	solicitacao.setUsuario(UsuarioDao.listarPorId(rs.getInt("idUsuario")));
            	
            	solicitacoes.add(solicitacao);
				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
		
		return solicitacoes;
		
	}
	
	public static ArrayList<Solicitacao> listarPorMes(int mes, int ano){
		
		String sql = "SELECT * FROM solicitacao where MONTH(dataSolicitacao) = '"+ String.valueOf(mes) +"' AND YEAR(dataSolicitacao) = '"+ String.valueOf(ano)+"'";
		ArrayList<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			Solicitacao solicitacao = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			while(rs.next()){
				
				solicitacao = new Solicitacao();
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setQtdFornecida(rs.getInt("qtdFornecida"));
				Calendar calendar = Calendar.getInstance();
				String dataHoraString = rs.getString("dataSolicitacao");
				Date dataHora = format.parse(dataHoraString);
				calendar.setTime(dataHora);
				solicitacao.setData(calendar);
            	solicitacao.setSolicitante(SolicitanteDao.listarPorId(rs.getInt("idSolicitante")));
            	solicitacao.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	solicitacao.setUsuario(UsuarioDao.listarPorId(rs.getInt("idUsuario")));
            	
            	solicitacoes.add(solicitacao);
				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
		
		return solicitacoes;
		
	}
	
	public static ArrayList<Solicitacao> listarManutencao(){
		
		String sql = "SELECT * FROM solicitacao where idSolicitante = 27";
		ArrayList<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			Solicitacao solicitacao = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			while(rs.next()){
				
				solicitacao = new Solicitacao();
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setQtdFornecida(rs.getInt("qtdFornecida"));
				Calendar calendar = Calendar.getInstance();
				String dataHoraString = rs.getString("dataSolicitacao");
				Date dataHora = format.parse(dataHoraString);
				calendar.setTime(dataHora);
				solicitacao.setData(calendar);
            	solicitacao.setSolicitante(SolicitanteDao.listarPorId(rs.getInt("idSolicitante")));
            	solicitacao.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	solicitacao.setUsuario(UsuarioDao.listarPorId(rs.getInt("idUsuario")));
            	
            	solicitacoes.add(solicitacao);
				
			}
			
            stmt.close();
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
		
		return solicitacoes;
		
	}
	
	public static ArrayList<Solicitacao> listarPorAno(int ano){
		
		String sql = "SELECT * FROM solicitacao where YEAR(dataSolicitacao) = '"+ String.valueOf(ano)+"'";
		ArrayList<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			Solicitacao solicitacao = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			while(rs.next()){
				
				solicitacao = new Solicitacao();
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setQtdFornecida(rs.getInt("qtdFornecida"));
				Calendar calendar = Calendar.getInstance();
				String dataHoraString = rs.getString("dataSolicitacao");
				Date dataHora = format.parse(dataHoraString);
				calendar.setTime(dataHora);
				solicitacao.setData(calendar);
            	solicitacao.setSolicitante(SolicitanteDao.listarPorId(rs.getInt("idSolicitante")));
            	solicitacao.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	solicitacao.setUsuario(UsuarioDao.listarPorId(rs.getInt("idUsuario")));
            	
            	solicitacoes.add(solicitacao);
				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
		
		return solicitacoes;
		
	}
	
	public static ArrayList<Solicitacao> listarPorMaterial(Material m){
		
		String sql = "SELECT * FROM solicitacao where idMaterial = "+ m.getIdMaterial();
		ArrayList<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Solicitacao solicitacao = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			
			while(rs.next()){
								
				solicitacao = new Solicitacao();
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setQtdFornecida(rs.getInt("qtdFornecida"));
				Calendar calendar = Calendar.getInstance();
				String dataHoraString = rs.getString("dataSolicitacao");
				Date dataHora = format.parse(dataHoraString);
				calendar.setTime(dataHora);
				solicitacao.setData(calendar);
            	solicitacao.setSolicitante(SolicitanteDao.listarPorId(rs.getInt("idSolicitante")));
            	solicitacao.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	solicitacao.setUsuario(UsuarioDao.listarPorId(rs.getInt("idUsuario")));
            	
            	solicitacoes.add(solicitacao);
            				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		return solicitacoes;
		
	}
	
	public static ArrayList<Solicitacao> listarPorMaterial(Material m, int mes, int ano){
		
		String sql = "SELECT * FROM solicitacao where idMaterial = "+ m.getIdMaterial() +" AND MONTH(dataSolicitacao) = '"+ String.valueOf(mes) +"' AND YEAR(dataSolicitacao) = '"+ String.valueOf(ano) +"'";
		ArrayList<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Solicitacao solicitacao = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			
			while(rs.next()){
								
				solicitacao = new Solicitacao();
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setQtdFornecida(rs.getInt("qtdFornecida"));
				Calendar calendar = Calendar.getInstance();
				String dataHoraString = rs.getString("dataSolicitacao");
				Date dataHora = format.parse(dataHoraString);
				calendar.setTime(dataHora);
				solicitacao.setData(calendar);
            	solicitacao.setSolicitante(SolicitanteDao.listarPorId(rs.getInt("idSolicitante")));
            	solicitacao.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	solicitacao.setUsuario(UsuarioDao.listarPorId(rs.getInt("idUsuario")));
            	
            	solicitacoes.add(solicitacao);
            				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		return solicitacoes;
		
	}
	
	public static ArrayList<Solicitacao> listarPorMaterial(Material m, int ano){
		
		String sql = "SELECT * FROM solicitacao where idMaterial = "+ m.getIdMaterial() +"' AND YEAR(dataSolicitacao) = '"+ String.valueOf(ano) +"'";
		ArrayList<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Solicitacao solicitacao = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			
			while(rs.next()){
								
				solicitacao = new Solicitacao();
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setQtdFornecida(rs.getInt("qtdFornecida"));
				Calendar calendar = Calendar.getInstance();
				String dataHoraString = rs.getString("dataSolicitacao");
				Date dataHora = format.parse(dataHoraString);
				calendar.setTime(dataHora);
				solicitacao.setData(calendar);
            	solicitacao.setSolicitante(SolicitanteDao.listarPorId(rs.getInt("idSolicitante")));
            	solicitacao.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	solicitacao.setUsuario(UsuarioDao.listarPorId(rs.getInt("idUsuario")));
            	
            	solicitacoes.add(solicitacao);
            				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		return solicitacoes;
		
	}
	
	
	public static Solicitacao listarPorId(int idSolicitacao){
		
		String sql = "SELECT * FROM solicitacao WHERE idSolicitacao = " + idSolicitacao;
		Solicitacao solicitacao = null;
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);  
            ResultSet rs = stmt.executeQuery();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            while(rs.next()){
            	
            	solicitacao = new Solicitacao();
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setQtdFornecida(rs.getInt("qtdFornecida"));
				Calendar calendar = Calendar.getInstance();
				String dataHoraString = rs.getString("dataSolicitacao");
				Date dataHora = format.parse(dataHoraString);
				calendar.setTime(dataHora);
				solicitacao.setData(calendar);
            	solicitacao.setSolicitante(SolicitanteDao.listarPorId(rs.getInt("idSolicitante")));
            	solicitacao.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	solicitacao.setUsuario(UsuarioDao.listarPorId(rs.getInt("idUsuario")));
            	
            	           	
            }            
                       
            stmt.close();
	        
		} 
		
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		}
		
		return solicitacao;
		
	}
	
	public static ArrayList<Solicitacao> listarPorSolicitanteMaterial(Material m, Solicitante s){
		
		String sql = "SELECT * FROM solicitacao where idMaterial = "+ m.getIdMaterial() +" AND idSolicitante = "+ s.getIdSolicitante();
		ArrayList<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Solicitacao solicitacao = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			
			while(rs.next()){
								
				solicitacao = new Solicitacao();
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setQtdFornecida(rs.getInt("qtdFornecida"));
				Calendar calendar = Calendar.getInstance();
				String dataHoraString = rs.getString("dataSolicitacao");
				Date dataHora = format.parse(dataHoraString);
				calendar.setTime(dataHora);
				solicitacao.setData(calendar);
            	solicitacao.setSolicitante(SolicitanteDao.listarPorId(rs.getInt("idSolicitante")));
            	solicitacao.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	solicitacao.setUsuario(UsuarioDao.listarPorId(rs.getInt("idUsuario")));
            	
            	solicitacoes.add(solicitacao);
            				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		return solicitacoes;
		
	}
	
	public static ArrayList<Solicitacao> listarPorSolicitanteMaterial(Material m, Solicitante s, int mes, int ano){
		
		String sql = "SELECT * FROM solicitacao where idMaterial = "+ m.getIdMaterial() +" AND idSolicitante = "+ s.getIdSolicitante() +" AND MONTH(dataSolicitacao) = '"+ String.valueOf(mes) +"' AND YEAR(dataSolicitacao) = '"+ String.valueOf(ano) +"'";
		ArrayList<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Solicitacao solicitacao = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			
			while(rs.next()){
				
				solicitacao = new Solicitacao();
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setQtdFornecida(rs.getInt("qtdFornecida"));
				Calendar calendar = Calendar.getInstance();
				String dataHoraString = rs.getString("dataSolicitacao");
				Date dataHora = format.parse(dataHoraString);
				calendar.setTime(dataHora);
				solicitacao.setData(calendar);
            	solicitacao.setSolicitante(SolicitanteDao.listarPorId(rs.getInt("idSolicitante")));
            	solicitacao.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	solicitacao.setUsuario(UsuarioDao.listarPorId(rs.getInt("idUsuario")));
            	
            	solicitacoes.add(solicitacao);
				            				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		return solicitacoes;
		
	}
	
	public static ArrayList<Solicitacao> listarPorSolicitante(Solicitante s){
		
		String sql = "SELECT * FROM solicitacao where idSolicitante = "+ s.getIdSolicitante();
		ArrayList<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Solicitacao solicitacao = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			
			while(rs.next()){
								
				solicitacao = new Solicitacao();
				solicitacao.setIdSolicitacao(rs.getInt("idSolicitacao"));
				solicitacao.setQtdFornecida(rs.getInt("qtdFornecida"));
				Calendar calendar = Calendar.getInstance();
				String dataHoraString = rs.getString("dataSolicitacao");
				Date dataHora = format.parse(dataHoraString);
				calendar.setTime(dataHora);
				solicitacao.setData(calendar);
            	solicitacao.setSolicitante(SolicitanteDao.listarPorId(rs.getInt("idSolicitante")));
            	solicitacao.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	solicitacao.setUsuario(UsuarioDao.listarPorId(rs.getInt("idUsuario")));
            	
            	solicitacoes.add(solicitacao);
            				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		return solicitacoes;
		
	}
	
	
		
}
