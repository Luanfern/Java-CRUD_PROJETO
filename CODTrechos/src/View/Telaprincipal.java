package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import model.Trecho;
import model.TrechoDAO;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class Telaprincipal extends JFrame {

	private JPanel contentPane;
	private JTextField Barrapesquisa;
	private JTextField Titulotxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Telaprincipal frame = new Telaprincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private boolean janelas = false;
	private JTextField titulotrecho;
	private JTextField linguagemtrecho;
	private String idatual; 
	int resultadots;
	
	public Telaprincipal() {
		super("Biblioteca de Trechos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Telaprincipal.class.getResource("/IMGS/logocodtrechos.png")));
		Container tela = getContentPane();
		setSize(1281,812);
		tela.setBackground(Color.white);
		getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		Titulotxt = new JTextField();
        JButton ListaTrechobtn = new JButton("Lista de Trechos");
		JButton NovoTrechobtn = new JButton("Novo Trecho");
		JPanel panel = new JPanel();
		JComboBox combolinguagem = new JComboBox();
		JButton btnCriarTrecho = new JButton("Criar Trecho");
		JPanel painel_novo = new JPanel();
		JLabel lblNewLabel_1 = new JLabel("");
		JLabel lblNewLabel_1_1 = new JLabel("");		JPanel Novo_Trecho = new JPanel();
		JPanel info_editor = new JPanel();
		JTabbedPane abascodigos = new JTabbedPane(JTabbedPane.TOP);
		JPanel panel_4 = new JPanel();
		JPanel panel_3 = new JPanel();
		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		Titulotxt = new JTextField();
		JPanel painel_lista = new JPanel();
		JLabel lblNewLabel = new JLabel("");
		Barrapesquisa = new JTextField();
		JComboBox linguagenspesquisa = new JComboBox();
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		JPanel visualizartrecho = new JPanel();
		JPanel panel_6 = new JPanel();
		JEditorPane codigotrecho = new JEditorPane();
		linguagemtrecho = new JTextField();
		titulotrecho = new JTextField();
		titulotrecho.setEditable(false);
		
		
		layeredPane.setBounds(462, 5, 797, 763);
		getContentPane().add(layeredPane);
		layeredPane.setLayout(null);
		JPanel Lista_Trechos = new JPanel();
		JScrollPane scrollpane = new JScrollPane();
		JTable tabela = new JTable() {
	         public boolean editCellAt(int row, int column, java.util.EventObject e) {
	             return false;
	          }
	       };
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = tabela.getSelectedRow();
				TableModel modelo = tabela.getModel();
				idatual = modelo.getValueAt(index, 0).toString();
				String titulo = modelo.getValueAt(index, 1).toString();
				String linguagem = modelo.getValueAt(index, 2).toString();
				
				TrechoDAO tdao = new TrechoDAO();
				tdao.pegarcod(titulo);
				String codi = tdao.pegarcodigo();
		 	    codigotrecho.setText(codi);
				
				layeredPane.removeAll();
				layeredPane.add(visualizartrecho);
				layeredPane.repaint();
				layeredPane.revalidate();
				titulotrecho.setText(titulo);
				linguagemtrecho.setText(linguagem);
				painel_novo.setVisible(false);
				painel_lista.setVisible(true);
				
				Barrapesquisa.setEnabled(false);
				linguagenspesquisa.setEnabled(false);
				
			}
		});
		
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		linguagenspesquisa.setModel(new DefaultComboBoxModel(new String[] {"Selecione a Linguagem...","JavaScript"
				, "Python"
				, "Java"
				, "PHP"
				, "C#"
				, "C++"
				, "Ruby"
				, "CSS"
				, "TypeScript"
				, "C"
				, "Swift"
				, "Objective-C"
				, "Scala"
				, "R"
				, "Go"
				, "Shell"
				, "PowerShell"
				, "Perl"
				, "Kotlin"
				, "Haskell", "Flutter", "Cordova"}));
		
		Lista_Trechos.setBackground(Color.WHITE);
		Lista_Trechos.setBounds(0, 0, 797, 763);
		layeredPane.add(Lista_Trechos);
		Lista_Trechos.setLayout(null);
		
        scrollpane.setBounds(0,0,797,763);
        Lista_Trechos.add(scrollpane);
        
         scrollpane.setViewportView(tabela);
         
         tabela.setModel(new DefaultTableModel(
         	new Object[][] {
         	},
         	new String[] {
         		"ID", "T\u00EDtulo", "Linguagem"
         	}
         ));
         tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
         tabela.getColumnModel().getColumn(1).setPreferredWidth(300);
         tabela.getColumnModel().getColumn(2).setPreferredWidth(200);
         
        
         //gerar linhas na tabela
         
         
         DefaultTableModel modelo = ( DefaultTableModel) tabela.getModel();
 	    modelo.setNumRows(0);
 	    TrechoDAO tdao = new TrechoDAO();
 	    
 	    for(Trecho t: tdao.listartrechos()) {
 	   	 
 	   	 modelo.addRow(new Object[] {
 	   			 t.getId(),
 	   			 t.getTitulo(),
 	   			 t.getLinguagem(),
 	   			 "BTN","BTN"
 	   	 });
 	   	 
 	    }				
				
				visualizartrecho.setBounds(0, 0, 797, 763);
				layeredPane.add(visualizartrecho);
				visualizartrecho.setLayout(null);
				
				codigotrecho.setBounds(0, 48, 797, 715);
				visualizartrecho.add(codigotrecho);
				
				panel_6.setBackground(Color.GRAY);
				panel_6.setBounds(0, 0, 797, 49);
				visualizartrecho.add(panel_6);
				panel_6.setLayout(null);
				
				JButton btnvoltarvisualizar = new JButton("");
				btnvoltarvisualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						lblNewLabel.setEnabled(true);
						Barrapesquisa.setEnabled(true);
						linguagenspesquisa.setEnabled(true);
						lblNewLabel_1_1_1.setEnabled(true);
						
						layeredPane.removeAll();
						layeredPane.add(Lista_Trechos);
						layeredPane.repaint();
						layeredPane.revalidate();
						painel_novo.setVisible(false);
						painel_lista.setVisible(true);
						
						DefaultTableModel modelo = ( DefaultTableModel) tabela.getModel();
				 	    modelo.setNumRows(0);
				 	    TrechoDAO tdao = new TrechoDAO();
				 	    
				 	    for(Trecho t: tdao.listartrechos()) {
				 	   	 
				 	   	 modelo.addRow(new Object[] {
				 	   			 t.getId(),
				 	   			 t.getTitulo(),
				 	   			 t.getLinguagem(),
				 	   	 });
				 	   	 
				 	    }
						
					}
				});
				btnvoltarvisualizar.setIcon(new ImageIcon(Telaprincipal.class.getResource("/IMGS/btnvoltar.png")));
				btnvoltarvisualizar.setBounds(0, 0, 81, 47);
				btnvoltarvisualizar.setFont(new Font("Tahoma", Font.BOLD, 13));
				btnvoltarvisualizar.setBorder(null);
				btnvoltarvisualizar.setBackground(Color.DARK_GRAY);
				panel_6.add(btnvoltarvisualizar);
				
				titulotrecho.setEditable(false);
				titulotrecho.setBounds(88, 11, 256, 27);
				titulotrecho.setForeground(Color.BLACK);
				titulotrecho.setFont(new Font("Tahoma", Font.BOLD, 13));
				titulotrecho.setHorizontalAlignment(SwingConstants.LEFT);
				panel_6.add(titulotrecho);
				titulotrecho.setColumns(10);
				
				linguagemtrecho.setEditable(false);
				linguagemtrecho.setHorizontalAlignment(SwingConstants.RIGHT);
				linguagemtrecho.setForeground(Color.BLACK);
				linguagemtrecho.setFont(new Font("Tahoma", Font.BOLD, 14));
				linguagemtrecho.setBounds(418, 11, 177, 27);
				panel_6.add(linguagemtrecho);
				linguagemtrecho.setColumns(10);
				
				JButton btnSalvaratt = new JButton();
				
				btnSalvaratt.setToolTipText("ALT + S = Salvar");
				btnSalvaratt.setMnemonic(KeyEvent.VK_S);
				
				btnSalvaratt.setText("<html>Salvar/<br>Atualizar</html>");
				btnSalvaratt.setIcon(new ImageIcon(Telaprincipal.class.getResource("/IMGS/salvarbtn.png")));
				btnSalvaratt.setFont(new Font("Tahoma", Font.BOLD, 13));
				btnSalvaratt.setBorder(null);
				btnSalvaratt.setBackground(Color.WHITE);
				btnSalvaratt.setBounds(672, 0, 125, 49);
				panel_6.add(btnSalvaratt);
				
				JButton btneditartitulotrecho = new JButton("");
				btneditartitulotrecho.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						titulotrecho.setEditable(true);
						
					}
				});
				btneditartitulotrecho.setIcon(new ImageIcon(Telaprincipal.class.getResource("/IMGS/lapisedit2.png")));
				btneditartitulotrecho.setFont(new Font("Tahoma", Font.BOLD, 13));
				btneditartitulotrecho.setBorder(null);
				btneditartitulotrecho.setBackground(Color.DARK_GRAY);
				btneditartitulotrecho.setBounds(354, 0, 54, 47);
				panel_6.add(btneditartitulotrecho);
				
				JButton btneditarlinguagemtrecho = new JButton("");
				btneditarlinguagemtrecho.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						linguagemtrecho.setEditable(true);
						
					}
				});
				btneditarlinguagemtrecho.setIcon(new ImageIcon(Telaprincipal.class.getResource("/IMGS/lapisedit2.png")));
				btneditarlinguagemtrecho.setFont(new Font("Tahoma", Font.BOLD, 13));
				btneditarlinguagemtrecho.setBorder(null);
				btneditarlinguagemtrecho.setBackground(Color.DARK_GRAY);
				btneditarlinguagemtrecho.setBounds(605, 0, 54, 47);
				panel_6.add(btneditarlinguagemtrecho);
 	    
		
		Novo_Trecho.setBackground(Color.WHITE);
		Novo_Trecho.setBounds(0, 0, 797, 763);
		layeredPane.add(Novo_Trecho);
		Novo_Trecho.setLayout(null);
		
		info_editor.setBackground(Color.LIGHT_GRAY);
		info_editor.setBounds(170, 236, 525, 205);
		Novo_Trecho.add(info_editor);
		info_editor.setLayout(null);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Crie o Trecho e edite-o aqui !!!");
		lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesLabel.setBounds(40, 27, 444, 148);
		info_editor.add(lblNewJgoodiesLabel);
		
		abascodigos.setBorder(new LineBorder(Color.WHITE, 0));
		abascodigos.setBounds(0, 0, 797, 763);
		Novo_Trecho.add(abascodigos);
         
		ListaTrechobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Barrapesquisa.setEnabled(true);
				linguagenspesquisa.setEnabled(true);
				
				layeredPane.removeAll();
				layeredPane.add(Lista_Trechos);
				layeredPane.repaint();
				layeredPane.revalidate();
				painel_novo.setVisible(false);
				painel_lista.setVisible(true);
				
					 DefaultTableModel modelo = ( DefaultTableModel) tabela.getModel();
				 	    modelo.setNumRows(0);
				 	    TrechoDAO tdao = new TrechoDAO();
				 	    
				 	    for(Trecho t: tdao.listartrechos()) {
				 	   	 
				 	   	 modelo.addRow(new Object[] {
				 	   			 t.getId(),
				 	   			 t.getTitulo(),
				 	   			 t.getLinguagem(),
				 	   	 });
				 	   	 
				 	    }
				
				
			}
		});
		ListaTrechobtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		ListaTrechobtn.setBackground(new Color(255, 255, 255));
		ListaTrechobtn.setBounds(45, 30, 155, 50);
		ListaTrechobtn.setBorder(null);
		getContentPane().add(ListaTrechobtn);
		ListaTrechobtn.setBorder(null);
		
		NovoTrechobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					layeredPane.removeAll();
					layeredPane.add(Novo_Trecho);
					layeredPane.repaint();
					layeredPane.revalidate();
					painel_novo.setVisible(true);
					painel_lista.setVisible(false);
					info_editor.setVisible(true);
					
					if(abascodigos.getComponentCount() >= 1) {
						janelas = true;
					}else {
						janelas = false;
					}
					if(janelas == true) {
						info_editor.setVisible(false);	
					}else {
						info_editor.setVisible(true);
					}
					
			}
		});
		
		btnCriarTrecho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String liguagem_selecionada = combolinguagem.getSelectedItem().toString();
				String titulo_trecho = Titulotxt.getText();
				if(liguagem_selecionada == "Selecione a Linguagem..." || titulo_trecho.isEmpty())
					JOptionPane.showMessageDialog(
					        null, "Título ou Linguagem Incompleto", "Falha", JOptionPane.ERROR_MESSAGE);
				else {
					
					Titulotxt.setText("");
					combolinguagem.setSelectedIndex(0);
					
					JPanel Título_Editor = new JPanel();
					abascodigos.addTab(titulo_trecho +" - " + liguagem_selecionada, null, Título_Editor, liguagem_selecionada);
					Título_Editor.setBackground(Color.white);
					Título_Editor.setLayout(null);
					
					JEditorPane editorPane = new JEditorPane();
					editorPane.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
					editorPane.setBounds(0, 35, 792, 700);
					Título_Editor.add(editorPane);
					
					JPanel panel_1 = new JPanel();
					panel_1.setBackground(Color.GRAY);
					panel_1.setBounds(0, 28, 797, 7);
					Título_Editor.add(panel_1);
					
					
					JButton btnSalvar = new JButton("  Salvar");
					btnSalvar.setBackground(Color.WHITE);
					btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
					btnSalvar.setIcon(new ImageIcon(Telaprincipal.class.getResource("/IMGS/salvarbtn.png")));
					btnSalvar.setBounds(684, 0, 113, 27);
					btnSalvar.setBorder(null);
					Título_Editor.add(btnSalvar);
					
					
					btnSalvar.setToolTipText("ALT + S = Salvar");
					btnSalvar.setMnemonic(KeyEvent.VK_S);
					
					btnSalvar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
							Trecho t = new Trecho();
							TrechoDAO DAO = new TrechoDAO();
							
							t.setTitulo(titulo_trecho);
							t.setLinguagem((liguagem_selecionada));//liguagem_selecionada
							t.setConteudo(editorPane.getText());
							
							DAO.criartrecho(t);
							
							//JOptionPane.showMessageDialog(null, "Quantidade de abas \n"+ abascodigos.getComponentCount());
							//editorPane.getText() + titulo_trecho +"\n \n Quantidade de abas \n"+ abascodigos.getComponentCount() +"\n \n \n"+ abascodigos.getTitleAt(1)
							
						}
					});
					
					
					JButton btnfechar = new JButton(" X ");
					btnfechar.setForeground(Color.black);
					btnfechar.setBackground(Color.white);
					btnfechar.setFont(new Font("Verdana", Font.BOLD, 16));
					btnfechar.setBounds(0, 0, 50, 28);
					btnfechar.setBorder(null);
					Título_Editor.add(btnfechar);
					btnfechar.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							btnfechar.setForeground(Color.white);
							btnfechar.setBackground(Color.red);
						}
					});
					btnfechar.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseExited(MouseEvent e) {
							btnfechar.setForeground(Color.black);
							btnfechar.setBackground(Color.white);
						}
					});
					btnfechar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
							
							abascodigos.remove(abascodigos.getSelectedIndex());
							if(abascodigos.getComponentCount() >= 1) {
								janelas = true;
							}else {
								janelas = false;
							}
							if(janelas == true) {
								info_editor.setVisible(false);	
							}else {
								info_editor.setVisible(true);
							}
							
							Salvarounao dialogosn = new Salvarounao(titulo_trecho, liguagem_selecionada, editorPane.getText());
							dialogosn.setLocationRelativeTo(null);
							dialogosn.setVisible(true);
						
						}
					});
					
					if(abascodigos.getComponentCount() >= 1) {
						janelas = true;
					}else {
						janelas = false;
					}
					if(janelas == true) {
						info_editor.setVisible(false);	
					}else {
						info_editor.setVisible(true);
					}
					
					JOptionPane.showMessageDialog(
					        null, "Lembre de Salvar o Trecho \nUse ALT + S", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnSalvaratt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TrechoDAO tdao = new TrechoDAO();
				
				tdao.atualizartrecho(idatual,titulotrecho.getText(),linguagemtrecho.getText(),codigotrecho.getText());
				
			}
		});
		NovoTrechobtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		NovoTrechobtn.setBorder(null);
		NovoTrechobtn.setBackground(Color.WHITE);
		NovoTrechobtn.setBounds(240, 30, 155, 50);
		getContentPane().add(NovoTrechobtn);
		
		panel.setBounds(0, 472, 458, 97);
		panel.setBackground(Color.ORANGE);
		getContentPane().add(panel);
		
		painel_novo.setVisible(false);
		painel_lista.setVisible(true);
		
		panel_4.setBackground(Color.ORANGE);
		panel_4.setBounds(0, 676, 458, 97);
		getContentPane().add(panel_4);
		
		panel_3.setBackground(Color.ORANGE);
		panel_3.setBounds(0, 574, 458, 97);
		getContentPane().add(panel_3);
		
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(0, 0, 458, 115);
		getContentPane().add(panel_1);
		
		painel_lista.setBounds(0, 0, 458, 467);
		painel_lista.setBackground(new Color(255, 153, 0));
		getContentPane().add(painel_lista);
		painel_lista.setLayout(null);
		Barrapesquisa.setToolTipText("Pesquise aqui");
		
		Barrapesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Barrapesquisa.setBounds(21, 184, 345, 44);
		Barrapesquisa.setBorder(new CompoundBorder());
		painel_lista.add(Barrapesquisa);
		Barrapesquisa.setColumns(10);
		
		lblNewLabel.setIcon(new ImageIcon(Telaprincipal.class.getResource("/IMGS/lupa2.0.png")));
		lblNewLabel.setBounds(4, 173, 448, 67);
		painel_lista.add(lblNewLabel);
		
		linguagenspesquisa.setBounds(140, 262, 308, 36);
		painel_lista.add(linguagenspesquisa);
		
		lblNewLabel_1_1_1.setIcon(new ImageIcon(Telaprincipal.class.getResource("/IMGS/area_linguagem.png")));
		lblNewLabel_1_1_1.setBounds(4, 248, 448, 67);
		painel_lista.add(lblNewLabel_1_1_1);
		
		JButton pesquisarbtn = new JButton("Pesquisar");
		pesquisarbtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		pesquisarbtn.setBorder(null);
		pesquisarbtn.setBackground(Color.WHITE);
		pesquisarbtn.setBounds(140, 320, 155, 50);
		painel_lista.add(pesquisarbtn);
		
		panel_2.setBounds(0, 370, 458, 97);
		painel_lista.add(panel_2);
		panel_2.setBackground(Color.ORANGE);
		
		painel_novo.setBackground(new Color(255, 153, 0));
		painel_novo.setBounds(0, 0, 458, 467);
		getContentPane().add(painel_novo);
		painel_novo.setLayout(null);
		
		Titulotxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Titulotxt.setColumns(10);
		Titulotxt.setBorder(new CompoundBorder());
		Titulotxt.setBounds(92, 158, 345, 44);
		painel_novo.add(Titulotxt);
		
		lblNewLabel_1.setIcon(new ImageIcon(Telaprincipal.class.getResource("/IMGS/area_titulo.png")));
		lblNewLabel_1.setBounds(4, 150, 448, 67);
		painel_novo.add(lblNewLabel_1);
		
		combolinguagem.setModel(new DefaultComboBoxModel(new String[] {"Selecione a Linguagem...","JavaScript"
				, "Python"
				, "Java"
				, "PHP"
				, "C#"
				, "C++"
				, "Ruby"
				, "CSS"
				, "TypeScript"
				, "C"
				, "Swift"
				, "Objective-C"
				, "Scala"
				, "R"
				, "Go"
				, "Shell"
				, "PowerShell"
				, "Perl"
				, "Kotlin"
				, "Haskell", "Flutter"}));
		combolinguagem.setBounds(140, 239, 308, 36);
		painel_novo.add(combolinguagem);
		
		lblNewLabel_1_1.setIcon(new ImageIcon(Telaprincipal.class.getResource("/IMGS/area_linguagem.png")));
		lblNewLabel_1_1.setBounds(4, 225, 448, 67);
		painel_novo.add(lblNewLabel_1_1);
		
		btnCriarTrecho.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCriarTrecho.setBorder(null);
		btnCriarTrecho.setBackground(Color.WHITE);
		btnCriarTrecho.setBounds(140, 345, 155, 50);
		painel_novo.add(btnCriarTrecho);
		setVisible(true);
		setLocationRelativeTo(null);
	
	}
}
