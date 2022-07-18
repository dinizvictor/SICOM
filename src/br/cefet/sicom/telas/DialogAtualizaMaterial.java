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
import br.cefet.sicom.modelo.TipoDeAcao;
import br.cefet.sicom.modelo.Usuario;
import br.cefet.sicom.sessao.Login;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DialogAtualizaMaterial extends JDialog {

	JPanel contentPane;
	JTextField descricaoTF;
	JTextField saldoTF;
	JComboBox<String> tipoUnidCB;
	JComboBox<String> localizacaoCB;
	JComboBox<String> categoriaCB;
	
		
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
	
	public DialogAtualizaMaterial(JComboBox ordenarCBTC, ArrayList<Material> materiaisTC, JTextField pesquisaTFTC, Object [][] objetosTC, JTable materiaisTableTC, Login l) {
		setLocationRelativeTo(null);
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 248);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAtualizaoDeMateriais = new JLabel("Atualiza\u00E7\u00E3o de Materiais");
		lblAtualizaoDeMateriais.setForeground(new Color(0, 0, 139));
		lblAtualizaoDeMateriais.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblAtualizaoDeMateriais.setBounds(10, 11, 414, 23);
		contentPane.add(lblAtualizaoDeMateriais);
		
		JLabel label_1 = new JLabel("Descri\u00E7\u00E3o:");
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setBounds(10, 45, 61, 14);
		contentPane.add(label_1);
		
		descricaoTF = new JTextField();
		descricaoTF.setForeground(new Color(0, 0, 128));
		descricaoTF.setColumns(10);
		descricaoTF.setBounds(10, 61, 414, 20);
		contentPane.add(descricaoTF);
		
		JLabel label_2 = new JLabel("Tipo de Unidade:");
		label_2.setForeground(new Color(0, 0, 128));
		label_2.setBounds(10, 92, 95, 14);
		contentPane.add(label_2);
		
		tipoUnidCB = new JComboBox();
		tipoUnidCB.setForeground(new Color(0, 0, 128));
		tipoUnidCB.setBounds(114, 89, 69, 20);
		tipoUnidCB.setModel(new DefaultComboBoxModel(new String[] {"UNID", "CX", "PCT", "METRO", "RESMA", "ROLO"}));
		contentPane.add(tipoUnidCB);
		
		
		
		saldoTF = new JTextField();
		saldoTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String caracteres="0987654321";
				if(!caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}
				if(saldoTF.getText().length() > 8){
					e.consume();
				}
				
				
			}
		});
		saldoTF.setText("0");
		saldoTF.setForeground(new Color(0, 0, 128));
		saldoTF.setColumns(10);
		saldoTF.setBounds(114, 117, 69, 20);
		contentPane.add(saldoTF);
		
		JLabel label_3 = new JLabel("Saldo:");
		label_3.setForeground(new Color(0, 0, 128));
		label_3.setBounds(61, 120, 44, 14);
		contentPane.add(label_3);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 128));
		btnCancelar.setBounds(209, 179, 105, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBackground(Color.WHITE);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!descricaoTF.getText().isEmpty() && !saldoTF.getText().isEmpty() && tipoUnidCB.getSelectedIndex() != -1){	
					
					Material m = materiaisTC.get(materiaisTableTC.getSelectedRow());
					Material mCopia = m;
									
					m.setDescricao(descricaoTF.getText()); 
					m.setSaldo(Integer.parseInt(saldoTF.getText()));
					m.setTipoUnid(tipoUnidCB.getSelectedItem().toString());
					m.setCategoria(categoriaCB.getSelectedItem().toString());
					m.setLocalizacao(localizacaoCB.getSelectedItem().toString());
					
					MaterialDao.atualizar(m);
					Calendar c = new GregorianCalendar();
					//Movimentacao mov = new Movimentacao(0, TipoDeAcao.ATUALIZACAO_DE_MATERIAL, mCopia.getIdMaterial()+"|"+mCopia.getDescricao()+"|"+mCopia.getCategoria()+"|"+mCopia.getLocalizacao()+"|"+mCopia.getSaldo()+"|"+mCopia.getTipoUnid(), c, l.getUsuario());
					//MovimentacaoDao.cadastrar(mov);
					JOptionPane.showMessageDialog(null, "Atualiza��o Realizada!");
					
					refreshTable(ordenarCBTC, materiaisTC, pesquisaTFTC, objetosTC, materiaisTableTC);
					
					dispose();
					
				}
				
			}
		});
		btnAtualizar.setForeground(new Color(0, 0, 128));
		btnAtualizar.setBounds(319, 179, 105, 23);
		contentPane.add(btnAtualizar);
		
		categoriaCB = new JComboBox();
		categoriaCB.setForeground(new Color(0, 0, 128));
		categoriaCB.setBounds(272, 86, 152, 23);
		contentPane.add(categoriaCB);
		categoriaCB.setModel(new DefaultComboBoxModel(new String[] {"ME", "TONER", "CARTUCHO", "ALVENARIA", "ELETRICA", "HIDRAULICA", "OUTROS"}));
		
		JLabel label = new JLabel("Categoria:");
		label.setForeground(new Color(0, 0, 128));
		label.setBounds(193, 92, 84, 14);
		contentPane.add(label);
		
		JLabel label_4 = new JLabel("Localiza\u00E7\u00E3o:");
		label_4.setForeground(new Color(0, 0, 128));
		label_4.setBounds(193, 118, 84, 14);
		contentPane.add(label_4);
		
		localizacaoCB = new JComboBox();
		localizacaoCB.setForeground(new Color(0, 0, 128));
		localizacaoCB.setBounds(272, 114, 152, 23);
		localizacaoCB.setModel(new DefaultComboBoxModel(new String[] {"ESTANTE_1A", "ESTANTE_1B", "ESTANTE_2A", "ESTANTE_2B", "ESTANTE_3A", "ESTANTE_3B", "ESTANTE_4", "ESTANTE_5", "ESTANTE_6", "ESTANTE_7", "ESTANTE_8", "ESTANTE_9", "ESTANTE_10", "ESTANTE_11", "ESTANTE_12", "ESTANTE_13", "ESTANTE_14", "ESTANTE_15", "ESTANTE_16", "ESTANTE_17", "ESTANTE_18", "ESTANTE_19", "ESTANTE_20", "ARMARIO_1", "ARMARIO_2", "ARMARIO_3", "ARMARIO_4", "ARMARIO_5", "ARMARIO_6", "ARMARIO_7", "DEPOSITO"}));
		contentPane.add(localizacaoCB);
		
	}

}
