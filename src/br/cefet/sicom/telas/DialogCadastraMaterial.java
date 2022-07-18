package br.cefet.sicom.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.cefet.sicom.dao.MaterialDao;
import br.cefet.sicom.modelo.Material;
import br.cefet.sicom.sessao.Login;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DialogCadastraMaterial extends JDialog {

	private JPanel contentPane;
	private JTextField descricaoTF;
	private JTextField saldoTF;
	private JComboBox categoriaCB;
	private JComboBox localizacaoCB;

	
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
		
		objetos = new Object[materiais.size()][6];
		for(int i = 0; i < materiais.size(); i++){
				
				objetos[i][0] = materiais.get(i).getIdMaterial();
				objetos[i][1] = materiais.get(i).getDescricao();
				objetos[i][2] = materiais.get(i).getTipoUnid().toString();
				objetos[i][3] = materiais.get(i).getSaldo();
				objetos[i][4] = materiais.get(i).getCategoria();
				objetos[i][5] = materiais.get(i).getLocalizacao();
			
		}
		
		materiaisTable.setModel(new DefaultTableModel(
			objetos,
			new String[] {
				"Id", "Descrição", "Tipo de Unid.", "Saldo", "Categoria", "Localização"
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
	
		
	public DialogCadastraMaterial(JComboBox ordenarCB, ArrayList<Material> materiais, JTextField pesquisaTF, Object [][] objetos, JTable materiaisTable, Login l) {
		setLocationRelativeTo(null);
		setTitle("SICOM");
		setResizable(false);
		setModal(true);
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 448, 231);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroDeMateriais = new JLabel("Cadastro de Materiais");
		lblCadastroDeMateriais.setForeground(new Color(0, 0, 139));
		lblCadastroDeMateriais.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblCadastroDeMateriais.setBounds(10, 11, 223, 23);
		contentPane.add(lblCadastroDeMateriais);
		
		JLabel lblNewLabel = new JLabel("Descrição:");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBounds(10, 45, 61, 14);
		contentPane.add(lblNewLabel);
		
		descricaoTF = new JTextField();
		descricaoTF.setForeground(new Color(0, 0, 128));
		lblNewLabel.setLabelFor(descricaoTF);
		descricaoTF.setBounds(10, 61, 414, 20);
		contentPane.add(descricaoTF);
		descricaoTF.setColumns(10);
		
		JLabel lblTipoDeUnidade = new JLabel("Tipo de Unidade:");
		lblTipoDeUnidade.setForeground(new Color(0, 0, 128));
		lblTipoDeUnidade.setBounds(10, 92, 95, 14);
		contentPane.add(lblTipoDeUnidade);
		
		JComboBox tipoUnidCB = new JComboBox();
		tipoUnidCB.setForeground(new Color(0, 0, 128));
		lblTipoDeUnidade.setLabelFor(tipoUnidCB);
		tipoUnidCB.setBounds(114, 89, 69, 20);
		tipoUnidCB.setModel(new DefaultComboBoxModel(new String[] {"UNID", "CX", "PCT", "METRO", "RESMA", "ROLO"}));
		contentPane.add(tipoUnidCB);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(Color.WHITE);
		btnCadastrar.addActionListener(new ActionListener() {
			//Cadastrar
			public void actionPerformed(ActionEvent e) {
				
				if(!descricaoTF.getText().isEmpty() && !saldoTF.getText().isEmpty() && tipoUnidCB.getSelectedIndex() != -1){
					
					ArrayList<Material> material = MaterialDao.listarPorDescricao(descricaoTF.getText());
					if(material.isEmpty()){
												
						Material m = new Material(0, descricaoTF.getText(), tipoUnidCB.getSelectedItem().toString(), Integer.parseInt(saldoTF.getText()),categoriaCB.getSelectedItem().toString(),localizacaoCB.getSelectedItem().toString(),false,Integer.parseInt(saldoTF.getText()));
						
						MaterialDao.cadastrar(m);
						
						Calendar c = new GregorianCalendar();
						//Movimentacao mov = new Movimentacao(0, TipoDeAcao.CADASTRO_DE_MATERIAL, m.getDescricao()+"|"+m.getCategoria()+"|"+m.getLocalizacao()+"|"+m.getSaldo()+"|"+m.getTipoUnid(), c, l.getUsuario());
						//MovimentacaoDao.cadastrar(mov);
						
						JOptionPane.showMessageDialog(null, "Cadastro Realizado!");
						
						refreshTable(ordenarCB, materiais, pesquisaTF, objetos, materiaisTable);
						
						dispose(); //Fechando a Tela!
						
						
						
					}
					else{
						JOptionPane.showMessageDialog(null, "Material já consta nos registros!");
					}
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Corretamente!");
				}
				
			}
			
		});
		btnCadastrar.setForeground(new Color(0, 0, 128));
		btnCadastrar.setBounds(319, 160, 105, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.addActionListener(new ActionListener() {
			//Cancelar
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 128));
		btnCancelar.setBounds(208, 160, 105, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setForeground(new Color(0, 0, 128));
		lblSaldo.setBounds(61, 120, 44, 14);
		contentPane.add(lblSaldo);
		
		saldoTF = new JTextField();
		saldoTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				String caracteres="0987654321";
				if(!caracteres.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
				if(saldoTF.getText().length() > 8){
					arg0.consume();
				}
				
			}
		});
		saldoTF.setForeground(new Color(0, 0, 128));
		saldoTF.setText("0");
		saldoTF.setBounds(114, 117, 69, 20);
		contentPane.add(saldoTF);
		saldoTF.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setForeground(new Color(0, 0, 128));
		lblCategoria.setBounds(193, 92, 84, 14);
		contentPane.add(lblCategoria);
		
		JLabel lblLocalizao = new JLabel("Localização:");
		lblLocalizao.setForeground(new Color(0, 0, 128));
		lblLocalizao.setBounds(193, 120, 84, 14);
		contentPane.add(lblLocalizao);
		
		categoriaCB = new JComboBox();
		categoriaCB.setForeground(new Color(0, 0, 128));
		categoriaCB.setBounds(272, 88, 152, 23);
		contentPane.add(categoriaCB);
		categoriaCB.setModel(new DefaultComboBoxModel(new String[] {"ME", "TONER", "CARTUCHO", "ALVENARIA", "ELETRICA", "HIDRAULICA", "OUTROS"}));
		
		localizacaoCB = new JComboBox();
		localizacaoCB.setForeground(new Color(0, 0, 128));
		localizacaoCB.setBounds(272, 116, 152, 23);
		contentPane.add(localizacaoCB);
		localizacaoCB.setModel(new DefaultComboBoxModel(new String[] {"ESTANTE_1A", "ESTANTE_1B", "ESTANTE_2A", "ESTANTE_2B", "ESTANTE_3A", "ESTANTE_3B", "ESTANTE_4", "ESTANTE_5", "ESTANTE_6", "ESTANTE_7", "ESTANTE_8", "ESTANTE_9", "ESTANTE_10", "ESTANTE_11", "ESTANTE_12", "ESTANTE_13", "ESTANTE_14", "ESTANTE_15", "ESTANTE_16", "ESTANTE_17", "ESTANTE_18", "ESTANTE_19", "ESTANTE_20", "ARMARIO_1", "ARMARIO_2", "ARMARIO_3", "ARMARIO_4", "ARMARIO_5", "ARMARIO_6", "ARMARIO_7", "DEPOSITO"}));

	}
}
