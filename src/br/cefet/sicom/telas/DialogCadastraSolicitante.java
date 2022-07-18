package br.cefet.sicom.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import br.cefet.sicom.dao.SolicitanteDao;
import br.cefet.sicom.modelo.Material;
import br.cefet.sicom.modelo.Solicitante;
import br.cefet.sicom.sessao.Login;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DialogCadastraSolicitante extends JDialog {

	private JPanel contentPane;
	private JTextField nomeTF;
	private JTextField setorTF;
	private JTextField ocupacaoTF;
	private JTextField siapeTF;

	
	public DialogCadastraSolicitante(JComboBox solicitanteCB, Login l) {
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 475, 243);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroDeSolicitante = new JLabel("Cadastro de Solicitante");
		lblCadastroDeSolicitante.setForeground(new Color(0, 0, 139));
		lblCadastroDeSolicitante.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblCadastroDeSolicitante.setBounds(10, 11, 223, 23);
		contentPane.add(lblCadastroDeSolicitante);
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setForeground(new Color(0, 0, 128));
		lblNomeCompleto.setBounds(10, 66, 113, 14);
		contentPane.add(lblNomeCompleto);
		
		nomeTF = new JTextField();
		nomeTF.setForeground(new Color(0, 0, 128));
		nomeTF.setBounds(123, 64, 328, 17);
		contentPane.add(nomeTF);
		nomeTF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Setor:");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBounds(59, 91, 64, 14);
		contentPane.add(lblNewLabel);
		
		setorTF = new JTextField();
		setorTF.setForeground(new Color(0, 0, 128));
		setorTF.setBounds(123, 89, 328, 17);
		contentPane.add(setorTF);
		setorTF.setColumns(10);
		
		JLabel lblOcupao = new JLabel("Ocupação:");
		lblOcupao.setForeground(new Color(0, 0, 128));
		lblOcupao.setBounds(37, 116, 86, 14);
		contentPane.add(lblOcupao);
		
		ocupacaoTF = new JTextField();
		ocupacaoTF.setForeground(new Color(0, 0, 128));
		ocupacaoTF.setBounds(123, 114, 328, 17);
		contentPane.add(ocupacaoTF);
		ocupacaoTF.setColumns(10);
		
		siapeTF = new JTextField();
		siapeTF.setForeground(new Color(0, 0, 128));
		siapeTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				String caracteres="0987654321";
				if(!caracteres.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
				if(siapeTF.getText().length() > 8){
					arg0.consume();
				}
				
			}
		});
		siapeTF.setBounds(123, 138, 328, 17);
		contentPane.add(siapeTF);
		siapeTF.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(Color.WHITE);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!nomeTF.getText().isEmpty() && !setorTF.getText().isEmpty() && !ocupacaoTF.getText().isEmpty() && !siapeTF.getText().isEmpty()){
					
					boolean existente = false;
					for(Solicitante so : SolicitanteDao.listarTodos()){
						if(so.getSiape() == Integer.parseInt(siapeTF.getText())){
							existente = true;
						}
					}
					
					
					if(!existente){
					
						Solicitante s = new Solicitante(0, nomeTF.getText(), setorTF.getText(), ocupacaoTF.getText(), Integer.parseInt(siapeTF.getText()),false);
						SolicitanteDao.cadastrar(s);
						
						Calendar c = new GregorianCalendar();
						//Movimentacao mov = new Movimentacao(0, TipoDeAcao.CADASTRO_DE_SOLICITANTE, s.getNome()+"|"+s.getSiape(), c, l.getUsuario());
						//MovimentacaoDao.cadastrar(mov);						
						
						DefaultComboBoxModel<Solicitante> dcbm = new DefaultComboBoxModel<>();
																	
						ArrayList<Solicitante> solicitantes = SolicitanteDao.listarTodos();
						for(Solicitante s2 : solicitantes){
							dcbm.addElement(s2);
						}
						
						solicitanteCB.setModel(dcbm);
						
						JOptionPane.showMessageDialog(null, "Cadastro Realizado!");
						dispose();
						
					}
					else{
						
						JOptionPane.showMessageDialog(null, "Solicitante já existe nos registros!");
						
					}
					
				}
				else{
					
					JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!");
					
				}
				
			}
		});
		btnCadastrar.setForeground(new Color(0, 0, 128));
		btnCadastrar.setBounds(10, 175, 113, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBackground(Color.WHITE);
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFechar.setForeground(new Color(0, 0, 128));
		btnFechar.setBounds(338, 175, 113, 23);
		contentPane.add(btnFechar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 162, 461, 2);
		contentPane.add(separator);
		
		JLabel lblSiape = new JLabel("Siape:");
		lblSiape.setForeground(new Color(0, 0, 128));
		lblSiape.setBounds(59, 141, 46, 14);
		contentPane.add(lblSiape);
		
	
	}
	
}
