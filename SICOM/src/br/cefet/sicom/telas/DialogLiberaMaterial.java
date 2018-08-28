package br.cefet.sicom.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import br.cefet.sicom.dao.MaterialDao;
import br.cefet.sicom.dao.SolicitanteDao;
import br.cefet.sicom.modelo.Material;
import br.cefet.sicom.modelo.Solicitacao;
import br.cefet.sicom.modelo.Solicitante;
import br.cefet.sicom.sessao.Login;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DialogLiberaMaterial extends JDialog {

	private JPanel contentPane;
	private JTextField qtdForTF;
	private JTextField pesquisaTF;
	JList materiaisList;
	JList solicitacaoList;
	ArrayList<Material> materiais; //Referente aos materiais registrados no banco de dados
	ArrayList<Material> materiaisFiltro; //Referente aos itens filtrados através do pesquisaTF
	ArrayList<Solicitacao> solicitacao;
	JComboBox solicitanteCB;
	
	//pedido - list | solicitacao - bd
	
	public void refreshListMaterial(){
		
		DefaultListModel model = new DefaultListModel();
		for(Material m : materiaisFiltro){
			
			model.addElement(m);
		
		}
		
		materiaisList.setModel(model);
		
	}
	
	public void refreshListSolicitacao(){
		
		DefaultListModel model = new DefaultListModel();
		for(Solicitacao soli : solicitacao){
			
			model.addElement(soli);
		
		}
		
		solicitacaoList.setModel(model);
		
	}
	
	public DialogLiberaMaterial(JComboBox ordenarCB, JTextField pesquisaTFTCM, Object [][] objetos, JTable materiaisTable, Login l) {
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 880, 565);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		solicitacao = new ArrayList<Solicitacao>();
		
		JLabel lblLiberaoDeMateriais = new JLabel("Libera\u00E7\u00E3o de Materiais");
		lblLiberaoDeMateriais.setForeground(new Color(0, 0, 139));
		lblLiberaoDeMateriais.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblLiberaoDeMateriais.setBounds(10, 9, 414, 23);
		contentPane.add(lblLiberaoDeMateriais);
		
		JLabel lblSolicitante = new JLabel("Solicitante:");
		lblSolicitante.setForeground(new Color(0, 0, 128));
		lblSolicitante.setBounds(10, 65, 74, 14);
		contentPane.add(lblSolicitante);
		
		solicitanteCB = new JComboBox();
		solicitanteCB.setForeground(new Color(0, 0, 128));
		ArrayList<Solicitante> solicitantes = SolicitanteDao.listarTodos();
		for(Solicitante s : solicitantes){
			solicitanteCB.addItem(s);
		}
		solicitanteCB.setBounds(79, 61, 595, 23);
		contentPane.add(solicitanteCB);
		
		JButton btnCadastrarSolicitante = new JButton("Cadastrar Solicitante");
		btnCadastrarSolicitante.setBackground(new Color(255, 255, 255));
		btnCadastrarSolicitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DialogCadastraSolicitante dcs = new DialogCadastraSolicitante(solicitanteCB, l);
				dcs.setVisible(true);
				
			}
		});
		btnCadastrarSolicitante.setForeground(new Color(0, 0, 128));
		btnCadastrarSolicitante.setBounds(684, 61, 180, 23);
		contentPane.add(btnCadastrarSolicitante);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 98, 874, 2);
		contentPane.add(separator);
		
		JLabel lblQtdFornecida = new JLabel("Qtd Fornecida:");
		lblQtdFornecida.setForeground(new Color(0, 0, 128));
		lblQtdFornecida.setBounds(359, 270, 87, 14);
		contentPane.add(lblQtdFornecida);
		
		qtdForTF = new JTextField();
		qtdForTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				String caracteres="0987654321";
				if(!caracteres.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
				if(qtdForTF.getText().length() > 8){
					arg0.consume();
				}
				
			}
		});
		
		qtdForTF.setForeground(new Color(0, 0, 128));
		qtdForTF.setBounds(359, 288, 95, 20);
		contentPane.add(qtdForTF);
		qtdForTF.setColumns(10);
		
		pesquisaTF = new JTextField();
		pesquisaTF.setForeground(new Color(0, 0, 128));
		pesquisaTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				ArrayList<Material> materiaisAtualizado = new ArrayList<Material>();
				for(Material m : materiais){
					if(m.getDescricao().contains(pesquisaTF.getText().toUpperCase())){
						materiaisAtualizado.add(m);
					}
				}
				materiaisFiltro = materiaisAtualizado;
				refreshListMaterial();
				
			}
		});
		pesquisaTF.setBounds(77, 400, 272, 20);
		contentPane.add(pesquisaTF);
		pesquisaTF.setColumns(10);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBackground(Color.WHITE);
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setForeground(new Color(0, 0, 128));
		btnFechar.setBounds(775, 503, 89, 23);
		contentPane.add(btnFechar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 129, 339, 263);
		contentPane.add(scrollPane);
		
		materiaisList = new JList();
		materiaisList.setForeground(new Color(0, 0, 128));
		materiais = MaterialDao.listarPorDescricaoSubString("","descricao");
		materiaisFiltro = materiais;
		materiaisList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		refreshListMaterial();	
		
		scrollPane.setViewportView(materiaisList);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(464, 129, 400, 263);
		contentPane.add(scrollPane_1);
		
		solicitacaoList = new JList();
		solicitacaoList.setForeground(new Color(0, 0, 128));
		scrollPane_1.setViewportView(solicitacaoList);
		
		JButton btnAdicionarMaterial = new JButton("Add >>");
		btnAdicionarMaterial.setBackground(new Color(255, 255, 255));
		btnAdicionarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(materiaisList.getSelectedIndex() != -1){
					if(!qtdForTF.getText().isEmpty()){
						if(Integer.parseInt(qtdForTF.getText()) != 0){
							Solicitante sol = (Solicitante) solicitanteCB.getSelectedItem();
							
							boolean existente = false;
							Solicitacao s2 = null;
							Material m = materiaisFiltro.get(materiaisList.getSelectedIndex());
							int qtdDisponivel = m.getSaldo();
							for(Solicitacao s : solicitacao){
								if(m.getIdMaterial() == s.getMaterial().getIdMaterial()){
									existente = true;
									s2 = s;
									break;
								}
																
							}
							if(existente){
								if(Integer.parseInt(qtdForTF.getText()) <= qtdDisponivel){
									s2.setQtdFornecida(s2.getQtdFornecida() + Integer.parseInt(qtdForTF.getText()));
									m.setSaldo(m.getSaldo() - Integer.parseInt(qtdForTF.getText()));
									refreshListSolicitacao();
									refreshListMaterial();
									qtdForTF.setText("");
								}
								else{
									JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
									qtdForTF.setText("");
								}
							}
							else{
								if(Integer.parseInt(qtdForTF.getText()) <= qtdDisponivel){
									Solicitacao s = new Solicitacao(0, sol, null, m, Integer.parseInt(qtdForTF.getText()), l.getUsuario());
									//Data null >> Para que todos os itens sejam armazenados com a mesma data
									m.setSaldo(m.getSaldo() - Integer.parseInt(qtdForTF.getText()));
									solicitacao.add(s);
									refreshListSolicitacao();
									refreshListMaterial();
									qtdForTF.setText("");
								}
								else{
									JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
									qtdForTF.setText("");
								}
								
							}
						
						}
						else{
							JOptionPane.showMessageDialog(null,"Digite uma quantidade válida!");
						}
						
					}
					else{
						JOptionPane.showMessageDialog(null,"Preencha o campo com a quantidade!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Selecione o material a ser adicionado!");
				}
			
				
			}
		});
		btnAdicionarMaterial.setForeground(new Color(0, 0, 128));
		btnAdicionarMaterial.setBounds(359, 236, 95, 23);
		contentPane.add(btnAdicionarMaterial);
		
		JLabel lblNewLabel = new JLabel("Materiais (Descri\u00E7\u00E3o - Saldo):");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBounds(10, 111, 180, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Solicita\u00E7\u00E3o:");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setBounds(464, 111, 74, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setForeground(new Color(0, 0, 128));
		lblPesquisar.setBounds(10, 403, 74, 14);
		contentPane.add(lblPesquisar);
		
		JButton btnLimparLista = new JButton("Limpar Lista");
		btnLimparLista.setBackground(Color.WHITE);
		btnLimparLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!solicitacao.isEmpty()){
					solicitacaoList.removeAll();
					for(int i = solicitacao.size() - 1; i >= 0; i--){
						solicitacao.remove(i);
					}
					materiaisFiltro = MaterialDao.listarPorDescricaoSubString("", "descricao");
					materiais = MaterialDao.listarPorDescricaoSubString("", "descricao");
					pesquisaTF.setText("");
					refreshListMaterial();
					refreshListSolicitacao();
				}
				else{
					JOptionPane.showMessageDialog(null, "Não existe nenhum material na lista de solicitação!");
				}
				
				
			}
		});
		btnLimparLista.setForeground(new Color(0, 0, 128));
		btnLimparLista.setBounds(745, 399, 119, 23);
		contentPane.add(btnLimparLista);
		
		JButton btnRemoverMaterial = new JButton("Remover");
		btnRemoverMaterial.setBackground(new Color(255, 255, 255));
		btnRemoverMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!solicitacao.isEmpty()){
					if(solicitacaoList.getSelectedIndex() != -1){
						Solicitacao s = solicitacao.get(solicitacaoList.getSelectedIndex());
						for(Material m : materiaisFiltro){
							if(m.getIdMaterial() == s.getMaterial().getIdMaterial()){
								m.setSaldo(MaterialDao.listarPorId(m.getIdMaterial()).getSaldo());
								break;
							}
						}
						solicitacao.remove(s);
						refreshListMaterial();
						refreshListSolicitacao();
					}
					else{
						JOptionPane.showMessageDialog(null, "Selecione um material da lista de solicitação!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Não existe nenhum material na lista de solicitação!");
				}
				
			}
		});
		btnRemoverMaterial.setForeground(new Color(0, 0, 128));
		btnRemoverMaterial.setBounds(607, 399, 119, 23);
		contentPane.add(btnRemoverMaterial);
		
		JButton btnEditarQtd = new JButton("Editar Qtd");
		btnEditarQtd.setBackground(new Color(255, 255, 255));
		btnEditarQtd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!solicitacao.isEmpty()){
					if(solicitacaoList.getSelectedIndex() != -1){
						Solicitacao s = solicitacao.get(solicitacaoList.getSelectedIndex());
						for(Material m : materiaisFiltro){
							if(m.getIdMaterial() == s.getMaterial().getIdMaterial()){
								try{
									String termo = JOptionPane.showInputDialog("Digite a nova quantidade:");
									int novaQtd = Integer.parseInt(termo);
									if(novaQtd > 0){
										
										for(Material mBD : MaterialDao.listarPorDescricaoSubString("", "descricao")){
											if(mBD.getIdMaterial() == m.getIdMaterial()){
												
												int qtdTotal = mBD.getSaldo();
												if(novaQtd <= qtdTotal){
													m.setSaldo(qtdTotal - novaQtd);
													s.setQtdFornecida(novaQtd);
													refreshListMaterial();
													refreshListSolicitacao();
												}
												else{
													JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
												}
												
											}
										}
										
									}
									
									else{
										JOptionPane.showMessageDialog(null, "Digite um número válido!");
									}
								}
								catch(NumberFormatException nfe){
									JOptionPane.showMessageDialog(null, "Digite um número válido!");
								}
								
								
								
								break;
							}
						}
						
						
						refreshListMaterial();
						refreshListSolicitacao();
					}
					else{
						JOptionPane.showMessageDialog(null, "Selecione um material da lista de solicitação!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Não existe nenhum material na lista de solicitação!");
				}
				
			}
		});
		btnEditarQtd.setForeground(new Color(0, 0, 128));
		btnEditarQtd.setBounds(464, 399, 124, 23);
		contentPane.add(btnEditarQtd);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 449, 874, 2);
		contentPane.add(separator_1);
		
		JButton btnFinalizarSolicitao = new JButton("Finalizar Solicita\u00E7\u00E3o");
		btnFinalizarSolicitao.setBackground(new Color(255, 255, 255));
		btnFinalizarSolicitao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(solicitanteCB.getSelectedIndex() != -1){
					if(!solicitacao.isEmpty()){
						DialogConfirmaSolicitacao dcs = new DialogConfirmaSolicitacao(solicitacao, (Solicitante) solicitanteCB.getSelectedItem(), solicitacaoList, ordenarCB, pesquisaTFTCM, objetos, materiaisTable, l);
						dcs.setVisible(true);
						
					}
					else{
						JOptionPane.showMessageDialog(null, "Nenhum material na solicitação!");
					}
				
				}
				else{
					JOptionPane.showMessageDialog(null, "Selecione um solicitante!");
				}
				
				
			}
		});
		btnFinalizarSolicitao.setForeground(new Color(0, 0, 128));
		btnFinalizarSolicitao.setBounds(607, 503, 158, 23);
		contentPane.add(btnFinalizarSolicitao);
	
	}
}
