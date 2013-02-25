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
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.ImagemDAO;
import dao.TrocaDAO;

import model.Utilizador;
import model.transacoes.Troca;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class PropostaTroca extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	Utilizador utilizador = new Utilizador();
	ImagemDAO imagemDAO = new ImagemDAO();
	TrocaDAO trocaDAO = new TrocaDAO();
	
	PerfilUtilizador pai = null;
	
	Troca troca = new Troca();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PropostaTroca frame = new PropostaTroca(new Utilizador(),new Troca(),null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param troca 
	 * @param utilizador 
	 * @throws SQLException 
	 */
	public PropostaTroca(Utilizador user, Troca t, PerfilUtilizador pv) throws SQLException {
		imagemDAO.addObserver(this);
		
		troca = t;
		pai = pv;
		
		if(user != null){
			this.utilizador = user.clone();
			utilizador.addObserver(this);
		}
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 796, 502);
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
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 756, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		JLabel imgsugerido = new JLabel("");
		imgsugerido.setIcon(new ImageIcon(PropostaTroca.class.getResource("/Imagens/cadeiras.jpg")));
		
		Blob blob = imagemDAO.getImagemProduto(troca.getProdutoParaTroca().getId());
		if (blob != null){ 
			imgsugerido.setIcon(new ImageIcon(blob.getBytes(1, (int)blob.length())));
		}
		
		JLabel img = new JLabel("");
		img.setIcon(new ImageIcon(PropostaTroca.class.getResource("/Imagens/esplanad.JPG")));
		
		Blob blob2 = imagemDAO.getImagemProduto(troca.getProdutoInteressado().getId());
		if (blob2 != null){ 
			img.setIcon(new ImageIcon(blob2.getBytes(1, (int)blob2.length())));
		}
		
		JLabel lblSugestoDeTroca = new JLabel("Proposta de Troca");
		lblSugestoDeTroca.setFont(new Font("Dialog", Font.BOLD, 25));
		
		JLabel lnome = new JLabel(troca.getProdutoInteressado().getNome());
		lnome.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel label_3 = new JLabel("Estado:");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lestadosugerido = new JLabel(troca.getProdutoParaTroca().getEstado());
		lestadosugerido.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel label_5 = new JLabel("Estado:");
		label_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lestado = new JLabel(troca.getProdutoInteressado().getEstado());
		lestado.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel label_7 = new JLabel("Vendedor:");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lvend = new JLabel(troca.getVendedor().getNomeUtilizador());
		lvend.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel label_9 = new JLabel("Vendedor:");
		label_9.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lvendsugerido = new JLabel(troca.getInteressado().getNomeUtilizador());
		lvendsugerido.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel label_10 = new JLabel("Preço:");
		label_10.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lpreco = new JLabel(troca.getProdutoInteressado().getPreco() + " €");
		
		JButton btfazerproposta = new JButton("");
		btfazerproposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende aceitar a proposta de troca?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					try {
						if(trocaDAO.aceitaTroca(troca)){
							JOptionPane.showMessageDialog(null, "Troca efectuada com sucesso", "Erro", JOptionPane.INFORMATION_MESSAGE );
							try {
								pai.update();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							PropostaTroca.this.setVisible(false);
							PropostaTroca.this.dispose();
						}else{
							JOptionPane.showMessageDialog(null, "Tente Novamente", "Erro", JOptionPane.WARNING_MESSAGE );
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
		});
		btfazerproposta.setToolTipText("Aceitar proposta de troca");
		btfazerproposta.setIcon(new ImageIcon(PropostaTroca.class.getResource("/Imagens/lida.png")));
		btfazerproposta.setContentAreaFilled(false);
		btfazerproposta.setBorderPainted(false);
		
		JLabel label_12 = new JLabel("Preço:");
		label_12.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lprecosugerido = new JLabel(troca.getProdutoParaTroca().getPreco() + " €");
		
		JLabel lblFazerProposta = new JLabel("Proposta:");
		lblFazerProposta.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lnomesugerido = new JLabel(troca.getProdutoParaTroca().getNome());
		lnomesugerido.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JButton btnsair = new JButton("");
		btnsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PropostaTroca.this.setVisible(false);
				PropostaTroca.this.dispose();
			}
		});
		btnsair.setIcon(new ImageIcon(PropostaTroca.class.getResource("/Imagens/sair32.png")));
		btnsair.setContentAreaFilled(false);
		btnsair.setBorderPainted(false);
		
		JButton btntele = new JButton("");
		btntele.setBorderPainted(false);
		btntele.setIcon(new ImageIcon(PropostaTroca.class.getResource("/Imagens/tele.png")));
		btntele.setDefaultCapable(false);
		btntele.setContentAreaFilled(false);
		
		JButton btnmail = new JButton("");
		btnmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NovaMensagem(utilizador, null, troca.getInteressado());
			}
		});
		btnmail.setIcon(new ImageIcon(PropostaTroca.class.getResource("/Imagens/mail.png")));
		btnmail.setDefaultCapable(false);
		btnmail.setContentAreaFilled(false);
		btnmail.setBorderPainted(false);
		
		JLabel lproposta = new JLabel(troca.getNovoPreco() + " €");
		lproposta.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende rejeitar a proposta de troca?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
						try {
							trocaDAO.removeTroca(troca);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						try {
							pai.update();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						PropostaTroca.this.setVisible(false);
						PropostaTroca.this.dispose();
				}
			}
		});
		button.setToolTipText("Rejeitar proposta de troca");
		button.setIcon(new ImageIcon(PropostaTroca.class.getResource("/Imagens/vazio32.png")));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
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
									.addComponent(imgsugerido, GroupLayout.PREFERRED_SIZE, 324, Short.MAX_VALUE)
									.addComponent(lnomesugerido, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lestadosugerido, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
										.addGap(15)
										.addComponent(lvendsugerido, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lprecosugerido, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)))
								.addGap(31)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblFazerProposta)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lproposta, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btfazerproposta, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnsair, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lestado, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
										.addComponent(lnome, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
										.addComponent(img, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lvend, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lpreco, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))))
								.addGap(74)))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblSugestoDeTroca)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lnomesugerido, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(imgsugerido, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3)
								.addComponent(lestadosugerido))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_9)
								.addComponent(lvendsugerido, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_12)
								.addComponent(lprecosugerido)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lnome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(img, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_5)
								.addComponent(lestado, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_7)
								.addComponent(lvend, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_10)
								.addComponent(lpreco))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(Alignment.LEADING, gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnmail, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
								.addComponent(btntele, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addComponent(btnsair, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addComponent(btfazerproposta, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lproposta)
								.addComponent(lblFazerProposta))
							.addContainerGap())))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
