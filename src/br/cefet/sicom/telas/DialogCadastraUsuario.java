package br.cefet.sicom.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.cefet.sicom.dao.Conexao;
import br.cefet.sicom.dao.UsuarioDao;
import br.cefet.sicom.modelo.TipoUsuario;
import br.cefet.sicom.modelo.Usuario;
import br.cefet.sicom.sessao.Login;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPasswordField;

public class DialogCadastraUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nomeTF;
	private JTextField siapeTF;
	private JPasswordField senhaPF;
	private JPasswordField confirmaSenhaPF;

	public DialogCadastraUsuario(Login l) {
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setForeground(new Color(255, 255, 255));
		setBounds(100, 100, 401, 308);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setForeground(new Color(0, 0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCadastroDeUsurio = new JLabel("Cadastro de Usuário");
			lblCadastroDeUsurio.setForeground(new Color(0, 0, 139));
			lblCadastroDeUsurio.setFont(new Font("Calibri", Font.PLAIN, 23));
			lblCadastroDeUsurio.setBounds(10, 11, 223, 23);
			contentPanel.add(lblCadastroDeUsurio);
		}
		{
			JLabel lblNome = new JLabel("Nome:");
			lblNome.setForeground(new Color(0, 0, 128));
			lblNome.setBounds(50, 75, 46, 14);
			contentPanel.add(lblNome);
		}
		
		nomeTF = new JTextField();
		nomeTF.setForeground(new Color(0, 0, 128));
		nomeTF.setBounds(115, 72, 229, 20);
		contentPanel.add(nomeTF);
		nomeTF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Siape:");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBounds(50, 100, 46, 14);
		contentPanel.add(lblNewLabel);
		
		siapeTF = new JTextField();
		siapeTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String caracteres="0987654321";
				if(!caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}
				if(siapeTF.getText().length() > 8){
					e.consume();
				}
				
				
				
			}
		});
		siapeTF.setForeground(new Color(0, 0, 128));
		siapeTF.setBounds(115, 97, 229, 20);
		contentPanel.add(siapeTF);
		siapeTF.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo:");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setBounds(60, 125, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		JComboBox tipoCB = new JComboBox();
		tipoCB.setForeground(new Color(0, 0, 128));
		tipoCB.setModel(new DefaultComboBoxModel(new String[] {"COMUM", "ADM"}));
		tipoCB.setBounds(115, 122, 229, 20);
		contentPanel.add(tipoCB);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(0, 0, 128));
		lblSenha.setBounds(50, 150, 46, 14);
		contentPanel.add(lblSenha);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar:");
		lblConfirmarSenha.setForeground(new Color(0, 0, 128));
		lblConfirmarSenha.setBounds(34, 175, 81, 14);
		contentPanel.add(lblConfirmarSenha);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBackground(new Color(255, 255, 255));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				dispose();
			
			}
		});
		btnFechar.setForeground(new Color(0, 0, 128));
		btnFechar.setBounds(10, 238, 89, 23);
		contentPanel.add(btnFechar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(new Color(255, 255, 255));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(Conexao.isConectado()){
					if(!nomeTF.getText().isEmpty() && !siapeTF.getText().isEmpty() && !senhaPF.getPassword().toString().isEmpty() && !confirmaSenhaPF.getPassword().toString().isEmpty() && tipoCB.getSelectedIndex() != -1){
						if(new String(senhaPF.getPassword()).equals(new String(confirmaSenhaPF.getPassword()))){
							
														
							Usuario u = new Usuario(0, nomeTF.getText(), Integer.parseInt(siapeTF.getText()), senhaPF.getPassword().toString(), TipoUsuario.valueOf(tipoCB.getSelectedItem().toString()));
							UsuarioDao.cadastrar(u);
							
							Calendar c = new GregorianCalendar();
							//Movimentacao mov = new Movimentacao(0, TipoDeAcao.CADASTRO_DE_USUARIO, u.getNome()+"|"+u.getSiape(), c, l.getUsuario());
							//MovimentacaoDao.cadastrar(mov);
							
							JOptionPane.showMessageDialog(null, "Cadastro realizado!");
							dispose();
							
						}
						else{
							JOptionPane.showMessageDialog(null, "Os campos Senha e Confirmação devem ser iguais!");
						}
						
					}
					else{
						JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Não há conexão com o banco de dados!");
				}
					
				
			}
		});
		btnCadastrar.setForeground(new Color(0, 0, 128));
		btnCadastrar.setBounds(270, 238, 104, 23);
		contentPanel.add(btnCadastrar);
		
		senhaPF = new JPasswordField();
		senhaPF.setForeground(new Color(0, 0, 128));
		senhaPF.setBounds(115, 147, 229, 20);
		contentPanel.add(senhaPF);
		
		confirmaSenhaPF = new JPasswordField();
		confirmaSenhaPF.setForeground(new Color(0, 0, 128));
		confirmaSenhaPF.setBounds(115, 172, 229, 20);
		contentPanel.add(confirmaSenhaPF);
	}
}
