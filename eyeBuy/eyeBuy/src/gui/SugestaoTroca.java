package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.ImagemDAO;
import dao.TrocaDAO;

import model.EyeBuy;
import model.Produto;
import model.Utilizador;
import model.transacoes.Troca;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;




public class SugestaoTroca extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfinsproposta;
	
	ImagemDAO imagemDAO = new ImagemDAO();
	TrocaDAO trocaDAO = new TrocaDAO();
	EyeBuy eyeBuy = new EyeBuy();
	
	private Utilizador utilizador = new Utilizador();
	private Produto doInteressado = new Produto();
	private Produto doVendedor = new Produto();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SugestaoTroca frame = new SugestaoTroca(new Utilizador(),new Produto(), new Produto());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param produto 
	 * @param doInteressado 
	 * @param utilizador 
	 * @throws SQLException 
	 */
	public SugestaoTroca(Utilizador user, Produto di, Produto dv) throws SQLException {
		imagemDAO.addObserver(this);
		
		doInteressado = di;
		doVendedor = dv;
		
		if(user != null){
			this.utilizador = user.clone();
			utilizador.addObserver(this);
		}
		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 869, 522);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setOpaque(false);
		contentPane.setFocusCycleRoot(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 836, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(SugestaoTroca.class.getResource("/Imagens/cadeiras.jpg")));
		
		Blob blob = imagemDAO.getImagemProduto(doVendedor.getId());
		if (blob != null){ 
			label.setIcon(new ImageIcon(blob.getBytes(1, (int)blob.length())));
		}
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(SugestaoTroca.class.getResource("/Imagens/esplanad.JPG")));
		
		Blob blob2 = imagemDAO.getImagemProduto(doInteressado.getId());
		if (blob2 != null){ 
			label.setIcon(new ImageIcon(blob2.getBytes(1, (int)blob2.length())));
		}
		
		JLabel lblSugestoDeTroca = new JLabel("Sugestão de Troca");
		lblSugestoDeTroca.setFont(new Font("Dialog", Font.BOLD, 25));
		
		JLabel lnome = new JLabel(doInteressado.getNome());
		lnome.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel label_3 = new JLabel("Estado:");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lestadosugerido = new JLabel(doVendedor.getEstado());
		lestadosugerido.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel label_5 = new JLabel("Estado:");
		label_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lestado = new JLabel(doInteressado.getEstado());
		lestado.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel label_7 = new JLabel("Vendedor:");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lvend = new JLabel(doInteressado.getUtilizador().getNomeUtilizador());
		lvend.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel label_9 = new JLabel("Vendedor:");
		label_9.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lvendsugerido = new JLabel(doVendedor.getUtilizador().getNomeUtilizador());
		lvendsugerido.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel label_10 = new JLabel("Preço:");
		label_10.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lpreco = new JLabel(doInteressado.getPreco() + " €");
		
		tfinsproposta = new JTextField();
		tfinsproposta.setColumns(10);
		
		JButton btfazerproposta = new JButton("");
		btfazerproposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!eyeBuy.isDouble(tfinsproposta.getText())){
					JOptionPane.showMessageDialog(null, "Valor inválido", "Erro", JOptionPane.WARNING_MESSAGE );
				}else{
					int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende propor troca?", "Aviso", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION){
						
						Troca troca = new Troca(0, Double.parseDouble(tfinsproposta.getText()), doVendedor, doInteressado, utilizador, doVendedor.getUtilizador());
						
						try {
							if(trocaDAO.novaTroca(troca)){
								JOptionPane.showMessageDialog(null, "Troca proposta com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE );
								SugestaoTroca.this.setEnabled(false);
								SugestaoTroca.this.dispose();
							}else{
								JOptionPane.showMessageDialog(null, "Ocorreu um erro", "Erro", JOptionPane.WARNING_MESSAGE );
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					}
				}
			}
		});
		btfazerproposta.setToolTipText("Fazer proposta de troca");
		btfazerproposta.setIcon(new ImageIcon(SugestaoTroca.class.getResource("/Imagens/lida.png")));
		btfazerproposta.setContentAreaFilled(false);
		btfazerproposta.setBorderPainted(false);
		
		JLabel label_12 = new JLabel("Preço:");
		label_12.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lprecosugerido = new JLabel(doVendedor.getPreco() + " €");
		
		JLabel lblFazerProposta = new JLabel("Fazer proposta:");
		lblFazerProposta.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lnomesugerido = new JLabel(doVendedor.getNome());
		lnomesugerido.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel label_14 = new JLabel("€");
		
		JButton btnsair = new JButton("");
		btnsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SugestaoTroca.this.setVisible(false);
				SugestaoTroca.this.dispose();
			}
		});
		btnsair.setIcon(new ImageIcon(SugestaoTroca.class.getResource("/Imagens/sair32.png")));
		btnsair.setContentAreaFilled(false);
		btnsair.setBorderPainted(false);
		
		JButton btntele = new JButton("");
		btntele.setBorderPainted(false);
		btntele.setIcon(new ImageIcon(SugestaoTroca.class.getResource("/Imagens/tele.png")));
		btntele.setDefaultCapable(false);
		btntele.setContentAreaFilled(false);
		
		JButton btnmail = new JButton("");
		btnmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NovaMensagem(utilizador, null, doVendedor.getUtilizador());
			}
		});
		btnmail.setIcon(new ImageIcon(SugestaoTroca.class.getResource("/Imagens/mail.png")));
		btnmail.setDefaultCapable(false);
		btnmail.setContentAreaFilled(false);
		btnmail.setBorderPainted(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btntele, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnmail, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblSugestoDeTroca)
								.addContainerGap())
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lprecosugerido, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 317, Short.MAX_VALUE)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lestadosugerido, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
									.addComponent(lnomesugerido, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
										.addGap(15)
										.addComponent(lvendsugerido, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))
								.addGap(79)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblFazerProposta)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tfinsproposta, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(label_14)
										.addGap(12)
										.addComponent(btfazerproposta, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnsair, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lpreco, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lvend, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lestado, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
										.addComponent(lnome, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 331, Short.MAX_VALUE)))
								.addGap(20)))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblSugestoDeTroca)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lnome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lnomesugerido, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(label_3)
									.addComponent(lestadosugerido, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(label_5)
									.addComponent(lestado, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_9)
										.addComponent(lvendsugerido, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_12)
										.addComponent(lprecosugerido)))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_7)
										.addComponent(lvend, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_10)
										.addComponent(lpreco))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnmail, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
								.addComponent(btntele, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(tfinsproposta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_14)
										.addComponent(lblFazerProposta))
									.addContainerGap())
								.addComponent(btfazerproposta, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnsair, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
