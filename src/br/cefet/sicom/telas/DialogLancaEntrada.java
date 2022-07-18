package br.cefet.sicom.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.cefet.sicom.dao.EntradaDao;
import br.cefet.sicom.dao.MaterialDao;
import br.cefet.sicom.modelo.Entrada;
import br.cefet.sicom.modelo.Material;
import br.cefet.sicom.sessao.Login;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogLancaEntrada extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField entradaTF;
	private JLabel lblNomematerial;
	private JTextField empenhoTF;
	private JTextField fornecedorTF;

	
	/**
	 * Create the dialog.
	 */
	
	public void refreshTable(JComboBox ordenarCB, ArrayList<Material> materiais, JTextField pesquisaTF, Object [][] objetos, JTable materiaisTable){
		
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
		
		objetos = new Object[materiais.size()][4];
		for(int i = 0; i < materiais.size(); i++){
				
				objetos[i][0] = materiais.get(i).getIdMaterial();
				objetos[i][1] = materiais.get(i).getDescricao();
				objetos[i][2] = materiais.get(i).getTipoUnid().toString();
				objetos[i][3] = materiais.get(i).getSaldo();				
			
		}
		
		materiaisTable.setModel(new DefaultTableModel(
			objetos,
			new String[] {
				"Id", "Descrição", "Tipo de Unid.", "Saldo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}
	
	public DialogLancaEntrada(Material m, JComboBox ordenarCB, JTextField pesquisaTF, Object [][] objetos, JTable materiaisTable, Login l) {
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("SEPAT");
		setResizable(false);
		setBounds(100, 100, 466, 252);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblLanarEntrada = new JLabel("Lançar Entrada");
		lblLanarEntrada.setForeground(new Color(0, 0, 139));
		lblLanarEntrada.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblLanarEntrada.setBounds(10, 11, 174, 23);
		contentPanel.add(lblLanarEntrada);
		
		JLabel lblQuantidadeASer = new JLabel("Quantidade a ser somada ao saldo:");
		lblQuantidadeASer.setForeground(new Color(0, 0, 128));
		lblQuantidadeASer.setBounds(10, 67, 211, 14);
		contentPanel.add(lblQuantidadeASer);
		
		JLabel lblMaterial = new JLabel("Material:");
		lblMaterial.setForeground(new Color(0, 0, 128));
		lblMaterial.setBounds(10, 45, 61, 14);
		contentPanel.add(lblMaterial);
		
		entradaTF = new JTextField();
		entradaTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				String caracteres="0987654321";
				if(!caracteres.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
				if(entradaTF.getText().length() > 8){
					arg0.consume();
				}
				
			}
		});
		entradaTF.setForeground(new Color(0, 0, 128));
		entradaTF.setBounds(218, 64, 221, 20);
		contentPanel.add(entradaTF);
		entradaTF.setColumns(10);
		
		lblNomematerial = new JLabel(m.getDescricao());
		lblNomematerial.setForeground(new Color(0, 0, 128));
		lblNomematerial.setBounds(66, 45, 373, 14);
		contentPanel.add(lblNomematerial);
		
		JButton btnFechar = new JButton("Cancelar");
		btnFechar.setBackground(new Color(255, 255, 255));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				dispose();
				
			}
		});
		btnFechar.setForeground(new Color(0, 0, 128));
		btnFechar.setBounds(10, 182, 120, 23);
		contentPanel.add(btnFechar);
		
		JButton btnLanar = new JButton("Lançar");
		btnLanar.setBackground(new Color(255, 255, 255));
		btnLanar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!entradaTF.getText().isEmpty() && !fornecedorTF.getText().isEmpty() && !empenhoTF.getText().isEmpty()){
					
					int qtdEntrada = Integer.parseInt(entradaTF.getText());
					Calendar c = new GregorianCalendar();					
					Entrada entrada = new Entrada(0, m, qtdEntrada, empenhoTF.getText(), fornecedorTF.getText(), c);
					
					EntradaDao.cadastrar(entrada);
					
					m.setSaldo(m.getSaldo() + entrada.getQtd());
					
					MaterialDao.atualizar(m);
					
					//Movimentacao mov = new Movimentacao(0, TipoDeAcao.LANCAMENTO_DE_ENTRADA, m.getDescricao() +"|"+entrada.getQtd(), c, l.getUsuario());
					//MovimentacaoDao.cadastrar(mov);
					
					JOptionPane.showMessageDialog(null, "Entrada lançada com sucesso!");
					
					ArrayList<Material> materiais = new ArrayList<Material>();
					refreshTable(ordenarCB, materiais, pesquisaTF, objetos, materiaisTable);
									
					dispose();
				
				}
				else{
					
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
					
				}
				
			}
		});
		btnLanar.setForeground(new Color(0, 0, 128));
		btnLanar.setBounds(319, 182, 120, 23);
		contentPanel.add(btnLanar);
		
		JLabel lblEmpenho = new JLabel("Empenho:");
		lblEmpenho.setForeground(new Color(0, 0, 128));
		lblEmpenho.setBounds(10, 92, 67, 14);
		contentPanel.add(lblEmpenho);
		
		empenhoTF = new JTextField();
		empenhoTF.setForeground(new Color(0, 0, 128));
		empenhoTF.setBounds(10, 106, 429, 20);
		contentPanel.add(empenhoTF);
		empenhoTF.setColumns(10);
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setForeground(new Color(0, 0, 128));
		lblFornecedor.setBounds(10, 137, 78, 14);
		contentPanel.add(lblFornecedor);
		
		fornecedorTF = new JTextField();
		fornecedorTF.setForeground(new Color(0, 0, 128));
		fornecedorTF.setBounds(10, 151, 429, 20);
		contentPanel.add(fornecedorTF);
		fornecedorTF.setColumns(10);
	}
}
