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

public class DialogRelPeriodoSolicitacao extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogRelPeriodoSolicitacao dialog = new DialogRelPeriodoSolicitacao();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogRelPeriodoSolicitacao() {
		setResizable(false);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblRelatrioDeSolicitaes = new JLabel("Relat\u00F3rio de Solicita\u00E7\u00F5es Por Per\u00EDodo");
		lblRelatrioDeSolicitaes.setForeground(new Color(0, 0, 139));
		lblRelatrioDeSolicitaes.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblRelatrioDeSolicitaes.setBounds(10, 11, 424, 23);
		getContentPane().add(lblRelatrioDeSolicitaes);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	}

}
