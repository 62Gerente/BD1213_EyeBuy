package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

import javax.swing.border.TitledBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;

import dao.CategoriaDAO;
import dao.ImagemDAO;
import dao.LocalidadeDAO;
import dao.PesquisaDAO;
import dao.ProdutoDAO;
import dao.SubCategoriaDAO;

import model.EyeBuy;
import model.Produto;
import model.Utilizador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;



public class Pesquisa extends JFrame implements Observer{

	/**
	 * 
	 */
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfoqueprocura;
	Utilizador utilizador = new Utilizador();
	private JTextField tfpreco1;
	private JTextField tfpreco2;
	private ImagemDAO imagemDAO = new ImagemDAO();
	private EyeBuy eyebuy = new EyeBuy();
	private SubCategoriaDAO subcategoriaDAO = new SubCategoriaDAO();
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private LocalidadeDAO localidadeDAO = new LocalidadeDAO();
	private PesquisaDAO pesquisaDAO = new PesquisaDAO();
	
	private String localidade;
	private String categoria;
	private String pesquisa;

	private JButton btcim;
	private JButton btbaix;
	private JButton btact;
	
	private int posicaoactual = 0;
	private int nr = 8;
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	
	
	private JComboBox cbcategoria_1;
	private JComboBox cbsubcategoria;
	private JComboBox cblocalidade;
	private JComboBox cbestado;
	
	private JPanel painel = new JPanel();
	private JButton imagem;
	private JLabel lbuser;
	private JLabel lbdata;
	private JLabel lbpreco;
	private JPanel painel_1 = new JPanel();
	private JButton imagem_1;
	private JLabel lbpreco_1;
	private JLabel lbuser_1;
	private JLabel lbdata_1;
	private JPanel painel_2 = new JPanel();
	private JButton imagem_2;
	private JLabel lbpreco_2;
	private JLabel lbuser_2;
	private JLabel lbdata_2;
	private JPanel painel_3 = new JPanel();
	private JButton imagem_3;
	private JLabel lbpreco_3;
	private JLabel lbuser_3;
	private JLabel lbdata_3;
	private JPanel painel_4 = new JPanel();
	private JButton imagem_4;
	private JLabel lbpreco_4;
	private JLabel lbuser_4;
	private JLabel lbdata_4;
	private JPanel painel_5 = new JPanel();
	private JButton imagem_5;
	private JLabel lbpreco_5;
	private JLabel lbuser_5;
	private JLabel lbdata_5;
	private JPanel painel_6 = new JPanel();
	private JButton imagem_6;
	private JLabel lbpreco_6;
	private JLabel lbuser_6;
	private JLabel lbdata_6;
	private JPanel painel_7 = new JPanel();
	private JButton imagem_7;
	private JLabel lbpreco_7;
	private JLabel lbuser_7;
	private JLabel lbdata_7;
	private JLabel lbnome;
	private JLabel lbnome_1;
	private JLabel lbnome_2;
	private JLabel lbnome_3;
	private JLabel lbnome_4;
	private JLabel lbnome_5;
	private JLabel lbnome_6;
	private JLabel lbnome_7;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pesquisa frame = new Pesquisa(new Utilizador(),"",null,null);
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
	public Pesquisa(Utilizador user, String argpesquisa, String argcategoria, String arglocalidade) throws SQLException {
		produtoDAO.addObserver(this);
		eyebuy.addObserver(this);
		categoriaDAO.addObserver(this);
		subcategoriaDAO.addObserver(this);
		localidadeDAO.addObserver(this);
		pesquisaDAO.addObserver(this);
		
		if(user != null){
			this.utilizador = user.clone();
			utilizador.addObserver(this);
		}
		
		if(!argpesquisa.equals(""))
			pesquisaDAO.addPesquisa(utilizador.getNomeUtilizador(), argpesquisa);
		
		this.pesquisa = argpesquisa;
		this.categoria = argcategoria;
		this.localidade = arglocalidade;
		
		setBackground(Color.WHITE);
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 981, 725);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Comunidade ▼ \n");
		mnNewMenu.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/comm2.png")));
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
		mnCarrinho.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/carr.png")));
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
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
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
		mnUtilizador.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/user2.png")));
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
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
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
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
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
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
			}
		});
		mnUtilizador.add(mntmHistrico);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende sair?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Pesquisa.this.setVisible(false);
					Pesquisa.this.dispose();
				}
			}
		});
		mnUtilizador.add(mntmSair);
		
		JButton btnPginaInicial = new JButton("Página Inicial");
		btnPginaInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Homev3(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
			}
		});
		btnPginaInicial.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/home2.png")));
		btnPginaInicial.setContentAreaFilled(false);
		btnPginaInicial.setBorderPainted(false);
		menuBar.add(btnPginaInicial);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setOpaque(false);
		contentPane.setFocusCycleRoot(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Lista de produtos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_9 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_9, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 947, Short.MAX_VALUE))
					.addGap(6))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 359, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblCategoria_1 = new JLabel("Categoria:");
		lblCategoria_1.setFont(new Font("Dialog", Font.BOLD, 13));
		
		cbcategoria_1 = new JComboBox();
		ArrayList<String> arrl = categoriaDAO.getListCategorias();
		arrl.add(0,"Todas");
		
		String[] arrays = arrl.toArray(new String[arrl.size()]);
		
		cbcategoria_1.setModel(new DefaultComboBoxModel(arrays));
		
		cbcategoria_1.setSelectedItem(this.categoria);
		
	
		
		cblocalidade = new JComboBox();
		
		ArrayList<String> arrl2 = localidadeDAO.getListLocalidades();
		arrl2.add(0,"Todas");
		
		String[] array2 = arrl2.toArray(new String[arrl2.size()]);
		
		cblocalidade.setModel(new DefaultComboBoxModel(array2));
		
		
		JLabel lblSubcategoria = new JLabel("Subcategoria:");
		lblSubcategoria.setFont(new Font("Dialog", Font.BOLD, 13));
		
		
		cbsubcategoria = new JComboBox();
		if(cbcategoria_1.getSelectedIndex() != -1){
			ArrayList<String> arrA = subcategoriaDAO.getListSubCategorias((String)cbcategoria_1.getSelectedItem());
			arrA.add(0,"Todas");
			
			String[] arraysc =arrA.toArray(new String[arrA.size()]);
			
			cbsubcategoria.setModel(new DefaultComboBoxModel(arraysc));
		}
		
		cbcategoria_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbcategoria_1.getSelectedIndex() != -1){
					try {
						ArrayList<String> arrA = subcategoriaDAO.getListSubCategorias((String)cbcategoria_1.getSelectedItem());
						arrA.add(0,"Todas");
						
						String[] arraysc =arrA.toArray(new String[arrA.size()]);
						
						cbsubcategoria.setModel(new DefaultComboBoxModel(arraysc));
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		
		JLabel lblLocalidade = new JLabel("Localidade:");
		lblLocalidade.setFont(new Font("Dialog", Font.BOLD, 13));
		

		cblocalidade.setSelectedItem(this.localidade);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Dialog", Font.BOLD, 13));
		
		cbestado = new JComboBox();
		cbestado.setModel(new DefaultComboBoxModel(new String[] {"Qualquer", "Novo", "Usado"}));
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(cbestado, popupMenu);
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		popupMenu.add(mntmNovo);
		
		JMenuItem mntmUsado = new JMenuItem("Usado");
		popupMenu.add(mntmUsado);
		
		JLabel lblPreo = new JLabel("Preço entre:");
		lblPreo.setFont(new Font("Dialog", Font.BOLD, 13));
		
		tfpreco1 = new JTextField();
		tfpreco1.setText("0");
		tfpreco1.setColumns(10);
		
		tfpreco2 = new JTextField();
		tfpreco2.setText("0");
		tfpreco2.setColumns(10);
		
		JLabel lblE = new JLabel("e");
		lblE.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblCategoria_1, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
							.addGap(2))
						.addComponent(lblSubcategoria, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(cbsubcategoria, 0, 197, Short.MAX_VALUE)
						.addComponent(cbcategoria_1, 0, 197, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblLocalidade, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
							.addGap(12)
							.addComponent(cblocalidade, 0, 195, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblEstado, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
							.addGap(12)
							.addComponent(cbestado, 0, 195, Short.MAX_VALUE)
							.addGap(18)))
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblPreo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(tfpreco2)
						.addComponent(tfpreco1, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCategoria_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(cbcategoria_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblLocalidade, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(cblocalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPreo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(tfpreco1, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel_3.createSequentialGroup()
								.addGap(2)
								.addComponent(lblEstado, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addComponent(cbestado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSubcategoria, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
							.addComponent(cbsubcategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(tfpreco2, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
							.addComponent(lblE)))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		btcim = new JButton("");
		btcim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactual -= nr;
				try {
					updatep();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btcim.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/setacima.png")));
		btcim.setEnabled(true);
		btcim.setContentAreaFilled(false);
		btcim.setBorderPainted(false);
		
		btbaix = new JButton("");
		btbaix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactual += nr;
				try {
					updatep();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btbaix.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/setabaixo.png")));
		btbaix.setEnabled(true);
		btbaix.setContentAreaFilled(false);
		btbaix.setBorderPainted(false);
		
		btact = new JButton("");
		btact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicaoactual = 0;
				try {
					updatep();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btact.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/actualizar.png")));
		btact.setContentAreaFilled(false);
		btact.setBorderPainted(false);
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_9.createSequentialGroup()
					.addContainerGap(522, Short.MAX_VALUE)
					.addComponent(btcim, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btbaix, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btact, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_9.setVerticalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addComponent(btcim, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
				.addComponent(btbaix, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
				.addComponent(btact, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
		);
		panel_9.setLayout(gl_panel_9);
		
		painel = new JPanel();
		
		imagem = new JButton("");
		imagem.setContentAreaFilled(false);
		imagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtos.get(0);
				
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
				
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
			}
		});
		imagem.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/casa-praia-recreio-b3.jpg")));
		
		lbpreco = new JLabel("10,00 €");
		lbpreco.setHorizontalAlignment(SwingConstants.CENTER);
		lbpreco.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuser = new JLabel("Gerente62");
		lbuser.setVerticalAlignment(SwingConstants.TOP);
		lbuser.setHorizontalAlignment(SwingConstants.LEFT);
		lbuser.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdata = new JLabel("12/10/2012");
		lbdata.setVerticalAlignment(SwingConstants.TOP);
		lbdata.setHorizontalAlignment(SwingConstants.CENTER);
		lbdata.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnome = new JLabel("Nome Produto");
		lbnome.setHorizontalAlignment(SwingConstants.CENTER);
		lbnome.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painel = new GroupLayout(painel);
		gl_painel.setHorizontalGroup(
			gl_painel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel.createSequentialGroup()
					.addGroup(gl_painel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbpreco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addComponent(lbnome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addComponent(imagem, GroupLayout.PREFERRED_SIZE, 220, Short.MAX_VALUE)
						.addGroup(gl_painel.createSequentialGroup()
							.addComponent(lbuser, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdata)))
					.addGap(0))
		);
		gl_painel.setVerticalGroup(
			gl_painel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel.createSequentialGroup()
					.addGroup(gl_painel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdata)
						.addComponent(lbuser))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagem, GroupLayout.PREFERRED_SIZE, 92, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(lbnome, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbpreco, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painel.setLayout(gl_painel);
		
		painel_1 = new JPanel();
		
		imagem_1 = new JButton("");
		imagem_1.setContentAreaFilled(false);
		imagem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtos.get(1);
				
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
				
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
			}
		});
		imagem_1.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/casa-praia-recreio-b3.jpg")));
		
		lbpreco_1 = new JLabel("10,00 €");
		lbpreco_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbpreco_1.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuser_1 = new JLabel("Gerente62");
		lbuser_1.setVerticalAlignment(SwingConstants.TOP);
		lbuser_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbuser_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdata_1 = new JLabel("12/10/2012");
		lbdata_1.setVerticalAlignment(SwingConstants.TOP);
		lbdata_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbdata_1.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnome_1 = new JLabel("Nome Produto");
		lbnome_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbnome_1.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painel_1 = new GroupLayout(painel_1);
		gl_painel_1.setHorizontalGroup(
			gl_painel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_1.createSequentialGroup()
					.addGroup(gl_painel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnome_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addComponent(imagem_1, GroupLayout.PREFERRED_SIZE, 220, Short.MAX_VALUE)
						.addComponent(lbpreco_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addGroup(gl_painel_1.createSequentialGroup()
							.addComponent(lbuser_1, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdata_1)))
					.addGap(0))
		);
		gl_painel_1.setVerticalGroup(
			gl_painel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_1.createSequentialGroup()
					.addGroup(gl_painel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdata_1)
						.addComponent(lbuser_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagem_1, GroupLayout.PREFERRED_SIZE, 92, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(lbnome_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbpreco_1, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painel_1.setLayout(gl_painel_1);
		
		painel_2 = new JPanel();
		
		imagem_2 = new JButton("");
		imagem_2.setContentAreaFilled(false);
		imagem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtos.get(2);
				
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
				
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
			}
		});
		imagem_2.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/casa-praia-recreio-b3.jpg")));
		
		lbpreco_2 = new JLabel("10,00 €");
		lbpreco_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbpreco_2.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuser_2 = new JLabel("Gerente62");
		lbuser_2.setVerticalAlignment(SwingConstants.TOP);
		lbuser_2.setHorizontalAlignment(SwingConstants.LEFT);
		lbuser_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdata_2 = new JLabel("12/10/2012");
		lbdata_2.setVerticalAlignment(SwingConstants.TOP);
		lbdata_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbdata_2.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnome_2 = new JLabel("Nome Produto");
		lbnome_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbnome_2.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painel_2 = new GroupLayout(painel_2);
		gl_painel_2.setHorizontalGroup(
			gl_painel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_2.createSequentialGroup()
					.addGroup(gl_painel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnome_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addComponent(imagem_2, GroupLayout.PREFERRED_SIZE, 220, Short.MAX_VALUE)
						.addComponent(lbpreco_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addGroup(gl_painel_2.createSequentialGroup()
							.addComponent(lbuser_2, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdata_2)))
					.addGap(0))
		);
		gl_painel_2.setVerticalGroup(
			gl_painel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_2.createSequentialGroup()
					.addGroup(gl_painel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdata_2)
						.addComponent(lbuser_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagem_2, GroupLayout.PREFERRED_SIZE, 92, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(lbnome_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbpreco_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painel_2.setLayout(gl_painel_2);
		
		painel_3 = new JPanel();
		
		imagem_3 = new JButton("");
		imagem_3.setContentAreaFilled(false);
		imagem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtos.get(3);
				
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
				
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
			}
		});
		imagem_3.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/casa-praia-recreio-b3.jpg")));
		
		lbpreco_3 = new JLabel("10,00 €");
		lbpreco_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbpreco_3.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuser_3 = new JLabel("Gerente62");
		lbuser_3.setVerticalAlignment(SwingConstants.TOP);
		lbuser_3.setHorizontalAlignment(SwingConstants.LEFT);
		lbuser_3.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdata_3 = new JLabel("12/10/2012");
		lbdata_3.setVerticalAlignment(SwingConstants.TOP);
		lbdata_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbdata_3.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnome_3 = new JLabel("Nome Produto");
		lbnome_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbnome_3.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painel_3 = new GroupLayout(painel_3);
		gl_painel_3.setHorizontalGroup(
			gl_painel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_3.createSequentialGroup()
					.addGroup(gl_painel_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnome_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
						.addComponent(imagem_3, GroupLayout.PREFERRED_SIZE, 223, Short.MAX_VALUE)
						.addComponent(lbpreco_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
						.addGroup(gl_painel_3.createSequentialGroup()
							.addComponent(lbuser_3, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdata_3)))
					.addGap(0))
		);
		gl_painel_3.setVerticalGroup(
			gl_painel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_3.createSequentialGroup()
					.addGroup(gl_painel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdata_3)
						.addComponent(lbuser_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagem_3, GroupLayout.PREFERRED_SIZE, 93, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbnome_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(lbpreco_3, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painel_3.setLayout(gl_painel_3);
		
		painel_4 = new JPanel();
		
		imagem_4 = new JButton("");
		imagem_4.setContentAreaFilled(false);
		imagem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtos.get(4);
				
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
				
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
			}
		});
		imagem_4.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/casa-praia-recreio-b3.jpg")));
		
		lbpreco_4 = new JLabel("10,00 €");
		lbpreco_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbpreco_4.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuser_4 = new JLabel("Gerente62");
		lbuser_4.setVerticalAlignment(SwingConstants.TOP);
		lbuser_4.setHorizontalAlignment(SwingConstants.LEFT);
		lbuser_4.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdata_4 = new JLabel("12/10/2012");
		lbdata_4.setVerticalAlignment(SwingConstants.TOP);
		lbdata_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbdata_4.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnome_4 = new JLabel("Nome Produto");
		lbnome_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbnome_4.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painel_4 = new GroupLayout(painel_4);
		gl_painel_4.setHorizontalGroup(
			gl_painel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_4.createSequentialGroup()
					.addGroup(gl_painel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnome_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addComponent(imagem_4, GroupLayout.PREFERRED_SIZE, 220, Short.MAX_VALUE)
						.addComponent(lbpreco_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addGroup(gl_painel_4.createSequentialGroup()
							.addComponent(lbuser_4, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdata_4)))
					.addGap(0))
		);
		gl_painel_4.setVerticalGroup(
			gl_painel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_4.createSequentialGroup()
					.addGroup(gl_painel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdata_4)
						.addComponent(lbuser_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagem_4, GroupLayout.PREFERRED_SIZE, 93, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(lbnome_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbpreco_4, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painel_4.setLayout(gl_painel_4);
		
		painel_5 = new JPanel();
		
		imagem_5 = new JButton("");
		imagem_5.setContentAreaFilled(false);
		imagem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtos.get(5);
				
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
				
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
			}
		});
		imagem_5.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/casa-praia-recreio-b3.jpg")));
		
		lbpreco_5 = new JLabel("10,00 €");
		lbpreco_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbpreco_5.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuser_5 = new JLabel("Gerente62");
		lbuser_5.setVerticalAlignment(SwingConstants.TOP);
		lbuser_5.setHorizontalAlignment(SwingConstants.LEFT);
		lbuser_5.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdata_5 = new JLabel("12/10/2012");
		lbdata_5.setVerticalAlignment(SwingConstants.TOP);
		lbdata_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbdata_5.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnome_5 = new JLabel("Nome Produto");
		lbnome_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbnome_5.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painel_5 = new GroupLayout(painel_5);
		gl_painel_5.setHorizontalGroup(
			gl_painel_5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_5.createSequentialGroup()
					.addGroup(gl_painel_5.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnome_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addComponent(imagem_5, GroupLayout.PREFERRED_SIZE, 220, Short.MAX_VALUE)
						.addComponent(lbpreco_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addGroup(gl_painel_5.createSequentialGroup()
							.addComponent(lbuser_5, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdata_5)))
					.addGap(0))
		);
		gl_painel_5.setVerticalGroup(
			gl_painel_5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_5.createSequentialGroup()
					.addGroup(gl_painel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdata_5)
						.addComponent(lbuser_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagem_5, GroupLayout.PREFERRED_SIZE, 93, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbnome_5, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(lbpreco_5, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painel_5.setLayout(gl_painel_5);
		
		painel_6 = new JPanel();
		
		imagem_6 = new JButton("");
		imagem_6.setContentAreaFilled(false);
		imagem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtos.get(6);
				
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
				
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
			}
		});
		imagem_6.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/casa-praia-recreio-b3.jpg")));
		
		lbpreco_6 = new JLabel("10,00 €");
		lbpreco_6.setHorizontalAlignment(SwingConstants.CENTER);
		lbpreco_6.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuser_6 = new JLabel("Gerente62");
		lbuser_6.setVerticalAlignment(SwingConstants.TOP);
		lbuser_6.setHorizontalAlignment(SwingConstants.LEFT);
		lbuser_6.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdata_6 = new JLabel("12/10/2012");
		lbdata_6.setVerticalAlignment(SwingConstants.TOP);
		lbdata_6.setHorizontalAlignment(SwingConstants.CENTER);
		lbdata_6.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnome_6 = new JLabel("Nome Produto");
		lbnome_6.setHorizontalAlignment(SwingConstants.CENTER);
		lbnome_6.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painel_6 = new GroupLayout(painel_6);
		gl_painel_6.setHorizontalGroup(
			gl_painel_6.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_6.createSequentialGroup()
					.addGroup(gl_painel_6.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnome_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addComponent(imagem_6, GroupLayout.PREFERRED_SIZE, 220, Short.MAX_VALUE)
						.addComponent(lbpreco_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
						.addGroup(gl_painel_6.createSequentialGroup()
							.addComponent(lbuser_6, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdata_6)))
					.addGap(0))
		);
		gl_painel_6.setVerticalGroup(
			gl_painel_6.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_6.createSequentialGroup()
					.addGroup(gl_painel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdata_6)
						.addComponent(lbuser_6))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagem_6, GroupLayout.PREFERRED_SIZE, 92, Short.MAX_VALUE)
					.addGap(8)
					.addComponent(lbnome_6, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbpreco_6, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painel_6.setLayout(gl_painel_6);
		
		painel_7 = new JPanel();
		
		imagem_7 = new JButton("");
		imagem_7.setContentAreaFilled(false);
		imagem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = produtos.get(7);
				
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
				
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
			}
		});
		imagem_7.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/casa-praia-recreio-b3.jpg")));
		
		lbpreco_7 = new JLabel("10,00 €");
		lbpreco_7.setHorizontalAlignment(SwingConstants.CENTER);
		lbpreco_7.setFont(new Font("Dialog", Font.BOLD, 10));
		
		lbuser_7 = new JLabel("Gerente62");
		lbuser_7.setVerticalAlignment(SwingConstants.TOP);
		lbuser_7.setHorizontalAlignment(SwingConstants.LEFT);
		lbuser_7.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbdata_7 = new JLabel("12/10/2012");
		lbdata_7.setVerticalAlignment(SwingConstants.TOP);
		lbdata_7.setHorizontalAlignment(SwingConstants.CENTER);
		lbdata_7.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		lbnome_7 = new JLabel("Nome Produto");
		lbnome_7.setHorizontalAlignment(SwingConstants.CENTER);
		lbnome_7.setFont(new Font("Dialog", Font.BOLD, 10));
		GroupLayout gl_painel_7 = new GroupLayout(painel_7);
		gl_painel_7.setHorizontalGroup(
			gl_painel_7.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_7.createSequentialGroup()
					.addGroup(gl_painel_7.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbnome_7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
						.addComponent(imagem_7, GroupLayout.PREFERRED_SIZE, 223, Short.MAX_VALUE)
						.addComponent(lbpreco_7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
						.addGroup(gl_painel_7.createSequentialGroup()
							.addComponent(lbuser_7, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbdata_7)))
					.addGap(0))
		);
		gl_painel_7.setVerticalGroup(
			gl_painel_7.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel_7.createSequentialGroup()
					.addGroup(gl_painel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbdata_7)
						.addComponent(lbuser_7))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(imagem_7, GroupLayout.PREFERRED_SIZE, 93, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbnome_7, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(lbpreco_7, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
		);
		painel_7.setLayout(gl_painel_7);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(painel, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(painel_1, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(painel_2, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(painel_3, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(painel_4, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(painel_5, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(painel_6, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(painel_7, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))
					.addGap(9))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(painel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(painel_1, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
									.addGap(12)))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(painel_2, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
								.addGap(12)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(painel_3, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
							.addGap(12)))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(painel_7, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
						.addComponent(painel_6, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
						.addComponent(painel_5, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
						.addComponent(painel_4, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
					.addGap(8))
		);
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
				Pesquisa.this.setVisible(false);
				Pesquisa.this.dispose();
			}
		});
		logo.setContentAreaFilled(false);
		logo.setBorderPainted(false);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/eyelogo.png")));
		
		JLabel lblPesquisar = new JLabel("O que é que procura?");
		lblPesquisar.setFont(new Font("Dialog", Font.BOLD, 15));
		
		tfoqueprocura = new JTextField();
		tfoqueprocura.setColumns(10);
		tfoqueprocura.setText(this.pesquisa);
		
		JButton btnpesquisar = new JButton("");
		btnpesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatep();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnpesquisar.setIcon(new ImageIcon(Pesquisa.class.getResource("/Imagens/proc64.png")));
		btnpesquisar.setBorderPainted(false);
		btnpesquisar.setContentAreaFilled(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(logo, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblPesquisar, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
							.addGap(323))
						.addComponent(tfoqueprocura, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(btnpesquisar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(logo, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(37)
							.addComponent(lblPesquisar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfoqueprocura, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addComponent(btnpesquisar, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		updatep();
	}
	private void updatep() throws SQLException {
		btcim.setEnabled(true);
		btbaix.setEnabled(true);
		
		if(tfpreco1.getText().equals("") || tfpreco2.getText().equals("") || !eyebuy.isDouble(tfpreco1.getText()) || !eyebuy.isDouble(tfpreco2.getText())){
			JOptionPane.showMessageDialog(null, "Introduza preços válidos", "Erro", JOptionPane.WARNING_MESSAGE );
		}
		
		else{ if(!tfoqueprocura.getText().equals(""))
			pesquisaDAO.addPesquisa(utilizador.getNomeUtilizador(),tfoqueprocura.getText());
		
		produtos = produtoDAO.procura(posicaoactual,nr,tfoqueprocura.getText(),(String)cbcategoria_1.getSelectedItem(),
				(String)cbsubcategoria.getSelectedItem(),(String)cblocalidade.getSelectedItem(),
				(String)cbestado.getSelectedItem(),Double.parseDouble(tfpreco1.getText()),Double.parseDouble(tfpreco2.getText()));
		
		btbaix.setEnabled(true);
		btcim.setEnabled(true);
		
		if(produtoDAO.procura(posicaoactual+nr,1,tfoqueprocura.getText(),(String)cbcategoria_1.getSelectedItem(),
				(String)cbsubcategoria.getSelectedItem(),(String)cblocalidade.getSelectedItem(),
				(String)cbestado.getSelectedItem(),Double.parseDouble(tfpreco1.getText()),Double.parseDouble(tfpreco2.getText())).size() == 0){
			btbaix.setEnabled(false);
		}
		
		painel.setVisible(true);
		painel_1.setVisible(true);
		painel_2.setVisible(true);
		painel_3.setVisible(true);
		painel_4.setVisible(true);
		painel_5.setVisible(true);
		painel_6.setVisible(true);
		painel_7.setVisible(true);
		
		}
		int arraysize = produtos.size();
		
		if(posicaoactual == 0){
			btcim.setEnabled(false);
		}
		
		if (arraysize == 0){
			btbaix.setEnabled(false);
			btcim.setEnabled(false);
			
			painel.setVisible(false);
			painel_1.setVisible(false);
			painel_2.setVisible(false);
			painel_3.setVisible(false);
			painel_4.setVisible(false);
			painel_5.setVisible(false);
			painel_6.setVisible(false);
			painel_7.setVisible(false);
			
		}else if(arraysize == 1){
			
			Produto produto = produtos.get(0);
			Blob blob = imagemDAO.getImagemProduto(produto.getId());
			
			imagem.setIcon(new ImageIcon(blob.getBytes(1, (int)blob.length())));
			lbpreco.setText(produto.getPreco() + " €");
			lbuser.setText(produto.getUtilizador().getNomeUtilizador());
			lbdata.setText(eyebuy.mostradata(produto.getDataColocacao()));
			lbnome.setText(produto.getNome());
			
			btbaix.setEnabled(false);
			
			painel_1.setVisible(false);
			painel_2.setVisible(false);
			painel_3.setVisible(false);
			painel_4.setVisible(false);
			painel_5.setVisible(false);
			painel_6.setVisible(false);
			painel_7.setVisible(false);
			
		}else if(arraysize == 2){
			
			Produto produto = produtos.get(0);
			Blob blob = imagemDAO.getImagemProduto(produto.getId());
			
			imagem.setIcon(new ImageIcon(blob.getBytes(1, (int)blob.length())));
			lbpreco.setText(produto.getPreco() + " €");
			lbuser.setText(produto.getUtilizador().getNomeUtilizador());
			lbdata.setText(eyebuy.mostradata(produto.getDataColocacao()));
			lbnome.setText(produto.getNome());
			
			Produto produto_1 = produtos.get(1);
			Blob blob_1 = imagemDAO.getImagemProduto(produto_1.getId());
			
			imagem_1.setIcon(new ImageIcon(blob_1.getBytes(1, (int)blob_1.length())));
			lbpreco_1.setText(produto_1.getPreco() + " €");
			lbuser_1.setText(produto_1.getUtilizador().getNomeUtilizador());
			lbdata_1.setText(eyebuy.mostradata(produto_1.getDataColocacao()));
			lbnome_1.setText(produto_1.getNome());
			
			btbaix.setEnabled(false);

			painel_2.setVisible(false);
			painel_3.setVisible(false);
			painel_4.setVisible(false);
			painel_5.setVisible(false);
			painel_6.setVisible(false);
			painel_7.setVisible(false);
		
		}else if(arraysize == 3){
		
			Produto produto = produtos.get(0);
			Blob blob = imagemDAO.getImagemProduto(produto.getId());
			
			imagem.setIcon(new ImageIcon(blob.getBytes(1, (int)blob.length())));
			lbpreco.setText(produto.getPreco() + " €");
			lbuser.setText(produto.getUtilizador().getNomeUtilizador());
			lbdata.setText(eyebuy.mostradata(produto.getDataColocacao()));
			lbnome.setText(produto.getNome());
			
			Produto produto_1 = produtos.get(1);
			Blob blob_1 = imagemDAO.getImagemProduto(produto_1.getId());
			
			imagem_1.setIcon(new ImageIcon(blob_1.getBytes(1, (int)blob_1.length())));
			lbpreco_1.setText(produto_1.getPreco() + " €");
			lbuser_1.setText(produto_1.getUtilizador().getNomeUtilizador());
			lbdata_1.setText(eyebuy.mostradata(produto_1.getDataColocacao()));
			lbnome_1.setText(produto_1.getNome());
			
			Produto produto_2 = produtos.get(2);
			Blob blob_2 = imagemDAO.getImagemProduto(produto_2.getId());
			
			imagem_2.setIcon(new ImageIcon(blob_2.getBytes(1, (int)blob_2.length())));
			lbpreco_2.setText(produto_2.getPreco() + " €");
			lbuser_2.setText(produto_2.getUtilizador().getNomeUtilizador());
			lbdata_2.setText(eyebuy.mostradata(produto_2.getDataColocacao()));
			lbnome_2.setText(produto_2.getNome());
			
			btbaix.setEnabled(false);

			painel_3.setVisible(false);
			painel_4.setVisible(false);
			painel_5.setVisible(false);
			painel_6.setVisible(false);
			painel_7.setVisible(false);
		
		}else if(arraysize == 4){
			
			Produto produto = produtos.get(0);
			Blob blob = imagemDAO.getImagemProduto(produto.getId());
			
			imagem.setIcon(new ImageIcon(blob.getBytes(1, (int)blob.length())));
			lbpreco.setText(produto.getPreco() + " €");
			lbuser.setText(produto.getUtilizador().getNomeUtilizador());
			lbdata.setText(eyebuy.mostradata(produto.getDataColocacao()));
			lbnome.setText(produto.getNome());
			
			Produto produto_1 = produtos.get(1);
			Blob blob_1 = imagemDAO.getImagemProduto(produto_1.getId());
			
			imagem_1.setIcon(new ImageIcon(blob_1.getBytes(1, (int)blob_1.length())));
			lbpreco_1.setText(produto_1.getPreco() + " €");
			lbuser_1.setText(produto_1.getUtilizador().getNomeUtilizador());
			lbdata_1.setText(eyebuy.mostradata(produto_1.getDataColocacao()));
			lbnome_1.setText(produto_1.getNome());
			
			Produto produto_2 = produtos.get(2);
			Blob blob_2 = imagemDAO.getImagemProduto(produto_2.getId());
			
			imagem_2.setIcon(new ImageIcon(blob_2.getBytes(1, (int)blob_2.length())));
			lbpreco_2.setText(produto_2.getPreco() + " €");
			lbuser_2.setText(produto_2.getUtilizador().getNomeUtilizador());
			lbdata_2.setText(eyebuy.mostradata(produto_2.getDataColocacao()));
			lbnome_2.setText(produto_2.getNome());
			
			Produto produto_3 = produtos.get(3);
			Blob blob_3 = imagemDAO.getImagemProduto(produto_3.getId());
			
			imagem_3.setIcon(new ImageIcon(blob_3.getBytes(1, (int)blob_3.length())));
			lbpreco_3.setText(produto_3.getPreco() + " €");
			lbuser_3.setText(produto_3.getUtilizador().getNomeUtilizador());
			lbdata_3.setText(eyebuy.mostradata(produto_3.getDataColocacao()));
			lbnome_3.setText(produto_3.getNome());
			
			btbaix.setEnabled(false);

			painel_4.setVisible(false);
			painel_5.setVisible(false);
			painel_6.setVisible(false);
			painel_7.setVisible(false);
			
		}else if(arraysize == 5){
			Produto produto = produtos.get(0);
			Blob blob = imagemDAO.getImagemProduto(produto.getId());
			
			imagem.setIcon(new ImageIcon(blob.getBytes(1, (int)blob.length())));
			lbpreco.setText(produto.getPreco() + " €");
			lbuser.setText(produto.getUtilizador().getNomeUtilizador());
			lbdata.setText(eyebuy.mostradata(produto.getDataColocacao()));
			lbnome.setText(produto.getNome());
			
			Produto produto_1 = produtos.get(1);
			Blob blob_1 = imagemDAO.getImagemProduto(produto_1.getId());
			
			imagem_1.setIcon(new ImageIcon(blob_1.getBytes(1, (int)blob_1.length())));
			lbpreco_1.setText(produto_1.getPreco() + " €");
			lbuser_1.setText(produto_1.getUtilizador().getNomeUtilizador());
			lbdata_1.setText(eyebuy.mostradata(produto_1.getDataColocacao()));
			lbnome_1.setText(produto_1.getNome());
			
			Produto produto_2 = produtos.get(2);
			Blob blob_2 = imagemDAO.getImagemProduto(produto_2.getId());
			
			imagem_2.setIcon(new ImageIcon(blob_2.getBytes(1, (int)blob_2.length())));
			lbpreco_2.setText(produto_2.getPreco() + " €");
			lbuser_2.setText(produto_2.getUtilizador().getNomeUtilizador());
			lbdata_2.setText(eyebuy.mostradata(produto_2.getDataColocacao()));
			lbnome_2.setText(produto_2.getNome());
			
			Produto produto_3 = produtos.get(3);
			Blob blob_3 = imagemDAO.getImagemProduto(produto_3.getId());
			
			imagem_3.setIcon(new ImageIcon(blob_3.getBytes(1, (int)blob_3.length())));
			lbpreco_3.setText(produto_3.getPreco() + " €");
			lbuser_3.setText(produto_3.getUtilizador().getNomeUtilizador());
			lbdata_3.setText(eyebuy.mostradata(produto_3.getDataColocacao()));
			lbnome_3.setText(produto_3.getNome());
			
			Produto produto_4 = produtos.get(4);
			Blob blob_4 = imagemDAO.getImagemProduto(produto_4.getId());
			
			imagem_4.setIcon(new ImageIcon(blob_4.getBytes(1, (int)blob_4.length())));
			lbpreco_4.setText(produto_4.getPreco() + " €");
			lbuser_4.setText(produto_4.getUtilizador().getNomeUtilizador());
			lbdata_4.setText(eyebuy.mostradata(produto_4.getDataColocacao()));
			lbnome_4.setText(produto_4.getNome());
			
			btbaix.setEnabled(false);

			painel_5.setVisible(false);
			painel_6.setVisible(false);
			painel_7.setVisible(false);
		}else if(arraysize == 6){
			Produto produto = produtos.get(0);
			Blob blob = imagemDAO.getImagemProduto(produto.getId());
			
			imagem.setIcon(new ImageIcon(blob.getBytes(1, (int)blob.length())));
			lbpreco.setText(produto.getPreco() + " €");
			lbuser.setText(produto.getUtilizador().getNomeUtilizador());
			lbdata.setText(eyebuy.mostradata(produto.getDataColocacao()));
			lbnome.setText(produto.getNome());
			
			Produto produto_1 = produtos.get(1);
			Blob blob_1 = imagemDAO.getImagemProduto(produto_1.getId());
			
			imagem_1.setIcon(new ImageIcon(blob_1.getBytes(1, (int)blob_1.length())));
			lbpreco_1.setText(produto_1.getPreco() + " €");
			lbuser_1.setText(produto_1.getUtilizador().getNomeUtilizador());
			lbdata_1.setText(eyebuy.mostradata(produto_1.getDataColocacao()));
			lbnome_1.setText(produto_1.getNome());
			
			Produto produto_2 = produtos.get(2);
			Blob blob_2 = imagemDAO.getImagemProduto(produto_2.getId());
			
			imagem_2.setIcon(new ImageIcon(blob_2.getBytes(1, (int)blob_2.length())));
			lbpreco_2.setText(produto_2.getPreco() + " €");
			lbuser_2.setText(produto_2.getUtilizador().getNomeUtilizador());
			lbdata_2.setText(eyebuy.mostradata(produto_2.getDataColocacao()));
			lbnome_2.setText(produto_2.getNome());
			
			Produto produto_3 = produtos.get(3);
			Blob blob_3 = imagemDAO.getImagemProduto(produto_3.getId());
			
			imagem_3.setIcon(new ImageIcon(blob_3.getBytes(1, (int)blob_3.length())));
			lbpreco_3.setText(produto_3.getPreco() + " €");
			lbuser_3.setText(produto_3.getUtilizador().getNomeUtilizador());
			lbdata_3.setText(eyebuy.mostradata(produto_3.getDataColocacao()));
			lbnome_3.setText(produto_3.getNome());
			
			Produto produto_4 = produtos.get(4);
			Blob blob_4 = imagemDAO.getImagemProduto(produto_4.getId());
			
			imagem_4.setIcon(new ImageIcon(blob_4.getBytes(1, (int)blob_4.length())));
			lbpreco_4.setText(produto_4.getPreco() + " €");
			lbuser_4.setText(produto_4.getUtilizador().getNomeUtilizador());
			lbdata_4.setText(eyebuy.mostradata(produto_4.getDataColocacao()));
			lbnome_4.setText(produto_4.getNome());
			
			Produto produto_5 = produtos.get(5);
			Blob blob_5 = imagemDAO.getImagemProduto(produto_5.getId());
			
			imagem_5.setIcon(new ImageIcon(blob_5.getBytes(1, (int)blob_5.length())));
			lbpreco_5.setText(produto_5.getPreco() + " €");
			lbuser_5.setText(produto_5.getUtilizador().getNomeUtilizador());
			lbdata_5.setText(eyebuy.mostradata(produto_5.getDataColocacao()));
			lbnome_5.setText(produto_5.getNome());
			
			btbaix.setEnabled(false);

			painel_6.setVisible(false);
			painel_7.setVisible(false);
		}else if(arraysize == 7){
			Produto produto = produtos.get(0);
			Blob blob = imagemDAO.getImagemProduto(produto.getId());
			
			imagem.setIcon(new ImageIcon(blob.getBytes(1, (int)blob.length())));
			lbpreco.setText(produto.getPreco() + " €");
			lbuser.setText(produto.getUtilizador().getNomeUtilizador());
			lbdata.setText(eyebuy.mostradata(produto.getDataColocacao()));
			lbnome.setText(produto.getNome());
			
			Produto produto_1 = produtos.get(1);
			Blob blob_1 = imagemDAO.getImagemProduto(produto_1.getId());
			
			imagem_1.setIcon(new ImageIcon(blob_1.getBytes(1, (int)blob_1.length())));
			lbpreco_1.setText(produto_1.getPreco() + " €");
			lbuser_1.setText(produto_1.getUtilizador().getNomeUtilizador());
			lbdata_1.setText(eyebuy.mostradata(produto_1.getDataColocacao()));
			lbnome_1.setText(produto_1.getNome());
			
			Produto produto_2 = produtos.get(2);
			Blob blob_2 = imagemDAO.getImagemProduto(produto_2.getId());
			
			imagem_2.setIcon(new ImageIcon(blob_2.getBytes(1, (int)blob_2.length())));
			lbpreco_2.setText(produto_2.getPreco() + " €");
			lbuser_2.setText(produto_2.getUtilizador().getNomeUtilizador());
			lbdata_2.setText(eyebuy.mostradata(produto_2.getDataColocacao()));
			lbnome_2.setText(produto_2.getNome());
			
			Produto produto_3 = produtos.get(3);
			Blob blob_3 = imagemDAO.getImagemProduto(produto_3.getId());
			
			imagem_3.setIcon(new ImageIcon(blob_3.getBytes(1, (int)blob_3.length())));
			lbpreco_3.setText(produto_3.getPreco() + " €");
			lbuser_3.setText(produto_3.getUtilizador().getNomeUtilizador());
			lbdata_3.setText(eyebuy.mostradata(produto_3.getDataColocacao()));
			lbnome_3.setText(produto_3.getNome());
			
			Produto produto_4 = produtos.get(4);
			Blob blob_4 = imagemDAO.getImagemProduto(produto_4.getId());
			
			imagem_4.setIcon(new ImageIcon(blob_4.getBytes(1, (int)blob_4.length())));
			lbpreco_4.setText(produto_4.getPreco() + " €");
			lbuser_4.setText(produto_4.getUtilizador().getNomeUtilizador());
			lbdata_4.setText(eyebuy.mostradata(produto_4.getDataColocacao()));
			lbnome_4.setText(produto_4.getNome());
			
			Produto produto_5 = produtos.get(5);
			Blob blob_5 = imagemDAO.getImagemProduto(produto_5.getId());
			
			imagem_5.setIcon(new ImageIcon(blob_5.getBytes(1, (int)blob_5.length())));
			lbpreco_5.setText(produto_5.getPreco() + " €");
			lbuser_5.setText(produto_5.getUtilizador().getNomeUtilizador());
			lbdata_5.setText(eyebuy.mostradata(produto_5.getDataColocacao()));
			lbnome_5.setText(produto_5.getNome());
			
			Produto produto_6 = produtos.get(6);
			Blob blob_6 = imagemDAO.getImagemProduto(produto_6.getId());
			
			imagem_6.setIcon(new ImageIcon(blob_6.getBytes(1, (int)blob_6.length())));
			lbpreco_6.setText(produto_6.getPreco() + " €");
			lbuser_6.setText(produto_6.getUtilizador().getNomeUtilizador());
			lbdata_6.setText(eyebuy.mostradata(produto_6.getDataColocacao()));
			lbnome_6.setText(produto_6.getNome());
			
			btbaix.setEnabled(false);

			painel_7.setVisible(false);
		}else{
			Produto produto = produtos.get(0);
			Blob blob = imagemDAO.getImagemProduto(produto.getId());
			
			imagem.setIcon(new ImageIcon(blob.getBytes(1, (int)blob.length())));
			lbpreco.setText(produto.getPreco() + " €");
			lbuser.setText(produto.getUtilizador().getNomeUtilizador());
			lbdata.setText(eyebuy.mostradata(produto.getDataColocacao()));
			lbnome.setText(produto.getNome());
			
			Produto produto_1 = produtos.get(1);
			Blob blob_1 = imagemDAO.getImagemProduto(produto_1.getId());
			
			imagem_1.setIcon(new ImageIcon(blob_1.getBytes(1, (int)blob_1.length())));
			lbpreco_1.setText(produto_1.getPreco() + " €");
			lbuser_1.setText(produto_1.getUtilizador().getNomeUtilizador());
			lbdata_1.setText(eyebuy.mostradata(produto_1.getDataColocacao()));
			lbnome_1.setText(produto_1.getNome());
			
			Produto produto_2 = produtos.get(2);
			Blob blob_2 = imagemDAO.getImagemProduto(produto_2.getId());
			
			imagem_2.setIcon(new ImageIcon(blob_2.getBytes(1, (int)blob_2.length())));
			lbpreco_2.setText(produto_2.getPreco() + " €");
			lbuser_2.setText(produto_2.getUtilizador().getNomeUtilizador());
			lbdata_2.setText(eyebuy.mostradata(produto_2.getDataColocacao()));
			lbnome_2.setText(produto_2.getNome());
			
			Produto produto_3 = produtos.get(3);
			Blob blob_3 = imagemDAO.getImagemProduto(produto_3.getId());
			
			imagem_3.setIcon(new ImageIcon(blob_3.getBytes(1, (int)blob_3.length())));
			lbpreco_3.setText(produto_3.getPreco() + " €");
			lbuser_3.setText(produto_3.getUtilizador().getNomeUtilizador());
			lbdata_3.setText(eyebuy.mostradata(produto_3.getDataColocacao()));
			lbnome_3.setText(produto_3.getNome());
			
			Produto produto_4 = produtos.get(4);
			Blob blob_4 = imagemDAO.getImagemProduto(produto_4.getId());
			
			imagem_4.setIcon(new ImageIcon(blob_4.getBytes(1, (int)blob_4.length())));
			lbpreco_4.setText(produto_4.getPreco() + " €");
			lbuser_4.setText(produto_4.getUtilizador().getNomeUtilizador());
			lbdata_4.setText(eyebuy.mostradata(produto_4.getDataColocacao()));
			lbnome_4.setText(produto_4.getNome());
			
			Produto produto_5 = produtos.get(5);
			Blob blob_5 = imagemDAO.getImagemProduto(produto_5.getId());
			
			imagem_5.setIcon(new ImageIcon(blob_5.getBytes(1, (int)blob_5.length())));
			lbpreco_5.setText(produto_5.getPreco() + " €");
			lbuser_5.setText(produto_5.getUtilizador().getNomeUtilizador());
			lbdata_5.setText(eyebuy.mostradata(produto_5.getDataColocacao()));
			lbnome_5.setText(produto_5.getNome());
			
			Produto produto_6 = produtos.get(6);
			Blob blob_6 = imagemDAO.getImagemProduto(produto_6.getId());
			
			imagem_6.setIcon(new ImageIcon(blob_6.getBytes(1, (int)blob_6.length())));
			lbpreco_6.setText(produto_6.getPreco() + " €");
			lbuser_6.setText(produto_6.getUtilizador().getNomeUtilizador());
			lbdata_6.setText(eyebuy.mostradata(produto_6.getDataColocacao()));
			lbnome_6.setText(produto_6.getNome());
			
			Produto produto_7 = produtos.get(7);
			Blob blob_7 = imagemDAO.getImagemProduto(produto_7.getId());
			
			imagem_7.setIcon(new ImageIcon(blob_7.getBytes(1, (int)blob_7.length())));
			lbpreco_7.setText(produto_7.getPreco() + " €");
			lbuser_7.setText(produto_7.getUtilizador().getNomeUtilizador());
			lbdata_7.setText(eyebuy.mostradata(produto_7.getDataColocacao()));
			lbnome_7.setText(produto_7.getNome());
		}
		
	
	
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
