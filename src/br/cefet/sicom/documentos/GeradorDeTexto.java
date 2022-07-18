package br.cefet.sicom.documentos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystemAlreadyExistsException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

import br.cefet.sicom.dao.MaterialDao;
import br.cefet.sicom.dao.SolicitacaoDao;
import br.cefet.sicom.modelo.Material;
import br.cefet.sicom.modelo.Solicitacao;
import br.cefet.sicom.modelo.Solicitante;
import br.cefet.sicom.modelo.Usuario;


public class GeradorDeTexto {
	
	public static String getEndereco(Solicitante s, Calendar calendar){
		
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int seg = calendar.get(Calendar.SECOND);
		
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		
		FileSystemView system = FileSystemView.getFileSystemView();
		
		File dir = new File(system.getHomeDirectory().getPath() + File.separator + "Comprovantes");
		if(!dir.exists()){
			dir.mkdir();
		}
		
		
        String path = dir.getPath() + File.separator + s.getNome() + " ("+ formatDate.format(calendar.getTime()) + " [" + hora +"]["+ min +"]["+ seg +"]).pdf";
					
		return path;
	}
	
	public static String getEnderecoRelatorio(String nomeRelatorio, Calendar calendar){
		
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int seg = calendar.get(Calendar.SECOND);
		
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		
		FileSystemView system = FileSystemView.getFileSystemView();
		
		File dir = new File(system.getHomeDirectory().getPath() + File.separator + "Relatorios");
		if(!dir.exists()){
			dir.mkdir();
		}
		
		
        String path = dir.getPath() + File.separator + nomeRelatorio + " ("+ formatDate.format(calendar.getTime()) + " [" + hora +"]["+ min +"]["+ seg +"]).pdf";
					
		return path;
	}
	
	public static String obterNomeMes(int mes){
	    String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", 
	                      "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
	    return meses[mes-1];
	}
	
	public static void gerarComprovanteSolicitacao(ArrayList<Solicitacao> solicitacao, Solicitante solicitante, Calendar calendar, Usuario u){
			
		Document doc = null;
		FileOutputStream os = null;
		
		
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
				
		try {
			
			doc = new Document(PageSize.A4, 30, 30, 30, 30);
			os = new FileOutputStream(getEndereco(solicitante, calendar));
							
			PdfWriter.getInstance(doc, os);
			doc.open();
			
			Font f1 = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
			Font f2 = new Font(FontFamily.HELVETICA, 14);
			Font f3 = new Font(FontFamily.HELVETICA, 10);
			
			Paragraph p1 = new Paragraph("CEFET-RJ\nCampus Nova Iguaçu\nAlmoxarifado\n", f1);
			p1.setAlignment(Element.ALIGN_CENTER);
			
			Image logo = Image.getInstance(GeradorDeTexto.class.getResource("/br/cefet/sepat/img/logo.png"));
			logo.setAlignment(Element.ALIGN_CENTER);
			logo.scalePercent(50f);
			
			Paragraph p2 = new Paragraph("Comprovante de Liberação de Material\n", f2);
			p2.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph p3 = new Paragraph("Data: " + formatDate.format(calendar.getTime()) + "\nHora: " + calendar.get(Calendar.HOUR_OF_DAY) +":"+ calendar.get(Calendar.MINUTE) +":"+ calendar.get(Calendar.SECOND) + "\n\n", f2);
			p3.setAlignment(Element.ALIGN_CENTER);
			
			PdfPTable table = new PdfPTable(4);
			
			table.addCell(new Phrase("ID",f3));
			table.addCell(new Phrase("Material",f3));
			table.addCell(new Phrase("Qtd Fornecida",f3));
			table.addCell(new Phrase("Categoria",f3));
						
			for(Solicitacao s : solicitacao){
				
				table.addCell(String.valueOf(s.getMaterial().getIdMaterial()));
				table.addCell(s.getMaterial().getDescricao());
				table.addCell(String.valueOf(s.getQtdFornecida()));
				table.addCell(s.getMaterial().getCategoria());
				
			}
			
			Paragraph p4 = new Paragraph("\n\n\n_______________________________________\nSolicitante: "+ solicitante.getNome()+ " ("+ solicitante.getSiape() +")" +"\n\n_______________________________________\nAlmoxarife (Usuário): "+u.getNome()+ " ("+ u.getSiape() +")", f3);
			p4.setAlignment(Element.ALIGN_CENTER);
			
			doc.add(p1);
			doc.add(logo);
			doc.add(p2);
			doc.add(p3);
			doc.add(table);												
			doc.add(p4);
						
			doc.newPage();
			
			doc.add(p1);
			doc.add(logo);
			doc.add(p2);
			doc.add(p3);
			doc.add(table);												
			doc.add(p4);
			
									
		}	
		 catch (DocumentException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}
		 catch (IOException e){
			 
			JOptionPane.showMessageDialog(null, e.getMessage());
			 
		 }
		finally {
			
			if(doc != null)
				doc.close();
			
									
												
		}
				
	}
	
	
	
	public static void gerarRelatorioConsumoMensal(Calendar calendar, Usuario u, int mes, int ano){
		
		Document doc = null;
		FileOutputStream os = null;
		
		ArrayList<Solicitacao> solicitacoes = SolicitacaoDao.listarPorMes(mes, ano);
		
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
				
		try {
			
			doc = new Document(PageSize.A4, 30, 30, 30, 30);
			os = new FileOutputStream(getEnderecoRelatorio("RelatorioConsumoMensal", calendar));
							
			PdfWriter.getInstance(doc, os);
			doc.open();
			
			Font f1 = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
			Font f2 = new Font(FontFamily.HELVETICA, 14);
			Font f3 = new Font(FontFamily.HELVETICA, 8);
			
			Paragraph p1 = new Paragraph("CEFET-RJ\nCampus Nova Iguaçu\nAlmoxarifado\n", f2);
			p1.setAlignment(Element.ALIGN_CENTER);
			
			Image logo = Image.getInstance(GeradorDeTexto.class.getResource("/br/cefet/sepat/img/logo.png"));
			logo.setAlignment(Element.ALIGN_CENTER);
			logo.scalePercent(40f);
							
			Paragraph p2 = new Paragraph("Relatório de Consumo Mensal\n"+ obterNomeMes(mes) +"\n", f2);
			p2.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph p3 = new Paragraph("Data: " + formatDate.format(calendar.getTime()) + "\nHora: " + calendar.get(Calendar.HOUR_OF_DAY) +":"+ calendar.get(Calendar.MINUTE) +":"+ calendar.get(Calendar.SECOND) + "\n\n", f2);
			p3.setAlignment(Element.ALIGN_CENTER);
			
			PdfPTable table = new PdfPTable(8);
			
			table.addCell(new Phrase("ID Solicitação",f1));
			table.addCell(new Phrase("ID Material",f1));
			table.addCell(new Phrase("Material",f1));
			table.addCell(new Phrase("Qtd Fornecida",f1));
			table.addCell(new Phrase("Solicitante",f1));
			table.addCell(new Phrase("Siape",f1));
			table.addCell(new Phrase("Usuário",f1));
			table.addCell(new Phrase("Data",f1));
						
			for(Solicitacao s : solicitacoes){
				
				table.addCell(new Phrase(String.valueOf(s.getIdSolicitacao()),f3));
				table.addCell(new Phrase(String.valueOf(s.getMaterial().getIdMaterial()),f3));
				table.addCell(new Phrase(s.getMaterial().getDescricao(),f3));
				table.addCell(new Phrase(String.valueOf(s.getQtdFornecida()),f3));
				table.addCell(new Phrase(s.getSolicitante().getNome(),f3));
				table.addCell(new Phrase(String.valueOf(s.getSolicitante().getSiape()),f3));
				table.addCell(new Phrase(s.getUsuario().getNome(),f3));
				table.addCell(new Phrase(formatDate.format(s.getData().getTime()),f3));
				
			}
			
			Paragraph p4 = new Paragraph("\n\n\nGerado por: "+u.getNome()+ " ("+ u.getSiape() +")", f3);
			p4.setAlignment(Element.ALIGN_CENTER);
			
			doc.add(p1);
			doc.add(logo);
			doc.add(p2);
			doc.add(p3);
			doc.add(table);												
			doc.add(p4);
												
		}	
		 catch (DocumentException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}
		 catch (IOException e){
			 
			JOptionPane.showMessageDialog(null, e.getMessage());
			 
		 }
		finally {
			
			if(doc != null)
				doc.close();
			
									
												
		}
				
	}
	
	public static void gerarRelatorioElanConsumo(Calendar calendar, Usuario u, int mes, int ano){
		
		Document doc = null;
		FileOutputStream os = null;
		
		ArrayList<Solicitacao> solicitacoes = SolicitacaoDao.listarManutencao();
		
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
				
		try {
			
			doc = new Document(PageSize.A4, 30, 30, 30, 30);
			os = new FileOutputStream(getEnderecoRelatorio("RelatorioConsumoMensal", calendar));
							
			PdfWriter.getInstance(doc, os);
			doc.open();
			
			Font f1 = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
			Font f2 = new Font(FontFamily.HELVETICA, 14);
			Font f3 = new Font(FontFamily.HELVETICA, 8);
			
			Paragraph p1 = new Paragraph("CEFET-RJ\nCampus Nova Iguaçu\nAlmoxarifado\n", f2);
			p1.setAlignment(Element.ALIGN_CENTER);
			
			Image logo = Image.getInstance(GeradorDeTexto.class.getResource("/br/cefet/sepat/img/logo.png"));
			logo.setAlignment(Element.ALIGN_CENTER);
			logo.scalePercent(40f);
							
			Paragraph p2 = new Paragraph("Relatório de Consumo da Manutenção\n"+ obterNomeMes(mes) +"\n", f2);
			p2.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph p3 = new Paragraph("Data: " + formatDate.format(calendar.getTime()) + "\nHora: " + calendar.get(Calendar.HOUR_OF_DAY) +":"+ calendar.get(Calendar.MINUTE) +":"+ calendar.get(Calendar.SECOND) + "\n\n", f2);
			p3.setAlignment(Element.ALIGN_CENTER);
			
			PdfPTable table = new PdfPTable(8);
			
			table.addCell(new Phrase("ID Solicitação",f1));
			table.addCell(new Phrase("ID Material",f1));
			table.addCell(new Phrase("Material",f1));
			table.addCell(new Phrase("Qtd Fornecida",f1));
			table.addCell(new Phrase("Solicitante",f1));
			table.addCell(new Phrase("Siape",f1));
			table.addCell(new Phrase("Usuário",f1));
			table.addCell(new Phrase("Data",f1));
						
			for(Solicitacao s : solicitacoes){
				
				table.addCell(new Phrase(String.valueOf(s.getIdSolicitacao()),f3));
				table.addCell(new Phrase(String.valueOf(s.getMaterial().getIdMaterial()),f3));
				table.addCell(new Phrase(s.getMaterial().getDescricao(),f3));
				table.addCell(new Phrase(String.valueOf(s.getQtdFornecida()),f3));
				table.addCell(new Phrase(s.getSolicitante().getNome(),f3));
				table.addCell(new Phrase(String.valueOf(s.getSolicitante().getSiape()),f3));
				table.addCell(new Phrase(s.getUsuario().getNome(),f3));
				table.addCell(new Phrase(formatDate.format(s.getData().getTime()),f3));
				
			}
			
			Paragraph p4 = new Paragraph("\n\n\nGerado por: "+u.getNome()+ " ("+ u.getSiape() +")", f3);
			p4.setAlignment(Element.ALIGN_CENTER);
			
			doc.add(p1);
			doc.add(logo);
			doc.add(p2);
			doc.add(p3);
			doc.add(table);												
			doc.add(p4);
												
		}	
		 catch (DocumentException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}
		 catch (IOException e){
			 
			JOptionPane.showMessageDialog(null, e.getMessage());
			 
		 }
		finally {
			
			if(doc != null)
				doc.close();
			
									
												
		}
				
	}
	
	
	public static void gerarRelatorioConsumoAnual(Calendar calendar, Usuario u, int ano){
		
		Document doc = null;
		FileOutputStream os = null;
		
		ArrayList<Solicitacao> solicitacoes = SolicitacaoDao.listarPorAno(ano);
		
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
				
		try {
			
			doc = new Document(PageSize.A4, 30, 30, 30, 30);
			os = new FileOutputStream(getEnderecoRelatorio("RelatorioConsumoAnual", calendar));
							
			PdfWriter.getInstance(doc, os);
			doc.open();
			
			Font f1 = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
			Font f2 = new Font(FontFamily.HELVETICA, 14);
			Font f3 = new Font(FontFamily.HELVETICA, 8);
			
			Paragraph p1 = new Paragraph("CEFET-RJ\nCampus Nova Iguaçu\nAlmoxarifado\n", f2);
			p1.setAlignment(Element.ALIGN_CENTER);
			
			Image logo = Image.getInstance(GeradorDeTexto.class.getResource("/br/cefet/sepat/img/logo.png"));
			logo.setAlignment(Element.ALIGN_CENTER);
			logo.scalePercent(40f);
							
			Paragraph p2 = new Paragraph("Relatório de Consumo Anual\n"+ ano +"\n", f2);
			p2.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph p3 = new Paragraph("Data: " + formatDate.format(calendar.getTime()) + "\nHora: " + calendar.get(Calendar.HOUR_OF_DAY) +":"+ calendar.get(Calendar.MINUTE) +":"+ calendar.get(Calendar.SECOND) + "\n\n", f2);
			p3.setAlignment(Element.ALIGN_CENTER);
			
			PdfPTable table = new PdfPTable(8);
			
			table.addCell(new Phrase("ID Solicitação",f1));
			table.addCell(new Phrase("ID Material",f1));
			table.addCell(new Phrase("Material",f1));
			table.addCell(new Phrase("Qtd Fornecida",f1));
			table.addCell(new Phrase("Solicitante",f1));
			table.addCell(new Phrase("Siape",f1));
			table.addCell(new Phrase("Usuário",f1));
			table.addCell(new Phrase("Data",f1));
						
			for(Solicitacao s : solicitacoes){
				
				table.addCell(new Phrase(String.valueOf(s.getIdSolicitacao()),f3));
				table.addCell(new Phrase(String.valueOf(s.getMaterial().getIdMaterial()),f3));
				table.addCell(new Phrase(s.getMaterial().getDescricao(),f3));
				table.addCell(new Phrase(String.valueOf(s.getQtdFornecida()),f3));
				table.addCell(new Phrase(s.getSolicitante().getNome(),f3));
				table.addCell(new Phrase(String.valueOf(s.getSolicitante().getSiape()),f3));
				table.addCell(new Phrase(s.getUsuario().getNome(),f3));
				table.addCell(new Phrase(formatDate.format(s.getData().getTime()),f3));
				
			}
			
			Paragraph p4 = new Paragraph("\n\n\nGerado por: "+u.getNome()+ " ("+ u.getSiape() +")", f3);
			p4.setAlignment(Element.ALIGN_CENTER);
			
			doc.add(p1);
			doc.add(logo);
			doc.add(p2);
			doc.add(p3);
			doc.add(table);												
			doc.add(p4);
												
		}	
		 catch (DocumentException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}
		 catch (IOException e){
			 
			JOptionPane.showMessageDialog(null, e.getMessage());
			 
		 }
		finally {
			
			if(doc != null)
				doc.close();
			
									
												
		}
				
	}
	
	
	public static void gerarRelatorioMateriais(Calendar calendar, Usuario u){
		
		Document doc = null;
		FileOutputStream os = null;
		
		ArrayList<Material> materiais = MaterialDao.listarPorDescricaoSubString("", "descricao");
		
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
				
		try {
			
			doc = new Document(PageSize.A4, 30, 30, 30, 30);
			os = new FileOutputStream(getEnderecoRelatorio("RelatorioMateriais", calendar));
							
			PdfWriter.getInstance(doc, os);
			doc.open();
			
			Font f1 = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
			Font f2 = new Font(FontFamily.HELVETICA, 14);
			Font f3 = new Font(FontFamily.HELVETICA, 8);
			
			Paragraph p1 = new Paragraph("CEFET-RJ\nCampus Nova Iguaçu\nAlmoxarifado\n", f2);
			p1.setAlignment(Element.ALIGN_CENTER);
			
			Image logo = Image.getInstance(GeradorDeTexto.class.getResource("/br/cefet/sepat/img/logo.png"));
			logo.setAlignment(Element.ALIGN_CENTER);
			logo.scalePercent(50f);
			
			Paragraph p2 = new Paragraph("Relatório de Materiais Cadastrados\n", f2);
			p2.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph p3 = new Paragraph("\nData: " + formatDate.format(calendar.getTime()) + "\nHora: " + calendar.get(Calendar.HOUR_OF_DAY) +":"+ calendar.get(Calendar.MINUTE) +":"+ calendar.get(Calendar.SECOND) + "\n\n", f3);
			p3.setAlignment(Element.ALIGN_CENTER);
			
			PdfPTable table = new PdfPTable(6);
			
			table.addCell(new Phrase("ID",f1));
			table.addCell(new Phrase("Material",f1));
			table.addCell(new Phrase("Saldo",f1));
			table.addCell(new Phrase("Tipo de Unidade",f1));
			table.addCell(new Phrase("Categoria",f1));
			table.addCell(new Phrase("Localização",f1));
						
			for(Material m : materiais){
				
				table.addCell(new Phrase(String.valueOf(m.getIdMaterial()),f3));
				table.addCell(new Phrase(m.getDescricao(),f3));
				table.addCell(new Phrase(String.valueOf(m.getSaldo()),f3));
				table.addCell(new Phrase(m.getTipoUnid(),f3));
				table.addCell(new Phrase(m.getCategoria(),f3));
				table.addCell(new Phrase(m.getLocalizacao(),f3));
							
			}
			
			Paragraph p4 = new Paragraph("\n\n\nGerado por: "+u.getNome()+ " ("+ u.getSiape() +")", f3);
			p4.setAlignment(Element.ALIGN_CENTER);
			
			doc.add(p1);
			doc.add(logo);
			doc.add(p2);
			doc.add(p3);
			doc.add(table);												
			doc.add(p4);
												
		}	
		 catch (DocumentException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}
		 catch (IOException e){
			 
			JOptionPane.showMessageDialog(null, e.getMessage());
			 
		 }
		finally {
			
			if(doc != null)
				doc.close();
			
									
												
		}
				
	}
	


}
