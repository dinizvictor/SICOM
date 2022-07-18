package br.cefet.sicom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.cefet.sicom.modelo.Entrada;
import br.cefet.sicom.modelo.Material;

public class EntradaDao {
	
public static void cadastrar(Entrada entrada){
		
		String sql = "INSERT INTO entrada(idMaterial,qtd,empenho,fornecedor,dataEntrada) VALUES (?,?,?,?,?)";
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, entrada.getMaterial().getIdMaterial());  
            stmt.setInt(2, entrada.getQtd());  
            stmt.setString(3, entrada.getEmpenho().toUpperCase());
            stmt.setString(4, entrada.getFornecedor().toUpperCase());
            stmt.setString(5, entrada.getData().get(Calendar.YEAR)+"-"+(entrada.getData().get(Calendar.MONTH) + 1)+"-"+entrada.getData().get(Calendar.DAY_OF_MONTH));
            stmt.execute();  
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	public static void excluirPorId(int idEntrada){
		
		String sql = "DELETE FROM entrada WHERE idEntrada = ?";
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);  
			stmt.setInt(1, idEntrada);
            stmt.execute();                      
            stmt.close();
	        
		} 
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	public static void atualizar(Entrada entrada){
		
		String sql = "UPDATE entrada SET qtd = ?, empenho = ?, fornecedor = ?, data = ?  WHERE idMaterial = " + entrada.getIdEntrada();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, entrada.getQtd());  
            stmt.setString(2, entrada.getEmpenho());
            stmt.setString(3, entrada.getFornecedor());
            stmt.setString(4, entrada.getData().get(Calendar.YEAR)+"-"+ (entrada.getData().get(Calendar.MONTH) + 1) +"-"+entrada.getData().get(Calendar.DAY_OF_MONTH));
            stmt.execute();                      
            stmt.close();
	        
		} 
		
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
	}
	
	public static Entrada listarPorId(int idEntrada){
		
		String sql = "SELECT * FROM entrada WHERE idEntrada = ?";
		Entrada entrada = null;
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, idEntrada);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            	
            	entrada = new Entrada();
            	Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("data"));
				entrada.setData(calendar);
            	entrada.setEmpenho(rs.getString("empenho"));
            	entrada.setFornecedor(rs.getString("fornecedor"));
            	entrada.setQtd(rs.getInt("qtd"));            	
            	entrada.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	            	           	
            }            
                       
            stmt.close();
	        
		} 
		
		
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return entrada;
		
	}
	
	public static ArrayList<Entrada> listarTodos(){
		
		String sql = "SELECT * FROM entrada";
		ArrayList<Entrada> entradas = new ArrayList<Entrada>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			Entrada entrada = null;
			
			while(rs.next()){
				
				entrada = new Entrada();
				entrada.setIdEntrada(rs.getInt("idEntrada"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("data"));
				entrada.setData(calendar);
				entrada.setEmpenho(rs.getString("empenho"));
            	entrada.setFornecedor(rs.getString("fornecedor"));
            	entrada.setQtd(rs.getInt("qtd"));            	
            	entrada.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	
            	entradas.add(entrada);
				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();

		}
		
		return entradas;
		
	}
	
	public static ArrayList<Entrada> listarPorEmpenhoSubString(Material m, String empenho){
		
		String sql = "SELECT * FROM entrada where idMaterial = "+ m.getIdMaterial() +" AND empenho like '%" + empenho + "%'";
		ArrayList<Entrada> entradas = new ArrayList<Entrada>(); 
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Entrada entrada = null;
			
			while(rs.next()){
				
				entrada = new Entrada();
				
				entrada.setIdEntrada(rs.getInt("idEntrada"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("data"));
				entrada.setData(calendar);
				entrada.setEmpenho(rs.getString("empenho"));
            	entrada.setFornecedor(rs.getString("fornecedor"));
            	entrada.setQtd(rs.getInt("qtd"));            	
            	entrada.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	
            	entradas.add(entrada);
            				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
		}
		
		return entradas;
		
	}
	
	public static ArrayList<Entrada> listarPorFornecedorSubString(Material m, String fornecedor){
		
		String sql = "SELECT * FROM entrada where idMaterial = "+ m.getIdMaterial() +" AND fornecedor like '%"+fornecedor+"%'";
		ArrayList<Entrada> entradas = new ArrayList<Entrada>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Entrada entrada = null;
			
			while(rs.next()){
				
				entrada = new Entrada();
				
				entrada.setIdEntrada(rs.getInt("idEntrada"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("data"));
				entrada.setData(calendar);
				entrada.setEmpenho(rs.getString("empenho"));
            	entrada.setFornecedor(rs.getString("fornecedor"));
            	entrada.setQtd(rs.getInt("qtd"));            	
            	entrada.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	
            	entradas.add(entrada);
            				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
		}
		
		return entradas;
		
	}
	
	public static ArrayList<Entrada> listarPorFornecedorSubString(Material m, String fornecedor, int mes, int ano){
		
		String sql = "SELECT * FROM entrada where idMaterial = "+ m.getIdMaterial() +" AND fornecedor like '%"+fornecedor+"%' AND MONTH(data) = '"+ String.valueOf(mes) +"' AND YEAR(data) = '"+ String.valueOf(ano) +"'";
		ArrayList<Entrada> entradas = new ArrayList<Entrada>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Entrada entrada = null;
			
			while(rs.next()){
				
				entrada = new Entrada();
				
				entrada.setIdEntrada(rs.getInt("idEntrada"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("data"));
				entrada.setData(calendar);
				entrada.setEmpenho(rs.getString("empenho"));
            	entrada.setFornecedor(rs.getString("fornecedor"));
            	entrada.setQtd(rs.getInt("qtd"));            	
            	entrada.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	
            	entradas.add(entrada);
            				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
		}
		
		return entradas;
		
	}
	
	public static ArrayList<Entrada> listarPorEmpenhoSubString(Material m, String empenho, int mes, int ano){
		
		String sql = "SELECT * FROM entrada WHERE idMaterial = "+ m.getIdMaterial() +" AND empenho like '%"+empenho+"%' AND MONTH(data) = '"+ String.valueOf(mes) +"' AND YEAR(data) = '"+ String.valueOf(ano) +"'";
		ArrayList<Entrada> entradas = new ArrayList<Entrada>();
		try {
			
			Connection c = Conexao.abrir();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Entrada entrada = null;
			
			while(rs.next()){
				
				entrada = new Entrada();
				
				entrada.setIdEntrada(rs.getInt("idEntrada"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("data"));
				entrada.setData(calendar);
				entrada.setEmpenho(rs.getString("empenho"));
            	entrada.setFornecedor(rs.getString("fornecedor"));
            	entrada.setQtd(rs.getInt("qtd"));            	
            	entrada.setMaterial(MaterialDao.listarPorId(rs.getInt("idMaterial")));
            	
            	entradas.add(entrada);
            				
			}
			
            stmt.close();  
	        
	        
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
		}
		
		return entradas;
		
	}

}
