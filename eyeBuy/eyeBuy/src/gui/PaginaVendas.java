package gui;

import model.EyeBuy;
import model.Produto;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractListModel;
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

import model.Utilizador;
import javax.swing.JScrollPane;

import dao.CategoriaDAO;
import dao.HistoricoDAO;
import dao.ImagemDAO;
import dao.LocalidadeDAO;
import dao.ProdutoDAO;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class PaginaVendas extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private EyeBuy eyebuy = new EyeBuy();
	private ImagemDAO imagemDAO = new ImagemDAO();
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private HistoricoDAO historicoDAO = new HistoricoDAO();
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private LocalidadeDAO localidadeDAO = new LocalidadeDAO();
	
	private Utilizador utilizador = new Utilizador();

	private JButton btaddmp = new JButton();
	private JButton btdirmp = new JButton();
	private JButton btesqmp = new JButton();
	private JButton btactmp = new JButton();

	private JPanel painelmp_3 = new JPanel();
	private JButton imagemmp_3 = new JButton();
	private JLabel lbprecomp_3;
	private JLabel lbusermp_3;
	private JLabel lbdatamp_3;
	private JPanel painelmp_2 = new JPanel();
	private JButton imagemmp_2 = new JButton();
	private JLabel lbprecomp_2;
	private JLabel lbusermp_2;
	private JLabel lbdatamp_2;
	private JPanel painelmp_1 = new JPanel();
	private JButton imagemmp_1 = new JButton();
	private JLabel lbprecomp_1;
	private JLabel lbusermp_1;
	private JLabel lbdatamp_1;
	private JPanel painelmp = new JPanel();
	private JLabel lbprecomp;
	private JButton imagemmp = new JButton();
	private JLabel lbusermp;
	private JLabel lbdatamp;
	
	
	private int posicaoactualmp = 0;
	private int nrmp = 4;
	private ArrayList<Produto> produtosmp = new ArrayList<Produto>();
	
	private JButton btdirpr = new JButton();
	private JButton btesqpr = new JButton();
	private JButton btactpr = new JButton();

	private JPanel painelpr_2 = new JPanel();
	private JButton imagempr_2 = new JButton();
	private JLabel lbprecopr_2;
	private JLabel lbuserpr_2;
	private JLabel lbdatapr_2;
	private JPanel painelpr_1 = new JPanel();
	private JButton imagempr_1 = new JButton();
	private JLabel lbprecopr_1;
	private JLabel lbuserpr_1;
	private JLabel lbdatapr_1;
	private JPanel painelpr = new JPanel();
	private JLabel lbprecopr;
	private JButton imagempr = new JButton();
	private JLabel lbuserpr;
	private JLabel lbdatapr;
	
	
	private int posicaoactualpr = 0;
	private int nrpr = 3;
	private ArrayList<Produto> produtospr = new ArrayList<Produto>();
	
	private JButton btbaixuv = new JButton();
	private JButton btcimuv = new JButton();
	private JButton btactuv = new JButton();
	private JList jlrelacionados = new JList();
	
	private int indexuv = 0;
	
	private JComboBox cblocalidade = new JComboBox();
	private JComboBox cbcategoria = new JComboBox();
	
	JLabel lbnomemp = new JLabel();
	JLabel lbnomemp_1 = new JLabel();
	JLabel lbnomemp_2 = new JLabel();
	JLabel lbnomemp_3 = new JLabel();
	
	JLabel lbnomepr = new JLabel();
	JLabel lbnomepr_1 = new JLabel();
	JLabel lbnomepr_2 = new JLabel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaginaVendas frame = new PaginaVendas(new Utilizador());
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
	public PaginaVendas(Utilizador user) throws SQLException {
		produtoDAO.addObserver(this);
		historicoDAO.addObserver(this);
		imagemDAO.addObserver(this);
		eyebuy.addObserver(this);
		localidadeDAO.addObserver(this);
		categoriaDAO.addObserver(this);

		
		if(user != null){
			this.utilizador = user.clone();
			utilizador.addObserver(this);
		}
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 725);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Comunidade ▼ \n");
		mnNewMenu.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/comunidade.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSobreOEyebuy = new JMenuItem("Sobre o eyeBuy");
		mntmSobreOEyebuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SobreNos();
			}
		});
		mnNewMenu.add(mntmSobreOEyebuy);
		
		JMenuItem mntmAjuda = new JMenuItem("Ajuda");
		mntmAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Ajuda();
			}
		});
		mnNewMenu.add(mntmAjuda);
		
		JMenu mnCarrinho = new JMenu("Carrinho ▼  ");
		mnCarrinho.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/carr.png")));
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
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
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
		mnUtilizador.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/user2.png")));
		menuBar.add(mnUtilizador);
		
		JMenuItem mntmPerfilDeUtilizador = new JMenuItem("Perfil de Utilizador");
		mntmPerfilDeUtilizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PerfilUtilizador(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
			}
		});
		mnUtilizador.add(mntmPerfilDeUtilizador);
		
		JMenuItem mntmHistrico = new JMenuItem("Histórico");
		mntmHistrico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new HistoricoGUI(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
			}
		});
		mnUtilizador.add(mntmHistrico);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende sair?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					PaginaVendas.this.setVisible(false);
					PaginaVendas.this.dispose();
				}
			}
		});
		mnUtilizador.add(mntmSair);
		
		JButton btnNewButton_1 = new JButton("Página Inicial");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Homev3(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/home2.png")));
		menuBar.add(btnNewButton_1);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setOpaque(false);
		contentPane.setFocusCycleRoot(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Montra de produtos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Produtos Relacionados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "\u00DAltimas Vendas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
							.addGap(12))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		btactuv = new JButton("");
		btactuv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateuv();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btactuv.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/actualizar.png")));
		btactuv.setContentAreaFilled(false);
		btactuv.setBorderPainted(false);
		
		btcimuv = new JButton("");
		btcimuv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaixuv.setEnabled(true);
				indexuv -= 1;
				
				jlrelacionados.setSelectedIndex(indexuv);
				jlrelacionados.ensureIndexIsVisible(indexuv);
				
				if(indexuv == 0){
					btcimuv.setEnabled(false);
				}
			}
		});
		btcimuv.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/setacima.png")));
		btcimuv.setContentAreaFilled(false);
		btcimuv.setBorderPainted(false);
		
		btbaixuv = new JButton("");
		btbaixuv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcimuv.setEnabled(true);
				indexuv += 1;
				
				jlrelacionados.setSelectedIndex(indexuv);
				jlrelacionados.ensureIndexIsVisible(indexuv);
				
				try {
					if(historicoDAO.listaUltimasVendas(utilizador.getNomeUtilizador()).length == indexuv+1){
						btbaixuv.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btbaixuv.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/setabaixo.png")));
		btbaixuv.setContentAreaFilled(false);
		btbaixuv.setBorderPainted(false);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
							.addComponent(btbaixuv, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btcimuv, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btactuv, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
					.addGap(9)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(btcimuv, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btactuv, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btbaixuv, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		jlrelacionados = new JList();
		jlrelacionados.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				btcimuv.setEnabled(true);
				btbaixuv.setEnabled(true);
				
				indexuv = jlrelacionados.getSelectedIndex();
				jlrelacionados.ensureIndexIsVisible(indexuv);
				
				if(indexuv == 0){
					btcimuv.setEnabled(false);
				} else
					try {
						if(historicoDAO.listaUltimasVendas(utilizador.getNomeUtilizador()).length == indexuv+1){
							btbaixuv.setEnabled(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		jlrelacionados.setBorder(null);
		
		updateuv();
		
		
		scrollPane.setViewportView(jlrelacionados);
		jlrelacionados.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_4.setLayout(gl_panel_4);
		
		painelpr = new JPanel();
		
		imagempr = new JButton("");
		imagempr.setContentAreaFilled(false);
		imagempr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtospr.get(0);
				
				if(produto.getMetodoVenda().equals("Leilão")){
					try {
						new VerProdutoLeilao(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					try {
						new VerProduto(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
			}
		});
		imagempr.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/financiamento_de_carros_sem_burocracia_e_sem_entrada_96752223312697652.jpg")));
		
		lbprecopr = new JLabel("10,00 €");
		lbprecopr.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecopr.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuserpr = new JLabel("Gerente62");
		lbuserpr.setVerticalAlignment(SwingConstants.TOP);
		lbuserpr.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserpr.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatapr = new JLabel("12/10/2012");
		lbdatapr.setVerticalAlignment(SwingConstants.TOP);
		lbdatapr.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatapr.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomepr = new JLabel("Nome Produto");
		lbnomepr.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomepr.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelpr = new GroupLayout(painelpr);
		gl_painelpr.setHorizontalGroup(
			gl_painelpr.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpr.createSequentialGroup()
					.addGroup(gl_painelpr.createParallelGroup(Alignment.TRAILING)
						.addComponent(imagempr, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 149, Short.MAX_VALUE)
						.addComponent(lbnomepr, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addComponent(lbprecopr, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addGroup(gl_painelpr.createSequentialGroup()
							.addComponent(lbuserpr, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatapr)))
					.addGap(0))
		);
		gl_painelpr.setVerticalGroup(
			gl_painelpr.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpr.createSequentialGroup()
					.addGroup(gl_painelpr.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatapr)
						.addComponent(lbuserpr))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagempr, GroupLayout.PREFERRED_SIZE, 123, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbnomepr, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbprecopr, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelpr.setLayout(gl_painelpr);
		
		painelpr_1 = new JPanel();
		
		imagempr_1 = new JButton("");
		imagempr_1.setContentAreaFilled(false);
		imagempr_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtospr.get(1);
				
				if(produto.getMetodoVenda().equals("Leilão")){
					try {
						new VerProdutoLeilao(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
										try {
						new VerProduto(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
			}
		});
		imagempr_1.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/financiamento_de_carros_sem_burocracia_e_sem_entrada_96752223312697652.jpg")));
		
		lbprecopr_1 = new JLabel("10,00 €");
		lbprecopr_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecopr_1.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuserpr_1 = new JLabel("Gerente62");
		lbuserpr_1.setVerticalAlignment(SwingConstants.TOP);
		lbuserpr_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserpr_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatapr_1 = new JLabel("12/10/2012");
		lbdatapr_1.setVerticalAlignment(SwingConstants.TOP);
		lbdatapr_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatapr_1.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomepr_1 = new JLabel("Nome Produto");
		lbnomepr_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomepr_1.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelpr_1 = new GroupLayout(painelpr_1);
		gl_painelpr_1.setHorizontalGroup(
			gl_painelpr_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpr_1.createSequentialGroup()
					.addGroup(gl_painelpr_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(imagempr_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE)
						.addComponent(lbnomepr_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
						.addComponent(lbprecopr_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
						.addGroup(gl_painelpr_1.createSequentialGroup()
							.addComponent(lbuserpr_1, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatapr_1)))
					.addGap(0))
		);
		gl_painelpr_1.setVerticalGroup(
			gl_painelpr_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpr_1.createSequentialGroup()
					.addGroup(gl_painelpr_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatapr_1)
						.addComponent(lbuserpr_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagempr_1, GroupLayout.PREFERRED_SIZE, 124, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbnomepr_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbprecopr_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelpr_1.setLayout(gl_painelpr_1);
		
		painelpr_2 = new JPanel();
		
		imagempr_2 = new JButton("");
		imagempr_2.setContentAreaFilled(false);
		imagempr_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtospr.get(2);
				
				if(produto.getMetodoVenda().equals("Leilão")){
					try {
						new VerProdutoLeilao(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
										try {
						new VerProduto(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
			}
		});
		imagempr_2.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/financiamento_de_carros_sem_burocracia_e_sem_entrada_96752223312697652.jpg")));
		
		lbprecopr_2 = new JLabel("10,00 €");
		lbprecopr_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecopr_2.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuserpr_2 = new JLabel("Gerente62");
		lbuserpr_2.setVerticalAlignment(SwingConstants.TOP);
		lbuserpr_2.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserpr_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatapr_2 = new JLabel("12/10/2012");
		lbdatapr_2.setVerticalAlignment(SwingConstants.TOP);
		lbdatapr_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatapr_2.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomepr_2 = new JLabel("Nome Produto");
		lbnomepr_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomepr_2.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelpr_2 = new GroupLayout(painelpr_2);
		gl_painelpr_2.setHorizontalGroup(
			gl_painelpr_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpr_2.createSequentialGroup()
					.addGroup(gl_painelpr_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(imagempr_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 145, Short.MAX_VALUE)
						.addComponent(lbnomepr_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(lbprecopr_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addGroup(gl_painelpr_2.createSequentialGroup()
							.addComponent(lbuserpr_2, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatapr_2)))
					.addGap(0))
		);
		gl_painelpr_2.setVerticalGroup(
			gl_painelpr_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpr_2.createSequentialGroup()
					.addGroup(gl_painelpr_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatapr_2)
						.addComponent(lbuserpr_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagempr_2, GroupLayout.PREFERRED_SIZE, 124, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbnomepr_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbprecopr_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelpr_2.setLayout(gl_painelpr_2);
		
		JPanel panel_5 = new JPanel();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(painelpr, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(painelpr_1, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(painelpr_2, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(painelpr, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(painelpr_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(painelpr_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(15)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		btdirpr = new JButton("");
		btdirpr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualpr += nrpr;
				try {
					updatepr();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btdirpr.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/setafrente.png")));
		btdirpr.setContentAreaFilled(false);
		btdirpr.setBorderPainted(false);
		
		btesqpr = new JButton("");
		btesqpr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualpr -= nrpr;
				try {
					updatepr();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btesqpr.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/setaptras.png")));
		btesqpr.setContentAreaFilled(false);
		btesqpr.setBorderPainted(false);
		
		btactpr = new JButton("");
		btactpr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualpr = 0;
				try {
					updatepr();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btactpr.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/actualizar.png")));
		btactpr.setContentAreaFilled(false);
		btactpr.setBorderPainted(false);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap(358, Short.MAX_VALUE)
					.addComponent(btesqpr, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btdirpr, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btactpr, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(btesqpr, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btdirpr, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btactpr, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_5.setLayout(gl_panel_5);
		panel_3.setLayout(gl_panel_3);
		
		painelmp = new JPanel();
		
		imagemmp = new JButton("");
		imagemmp.setContentAreaFilled(false);
		imagemmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosmp.get(0);
				
				if(produto.getMetodoVenda().equals("Leilão")){
					try {
						new VerProdutoLeilao(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
										try {
						new VerProduto(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
			}
		});
		imagemmp.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/cenoura.jpg")));
		
		lbprecomp = new JLabel("10,00 €");
		lbprecomp.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecomp.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbusermp = new JLabel("Gerente62");
		lbusermp.setVerticalAlignment(SwingConstants.TOP);
		lbusermp.setHorizontalAlignment(SwingConstants.LEFT);
		lbusermp.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatamp = new JLabel("12/10/2012");
		lbdatamp.setVerticalAlignment(SwingConstants.TOP);
		lbdatamp.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatamp.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		JButton btremmp = new JButton("");
		btremmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover da montra de produtos?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtosmp.get(0);
					try {
						produtoDAO.removeProdutoMontra(utilizador.getNomeUtilizador(), produto.getId());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						updatemp();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btremmp.setContentAreaFilled(false);
		btremmp.setBorderPainted(false);
		btremmp.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/remover16.png")));
		btremmp.setHorizontalAlignment(SwingConstants.CENTER);
		btremmp.setFont(new Font("Dialog", Font.BOLD, 10));
		
		JButton bteditamp = new JButton("");
		bteditamp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosmp.get(0);
				try {
					new EditarProduto(utilizador,produto,PaginaVendas.this);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bteditamp.setContentAreaFilled(false);
		bteditamp.setBorderPainted(false);
		bteditamp.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/edit16.png")));
		bteditamp.setHorizontalAlignment(SwingConstants.CENTER);
		bteditamp.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbnomemp = new JLabel("Nome Produto");
		lbnomemp.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomemp.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelmp = new GroupLayout(painelmp);
		gl_painelmp.setHorizontalGroup(
			gl_painelmp.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmp.createSequentialGroup()
					.addGroup(gl_painelmp.createParallelGroup(Alignment.TRAILING)
						.addComponent(imagemmp, GroupLayout.PREFERRED_SIZE, 219, Short.MAX_VALUE)
						.addComponent(lbnomemp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
						.addGroup(gl_painelmp.createSequentialGroup()
							.addComponent(lbusermp, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatamp))
						.addGroup(gl_painelmp.createSequentialGroup()
							.addComponent(lbprecomp, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bteditamp, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btremmp, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
		);
		gl_painelmp.setVerticalGroup(
			gl_painelmp.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmp.createSequentialGroup()
					.addGroup(gl_painelmp.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatamp)
						.addComponent(lbusermp))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemmp, GroupLayout.PREFERRED_SIZE, 151, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbnomemp, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelmp.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_painelmp.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbprecomp, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
							.addComponent(btremmp, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addComponent(bteditamp, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
		);
		painelmp.setLayout(gl_painelmp);
		
		painelmp_1 = new JPanel();
		
		JButton btremmp_1 = new JButton("");
		btremmp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover da montra de produtos?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtosmp.get(1);
					try {
						produtoDAO.removeProdutoMontra(utilizador.getNomeUtilizador(),produto.getId());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						updatemp();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btremmp_1.setContentAreaFilled(false);
		btremmp_1.setBorderPainted(false);
		btremmp_1.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/remover16.png")));
		btremmp_1.setHorizontalAlignment(SwingConstants.CENTER);
		btremmp_1.setFont(new Font("Dialog", Font.BOLD, 10));
		
		JButton bteditamp_1 = new JButton("");
		bteditamp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosmp.get(1);
				try {
					new EditarProduto(utilizador,produto,PaginaVendas.this);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bteditamp_1.setContentAreaFilled(false);
		bteditamp_1.setBorderPainted(false);
		bteditamp_1.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/edit16.png")));
		bteditamp_1.setHorizontalAlignment(SwingConstants.CENTER);
		bteditamp_1.setFont(new Font("Dialog", Font.BOLD, 10));
		
		imagemmp_1 = new JButton("");
		imagemmp_1.setContentAreaFilled(false);
		imagemmp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosmp.get(1);
				
				if(produto.getMetodoVenda().equals("Leilão")){
					try {
						new VerProdutoLeilao(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
										try {
						new VerProduto(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
			}
		});
		imagemmp_1.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/esplanad.JPG")));
		
		lbprecomp_1 = new JLabel("10,00 €");
		lbprecomp_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecomp_1.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbusermp_1 = new JLabel("Gerente62");
		lbusermp_1.setVerticalAlignment(SwingConstants.TOP);
		lbusermp_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbusermp_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatamp_1 = new JLabel("12/10/2012");
		lbdatamp_1.setVerticalAlignment(SwingConstants.TOP);
		lbdatamp_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatamp_1.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomemp_1 = new JLabel("Nome Produto");
		lbnomemp_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomemp_1.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelmp_1 = new GroupLayout(painelmp_1);
		gl_painelmp_1.setHorizontalGroup(
			gl_painelmp_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmp_1.createSequentialGroup()
					.addGroup(gl_painelmp_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(imagemmp_1, GroupLayout.PREFERRED_SIZE, 219, Short.MAX_VALUE)
						.addComponent(lbnomemp_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
						.addGroup(gl_painelmp_1.createSequentialGroup()
							.addComponent(lbusermp_1, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatamp_1))
						.addGroup(gl_painelmp_1.createSequentialGroup()
							.addComponent(lbprecomp_1, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bteditamp_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btremmp_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
		);
		gl_painelmp_1.setVerticalGroup(
			gl_painelmp_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmp_1.createSequentialGroup()
					.addGroup(gl_painelmp_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatamp_1)
						.addComponent(lbusermp_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemmp_1, GroupLayout.PREFERRED_SIZE, 151, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbnomemp_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelmp_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_painelmp_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbprecomp_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
							.addComponent(btremmp_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addComponent(bteditamp_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
		);
		painelmp_1.setLayout(gl_painelmp_1);
		
		painelmp_2 = new JPanel();
		
		JButton btremmp_2 = new JButton("");
		btremmp_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover da montra de produtos?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtosmp.get(2);
					try {
						produtoDAO.removeProdutoMontra(utilizador.getNomeUtilizador(),produto.getId());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						updatemp();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btremmp_2.setContentAreaFilled(false);
		btremmp_2.setBorderPainted(false);
		btremmp_2.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/remover16.png")));
		btremmp_2.setHorizontalAlignment(SwingConstants.CENTER);
		btremmp_2.setFont(new Font("Dialog", Font.BOLD, 10));
		
		JButton bteditamp_2 = new JButton("");
		bteditamp_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosmp.get(2);
				try {
					new EditarProduto(utilizador,produto,PaginaVendas.this);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bteditamp_2.setContentAreaFilled(false);
		bteditamp_2.setBorderPainted(false);
		bteditamp_2.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/edit16.png")));
		bteditamp_2.setHorizontalAlignment(SwingConstants.CENTER);
		bteditamp_2.setFont(new Font("Dialog", Font.BOLD, 10));
		
		imagemmp_2 = new JButton("");
		imagemmp_2.setContentAreaFilled(false);
		imagemmp_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosmp.get(2);
				
				if(produto.getMetodoVenda().equals("Leilão")){
					try {
						new VerProdutoLeilao(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
										try {
						new VerProduto(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
			}
		});
		imagemmp_2.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/kamikaze.jpg")));
		
		lbprecomp_2 = new JLabel("10,00 €");
		lbprecomp_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecomp_2.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbusermp_2 = new JLabel("Gerente62");
		lbusermp_2.setVerticalAlignment(SwingConstants.TOP);
		lbusermp_2.setHorizontalAlignment(SwingConstants.LEFT);
		lbusermp_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatamp_2 = new JLabel("12/10/2012");
		lbdatamp_2.setVerticalAlignment(SwingConstants.TOP);
		lbdatamp_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatamp_2.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomemp_2 = new JLabel("Nome Produto");
		lbnomemp_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomemp_2.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelmp_2 = new GroupLayout(painelmp_2);
		gl_painelmp_2.setHorizontalGroup(
			gl_painelmp_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmp_2.createSequentialGroup()
					.addGroup(gl_painelmp_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnomemp_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
						.addComponent(imagemmp_2, GroupLayout.PREFERRED_SIZE, 219, Short.MAX_VALUE)
						.addGroup(gl_painelmp_2.createSequentialGroup()
							.addComponent(lbusermp_2, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatamp_2))
						.addGroup(gl_painelmp_2.createSequentialGroup()
							.addComponent(lbprecomp_2, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bteditamp_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btremmp_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
		);
		gl_painelmp_2.setVerticalGroup(
			gl_painelmp_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmp_2.createSequentialGroup()
					.addGroup(gl_painelmp_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatamp_2)
						.addComponent(lbusermp_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemmp_2, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomemp_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelmp_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_painelmp_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbprecomp_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
							.addComponent(btremmp_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addComponent(bteditamp_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
		);
		painelmp_2.setLayout(gl_painelmp_2);
		
		painelmp_3 = new JPanel();
		
		JButton btremmp_3 = new JButton("");
		btremmp_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover da montra de produtos?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtosmp.get(3);
					try {
						produtoDAO.removeProdutoMontra(utilizador.getNomeUtilizador(),produto.getId());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						updatemp();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btremmp_3.setContentAreaFilled(false);
		btremmp_3.setBorderPainted(false);
		btremmp_3.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/remover16.png")));
		btremmp_3.setHorizontalAlignment(SwingConstants.CENTER);
		btremmp_3.setFont(new Font("Dialog", Font.BOLD, 10));
		
		JButton bteditamp_3 = new JButton("");
		bteditamp_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosmp.get(3);
				try {
					new EditarProduto(utilizador,produto,PaginaVendas.this);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bteditamp_3.setContentAreaFilled(false);
		bteditamp_3.setBorderPainted(false);
		bteditamp_3.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/edit16.png")));
		bteditamp_3.setHorizontalAlignment(SwingConstants.CENTER);
		bteditamp_3.setFont(new Font("Dialog", Font.BOLD, 10));
		
		imagemmp_3 = new JButton("");
		imagemmp_3.setContentAreaFilled(false);
		imagemmp_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosmp.get(3);
				
				if(produto.getMetodoVenda().equals("Leilão")){
					try {
						new VerProdutoLeilao(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
										try {
						new VerProduto(utilizador,produto);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
			}
		});
		imagemmp_3.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/insatisfeito.jpg")));
		
		lbprecomp_3 = new JLabel("10,00 €");
		lbprecomp_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecomp_3.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbusermp_3 = new JLabel("Gerente62");
		lbusermp_3.setVerticalAlignment(SwingConstants.TOP);
		lbusermp_3.setHorizontalAlignment(SwingConstants.LEFT);
		lbusermp_3.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatamp_3 = new JLabel("12/10/2012");
		lbdatamp_3.setVerticalAlignment(SwingConstants.TOP);
		lbdatamp_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatamp_3.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomemp_3 = new JLabel("Nome Produto");
		lbnomemp_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomemp_3.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelmp_3 = new GroupLayout(painelmp_3);
		gl_painelmp_3.setHorizontalGroup(
			gl_painelmp_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmp_3.createSequentialGroup()
					.addGroup(gl_painelmp_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnomemp_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
						.addComponent(imagemmp_3, GroupLayout.PREFERRED_SIZE, 219, Short.MAX_VALUE)
						.addGroup(gl_painelmp_3.createSequentialGroup()
							.addComponent(lbusermp_3, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatamp_3))
						.addGroup(gl_painelmp_3.createSequentialGroup()
							.addComponent(lbprecomp_3, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bteditamp_3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btremmp_3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
		);
		gl_painelmp_3.setVerticalGroup(
			gl_painelmp_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmp_3.createSequentialGroup()
					.addGroup(gl_painelmp_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatamp_3)
						.addComponent(lbusermp_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemmp_3, GroupLayout.PREFERRED_SIZE, 147, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbnomemp_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelmp_3.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_painelmp_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbprecomp_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
							.addComponent(btremmp_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addComponent(bteditamp_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
		);
		painelmp_3.setLayout(gl_painelmp_3);
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(painelmp, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(painelmp_1, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(painelmp_2, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(painelmp_3, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(painelmp, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(painelmp_1, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
							.addGap(12)))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(painelmp_2, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
						.addComponent(painelmp_3, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
		);
		
		btesqmp = new JButton("");
		btesqmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualmp -= nrmp;
				try {
					updatemp();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btesqmp.setContentAreaFilled(false);
		btesqmp.setBorderPainted(false);
		btesqmp.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/setaptras.png")));
		
		btdirmp = new JButton("");
		btdirmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				posicaoactualmp += nrmp;
				try {
					updatemp();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btdirmp.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/irpara.png")));
		btdirmp.setContentAreaFilled(false);
		btdirmp.setBorderPainted(false);
		
		btaddmp = new JButton("");
		btaddmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdicionarVenda(utilizador,PaginaVendas.this);
			}
		});
		btaddmp.setToolTipText("Colocar produto á venda");
		btaddmp.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/add32.png")));
		btaddmp.setContentAreaFilled(false);
		btaddmp.setBorderPainted(false);
		
		btactmp = new JButton("");
		btactmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualmp = 0;
				try {
					updatemp();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btactmp.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/actualizar.png")));
		btactmp.setContentAreaFilled(false);
		btactmp.setBorderPainted(false);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(322, Short.MAX_VALUE)
					.addComponent(btesqmp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btdirmp, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btactmp, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btaddmp, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btaddmp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btactmp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btdirmp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btesqmp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		panel_1.setLayout(gl_panel_1);
		
		JButton logo = new JButton("");
		logo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Homev3(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
			}
		});
		logo.setContentAreaFilled(false);
		logo.setBorderPainted(false);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(PaginaVendas.class.getResource("/Imagens/eyelogo.png")));
		
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
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
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
				PaginaVendas.this.setVisible(false);
				PaginaVendas.this.dispose();
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
		
		updatemp();
		updatepr();
	}
	
	private void updatemp() throws SQLException {
		btdirmp.setEnabled(true);
		btesqmp.setEnabled(true);
		
		painelmp.setVisible(true);
		painelmp_1.setVisible(true);
		painelmp_2.setVisible(true);
		painelmp_3.setVisible(true);
		
		produtosmp = produtoDAO.getMontraProdutos(utilizador.getNomeUtilizador(),posicaoactualmp,nrmp);

		
		int arraysize = produtosmp.size();
		
		if(posicaoactualmp == 0){
			btesqmp.setEnabled(false);
		}
	
		if (arraysize == 0){
			btdirmp.setEnabled(false);
			btesqmp.setEnabled(false);
			
			
			painelmp.setVisible(false);
			painelmp_1.setVisible(false);
			painelmp_2.setVisible(false);
			painelmp_3.setVisible(false);
			
		}else if(arraysize == 1){
			
			Produto produtomp = produtosmp.get(0);
			Blob blobmp = imagemDAO.getImagemProduto(produtomp.getId());
			
			imagemmp.setIcon(new ImageIcon(blobmp.getBytes(1, (int)blobmp.length())));
			lbprecomp.setText(produtomp.getPreco() + " €");
			lbusermp.setText(produtomp.getUtilizador().getNomeUtilizador());
			lbdatamp.setText(eyebuy.mostradata(produtomp.getDataColocacao()));
			lbnomemp.setText(produtomp.getNome());
			
			btdirmp.setEnabled(false);
			
			painelmp_1.setVisible(false);
			painelmp_2.setVisible(false);
			painelmp_3.setVisible(false);
			
		}else if(arraysize == 2){
			
			Produto produtomp = produtosmp.get(0);
			Blob blobmp = imagemDAO.getImagemProduto(produtomp.getId());
			
			imagemmp.setIcon(new ImageIcon(blobmp.getBytes(1, (int)blobmp.length())));
			lbprecomp.setText(produtomp.getPreco() + " €");
			lbusermp.setText(produtomp.getUtilizador().getNomeUtilizador());
			lbdatamp.setText(eyebuy.mostradata(produtomp.getDataColocacao()));
			lbnomemp.setText(produtomp.getNome());
			
			Produto produtomp_1 = produtosmp.get(1);
			Blob blobmp_1 = imagemDAO.getImagemProduto(produtomp_1.getId());
			
			imagemmp_1.setIcon(new ImageIcon(blobmp_1.getBytes(1, (int)blobmp_1.length())));
			lbprecomp_1.setText(produtomp_1.getPreco() + " €");
			lbusermp_1.setText(produtomp_1.getUtilizador().getNomeUtilizador());
			lbdatamp_1.setText(eyebuy.mostradata(produtomp_1.getDataColocacao()));
			lbnomemp_1.setText(produtomp_1.getNome());
			
			btdirmp.setEnabled(false);

			painelmp_2.setVisible(false);
			painelmp_3.setVisible(false);
		
		}else if(arraysize == 3){
		
			Produto produtomp = produtosmp.get(0);
			Blob blobmp = imagemDAO.getImagemProduto(produtomp.getId());
			
			imagemmp.setIcon(new ImageIcon(blobmp.getBytes(1, (int)blobmp.length())));
			lbprecomp.setText(produtomp.getPreco() + " €");
			lbusermp.setText(produtomp.getUtilizador().getNomeUtilizador());
			lbdatamp.setText(eyebuy.mostradata(produtomp.getDataColocacao()));
			lbnomemp.setText(produtomp.getNome());
			
			Produto produtomp_1 = produtosmp.get(1);
			Blob blobmp_1 = imagemDAO.getImagemProduto(produtomp_1.getId());
			
			imagemmp_1.setIcon(new ImageIcon(blobmp_1.getBytes(1, (int)blobmp_1.length())));
			lbprecomp_1.setText(produtomp_1.getPreco() + " €");
			lbusermp_1.setText(produtomp_1.getUtilizador().getNomeUtilizador());
			lbdatamp_1.setText(eyebuy.mostradata(produtomp_1.getDataColocacao()));
			lbnomemp_1.setText(produtomp_1.getNome());
			
			Produto produtomp_2 = produtosmp.get(2);
			Blob blobmp_2 = imagemDAO.getImagemProduto(produtomp_2.getId());
			
			imagemmp_2.setIcon(new ImageIcon(blobmp_2.getBytes(1, (int)blobmp_2.length())));
			lbprecomp_2.setText(produtomp_2.getPreco() + " €");
			lbusermp_2.setText(produtomp_2.getUtilizador().getNomeUtilizador());
			lbdatamp_2.setText(eyebuy.mostradata(produtomp_2.getDataColocacao()));
			lbnomemp_2.setText(produtomp_2.getNome());
			
			btdirmp.setEnabled(false);

			painelmp_3.setVisible(false);
		
		}else{
			
			Produto produtomp = produtosmp.get(0);
			Blob blobmp = imagemDAO.getImagemProduto(produtomp.getId());
			
			imagemmp.setIcon(new ImageIcon(blobmp.getBytes(1, (int)blobmp.length())));
			lbprecomp.setText(produtomp.getPreco() + " €");
			lbusermp.setText(produtomp.getUtilizador().getNomeUtilizador());
			lbdatamp.setText(eyebuy.mostradata(produtomp.getDataColocacao()));
			lbnomemp.setText(produtomp.getNome());
			
			Produto produtomp_1 = produtosmp.get(1);
			Blob blobmp_1 = imagemDAO.getImagemProduto(produtomp_1.getId());
			
			imagemmp_1.setIcon(new ImageIcon(blobmp_1.getBytes(1, (int)blobmp_1.length())));
			lbprecomp_1.setText(produtomp_1.getPreco() + " €");
			lbusermp_1.setText(produtomp_1.getUtilizador().getNomeUtilizador());
			lbdatamp_1.setText(eyebuy.mostradata(produtomp_1.getDataColocacao()));
			lbnomemp_1.setText(produtomp_1.getNome());
			
			Produto produtomp_2 = produtosmp.get(2);
			Blob blobmp_2 = imagemDAO.getImagemProduto(produtomp_2.getId());
			
			imagemmp_2.setIcon(new ImageIcon(blobmp_2.getBytes(1, (int)blobmp_2.length())));
			lbprecomp_2.setText(produtomp_2.getPreco() + " €");
			lbusermp_2.setText(produtomp_2.getUtilizador().getNomeUtilizador());
			lbdatamp_2.setText(eyebuy.mostradata(produtomp_2.getDataColocacao()));
			lbnomemp_2.setText(produtomp_2.getNome());
			
			Produto produtomp_3 = produtosmp.get(3);
			Blob blobmp_3 = imagemDAO.getImagemProduto(produtomp_3.getId());
			
			imagemmp_3.setIcon(new ImageIcon(blobmp_3.getBytes(1, (int)blobmp_3.length())));
			lbprecomp_3.setText(produtomp_3.getPreco() + " €");
			lbusermp_3.setText(produtomp_3.getUtilizador().getNomeUtilizador());
			lbdatamp_3.setText(eyebuy.mostradata(produtomp_3.getDataColocacao()));
			lbnomemp_3.setText(produtomp_3.getNome());
			
		}
		
		if(produtoDAO.getMontraProdutos(utilizador.getNomeUtilizador(),posicaoactualmp+nrmp,1).size()==0){
			btdirmp.setEnabled(false);
		}
	}
	
		private void updatepr() throws SQLException {
			btdirpr.setEnabled(true);
			btesqpr.setEnabled(true);
			painelpr.setVisible(true);
			painelpr_1.setVisible(true);
			painelpr_2.setVisible(true);
			painelmp_3.setVisible(true);
			
			produtospr = produtoDAO.getProdutosRelacionados(utilizador.getNomeUtilizador(),posicaoactualpr,nrpr);
			
			btdirpr.setEnabled(true);
			btesqpr.setEnabled(true);
			
			int arraysize = produtospr.size();
			
			if(posicaoactualpr == 0){
				btesqpr.setEnabled(false);
			}
			
			if (arraysize == 0){
				btdirpr.setEnabled(false);
				btesqpr.setEnabled(false);
				
				painelpr.setVisible(false);
				painelpr_1.setVisible(false);
				painelpr_2.setVisible(false);
				
			}else if(arraysize == 1){
				
				Produto produtopr = produtospr.get(0);
				Blob blobpr = imagemDAO.getImagemProduto(produtopr.getId());
				
				imagempr.setIcon(new ImageIcon(blobpr.getBytes(1, (int)blobpr.length())));
				lbprecopr.setText(produtopr.getPreco() + " €");
				lbuserpr.setText(produtopr.getUtilizador().getNomeUtilizador());
				lbdatapr.setText(eyebuy.mostradata(produtopr.getDataColocacao()));
				lbnomepr.setText(produtopr.getNome());
				
				btdirpr.setEnabled(false);
				
				painelpr_1.setVisible(false);
				painelpr_2.setVisible(false);
				
			}else if(arraysize == 2){
				
				Produto produtopr = produtospr.get(0);
				Blob blobpr = imagemDAO.getImagemProduto(produtopr.getId());
				
				imagempr.setIcon(new ImageIcon(blobpr.getBytes(1, (int)blobpr.length())));
				lbprecopr.setText(produtopr.getPreco() + " €");
				lbuserpr.setText(produtopr.getUtilizador().getNomeUtilizador());
				lbdatapr.setText(eyebuy.mostradata(produtopr.getDataColocacao()));
				lbnomepr.setText(produtopr.getNome());
				
				Produto produtopr_1 = produtospr.get(1);
				Blob blobpr_1 = imagemDAO.getImagemProduto(produtopr_1.getId());
				
				imagempr_1.setIcon(new ImageIcon(blobpr_1.getBytes(1, (int)blobpr_1.length())));
				lbprecopr_1.setText(produtopr_1.getPreco() + " €");
				lbuserpr_1.setText(produtopr_1.getUtilizador().getNomeUtilizador());
				lbdatapr_1.setText(eyebuy.mostradata(produtopr_1.getDataColocacao()));
				lbnomepr_1.setText(produtopr_1.getNome());
				
				btdirpr.setEnabled(false);

				painelpr_2.setVisible(false);
			
			}else if(arraysize == 3){
			
				Produto produtopr = produtospr.get(0);
				Blob blobpr = imagemDAO.getImagemProduto(produtopr.getId());
				
				imagempr.setIcon(new ImageIcon(blobpr.getBytes(1, (int)blobpr.length())));
				lbprecopr.setText(produtopr.getPreco() + " €");
				lbuserpr.setText(produtopr.getUtilizador().getNomeUtilizador());
				lbdatapr.setText(eyebuy.mostradata(produtopr.getDataColocacao()));
				lbnomepr.setText(produtopr.getNome());
				
				Produto produtopr_1 = produtospr.get(1);
				Blob blobpr_1 = imagemDAO.getImagemProduto(produtopr_1.getId());
				
				imagempr_1.setIcon(new ImageIcon(blobpr_1.getBytes(1, (int)blobpr_1.length())));
				lbprecopr_1.setText(produtopr_1.getPreco() + " €");
				lbuserpr_1.setText(produtopr_1.getUtilizador().getNomeUtilizador());
				lbdatapr_1.setText(eyebuy.mostradata(produtopr_1.getDataColocacao()));
				lbnomepr_1.setText(produtopr_1.getNome());
				
				Produto produtopr_2 = produtospr.get(2);
				Blob blobpr_2 = imagemDAO.getImagemProduto(produtopr_2.getId());
				
				imagempr_2.setIcon(new ImageIcon(blobpr_2.getBytes(1, (int)blobpr_2.length())));
				lbprecopr_2.setText(produtopr_2.getPreco() + " €");
				lbuserpr_2.setText(produtopr_2.getUtilizador().getNomeUtilizador());
				lbdatapr_2.setText(eyebuy.mostradata(produtopr_2.getDataColocacao()));
				lbnomepr_2.setText(produtopr_2.getNome());
			
			}
			
			if(produtoDAO.getProdutosRelacionados(utilizador.getNomeUtilizador(),posicaoactualpr+nrpr,1).size()==0){
				btdirpr.setEnabled(false);
			}
			
			updatemp();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public void update() throws SQLException {
		updateuv();
		
	}
	
	public void updateuv() throws SQLException{
		btbaixuv.setEnabled(true);
		indexuv = 0;
		
		jlrelacionados.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = historicoDAO.listaUltimasVendas(utilizador.getNomeUtilizador());
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		jlrelacionados.setSelectedIndex(indexuv);
		jlrelacionados.ensureIndexIsVisible(indexuv);
		
		if(jlrelacionados.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    jlrelacionados.setCellRenderer(renderer);
		}
		
		btcimuv.setEnabled(false);
		btbaixuv.setEnabled(true);
		if (jlrelacionados.getSelectedValue() == null || historicoDAO.listaUltimasVendas(utilizador.getNomeUtilizador()).length < 2){
			btbaixuv.setEnabled(false);
		}
	}
}
