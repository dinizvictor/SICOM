package br.cefet.sicom.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.cefet.sicom.dao.MaterialDao;
import br.cefet.sicom.dao.SolicitacaoDao;
import br.cefet.sicom.documentos.GeradorDeTexto;
import br.cefet.sicom.modelo.Material;
import br.cefet.sicom.modelo.Solicitacao;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
		
		JLabel lblRelatrios = new JLabel("Relatórios");
		lblRelatrios.setForeground(new Color(0, 0, 139));
		lblRelatrios.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblRelatrios.setBounds(10, 11, 326, 23);
		contentPanel.add(lblRelatrios);
		
		JButton btnPeriodo = new JButton("Por Período");
		btnPeriodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
			}
		});
		btnPeriodo.setForeground(new Color(0, 0, 128));
		btnPeriodo.setBackground(new Color(255, 255, 255));
		btnPeriodo.setBounds(10, 100, 254, 23);
		contentPanel.add(btnPeriodo);
		
		JButton btnSolicitante = new JButton("Por Solicitante");
		btnSolicitante.setForeground(new Color(0, 0, 128));
		btnSolicitante.setBackground(new Color(255, 255, 255));
		btnSolicitante.setBounds(10, 134, 254, 23);
		contentPanel.add(btnSolicitante);
		
		JButton btnCategoria = new JButton("Por Categoria");
		btnCategoria.setForeground(new Color(0, 0, 128));
		btnCategoria.setBackground(new Color(255, 255, 255));
		btnCategoria.setBounds(10, 168, 254, 23);
		contentPanel.add(btnCategoria);
		
		JButton btnLiberaoDeMaterial = new JButton("Por Usuário");
		btnLiberaoDeMaterial.setForeground(new Color(0, 0, 128));
		btnLiberaoDeMaterial.setBackground(new Color(255, 255, 255));
		btnLiberaoDeMaterial.setBounds(10, 202, 254, 23);
		contentPanel.add(btnLiberaoDeMaterial);
		
		JButton btnMateriaisCadastrados = new JButton("Relatório Geral");
		btnMateriaisCadastrados.setForeground(new Color(0, 0, 128));
		btnMateriaisCadastrados.setBackground(new Color(255, 255, 255));
		btnMateriaisCadastrados.setBounds(10, 261, 254, 23);
		contentPanel.add(btnMateriaisCadastrados);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBackground(new Color(255, 255, 255));
		btnFechar.setForeground(new Color(0, 0, 128));
		btnFechar.setBounds(10, 384, 254, 23);
		contentPanel.add(btnFechar);
		
		JLabel lblSolicitaes = new JLabel("Solicitações:");
		lblSolicitaes.setForeground(new Color(0, 0, 128));
		lblSolicitaes.setBounds(10, 45, 110, 14);
		contentPanel.add(lblSolicitaes);
		
		JLabel lblMateriais = new JLabel("Materiais:");
		lblMateriais.setForeground(new Color(0, 0, 128));
		lblMateriais.setBounds(10, 236, 110, 14);
		contentPanel.add(lblMateriais);
		
		JButton btnPorCategoria = new JButton("Por Categoria");
		btnPorCategoria.setForeground(new Color(0, 0, 128));
		btnPorCategoria.setBackground(Color.WHITE);
		btnPorCategoria.setBounds(10, 295, 254, 23);
		contentPanel.add(btnPorCategoria);
		
		JButton btnEntradas = new JButton("Entradas");
		btnEntradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEntradas.setForeground(new Color(0, 0, 128));
		btnEntradas.setBackground(Color.WHITE);
		btnEntradas.setBounds(10, 329, 254, 23);
		contentPanel.add(btnEntradas);
		
		JButton btnMateriaisCadastrados_1 = new JButton("Relatório Geral");
		btnMateriaisCadastrados_1.setForeground(new Color(0, 0, 128));
		btnMateriaisCadastrados_1.setBackground(Color.WHITE);
		btnMateriaisCadastrados_1.setBounds(10, 66, 254, 23);
		contentPanel.add(btnMateriaisCadastrados_1);
	}
}
