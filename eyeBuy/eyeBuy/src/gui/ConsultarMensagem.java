package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Font;

import javax.swing.border.LineBorder;

import javax.swing.JList;
import javax.swing.border.TitledBorder;

import model.Utilizador;
import model.mensagens.Mensagem;
import model.mensagens.MensagemPessoal;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import dao.MensagemDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ConsultarMensagem extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private MensagemDAO mensagemDAO = new MensagemDAO();
	private Utilizador utilizador = new Utilizador();
	private Mensagem mensagem = ((Mensagem)new MensagemPessoal());
	private PerfilUtilizador pai = null;
	
	private JList list = new JList();
	private int index = 0;
	private JButton btcim = new JButton();
	private JButton btbaix = new JButton();
	private JButton btdir = new JButton();
	private JButton btadd = new JButton();
	private JButton btrem = new JButton();
	private JButton btact = new JButton();
	private JButton btresp = new JButton();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mensagem mensagem = new MensagemPessoal();
					ConsultarMensagem frame = new ConsultarMensagem(new Utilizador(), mensagem, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param mensagem 
	 * @param utilizador 
	 */
	public ConsultarMensagem(Utilizador user, Mensagem mens, PerfilUtilizador pu) {
		mensagem = mens.clone();
		pai = pu;
		
		mensagem.addObserver(this);
		mensagemDAO.addObserver(this);
		
		try {
			MensagemDAO.marcaComoLida(mensagem.getId());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		if(user != null){
			this.utilizador = user.clone();
			utilizador.addObserver(this);
		}
		
		setAlwaysOnTop(true);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 507);
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
		
		btrem = new JButton("");
		btrem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover a mensagem?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					try {
						MensagemDAO.removeMensagem(utilizador.getNomeUtilizador(),mensagem.getId());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					updaterecebidas();
					try {
						pai.update();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (list.getSelectedValue() == null){
						ConsultarMensagem.this.setVisible(false);
						ConsultarMensagem.this.dispose();
					} else{
						Mensagem mensagem;
						mensagem = mensagemDAO.getMensagemRecebidaListada(utilizador.getNomeUtilizador(),(String)list.getSelectedValue());
						new ConsultarMensagem(utilizador,mensagem,pai);
						ConsultarMensagem.this.setVisible(false);
						ConsultarMensagem.this.dispose();
					}
				}
			}
		});
		btrem.setToolTipText("Apagar mensagem");
		btrem.setIcon(new ImageIcon(ConsultarMensagem.class.getResource("/Imagens/remover.png")));
		btrem.setBorderPainted(false);
		btrem.setContentAreaFilled(false);
		
		btadd = new JButton("");
		btadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NovaMensagem(utilizador,pai,null);
			}
		});
		btadd.setToolTipText("Nova mensagem");
		btadd.setIcon(new ImageIcon(ConsultarMensagem.class.getResource("/Imagens/adicionar.png")));
		btadd.setContentAreaFilled(false);
		btadd.setBorderPainted(false);
		
		btresp = new JButton("");
		btresp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mensagem mensagem;
				mensagem = mensagemDAO.getMensagemRecebidaListada(utilizador.getNomeUtilizador(),(String)list.getSelectedValue());
				new NovaMensagem(utilizador,pai,mensagem.getRemetente());
				ConsultarMensagem.this.setVisible(false);
				ConsultarMensagem.this.dispose();
			}
		});
		btresp.setToolTipText("Responder a mensagem");
		btresp.setIcon(new ImageIcon(ConsultarMensagem.class.getResource("/Imagens/responder.png")));
		btresp.setContentAreaFilled(false);
		btresp.setBorderPainted(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "\u00DAltimas mensagens recebidas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JButton btsair = new JButton("");
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarMensagem.this.setVisible(false);
				ConsultarMensagem.this.dispose();
			}
		});
		btsair.setIcon(new ImageIcon(ConsultarMensagem.class.getResource("/Imagens/sair32.png")));
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 635, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btadd, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btrem, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btresp, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btsair, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 495, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btsair, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(btadd, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addComponent(btrem, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addComponent(btresp, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(40))
		);
		
		btact = new JButton("");
		btact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updaterecebidas();
			}
		});
		btact.setIcon(new ImageIcon(ConsultarMensagem.class.getResource("/Imagens/actualizar.png")));
		btact.setContentAreaFilled(false);
		btact.setBorderPainted(false);
		
		btdir = new JButton("");
		btdir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mensagem mensagem;
				mensagem = mensagemDAO.getMensagemRecebidaListada(utilizador.getNomeUtilizador(),(String)list.getSelectedValue());
				new ConsultarMensagem(utilizador,mensagem,pai);
				ConsultarMensagem.this.setVisible(false);
				ConsultarMensagem.this.dispose();
			}
		});
		btdir.setToolTipText("Ir para mensagem");
		btdir.setIcon(new ImageIcon(ConsultarMensagem.class.getResource("/Imagens/irpara.png")));
		btdir.setContentAreaFilled(false);
		btdir.setBorderPainted(false);
		
		btbaix = new JButton("");
		btbaix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcim.setEnabled(true);
				index += 1;
				
				list.setSelectedIndex(index);
				list.ensureIndexIsVisible(index);
				
				try {
					if(mensagemDAO.listaMensagensRecebidas(utilizador.getNomeUtilizador()).length == index+1){
						btbaix.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btbaix.setIcon(new ImageIcon(ConsultarMensagem.class.getResource("/Imagens/setabaixo.png")));
		btbaix.setContentAreaFilled(false);
		btbaix.setBorderPainted(false);
		
		btcim = new JButton("");
		btcim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaix.setEnabled(true);
				index -= 1;
				
				list.setSelectedIndex(index);
				list.ensureIndexIsVisible(index);
				
				if(index == 0){
					btcim.setEnabled(false);
				}
			}
		});
		btcim.setIcon(new ImageIcon(ConsultarMensagem.class.getResource("/Imagens/setacima.png")));
		btcim.setContentAreaFilled(false);
		btcim.setBorderPainted(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addComponent(btcim, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btbaix, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btdir, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btact, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btact, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btdir, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btbaix, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btcim, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Mensagem mensagem;
					mensagem = mensagemDAO.getMensagemRecebidaListada(utilizador.getNomeUtilizador(),(String)list.getSelectedValue());
					new ConsultarMensagem(utilizador,mensagem,pai);
					ConsultarMensagem.this.setVisible(false);
					ConsultarMensagem.this.dispose();
				}
			}
		});
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				btcim.setEnabled(true);
				btbaix.setEnabled(true);
				
				index = list.getSelectedIndex();
				list.ensureIndexIsVisible(index);
				
				btbaix.setEnabled(true);
				btdir.setEnabled(true);
				btrem.setEnabled(true);
				if(index == 0){
					btcim.setEnabled(false);
				}
				try {
					if (mensagemDAO.listaMensagensRecebidas(utilizador.getNomeUtilizador()).length < 2){
						btbaix.setEnabled(false);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (list.getSelectedValue() == null){
					btdir.setEnabled(false);
					btrem.setEnabled(false);
				}
			}
		});
		
		updaterecebidas();
		
		scrollPane_1.setViewportView(list);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblTipoDeMensagem = new JLabel("Tipo de mensagem:");
		lblTipoDeMensagem.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		String string;
		
		if(mensagem.getRemetente().getNomeUtilizador().equals("") || mensagem.getRemetente() == null){
			string = "Mensagem Sistema";
		} else{
			string = "Mensagem Pessoal";
		}
		
		JLabel lbtipo = new JLabel(string);
		lbtipo.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblDe = new JLabel("De:");
		lblDe.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		String string2;
		
		if(mensagem.getRemetente().getNomeUtilizador().equals("") || mensagem.getRemetente() == null){
			string2 = "eyeBuy";
		} else{
			string2 = mensagem.getRemetente().getNome();
		}
		
		JLabel lbremetente = new JLabel(string2);
		
		JLabel lblPara = new JLabel("Para:");
		lblPara.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lbdestino = new JLabel("mim");
		
		if(mensagem.getDestinatario().getNomeUtilizador().equals("") || mensagem.getDestinatario() == null){
			lbdestino.setText("EyeBuy");
		} else{
			lbdestino.setText(mensagem.getDestinatario().getNomeUtilizador());
		}
		
		lbdestino.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Corpo Mensagem", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblTipoDeMensagem)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbtipo, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblDe)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbremetente, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblPara)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdestino, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoDeMensagem)
						.addComponent(lbtipo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDe)
						.addComponent(lbremetente))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPara)
						.addComponent(lbdestino))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTextArea tacorpo = new JTextArea();
		tacorpo.setEditable(false);
		scrollPane.setViewportView(tacorpo);
		panel_1.setLayout(gl_panel_1);
		
		tacorpo.setText(mensagem.getCorpo());
		
		JLabel lbassunto = new JLabel(mensagem.getAssunto());
		
		lbassunto.setFont(new Font("Dialog", Font.BOLD, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbassunto, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbassunto, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void updaterecebidas(){
		index = 0;
		
		try {
			list.setModel(new AbstractListModel() {
				private static final long serialVersionUID = 1L;
				String[] values = mensagemDAO.listaMensagensRecebidas(utilizador.getNomeUtilizador());
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		list.setSelectedIndex(index);
		list.ensureIndexIsVisible(index);
		
		if(list.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    list.setCellRenderer(renderer);
		}
		
		btbaix.setEnabled(true);
		btcim.setEnabled(false);
		btdir.setEnabled(true);
		btrem.setEnabled(true);
		try {
			if (mensagemDAO.listaMensagensRecebidas(utilizador.getNomeUtilizador()).length < 2){
				btbaix.setEnabled(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.getSelectedValue() == null){
			btdir.setEnabled(false);
			btrem.setEnabled(false);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
