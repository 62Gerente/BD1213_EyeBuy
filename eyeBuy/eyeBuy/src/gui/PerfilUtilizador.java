package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JList;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import dao.CategoriaDAO;
import dao.HistoricoDAO;
import dao.LocalidadeDAO;
import dao.MensagemDAO;
import dao.ProdutoDAO;
import dao.TrocaDAO;
import dao.UtilizadorDAO;

import model.Utilizador;
import model.mensagens.Mensagem;
import model.transacoes.Troca;

import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JCheckBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class PerfilUtilizador extends JFrame implements Observer {

	/**
	 * 
	 */
	private Utilizador utilizador = new Utilizador();
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private HistoricoDAO historicoDAO = new HistoricoDAO();
	private TrocaDAO trocaDAO = new TrocaDAO();
	private MensagemDAO mensagemDAO = new MensagemDAO();
	private LocalidadeDAO localidadeDAO = new LocalidadeDAO();
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	
	private int indexhistorico = 0;
	private int indextrocas = 0;
	private int indexmensagensr = 0;
	private int indexmensagense = 0;
	
	private JButton btcimt;
	private JButton btbaixt;
	private JButton btdirt;
	private JButton btcimh;
	private JButton btbaixh;
	
	private JButton btbaixm;
	private JButton btcimm;
	private JButton btactm;
	private JButton btremm;
	private JButton btaddm;
	private JButton btlidam;
	private JButton btdirm;
	
	private JList jlhistorico = new JList();
	private JList jllistatrocas = new JList();
	private JList jlMensagens = new JList();
	private JList jlMensagense = new JList();
	
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JCheckBox cbnaolidas = new JCheckBox();
	
	private JComboBox cblocalidade = new JComboBox();
	private JComboBox cbcategoria = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilUtilizador frame = new PerfilUtilizador(null);
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
	 * @throws SQLException 
	 */
	public PerfilUtilizador(Utilizador user) throws SQLException {
		produtoDAO.addObserver(this);
		historicoDAO.addObserver(this);
		trocaDAO.addObserver(this);
		mensagemDAO.addObserver(this);
		localidadeDAO.addObserver(this);
		categoriaDAO.addObserver(this);
		utilizador.addObserver(this);
		
		if(user != null){
			this.utilizador = user.clone();
			utilizador.addObserver(this);
		}
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1056, 748);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setJMenuBar(menuBar);
		
		JMenu mnComunidade = new JMenu("Comunidade  ▼  ");
		mnComunidade.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/comm2.png")));
		menuBar.add(mnComunidade);
		
		JMenuItem mntmSobreOEyebuy = new JMenuItem("Sobre o eyeBuy");
		mntmSobreOEyebuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SobreNos();
			}
		});
		mnComunidade.add(mntmSobreOEyebuy);
		
		JMenuItem mntmAjuda = new JMenuItem("Ajuda");
		mntmAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Ajuda();
			}
		});
		mnComunidade.add(mntmAjuda);
		
		JMenu mnCarrinho = new JMenu("Carrinho ▼  ");
		mnCarrinho.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/carr.png")));
		menuBar.add(mnCarrinho);
		
		JMenuItem mntmConsultarCarrinho = new JMenuItem("Consultar carrinho");
		mntmConsultarCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ConsultarCarrinho(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PerfilUtilizador.this.setVisible(false);
				PerfilUtilizador.this.dispose();
			}
		});
		mnCarrinho.add(mntmConsultarCarrinho);
		
		JMenuItem mntmEsvaziarCarrinho = new JMenuItem("Esvaziar carrinho");
		mntmEsvaziarCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que esvaziar o seu carrinho?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					try {
						produtoDAO.limpaCarrinho(utilizador.getNomeUtilizador());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mnCarrinho.add(mntmEsvaziarCarrinho);
		
		JMenu mnUtilizador = new JMenu("Bem-vindo " + "Default" + "  ▼  ");
		
		if (utilizador != null){
			mnUtilizador = new JMenu("Bem-vindo " + this.utilizador.getNomeUtilizador() + "  ▼  ");
		}
		mnUtilizador.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/user2.png")));
		menuBar.add(mnUtilizador);
		
		JMenuItem mntmPerfilDeVendedor = new JMenuItem("Página de Vendas");
		mntmPerfilDeVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PaginaVendas(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PerfilUtilizador.this.setVisible(false);
				PerfilUtilizador.this.dispose();
			}
		});
		mnUtilizador.add(mntmPerfilDeVendedor);
		
		JMenuItem mntmHistrico = new JMenuItem("Histórico");
		mntmHistrico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new HistoricoGUI(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PerfilUtilizador.this.setVisible(false);
				PerfilUtilizador.this.dispose();
			}
		});
		mnUtilizador.add(mntmHistrico);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende sair?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					PerfilUtilizador.this.setVisible(false);
					PerfilUtilizador.this.dispose();
				}
			}
		});
		mnUtilizador.add(mntmSair);
		
		JButton btnPaginaInicial = new JButton("Página Inicial");
		btnPaginaInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Homev3(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PerfilUtilizador.this.setVisible(false);
				PerfilUtilizador.this.dispose();
			}
		});
		btnPaginaInicial.setContentAreaFilled(false);
		btnPaginaInicial.setBorderPainted(false);
		btnPaginaInicial.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/home2.png")));
		menuBar.add(btnPaginaInicial);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setOpaque(false);
		contentPane.setFocusCycleRoot(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_6 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
							.addGap(12))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
								.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
								.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
						.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
							.addGap(1))
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
					.addGap(18))
		);
		
		JButton btacth = new JButton("");
		btacth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatehistorico();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btacth.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/actualizar.png")));
		btacth.setContentAreaFilled(false);
		btacth.setBorderPainted(false);
		
		btbaixh = new JButton("");
		btbaixh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcimh.setEnabled(true);
				indexhistorico += 1;
				
				jlhistorico.setSelectedIndex(indexhistorico);
				jlhistorico.ensureIndexIsVisible(indexhistorico);
				
				try {
					if(historicoDAO.listaHistorico(utilizador.getNomeUtilizador()).length == indexhistorico+1){
						btbaixh.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btbaixh.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/setabaixo.png")));
		btbaixh.setContentAreaFilled(false);
		btbaixh.setBorderPainted(false);
		
		btcimh = new JButton("");
		btcimh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaixh.setEnabled(true);
				indexhistorico -= 1;
				
				jlhistorico.setSelectedIndex(indexhistorico);
				jlhistorico.ensureIndexIsVisible(indexhistorico);
				
				if(indexhistorico == 0){
					btcimh.setEnabled(false);
				}
			}
		});
		btcimh.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/setacima.png")));
		btcimh.setContentAreaFilled(false);
		btcimh.setBorderPainted(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Hist\u00F3rico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(569)
					.addComponent(btcimh, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btbaixh, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btacth, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(1))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(btcimh, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btbaixh, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btacth, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		jlhistorico = new JList();
		jlhistorico.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				btcimh.setEnabled(true);
				btbaixh.setEnabled(true);
				
				indexhistorico = jlhistorico.getSelectedIndex();
				jlhistorico.ensureIndexIsVisible(indexhistorico);
				
				if(indexhistorico == 0){
					btcimh.setEnabled(false);
				} else
					try {
						if(historicoDAO.listaHistorico(utilizador.getNomeUtilizador()).length == indexhistorico+1){
							btbaixh.setEnabled(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		jlhistorico.setVisibleRowCount(6);
		scrollPane.setViewportView(jlhistorico);
		
		updatehistorico();
		
		panel_6.setLayout(gl_panel_6);
		
		JButton btactt = new JButton("");
		btactt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatetrocas();
			}
		});
		btactt.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/actualizar.png")));
		btactt.setContentAreaFilled(false);
		btactt.setBorderPainted(false);
		
		btdirt = new JButton("");
		btdirt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Troca troca = new Troca();
				try {
					troca = trocaDAO.getTrocaListada((String)jllistatrocas.getSelectedValue());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				try {
					new PropostaTroca(utilizador,troca,PerfilUtilizador.this);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btdirt.setToolTipText("Abrir troca selecionada");
		btdirt.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/irpara.png")));
		btdirt.setContentAreaFilled(false);
		btdirt.setBorderPainted(false);
		
		btcimt = new JButton("");
		btcimt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaixt.setEnabled(true);
				indextrocas -= 1;
				
				jllistatrocas.setSelectedIndex(indextrocas);
				jllistatrocas.ensureIndexIsVisible(indextrocas);
				
				if(indextrocas == 0){
					btcimt.setEnabled(false);
				}
			}
		});
		btcimt.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/setacima.png")));
		btcimt.setContentAreaFilled(false);
		btcimt.setBorderPainted(false);
		
		btbaixt = new JButton("");
		btbaixt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcimt.setEnabled(true);
				indextrocas += 1;
				
				jllistatrocas.setSelectedIndex(indextrocas);
				jllistatrocas.ensureIndexIsVisible(indextrocas);
				
				try {
					if(trocaDAO.listaTrocas(utilizador.getNomeUtilizador()).length == indextrocas+1){
						btbaixt.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btbaixt.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/setabaixo.png")));
		btbaixt.setContentAreaFilled(false);
		btbaixt.setBorderPainted(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(null, "Lista de Trocas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(201, Short.MAX_VALUE)
					.addComponent(btcimt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btbaixt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btdirt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btactt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(btactt, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btdirt, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
							.addComponent(btcimt, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(btbaixt, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		jllistatrocas = new JList();
		jllistatrocas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Troca troca = new Troca();
					try {
						troca = trocaDAO.getTrocaListada((String)jllistatrocas.getSelectedValue());
					} catch (NumberFormatException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						new PropostaTroca(utilizador,troca,PerfilUtilizador.this);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		jllistatrocas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btcimt.setEnabled(true);
				btbaixt.setEnabled(true);
				
				indextrocas = jllistatrocas.getSelectedIndex();
				jllistatrocas.ensureIndexIsVisible(indextrocas);
				btdirt.setEnabled(true);
				btbaixt.setEnabled(true);
				if(indextrocas == 0){
					btcimt.setEnabled(false);
				}
				try {
					if (trocaDAO.listaTrocas(utilizador.getNomeUtilizador()).length < 2){
						btbaixt.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (jllistatrocas.getSelectedValue() == null){
					btdirt.setEnabled(false);
				}
			}
		});
		scrollPane_1.setViewportView(jllistatrocas);
		
		updatetrocas();
		
		panel_2.setLayout(gl_panel_2);
		
		JLabel lfoto = new JLabel("");
		lfoto.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/user1.png")));
		

		if (utilizador != null){
			Blob blobmv_2 = utilizador.getImagem();
			if (blobmv_2 != null){ 
				lfoto.setIcon(new ImageIcon(blobmv_2.getBytes(1, (int)blobmv_2.length())));
			}
		}
		
		lfoto.setHorizontalAlignment(SwingConstants.CENTER);

		
		JLabel lnome = new JLabel("André Augusto Costa Santos");
		
		if (utilizador != null){
			lnome = new JLabel(utilizador.getNome());
		}
		
		lnome.setFont(new Font("Dialog", Font.BOLD, 17));
		
		JLabel lemail = new JLabel("andreccdr@gmail.com");
		
		if (utilizador != null){
			lemail = new JLabel(utilizador.getEmail());
		}
		
		lemail.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lMorada = new JLabel("Rua Quinta da Aramada, nº41");
		
		if (utilizador != null){
			lMorada = new JLabel(utilizador.getMorada());
		}
		
		lMorada.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lLocalidade = new JLabel("Braga");
		if (utilizador != null){
			lLocalidade = new JLabel(utilizador.getLocalidade().getNome());
		}
		lLocalidade.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lContacto = new JLabel("917556233");
		if (utilizador != null){
			lContacto = new JLabel(utilizador.getTelemovel());
		}
		lContacto.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lcodigopostal = new JLabel("4815-300");
		if (utilizador != null){
			lcodigopostal = new JLabel(utilizador.getCodigoPostal());
		}
		lcodigopostal.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel ldescricao = new JLabel("<html> Aluno de engenharia informática da universidade do minho");
		if (utilizador != null){
			ldescricao = new JLabel("<html> " + utilizador.getDescricao());
		}
		ldescricao.setVerticalAlignment(SwingConstants.TOP);
		ldescricao.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JButton btnEditarPerfil = new JButton("");
		btnEditarPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new EditarPerfil(utilizador,PerfilUtilizador.this);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEditarPerfil.setContentAreaFilled(false);
		btnEditarPerfil.setBorderPainted(false);
		btnEditarPerfil.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/edit48.png")));
		
		JLabel lblAvaliaoComoComprador = new JLabel("Avaliação como comprador:");
		lblAvaliaoComoComprador.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblAvaliaoComoVendedor = new JLabel("Avaliação como vendedor:");
		lblAvaliaoComoVendedor.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lavaliacaocomp = new JLabel("67%");
		if (utilizador != null){
			lavaliacaocomp = new JLabel(utilizador.getAvaliacaoComprador() + " %");
		}
		
		JLabel lavaliacaovend = new JLabel("100%");
		if (utilizador != null){
			lavaliacaovend = new JLabel(utilizador.getAvaliacaoVendedor() + " %");
		}
		
		JLabel lblNmeroDeAvaliaes = new JLabel("Número de avaliações:\n\n");
		lblNmeroDeAvaliaes.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel label_3 = new JLabel("Número de avaliações:\n\n");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lnavaliacoescomp = new JLabel("12");
		if (utilizador != null){
			lnavaliacoescomp = new JLabel((utilizador.getAvaliacoesPositivas() + utilizador.getAvaliacoesNegativas())+"");
		}
		
		JLabel lnavaliacoesvend = new JLabel("6");
		if (utilizador != null){
			lnavaliacoesvend = new JLabel((utilizador.getNrAvaliacoesVendaPos() + utilizador.getNrAvaliacoesVendaNeg())+"");
		}
		
		JLabel lblNmeroDeCompras = new JLabel("Número de compras:\n\n");
		
		lblNmeroDeCompras.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lncompras = new JLabel("12");
		if (utilizador != null){
			lncompras = new JLabel(utilizador.getNrCompras()+"");
		}
		JLabel lblNmeroDeVendas = new JLabel("Número de vendas:\n\n");
		lblNmeroDeVendas.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lnvendas = new JLabel("6");
		if (utilizador != null){
			lnvendas = new JLabel(utilizador.getNrVendas()+"");
		}
		
		JLabel lbdadosincompletos = new JLabel("Dados Incompletos");
		if(utilizador.getPerfilCompleto()){
			lbdadosincompletos.setVisible(false);
		}
		lbdadosincompletos.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(ldescricao, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lfoto, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
										.addComponent(lemail, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
										.addComponent(lnome, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnEditarPerfil)
									.addGap(43))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
										.addComponent(lMorada, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
										.addComponent(lContacto, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
										.addComponent(lcodigopostal, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
										.addComponent(lLocalidade, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
									.addGap(18)
									.addComponent(lbdadosincompletos, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lblAvaliaoComoVendedor, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lblAvaliaoComoComprador, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(12)))
							.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
								.addComponent(lavaliacaocomp, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
								.addComponent(lavaliacaovend, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
							.addGap(6)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lblNmeroDeAvaliaes, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lnavaliacoescomp, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNmeroDeCompras, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(21)
									.addComponent(lnavaliacoesvend, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNmeroDeVendas, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(lnvendas, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
								.addComponent(lncompras, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnEditarPerfil)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lnome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lemail)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lMorada)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lcodigopostal)
									.addGap(8)
									.addComponent(lLocalidade)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lContacto))
								.addComponent(lbdadosincompletos)))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addContainerGap()
							.addComponent(lfoto, 0, 0, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ldescricao, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblAvaliaoComoComprador)
							.addComponent(lavaliacaocomp, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNmeroDeAvaliaes))
						.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNmeroDeCompras)
							.addComponent(lncompras)
							.addComponent(lnavaliacoescomp)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNmeroDeVendas)
							.addComponent(lnvendas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lnavaliacoesvend))
						.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblAvaliaoComoVendedor)
							.addComponent(lavaliacaovend)
							.addComponent(label_3)))
					.addContainerGap())
		);
		panel_5.setLayout(gl_panel_5);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (tabbedPane.getSelectedIndex() == 0){
					updaterecebidas();
				}else{
					cbnaolidas.setVisible(false);
					
					updateenviadas();
				}
			}
		});
		
		btlidam = new JButton("");
		btlidam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mensagemDAO.marcaLidaMensagemListada(utilizador.getNomeUtilizador(),(String)jlMensagens.getSelectedValue());
				updaterecebidas();
			}
		});
		btlidam.setToolTipText("Marcar mensagem como lida");
		btlidam.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/lida2.png")));
		btlidam.setContentAreaFilled(false);
		btlidam.setBorderPainted(false);
		
		btremm = new JButton("");
		btremm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getSelectedIndex() == 0){
					int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover a mensagem?", "Aviso", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION){
						MensagemDAO.removeMensagemListada(utilizador.getNomeUtilizador(),(String)jlMensagens.getSelectedValue());
					}
					updaterecebidas();
				}else{
					int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover a mensagem?", "Aviso", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION){
						MensagemDAO.removeMensagemListada(utilizador.getNomeUtilizador(),(String)jlMensagense.getSelectedValue());
					}
					updateenviadas();
				}
			}
		});
		btremm.setToolTipText("Apagar mensagem selecionada");
		btremm.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/remover.png")));
		btremm.setContentAreaFilled(false);
		btremm.setBorderPainted(false);
		
		btaddm = new JButton("");
		btaddm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NovaMensagem(utilizador,PerfilUtilizador.this,null);
			}
		});
		btaddm.setToolTipText("Nova mensagem");
		btaddm.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/adicionar.png")));
		btaddm.setContentAreaFilled(false);
		btaddm.setBorderPainted(false);
		
		btactm = new JButton("");
		btactm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaixm.setEnabled(true);
				if(tabbedPane.getSelectedIndex() == 0){
					updaterecebidas();
				}else{
					updateenviadas();
				}
			}
		});
		
		btactm.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/actualizar.png")));
		btactm.setContentAreaFilled(false);
		btactm.setBorderPainted(false);
		
		btdirm = new JButton("");
		btdirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mensagem mensagem;
				if(tabbedPane.getSelectedIndex() == 0){
					mensagem = mensagemDAO.getMensagemRecebidaListada(utilizador.getNomeUtilizador(),(String)jlMensagens.getSelectedValue());
				}else{
					mensagem = mensagemDAO.getMensagemEnviadaListada(utilizador.getNomeUtilizador(),(String)jlMensagense.getSelectedValue());
				}
				new ConsultarMensagem(utilizador,mensagem,PerfilUtilizador.this);
			}
		});
		btdirm.setToolTipText("Abrir mensagem selecionada");
		btdirm.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/irpara.png")));
		btdirm.setContentAreaFilled(false);
		btdirm.setBorderPainted(false);
		
		btbaixm = new JButton("");
		btbaixm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabbedPane.getSelectedIndex() == 0){
					btcimm.setEnabled(true);
					indexmensagensr += 1;
					
					jlMensagens.setSelectedIndex(indexmensagensr);
					jlMensagens.ensureIndexIsVisible(indexmensagensr);
					
					try {
						if(mensagemDAO.listaMensagensRecebidas(utilizador.getNomeUtilizador()).length == indexmensagensr+1){
							btbaixm.setEnabled(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else{
					btcimm.setEnabled(true);
					indexmensagense += 1;
					
					jlMensagense.setSelectedIndex(indexmensagense);
					jlMensagense.ensureIndexIsVisible(indexmensagense);
					
					try {
						if(mensagemDAO.listaMensagensEnviadas(utilizador.getNomeUtilizador()).length == indexmensagense+1){
							btbaixm.setEnabled(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btbaixm.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/setabaixo.png")));
		btbaixm.setContentAreaFilled(false);
		btbaixm.setBorderPainted(false);
		
		btcimm = new JButton("");
		btcimm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tabbedPane.getSelectedIndex() == 0){
					btbaixm.setEnabled(true);
					indexmensagensr -= 1;
					
					jlMensagens.setSelectedIndex(indexmensagensr);
					jlMensagens.ensureIndexIsVisible(indexmensagensr);
					
					if(indexmensagensr == 0){
						btcimm.setEnabled(false);
					}
				} else {
					btbaixm.setEnabled(true);
					indexmensagense -= 1;
					
					jlMensagense.setSelectedIndex(indexmensagense);
					jlMensagense.ensureIndexIsVisible(indexmensagense);
					
					if(indexmensagense == 0){
						btcimm.setEnabled(false);
					}
				}
			}
		});
		btcimm.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/setacima.png")));
		btcimm.setContentAreaFilled(false);
		btcimm.setBorderPainted(false);
		
		cbnaolidas = new JCheckBox("<html>Apenas não lidas</html>");
		cbnaolidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaixm.setEnabled(true);
				if(cbnaolidas.getSelectedObjects() == null){
					updaterecebidas();
				}else{
					indexmensagensr = 0;
					
					try {
						jlMensagens.setModel(new AbstractListModel() {
							private static final long serialVersionUID = 1L;
							String[] values = mensagemDAO.listaMensagensNaoLidas(utilizador.getNomeUtilizador());
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					jlMensagens.setSelectedIndex(0);
					jlMensagens.ensureIndexIsVisible(0);
					
					btcimm.setEnabled(false);
					btdirm.setEnabled(true);
					btremm.setEnabled(true);
					btlidam.setEnabled(true);
					if (jlMensagens.getSelectedValue() == null){
						btdirm.setEnabled(false);
						btremm.setEnabled(false);
						btbaixm.setEnabled(false);
						btlidam.setEnabled(false);
					}
				}
			}
		});
		cbnaolidas.setFont(new Font("Dialog", Font.PLAIN, 10));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(tabbedPane)
					.addGap(6))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(cbnaolidas)
					.addPreferredGap(ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
					.addComponent(btcimm, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btbaixm, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btdirm, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btactm, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btaddm, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btremm, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btlidam, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(cbnaolidas, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(btaddm, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(btremm, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(btlidam, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(btactm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btdirm, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(btbaixm, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(btcimm, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Recebidas", null, panel_1, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
		);
		
		jlMensagens = new JList();
		jlMensagens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Mensagem mensagem;
						mensagem = mensagemDAO.getMensagemRecebidaListada(utilizador.getNomeUtilizador(),(String)jlMensagens.getSelectedValue());
					new ConsultarMensagem(utilizador,mensagem,PerfilUtilizador.this);
				}
			}
		});
		jlMensagens.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btcimm.setEnabled(true);
				btbaixm.setEnabled(true);
				
				indexmensagensr = jlMensagens.getSelectedIndex();
				jlMensagens.ensureIndexIsVisible(indexmensagensr);
				
				btbaixm.setEnabled(true);
				btdirm.setEnabled(true);
				btremm.setEnabled(true);
				btlidam.setEnabled(true);
				if(indexmensagensr == 0){
					btcimm.setEnabled(false);
				}
				try {
					if (mensagemDAO.listaMensagensRecebidas(utilizador.getNomeUtilizador()).length < 2){
						btbaixm.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (jlMensagens.getSelectedValue() == null){
					btdirm.setEnabled(false);
					btremm.setEnabled(false);
					btlidam.setEnabled(false);
				}
			}
		});
		scrollPane_2.setViewportView(jlMensagens);

			updaterecebidas();
		
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Enviadas", null, panel_3, null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
		);
		
		jlMensagense = new JList();
		jlMensagense.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Mensagem mensagem;
						mensagem = mensagemDAO.getMensagemEnviadaListada(utilizador.getNomeUtilizador(),(String)jlMensagense.getSelectedValue());
					new ConsultarMensagem(utilizador,mensagem,PerfilUtilizador.this);
				}
			}
		});
		jlMensagense.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btcimm.setEnabled(true);
				btbaixm.setEnabled(true);
				
				indexmensagense = jlMensagense.getSelectedIndex();
				jlMensagense.ensureIndexIsVisible(indexmensagense);
				
				btbaixm.setEnabled(true);
				btdirm.setEnabled(true);
				btremm.setEnabled(true);
				if(indexmensagense == 0){
					btcimm.setEnabled(false);
				}
				try {
					if (mensagemDAO.listaMensagensEnviadas(utilizador.getNomeUtilizador()).length < 2){
						btbaixm.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (jlMensagens.getSelectedValue() == null){
					btdirm.setEnabled(false);
					btremm.setEnabled(false);
				}
			}
		});
		jlMensagense.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = mensagemDAO.listaMensagensEnviadas(utilizador.getNomeUtilizador());
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane_3.setViewportView(jlMensagense);
		
		jlhistorico.setSelectedIndex(indexmensagense);
		jlhistorico.ensureIndexIsVisible(indexmensagense);
		
		panel_3.setLayout(gl_panel_3);
		panel_4.setLayout(gl_panel_4);
		
		JButton logo = new JButton("");
		logo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Homev3(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PerfilUtilizador.this.setVisible(false);
				PerfilUtilizador.this.dispose();
			}
		});
		logo.setBorderPainted(false);
		logo.setContentAreaFilled(false);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(PerfilUtilizador.class.getResource("/Imagens/eyelogo.png")));
		
		JLabel lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		cbcategoria = new JComboBox();
		
		ArrayList<String> arrl = categoriaDAO.getListCategorias();
		arrl.add(0,"Todas");
		
		String[] arrays = arrl.toArray(new String[arrl.size()]);
		
		cbcategoria.setModel(new DefaultComboBoxModel(arrays));
		
		cblocalidade = new JComboBox();
		
		ArrayList<String> arrl2 = localidadeDAO.getListLocalidades();
		arrl2.add(0,"Todas");
		
		String[] array2 = arrl2.toArray(new String[arrl2.size()]);
		
		cblocalidade.setModel(new DefaultComboBoxModel(array2));
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblPas = new JLabel("Localidade");
		lblPas.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JButton btnPesquisaAvanada = new JButton("Pesquisar");
		btnPesquisaAvanada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Pesquisa(utilizador, textField.getText(), (String)cbcategoria.getSelectedItem(), (String)cblocalidade.getSelectedItem());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PerfilUtilizador.this.setVisible(false);
				PerfilUtilizador.this.dispose();
			}
		});
		
		JButton btnNewButton = new JButton("Pesquisa Avançada");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new PesquisaAvancada(utilizador);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PerfilUtilizador.this.setVisible(false);
				PerfilUtilizador.this.dispose();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(logo, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
						.addComponent(lblPesquisar, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
						.addComponent(btnPesquisaAvanada, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblCategoria, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
								.addComponent(cbcategoria, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblPas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cblocalidade, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(logo, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPesquisar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPas, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCategoria, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(cblocalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbcategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton)
								.addComponent(btnPesquisaAvanada))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public void updaterecebidas(){
		cbnaolidas.setVisible(true);
		cbnaolidas.setSelected(false);
		
		btlidam.setEnabled(true);
		
		indexmensagensr = 0;
		
		try {
			jlMensagens.setModel(new AbstractListModel() {
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
		
		jlMensagens.setSelectedIndex(indexmensagensr);
		jlMensagens.ensureIndexIsVisible(indexmensagensr);
		
		if(jlMensagens.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    jlMensagens.setCellRenderer(renderer);
		}
		
		btcimm.setEnabled(false);
		btbaixm.setEnabled(true);
		btdirm.setEnabled(true);
		btremm.setEnabled(true);
		btlidam.setEnabled(true);
		try {
			if (mensagemDAO.listaMensagensRecebidas(utilizador.getNomeUtilizador()).length < 2){
				btbaixm.setEnabled(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (jlMensagens.getSelectedValue() == null){
			btdirm.setEnabled(false);
			btremm.setEnabled(false);
			btlidam.setEnabled(false);
		}
	}
	public void updateenviadas(){
		cbnaolidas.setVisible(false);
		btlidam.setEnabled(false);
		indexmensagense = 0;
		
		btlidam.setEnabled(true);
		
		try {
			jlMensagense.setModel(new AbstractListModel() {
				private static final long serialVersionUID = 1L;
				String[] values = mensagemDAO.listaMensagensEnviadas(utilizador.getNomeUtilizador());
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
		
		jlMensagense.setSelectedIndex(indexmensagense);
		jlMensagense.ensureIndexIsVisible(indexmensagense);
		
		if(jlMensagense.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    jlMensagense.setCellRenderer(renderer);
		}
		
		btbaixm.setEnabled(true);
		btcimm.setEnabled(false);
		btdirm.setEnabled(true);
		btremm.setEnabled(true);
		try {
			if (mensagemDAO.listaMensagensEnviadas(utilizador.getNomeUtilizador()).length < 2){
				btbaixm.setEnabled(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (jlMensagens.getSelectedValue() == null){
			btdirm.setEnabled(false);
			btremm.setEnabled(false);
		}
	}
	public void updatetrocas(){
		btbaixt.setEnabled(true);
		indextrocas = 0;
		
		try {
			jllistatrocas.setModel(new AbstractListModel() {
				private static final long serialVersionUID = 1L;
				String[] values = trocaDAO.listaTrocas(utilizador.getNomeUtilizador());
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
		
		jllistatrocas.setSelectedIndex(indextrocas);
		jllistatrocas.ensureIndexIsVisible(indextrocas);
		
		if(jllistatrocas.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(40);
		    jllistatrocas.setCellRenderer(renderer);
		}
		
		btcimt.setEnabled(false);
		btdirt.setEnabled(true);
		btbaixt.setEnabled(true);
		try {
			if (trocaDAO.listaTrocas(utilizador.getNomeUtilizador()).length < 2){
				btbaixt.setEnabled(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (jllistatrocas.getSelectedValue() == null){
			btdirt.setEnabled(false);
		}
	}
	public void updatehistorico() throws SQLException{
		btbaixh.setEnabled(true);
		indexhistorico = 0;
		
		jlhistorico.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = historicoDAO.listaHistorico(utilizador.getNomeUtilizador());
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		jlhistorico.setSelectedIndex(indexhistorico);
		jlhistorico.ensureIndexIsVisible(indexhistorico);
		
		if(jlhistorico.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    jlhistorico.setCellRenderer(renderer);
		}
		
		btcimh.setEnabled(false);
		btbaixh.setEnabled(true);
		if (jlhistorico.getSelectedValue() == null || historicoDAO.listaHistorico(utilizador.getNomeUtilizador()).length < 2){
			btbaixh.setEnabled(false);
		}
	}
	
	public void update() throws SQLException{
		PerfilUtilizador.this.setVisible(false);
		new PerfilUtilizador(UtilizadorDAO.getUtilizador(utilizador.getNomeUtilizador()));
		PerfilUtilizador.this.dispose();
	}
}
