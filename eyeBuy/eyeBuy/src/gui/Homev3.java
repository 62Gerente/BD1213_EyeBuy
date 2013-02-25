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
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;

import dao.CategoriaDAO;
import dao.ImagemDAO;
import dao.LocalidadeDAO;
import dao.ProdutoDAO;

import model.Produto;
import model.Utilizador;
import model.EyeBuy;

import java.awt.SystemColor;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class Homev3 extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	
	private LocalidadeDAO localidadeDAO = new LocalidadeDAO();
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private Utilizador utilizador = new Utilizador();
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private ImagemDAO imagemDAO = new ImagemDAO();
	private EyeBuy eyebuy = new EyeBuy();
	private JPanel tabps;
	private JTabbedPane tabbedPane;
	
	// Produtos que podem interessar:
	
	private JButton btesqps = new JButton();
	private JButton btdirpi = new JButton();
	private JButton btesqpi = new JButton();
	private JButton btactpi = new JButton();
	private JButton button_4 = new JButton();
	private JPanel painelpi_4 = new JPanel();
	private JButton imagempi_4 = new JButton();
	private JLabel lbprecopi_4;
	private JLabel lbuserpi_4;
	private JLabel lbdatapi_4;
	private JPanel painelpi_3 = new JPanel();
	private JButton imagempi_3 = new JButton();
	private JLabel lbprecopi_3;
	private JLabel lbuserpi_3;
	private JLabel lbdatapi_3;
	private JPanel painelpi_2 = new JPanel();
	private JButton imagempi_2 = new JButton();
	private JLabel lbprecopi_2;
	private JLabel lbuserpi_2;
	private JLabel lbdatapi_2;
	private JPanel painelpi_1 = new JPanel();
	private JButton imagempi_1 = new JButton();
	private JLabel lbprecopi_1;
	private JLabel lbuserpi_1;
	private JLabel lbdatapi_1;
	private JPanel painelpi = new JPanel();
	private JLabel lbprecopi;
	private JButton imagempi = new JButton();
	private JLabel lbuserpi;
	private JLabel lbdatapi;
	
	
	private int posicaoactualpi = 0;
	private int nrpi = 5;
	private ArrayList<Produto> produtospi = new ArrayList<Produto>();
	
	// Produtos Seguidos
	
	private JPanel painelps = new JPanel();
	private JLabel lbprecops = new JLabel();
	private JButton imagemps = new JButton();
	private JLabel lbuserps = new JLabel();
	private JLabel lbdataps = new JLabel();
	private JPanel painelps_1 = new JPanel();
	private JButton imagemps_1 = new JButton();
	private JLabel lbprecops_1 = new JLabel();
	private JLabel lbuserps_1 = new JLabel();
	private JLabel lbdataps_1 = new JLabel();
	private JPanel painelps_2 = new JPanel();
	private JButton imagemps_2 = new JButton();
	private JLabel lbprecops_2 = new JLabel();
	private JLabel lbuserps_2 = new JLabel();
	private JLabel lbdataps_2 = new JLabel();
	private JPanel painelps_3 = new JPanel();
	private JButton imagemps_3 = new JButton();
	private JLabel lbprecops_3 = new JLabel();
	private JLabel lbuserps_3 = new JLabel();
	private JLabel lbdataps_3 = new JLabel();
	private JPanel painelps_4 = new JPanel();
	private JButton imagemps_4 = new JButton();
	private JLabel lbprecops_4 = new JLabel();
	private JLabel lbuserps_4 = new JLabel();
	private JLabel lbdataps_4 = new JLabel();
	private JButton btlixops = new JButton();
	private JButton btdirps = new JButton();
	private JButton btactps = new JButton();
	
	private int posicaoactualps = 0;
	private int nrps = 5;
	private ArrayList<Produto> produtosps = new ArrayList<Produto>();
	private int posicaoactualpd = 0;
	private int nrpd = 5;
	private ArrayList<Produto> produtospd = new ArrayList<Produto>();
	
	private JPanel panel_8 = new JPanel();
	private JPanel painelnp = new JPanel();
	private JLabel lbpreconp = new JLabel();
	private JButton imagemnp = new JButton();
	private JLabel lbusernp = new JLabel();
	private JLabel lbdatanp = new JLabel();
	private JPanel painelnp_1 = new JPanel();
	private JButton imagemnp_1 = new JButton();
	private JLabel lbpreconp_1 = new JLabel();
	private JLabel lbusernp_1 = new JLabel();
	private JLabel lbdatanp_1 = new JLabel();
	private JPanel painelnp_2 = new JPanel();
	private JButton imagemnp_2 = new JButton();
	private JLabel lbpreconp_2 = new JLabel();
	private JLabel lbusernp_2 = new JLabel();
	private JLabel lbdatanp_2 = new JLabel();
	private JPanel painelnp_3 = new JPanel();
	private JButton imagemnp_3 = new JButton();
	private JLabel lbpreconp_3 = new JLabel();
	private JLabel lbusernp_3 = new JLabel();
	private JLabel lbdatanp_3 = new JLabel();
	private JPanel painelnp_4 = new JPanel();
	private JButton imagemnp_4 = new JButton();
	private JLabel lbpreconp_4 = new JLabel();
	private JLabel lbusernp_4 = new JLabel();
	private JLabel lbdatanp_4 = new JLabel();
	private JButton btactnp = new JButton();
	private JButton button_16 = new JButton();
	private JButton btdirnp = new JButton();
	private JButton btesqnp = new JButton();
	private JButton btremps;
	private JButton btremps_1;
	private JButton btremps_2;
	private JButton btremps_3;
	private JButton btremps_4;
	private JPanel panel_1 = new JPanel();
	private JPanel painelpd = new JPanel();
	private JButton imagempd = new JButton();
	private JLabel lbuserpd;
	private JLabel lbdatapd;
	private JLabel lbprecopd;
	private JButton lbrempd;
	private JPanel painelpd_1 = new JPanel();
	private JLabel lbprecopd_1;
	private JButton lbrempd_1;
	private JButton imagempd_1 = new JButton();
	private JLabel lbuserpd_1;
	private JLabel lbdatapd_1;
	private JPanel painelpd_2 = new JPanel();
	private JButton imagempd_2 = new JButton();
	private JLabel lbuserpd_2;
	private JLabel lbdatapd_2;
	private JLabel lbprecopd_2;
	private JButton lbrempd_2;
	private JPanel painelpd_3 = new JPanel();
	private JLabel lbuserpd_3;
	private JLabel lbdatapd_3;
	private JLabel lbprecopd_3;
	private JButton lbrempd_3;
	private JButton imagempd_3 = new JButton();
	private JPanel painelpd_4 = new JPanel();
	private JButton imagempd_4 = new JButton();
	private JLabel lbuserpd_4;
	private JLabel lbdatapd_4;
	private JLabel lbprecopd_4;
	private JButton lbrempd_4;
	private JButton btactpd = new JButton();
	private JButton button_7 = new JButton();
	private JButton btesqpd = new JButton();
	private JButton btdirpd = new JButton();
	private JPanel panel_2 = new JPanel();
	private JPanel painelmv = new JPanel();
	private JLabel lbprecomv;
	private JButton imagemmv = new JButton();
	private JLabel lbusermv;
	private JLabel lbdatamv;
	private JPanel painelmv_1 = new JPanel();
	private JButton imagemmv_1 = new JButton();
	private JLabel lbprecomv_1;
	
	private JLabel lbusermv_1;
	private JLabel lbdatamv_1;
	private JPanel painelmv_2 = new JPanel();
	private JButton imagemmv_2 = new JButton();
	private JLabel lbprecomv_2;
	private JLabel lbusermv_2;
	private JLabel lbdatamv_2;
	private JPanel painelmv_3 = new JPanel();
	private JButton imagemmv_3 = new JButton();
	private JLabel lbprecomv_3;
	private JLabel lbusermv_3;
	private JLabel lbdatamv_3;
	private JPanel painelmv_4 = new JPanel();
	private JButton imagemmv_4 = new JButton();
	private JLabel lbprecomv_4;
	private JLabel lbusermv_4;
	private JLabel lbdatamv_4;
	private JButton btactmv = new JButton();
	private JButton button_8 = new JButton();
	private JButton btdirmv = new JButton();
	private JButton btesqmv = new JButton();


	private int posicaoactualmv = 0;
	private int nrmv = 5;
	private ArrayList<Produto> produtosmv = new ArrayList<Produto>();
	
	private int posicaoactualnp = 0;
	private int nrnp = 5;
	private ArrayList<Produto> produtosnp = new ArrayList<Produto>();
	
	private JComboBox cblocalidade = new JComboBox();
	private JComboBox cbcategoria = new JComboBox();
	
	private JLabel lbnomepi = new JLabel();
	private JLabel lbnomepi_1 = new JLabel();
	private JLabel lbnomepi_4 = new JLabel();
	private JLabel lbnomepi_3 = new JLabel();
	private JLabel lbnomepi_2 = new JLabel();
	private JLabel lbnomenp = new JLabel();
	private JLabel lbnomenp_1 = new JLabel();
	private JLabel lbnomenp_2 = new JLabel();
	private JLabel lbnomenp_3 = new JLabel();
	private JLabel lbnomenp_4 = new JLabel();
	private JLabel lbnomemv;
	private JLabel lbnomemv_1;
	private JLabel lbnomemv_2;
	private JLabel lbnomemv_3;
	private JLabel lbnomemv_4;
	private JLabel lbnomeps;
	private JLabel lbnomeps_1;
	private JLabel lbnomeps_2;
	private JLabel lbnomeps_3;
	private JLabel lbnomeps_4;
	private JLabel lbnomepd;
	private JLabel lbnomepd_1;
	private JLabel lbnomepd_2;
	private JLabel lbnomepd_3;
	private JLabel lbnomepd_4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homev3 frame = new Homev3(null);
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
	public Homev3(Utilizador user) throws SQLException {
		produtoDAO.addObserver(this);
		imagemDAO.addObserver(this);
		eyebuy.addObserver(this);
		categoriaDAO.addObserver(this);
		localidadeDAO.addObserver(this);
		
		setBackground(SystemColor.text);
		
		if(user != null){
			this.utilizador = user.clone();
			utilizador.addObserver(this);
		}
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 745);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Comunidade ▼ \n");
		mnNewMenu.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/comm2.png")));
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
		mnCarrinho.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/carr.png")));
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
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
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
		
		mnUtilizador.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/user2.png")));
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
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		mnUtilizador.add(mntmPerfilDeUtilizador);
		
		JMenuItem mntmPerfilDeVendedor = new JMenuItem("Página de Vendas");
		mntmPerfilDeVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PaginaVendas(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
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
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		mnUtilizador.add(mntmHistrico);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende sair?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Homev3.this.setVisible(false);
					Homev3.this.dispose();
				}
			}
		});
		mnUtilizador.add(mntmSair);
		
		JButton btnPginaInicial = new JButton("Página Inicial");
		btnPginaInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		});
		btnPginaInicial.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/home2.png")));
		btnPginaInicial.setContentAreaFilled(false);
		btnPginaInicial.setBorderPainted(false);
		menuBar.add(btnPginaInicial);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setOpaque(false);
		contentPane.setFocusCycleRoot(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(tabbedPane.getSelectedIndex() == 0){
					try {
						updatenp();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(tabbedPane.getSelectedIndex() == 1){
					try {
						updatemv();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(tabbedPane.getSelectedIndex() == 2){
					try {
						updateps();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					try {
						updatepd();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Produtos que lhe podem interessar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblNewLabel = new JLabel("Direitos reservados DSS 2012  @EyeBuy");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 650, Short.MAX_VALUE)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
							.addGap(34))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_4, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 995, Short.MAX_VALUE))
							.addGap(1))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel))
		);
		
		btdirpi = new JButton("");
		btdirpi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualpi += nrpi;
				try {
					updatepi();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btdirpi.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/setafrente.png")));
		btdirpi.setEnabled(true);
		btdirpi.setContentAreaFilled(false);
		btdirpi.setBorderPainted(false);
		
		btesqpi = new JButton("");
		btesqpi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualpi -= nrpi;
				try {
					updatepi();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btesqpi.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/setaptras.png")));
		btesqpi.setEnabled(true);
		btesqpi.setContentAreaFilled(false);
		btesqpi.setBorderPainted(false);
		
		btactpi = new JButton("");
		btactpi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualpi = 0;
				try {
					updatepi();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btactpi.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/actualizar.png")));
		btactpi.setContentAreaFilled(false);
		btactpi.setBorderPainted(false);
		
		button_4 = new JButton("");
		button_4.setEnabled(true);
		button_4.setContentAreaFilled(false);
		button_4.setBorderPainted(false);
		
		painelpi_4 = new JPanel();
		
		imagempi_4 = new JButton("");
		imagempi_4.setContentAreaFilled(false);
		imagempi_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtospi.get(4);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagempi_4.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/seguro-de-carro-00.jpg")));
		
		lbprecopi_4 = new JLabel("10,00 €");
		lbprecopi_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecopi_4.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuserpi_4 = new JLabel("Gerente62");
		lbuserpi_4.setVerticalAlignment(SwingConstants.TOP);
		lbuserpi_4.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserpi_4.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatapi_4 = new JLabel("12/10/2012");
		lbdatapi_4.setVerticalAlignment(SwingConstants.TOP);
		lbdatapi_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatapi_4.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomepi_4 = new JLabel("Nome Produto");
		lbnomepi_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomepi_4.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelpi_4 = new GroupLayout(painelpi_4);
		gl_painelpi_4.setHorizontalGroup(
			gl_painelpi_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpi_4.createSequentialGroup()
					.addGroup(gl_painelpi_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbprecopi_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(lbnomepi_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(imagempi_4, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
						.addGroup(gl_painelpi_4.createSequentialGroup()
							.addComponent(lbuserpi_4, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatapi_4)))
					.addGap(0))
		);
		gl_painelpi_4.setVerticalGroup(
			gl_painelpi_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelpi_4.createSequentialGroup()
					.addGroup(gl_painelpi_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatapi_4)
						.addComponent(lbuserpi_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagempi_4, GroupLayout.PREFERRED_SIZE, 166, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(lbnomepi_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbprecopi_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelpi_4.setLayout(gl_painelpi_4);
		
		painelpi_3 = new JPanel();
		
		imagempi_3 = new JButton("");
		imagempi_3.setContentAreaFilled(false);
		imagempi_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtospi.get(3);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagempi_3.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/seguro-de-carro-00.jpg")));
		
		lbprecopi_3 = new JLabel("10,00 €");
		lbprecopi_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecopi_3.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuserpi_3 = new JLabel("Gerente62");
		lbuserpi_3.setVerticalAlignment(SwingConstants.TOP);
		lbuserpi_3.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserpi_3.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatapi_3 = new JLabel("12/10/2012");
		lbdatapi_3.setVerticalAlignment(SwingConstants.TOP);
		lbdatapi_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatapi_3.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomepi_3 = new JLabel("Nome Produto");
		lbnomepi_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomepi_3.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelpi_3 = new GroupLayout(painelpi_3);
		gl_painelpi_3.setHorizontalGroup(
			gl_painelpi_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpi_3.createSequentialGroup()
					.addGroup(gl_painelpi_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbprecopi_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(lbnomepi_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(imagempi_3, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
						.addGroup(gl_painelpi_3.createSequentialGroup()
							.addComponent(lbuserpi_3, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatapi_3)))
					.addGap(0))
		);
		gl_painelpi_3.setVerticalGroup(
			gl_painelpi_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelpi_3.createSequentialGroup()
					.addGroup(gl_painelpi_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatapi_3)
						.addComponent(lbuserpi_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagempi_3, GroupLayout.PREFERRED_SIZE, 167, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomepi_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbprecopi_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelpi_3.setLayout(gl_painelpi_3);
		
		painelpi_2 = new JPanel();
		
		imagempi_2 = new JButton("");
		imagempi_2.setContentAreaFilled(false);
		imagempi_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtospi.get(2);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagempi_2.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/seguro-de-carro-00.jpg")));
		
		lbprecopi_2 = new JLabel("10,00 €");
		lbprecopi_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecopi_2.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuserpi_2 = new JLabel("Gerente62");
		lbuserpi_2.setVerticalAlignment(SwingConstants.TOP);
		lbuserpi_2.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserpi_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatapi_2 = new JLabel("12/10/2012");
		lbdatapi_2.setVerticalAlignment(SwingConstants.TOP);
		lbdatapi_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatapi_2.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomepi_2 = new JLabel("Nome Produto");
		lbnomepi_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomepi_2.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelpi_2 = new GroupLayout(painelpi_2);
		gl_painelpi_2.setHorizontalGroup(
			gl_painelpi_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpi_2.createSequentialGroup()
					.addGroup(gl_painelpi_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbprecopi_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
						.addComponent(lbnomepi_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
						.addComponent(imagempi_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 140, Short.MAX_VALUE)
						.addGroup(gl_painelpi_2.createSequentialGroup()
							.addComponent(lbuserpi_2, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatapi_2)))
					.addGap(0))
		);
		gl_painelpi_2.setVerticalGroup(
			gl_painelpi_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelpi_2.createSequentialGroup()
					.addGroup(gl_painelpi_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatapi_2)
						.addComponent(lbuserpi_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagempi_2, GroupLayout.PREFERRED_SIZE, 165, Short.MAX_VALUE)
					.addGap(9)
					.addComponent(lbnomepi_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbprecopi_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelpi_2.setLayout(gl_painelpi_2);
		
		painelpi_1 = new JPanel();
		
		imagempi_1 = new JButton("");
		imagempi_1.setContentAreaFilled(false);
		imagempi_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produto produto = produtospi.get(1);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagempi_1.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/seguro-de-carro-00.jpg")));
		
		lbprecopi_1 = new JLabel("10,00 €");
		lbprecopi_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecopi_1.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuserpi_1 = new JLabel("Gerente62");
		lbuserpi_1.setVerticalAlignment(SwingConstants.TOP);
		lbuserpi_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserpi_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatapi_1 = new JLabel("12/10/2012");
		lbdatapi_1.setVerticalAlignment(SwingConstants.TOP);
		lbdatapi_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatapi_1.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomepi_1 = new JLabel("Nome Produto");
		lbnomepi_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomepi_1.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelpi_1 = new GroupLayout(painelpi_1);
		gl_painelpi_1.setHorizontalGroup(
			gl_painelpi_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpi_1.createSequentialGroup()
					.addGroup(gl_painelpi_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbprecopi_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(lbnomepi_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(imagempi_1, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
						.addGroup(gl_painelpi_1.createSequentialGroup()
							.addComponent(lbuserpi_1, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatapi_1)))
					.addGap(0))
		);
		gl_painelpi_1.setVerticalGroup(
			gl_painelpi_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelpi_1.createSequentialGroup()
					.addGroup(gl_painelpi_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatapi_1)
						.addComponent(lbuserpi_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagempi_1, GroupLayout.PREFERRED_SIZE, 164, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(lbnomepi_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbprecopi_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelpi_1.setLayout(gl_painelpi_1);
		
		painelpi = new JPanel();
		
		lbprecopi = new JLabel("10,00 €");
		lbprecopi.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecopi.setFont(new Font("Dialog", Font.BOLD, 10));
		
		imagempi = new JButton("");
		imagempi.setContentAreaFilled(false);
		imagempi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produto produto = produtospi.get(0);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagempi.setIcon(new ImageIcon("/home/gerente/fotos/1v2.jpg"));
		
		lbuserpi = new JLabel("Gerente62");
		lbuserpi.setVerticalAlignment(SwingConstants.TOP);
		lbuserpi.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserpi.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatapi = new JLabel("12/10/2012");
		lbdatapi.setVerticalAlignment(SwingConstants.TOP);
		lbdatapi.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatapi.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomepi = new JLabel("Nome Produto");
		lbnomepi.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomepi.setFont(new Font("Dialog", Font.BOLD, 11));
		GroupLayout gl_painelpi = new GroupLayout(painelpi);
		gl_painelpi.setHorizontalGroup(
			gl_painelpi.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_painelpi.createSequentialGroup()
					.addComponent(lbprecopi, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_painelpi.createSequentialGroup()
					.addGroup(gl_painelpi.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnomepi, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
						.addComponent(imagempi, GroupLayout.PREFERRED_SIZE, 304, Short.MAX_VALUE)
						.addGroup(gl_painelpi.createSequentialGroup()
							.addComponent(lbuserpi, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatapi)))
					.addGap(0))
		);
		gl_painelpi.setVerticalGroup(
			gl_painelpi.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpi.createSequentialGroup()
					.addGroup(gl_painelpi.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatapi)
						.addComponent(lbuserpi))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagempi, GroupLayout.PREFERRED_SIZE, 163, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(lbnomepi, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbprecopi, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelpi.setLayout(gl_painelpi);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(painelpi, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelpi_1, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelpi_2, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelpi_3, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelpi_4, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(button_4, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
							.addComponent(btactpi, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(3))
						.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
							.addComponent(btdirpi, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(3))
						.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
							.addComponent(btesqpi, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(3)))
					.addGap(0))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(22)
					.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(btesqpi)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btdirpi, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btactpi))
				.addComponent(painelpi, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
				.addComponent(painelpi_1, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
				.addComponent(painelpi_2, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
				.addComponent(painelpi_3, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
				.addComponent(painelpi_4, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
		);
		panel_4.setLayout(gl_panel_4);
		
		panel_8 = new JPanel();
		tabbedPane.addTab("Novos Produtos", null, panel_8, null);
		
		painelnp = new JPanel();
		lbpreconp = new JLabel("10,00 €");
		lbpreconp.setHorizontalAlignment(SwingConstants.CENTER);
		lbpreconp.setFont(new Font("Dialog", Font.BOLD, 10));
		
		imagemnp = new JButton("");
		imagemnp.setContentAreaFilled(false);
		imagemnp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosnp.get(0);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemnp.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/casa-praia-recreio-b3.jpg")));
		
		lbusernp = new JLabel("Gerente62");
		lbusernp.setVerticalAlignment(SwingConstants.TOP);
		lbusernp.setHorizontalAlignment(SwingConstants.LEFT);
		lbusernp.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatanp = new JLabel("12/10/2012");
		lbdatanp.setVerticalAlignment(SwingConstants.TOP);
		lbdatanp.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatanp.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomenp = new JLabel("Nome Produto");
		lbnomenp.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomenp.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelnp = new GroupLayout(painelnp);
		gl_painelnp.setHorizontalGroup(
			gl_painelnp.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelnp.createSequentialGroup()
					.addGroup(gl_painelnp.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbpreconp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addComponent(lbnomenp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addComponent(imagemnp, GroupLayout.PREFERRED_SIZE, 304, Short.MAX_VALUE)
						.addGroup(gl_painelnp.createSequentialGroup()
							.addComponent(lbusernp, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatanp)))
					.addGap(0))
		);
		gl_painelnp.setVerticalGroup(
			gl_painelnp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelnp.createSequentialGroup()
					.addGroup(gl_painelnp.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatanp)
						.addComponent(lbusernp))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemnp, GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(lbnomenp, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbpreconp, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelnp.setLayout(gl_painelnp);
		
		painelnp_1 = new JPanel();
		
		imagemnp_1 = new JButton("");
		imagemnp_1.setContentAreaFilled(false);
		imagemnp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosnp.get(1);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemnp_1.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/casa-praia-recreio-b3.jpg")));
		
		lbpreconp_1 = new JLabel("10,00 €");
		lbpreconp_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbpreconp_1.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbusernp_1 = new JLabel("Gerente62");
		lbusernp_1.setVerticalAlignment(SwingConstants.TOP);
		lbusernp_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbusernp_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatanp_1 = new JLabel("12/10/2012");
		lbdatanp_1.setVerticalAlignment(SwingConstants.TOP);
		lbdatanp_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatanp_1.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomenp_1 = new JLabel("Nome Produto");
		lbnomenp_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomenp_1.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelnp_1 = new GroupLayout(painelnp_1);
		gl_painelnp_1.setHorizontalGroup(
			gl_painelnp_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelnp_1.createSequentialGroup()
					.addGroup(gl_painelnp_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbpreconp_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(lbnomenp_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(imagemnp_1, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
						.addGroup(gl_painelnp_1.createSequentialGroup()
							.addComponent(lbusernp_1, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatanp_1)))
					.addGap(0))
		);
		gl_painelnp_1.setVerticalGroup(
			gl_painelnp_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelnp_1.createSequentialGroup()
					.addGroup(gl_painelnp_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatanp_1)
						.addComponent(lbusernp_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemnp_1, GroupLayout.PREFERRED_SIZE, 136, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomenp_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbpreconp_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelnp_1.setLayout(gl_painelnp_1);
		
		painelnp_2 = new JPanel();
		
		imagemnp_2 = new JButton("");
		imagemnp_2.setContentAreaFilled(false);
		imagemnp_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosnp.get(2);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemnp_2.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/casa-praia-recreio-b3.jpg")));
		
		lbpreconp_2 = new JLabel("10,00 €");
		lbpreconp_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbpreconp_2.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbusernp_2 = new JLabel("Gerente62");
		lbusernp_2.setVerticalAlignment(SwingConstants.TOP);
		lbusernp_2.setHorizontalAlignment(SwingConstants.LEFT);
		lbusernp_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatanp_2 = new JLabel("12/10/2012");
		lbdatanp_2.setVerticalAlignment(SwingConstants.TOP);
		lbdatanp_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatanp_2.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomenp_2 = new JLabel("Nome Produto");
		lbnomenp_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomenp_2.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelnp_2 = new GroupLayout(painelnp_2);
		gl_painelnp_2.setHorizontalGroup(
			gl_painelnp_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelnp_2.createSequentialGroup()
					.addGroup(gl_painelnp_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbpreconp_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
						.addComponent(lbnomenp_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
						.addComponent(imagemnp_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 140, Short.MAX_VALUE)
						.addGroup(gl_painelnp_2.createSequentialGroup()
							.addComponent(lbusernp_2, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatanp_2)))
					.addGap(0))
		);
		gl_painelnp_2.setVerticalGroup(
			gl_painelnp_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelnp_2.createSequentialGroup()
					.addGroup(gl_painelnp_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatanp_2)
						.addComponent(lbusernp_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemnp_2, GroupLayout.PREFERRED_SIZE, 137, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomenp_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbpreconp_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelnp_2.setLayout(gl_painelnp_2);
		
		painelnp_3 = new JPanel();
		
		imagemnp_3 = new JButton("");
		imagemnp_3.setContentAreaFilled(false);
		imagemnp_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosnp.get(3);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemnp_3.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/casa-praia-recreio-b3.jpg")));
		
		lbpreconp_3 = new JLabel("10,00 €");
		lbpreconp_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbpreconp_3.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbusernp_3 = new JLabel("Gerente62");
		lbusernp_3.setVerticalAlignment(SwingConstants.TOP);
		lbusernp_3.setHorizontalAlignment(SwingConstants.LEFT);
		lbusernp_3.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatanp_3 = new JLabel("12/10/2012");
		lbdatanp_3.setVerticalAlignment(SwingConstants.TOP);
		lbdatanp_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatanp_3.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomenp_3 = new JLabel("Nome Produto");
		lbnomenp_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomenp_3.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelnp_3 = new GroupLayout(painelnp_3);
		gl_painelnp_3.setHorizontalGroup(
			gl_painelnp_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelnp_3.createSequentialGroup()
					.addGroup(gl_painelnp_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbpreconp_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(lbnomenp_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(imagemnp_3, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
						.addGroup(gl_painelnp_3.createSequentialGroup()
							.addComponent(lbusernp_3, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatanp_3)))
					.addGap(0))
		);
		gl_painelnp_3.setVerticalGroup(
			gl_painelnp_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelnp_3.createSequentialGroup()
					.addGroup(gl_painelnp_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatanp_3)
						.addComponent(lbusernp_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemnp_3, GroupLayout.PREFERRED_SIZE, 136, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomenp_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbpreconp_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelnp_3.setLayout(gl_painelnp_3);
		
		painelnp_4 = new JPanel();
		
		imagemnp_4 = new JButton("");
		imagemnp_4.setContentAreaFilled(false);
		imagemnp_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosnp.get(4);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemnp_4.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/casa-praia-recreio-b3.jpg")));
		
		lbpreconp_4 = new JLabel("10,00 €");
		lbpreconp_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbpreconp_4.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbusernp_4 = new JLabel("Gerente62");
		lbusernp_4.setVerticalAlignment(SwingConstants.TOP);
		lbusernp_4.setHorizontalAlignment(SwingConstants.LEFT);
		lbusernp_4.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatanp_4 = new JLabel("12/10/2012");
		lbdatanp_4.setVerticalAlignment(SwingConstants.TOP);
		lbdatanp_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatanp_4.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomenp_4 = new JLabel("Nome Produto");
		lbnomenp_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomenp_4.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelnp_4 = new GroupLayout(painelnp_4);
		gl_painelnp_4.setHorizontalGroup(
			gl_painelnp_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelnp_4.createSequentialGroup()
					.addGroup(gl_painelnp_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbpreconp_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(lbnomenp_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(imagemnp_4, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
						.addGroup(gl_painelnp_4.createSequentialGroup()
							.addComponent(lbusernp_4, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatanp_4)))
					.addGap(0))
		);
		gl_painelnp_4.setVerticalGroup(
			gl_painelnp_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelnp_4.createSequentialGroup()
					.addGroup(gl_painelnp_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatanp_4)
						.addComponent(lbusernp_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemnp_4, GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(lbnomenp_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbpreconp_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelnp_4.setLayout(gl_painelnp_4);
		
		btactnp = new JButton("");
		btactnp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualnp = 0;
				try {
					updatenp();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btactnp.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/actualizar.png")));
		btactnp.setContentAreaFilled(false);
		btactnp.setBorderPainted(false);
		
		button_16 = new JButton("");
		button_16.setEnabled(true);
		button_16.setContentAreaFilled(false);
		button_16.setBorderPainted(false);
		
		btdirnp = new JButton("");
		btdirnp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualnp += nrnp;
				try {
					updatenp();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btdirnp.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/setafrente.png")));
		btdirnp.setEnabled(true);
		btdirnp.setContentAreaFilled(false);
		btdirnp.setBorderPainted(false);
		
		btesqnp = new JButton("");
		btesqnp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualnp -= nrnp;
				try {
					updatenp();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btesqnp.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/setaptras.png")));
		btesqnp.setEnabled(true);
		btesqnp.setContentAreaFilled(false);
		btesqnp.setBorderPainted(false);
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addComponent(painelnp, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelnp_1, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelnp_2, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelnp_3, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelnp_4, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_8.createParallelGroup(Alignment.TRAILING)
							.addComponent(btactnp, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(button_16, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
						.addComponent(btdirnp, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btesqnp, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(9))
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_8.createSequentialGroup()
							.addComponent(painelnp, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel_8.createSequentialGroup()
							.addComponent(painelnp_1, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(1))
						.addComponent(painelnp_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_8.createSequentialGroup()
							.addComponent(painelnp_3, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel_8.createSequentialGroup()
							.addComponent(painelnp_4, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel_8.createSequentialGroup()
							.addGap(23)
							.addComponent(button_16, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(btesqnp, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btdirnp, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btactnp, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(12)))
					.addGap(4))
		);
		panel_8.setLayout(gl_panel_8);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Dos Melhores Vendedores", null, panel_2, null);
		
		painelmv = new JPanel();
		
		lbprecomv = new JLabel("10,00 €");
		lbprecomv.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecomv.setFont(new Font("Dialog", Font.BOLD, 10));
		
		imagemmv = new JButton("");
		imagemmv.setContentAreaFilled(false);
		imagemmv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosmv.get(0);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemmv.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/espl.jpg")));
		
		lbusermv = new JLabel("Gerente62");
		lbusermv.setVerticalAlignment(SwingConstants.TOP);
		lbusermv.setHorizontalAlignment(SwingConstants.LEFT);
		lbusermv.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatamv = new JLabel("12/10/2012");
		lbdatamv.setVerticalAlignment(SwingConstants.TOP);
		lbdatamv.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatamv.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomemv = new JLabel("Nome Produto");
		lbnomemv.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomemv.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelmv = new GroupLayout(painelmv);
		gl_painelmv.setHorizontalGroup(
			gl_painelmv.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmv.createSequentialGroup()
					.addGroup(gl_painelmv.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnomemv, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
						.addComponent(lbprecomv, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
						.addComponent(imagemmv, GroupLayout.PREFERRED_SIZE, 316, Short.MAX_VALUE)
						.addGroup(gl_painelmv.createSequentialGroup()
							.addComponent(lbusermv, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatamv)))
					.addGap(0))
		);
		gl_painelmv.setVerticalGroup(
			gl_painelmv.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelmv.createSequentialGroup()
					.addGroup(gl_painelmv.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatamv)
						.addComponent(lbusermv))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemmv, GroupLayout.PREFERRED_SIZE, 136, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomemv, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbprecomv, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelmv.setLayout(gl_painelmv);
		
		painelmv_1 = new JPanel();
		
		imagemmv_1 = new JButton("");
		imagemmv_1.setContentAreaFilled(false);
		imagemmv_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosmv.get(1);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemmv_1.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/espl.jpg")));
		
		lbprecomv_1 = new JLabel("10,00 €");
		lbprecomv_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecomv_1.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbusermv_1 = new JLabel("Gerente62");
		lbusermv_1.setVerticalAlignment(SwingConstants.TOP);
		lbusermv_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbusermv_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatamv_1 = new JLabel("12/10/2012");
		lbdatamv_1.setVerticalAlignment(SwingConstants.TOP);
		lbdatamv_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatamv_1.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomemv_1 = new JLabel("Nome Produto");
		lbnomemv_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomemv_1.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelmv_1 = new GroupLayout(painelmv_1);
		gl_painelmv_1.setHorizontalGroup(
			gl_painelmv_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmv_1.createSequentialGroup()
					.addGroup(gl_painelmv_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbprecomv_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(lbnomemv_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(imagemmv_1, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
						.addGroup(gl_painelmv_1.createSequentialGroup()
							.addComponent(lbusermv_1, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatamv_1)))
					.addGap(0))
		);
		gl_painelmv_1.setVerticalGroup(
			gl_painelmv_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelmv_1.createSequentialGroup()
					.addGroup(gl_painelmv_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatamv_1)
						.addComponent(lbusermv_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemmv_1, GroupLayout.PREFERRED_SIZE, 136, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomemv_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbprecomv_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelmv_1.setLayout(gl_painelmv_1);
		
		painelmv_2 = new JPanel();
		
		imagemmv_2 = new JButton("");
		imagemmv_2.setContentAreaFilled(false);
		imagemmv_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosmv.get(2);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemmv_2.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/espl.jpg")));
		
		lbprecomv_2 = new JLabel("10,00 €");
		lbprecomv_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecomv_2.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbusermv_2 = new JLabel("Gerente62");
		lbusermv_2.setVerticalAlignment(SwingConstants.TOP);
		lbusermv_2.setHorizontalAlignment(SwingConstants.LEFT);
		lbusermv_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatamv_2 = new JLabel("12/10/2012");
		lbdatamv_2.setVerticalAlignment(SwingConstants.TOP);
		lbdatamv_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatamv_2.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomemv_2 = new JLabel("Nome Produto");
		lbnomemv_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomemv_2.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelmv_2 = new GroupLayout(painelmv_2);
		gl_painelmv_2.setHorizontalGroup(
			gl_painelmv_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmv_2.createSequentialGroup()
					.addGroup(gl_painelmv_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnomemv_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
						.addComponent(imagemmv_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 140, Short.MAX_VALUE)
						.addComponent(lbprecomv_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
						.addGroup(gl_painelmv_2.createSequentialGroup()
							.addComponent(lbusermv_2, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatamv_2)))
					.addGap(0))
		);
		gl_painelmv_2.setVerticalGroup(
			gl_painelmv_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmv_2.createSequentialGroup()
					.addGroup(gl_painelmv_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatamv_2)
						.addComponent(lbusermv_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemmv_2, GroupLayout.PREFERRED_SIZE, 137, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomemv_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbprecomv_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelmv_2.setLayout(gl_painelmv_2);
		
		painelmv_3 = new JPanel();
		
		imagemmv_3 = new JButton("");
		imagemmv_3.setContentAreaFilled(false);
		imagemmv_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosmv.get(3);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemmv_3.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/espl.jpg")));
		
		lbprecomv_3 = new JLabel("10,00 €");
		lbprecomv_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecomv_3.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbusermv_3 = new JLabel("Gerente62");
		lbusermv_3.setVerticalAlignment(SwingConstants.TOP);
		lbusermv_3.setHorizontalAlignment(SwingConstants.LEFT);
		lbusermv_3.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatamv_3 = new JLabel("12/10/2012");
		lbdatamv_3.setVerticalAlignment(SwingConstants.TOP);
		lbdatamv_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatamv_3.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomemv_3 = new JLabel("Nome Produto");
		lbnomemv_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomemv_3.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelmv_3 = new GroupLayout(painelmv_3);
		gl_painelmv_3.setHorizontalGroup(
			gl_painelmv_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmv_3.createSequentialGroup()
					.addGroup(gl_painelmv_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnomemv_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(imagemmv_3, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
						.addComponent(lbprecomv_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addGroup(gl_painelmv_3.createSequentialGroup()
							.addComponent(lbusermv_3, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatamv_3)))
					.addGap(0))
		);
		gl_painelmv_3.setVerticalGroup(
			gl_painelmv_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmv_3.createSequentialGroup()
					.addGroup(gl_painelmv_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatamv_3)
						.addComponent(lbusermv_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemmv_3, GroupLayout.PREFERRED_SIZE, 136, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomemv_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbprecomv_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelmv_3.setLayout(gl_painelmv_3);
		
		painelmv_4 = new JPanel();
		
		imagemmv_4 = new JButton("");
		imagemmv_4.setContentAreaFilled(false);
		imagemmv_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosmv.get(4);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemmv_4.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/espl.jpg")));
		
		lbprecomv_4 = new JLabel("10,00 €");
		lbprecomv_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecomv_4.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbusermv_4 = new JLabel("Gerente62");
		lbusermv_4.setVerticalAlignment(SwingConstants.TOP);
		lbusermv_4.setHorizontalAlignment(SwingConstants.LEFT);
		lbusermv_4.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatamv_4 = new JLabel("12/10/2012");
		lbdatamv_4.setVerticalAlignment(SwingConstants.TOP);
		lbdatamv_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatamv_4.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomemv_4 = new JLabel("Nome Produto");
		lbnomemv_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomemv_4.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelmv_4 = new GroupLayout(painelmv_4);
		gl_painelmv_4.setHorizontalGroup(
			gl_painelmv_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmv_4.createSequentialGroup()
					.addGroup(gl_painelmv_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnomemv_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(imagemmv_4, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
						.addComponent(lbprecomv_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addGroup(gl_painelmv_4.createSequentialGroup()
							.addComponent(lbusermv_4, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatamv_4)))
					.addGap(0))
		);
		gl_painelmv_4.setVerticalGroup(
			gl_painelmv_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelmv_4.createSequentialGroup()
					.addGroup(gl_painelmv_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatamv_4)
						.addComponent(lbusermv_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemmv_4, GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(lbnomemv_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbprecomv_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painelmv_4.setLayout(gl_painelmv_4);
		
		btactmv = new JButton("");
		btactmv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualmv = 0;
				try {
					updatemv();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btactmv.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/actualizar.png")));
		btactmv.setContentAreaFilled(false);
		btactmv.setBorderPainted(false);
		
		button_8 = new JButton("");
		button_8.setEnabled(true);
		button_8.setContentAreaFilled(false);
		button_8.setBorderPainted(false);
		
		btdirmv = new JButton("");
		btdirmv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualmv += nrmv;
				try {
					updatemv();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btdirmv.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/setafrente.png")));
		btdirmv.setEnabled(true);
		btdirmv.setContentAreaFilled(false);
		btdirmv.setBorderPainted(false);
		
		btesqmv = new JButton("");
		btesqmv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualmv -= nrmv;
				try {
					updatemv();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btesqmv.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/setaptras.png")));
		btesqmv.setEnabled(true);
		btesqmv.setContentAreaFilled(false);
		btesqmv.setBorderPainted(false);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(painelmv, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelmv_1, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelmv_2, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelmv_3, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelmv_4, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
							.addComponent(btactmv, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(button_8, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
						.addComponent(btdirmv, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btesqmv, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(9))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(painelmv, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(painelmv_1, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(1))
						.addComponent(painelmv_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(painelmv_3, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(painelmv_4, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(23)
							.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(btesqmv, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btdirmv, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btactmv, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(12)))
					.addGap(4))
		);
		panel_2.setLayout(gl_panel_2);
		
		tabps = new JPanel();
		tabbedPane.addTab("Produtos Seguidos", null, tabps, null);
		
		painelps = new JPanel();
		
		lbprecops = new JLabel("10,00 €");
		lbprecops.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecops.setFont(new Font("Dialog", Font.BOLD, 10));
		
		imagemps = new JButton("");
		imagemps.setContentAreaFilled(false);
		imagemps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosps.get(0);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemps.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/financiamento_de_carros_sem_burocracia_e_sem_entrada_96752223312697652.jpg")));
		
		lbuserps = new JLabel("Gerente62");
		lbuserps.setVerticalAlignment(SwingConstants.TOP);
		lbuserps.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserps.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdataps = new JLabel("12/10/2012");
		lbdataps.setVerticalAlignment(SwingConstants.TOP);
		lbdataps.setHorizontalAlignment(SwingConstants.CENTER);
		lbdataps.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		btremps = new JButton("");
		btremps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover dos produtos seguidos?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtosps.get(0);
					try {
						produtoDAO.removeProdutoDesejado(utilizador.getNomeUtilizador(),produto.getId());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						updateps();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btremps.setContentAreaFilled(false);
		btremps.setBorderPainted(false);
		btremps.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/remover16.png")));
		btremps.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbnomeps = new JLabel("Novos Produtos");
		lbnomeps.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomeps.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelps = new GroupLayout(painelps);
		gl_painelps.setHorizontalGroup(
			gl_painelps.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelps.createSequentialGroup()
					.addGroup(gl_painelps.createParallelGroup(Alignment.LEADING)
						.addComponent(imagemps, GroupLayout.PREFERRED_SIZE, 300, Short.MAX_VALUE)
						.addGroup(gl_painelps.createSequentialGroup()
							.addComponent(lbuserps, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdataps, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_painelps.createSequentialGroup()
							.addComponent(lbprecops, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btremps, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(lbnomeps, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_painelps.setVerticalGroup(
			gl_painelps.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelps.createSequentialGroup()
					.addGroup(gl_painelps.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbuserps)
						.addComponent(lbdataps))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemps, GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(lbnomeps, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelps.createParallelGroup(Alignment.LEADING)
						.addComponent(btremps, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbprecops, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
		);
		painelps.setLayout(gl_painelps);
		
		painelps_1 = new JPanel();
		
		imagemps_1 = new JButton("");
		imagemps_1.setContentAreaFilled(false);
		imagemps_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosps.get(1);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemps_1.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/financiamento_de_carros_sem_burocracia_e_sem_entrada_96752223312697652.jpg")));
		
		lbprecops_1 = new JLabel("10,00 €");
		lbprecops_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecops_1.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuserps_1 = new JLabel("Gerente62");
		lbuserps_1.setVerticalAlignment(SwingConstants.TOP);
		lbuserps_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserps_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdataps_1 = new JLabel("12/10/2012");
		lbdataps_1.setVerticalAlignment(SwingConstants.TOP);
		lbdataps_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbdataps_1.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		btremps_1 = new JButton("");
		btremps_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover dos produtos seguidos?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtosps.get(1);
					try {
						produtoDAO.removeProdutoDesejado(utilizador.getNomeUtilizador(),produto.getId());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						updateps();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btremps_1.setContentAreaFilled(false);
		btremps_1.setBorderPainted(false);
		btremps_1.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/remover16.png")));
		btremps_1.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbnomeps_1 = new JLabel("Novos Produtos");
		lbnomeps_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomeps_1.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelps_1 = new GroupLayout(painelps_1);
		gl_painelps_1.setHorizontalGroup(
			gl_painelps_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelps_1.createSequentialGroup()
					.addGroup(gl_painelps_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnomeps_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addComponent(imagemps_1, GroupLayout.PREFERRED_SIZE, 149, Short.MAX_VALUE)
						.addGroup(gl_painelps_1.createSequentialGroup()
							.addComponent(lbprecops_1, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btremps_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_painelps_1.createSequentialGroup()
							.addComponent(lbuserps_1, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdataps_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
		);
		gl_painelps_1.setVerticalGroup(
			gl_painelps_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelps_1.createSequentialGroup()
					.addGroup(gl_painelps_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdataps_1)
						.addComponent(lbuserps_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemps_1, GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(lbnomeps_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelps_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbprecops_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
						.addComponent(btremps_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
		);
		painelps_1.setLayout(gl_painelps_1);
		
		painelps_2 = new JPanel();
		
		imagemps_2 = new JButton("");
		imagemps_2.setContentAreaFilled(false);
		imagemps_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosps.get(2);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemps_2.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/financiamento_de_carros_sem_burocracia_e_sem_entrada_96752223312697652.jpg")));
		
		lbprecops_2 = new JLabel("10,00 €");
		lbprecops_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecops_2.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuserps_2 = new JLabel("Gerente62");
		lbuserps_2.setVerticalAlignment(SwingConstants.TOP);
		lbuserps_2.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserps_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdataps_2 = new JLabel("12/10/2012");
		lbdataps_2.setVerticalAlignment(SwingConstants.TOP);
		lbdataps_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbdataps_2.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		btremps_2 = new JButton("");
		btremps_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover dos produtos seguidos?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtosps.get(2);
					try {
						produtoDAO.removeProdutoDesejado(utilizador.getNomeUtilizador(),produto.getId());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						updateps();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btremps_2.setContentAreaFilled(false);
		btremps_2.setBorderPainted(false);
		btremps_2.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/remover16.png")));
		btremps_2.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbnomeps_2 = new JLabel("Novos Produtos");
		lbnomeps_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomeps_2.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelps_2 = new GroupLayout(painelps_2);
		gl_painelps_2.setHorizontalGroup(
			gl_painelps_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelps_2.createSequentialGroup()
					.addGroup(gl_painelps_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnomeps_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
						.addComponent(imagemps_2, GroupLayout.PREFERRED_SIZE, 143, Short.MAX_VALUE)
						.addGroup(gl_painelps_2.createSequentialGroup()
							.addComponent(lbuserps_2, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdataps_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_painelps_2.createSequentialGroup()
							.addComponent(lbprecops_2, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btremps_2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
		);
		gl_painelps_2.setVerticalGroup(
			gl_painelps_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelps_2.createSequentialGroup()
					.addGroup(gl_painelps_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdataps_2)
						.addComponent(lbuserps_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemps_2, GroupLayout.PREFERRED_SIZE, 137, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomeps_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelps_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbprecops_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
						.addComponent(btremps_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
		);
		painelps_2.setLayout(gl_painelps_2);
		
		painelps_3 = new JPanel();
		
		imagemps_3 = new JButton("");
		imagemps_3.setContentAreaFilled(false);
		imagemps_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosps.get(3);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemps_3.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/financiamento_de_carros_sem_burocracia_e_sem_entrada_96752223312697652.jpg")));
		
		lbprecops_3 = new JLabel("10,00 €");
		lbprecops_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecops_3.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuserps_3 = new JLabel("Gerente62");
		lbuserps_3.setVerticalAlignment(SwingConstants.TOP);
		lbuserps_3.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserps_3.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdataps_3 = new JLabel("12/10/2012");
		lbdataps_3.setVerticalAlignment(SwingConstants.TOP);
		lbdataps_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbdataps_3.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		btremps_3 = new JButton("");
		btremps_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover dos produtos seguidos?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtosps.get(3);
					try {
						produtoDAO.removeProdutoDesejado(utilizador.getNomeUtilizador(),produto.getId());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						updateps();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btremps_3.setContentAreaFilled(false);
		btremps_3.setBorderPainted(false);
		btremps_3.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/remover16.png")));
		btremps_3.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbnomeps_3 = new JLabel("Novos Produtos");
		lbnomeps_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomeps_3.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelps_3 = new GroupLayout(painelps_3);
		gl_painelps_3.setHorizontalGroup(
			gl_painelps_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelps_3.createSequentialGroup()
					.addGroup(gl_painelps_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnomeps_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
						.addGroup(gl_painelps_3.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_painelps_3.createParallelGroup(Alignment.TRAILING)
								.addComponent(imagemps_3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 147, Short.MAX_VALUE)
								.addGroup(gl_painelps_3.createSequentialGroup()
									.addComponent(lbuserps_3, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lbdataps_3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_painelps_3.createSequentialGroup()
									.addComponent(lbprecops_3, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btremps_3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_painelps_3.setVerticalGroup(
			gl_painelps_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelps_3.createSequentialGroup()
					.addGroup(gl_painelps_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdataps_3)
						.addComponent(lbuserps_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemps_3, GroupLayout.PREFERRED_SIZE, 136, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomeps_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelps_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbprecops_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
						.addComponent(btremps_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
		);
		painelps_3.setLayout(gl_painelps_3);
		
		painelps_4 = new JPanel();
		
		imagemps_4 = new JButton("");
		imagemps_4.setContentAreaFilled(false);
		imagemps_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtosps.get(4);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagemps_4.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/financiamento_de_carros_sem_burocracia_e_sem_entrada_96752223312697652.jpg")));
		
		lbprecops_4 = new JLabel("10,00 €");
		lbprecops_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecops_4.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuserps_4 = new JLabel("Gerente62");
		lbuserps_4.setVerticalAlignment(SwingConstants.TOP);
		lbuserps_4.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserps_4.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdataps_4 = new JLabel("12/10/2012");
		lbdataps_4.setVerticalAlignment(SwingConstants.TOP);
		lbdataps_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbdataps_4.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		btremps_4 = new JButton("");
		btremps_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover dos produtos seguidos?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtosps.get(4);
					try {
						produtoDAO.removeProdutoDesejado(utilizador.getNomeUtilizador(),produto.getId());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						updateps();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btremps_4.setContentAreaFilled(false);
		btremps_4.setBorderPainted(false);
		btremps_4.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/remover16.png")));
		btremps_4.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbnomeps_4 = new JLabel("Novos Produtos");
		lbnomeps_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomeps_4.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelps_4 = new GroupLayout(painelps_4);
		gl_painelps_4.setHorizontalGroup(
			gl_painelps_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelps_4.createSequentialGroup()
					.addGroup(gl_painelps_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(imagemps_4, GroupLayout.PREFERRED_SIZE, 149, Short.MAX_VALUE)
						.addGroup(gl_painelps_4.createSequentialGroup()
							.addComponent(lbuserps_4, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdataps_4, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_painelps_4.createSequentialGroup()
							.addGroup(gl_painelps_4.createParallelGroup(Alignment.TRAILING)
								.addComponent(lbnomeps_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
								.addGroup(gl_painelps_4.createSequentialGroup()
									.addComponent(lbprecops_4, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btremps_4, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(0))
		);
		gl_painelps_4.setVerticalGroup(
			gl_painelps_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelps_4.createSequentialGroup()
					.addGroup(gl_painelps_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdataps_4)
						.addComponent(lbuserps_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagemps_4, GroupLayout.PREFERRED_SIZE, 136, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomeps_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelps_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(btremps_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbprecops_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
		);
		painelps_4.setLayout(gl_painelps_4);
		
		btlixops = new JButton("");
		btlixops.setIcon(null);
		btlixops.setEnabled(true);
		btlixops.setContentAreaFilled(false);
		btlixops.setBorderPainted(false);
		
		btdirps = new JButton("");
		btdirps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualps += nrps;
				try {
					updateps();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btdirps.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/setafrente.png")));
		btdirps.setEnabled(true);
		btdirps.setContentAreaFilled(false);
		btdirps.setBorderPainted(false);
		
		btactps = new JButton("");
		btactps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualps = 0;
				try {
					updateps();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btactps.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/actualizar.png")));
		btactps.setContentAreaFilled(false);
		btactps.setBorderPainted(false);
		
		btesqps = new JButton("");
		btesqps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualps -= nrps;
				try {
					updateps();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btesqps.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/setaptras.png")));
		btesqps.setEnabled(true);
		btesqps.setContentAreaFilled(false);
		btesqps.setBorderPainted(false);
		GroupLayout gl_tabps = new GroupLayout(tabps);
		gl_tabps.setHorizontalGroup(
			gl_tabps.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tabps.createSequentialGroup()
					.addContainerGap()
					.addComponent(painelps, GroupLayout.PREFERRED_SIZE, 304, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(painelps_1, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelps_2, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelps_3, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(painelps_4, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_tabps.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tabps.createParallelGroup(Alignment.TRAILING)
							.addComponent(btactps, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(btlixops, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
						.addComponent(btesqps, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btdirps, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(9))
		);
		gl_tabps.setVerticalGroup(
			gl_tabps.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_tabps.createSequentialGroup()
					.addGroup(gl_tabps.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tabps.createSequentialGroup()
							.addGap(13)
							.addComponent(painelps, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
						.addGroup(gl_tabps.createSequentialGroup()
							.addGap(0)
							.addGroup(gl_tabps.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_tabps.createSequentialGroup()
									.addGap(13)
									.addComponent(painelps_1, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
								.addGroup(gl_tabps.createSequentialGroup()
									.addGap(12)
									.addComponent(painelps_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_tabps.createSequentialGroup()
									.addGap(22)
									.addComponent(btlixops, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(24)
									.addComponent(btdirps, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btesqps, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btactps, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addGap(12))
								.addGroup(gl_tabps.createSequentialGroup()
									.addGap(13)
									.addGroup(gl_tabps.createParallelGroup(Alignment.LEADING)
										.addComponent(painelps_4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
										.addComponent(painelps_3, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))))))
					.addGap(4))
		);
		tabps.setLayout(gl_tabps);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Produtos Desejados", null, panel_1, null);
		
		painelpd = new JPanel();
		
		imagempd = new JButton("");
		imagempd.setContentAreaFilled(false);
		imagempd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtospd.get(0);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagempd.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/esplanad.JPG")));
		
		lbuserpd = new JLabel("Gerente62");
		lbuserpd.setVerticalAlignment(SwingConstants.TOP);
		lbuserpd.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserpd.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatapd = new JLabel("12/10/2012");
		lbdatapd.setVerticalAlignment(SwingConstants.TOP);
		lbdatapd.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatapd.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbprecopd = new JLabel("10,00 €");
		lbprecopd.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecopd.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbrempd = new JButton("");
		lbrempd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover dos produtos desejados?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtospd.get(0);
					try {
						produtoDAO.removeProdutoDesejado(utilizador.getNomeUtilizador(),produto.getId());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						updatepd();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		lbrempd.setContentAreaFilled(false);
		lbrempd.setBorderPainted(false);
		lbrempd.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/remover16.png")));
		lbrempd.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbnomepd = new JLabel("Nome Produto");
		lbnomepd.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomepd.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelpd = new GroupLayout(painelpd);
		gl_painelpd.setHorizontalGroup(
			gl_painelpd.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpd.createSequentialGroup()
					.addGroup(gl_painelpd.createParallelGroup(Alignment.LEADING)
						.addComponent(imagempd, GroupLayout.PREFERRED_SIZE, 311, Short.MAX_VALUE)
						.addGroup(gl_painelpd.createSequentialGroup()
							.addComponent(lbuserpd, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatapd, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_painelpd.createSequentialGroup()
							.addContainerGap()
							.addComponent(lbprecopd, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(lbrempd, GroupLayout.PREFERRED_SIZE, 40, Short.MAX_VALUE))
						.addComponent(lbnomepd, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_painelpd.setVerticalGroup(
			gl_painelpd.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpd.createSequentialGroup()
					.addGroup(gl_painelpd.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbuserpd)
						.addComponent(lbdatapd))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagempd, GroupLayout.PREFERRED_SIZE, 136, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomepd, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelpd.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbprecopd, GroupLayout.DEFAULT_SIZE, 13, Short.MAX_VALUE)
						.addComponent(lbrempd, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
		);
		painelpd.setLayout(gl_painelpd);
		
		painelpd_1 = new JPanel();
		
		lbprecopd_1 = new JLabel("10,00 €");
		lbprecopd_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecopd_1.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbrempd_1 = new JButton("");
		lbrempd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover dos produtos desejados?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtospd.get(1);
					try {
						produtoDAO.removeProdutoDesejado(utilizador.getNomeUtilizador(),produto.getId());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						updatepd();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		lbrempd_1.setContentAreaFilled(false);
		lbrempd_1.setBorderPainted(false);
		lbrempd_1.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/remover16.png")));
		lbrempd_1.setFont(new Font("Dialog", Font.BOLD, 10));
		
		imagempd_1 = new JButton("");
		imagempd_1.setContentAreaFilled(false);
		imagempd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtospd.get(1);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagempd_1.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/esplanad.JPG")));
		
		lbuserpd_1 = new JLabel("Gerente62");
		lbuserpd_1.setVerticalAlignment(SwingConstants.TOP);
		lbuserpd_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserpd_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatapd_1 = new JLabel("12/10/2012");
		lbdatapd_1.setVerticalAlignment(SwingConstants.TOP);
		lbdatapd_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatapd_1.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnomepd_1 = new JLabel("Nome Produto");
		lbnomepd_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomepd_1.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelpd_1 = new GroupLayout(painelpd_1);
		gl_painelpd_1.setHorizontalGroup(
			gl_painelpd_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpd_1.createSequentialGroup()
					.addGroup(gl_painelpd_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnomepd_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
						.addComponent(imagempd_1, GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE)
						.addGroup(gl_painelpd_1.createSequentialGroup()
							.addComponent(lbprecopd_1, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbrempd_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_painelpd_1.createSequentialGroup()
							.addComponent(lbuserpd_1, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatapd_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
		);
		gl_painelpd_1.setVerticalGroup(
			gl_painelpd_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpd_1.createSequentialGroup()
					.addGroup(gl_painelpd_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatapd_1)
						.addComponent(lbuserpd_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagempd_1, GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(lbnomepd_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelpd_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbprecopd_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbrempd_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
		);
		painelpd_1.setLayout(gl_painelpd_1);
		
		painelpd_2 = new JPanel();
		
		imagempd_2 = new JButton("");
		imagempd_2.setContentAreaFilled(false);
		imagempd_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtospd.get(2);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagempd_2.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/esplanad.JPG")));
		
		lbuserpd_2 = new JLabel("Gerente62");
		lbuserpd_2.setVerticalAlignment(SwingConstants.TOP);
		lbuserpd_2.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserpd_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatapd_2 = new JLabel("12/10/2012");
		lbdatapd_2.setVerticalAlignment(SwingConstants.TOP);
		lbdatapd_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatapd_2.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbprecopd_2 = new JLabel("10,00 €");
		lbprecopd_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecopd_2.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbrempd_2 = new JButton("");
		lbrempd_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover dos produtos desejados?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtospd.get(2);
					try {
						produtoDAO.removeProdutoDesejado(utilizador.getNomeUtilizador(),produto.getId());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						updatepd();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		lbrempd_2.setContentAreaFilled(false);
		lbrempd_2.setBorderPainted(false);
		lbrempd_2.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/remover16.png")));
		lbrempd_2.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbnomepd_2 = new JLabel("Nome Produto");
		lbnomepd_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomepd_2.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelpd_2 = new GroupLayout(painelpd_2);
		gl_painelpd_2.setHorizontalGroup(
			gl_painelpd_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpd_2.createSequentialGroup()
					.addGroup(gl_painelpd_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnomepd_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
						.addComponent(imagempd_2, GroupLayout.PREFERRED_SIZE, 142, Short.MAX_VALUE)
						.addGroup(gl_painelpd_2.createSequentialGroup()
							.addComponent(lbuserpd_2, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatapd_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_painelpd_2.createSequentialGroup()
							.addComponent(lbprecopd_2, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbrempd_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
		);
		gl_painelpd_2.setVerticalGroup(
			gl_painelpd_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpd_2.createSequentialGroup()
					.addGroup(gl_painelpd_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatapd_2)
						.addComponent(lbuserpd_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagempd_2, GroupLayout.PREFERRED_SIZE, 136, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(lbnomepd_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelpd_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbprecopd_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbrempd_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
		);
		painelpd_2.setLayout(gl_painelpd_2);
		
		painelpd_3 = new JPanel();
		
		lbuserpd_3 = new JLabel("Gerente62");
		lbuserpd_3.setVerticalAlignment(SwingConstants.TOP);
		lbuserpd_3.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserpd_3.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatapd_3 = new JLabel("12/10/2012");
		lbdatapd_3.setVerticalAlignment(SwingConstants.TOP);
		lbdatapd_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatapd_3.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbprecopd_3 = new JLabel("10,00 €");
		lbprecopd_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecopd_3.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbrempd_3 = new JButton("");
		lbrempd_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover dos produtos desejados?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtospd.get(3);
					try {
						produtoDAO.removeProdutoDesejado(utilizador.getNomeUtilizador(),produto.getId());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						updatepd();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		lbrempd_3.setContentAreaFilled(false);
		lbrempd_3.setBorderPainted(false);
		lbrempd_3.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/remover16.png")));
		lbrempd_3.setFont(new Font("Dialog", Font.BOLD, 10));
		
		imagempd_3 = new JButton("");
		imagempd_3.setContentAreaFilled(false);
		imagempd_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtospd.get(3);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagempd_3.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/esplanad.JPG")));
		
		lbnomepd_3 = new JLabel("Nome Produto");
		lbnomepd_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomepd_3.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelpd_3 = new GroupLayout(painelpd_3);
		gl_painelpd_3.setHorizontalGroup(
			gl_painelpd_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelpd_3.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_painelpd_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(imagempd_3, GroupLayout.PREFERRED_SIZE, 147, Short.MAX_VALUE)
						.addGroup(gl_painelpd_3.createSequentialGroup()
							.addComponent(lbuserpd_3, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatapd_3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_painelpd_3.createSequentialGroup()
							.addComponent(lbprecopd_3, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbrempd_3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(lbnomepd_3, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_painelpd_3.setVerticalGroup(
			gl_painelpd_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpd_3.createSequentialGroup()
					.addGroup(gl_painelpd_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatapd_3)
						.addComponent(lbuserpd_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagempd_3, GroupLayout.PREFERRED_SIZE, 136, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnomepd_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelpd_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbprecopd_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbrempd_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
		);
		painelpd_3.setLayout(gl_painelpd_3);
		
		painelpd_4 = new JPanel();
		
		imagempd_4 = new JButton("");
		imagempd_4.setContentAreaFilled(false);
		imagempd_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtospd.get(4);
				
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
				
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
			}
		});
		imagempd_4.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/esplanad.JPG")));
		
		lbuserpd_4 = new JLabel("Gerente62");
		lbuserpd_4.setVerticalAlignment(SwingConstants.TOP);
		lbuserpd_4.setHorizontalAlignment(SwingConstants.LEFT);
		lbuserpd_4.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdatapd_4 = new JLabel("12/10/2012");
		lbdatapd_4.setVerticalAlignment(SwingConstants.TOP);
		lbdatapd_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbdatapd_4.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbprecopd_4 = new JLabel("10,00 €");
		lbprecopd_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbprecopd_4.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbrempd_4 = new JButton("");
		lbrempd_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover dos produtos desejados?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Produto produto = produtospd.get(4);
					try {
						produtoDAO.removeProdutoDesejado(utilizador.getNomeUtilizador(),produto.getId());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						updatepd();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		lbrempd_4.setContentAreaFilled(false);
		lbrempd_4.setBorderPainted(false);
		lbrempd_4.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/remover16.png")));
		lbrempd_4.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbnomepd_4 = new JLabel("Nome Produto");
		lbnomepd_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbnomepd_4.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painelpd_4 = new GroupLayout(painelpd_4);
		gl_painelpd_4.setHorizontalGroup(
			gl_painelpd_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpd_4.createSequentialGroup()
					.addGroup(gl_painelpd_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(imagempd_4, GroupLayout.PREFERRED_SIZE, 149, Short.MAX_VALUE)
						.addGroup(gl_painelpd_4.createSequentialGroup()
							.addComponent(lbuserpd_4, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdatapd_4, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_painelpd_4.createSequentialGroup()
							.addGroup(gl_painelpd_4.createParallelGroup(Alignment.TRAILING)
								.addComponent(lbnomepd_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
								.addGroup(gl_painelpd_4.createSequentialGroup()
									.addComponent(lbprecopd_4, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lbrempd_4, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(0))
		);
		gl_painelpd_4.setVerticalGroup(
			gl_painelpd_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelpd_4.createSequentialGroup()
					.addGroup(gl_painelpd_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdatapd_4)
						.addComponent(lbuserpd_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagempd_4, GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(lbnomepd_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelpd_4.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lbprecopd_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lbrempd_4, GroupLayout.PREFERRED_SIZE, 13, Short.MAX_VALUE)))
		);
		painelpd_4.setLayout(gl_painelpd_4);
		
		btactpd = new JButton("");
		btactpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualpd = 0;
				try {
					updatepd();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btactpd.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/actualizar.png")));
		btactpd.setContentAreaFilled(false);
		btactpd.setBorderPainted(false);
		
		button_7 = new JButton("");
		button_7.setEnabled(true);
		button_7.setContentAreaFilled(false);
		button_7.setBorderPainted(false);
		
		btesqpd = new JButton("");
		btesqpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualpd -= nrpd;
				try {
					updatepd();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btesqpd.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/setaptras.png")));
		btesqpd.setEnabled(true);
		btesqpd.setContentAreaFilled(false);
		btesqpd.setBorderPainted(false);
		
		btdirpd = new JButton("");
		btdirpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactualpd += nrpd;
				try {
					updatepd();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btdirpd.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/setafrente.png")));
		btdirpd.setEnabled(true);
		btdirpd.setContentAreaFilled(false);
		btdirpd.setBorderPainted(false);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(painelpd, GroupLayout.PREFERRED_SIZE, 304, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(painelpd_1, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelpd_2, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(painelpd_3, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(painelpd_4, GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addComponent(btactpd, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(button_7, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
						.addComponent(btesqpd, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btdirpd, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(9))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(painelpd_3, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(painelpd_4, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(painelpd, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(painelpd_1, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(1))
						.addComponent(painelpd_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(23)
							.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(btdirpd, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btesqpd, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btactpd, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(12)))
					.addGap(4))
		);
		panel_1.setLayout(gl_panel_1);
		
		JButton logo = new JButton("");
		logo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		logo.setContentAreaFilled(false);
		logo.setBorderPainted(false);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(Homev3.class.getResource("/Imagens/eyelogo.png")));
		
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
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
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
				Homev3.this.setVisible(false);
				Homev3.this.dispose();
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
		
		updatenp();
		updatepi();

	}

	private void updatepi() throws SQLException {
		btdirpi.setEnabled(true);
		btesqpi.setEnabled(true);
		painelpi.setVisible(true);
		painelpi_1.setVisible(true);
		painelpi_2.setVisible(true);
		painelpi_3.setVisible(true);
		painelpi_4.setVisible(true);
		produtospi = produtoDAO.getProdutosPodemInteressar(utilizador.getNomeUtilizador(),posicaoactualpi,nrpi);
		
		int arraysize = produtospi.size();
		
		if(posicaoactualpi == 0){
			btesqpi.setEnabled(false);
		}
		
		if (arraysize == 0){
			btdirpi.setEnabled(false);
			btesqpi.setEnabled(false);
			
			painelpi.setVisible(false);
			painelpi_1.setVisible(false);
			painelpi_2.setVisible(false);
			painelpi_3.setVisible(false);
			painelpi_4.setVisible(false);
			
		}else if(arraysize == 1){
			
			Produto produtopi = produtospi.get(0);
			Blob blobpi = imagemDAO.getImagemProduto(produtopi.getId());
			
			imagempi.setIcon(new ImageIcon(blobpi.getBytes(1, (int)blobpi.length())));
			lbprecopi.setText(produtopi.getPreco() + " €");
			lbuserpi.setText(produtopi.getUtilizador().getNomeUtilizador());
			lbdatapi.setText(eyebuy.mostradata(produtopi.getDataColocacao()));
			lbnomepi.setText(produtopi.getNome());
			
			btdirpi.setEnabled(false);
			
			painelpi_1.setVisible(false);
			painelpi_2.setVisible(false);
			painelpi_3.setVisible(false);
			painelpi_4.setVisible(false);
			
		}else if(arraysize == 2){
			
			Produto produtopi = produtospi.get(0);
			Blob blobpi = imagemDAO.getImagemProduto(produtopi.getId());
			
			imagempi.setIcon(new ImageIcon(blobpi.getBytes(1, (int)blobpi.length())));
			lbprecopi.setText(produtopi.getPreco() + " €");
			lbuserpi.setText(produtopi.getUtilizador().getNomeUtilizador());
			lbdatapi.setText(eyebuy.mostradata(produtopi.getDataColocacao()));
			lbnomepi.setText(produtopi.getNome());
			
			Produto produtopi_1 = produtospi.get(1);
			Blob blobpi_1 = imagemDAO.getImagemProduto(produtopi_1.getId());
			
			imagempi_1.setIcon(new ImageIcon(blobpi_1.getBytes(1, (int)blobpi_1.length())));
			lbprecopi_1.setText(produtopi_1.getPreco() + " €");
			lbuserpi_1.setText(produtopi_1.getUtilizador().getNomeUtilizador());
			lbdatapi_1.setText(eyebuy.mostradata(produtopi_1.getDataColocacao()));
			lbnomepi_1.setText(produtopi_1.getNome());
			
			btdirpi.setEnabled(false);

			painelpi_2.setVisible(false);
			painelpi_3.setVisible(false);
			painelpi_4.setVisible(false);
		
		}else if(arraysize == 3){
		
			Produto produtopi = produtospi.get(0);
			Blob blobpi = imagemDAO.getImagemProduto(produtopi.getId());
			
			imagempi.setIcon(new ImageIcon(blobpi.getBytes(1, (int)blobpi.length())));
			lbprecopi.setText(produtopi.getPreco() + " €");
			lbuserpi.setText(produtopi.getUtilizador().getNomeUtilizador());
			lbdatapi.setText(eyebuy.mostradata(produtopi.getDataColocacao()));
			lbnomepi.setText(produtopi.getNome());
			
			Produto produtopi_1 = produtospi.get(1);
			Blob blobpi_1 = imagemDAO.getImagemProduto(produtopi_1.getId());
			
			imagempi_1.setIcon(new ImageIcon(blobpi_1.getBytes(1, (int)blobpi_1.length())));
			lbprecopi_1.setText(produtopi_1.getPreco() + " €");
			lbuserpi_1.setText(produtopi_1.getUtilizador().getNomeUtilizador());
			lbdatapi_1.setText(eyebuy.mostradata(produtopi_1.getDataColocacao()));
			lbnomepi_1.setText(produtopi_1.getNome());
			
			Produto produtopi_2 = produtospi.get(2);
			Blob blobpi_2 = imagemDAO.getImagemProduto(produtopi_2.getId());
			
			imagempi_2.setIcon(new ImageIcon(blobpi_2.getBytes(1, (int)blobpi_2.length())));
			lbprecopi_2.setText(produtopi_2.getPreco() + " €");
			lbuserpi_2.setText(produtopi_2.getUtilizador().getNomeUtilizador());
			lbdatapi_2.setText(eyebuy.mostradata(produtopi_2.getDataColocacao()));
			lbnomepi_2.setText(produtopi_2.getNome());
			
			btdirpi.setEnabled(false);

			painelpi_3.setVisible(false);
			painelpi_4.setVisible(false);
		
		}else if(arraysize == 4){
			
			Produto produtopi = produtospi.get(0);
			Blob blobpi = imagemDAO.getImagemProduto(produtopi.getId());
			
			imagempi.setIcon(new ImageIcon(blobpi.getBytes(1, (int)blobpi.length())));
			lbprecopi.setText(produtopi.getPreco() + " €");
			lbuserpi.setText(produtopi.getUtilizador().getNomeUtilizador());
			lbdatapi.setText(eyebuy.mostradata(produtopi.getDataColocacao()));
			lbnomepi.setText(produtopi.getNome());
			
			Produto produtopi_1 = produtospi.get(1);
			Blob blobpi_1 = imagemDAO.getImagemProduto(produtopi_1.getId());
			
			imagempi_1.setIcon(new ImageIcon(blobpi_1.getBytes(1, (int)blobpi_1.length())));
			lbprecopi_1.setText(produtopi_1.getPreco() + " €");
			lbuserpi_1.setText(produtopi_1.getUtilizador().getNomeUtilizador());
			lbdatapi_1.setText(eyebuy.mostradata(produtopi_1.getDataColocacao()));
			lbnomepi_1.setText(produtopi_1.getNome());
			
			Produto produtopi_2 = produtospi.get(2);
			Blob blobpi_2 = imagemDAO.getImagemProduto(produtopi_2.getId());
			
			imagempi_2.setIcon(new ImageIcon(blobpi_2.getBytes(1, (int)blobpi_2.length())));
			lbprecopi_2.setText(produtopi_2.getPreco() + " €");
			lbuserpi_2.setText(produtopi_2.getUtilizador().getNomeUtilizador());
			lbdatapi_2.setText(eyebuy.mostradata(produtopi_2.getDataColocacao()));
			lbnomepi_2.setText(produtopi_2.getNome());
			
			Produto produtopi_3 = produtospi.get(3);
			Blob blobpi_3 = imagemDAO.getImagemProduto(produtopi_3.getId());
			
			imagempi_3.setIcon(new ImageIcon(blobpi_3.getBytes(1, (int)blobpi_3.length())));
			lbprecopi_3.setText(produtopi_3.getPreco() + " €");
			lbuserpi_3.setText(produtopi_3.getUtilizador().getNomeUtilizador());
			lbdatapi_3.setText(eyebuy.mostradata(produtopi_3.getDataColocacao()));
			lbnomepi_3.setText(produtopi_3.getNome());
			
			btdirpi.setEnabled(false);

			painelpi_4.setVisible(false);
			
		}else{
			Produto produtopi = produtospi.get(0);
			Blob blobpi = imagemDAO.getImagemProduto(produtopi.getId());
			
			imagempi.setIcon(new ImageIcon(blobpi.getBytes(1, (int)blobpi.length())));
			lbprecopi.setText(produtopi.getPreco() + " €");
			lbuserpi.setText(produtopi.getUtilizador().getNomeUtilizador());
			lbdatapi.setText(eyebuy.mostradata(produtopi.getDataColocacao()));
			lbnomepi.setText(produtopi.getNome());
			
			Produto produtopi_1 = produtospi.get(1);
			Blob blobpi_1 = imagemDAO.getImagemProduto(produtopi_1.getId());
			
			imagempi_1.setIcon(new ImageIcon(blobpi_1.getBytes(1, (int)blobpi_1.length())));
			lbprecopi_1.setText(produtopi_1.getPreco() + " €");
			lbuserpi_1.setText(produtopi_1.getUtilizador().getNomeUtilizador());
			lbdatapi_1.setText(eyebuy.mostradata(produtopi_1.getDataColocacao()));
			lbnomepi_1.setText(produtopi_1.getNome());
			
			Produto produtopi_2 = produtospi.get(2);
			Blob blobpi_2 = imagemDAO.getImagemProduto(produtopi_2.getId());
			
			imagempi_2.setIcon(new ImageIcon(blobpi_2.getBytes(1, (int)blobpi_2.length())));
			lbprecopi_2.setText(produtopi_2.getPreco() + " €");
			lbuserpi_2.setText(produtopi_2.getUtilizador().getNomeUtilizador());
			lbdatapi_2.setText(eyebuy.mostradata(produtopi_2.getDataColocacao()));
			lbnomepi_2.setText(produtopi_2.getNome());
			
			Produto produtopi_3 = produtospi.get(3);
			Blob blobpi_3 = imagemDAO.getImagemProduto(produtopi_3.getId());
			
			imagempi_3.setIcon(new ImageIcon(blobpi_3.getBytes(1, (int)blobpi_3.length())));
			lbprecopi_3.setText(produtopi_3.getPreco() + " €");
			lbuserpi_3.setText(produtopi_3.getUtilizador().getNomeUtilizador());
			lbdatapi_3.setText(eyebuy.mostradata(produtopi_3.getDataColocacao()));
			lbnomepi_3.setText(produtopi_3.getNome());
			
			Produto produtopi_4 = produtospi.get(4);
			Blob blobpi_4 = imagemDAO.getImagemProduto(produtopi_4.getId());
			
			imagempi_4.setIcon(new ImageIcon(blobpi_4.getBytes(1, (int)blobpi_4.length())));
			lbprecopi_4.setText(produtopi_4.getPreco() + " €");
			lbuserpi_4.setText(produtopi_4.getUtilizador().getNomeUtilizador());
			lbdatapi_4.setText(eyebuy.mostradata(produtopi_4.getDataColocacao()));

			lbnomepi_4.setText(produtopi_4.getNome());

		}
		
		if(produtoDAO.getProdutosPodemInteressar(utilizador.getNomeUtilizador(), posicaoactualpi+nrpi,1).size()==0){
			btdirpi.setEnabled(false);
		}
		
	}
	
	private void updateps() throws SQLException {
		btdirps.setEnabled(true);
		btesqps.setEnabled(true);
		painelps.setVisible(true);
		painelps_1.setVisible(true);
		painelps_2.setVisible(true);
		painelps_3.setVisible(true);
		painelps_4.setVisible(true);
		produtosps = produtoDAO.getProdutosSeguidos(utilizador.getNomeUtilizador(),posicaoactualps,nrps);
		
		btdirps.setEnabled(true);
		btesqps.setEnabled(true);
		
		int arraysize = produtosps.size();
		
		if(posicaoactualps == 0){
			btesqps.setEnabled(false);
		}
		
		if (arraysize == 0){
			btdirps.setEnabled(false);
			btesqps.setEnabled(false);
			
			painelps.setVisible(false);
			painelps_1.setVisible(false);
			painelps_2.setVisible(false);
			painelps_3.setVisible(false);
			painelps_4.setVisible(false);
			
		}else if(arraysize == 1){
			
			Produto produtops = produtosps.get(0);
			Blob blobps = imagemDAO.getImagemProduto(produtops.getId());
			
			imagemps.setIcon(new ImageIcon(blobps.getBytes(1, (int)blobps.length())));
			lbprecops.setText(produtops.getPreco() + " €");
			lbuserps.setText(produtops.getUtilizador().getNomeUtilizador());
			lbdataps.setText(eyebuy.mostradata(produtops.getDataColocacao()));
			lbnomeps.setText(produtops.getNome());
			
			btdirps.setEnabled(false);
			
			painelps_1.setVisible(false);
			painelps_2.setVisible(false);
			painelps_3.setVisible(false);
			painelps_4.setVisible(false);
			
		}else if(arraysize == 2){
			
			Produto produtops = produtosps.get(0);
			Blob blobps = imagemDAO.getImagemProduto(produtops.getId());
			
			imagemps.setIcon(new ImageIcon(blobps.getBytes(1, (int)blobps.length())));
			lbprecops.setText(produtops.getPreco() + " €");
			lbuserps.setText(produtops.getUtilizador().getNomeUtilizador());
			lbdataps.setText(eyebuy.mostradata(produtops.getDataColocacao()));
			lbnomeps.setText(produtops.getNome());
			
			Produto produtops_1 = produtosps.get(1);
			Blob blobps_1 = imagemDAO.getImagemProduto(produtops_1.getId());
			
			imagemps_1.setIcon(new ImageIcon(blobps_1.getBytes(1, (int)blobps_1.length())));
			lbprecops_1.setText(produtops_1.getPreco() + " €");
			lbuserps_1.setText(produtops_1.getUtilizador().getNomeUtilizador());
			lbdataps_1.setText(eyebuy.mostradata(produtops_1.getDataColocacao()));
			lbnomeps_1.setText(produtops_1.getNome());
			
			btdirps.setEnabled(false);

			painelps_2.setVisible(false);
			painelps_3.setVisible(false);
			painelps_4.setVisible(false);
		
		}else if(arraysize == 3){
		
			Produto produtops = produtosps.get(0);
			Blob blobps = imagemDAO.getImagemProduto(produtops.getId());
			
			imagemps.setIcon(new ImageIcon(blobps.getBytes(1, (int)blobps.length())));
			lbprecops.setText(produtops.getPreco() + " €");
			lbuserps.setText(produtops.getUtilizador().getNomeUtilizador());
			lbdataps.setText(eyebuy.mostradata(produtops.getDataColocacao()));
			lbnomeps.setText(produtops.getNome());
			
			Produto produtops_1 = produtosps.get(1);
			Blob blobps_1 = imagemDAO.getImagemProduto(produtops_1.getId());
			
			imagemps_1.setIcon(new ImageIcon(blobps_1.getBytes(1, (int)blobps_1.length())));
			lbprecops_1.setText(produtops_1.getPreco() + " €");
			lbuserps_1.setText(produtops_1.getUtilizador().getNomeUtilizador());
			lbdataps_1.setText(eyebuy.mostradata(produtops_1.getDataColocacao()));
			lbnomeps_1.setText(produtops_1.getNome());
			
			Produto produtops_2 = produtosps.get(2);
			Blob blobps_2 = imagemDAO.getImagemProduto(produtops_2.getId());
			
			imagemps_2.setIcon(new ImageIcon(blobps_2.getBytes(1, (int)blobps_2.length())));
			lbprecops_2.setText(produtops_2.getPreco() + " €");
			lbuserps_2.setText(produtops_2.getUtilizador().getNomeUtilizador());
			lbdataps_2.setText(eyebuy.mostradata(produtops_2.getDataColocacao()));
			lbnomeps_2.setText(produtops_2.getNome());
			
			btdirps.setEnabled(false);

			painelps_3.setVisible(false);
			painelps_4.setVisible(false);
		
		}else if(arraysize == 4){
			
			Produto produtops = produtosps.get(0);
			Blob blobps = imagemDAO.getImagemProduto(produtops.getId());
			
			imagemps.setIcon(new ImageIcon(blobps.getBytes(1, (int)blobps.length())));
			lbprecops.setText(produtops.getPreco() + " €");
			lbuserps.setText(produtops.getUtilizador().getNomeUtilizador());
			lbdataps.setText(eyebuy.mostradata(produtops.getDataColocacao()));
			lbnomeps.setText(produtops.getNome());
			
			Produto produtops_1 = produtosps.get(1);
			Blob blobps_1 = imagemDAO.getImagemProduto(produtops_1.getId());
			
			imagemps_1.setIcon(new ImageIcon(blobps_1.getBytes(1, (int)blobps_1.length())));
			lbprecops_1.setText(produtops_1.getPreco() + " €");
			lbuserps_1.setText(produtops_1.getUtilizador().getNomeUtilizador());
			lbdataps_1.setText(eyebuy.mostradata(produtops_1.getDataColocacao()));
			lbnomeps_1.setText(produtops_1.getNome());
			
			Produto produtops_2 = produtosps.get(2);
			Blob blobps_2 = imagemDAO.getImagemProduto(produtops_2.getId());
			
			imagemps_2.setIcon(new ImageIcon(blobps_2.getBytes(1, (int)blobps_2.length())));
			lbprecops_2.setText(produtops_2.getPreco() + " €");
			lbuserps_2.setText(produtops_2.getUtilizador().getNomeUtilizador());
			lbdataps_2.setText(eyebuy.mostradata(produtops_2.getDataColocacao()));
			lbnomeps_2.setText(produtops_2.getNome());
			
			Produto produtops_3 = produtosps.get(3);
			Blob blobps_3 = imagemDAO.getImagemProduto(produtops_3.getId());
			
			imagemps_3.setIcon(new ImageIcon(blobps_3.getBytes(1, (int)blobps_3.length())));
			lbprecops_3.setText(produtops_3.getPreco() + " €");
			lbuserps_3.setText(produtops_3.getUtilizador().getNomeUtilizador());
			lbdataps_3.setText(eyebuy.mostradata(produtops_3.getDataColocacao()));
			lbnomeps_3.setText(produtops_3.getNome());
			
			btdirps.setEnabled(false);

			painelps_4.setVisible(false);
			
		}else{
			Produto produtops = produtosps.get(0);
			Blob blobps = imagemDAO.getImagemProduto(produtops.getId());
			
			imagemps.setIcon(new ImageIcon(blobps.getBytes(1, (int)blobps.length())));
			lbprecops.setText(produtops.getPreco() + " €");
			lbuserps.setText(produtops.getUtilizador().getNomeUtilizador());
			lbdataps.setText(eyebuy.mostradata(produtops.getDataColocacao()));
			lbnomeps.setText(produtops.getNome());
			
			Produto produtops_1 = produtosps.get(1);
			Blob blobps_1 = imagemDAO.getImagemProduto(produtops_1.getId());
			
			imagemps_1.setIcon(new ImageIcon(blobps_1.getBytes(1, (int)blobps_1.length())));
			lbprecops_1.setText(produtops_1.getPreco() + " €");
			lbuserps_1.setText(produtops_1.getUtilizador().getNomeUtilizador());
			lbdataps_1.setText(eyebuy.mostradata(produtops_1.getDataColocacao()));
			lbnomeps_1.setText(produtops_1.getNome());
			
			Produto produtops_2 = produtosps.get(2);
			Blob blobps_2 = imagemDAO.getImagemProduto(produtops_2.getId());
			
			imagemps_2.setIcon(new ImageIcon(blobps_2.getBytes(1, (int)blobps_2.length())));
			lbprecops_2.setText(produtops_2.getPreco() + " €");
			lbuserps_2.setText(produtops_2.getUtilizador().getNomeUtilizador());
			lbdataps_2.setText(eyebuy.mostradata(produtops_2.getDataColocacao()));
			lbnomeps_2.setText(produtops_2.getNome());
			
			Produto produtops_3 = produtosps.get(3);
			Blob blobps_3 = imagemDAO.getImagemProduto(produtops_3.getId());
			
			imagemps_3.setIcon(new ImageIcon(blobps_3.getBytes(1, (int)blobps_3.length())));
			lbprecops_3.setText(produtops_3.getPreco() + " €");
			lbuserps_3.setText(produtops_3.getUtilizador().getNomeUtilizador());
			lbdataps_3.setText(eyebuy.mostradata(produtops_3.getDataColocacao()));
			lbnomeps_3.setText(produtops_3.getNome());
			
			Produto produtops_4 = produtosps.get(4);
			Blob blobps_4 = imagemDAO.getImagemProduto(produtops_4.getId());
			
			imagemps_4.setIcon(new ImageIcon(blobps_4.getBytes(1, (int)blobps_4.length())));
			lbprecops_4.setText(produtops_4.getPreco() + " €");
			lbuserps_4.setText(produtops_4.getUtilizador().getNomeUtilizador());
			lbdataps_4.setText(eyebuy.mostradata(produtops_4.getDataColocacao()));
			lbnomeps_4.setText(produtops_4.getNome());
		}
		
		if(produtoDAO.getProdutosSeguidos(utilizador.getNomeUtilizador(), posicaoactualps+nrps,1).size()==0){
			btdirps.setEnabled(false);
		}
		
	}
	
	private void updatepd() throws SQLException {
		btdirpd.setEnabled(true);
		btesqpd.setEnabled(true);
		painelpd.setVisible(true);
		painelpd_1.setVisible(true);
		painelpd_2.setVisible(true);
		painelpd_3.setVisible(true);
		painelpd_4.setVisible(true);
		produtospd = produtoDAO.getProdutosDesejados(utilizador.getNomeUtilizador(),posicaoactualpd,nrpd);
		
		btdirpd.setEnabled(true);
		btesqpd.setEnabled(true);
		
		int arraysize = produtospd.size();
		
		if(posicaoactualpd == 0){
			btesqpd.setEnabled(false);
		}
		
		if (arraysize == 0){
			btdirpd.setEnabled(false);
			btesqpd.setEnabled(false);
			
			painelpd.setVisible(false);
			painelpd_1.setVisible(false);
			painelpd_2.setVisible(false);
			painelpd_3.setVisible(false);
			painelpd_4.setVisible(false);
			
		}else if(arraysize == 1){
			
			Produto produtopd = produtospd.get(0);
			Blob blobpd = imagemDAO.getImagemProduto(produtopd.getId());
			
			imagempd.setIcon(new ImageIcon(blobpd.getBytes(1, (int)blobpd.length())));
			lbprecopd.setText(produtopd.getPreco() + " €");
			lbuserpd.setText(produtopd.getUtilizador().getNomeUtilizador());
			lbdatapd.setText(eyebuy.mostradata(produtopd.getDataColocacao()));
			lbnomepd.setText(produtopd.getNome());
			
			btdirpd.setEnabled(false);
			
			painelpd_1.setVisible(false);
			painelpd_2.setVisible(false);
			painelpd_3.setVisible(false);
			painelpd_4.setVisible(false);
			
		}else if(arraysize == 2){
			
			Produto produtopd = produtospd.get(0);
			Blob blobpd = imagemDAO.getImagemProduto(produtopd.getId());
			
			imagempd.setIcon(new ImageIcon(blobpd.getBytes(1, (int)blobpd.length())));
			lbprecopd.setText(produtopd.getPreco() + " €");
			lbuserpd.setText(produtopd.getUtilizador().getNomeUtilizador());
			lbdatapd.setText(eyebuy.mostradata(produtopd.getDataColocacao()));
			lbnomepd.setText(produtopd.getNome());
			
			Produto produtopd_1 = produtospd.get(1);
			Blob blobpd_1 = imagemDAO.getImagemProduto(produtopd_1.getId());
			
			imagempd_1.setIcon(new ImageIcon(blobpd_1.getBytes(1, (int)blobpd_1.length())));
			lbprecopd_1.setText(produtopd_1.getPreco() + " €");
			lbuserpd_1.setText(produtopd_1.getUtilizador().getNomeUtilizador());
			lbdatapd_1.setText(eyebuy.mostradata(produtopd_1.getDataColocacao()));
			lbnomepd_1.setText(produtopd_1.getNome());
			
			btdirpd.setEnabled(false);

			painelpd_2.setVisible(false);
			painelpd_3.setVisible(false);
			painelpd_4.setVisible(false);
		
		}else if(arraysize == 3){
		
			Produto produtopd = produtospd.get(0);
			Blob blobpd = imagemDAO.getImagemProduto(produtopd.getId());
			
			imagempd.setIcon(new ImageIcon(blobpd.getBytes(1, (int)blobpd.length())));
			lbprecopd.setText(produtopd.getPreco() + " €");
			lbuserpd.setText(produtopd.getUtilizador().getNomeUtilizador());
			lbdatapd.setText(eyebuy.mostradata(produtopd.getDataColocacao()));
			lbnomepd.setText(produtopd.getNome());
			
			Produto produtopd_1 = produtospd.get(1);
			Blob blobpd_1 = imagemDAO.getImagemProduto(produtopd_1.getId());
			
			imagempd_1.setIcon(new ImageIcon(blobpd_1.getBytes(1, (int)blobpd_1.length())));
			lbprecopd_1.setText(produtopd_1.getPreco() + " €");
			lbuserpd_1.setText(produtopd_1.getUtilizador().getNomeUtilizador());
			lbdatapd_1.setText(eyebuy.mostradata(produtopd_1.getDataColocacao()));
			lbnomepd_1.setText(produtopd_1.getNome());
			
			Produto produtopd_2 = produtospd.get(2);
			Blob blobpd_2 = imagemDAO.getImagemProduto(produtopd_2.getId());
			
			imagempd_2.setIcon(new ImageIcon(blobpd_2.getBytes(1, (int)blobpd_2.length())));
			lbprecopd_2.setText(produtopd_2.getPreco() + " €");
			lbuserpd_2.setText(produtopd_2.getUtilizador().getNomeUtilizador());
			lbdatapd_2.setText(eyebuy.mostradata(produtopd_2.getDataColocacao()));
			lbnomepd_2.setText(produtopd_2.getNome());
			
			
			btdirpd.setEnabled(false);

			painelpd_3.setVisible(false);
			painelpd_4.setVisible(false);
		
		}else if(arraysize == 4){
			
			Produto produtopd = produtospd.get(0);
			Blob blobpd = imagemDAO.getImagemProduto(produtopd.getId());
			
			imagempd.setIcon(new ImageIcon(blobpd.getBytes(1, (int)blobpd.length())));
			lbprecopd.setText(produtopd.getPreco() + " €");
			lbuserpd.setText(produtopd.getUtilizador().getNomeUtilizador());
			lbdatapd.setText(eyebuy.mostradata(produtopd.getDataColocacao()));
			lbnomepd.setText(produtopd.getNome());
			
			Produto produtopd_1 = produtospd.get(1);
			Blob blobpd_1 = imagemDAO.getImagemProduto(produtopd_1.getId());
			
			imagempd_1.setIcon(new ImageIcon(blobpd_1.getBytes(1, (int)blobpd_1.length())));
			lbprecopd_1.setText(produtopd_1.getPreco() + " €");
			lbuserpd_1.setText(produtopd_1.getUtilizador().getNomeUtilizador());
			lbdatapd_1.setText(eyebuy.mostradata(produtopd_1.getDataColocacao()));
			lbnomepd_1.setText(produtopd_1.getNome());
			
			Produto produtopd_2 = produtospd.get(2);
			Blob blobpd_2 = imagemDAO.getImagemProduto(produtopd_2.getId());
			
			imagempd_2.setIcon(new ImageIcon(blobpd_2.getBytes(1, (int)blobpd_2.length())));
			lbprecopd_2.setText(produtopd_2.getPreco() + " €");
			lbuserpd_2.setText(produtopd_2.getUtilizador().getNomeUtilizador());
			lbdatapd_2.setText(eyebuy.mostradata(produtopd_2.getDataColocacao()));
			lbnomepd_2.setText(produtopd_2.getNome());
			
			Produto produtopd_3 = produtospd.get(3);
			Blob blobpd_3 = imagemDAO.getImagemProduto(produtopd_3.getId());
			
			imagempd_3.setIcon(new ImageIcon(blobpd_3.getBytes(1, (int)blobpd_3.length())));
			lbprecopd_3.setText(produtopd_3.getPreco() + " €");
			lbuserpd_3.setText(produtopd_3.getUtilizador().getNomeUtilizador());
			lbdatapd_3.setText(eyebuy.mostradata(produtopd_3.getDataColocacao()));
			lbnomepd_3.setText(produtopd_3.getNome());
			
			btdirpd.setEnabled(false);

			painelpd_4.setVisible(false);
			
		}else{
			Produto produtopd = produtospd.get(0);
			Blob blobpd = imagemDAO.getImagemProduto(produtopd.getId());
			
			imagempd.setIcon(new ImageIcon(blobpd.getBytes(1, (int)blobpd.length())));
			lbprecopd.setText(produtopd.getPreco() + " €");
			lbuserpd.setText(produtopd.getUtilizador().getNomeUtilizador());
			lbdatapd.setText(eyebuy.mostradata(produtopd.getDataColocacao()));
			lbnomepd.setText(produtopd.getNome());
			
			Produto produtopd_1 = produtospd.get(1);
			Blob blobpd_1 = imagemDAO.getImagemProduto(produtopd_1.getId());
			
			imagempd_1.setIcon(new ImageIcon(blobpd_1.getBytes(1, (int)blobpd_1.length())));
			lbprecopd_1.setText(produtopd_1.getPreco() + " €");
			lbuserpd_1.setText(produtopd_1.getUtilizador().getNomeUtilizador());
			lbdatapd_1.setText(eyebuy.mostradata(produtopd_1.getDataColocacao()));
			lbnomepd_1.setText(produtopd_1.getNome());
			
			Produto produtopd_2 = produtospd.get(2);
			Blob blobpd_2 = imagemDAO.getImagemProduto(produtopd_2.getId());
			
			imagempd_2.setIcon(new ImageIcon(blobpd_2.getBytes(1, (int)blobpd_2.length())));
			lbprecopd_2.setText(produtopd_2.getPreco() + " €");
			lbuserpd_2.setText(produtopd_2.getUtilizador().getNomeUtilizador());
			lbdatapd_2.setText(eyebuy.mostradata(produtopd_2.getDataColocacao()));
			lbnomepd_2.setText(produtopd_2.getNome());
			
			Produto produtopd_3 = produtospd.get(3);
			Blob blobpd_3 = imagemDAO.getImagemProduto(produtopd_3.getId());
			
			imagempd_3.setIcon(new ImageIcon(blobpd_3.getBytes(1, (int)blobpd_3.length())));
			lbprecopd_3.setText(produtopd_3.getPreco() + " €");
			lbuserpd_3.setText(produtopd_3.getUtilizador().getNomeUtilizador());
			lbdatapd_3.setText(eyebuy.mostradata(produtopd_3.getDataColocacao()));
			lbnomepd_3.setText(produtopd_3.getNome());
			
			
			Produto produtopd_4 = produtospd.get(4);
			Blob blobpd_4 = imagemDAO.getImagemProduto(produtopd_4.getId());
			
			imagempd_4.setIcon(new ImageIcon(blobpd_4.getBytes(1, (int)blobpd_4.length())));
			lbprecopd_4.setText(produtopd_4.getPreco() + " €");
			lbuserpd_4.setText(produtopd_4.getUtilizador().getNomeUtilizador());
			lbdatapd_4.setText(eyebuy.mostradata(produtopd_4.getDataColocacao()));
			lbnomepd_4.setText(produtopd_4.getNome());
		}
		
		if(produtoDAO.getProdutosDesejados(utilizador.getNomeUtilizador(), posicaoactualpd+nrpd,1).size()==0){
			btdirpd.setEnabled(false);
		}
		
	}
	
	private void updatenp() throws SQLException {
		btdirnp.setEnabled(true);
		btesqnp.setEnabled(true);
		painelnp.setVisible(true);
		painelnp_1.setVisible(true);
		painelnp_2.setVisible(true);
		painelnp_3.setVisible(true);
		painelnp_4.setVisible(true);
		produtosnp = produtoDAO.getNovosProdutos(utilizador.getNomeUtilizador(),posicaoactualnp,nrnp);
		
		btdirnp.setEnabled(true);
		btesqnp.setEnabled(true);
		
		int arraysize = produtosnp.size();
		
		if(posicaoactualnp == 0){
			btesqnp.setEnabled(false);
		}
		
		if (arraysize == 0){
			btdirnp.setEnabled(false);
			btesqnp.setEnabled(false);
			
			painelnp.setVisible(false);
			painelnp_1.setVisible(false);
			painelnp_2.setVisible(false);
			painelnp_3.setVisible(false);
			painelnp_4.setVisible(false);
			
		}else if(arraysize == 1){
			
			Produto produtonp = produtosnp.get(0);
			Blob blobnp = imagemDAO.getImagemProduto(produtonp.getId());
			
			imagemnp.setIcon(new ImageIcon(blobnp.getBytes(1, (int)blobnp.length())));
			lbpreconp.setText(produtonp.getPreco() + " €");
			lbusernp.setText(produtonp.getUtilizador().getNomeUtilizador());
			lbdatanp.setText(eyebuy.mostradata(produtonp.getDataColocacao()));
			lbnomenp.setText(produtonp.getNome());
			
			btdirnp.setEnabled(false);
			
			painelnp_1.setVisible(false);
			painelnp_2.setVisible(false);
			painelnp_3.setVisible(false);
			painelnp_4.setVisible(false);
			
		}else if(arraysize == 2){
			
			Produto produtonp = produtosnp.get(0);
			Blob blobnp = imagemDAO.getImagemProduto(produtonp.getId());
			
			imagemnp.setIcon(new ImageIcon(blobnp.getBytes(1, (int)blobnp.length())));
			lbpreconp.setText(produtonp.getPreco() + " €");
			lbusernp.setText(produtonp.getUtilizador().getNomeUtilizador());
			lbdatanp.setText(eyebuy.mostradata(produtonp.getDataColocacao()));
			lbnomenp.setText(produtonp.getNome());
			
			Produto produtonp_1 = produtosnp.get(1);
			Blob blobnp_1 = imagemDAO.getImagemProduto(produtonp_1.getId());
			
			imagemnp_1.setIcon(new ImageIcon(blobnp_1.getBytes(1, (int)blobnp_1.length())));
			lbpreconp_1.setText(produtonp_1.getPreco() + " €");
			lbusernp_1.setText(produtonp_1.getUtilizador().getNomeUtilizador());
			lbdatanp_1.setText(eyebuy.mostradata(produtonp_1.getDataColocacao()));
			lbnomenp_1.setText(produtonp_1.getNome());
			
			btdirnp.setEnabled(false);

			painelnp_2.setVisible(false);
			painelnp_3.setVisible(false);
			painelnp_4.setVisible(false);
		
		}else if(arraysize == 3){
		
			Produto produtonp = produtosnp.get(0);
			Blob blobnp = imagemDAO.getImagemProduto(produtonp.getId());
			
			imagemnp.setIcon(new ImageIcon(blobnp.getBytes(1, (int)blobnp.length())));
			lbpreconp.setText(produtonp.getPreco() + " €");
			lbusernp.setText(produtonp.getUtilizador().getNomeUtilizador());
			lbdatanp.setText(eyebuy.mostradata(produtonp.getDataColocacao()));
			lbnomenp.setText(produtonp.getNome());
			
			Produto produtonp_1 = produtosnp.get(1);
			Blob blobnp_1 = imagemDAO.getImagemProduto(produtonp_1.getId());
			
			imagemnp_1.setIcon(new ImageIcon(blobnp_1.getBytes(1, (int)blobnp_1.length())));
			lbpreconp_1.setText(produtonp_1.getPreco() + " €");
			lbusernp_1.setText(produtonp_1.getUtilizador().getNomeUtilizador());
			lbdatanp_1.setText(eyebuy.mostradata(produtonp_1.getDataColocacao()));
			lbnomenp_1.setText(produtonp_1.getNome());
			
			Produto produtonp_2 = produtosnp.get(2);
			Blob blobnp_2 = imagemDAO.getImagemProduto(produtonp_2.getId());
			
			imagemnp_2.setIcon(new ImageIcon(blobnp_2.getBytes(1, (int)blobnp_2.length())));
			lbpreconp_2.setText(produtonp_2.getPreco() + " €");
			lbusernp_2.setText(produtonp_2.getUtilizador().getNomeUtilizador());
			lbdatanp_2.setText(eyebuy.mostradata(produtonp_2.getDataColocacao()));
			lbnomenp_2.setText(produtonp_2.getNome());
			
			
			btdirnp.setEnabled(false);

			painelnp_3.setVisible(false);
			painelnp_4.setVisible(false);
		
		}else if(arraysize == 4){
			
			Produto produtonp = produtosnp.get(0);
			Blob blobnp = imagemDAO.getImagemProduto(produtonp.getId());
			
			imagemnp.setIcon(new ImageIcon(blobnp.getBytes(1, (int)blobnp.length())));
			lbpreconp.setText(produtonp.getPreco() + " €");
			lbusernp.setText(produtonp.getUtilizador().getNomeUtilizador());
			lbdatanp.setText(eyebuy.mostradata(produtonp.getDataColocacao()));
			lbnomenp.setText(produtonp.getNome());
			
			Produto produtonp_1 = produtosnp.get(1);
			Blob blobnp_1 = imagemDAO.getImagemProduto(produtonp_1.getId());
			
			imagemnp_1.setIcon(new ImageIcon(blobnp_1.getBytes(1, (int)blobnp_1.length())));
			lbpreconp_1.setText(produtonp_1.getPreco() + " €");
			lbusernp_1.setText(produtonp_1.getUtilizador().getNomeUtilizador());
			lbdatanp_1.setText(eyebuy.mostradata(produtonp_1.getDataColocacao()));
			lbnomenp_1.setText(produtonp_1.getNome());
			
			Produto produtonp_2 = produtosnp.get(2);
			Blob blobnp_2 = imagemDAO.getImagemProduto(produtonp_2.getId());
			
			imagemnp_2.setIcon(new ImageIcon(blobnp_2.getBytes(1, (int)blobnp_2.length())));
			lbpreconp_2.setText(produtonp_2.getPreco() + " €");
			lbusernp_2.setText(produtonp_2.getUtilizador().getNomeUtilizador());
			lbdatanp_2.setText(eyebuy.mostradata(produtonp_2.getDataColocacao()));
			lbnomenp_2.setText(produtonp_2.getNome());
			
			Produto produtonp_3 = produtosnp.get(3);
			Blob blobnp_3 = imagemDAO.getImagemProduto(produtonp_3.getId());
			
			imagemnp_3.setIcon(new ImageIcon(blobnp_3.getBytes(1, (int)blobnp_3.length())));
			lbpreconp_3.setText(produtonp_3.getPreco() + " €");
			lbusernp_3.setText(produtonp_3.getUtilizador().getNomeUtilizador());
			lbdatanp_3.setText(eyebuy.mostradata(produtonp_3.getDataColocacao()));
			lbnomenp_3.setText(produtonp_3.getNome());
			
			btdirnp.setEnabled(false);

			painelnp_4.setVisible(false);
			
		}else{
			Produto produtonp = produtosnp.get(0);
			Blob blobnp = imagemDAO.getImagemProduto(produtonp.getId());
			
			imagemnp.setIcon(new ImageIcon(blobnp.getBytes(1, (int)blobnp.length())));
			lbpreconp.setText(produtonp.getPreco() + " €");
			lbusernp.setText(produtonp.getUtilizador().getNomeUtilizador());
			lbdatanp.setText(eyebuy.mostradata(produtonp.getDataColocacao()));
			lbnomenp.setText(produtonp.getNome());
			
			Produto produtonp_1 = produtosnp.get(1);
			Blob blobnp_1 = imagemDAO.getImagemProduto(produtonp_1.getId());
			
			imagemnp_1.setIcon(new ImageIcon(blobnp_1.getBytes(1, (int)blobnp_1.length())));
			lbpreconp_1.setText(produtonp_1.getPreco() + " €");
			lbusernp_1.setText(produtonp_1.getUtilizador().getNomeUtilizador());
			lbdatanp_1.setText(eyebuy.mostradata(produtonp_1.getDataColocacao()));
			lbnomenp_1.setText(produtonp_1.getNome());
			
			Produto produtonp_2 = produtosnp.get(2);
			Blob blobnp_2 = imagemDAO.getImagemProduto(produtonp_2.getId());
			
			imagemnp_2.setIcon(new ImageIcon(blobnp_2.getBytes(1, (int)blobnp_2.length())));
			lbpreconp_2.setText(produtonp_2.getPreco() + " €");
			lbusernp_2.setText(produtonp_2.getUtilizador().getNomeUtilizador());
			lbdatanp_2.setText(eyebuy.mostradata(produtonp_2.getDataColocacao()));
			lbnomenp_2.setText(produtonp_2.getNome());
			
			Produto produtonp_3 = produtosnp.get(3);
			Blob blobnp_3 = imagemDAO.getImagemProduto(produtonp_3.getId());
			
			imagemnp_3.setIcon(new ImageIcon(blobnp_3.getBytes(1, (int)blobnp_3.length())));
			lbpreconp_3.setText(produtonp_3.getPreco() + " €");
			lbusernp_3.setText(produtonp_3.getUtilizador().getNomeUtilizador());
			lbdatanp_3.setText(eyebuy.mostradata(produtonp_3.getDataColocacao()));
			lbnomenp_3.setText(produtonp_3.getNome());
			
			
			Produto produtonp_4 = produtosnp.get(4);
			Blob blobnp_4 = imagemDAO.getImagemProduto(produtonp_4.getId());
			
			imagemnp_4.setIcon(new ImageIcon(blobnp_4.getBytes(1, (int)blobnp_4.length())));
			lbpreconp_4.setText(produtonp_4.getPreco() + " €");
			lbusernp_4.setText(produtonp_4.getUtilizador().getNomeUtilizador());
			lbdatanp_4.setText(eyebuy.mostradata(produtonp_4.getDataColocacao()));
			lbnomenp_4.setText(produtonp_4.getNome());
		}
		
		if(produtoDAO.getNovosProdutos(utilizador.getNomeUtilizador(),posicaoactualnp+nrnp,1).size()==0){
			btdirnp.setEnabled(false);
		}
		
	}
	
	private void updatemv() throws SQLException {
		btdirmv.setEnabled(true);
		btesqmv.setEnabled(true);
		painelmv.setVisible(true);
		painelmv_1.setVisible(true);
		painelmv_2.setVisible(true);
		painelmv_3.setVisible(true);
		painelmv_4.setVisible(true);
		produtosmv = produtoDAO.getProdutosMelhoresVendedores(utilizador.getNomeUtilizador(),posicaoactualmv,nrmv);
		
		btdirmv.setEnabled(true);
		btesqmv.setEnabled(true);
		
		int arraysize = produtosmv.size();
		
		if(posicaoactualmv == 0){
			btesqmv.setEnabled(false);
		}
		
		if (arraysize == 0){
			btdirmv.setEnabled(false);
			btesqmv.setEnabled(false);
			
			painelmv.setVisible(false);
			painelmv_1.setVisible(false);
			painelmv_2.setVisible(false);
			painelmv_3.setVisible(false);
			painelmv_4.setVisible(false);
			
		}else if(arraysize == 1){
			
			Produto produtomv = produtosmv.get(0);
			Blob blobmv = imagemDAO.getImagemProduto(produtomv.getId());
			
			imagemmv.setIcon(new ImageIcon(blobmv.getBytes(1, (int)blobmv.length())));
			lbprecomv.setText(produtomv.getPreco() + " €");
			lbusermv.setText(produtomv.getUtilizador().getNomeUtilizador());
			lbdatamv.setText(eyebuy.mostradata(produtomv.getDataColocacao()));
			lbnomemv.setText(produtomv.getNome());
			
			btdirmv.setEnabled(false);
			
			painelmv_1.setVisible(false);
			painelmv_2.setVisible(false);
			painelmv_3.setVisible(false);
			painelmv_4.setVisible(false);
			
		}else if(arraysize == 2){
			
			Produto produtomv = produtosmv.get(0);
			Blob blobmv = imagemDAO.getImagemProduto(produtomv.getId());
			
			imagemmv.setIcon(new ImageIcon(blobmv.getBytes(1, (int)blobmv.length())));
			lbprecomv.setText(produtomv.getPreco() + " €");
			lbusermv.setText(produtomv.getUtilizador().getNomeUtilizador());
			lbdatamv.setText(eyebuy.mostradata(produtomv.getDataColocacao()));
			lbnomemv.setText(produtomv.getNome());
			
			Produto produtomv_1 = produtosmv.get(1);
			Blob blobmv_1 = imagemDAO.getImagemProduto(produtomv_1.getId());
			
			imagemmv_1.setIcon(new ImageIcon(blobmv_1.getBytes(1, (int)blobmv_1.length())));
			lbprecomv_1.setText(produtomv_1.getPreco() + " €");
			lbusermv_1.setText(produtomv_1.getUtilizador().getNomeUtilizador());
			lbdatamv_1.setText(eyebuy.mostradata(produtomv_1.getDataColocacao()));
			lbnomemv_1.setText(produtomv_1.getNome());
			
			btdirmv.setEnabled(false);

			painelmv_2.setVisible(false);
			painelmv_3.setVisible(false);
			painelmv_4.setVisible(false);
		
		}else if(arraysize == 3){
		
			Produto produtomv = produtosmv.get(0);
			Blob blobmv = imagemDAO.getImagemProduto(produtomv.getId());
			
			imagemmv.setIcon(new ImageIcon(blobmv.getBytes(1, (int)blobmv.length())));
			lbprecomv.setText(produtomv.getPreco() + " €");
			lbusermv.setText(produtomv.getUtilizador().getNomeUtilizador());
			lbdatamv.setText(eyebuy.mostradata(produtomv.getDataColocacao()));
			lbnomemv.setText(produtomv.getNome());
			
			Produto produtomv_1 = produtosmv.get(1);
			Blob blobmv_1 = imagemDAO.getImagemProduto(produtomv_1.getId());
			
			imagemmv_1.setIcon(new ImageIcon(blobmv_1.getBytes(1, (int)blobmv_1.length())));
			lbprecomv_1.setText(produtomv_1.getPreco() + " €");
			lbusermv_1.setText(produtomv_1.getUtilizador().getNomeUtilizador());
			lbdatamv_1.setText(eyebuy.mostradata(produtomv_1.getDataColocacao()));
			lbnomemv_1.setText(produtomv_1.getNome());
			
			Produto produtomv_2 = produtosmv.get(2);
			Blob blobmv_2 = imagemDAO.getImagemProduto(produtomv_2.getId());
			
			imagemmv_2.setIcon(new ImageIcon(blobmv_2.getBytes(1, (int)blobmv_2.length())));
			lbprecomv_2.setText(produtomv_2.getPreco() + " €");
			lbusermv_2.setText(produtomv_2.getUtilizador().getNomeUtilizador());
			lbdatamv_2.setText(eyebuy.mostradata(produtomv_2.getDataColocacao()));
			lbnomemv_2.setText(produtomv_2.getNome());
			
			
			btdirmv.setEnabled(false);

			painelmv_3.setVisible(false);
			painelmv_4.setVisible(false);
		
		}else if(arraysize == 4){
			
			Produto produtomv = produtosmv.get(0);
			Blob blobmv = imagemDAO.getImagemProduto(produtomv.getId());
			
			imagemmv.setIcon(new ImageIcon(blobmv.getBytes(1, (int)blobmv.length())));
			lbprecomv.setText(produtomv.getPreco() + " €");
			lbusermv.setText(produtomv.getUtilizador().getNomeUtilizador());
			lbdatamv.setText(eyebuy.mostradata(produtomv.getDataColocacao()));
			lbnomemv.setText(produtomv.getNome());
			
			Produto produtomv_1 = produtosmv.get(1);
			Blob blobmv_1 = imagemDAO.getImagemProduto(produtomv_1.getId());
			
			imagemmv_1.setIcon(new ImageIcon(blobmv_1.getBytes(1, (int)blobmv_1.length())));
			lbprecomv_1.setText(produtomv_1.getPreco() + " €");
			lbusermv_1.setText(produtomv_1.getUtilizador().getNomeUtilizador());
			lbdatamv_1.setText(eyebuy.mostradata(produtomv_1.getDataColocacao()));
			lbnomemv_1.setText(produtomv_1.getNome());
			
			Produto produtomv_2 = produtosmv.get(2);
			Blob blobmv_2 = imagemDAO.getImagemProduto(produtomv_2.getId());
			
			imagemmv_2.setIcon(new ImageIcon(blobmv_2.getBytes(1, (int)blobmv_2.length())));
			lbprecomv_2.setText(produtomv_2.getPreco() + " €");
			lbusermv_2.setText(produtomv_2.getUtilizador().getNomeUtilizador());
			lbdatamv_2.setText(eyebuy.mostradata(produtomv_2.getDataColocacao()));
			lbnomemv_2.setText(produtomv_2.getNome());
			
			Produto produtomv_3 = produtosmv.get(3);
			Blob blobmv_3 = imagemDAO.getImagemProduto(produtomv_3.getId());
			
			imagemmv_3.setIcon(new ImageIcon(blobmv_3.getBytes(1, (int)blobmv_3.length())));
			lbprecomv_3.setText(produtomv_3.getPreco() + " €");
			lbusermv_3.setText(produtomv_3.getUtilizador().getNomeUtilizador());
			lbdatamv_3.setText(eyebuy.mostradata(produtomv_3.getDataColocacao()));
			lbnomemv_3.setText(produtomv_3.getNome());
			
			btdirmv.setEnabled(false);

			painelmv_4.setVisible(false);
			
		}else{
			Produto produtomv = produtosmv.get(0);
			Blob blobmv = imagemDAO.getImagemProduto(produtomv.getId());
			
			imagemmv.setIcon(new ImageIcon(blobmv.getBytes(1, (int)blobmv.length())));
			lbprecomv.setText(produtomv.getPreco() + " €");
			lbusermv.setText(produtomv.getUtilizador().getNomeUtilizador());
			lbdatamv.setText(eyebuy.mostradata(produtomv.getDataColocacao()));
			lbnomemv.setText(produtomv.getNome());
			
			Produto produtomv_1 = produtosmv.get(1);
			Blob blobmv_1 = imagemDAO.getImagemProduto(produtomv_1.getId());
			
			imagemmv_1.setIcon(new ImageIcon(blobmv_1.getBytes(1, (int)blobmv_1.length())));
			lbprecomv_1.setText(produtomv_1.getPreco() + " €");
			lbusermv_1.setText(produtomv_1.getUtilizador().getNomeUtilizador());
			lbdatamv_1.setText(eyebuy.mostradata(produtomv_1.getDataColocacao()));
			lbnomemv_1.setText(produtomv_1.getNome());
			
			Produto produtomv_2 = produtosmv.get(2);
			Blob blobmv_2 = imagemDAO.getImagemProduto(produtomv_2.getId());
			
			imagemmv_2.setIcon(new ImageIcon(blobmv_2.getBytes(1, (int)blobmv_2.length())));
			lbprecomv_2.setText(produtomv_2.getPreco() + " €");
			lbusermv_2.setText(produtomv_2.getUtilizador().getNomeUtilizador());
			lbdatamv_2.setText(eyebuy.mostradata(produtomv_2.getDataColocacao()));
			lbnomemv_2.setText(produtomv_2.getNome());
			
			Produto produtomv_3 = produtosmv.get(3);
			Blob blobmv_3 = imagemDAO.getImagemProduto(produtomv_3.getId());
			
			imagemmv_3.setIcon(new ImageIcon(blobmv_3.getBytes(1, (int)blobmv_3.length())));
			lbprecomv_3.setText(produtomv_3.getPreco() + " €");
			lbusermv_3.setText(produtomv_3.getUtilizador().getNomeUtilizador());
			lbdatamv_3.setText(eyebuy.mostradata(produtomv_3.getDataColocacao()));
			lbnomemv_3.setText(produtomv_3.getNome());
			
			
			Produto produtomv_4 = produtosmv.get(4);
			Blob blobmv_4 = imagemDAO.getImagemProduto(produtomv_4.getId());
			
			imagemmv_4.setIcon(new ImageIcon(blobmv_4.getBytes(1, (int)blobmv_4.length())));
			lbprecomv_4.setText(produtomv_4.getPreco() + " €");
			lbusermv_4.setText(produtomv_4.getUtilizador().getNomeUtilizador());
			lbdatamv_4.setText(eyebuy.mostradata(produtomv_4.getDataColocacao()));
			lbnomemv_4.setText(produtomv_4.getNome());
		}
		
		if(produtoDAO.getProdutosMelhoresVendedores(utilizador.getNomeUtilizador(),posicaoactualmv+nrmv,1).size()==0){
			btdirmv.setEnabled(false);
		}
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
