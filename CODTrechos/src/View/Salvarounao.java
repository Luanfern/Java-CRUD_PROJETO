package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Trecho;
import model.TrechoDAO;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Salvarounao extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public Salvarounao(Object object, Object object2, Object object3) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Salvarounao dialog = new Salvarounao(null, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public Salvarounao(String titulo, String linguagem, String codigo) {
		
		setBounds(100, 100, 350, 130);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel areatexto = new JPanel();
			areatexto.setBounds(0, 0, 334, 58);
			contentPanel.add(areatexto);
			areatexto.setLayout(null);
			
			JLabel mtitulo = new JLabel("New label");
			mtitulo.setBounds(0, 25, 334, 22);
			mtitulo.setText("Deseja Salvar o Trecho : "+titulo + " ?");
			areatexto.add(mtitulo);
			{
				JLabel lblNewLabel = new JLabel("A guia ser\u00E1 fechada...");
				lblNewLabel.setBounds(0, 0, 334, 25);
				areatexto.add(lblNewLabel);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			
			
			
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton salvar = new JButton("SALVAR");
				salvar.setActionCommand("SALVAR");
				buttonPane.add(salvar);
				getRootPane().setDefaultButton(salvar);
				
				salvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Trecho t = new Trecho();
						TrechoDAO DAO = new TrechoDAO();
						
						t.setTitulo(titulo);
						t.setLinguagem((linguagem));
						t.setConteudo(codigo);
						
						DAO.criartrecho(t);
						
						JOptionPane.showMessageDialog(null, "Salvo com Sucesso !");	
					
						Salvarounao.this.setVisible(false);
						Salvarounao.this.dispose();
					}});
				
			}
			{
				JButton nsalv = new JButton("NÃO SALVAR");
				nsalv.setActionCommand("NÃO SALVAR");
				buttonPane.add(nsalv);
				
				nsalv.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Salvarounao.this.setVisible(false);
						Salvarounao.this.dispose();
						
					}});
			}
		}
		
	}
}
