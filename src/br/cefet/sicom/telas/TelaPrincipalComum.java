package br.cefet.sicom.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.cefet.sicom.sessao.Login;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TelaPrincipalComum extends JFrame {

	private JPanel contentPane;
	
	public TelaPrincipalComum(Login l) {
		setTitle("SICOM");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 370, 310);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCM = new JButton("Controle de Materiais");
		btnCM.setBackground(new Color(255, 255, 255));
		btnCM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DialogControleMaterial dcm = new DialogControleMaterial(l);
				dcm.setVisible(true);
				
			}
		});
		btnCM.setForeground(new Color(0, 0, 128));
		btnCM.setBounds(10, 192, 336, 35);
		contentPane.add(btnCM);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setForeground(new Color(0, 0, 128));
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsurio.setBounds(10, 117, 83, 14);
		contentPane.add(lblUsurio);
		
		JLabel lblSiape = new JLabel("Siape:");
		lblSiape.setForeground(new Color(0, 0, 128));
		lblSiape.setBounds(36, 140, 46, 14);
		contentPane.add(lblSiape);
		
		JButton btnSair = new JButton("Encerrar Sess\u00E3o");
		btnSair.setBackground(new Color(255, 255, 255));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				TelaLogin tdl = new TelaLogin();
				tdl.setVisible(true);
				
			}
		});
		btnSair.setForeground(new Color(0, 0, 128));
		btnSair.setBounds(10, 238, 336, 23);
		contentPane.add(btnSair);
		
		JLabel lblNome = new JLabel(l.getUsuario().getNome());
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setForeground(new Color(0, 0, 128));
		lblNome.setBounds(86, 117, 260, 14);
		contentPane.add(lblNome);
		
		JLabel lblSiape1 = new JLabel(String.valueOf(l.getUsuario().getSiape()));
		lblSiape1.setForeground(new Color(0, 0, 128));
		lblSiape1.setBounds(85, 140, 261, 14);
		contentPane.add(lblSiape1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipalComum.class.getResource("/br/cefet/sicom/img/logoCEFET.jpg")));
		lblNewLabel.setBounds(137, 11, 76, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblSistemaDeControle = new JLabel("Sistema de Controle dos Materiais de Consumo");
		lblSistemaDeControle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSistemaDeControle.setForeground(new Color(0, 0, 128));
		lblSistemaDeControle.setBounds(36, 73, 336, 14);
		contentPane.add(lblSistemaDeControle);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
