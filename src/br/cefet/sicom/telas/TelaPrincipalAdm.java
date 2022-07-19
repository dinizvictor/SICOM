package br.cefet.sicom.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.cefet.sicom.sessao.Login;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class TelaPrincipalAdm extends JFrame {

	private JPanel contentPane;

	public TelaPrincipalAdm(Login l) {
		setTitle("SICOM");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 361, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 128));
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 348, 413);
		contentPane.add(panel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/br/cefet/sicom/img/logoCEFET.jpg")));
		label.setBounds(134, 11, 76, 51);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Usuário:");
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(10, 117, 83, 14);
		panel.add(label_1);
		
		JLabel label_3 = new JLabel("Siape:");
		label_3.setForeground(new Color(0, 0, 128));
		label_3.setBounds(36, 140, 46, 14);
		panel.add(label_3);
		
		JButton btnControle = new JButton("Controle de Materiais");
		btnControle.setBackground(new Color(255, 255, 255));
		btnControle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DialogControleMaterial dcm = new DialogControleMaterial(l);
				dcm.setVisible(true);
				
			}
		});
		btnControle.setForeground(new Color(0, 0, 128));
		btnControle.setBounds(10, 192, 328, 35);
		panel.add(btnControle);
		
		JButton btnCadastrarUsurios = new JButton("Cadastrar Usuários");
		btnCadastrarUsurios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DialogCadastraUsuario dcu = new DialogCadastraUsuario(l);
				dcu.setVisible(true);
				
			}
		});
		btnCadastrarUsurios.setBackground(new Color(255, 255, 255));
		btnCadastrarUsurios.setForeground(new Color(0, 0, 128));
		btnCadastrarUsurios.setBounds(10, 235, 328, 35);
		panel.add(btnCadastrarUsurios);
		
		JLabel lbNome = new JLabel(l.getUsuario().getNome());
		lbNome.setForeground(new Color(0, 0, 128));
		lbNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNome.setBounds(76, 117, 260, 14);
		panel.add(lbNome);
		
		JLabel lbSiape = new JLabel(String.valueOf(l.getUsuario().getSiape()));
		lbSiape.setForeground(new Color(0, 0, 128));
		lbSiape.setBounds(75, 140, 261, 14);
		panel.add(lbSiape);
		
		JLabel label_2 = new JLabel("Sistema de Controle dos Materiais de Consumo");
		label_2.setForeground(new Color(0, 0, 128));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(28, 73, 336, 14);
		panel.add(label_2);
		
		JButton btnRelatrios = new JButton("Relatórios");
		btnRelatrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRelatrios.setBackground(new Color(255, 255, 255));
		btnRelatrios.setForeground(new Color(0, 0, 128));
		btnRelatrios.setBounds(10, 281, 328, 35);
		panel.add(btnRelatrios);
		
		JButton btnSair = new JButton("Encerrar Sessão");
		btnSair.setBounds(10, 376, 328, 23);
		panel.add(btnSair);
		btnSair.setBackground(new Color(255, 255, 255));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				TelaLogin tdl = new TelaLogin();
				tdl.setVisible(true);
				
			}
		});
		btnSair.setForeground(new Color(0, 0, 128));
	}
}
