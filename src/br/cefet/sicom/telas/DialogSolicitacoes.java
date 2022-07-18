package br.cefet.sicom.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.cefet.sicom.dao.EntradaDao;
import br.cefet.sicom.dao.SolicitacaoDao;
import br.cefet.sicom.dao.SolicitanteDao;
import br.cefet.sicom.dao.UsuarioDao;
import br.cefet.sicom.documentos.GeradorDeTexto;
import br.cefet.sicom.modelo.Material;
import br.cefet.sicom.modelo.Solicitacao;
import br.cefet.sicom.modelo.Solicitante;
import br.cefet.sicom.modelo.Usuario;
import br.cefet.sicom.sessao.Login;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class DialogSolicitacoes extends JDialog {

	private JPanel contentPane;
	private JTable tableSolicitacoes = new JTable();
	private JComboBox mesCB;
	private JComboBox anoCB;
	private JButton button;
	private JLabel materialLabel;
	ArrayList<Solicitacao> solicitacoes;
	private JComboBox nomeCB;
	
	public void refreshTable(Material m){
		
		if(mesCB.getSelectedIndex() != 0 && anoCB.getSelectedIndex() != 0){
			if(nomeCB.getSelectedIndex() == 0){
				
				solicitacoes = SolicitacaoDao.listarPorMaterial(m,mesCB.getSelectedIndex(), Integer.parseInt(anoCB.getSelectedItem().toString()));
				
			}
			else{

				solicitacoes = SolicitacaoDao.listarPorSolicitanteMaterial(m, (Solicitante) nomeCB.getSelectedItem(), mesCB.getSelectedIndex(), Integer.parseInt(anoCB.getSelectedItem().toString()));
			}
		}
		else{
			
			if(nomeCB.getSelectedIndex() == 0){
				
				solicitacoes = SolicitacaoDao.listarPorMaterial(m);
				
			}
			else{

				solicitacoes = SolicitacaoDao.listarPorSolicitanteMaterial(m, (Solicitante) nomeCB.getSelectedItem());
			}
			
		}
			
				
		String [] colunas = new String[] {"Id", "Data", "Qtd Fornecida", "Solicitante", "Usuário"};
				
		Object [][] objetos = new Object[solicitacoes.size()][5];
		for(int i = 0; i < solicitacoes.size(); i++){
			
			String data = solicitacoes.get(i).getData().get(Calendar.DAY_OF_MONTH)+ "/" +(solicitacoes.get(i).getData().get(Calendar.MONTH) + 1)+ "/" +solicitacoes.get(i).getData().get(Calendar.YEAR);
			
			objetos[i][0] = solicitacoes.get(i).getIdSolicitacao();
			objetos[i][1] = data;
			objetos[i][2] = solicitacoes.get(i).getQtdFornecida();
			objetos[i][3] = solicitacoes.get(i).getSolicitante().getNome();
			objetos[i][4] = solicitacoes.get(i).getUsuario().toString();
			
		}
						
		DefaultTableModel dtm = new DefaultTableModel(objetos, colunas){
			
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			
		};
		tableSolicitacoes.setModel(dtm);
		tableSolicitacoes.getColumnModel().getColumn(2).setPreferredWidth(103);
		tableSolicitacoes.getColumnModel().getColumn(3).setPreferredWidth(98);
		tableSolicitacoes.setForeground(new Color(0, 0, 128));
						
	}
	
	public boolean isDataEquals(Calendar c1, Calendar c2){
		
		if(c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH) &&
	       c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && c1.get(Calendar.HOUR) == c2.get(Calendar.HOUR) &&
	       c1.get(Calendar.MINUTE) == c2.get(Calendar.MINUTE) && c1.get(Calendar.SECOND) == c2.get(Calendar.SECOND)){
			
			return true;
			
		}
		else{
			
			return false;
			
		}
		
	}
	
	
	public DialogSolicitacoes(Material m, Login l) {
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		setTitle("SEPAT");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1110, 575);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSolicitaessadas = new JLabel("Solicitações (Saídas)");
		lblSolicitaessadas.setForeground(new Color(0, 0, 139));
		lblSolicitaessadas.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblSolicitaessadas.setBounds(10, 11, 738, 34);
		contentPane.add(lblSolicitaessadas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 128, 1074, 345);
		contentPane.add(scrollPane);
				
		JLabel label = new JLabel("Material:");
		label.setForeground(new Color(0, 0, 128));
		label.setBounds(10, 45, 56, 14);
		contentPane.add(label);
		
		materialLabel = new JLabel(m.getDescricao());
		materialLabel.setForeground(new Color(0, 0, 128));
		materialLabel.setBounds(76, 45, 708, 14);
		contentPane.add(materialLabel);
		
		JLabel label_3 = new JLabel("Período:");
		label_3.setForeground(new Color(0, 0, 128));
		label_3.setBounds(10, 67, 62, 14);
		contentPane.add(label_3);
		
		mesCB = new JComboBox();
		mesCB.setForeground(new Color(0, 0, 128));
		mesCB.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		mesCB.setBounds(62, 64, 95, 20);
		contentPane.add(mesCB);
		
		JLabel label_4 = new JLabel("Ano:");
		label_4.setForeground(new Color(0, 0, 128));
		label_4.setBounds(167, 67, 46, 14);
		contentPane.add(label_4);
		
		anoCB = new JComboBox();
		anoCB.setForeground(new Color(0, 0, 128));
		anoCB.setModel(new DefaultComboBoxModel(new String[] {"", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060"}));
		anoCB.setBounds(202, 64, 62, 20);
		contentPane.add(anoCB);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnFechar.setBackground(Color.WHITE);
		btnFechar.setForeground(new Color(0, 0, 128));
		btnFechar.setBounds(995, 502, 89, 23);
		contentPane.add(btnFechar);
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				refreshTable(m);
				
			}
		});
		button.setBackground(Color.WHITE);
		button.setIcon(new ImageIcon(DialogSolicitacoes.class.getResource("/br/cefet/sicom/img/atualizar.png")));
		button.setBounds(10, 484, 45, 41);
		contentPane.add(button);
		
		nomeCB = new JComboBox();
		nomeCB.setModel(new DefaultComboBoxModel(new String[] {""}));
		for(Solicitante s : SolicitanteDao.listarTodos()){
			
			nomeCB.addItem(s);
			
		}
		nomeCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				refreshTable(m);
								
			}
		});
		nomeCB.setForeground(new Color(0, 0, 128));
		nomeCB.setBounds(87, 92, 372, 20);
		contentPane.add(nomeCB);
		
		JLabel lblNome = new JLabel("Solicitante:");
		lblNome.setForeground(new Color(0, 0, 128));
		lblNome.setBounds(10, 95, 81, 14);
		contentPane.add(lblNome);
		
		tableSolicitacoes = new JTable();
		refreshTable(m);
		scrollPane.setViewportView(tableSolicitacoes);
		
		JButton btnGerarComprovante = new JButton("Gerar Comprovante De Saída");
		btnGerarComprovante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!solicitacoes.isEmpty()){
					if(tableSolicitacoes.getSelectedRow() != -1){
					
						if(mesCB.getSelectedIndex() != 0 && anoCB.getSelectedIndex() != 0){
							if(nomeCB.getSelectedIndex() == 0){
								
								solicitacoes = SolicitacaoDao.listarPorMaterial(m,mesCB.getSelectedIndex(), Integer.parseInt(anoCB.getSelectedItem().toString()));
								
							}
							else{

								solicitacoes = SolicitacaoDao.listarPorSolicitanteMaterial(m, (Solicitante) nomeCB.getSelectedItem(), mesCB.getSelectedIndex(), Integer.parseInt(anoCB.getSelectedItem().toString()));
							}
						}
						else{
							
							if(nomeCB.getSelectedIndex() == 0){
								
								solicitacoes = SolicitacaoDao.listarPorMaterial(m);
								
							}
							else{

								solicitacoes = SolicitacaoDao.listarPorSolicitanteMaterial(m, (Solicitante) nomeCB.getSelectedItem());
							}
							
						}
						
						Solicitacao s = solicitacoes.get(tableSolicitacoes.getSelectedRow());
						
						ArrayList<Solicitacao> solicitacoesDoSolicitante = SolicitacaoDao.listarPorSolicitante(s.getSolicitante());
						
						ArrayList<Solicitacao> solicitacoesComprovante = new ArrayList<Solicitacao>();
						
						for(Solicitacao s2 : solicitacoesDoSolicitante){
							
							if(isDataEquals(s.getData(), s2.getData())){
								
								solicitacoesComprovante.add(s2);
								
							}
							
						}
						
						GeradorDeTexto.gerarComprovanteSolicitacao(solicitacoesComprovante, s.getSolicitante(), s.getData(), s.getUsuario());
						
						try {
							
							java.awt.Desktop.getDesktop().open(new File(GeradorDeTexto.getEndereco(s.getSolicitante(), s.getData())));
												
						
						} catch (IOException e1) {
							
							JOptionPane.showMessageDialog(null, e1);
						
						}
					
					}
					else{
					
						JOptionPane.showMessageDialog(null, "Nenhuma solicitação selecionada!");
					}
					
				}
				else{
					
					JOptionPane.showMessageDialog(null, "Não existem solicitações!");
				}
				
			}
		});
		btnGerarComprovante.setBackground(new Color(255, 255, 255));
		btnGerarComprovante.setForeground(new Color(0, 0, 128));
		btnGerarComprovante.setBounds(469, 91, 221, 23);
		contentPane.add(btnGerarComprovante);
		
	}
}
