package gui;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;

import model.Utilizador;
import model.mensagens.Mensagem;
import model.mensagens.MensagemAdministrador;
import model.mensagens.MensagemPessoal;

import javax.swing.JScrollPane;

import dao.MensagemDAO;
import dao.UtilizadorDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;



public class NovaMensagem extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane = new JPanel();
	private JTextField tfassunto = new JTextField();
	private JTextField tfdestinatario = new JTextField();
	private JTextPane tpcorpo = new JTextPane();
	private JComboBox cbtipo = new JComboBox();
	
	private PerfilUtilizador pai = null;
	private Utilizador utilizador = new Utilizador();
	private Utilizador destinatario = new Utilizador();
	private UtilizadorDAO utilizadorDAO = new UtilizadorDAO();
	private MensagemDAO mensagemDAO = new MensagemDAO();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaMensagem frame = new NovaMensagem(new Utilizador(),null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param utilizador 
	 * @param utilizador2 
	 * @param pai 
	 * @param mensagem 
	 */
	public NovaMensagem(Utilizador user, PerfilUtilizador pu, Utilizador dest) {
		utilizadorDAO.addObserver(this);
		mensagemDAO.addObserver(this);
		
		pai = pu;
		destinatario = dest;
		
		if(user != null){
			this.utilizador = user.clone();
			utilizador.addObserver(this);
		}
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 545);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setOpaque(false);
		contentPane.setFocusCycleRoot(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE))
					.addContainerGap(296, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblTipoDeMensagem = new JLabel("Tipo de mensagem:");
		lblTipoDeMensagem.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblPara = new JLabel("Para:");
		lblPara.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblAssunto = new JLabel("Assunto:");
		lblAssunto.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfassunto = new JTextField();
		tfassunto.setColumns(10);
		
		cbtipo = new JComboBox();
		cbtipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cbtipo.getSelectedItem().equals("Mensagem Pessoal")){
					tfdestinatario.setEnabled(false);
				}
				else{
					tfdestinatario.setEnabled(true);
				}
			}
		});
		cbtipo.setModel(new DefaultComboBoxModel(new String[] {"Mensagem Pessoal", "Mensagem Administrador"}));

		tfdestinatario = new JTextField("");
		
		if(destinatario != null)
			tfdestinatario = new JTextField(destinatario.getNomeUtilizador());
		
		tfdestinatario.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblTipoDeMensagem)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(cbtipo, 0, 334, Short.MAX_VALUE))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addComponent(lblAssunto)
									.addComponent(lblPara))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
									.addComponent(tfdestinatario, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
									.addComponent(tfassunto)))))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoDeMensagem)
						.addComponent(cbtipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAssunto)
						.addComponent(tfassunto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPara)
						.addComponent(tfdestinatario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tpcorpo = new JTextPane();
		scrollPane.setViewportView(tpcorpo);
		tpcorpo.setBorder(new TitledBorder(null, "Corpo da mensagem", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(cbtipo, popupMenu);
		
		JMenuItem mntmMensagemPessoal = new JMenuItem("Mensagem pessoal");
		popupMenu.add(mntmMensagemPessoal);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblSugestoDeTroca = new JLabel("Nova mensagem");
		lblSugestoDeTroca.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JButton btnsair = new JButton("");
		btnsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende cancelar?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					NovaMensagem.this.setVisible(false);
					NovaMensagem.this.dispose();
				}
			}
		});
		btnsair.setIcon(new ImageIcon(NovaMensagem.class.getResource("/Imagens/sair32.png")));
		btnsair.setContentAreaFilled(false);
		btnsair.setBorderPainted(false);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfassunto.getText().equals("") ||
						tpcorpo.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.WARNING_MESSAGE );
				} else {
					if(cbtipo.getSelectedItem().equals("Mensagem Pessoal") && (tfdestinatario.getText().equals(""))){
						JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.WARNING_MESSAGE );
					} else
						try {
							if(cbtipo.getSelectedItem().equals("Mensagem Pessoal") && utilizadorDAO.verificaSeUtilizadorExiste(tfdestinatario.getText())){
								JOptionPane.showMessageDialog(null, "Destinatário inválido", "Erro", JOptionPane.WARNING_MESSAGE );
							}else {
								Mensagem mensagem;
								
								if(cbtipo.getSelectedItem().equals("Mensagem Pessoal")){
									Utilizador dest;
									
									if(destinatario == null){
										dest = new Utilizador();
										dest.setNomeUtilizador(tfdestinatario.getText());
									}else{
										dest = destinatario.clone();
									}
									
									mensagem = new MensagemPessoal(tfassunto.getText(), tpcorpo.getText(), false, dest.clone(), utilizador.clone());
								}else{
									mensagem = new MensagemAdministrador(tfassunto.getText(), tpcorpo.getText(), false, new Utilizador(), utilizador.clone());
								}
								
								mensagemDAO.novaMensagem(mensagem.clone());
								
								if(pai != null)
									pai.update();
								
								NovaMensagem.this.setVisible(false);
								NovaMensagem.this.dispose();
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
		button.setToolTipText("Enviar mensagem");
		button.setIcon(new ImageIcon(NovaMensagem.class.getResource("/Imagens/lida.png")));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSugestoDeTroca)
					.addPreferredGap(ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
					.addComponent(btnsair, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSugestoDeTroca))
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnsair, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
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

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}