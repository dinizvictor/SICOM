package br.cefet.sicom.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.cefet.sicom.dao.MaterialDao;
import br.cefet.sicom.modelo.Material;
import br.cefet.sicom.sessao.Login;


public class DialogControleMaterial extends JDialog {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable materiaisTable;
	private JLabel lblOrdenarPor;
	JComboBox ordenarCB;
	JTextField pesquisaTF;
	ArrayList<Material> materiais = null; //Dados do Banco de Dados
	Object [][] objetos = null; //Objetos colocados em formato de tabela
	
	
	public void refreshTable(){
		
		if(ordenarCB.getSelectedIndex() == 0){
			
			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"descricao");
			
		}
		else if(ordenarCB.getSelectedIndex() == 1){
			
			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"saldo");
			
		}
		else if(ordenarCB.getSelectedIndex() == 2){
			
			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"tipoUnid");
			
		}
		else if(ordenarCB.getSelectedIndex() == 3){
			
			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"categoria");
			
		}
		else if(ordenarCB.getSelectedIndex() == 4){
			
			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"localizacao");
			
		}
		
		objetos = new Object[materiais.size()][6];
		for(int i = 0; i < materiais.size(); i++){
				
				objetos[i][0] = materiais.get(i).getIdMaterial();
				objetos[i][1] = materiais.get(i).getDescricao();
				objetos[i][2] = materiais.get(i).getTipoUnid().toString();
				objetos[i][3] = materiais.get(i).getSaldo();
				objetos[i][4] = materiais.get(i).getCategoria().toString();
				objetos[i][5] = materiais.get(i).getLocalizacao().toString();
			
		}
		
		materiaisTable.setModel(new DefaultTableModel(
			objetos,
			new String[] {
				"Id", "Descri\u00E7\u00E3o", "Tipo de Unid.", "Saldo", "Categoria", "Localiza\u00E7\u00E3o"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}
	
	public DialogControleMaterial(Login l) {
		setModal(true);
		setLocationRelativeTo(null);
		setTitle("SICOM");
		//Defini��o
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1113, 582);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblControleDeMateriais = new JLabel("Controle de Materiais");
		lblControleDeMateriais.setForeground(new Color(0, 0, 139));
		lblControleDeMateriais.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblControleDeMateriais.setBounds(10, 11, 232, 23);
		contentPane.add(lblControleDeMateriais);
		
		lblOrdenarPor = new JLabel("Ordenar:");
		lblOrdenarPor.setForeground(new Color(0, 0, 128));
		lblOrdenarPor.setBounds(10, 70, 70, 14);
		contentPane.add(lblOrdenarPor);
		
		ordenarCB = new JComboBox();
		ordenarCB.addActionListener(new ActionListener() {
			//Ao selecionar uma categoria de ordena��o >>
			public void actionPerformed(ActionEvent arg0) {
				
				refreshTable();
				materiaisTable.setForeground(new Color(0, 0, 128));
				scrollPane.setViewportView(materiaisTable);
				
			}
		});
				
		lblOrdenarPor.setLabelFor(ordenarCB);
		ordenarCB.setModel(new DefaultComboBoxModel(new String[] {"Por Ordem Alfab\u00E9tica", "Por Saldo", "Por Tipo de Unidade", "Por Categoria", "Por Localiza\u00E7\u00E3o"}));
		ordenarCB.setForeground(new Color(0, 0, 128));
		ordenarCB.setBounds(78, 67, 164, 20);
		contentPane.add(ordenarCB);
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setForeground(new Color(0, 0, 128));
		lblPesquisar.setBounds(10, 45, 70, 14);
		contentPane.add(lblPesquisar);
		pesquisaTF = new JTextField();		
		lblPesquisar.setLabelFor(pesquisaTF);
		pesquisaTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//TipoDeOrdena��o
				refreshTable();
				materiaisTable.setForeground(new Color(0, 0, 128));
				scrollPane.setViewportView(materiaisTable);
				
			}
		});
		pesquisaTF.setForeground(new Color(0, 0, 128));
		pesquisaTF.setBounds(78, 42, 1008, 20);
		contentPane.add(pesquisaTF);
		pesquisaTF.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 1076, 370);
		contentPane.add(scrollPane);
		
		materiaisTable = new JTable();
		materiaisTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		refreshTable();
		
		materiaisTable.setForeground(new Color(0, 0, 128));
		scrollPane.setViewportView(materiaisTable);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBackground(new Color(255, 255, 255));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setForeground(new Color(0, 0, 128));
		btnFechar.setBounds(937, 508, 149, 23);
		contentPane.add(btnFechar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBackground(new Color(255, 255, 255));
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(materiaisTable.getSelectedRow() != -1 && !materiais.isEmpty()){
					
					int botoes = JOptionPane.YES_NO_OPTION;
	                int resultado = JOptionPane.showConfirmDialog (null, "Deseja realmente excluir o material?","Confirma��o",botoes);

	                if(resultado == JOptionPane.YES_OPTION){
	                	
	                	if(ordenarCB.getSelectedIndex() == 0){
	            			
	            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"descricao");
	            			
	            		}
	            		else if(ordenarCB.getSelectedIndex() == 1){
	            			
	            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"saldo");
	            			
	            		}
	            		else if(ordenarCB.getSelectedIndex() == 2){
	            			
	            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"tipoUnid");
	            			
	            		}
	                	
	                	Material m = materiais.get(materiaisTable.getSelectedRow());
	                	MaterialDao.excluirPorId(m.getIdMaterial());
	                	JOptionPane.showMessageDialog(null, "Removido com sucesso!");
	                	refreshTable();
	                	
	                }
										
				}
				else{
					JOptionPane.showMessageDialog(null, "Nenhum Material Selecionado!");
				}
				
				
			}
		});
		btnRemover.setForeground(new Color(0, 0, 128));
		btnRemover.setBounds(936, 66, 150, 23);
		contentPane.add(btnRemover);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(new Color(255, 255, 255));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DialogCadastraMaterial dcm = new DialogCadastraMaterial(ordenarCB, materiais, pesquisaTF, objetos, materiaisTable,l);
				dcm.setVisible(true);
				
			}
		});
		btnCadastrar.setForeground(new Color(0, 0, 128));
		btnCadastrar.setBounds(457, 66, 150, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBackground(new Color(255, 255, 255));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(materiaisTable.getSelectedRow() != -1 && !materiais.isEmpty()){
					
					if(ordenarCB.getSelectedIndex() == 0){
            			
            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"descricao");
            			
            		}
            		else if(ordenarCB.getSelectedIndex() == 1){
            			
            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"saldo");
            			
            		}
            		else if(ordenarCB.getSelectedIndex() == 2){
            			
            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"tipoUnid");
            			
            		}
					
					DialogAtualizaMaterial dam = new DialogAtualizaMaterial(ordenarCB, materiais, pesquisaTF, objetos, materiaisTable,l);
					Material m = materiais.get(materiaisTable.getSelectedRow());
					dam.descricaoTF.setText(m.getDescricao());
					dam.tipoUnidCB.setSelectedItem(m.getTipoUnid());
					dam.localizacaoCB.setSelectedItem(m.getLocalizacao());
					dam.saldoTF.setText(String.valueOf(m.getSaldo()));
					dam.categoriaCB.setSelectedItem(m.getCategoria());
					dam.setVisible(true);
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Nenhum Material Selecionado!");
				}
				
			}
		});
		btnAtualizar.setForeground(new Color(0, 0, 128));
		btnAtualizar.setBounds(297, 66, 150, 23);
		contentPane.add(btnAtualizar);
		
		JButton button = new JButton("");
		button.setBackground(new Color(255, 255, 255));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				refreshTable();
				
			}
		});
		button.setIcon(new ImageIcon(DialogControleMaterial.class.getResource("/br/cefet/sepat/img/atualizar.png")));
		button.setBounds(10, 476, 45, 55);
		contentPane.add(button);
		
		JButton btnEntradas = new JButton("Entradas");
		btnEntradas.setBackground(new Color(255, 255, 255));
		btnEntradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(materiaisTable.getSelectedRow() != -1 && !materiais.isEmpty()){
					
					if(ordenarCB.getSelectedIndex() == 0){
            			
            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"descricao");
            			
            		}
            		else if(ordenarCB.getSelectedIndex() == 1){
            			
            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"saldo");
            			
            		}
            		else if(ordenarCB.getSelectedIndex() == 2){
            			
            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"tipoUnid");
            			
            		}
					
					Material m = materiais.get(materiaisTable.getSelectedRow());
					DialogEntradas de = new DialogEntradas(m);
					de.setVisible(true);
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Nenhum Material Selecionado!");
				}
				
			}
		});
		btnEntradas.setForeground(new Color(0, 0, 128));
		btnEntradas.setBounds(65, 476, 150, 23);
		contentPane.add(btnEntradas);
		
		JButton btnSolicitacoes = new JButton("Solicita\u00E7\u00F5es");
		btnSolicitacoes.setBackground(new Color(255, 255, 255));
		btnSolicitacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(materiaisTable.getSelectedRow() != -1 && !materiais.isEmpty()){
					
					if(ordenarCB.getSelectedIndex() == 0){
            			
            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"descricao");
            			
            		}
            		else if(ordenarCB.getSelectedIndex() == 1){
            			
            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"saldo");
            			
            		}
            		else if(ordenarCB.getSelectedIndex() == 2){
            			
            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"tipoUnid");
            			
            		}
					
					Material m = materiais.get(materiaisTable.getSelectedRow());
					DialogSolicitacoes ds = new DialogSolicitacoes(m, l);
					ds.setVisible(true);
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Nenhum Material Selecionado!");
				}
				
			}
		});
		btnSolicitacoes.setForeground(new Color(0, 0, 128));
		btnSolicitacoes.setBounds(65, 508, 150, 23);
		contentPane.add(btnSolicitacoes);
		
		JButton btnLiberar = new JButton("Liberar Sa\u00EDda");
		btnLiberar.setBackground(new Color(255, 255, 255));
		btnLiberar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DialogLiberaMaterial dlm = new DialogLiberaMaterial(ordenarCB, pesquisaTF, objetos, materiaisTable,l);
				dlm.setVisible(true);
								
				
			}
		});
		btnLiberar.setForeground(new Color(0, 0, 128));
		btnLiberar.setBounds(777, 66, 149, 23);
		contentPane.add(btnLiberar);
		
		JButton btnNewButton = new JButton("Lan\u00E7ar Entrada");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(materiaisTable.getSelectedRow() != -1 && !materiais.isEmpty()){
					
					if(ordenarCB.getSelectedIndex() == 0){
            			
            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"descricao");
            			
            		}
            		else if(ordenarCB.getSelectedIndex() == 1){
            			
            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"saldo");
            			
            		}
            		else if(ordenarCB.getSelectedIndex() == 2){
            			
            			materiais = MaterialDao.listarPorDescricaoSubString(pesquisaTF.getText(),"tipoUnid");
            			
            		}
					
					Material m = materiais.get(materiaisTable.getSelectedRow());
					DialogLancaEntrada dle = new DialogLancaEntrada(m, ordenarCB, pesquisaTF, objetos, materiaisTable,l);
					dle.setVisible(true);
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Nenhum Material Selecionado!");
				}
				
				
				
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setBounds(617, 66, 149, 23);
		contentPane.add(btnNewButton);
		
		
	}

}
