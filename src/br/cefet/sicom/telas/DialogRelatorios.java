package br.cefet.sicom.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogRelatorios extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public DialogRelatorios() {
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 290, 444);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblRelatrios = new JLabel("Relat\u00F3rios");
		lblRelatrios.setForeground(new Color(0, 0, 139));
		lblRelatrios.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblRelatrios.setBounds(10, 11, 326, 23);
		contentPanel.add(lblRelatrios);
		
		JButton btnPeriodo = new JButton("Por Per\u00EDodo");
		btnPeriodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
			}
		});
		btnPeriodo.setForeground(new Color(0, 0, 128));
		btnPeriodo.setBackground(new Color(255, 255, 255));
		btnPeriodo.setBounds(10, 93, 254, 23);
		contentPanel.add(btnPeriodo);
		
		JButton btnSolicitante = new JButton("Por Solicitante");
		btnSolicitante.setForeground(new Color(0, 0, 128));
		btnSolicitante.setBackground(new Color(255, 255, 255));
		btnSolicitante.setBounds(10, 126, 254, 23);
		contentPanel.add(btnSolicitante);
		
		JButton btnCategoria = new JButton("Por Categoria");
		btnCategoria.setForeground(new Color(0, 0, 128));
		btnCategoria.setBackground(new Color(255, 255, 255));
		btnCategoria.setBounds(10, 160, 254, 23);
		contentPanel.add(btnCategoria);
		
		JButton btnLiberaoDeMaterial = new JButton("Por Usu\u00E1rio");
		btnLiberaoDeMaterial.setForeground(new Color(0, 0, 128));
		btnLiberaoDeMaterial.setBackground(new Color(255, 255, 255));
		btnLiberaoDeMaterial.setBounds(10, 194, 254, 23);
		contentPanel.add(btnLiberaoDeMaterial);
		
		JButton btnMateriaisCadastrados = new JButton("Relat\u00F3rio Geral");
		btnMateriaisCadastrados.setForeground(new Color(0, 0, 128));
		btnMateriaisCadastrados.setBackground(new Color(255, 255, 255));
		btnMateriaisCadastrados.setBounds(10, 253, 254, 23);
		contentPanel.add(btnMateriaisCadastrados);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBackground(new Color(255, 255, 255));
		btnFechar.setForeground(new Color(0, 0, 128));
		btnFechar.setBounds(10, 384, 254, 23);
		contentPanel.add(btnFechar);
		
		JLabel lblSolicitaes = new JLabel("Solicita\u00E7\u00F5es:");
		lblSolicitaes.setForeground(new Color(0, 0, 128));
		lblSolicitaes.setBounds(10, 68, 110, 14);
		contentPanel.add(lblSolicitaes);
		
		JLabel lblMateriais = new JLabel("Materiais:");
		lblMateriais.setForeground(new Color(0, 0, 128));
		lblMateriais.setBounds(10, 228, 110, 14);
		contentPanel.add(lblMateriais);
		
		JButton btnPorCategoria = new JButton("Por Categoria");
		btnPorCategoria.setForeground(new Color(0, 0, 128));
		btnPorCategoria.setBackground(Color.WHITE);
		btnPorCategoria.setBounds(10, 287, 254, 23);
		contentPanel.add(btnPorCategoria);
		
		JButton btnEntradas = new JButton("Entradas");
		btnEntradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEntradas.setForeground(new Color(0, 0, 128));
		btnEntradas.setBackground(Color.WHITE);
		btnEntradas.setBounds(10, 321, 254, 23);
		contentPanel.add(btnEntradas);
	}
}
