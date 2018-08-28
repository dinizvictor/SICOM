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

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.cefet.sicom.modelo.Usuario;


public class MovimentacaoDao {
/*	
			
		public static void cadastrar(Movimentacao m){
			
			String sql = "INSERT INTO movimentacao(tipoMovimentacao,descricaoEntidade,data,idUsuario) VALUES (?,?,?,?)";
			String data = m.getData().get(Calendar.YEAR) +"-"+ (m.getData().get(Calendar.MONTH) + 1) +"-"+ m.getData().get(Calendar.DAY_OF_MONTH) +" "+
				      m.getData().get(Calendar.HOUR_OF_DAY) +":"+ m.getData().get(Calendar.MINUTE) +":"+ m.getData().get(Calendar.SECOND);
			try {
				
				Connection c = Conexao.abrir();
				PreparedStatement stmt = c.prepareStatement(sql);  
	            
				stmt.setString(1, m.getTipoDeMovimentacao().toString().toUpperCase());
				stmt.setString(2, m.getDescricaoEntidade());
	            stmt.setString(3, data); 
	            stmt.setInt(4, m.getUsuario().getIdUsuario()); 
	            
	            stmt.execute();  
	            stmt.close();  
		        
		        
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.toString());
			}
			
		}
		
		public static void excluirPorId(int idMovimentacao){
			
			String sql = "DELETE FROM movimentacao WHERE idMovimentacao = ?";
			try {
				
				Connection c = Conexao.abrir();
				PreparedStatement stmt = c.prepareStatement(sql);  
				stmt.setInt(1, idMovimentacao);
	            stmt.execute();                      
	            stmt.close();
		        
			} 
			
			catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.toString());
			}
			
		}
		
		public static void atualizar(Movimentacao m){
			
			String data = m.getData().get(Calendar.YEAR) +"-"+ (m.getData().get(Calendar.MONTH) + 1) +"-"+ m.getData().get(Calendar.DAY_OF_MONTH) +" "+
				      m.getData().get(Calendar.HOUR_OF_DAY) +":"+ m.getData().get(Calendar.MINUTE) +":"+ m.getData().get(Calendar.SECOND);
			String sql = "UPDATE movimentacao SET tipoMovimentacao = ?, idEntidadeMovimentada = ?, data = ?, idUsuario = ?  WHERE idMovimentacao = " + m.getIdMovimentacao();
			try {
				
				Connection c = Conexao.abrir();
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1, m.getTipoDeMovimentacao().toString().toUpperCase());
	            stmt.setString(3, data); 
	            stmt.setInt(4, m.getUsuario().getIdUsuario()); 
	            stmt.execute();                      
	            stmt.close();
		        
			} 
			
			
			catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.toString());
			}
			
		}
		
		public static Movimentacao listarPorId(int idMovimentacao){
			
			String sql = "SELECT * FROM movimentacao WHERE idMovimentacao = " + idMovimentacao;
			Movimentacao m = null;
			try {
				
				Connection c = Conexao.abrir();
				PreparedStatement stmt = c.prepareStatement(sql);  
	            ResultSet rs = stmt.executeQuery();
	            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            
	            while(rs.next()){
	            	
	            	m = new Movimentacao();
	            	
		            m.setIdMovimentacao(rs.getInt("idMovimentacao"));
	            	m.setTipoDeMovimentacao(TipoDeMovimentacao.valueOf(rs.getString("tipoMovimentacao")));
	            		            	
	            	Calendar calendar = Calendar.getInstance();
					String dataHoraString = rs.getString("data");
					Date dataHora = format.parse(dataHoraString);
					calendar.setTime(dataHora);
					m.setData(calendar);
					
					Usuario u = UsuarioDao.listarPorId(rs.getInt("idUsuario"));
	            	m.setUsuario(u);
	            		
	            	
	            }            
	                       
	            stmt.close();
		        
			} 
			
			
			catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.toString());
			}
			
			return m;
			
		}
		
		public static ArrayList<Movimentacao> listarTodos(){
			
			String sql = "SELECT * FROM movimentacao ORDER BY data DESC";
			ArrayList<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				
				Connection c = Conexao.abrir();
				PreparedStatement stmt = c.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
	            
				Movimentacao m = null;
				
				while(rs.next()){
					
					m = new Movimentacao();
	            	
		            m.setIdMovimentacao(rs.getInt("idMovimentacao"));
	            	m.setTipoDeMovimentacao(TipoDeMovimentacao.valueOf(rs.getString("tipoMovimentacao")));
	            		            	
	            	Calendar calendar = Calendar.getInstance();
					String dataHoraString = rs.getString("data");
					Date dataHora = format.parse(dataHoraString);
					calendar.setTime(dataHora);
					m.setData(calendar);
					
					Usuario u = UsuarioDao.listarPorId(rs.getInt("idUsuario"));
	            	m.setUsuario(u);
	            	
	            	movimentacoes.add(m);
					
				}
				
	            stmt.close();  
		        
		        
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.toString());
			}
			
			return movimentacoes;
			
		}
		
		
		public static ArrayList<Movimentacao> listarPorUsuario(Usuario usuario){
			
			String sql = "SELECT * FROM movimentacao WHERE idUsuario = ? ORDER BY data DESC";
			ArrayList<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				
				Connection c = Conexao.abrir();
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setInt(1, usuario.getIdUsuario());
				ResultSet rs = stmt.executeQuery();
	            
				Movimentacao m = null;
				
				while(rs.next()){
					
					m = new Movimentacao();
	            	
		            m.setIdMovimentacao(rs.getInt("idMovimentacao"));
	            	m.setTipoDeMovimentacao(TipoDeMovimentacao.valueOf(rs.getString("tipoMovimentacao")));
	            		            	
	            	Calendar calendar = Calendar.getInstance();
					String dataHoraString = rs.getString("data");
					Date dataHora = format.parse(dataHoraString);
					calendar.setTime(dataHora);
					m.setData(calendar);
					
					Usuario u = UsuarioDao.listarPorId(rs.getInt("idUsuario"));
	            	m.setUsuario(u);
	            	
	            	movimentacoes.add(m);
					
				}
				
	            stmt.close();  
		        
		        
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.toString());
			}
			
			return movimentacoes;
			
		}
		
		public static ArrayList<Movimentacao> listarPorTipoDeMovimentacao(TipoDeMovimentacao tm){
			
			String sql = "SELECT * FROM movimentacao WHERE tipoMovimentacao LIKE ? ORDER BY data DESC";
			ArrayList<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				
				Connection c = Conexao.abrir();
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1, tm.toString());
				ResultSet rs = stmt.executeQuery();
	            
				Movimentacao m = null;
				
				while(rs.next()){
					
					m = new Movimentacao();
	            	
		            m.setIdMovimentacao(rs.getInt("idMovimentacao"));
	            	m.setTipoDeMovimentacao(TipoDeMovimentacao.valueOf(rs.getString("tipoMovimentacao")));
	            		            	
	            	Calendar calendar = Calendar.getInstance();
					String dataHoraString = rs.getString("data");
					Date dataHora = format.parse(dataHoraString);
					calendar.setTime(dataHora);
					m.setData(calendar);
					
					Usuario u = UsuarioDao.listarPorId(rs.getInt("idUsuario"));
	            	m.setUsuario(u);
	            	
	            	movimentacoes.add(m);
					
				}
				
	            stmt.close();  
		        
		        
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.toString());
			}
			
			return movimentacoes;
			
		}
		
		public static ArrayList<Movimentacao> listarPorTM_Usuario(TipoDeMovimentacao tm, Usuario usu){
			
			String sql = "SELECT * FROM movimentacao WHERE tipoMovimentacao LIKE ? AND idUsuario = ? ORDER BY data DESC";
			ArrayList<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				
				Connection c = Conexao.abrir();
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1, tm.toString());
				stmt.setInt(2, usu.getIdUsuario());
				ResultSet rs = stmt.executeQuery();
	            
				Movimentacao m = null;
				
				while(rs.next()){
					
					m = new Movimentacao();
	            	
		            m.setIdMovimentacao(rs.getInt("idMovimentacao"));
	            	m.setTipoDeMovimentacao(TipoDeMovimentacao.valueOf(rs.getString("tipoMovimentacao")));
	            		            	
	            	Calendar calendar = Calendar.getInstance();
					String dataHoraString = rs.getString("data");
					Date dataHora = format.parse(dataHoraString);
					calendar.setTime(dataHora);
					m.setData(calendar);
					
					Usuario u = UsuarioDao.listarPorId(rs.getInt("idUsuario"));
	            	m.setUsuario(u);
	            	
	            	movimentacoes.add(m);
					
				}
				
	            stmt.close();  
		        
		        
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.toString());
			}
			
			return movimentacoes;
			
		}
		
		public static ArrayList<Movimentacao> listarPorTM_Usuario_Data(TipoDeMovimentacao tm, Usuario usu, Calendar cal){
			
			String sql = "SELECT * FROM movimentacao WHERE tipoMovimentacao LIKE ? AND idUsuario = ? AND DAY(data) = ? AND MONTH(data) = ? AND YEAR(data) = ? ORDER BY data DESC";
			ArrayList<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				
				Connection c = Conexao.abrir();
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1, tm.toString());
				stmt.setInt(2, usu.getIdUsuario());
				stmt.setInt(3, cal.get(Calendar.DAY_OF_MONTH));
				stmt.setInt(4, cal.get(Calendar.MONTH)+1);
				stmt.setInt(5, cal.get(Calendar.YEAR));
				ResultSet rs = stmt.executeQuery();
	            
				Movimentacao m = null;
				
				while(rs.next()){
					
					m = new Movimentacao();
	            	
		            m.setIdMovimentacao(rs.getInt("idMovimentacao"));
	            	m.setTipoDeMovimentacao(TipoDeMovimentacao.valueOf(rs.getString("tipoMovimentacao")));
	            		            	
	            	Calendar calendar = Calendar.getInstance();
					String dataHoraString = rs.getString("data");
					Date dataHora = format.parse(dataHoraString);
					calendar.setTime(dataHora);
					m.setData(calendar);
					
					Usuario u = UsuarioDao.listarPorId(rs.getInt("idUsuario"));
	            	m.setUsuario(u);
	            	
	            	movimentacoes.add(m);
					
				}
				
	            stmt.close();  
		        
		        
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.toString());
			}
			
			return movimentacoes;
			
		}
		
		public static ArrayList<Movimentacao> listarPorData(Calendar cal){
			
			String sql = "SELECT * FROM movimentacao WHERE DAY(data) = ? AND MONTH(data) = ? AND YEAR(data) = ? ORDER BY data DESC";
			ArrayList<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				
				Connection c = Conexao.abrir();
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setInt(1, cal.get(Calendar.DAY_OF_MONTH));
				stmt.setInt(2, cal.get(Calendar.MONTH)+1);
				stmt.setInt(3, cal.get(Calendar.YEAR));
				ResultSet rs = stmt.executeQuery();
	            
				Movimentacao m = null;
				
				while(rs.next()){
					
					m = new Movimentacao();
	            	
		            m.setIdMovimentacao(rs.getInt("idMovimentacao"));
	            	m.setTipoDeMovimentacao(TipoDeMovimentacao.valueOf(rs.getString("tipoMovimentacao")));
	            		            	
	            	Calendar calendar = Calendar.getInstance();
					String dataHoraString = rs.getString("data");
					Date dataHora = format.parse(dataHoraString);
					calendar.setTime(dataHora);
					m.setData(calendar);
					
					Usuario u = UsuarioDao.listarPorId(rs.getInt("idUsuario"));
	            	m.setUsuario(u);
	            	
	            	movimentacoes.add(m);
					
				}
				
	            stmt.close();  
		        
		        
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.toString());
			}
			
			return movimentacoes;
			
		}
		
		public static ArrayList<Movimentacao> listarPorUsuario_Data(Usuario usu, Calendar cal){
			
			String sql = "SELECT * FROM movimentacao WHERE idUsuario = ? AND DAY(data) = ? AND MONTH(data) = ? AND YEAR(data) = ? ORDER BY data DESC";
			ArrayList<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				
				Connection c = Conexao.abrir();
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setInt(1, usu.getIdUsuario());
				stmt.setInt(2, cal.get(Calendar.DAY_OF_MONTH));
				stmt.setInt(3, cal.get(Calendar.MONTH)+1);
				stmt.setInt(4, cal.get(Calendar.YEAR));
				ResultSet rs = stmt.executeQuery();
	            
				Movimentacao m = null;
				
				while(rs.next()){
					
					m = new Movimentacao();
	            	
		            m.setIdMovimentacao(rs.getInt("idMovimentacao"));
	            	m.setTipoDeMovimentacao(TipoDeMovimentacao.valueOf(rs.getString("tipoMovimentacao")));
	            		            	
	            	Calendar calendar = Calendar.getInstance();
					String dataHoraString = rs.getString("data");
					Date dataHora = format.parse(dataHoraString);
					calendar.setTime(dataHora);
					m.setData(calendar);
					
					Usuario u = UsuarioDao.listarPorId(rs.getInt("idUsuario"));
	            	m.setUsuario(u);
	            	
	            	movimentacoes.add(m);
					
				}
				
	            stmt.close();  
		        
		        
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.toString());
			}
			
			return movimentacoes;
			
		}
		
		public static ArrayList<Movimentacao> listarPorTM_Data(TipoDeMovimentacao tm, Calendar cal){
			
			String sql = "SELECT * FROM movimentacao WHERE tipoMovimentacao LIKE ? AND DAY(data) = ? AND MONTH(data) = ? AND YEAR(data) = ? ORDER BY data DESC";
			ArrayList<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				
				Connection c = Conexao.abrir();
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1, tm.toString());
				stmt.setInt(2, cal.get(Calendar.DAY_OF_MONTH));
				stmt.setInt(3, cal.get(Calendar.MONTH)+1);
				stmt.setInt(4, cal.get(Calendar.YEAR));
				ResultSet rs = stmt.executeQuery();
	            
				Movimentacao m = null;
				
				while(rs.next()){
					
					m = new Movimentacao();
	            	
		            m.setIdMovimentacao(rs.getInt("idMovimentacao"));
	            	m.setTipoDeMovimentacao(TipoDeMovimentacao.valueOf(rs.getString("tipoMovimentacao")));
	            		            	
	            	Calendar calendar = Calendar.getInstance();
					String dataHoraString = rs.getString("data");
					Date dataHora = format.parse(dataHoraString);
					calendar.setTime(dataHora);
					m.setData(calendar);
					
					Usuario u = UsuarioDao.listarPorId(rs.getInt("idUsuario"));
	            	m.setUsuario(u);
	            	
	            	movimentacoes.add(m);
					
				}
				
	            stmt.close();  
		        
		        
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.toString());
			}
			
			return movimentacoes;
			
		}
		
*/
}

