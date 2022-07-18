package br.cefet.sicom.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.cefet.sicom.dao.Conexao;
import br.cefet.sicom.dao.UsuarioDao;
import br.cefet.sicom.modelo.TipoUsuario;
import br.cefet.sicom.modelo.Usuario;
import br.cefet.sicom.sessao.Login;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField TFLogin;
	private JPasswordField PFSenha;
	private final JSeparator separator = new JSeparator();
	private JLabel labelBD;
	private JButton btnLogin;
	
	public TelaLogin() {
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("SICOM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 232, 384);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSepat = new JLabel("SICOM - Controle de Materiais");
		lblSepat.setForeground(new Color(0, 0, 128));
		lblSepat.setBounds(20, 53, 177, 14);
		contentPane.add(lblSepat);
		
		JLabel lblLogin = new JLabel("Siape:");
		lblLogin.setForeground(new Color(0, 0, 128));
		lblLogin.setBounds(10, 174, 46, 14);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(0, 0, 128));
		lblSenha.setBounds(10, 200, 46, 14);
		contentPane.add(lblSenha);
		
		TFLogin = new JTextField();
		TFLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				String caracteres="0987654321";
				if(!caracteres.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
				if(TFLogin.getText().length() > 8){
					arg0.consume();
				}
				
			}
		});
		TFLogin.setForeground(new Color(0, 0, 128));
		TFLogin.setBounds(53, 171, 153, 20);
		contentPane.add(TFLogin);
		TFLogin.setColumns(10);
		
		PFSenha = new JPasswordField();
		PFSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					
					if(Conexao.isConectado()){
						if(!TFLogin.getText().isEmpty() && !PFSenha.getPassword().toString().isEmpty()){
							
							Usuario u = UsuarioDao.autenticar(Integer.parseInt(TFLogin.getText()), PFSenha.getPassword());
							PFSenha.setText("");
							TFLogin.setText("");
							if(u != null){
								
								Calendar c = new GregorianCalendar();
								Login l = new Login(u,c);
								dispose();
								if(u.getTipo() == TipoUsuario.ADM){
									
									JOptionPane.showMessageDialog(null, "Login realizado!");
									TelaPrincipalAdm tpa = new TelaPrincipalAdm(l);
									tpa.setVisible(true);
									dispose();
									
								}
								else if(u.getTipo() == TipoUsuario.COMUM){
									
									JOptionPane.showMessageDialog(null, "Login realizado!");
									TelaPrincipalComum tpc = new TelaPrincipalComum(l);
									tpc.setVisible(true);
									dispose();
									
								}
								
								
							}
							else{
								JOptionPane.showMessageDialog(null, "Siape ou Senha incorretos!");
							}
						
						}
						else{
							JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
						}
						
					}
					else{
						JOptionPane.showMessageDialog(null, "Sem conexão com o banco de dados!");
					}
					
				}
				
			}
		});
		PFSenha.setForeground(new Color(0, 0, 128));
		PFSenha.setBounds(53, 197, 153, 20);
		contentPane.add(PFSenha);
		
		btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(0, 0, 128));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBackground(Color.WHITE);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(Conexao.isConectado()){
					if(!TFLogin.getText().isEmpty() && !PFSenha.getPassword().toString().isEmpty()){
						
						Usuario u = UsuarioDao.autenticar(Integer.parseInt(TFLogin.getText()), PFSenha.getPassword());
						PFSenha.setText("");
						TFLogin.setText("");
						if(u != null){
							
							Calendar c = new GregorianCalendar();
							Login l = new Login(u,c);
							dispose();
							if(u.getTipo() == TipoUsuario.ADM){
								
								JOptionPane.showMessageDialog(null, "Login realizado!");
								TelaPrincipalAdm tpa = new TelaPrincipalAdm(l);
								tpa.setVisible(true);
								dispose();
								
							}
							else if(u.getTipo() == TipoUsuario.COMUM){
								
								JOptionPane.showMessageDialog(null, "Login realizado!");
								TelaPrincipalComum tpc = new TelaPrincipalComum(l);
								tpc.setVisible(true);
								dispose();
								
							}
							
							
						}
						else{
							JOptionPane.showMessageDialog(null, "Siape ou Senha incorretos!");
						}
					
					}
					else{
						JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
					}
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Sem conex�o com o banco de dados!");
				}
				
			}
		});
		btnLogin.setBounds(53, 225, 153, 23);
		contentPane.add(btnLogin);
		
		JLabel lblCefetrjUned = new JLabel("CEFET/RJ - UnED N.I.");
		lblCefetrjUned.setForeground(new Color(0, 0, 139));
		lblCefetrjUned.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblCefetrjUned.setBounds(10, 11, 228, 23);
		contentPane.add(lblCefetrjUned);
		
		labelBD = new JLabel("");
		if(Conexao.isConectado()){
			labelBD.setIcon(new ImageIcon(getClass().getResource("/br/cefet/sicom/img/bdgood.png")));
		}
		else{
			labelBD.setIcon(new ImageIcon(getClass().getResource("/br/cefet/sicom/img/bdbad.png")));
		}
		labelBD.setBounds(86, 78, 46, 58);
		contentPane.add(labelBD);
		separator.setBounds(0, 144, 214, 2);
		contentPane.add(separator);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnFechar.setBackground(new Color(255, 255, 255));
		btnFechar.setForeground(new Color(0, 0, 128));
		btnFechar.setBounds(117, 302, 89, 23);
		contentPane.add(btnFechar);
	}
}
