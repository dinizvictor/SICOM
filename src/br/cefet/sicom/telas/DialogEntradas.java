package br.cefet.sicom.telas;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.cefet.sicom.dao.EntradaDao;
import br.cefet.sicom.dao.MaterialDao;
import br.cefet.sicom.modelo.Entrada;
import br.cefet.sicom.modelo.Material;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;;

public class DialogEntradas extends JDialog {

	private JPanel contentPane;
	private JTable entradasTable;
	private JTextField pesquisaTF;
	ArrayList<Entrada> entradas;
	private JComboBox filtrarCB;
	private JComboBox mesCB;
	private JComboBox anoCB;

	
	public void refreshTable(Material m){
		
		if(filtrarCB.getSelectedIndex() == 0){
			if(mesCB.getSelectedIndex() != 0 && anoCB.getSelectedIndex() != 0){
				
				entradas = EntradaDao.listarPorEmpenhoSubString(m, pesquisaTF.getText(), mesCB.getSelectedIndex(), Integer.parseInt(anoCB.getSelectedItem().toString()));
				
			}
			else{
			
				entradas = EntradaDao.listarPorEmpenhoSubString(m, pesquisaTF.getText());
			
			}
			
		}
		else if(filtrarCB.getSelectedIndex() == 1){
			
			if(mesCB.getSelectedIndex() != 0 && anoCB.getSelectedIndex() != 0){
				
				entradas = EntradaDao.listarPorFornecedorSubString(m, pesquisaTF.getText(), mesCB.getSelectedIndex(), Integer.parseInt(anoCB.getSelectedItem().toString()));
				
			}
			else{
			
				entradas = EntradaDao.listarPorFornecedorSubString(m, pesquisaTF.getText());
			
			}
			
		}
				
		String [] colunas = new String[] {"Id", "Data", "Qtd", "Empenho", "Fornecedor"};
				
		Object [][] objetos = new Object[entradas.size()][5];
		for(int i = 0; i < entradas.size(); i++){
				
			String data = entradas.get(i).getData().get(Calendar.DAY_OF_MONTH)+ "/" +(entradas.get(i).getData().get(Calendar.MONTH) + 1)+ "/" +entradas.get(i).getData().get(Calendar.YEAR);	
			
			objetos[i][0] = entradas.get(i).getIdEntrada();
			objetos[i][1] = data;
			objetos[i][2] = entradas.get(i).getQtd();
			objetos[i][3] = entradas.get(i).getEmpenho();
			objetos[i][4] = entradas.get(i).getFornecedor();
			
		}
		
		DefaultTableModel dtm = new DefaultTableModel(objetos, colunas){
			
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
			public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			
		};
		entradasTable.setModel(dtm);
		entradasTable.setForeground(new Color(0, 0, 128));
		entradasTable.getColumnModel().getColumn(0).setPreferredWidth(57);
		entradasTable.getColumnModel().getColumn(1).setPreferredWidth(86);
		entradasTable.getColumnModel().getColumn(2).setPreferredWidth(56);
		entradasTable.getColumnModel().getColumn(3).setPreferredWidth(288);
		entradasTable.getColumnModel().getColumn(4).setPreferredWidth(134);
		entradasTable.setForeground(new Color(0, 0, 128));
		
						
	}
	
	public DialogEntradas(Material m) {
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("SEPAT");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 811, 534);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEntradas = new JLabel("Entradas");
		lblEntradas.setForeground(new Color(0, 0, 139));
		lblEntradas.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblEntradas.setBounds(10, 11, 108, 23);
		contentPane.add(lblEntradas);
		
		JLabel lblTitulo = new JLabel("Material:");
		lblTitulo.setForeground(new Color(0, 0, 128));
		lblTitulo.setBounds(10, 45, 56, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblMaterial = new JLabel(m.getDescricao());
		lblMaterial.setForeground(new Color(0, 0, 128));
		lblMaterial.setBounds(76, 45, 708, 14);
		contentPane.add(lblMaterial);
		
		JLabel lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setForeground(new Color(0, 0, 128));
		lblFiltrarPor.setBounds(10, 70, 62, 14);
		contentPane.add(lblFiltrarPor);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBackground(new Color(255, 255, 255));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				
			}
		});
		btnFechar.setForeground(new Color(0, 0, 128));
		btnFechar.setBounds(664, 445, 120, 41);
		contentPane.add(btnFechar);
		
		filtrarCB = new JComboBox();
		filtrarCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				pesquisaTF.setText("");
				refreshTable(m);
				
			}
		});
		filtrarCB.setForeground(new Color(0, 0, 128));
		filtrarCB.setModel(new DefaultComboBoxModel(new String[] {"Empenho", "Fornecedor"}));
		filtrarCB.setBounds(76, 67, 108, 20);
		contentPane.add(filtrarCB);
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setForeground(new Color(0, 0, 128));
		lblPesquisar.setBounds(10, 95, 62, 14);
		contentPane.add(lblPesquisar);
		
		pesquisaTF = new JTextField();
		pesquisaTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				refreshTable(m);
				
			}
		});
		pesquisaTF.setForeground(new Color(0, 0, 128));
		pesquisaTF.setBounds(76, 92, 708, 20);
		contentPane.add(pesquisaTF);
		pesquisaTF.setColumns(10);
		
		JLabel lblPerodo = new JLabel("Período:");
		lblPerodo.setForeground(new Color(0, 0, 128));
		lblPerodo.setBounds(194, 70, 62, 14);
		contentPane.add(lblPerodo);
		
		mesCB = new JComboBox();
		mesCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				refreshTable(m);
				
			}
		});
		mesCB.setForeground(new Color(0, 0, 128));
		mesCB.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		mesCB.setBounds(246, 67, 95, 20);
		contentPane.add(mesCB);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setForeground(new Color(0, 0, 128));
		lblAno.setBounds(351, 70, 46, 14);
		contentPane.add(lblAno);
		
		anoCB = new JComboBox();
		anoCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				refreshTable(m);
				
			}
		});
		anoCB.setModel(new DefaultComboBoxModel(new String[] {"", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060"}));
		anoCB.setForeground(new Color(0, 0, 128));
		anoCB.setBounds(386, 67, 62, 20);
		contentPane.add(anoCB);
		SpinnerModel model1 = new SpinnerDateModel();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 132, 774, 302);
		contentPane.add(scrollPane);
		
		entradasTable = new JTable();
		refreshTable(m);
		scrollPane.setViewportView(entradasTable);
		
		JButton button = new JButton("");
		button.setBackground(new Color(255, 255, 255));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				refreshTable(m);
				
			}
		});
		button.setIcon(new ImageIcon(DialogEntradas.class.getResource("/br/cefet/sicom/img/atualizar.png")));
		button.setBounds(10, 445, 45, 41);
		contentPane.add(button);
				
		
	}
}
