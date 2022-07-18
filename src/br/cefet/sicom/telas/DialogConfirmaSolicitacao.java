package br.cefet.sicom.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.cefet.sicom.dao.MaterialDao;
import br.cefet.sicom.dao.SolicitacaoDao;
import br.cefet.sicom.documentos.GeradorDeTexto;
import br.cefet.sicom.modelo.Material;
import br.cefet.sicom.modelo.Solicitacao;
import br.cefet.sicom.modelo.Solicitante;
import br.cefet.sicom.modelo.TipoDeAcao;
import br.cefet.sicom.sessao.Login;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class DialogConfirmaSolicitacao extends JDialog {

	private final JPanel contentPanel = new JPanel();
		
		
	public void refreshListSolicitacao(ArrayList<Solicitacao> solicitacao, JList list){
		
		DefaultListModel model = new DefaultListModel();
		for(Solicitacao soli : solicitacao){
			
			model.addElement(soli);
		
		}
		
		list.setModel(model);
		
	}
	
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
			
	public DialogConfirmaSolicitacao(ArrayList<Solicitacao> solicitacao, Solicitante solicitante, JList listLM, JComboBox ordenarCB, JTextField pesquisaTF, Object [][] objetos, JTable materiaisTable, Login l) {
		setModal(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBackground(Color.WHITE);
		setResizable(false);
		setBounds(100, 100, 516, 542);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(new Color(0, 0, 128));
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblConfirmaoDeSolicitao = new JLabel("Confirma\u00E7\u00E3o de Solicita\u00E7\u00E3o");
		lblConfirmaoDeSolicitao.setForeground(new Color(0, 0, 139));
		lblConfirmaoDeSolicitao.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblConfirmaoDeSolicitao.setBounds(10, 11, 347, 23);
		contentPanel.add(lblConfirmaoDeSolicitao);
		
		JLabel lblMateriaisSolicitados = new JLabel("Materiais Solicitados:");
		lblMateriaisSolicitados.setForeground(new Color(0, 0, 128));
		lblMateriaisSolicitados.setBounds(10, 120, 129, 14);
		contentPanel.add(lblMateriaisSolicitados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 479, 249);
		contentPanel.add(scrollPane);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setForeground(new Color(0, 0, 128));
		scrollPane.setViewportView(list);
		
		refreshListSolicitacao(solicitacao, list);
		
		JLabel lblSolicitante = new JLabel("Solicitante:");
		lblSolicitante.setForeground(new Color(0, 0, 128));
		lblSolicitante.setBounds(10, 45, 74, 14);
		contentPanel.add(lblSolicitante);
		
		JLabel lblNome = new JLabel(solicitante.getNome());
		lblNome.setForeground(new Color(0, 0, 128));
		lblNome.setBounds(78, 45, 411, 14);
		contentPanel.add(lblNome);
		
		JLabel lblNewLabel = new JLabel("Setor:");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBounds(10, 70, 46, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblOcupao = new JLabel("Ocupa\u00E7\u00E3o:");
		lblOcupao.setForeground(new Color(0, 0, 128));
		lblOcupao.setBounds(10, 95, 74, 14);
		contentPanel.add(lblOcupao);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setBounds(388, 470, 101, 23);
		contentPanel.add(btnNewButton);
		
		JButton btnConfirmar = new JButton("Confirmar e Gerar Comprovante");
		btnConfirmar.setBackground(new Color(255, 255, 255));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Calendar calendar = new GregorianCalendar();
				
				for(Solicitacao s : solicitacao){
					
					s.setData(calendar);
					SolicitacaoDao.cadastrar(s);
														
					MaterialDao.atualizar(s.getMaterial());
										
				}
				
				Calendar c = new GregorianCalendar();
				//Movimentacao mov = new Movimentacao(0, TipoDeAcao.LIBERACAO_DE_MATERIAL, "Solicitante: "+solicitacao.get(0).getSolicitante(), calendar, l.getUsuario());
				//MovimentacaoDao.cadastrar(mov);
				
				GeradorDeTexto.gerarComprovanteSolicitacao(solicitacao, solicitante, calendar, l.getUsuario());
				try {
					
					java.awt.Desktop.getDesktop().open(new File(GeradorDeTexto.getEndereco(solicitante, calendar)));
										
				
				} catch (IOException e1) {
					
					JOptionPane.showMessageDialog(null, e1);
				
				}
				
				solicitacao.clear();
				DefaultListModel model = new DefaultListModel();
				listLM.setModel(model);
				
				ArrayList<Material> materiais = new ArrayList<Material>();
				refreshTable(ordenarCB, materiais, pesquisaTF, objetos, materiaisTable);
				
								
				dispose();
				
								
				
			}
		});
		btnConfirmar.setForeground(new Color(0, 0, 128));
		btnConfirmar.setBounds(120, 400, 254, 23);
		contentPanel.add(btnConfirmar);
		
		JLabel lblSetor = new JLabel(solicitante.getSetor());
		lblSetor.setForeground(new Color(0, 0, 128));
		lblSetor.setBounds(51, 70, 437, 14);
		contentPanel.add(lblSetor);
		
		JLabel lblOcupacao = new JLabel(solicitante.getOcupacao());
		lblOcupacao.setForeground(new Color(0, 0, 128));
		lblOcupacao.setBounds(78, 95, 410, 14);
		contentPanel.add(lblOcupacao);
	}
	
	
}
