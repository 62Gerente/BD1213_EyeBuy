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

import dao.CategoriaDAO;
import dao.HistoricoDAO;
import dao.LocalidadeDAO;
import dao.MensagemDAO;
import dao.ProdutoDAO;
import dao.TrocaDAO;

import model.Produto;
import model.Utilizador;

import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class VerPerfilUtilizadorv2 extends JFrame implements Observer {

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
	private int indexmensagense = 0;
	
	private JButton btcimt = new JButton();
	private JButton btbaixt = new JButton();
	private JButton btdirt = new JButton();
	private JButton btcimh = new JButton();
	private JButton btbaixh = new JButton();
	
	private JButton btmaisc = new JButton();
	private JButton btmenosc = new JButton();
	private JButton btmaisv = new JButton();
	private JButton btmenosv = new JButton();
	
	private Utilizador aVer = new Utilizador();
	
	private JList jlhistorico = new JList();
	private JList jllistatrocas = new JList();
	
	private JComboBox cblocalidade = new JComboBox();
	private JComboBox cbcategoria = new JComboBox();
	
	private JLabel lbavc = new JLabel();
	private JLabel lbavv = new JLabel();

	private int avc = 0;
	private int avv = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerPerfilUtilizadorv2 frame = new VerPerfilUtilizadorv2(new Utilizador(),new Utilizador());
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
	public VerPerfilUtilizadorv2(Utilizador user, Utilizador ver) throws SQLException {
		produtoDAO.addObserver(this);
		historicoDAO.addObserver(this);
		trocaDAO.addObserver(this);
		mensagemDAO.addObserver(this);
		localidadeDAO.addObserver(this);
		categoriaDAO.addObserver(this);
		utilizador.addObserver(this);
		
		aVer = ver;
		
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
		mnComunidade.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/comm2.png")));
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
		mnCarrinho.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/carr.png")));
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
				VerPerfilUtilizadorv2.this.setVisible(false);
				VerPerfilUtilizadorv2.this.dispose();
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
		mnUtilizador.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/user2.png")));
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
				VerPerfilUtilizadorv2.this.setVisible(false);
				VerPerfilUtilizadorv2.this.dispose();
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
				VerPerfilUtilizadorv2.this.setVisible(false);
				VerPerfilUtilizadorv2.this.dispose();
			}
		});
		mnUtilizador.add(mntmHistrico);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende sair?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					VerPerfilUtilizadorv2.this.setVisible(false);
					VerPerfilUtilizadorv2.this.dispose();
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
				VerPerfilUtilizadorv2.this.setVisible(false);
				VerPerfilUtilizadorv2.this.dispose();
			}
		});
		btnPaginaInicial.setContentAreaFilled(false);
		btnPaginaInicial.setBorderPainted(false);
		btnPaginaInicial.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/home2.png")));
		menuBar.add(btnPaginaInicial);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setOpaque(false);
		contentPane.setFocusCycleRoot(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_6 = new JPanel();
		
		btmaisc = new JButton("");
		btmaisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					historicoDAO.avaliaPosComprador(utilizador,aVer.getNomeUtilizador());
					avc = historicoDAO.getNrAvaliacoesCompraDisponiveis(utilizador,aVer);
					
					lbavc = new JLabel(avc + "");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				avc--;
				if (avc==0){
					btmaisc.setEnabled(false);
					btmenosc.setEnabled(false);
				}
			}
		});
		btmaisc.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/adicionar.png")));
		btmaisc.setContentAreaFilled(false);
		btmaisc.setBorderPainted(false);
		
		btmenosc = new JButton("");
		btmenosc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					historicoDAO.avaliaNegComprador(utilizador,aVer.getNomeUtilizador());
					avc = historicoDAO.getNrAvaliacoesCompraDisponiveis(utilizador,aVer);
					
					lbavc = new JLabel(avc + "");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				avc--;
				if (avc==0){
					btmaisc.setEnabled(false);
					btmenosc.setEnabled(false);
				}
			}
		});
		btmenosc.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/remover.png")));
		btmenosc.setContentAreaFilled(false);
		btmenosc.setBorderPainted(false);
		
		avc = historicoDAO.getNrAvaliacoesCompraDisponiveis(utilizador,aVer);
		
		lbavc = new JLabel(avc + "");
		lbavc.setHorizontalAlignment(SwingConstants.RIGHT);
		
		if(avc == 0){
			btmaisc.setEnabled(false);
			btmenosc.setEnabled(false);
		}
		
		JLabel lblclassificaesComoComprador = new JLabel("<html>Classificações como comprador disponíveis");
		lblclassificaesComoComprador.setFont(new Font("Dialog", Font.BOLD, 12));
		
		btmaisv = new JButton("");
		btmaisv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					historicoDAO.avaliaPosVendedor(utilizador,aVer.getNomeUtilizador());
					avv = historicoDAO.getNrAvaliacoesVendaDisponiveis(utilizador,aVer);
					
					lbavv = new JLabel(avv + "");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				avv--;
				if (avv==0){
					btmaisv.setEnabled(false);
					btmenosv.setEnabled(false);
				}
			}
		});
		btmaisv.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/adicionar.png")));
		btmaisv.setContentAreaFilled(false);
		btmaisv.setBorderPainted(false);
		
		btmenosv = new JButton("");
		btmenosv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					historicoDAO.avaliaNegVendedor(utilizador,aVer.getNomeUtilizador());
					avv = historicoDAO.getNrAvaliacoesVendaDisponiveis(utilizador,aVer);
					
					lbavv = new JLabel(avv + "");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				avv--;
				if (avv==0){
					btmaisv.setEnabled(false);
					btmenosv.setEnabled(false);
				}
			}
		});
		btmenosv.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/remover.png")));
		btmenosv.setContentAreaFilled(false);
		btmenosv.setBorderPainted(false);
		
		avv = historicoDAO.getNrAvaliacoesVendaDisponiveis(utilizador,aVer);
		
		lbavv = new JLabel(avv + "");
		lbavv.setHorizontalAlignment(SwingConstants.RIGHT);
		
		if(avv == 0){
			btmaisv.setEnabled(false);
			btmenosv.setEnabled(false);
		}
		
		JLabel lblclassificaesComoVendedor = new JLabel("<html>Classificações como vendedor disponíveis");
		lblclassificaesComoVendedor.setFont(new Font("Dialog", Font.BOLD, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
							.addGap(12))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
								.addComponent(panel_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btmaisv, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btmenosv, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btmaisc, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btmenosc, 0, 0, Short.MAX_VALUE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lbavv, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
										.addComponent(lbavc, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblclassificaesComoVendedor, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
										.addComponent(lblclassificaesComoComprador, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
							.addGap(3))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btmenosc, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
								.addComponent(lbavc, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
								.addComponent(lblclassificaesComoComprador, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
								.addComponent(btmaisc, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btmenosv, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btmaisv, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lbavv, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblclassificaesComoVendedor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(18)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))))
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
		btacth.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/actualizar.png")));
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
					if(historicoDAO.listaHistorico(aVer.getNomeUtilizador()).length == indexhistorico+1){
						btbaixh.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btbaixh.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/setabaixo.png")));
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
		btcimh.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/setacima.png")));
		btcimh.setContentAreaFilled(false);
		btcimh.setBorderPainted(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Hist\u00F3rico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addComponent(btcimh, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btbaixh, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btacth, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
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
				try {
					updatetrocas();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btactt.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/actualizar.png")));
		btactt.setContentAreaFilled(false);
		btactt.setBorderPainted(false);
		
		btdirt = new JButton("");
		btdirt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produto p = new Produto();
				try {
					p = produtoDAO.getProdutoMontraListado((String)jllistatrocas.getSelectedValue());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				if(p.getMetodoVenda().equals("Leilão")){
					try {
						new VerProduto(utilizador,p);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						new VerProdutoLeilao(utilizador,p);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				VerPerfilUtilizadorv2.this.setVisible(false);
				VerPerfilUtilizadorv2.this.dispose();
				
			}
		});
		btdirt.setToolTipText("Abrir produto selecionado");
		btdirt.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/irpara.png")));
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
		btcimt.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/setacima.png")));
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
					if(produtoDAO.listaMontra(aVer.getNomeUtilizador()).length == indextrocas+1){
						btbaixt.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btbaixt.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/setabaixo.png")));
		btbaixt.setContentAreaFilled(false);
		btbaixt.setBorderPainted(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Montra Produtos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
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
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
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
					Produto p = new Produto();
					try {
						p = produtoDAO.getProdutoMontraListado((String)jllistatrocas.getSelectedValue());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					if(p.getMetodoVenda().equals("Leilão")){
						try {
							new VerProduto(utilizador,p);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						try {
							new VerProdutoLeilao(utilizador,p);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					VerPerfilUtilizadorv2.this.setVisible(false);
					VerPerfilUtilizadorv2.this.dispose();
				}
			}
		});
		jllistatrocas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btcimt.setEnabled(true);
				btbaixt.setEnabled(true);
				btdirt.setEnabled(true);
				
				indextrocas = jlhistorico.getSelectedIndex();
				jllistatrocas.ensureIndexIsVisible(indextrocas);
				
				btdirt.setEnabled(true);
				if(jllistatrocas.getSelectedValue() == null){
					btdirt.setEnabled(false);
				}
				if(indextrocas == 0){
					btcimt.setEnabled(false);
				} else
					try {
						if(produtoDAO.listaMontra(utilizador.getNomeUtilizador()).length == indextrocas+1){
							btbaixt.setEnabled(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		scrollPane_1.setViewportView(jllistatrocas);
		
		updatetrocas();
		
		panel_2.setLayout(gl_panel_2);
		
		JLabel lfoto = new JLabel("");
		lfoto.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/user1.png")));
		

		if (aVer != null){
			Blob blobmv_2 = aVer.getImagem();
			if (blobmv_2 != null){ 
				lfoto.setIcon(new ImageIcon(blobmv_2.getBytes(1, (int)blobmv_2.length())));
			}
		}
		
		lfoto.setHorizontalAlignment(SwingConstants.CENTER);

		
		JLabel lnome = new JLabel("André Augusto Costa Santos");
		
		if (aVer != null){
			lnome = new JLabel(aVer.getNome());
		}
		
		lnome.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lemail = new JLabel("andreccdr@gmail.com");
		
		if (aVer != null){
			lemail = new JLabel(aVer.getEmail());
		}
		
		lemail.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lMorada = new JLabel("Rua Quinta da Aramada, nº41");
		
		if (aVer != null){
			lMorada = new JLabel(aVer.getMorada());
		}
		
		lMorada.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lLocalidade = new JLabel("Braga");
		if (aVer != null){
			lLocalidade = new JLabel(aVer.getLocalidade().getNome());
		}
		lLocalidade.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lContacto = new JLabel("917556233");
		if (aVer != null){
			lContacto = new JLabel(aVer.getTelemovel());
		}
		lContacto.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lcodigopostal = new JLabel("4815-300");
		if (aVer != null){
			lcodigopostal = new JLabel(aVer.getCodigoPostal());
		}
		lcodigopostal.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel ldescricao = new JLabel("Aluno Engenharia Informática");
		if (aVer != null){
			ldescricao = new JLabel("<html> " + aVer.getDescricao());
		}
		ldescricao.setVerticalAlignment(SwingConstants.TOP);
		ldescricao.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblAvaliaoComoComprador = new JLabel("Avaliação como comprador:");
		lblAvaliaoComoComprador.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblAvaliaoComoVendedor = new JLabel("Avaliação como vendedor:");
		lblAvaliaoComoVendedor.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lavaliacaocomp = new JLabel("67%");
		if (aVer != null){
			lavaliacaocomp = new JLabel(aVer.getAvaliacaoComprador() + " %");
		}
		
		JLabel lavaliacaovend = new JLabel("100%");
		if (aVer != null){
			lavaliacaovend = new JLabel(aVer.getAvaliacaoVendedor() + " %");
		}
		
		JLabel lblNmeroDeAvaliaes = new JLabel("Número de avaliações:\n\n");
		lblNmeroDeAvaliaes.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel label_3 = new JLabel("Número de avaliações:\n\n");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lnavaliacoescomp = new JLabel("12");
		if (aVer != null){
			lnavaliacoescomp = new JLabel((aVer.getAvaliacoesPositivas() + aVer.getAvaliacoesNegativas())+"");
		}
		
		JLabel lnavaliacoesvend = new JLabel("6");
		if (aVer != null){
			lnavaliacoesvend = new JLabel((aVer.getNrAvaliacoesVendaPos() + aVer.getNrAvaliacoesVendaNeg())+"");
		}
		
		JLabel lblNmeroDeCompras = new JLabel("Número de compras:\n\n");
		
		lblNmeroDeCompras.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lncompras = new JLabel("12");
		if (aVer != null){
			lncompras = new JLabel(aVer.getNrCompras()+"");
		}
		JLabel lblNmeroDeVendas = new JLabel("Número de vendas:\n\n");
		lblNmeroDeVendas.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lnvendas = new JLabel("6");
		if (aVer != null){
			lnvendas = new JLabel(aVer.getNrVendas()+"");
		}
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/mail.png")));
		button.setDefaultCapable(false);
		button.setContentAreaFilled(false);
		button.setBorder(null);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/tele.png")));
		button_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
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
									.addGap(143))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
										.addComponent(lMorada, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
										.addComponent(lContacto, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
										.addComponent(lcodigopostal, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
										.addComponent(lLocalidade, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
									.addGap(168))))
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
				.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
					.addContainerGap(596, Short.MAX_VALUE)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addGap(31)
									.addComponent(lnome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lemail)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lMorada)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lcodigopostal)
									.addGap(8)
									.addComponent(lLocalidade)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lContacto))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addContainerGap()
									.addComponent(lfoto, 0, 0, Short.MAX_VALUE)))
							.addGap(33))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(ldescricao, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
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

		
		jlhistorico.setSelectedIndex(indexmensagense);
		jlhistorico.ensureIndexIsVisible(indexmensagense);
		
		JButton logo = new JButton("");
		logo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Homev3(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				VerPerfilUtilizadorv2.this.setVisible(false);
				VerPerfilUtilizadorv2.this.dispose();
			}
		});
		logo.setBorderPainted(false);
		logo.setContentAreaFilled(false);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(VerPerfilUtilizadorv2.class.getResource("/Imagens/eyelogo.png")));
		
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
				VerPerfilUtilizadorv2.this.setVisible(false);
				VerPerfilUtilizadorv2.this.dispose();
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
				VerPerfilUtilizadorv2.this.setVisible(false);
				VerPerfilUtilizadorv2.this.dispose();
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
	public void updatetrocas() throws SQLException{
		btbaixt.setEnabled(true);
		indextrocas = 0;
		
		jllistatrocas.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = produtoDAO.listaMontra(aVer.getNomeUtilizador());
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		jllistatrocas.setSelectedIndex(indextrocas);
		jllistatrocas.ensureIndexIsVisible(indextrocas);
		
		if(jllistatrocas.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    jllistatrocas.setCellRenderer(renderer);
		}
		
		btcimt.setEnabled(false);
		btdirt.setEnabled(true);
		btbaixt.setEnabled(true);
		if (produtoDAO.listaMontra(aVer.getNomeUtilizador()).length < 2){
			btbaixt.setEnabled(false);
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
			String[] values = historicoDAO.listaHistorico(aVer.getNomeUtilizador());
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
		if (jlhistorico.getSelectedValue() == null || historicoDAO.listaHistorico(aVer.getNomeUtilizador()).length < 2){
			btbaixh.setEnabled(false);
		}
	}
	
	public void update(){
		try {
			updatehistorico();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			updatetrocas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
