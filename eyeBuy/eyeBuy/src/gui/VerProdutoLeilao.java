package gui;

import javax.swing.AbstractListModel;
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
import java.awt.HeadlessException;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.EyeBuy;
import model.Imagem;
import model.Produto;
import model.Utilizador;
import model.transacoes.Proposta;

import javax.swing.JScrollPane;

import dao.CategoriaDAO;
import dao.ImagemDAO;
import dao.LeilaoDAO;
import dao.LocalidadeDAO;
import dao.ProdutoDAO;
import dao.PropostaDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class VerProdutoLeilao extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField tfbid;
	
	private JLabel lvendedor = new JLabel();
	
	private JLabel lfoto = new JLabel();
	
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	
	private JButton btcimr = new JButton();
	private JButton btbaixr = new JButton();
	private JButton btdirr = new JButton();
	private JButton btcimup = new JButton();
	private JButton btbaixup = new JButton();
	private JButton btdirf = new JButton();
	private JButton btesqf = new JButton();
	private JButton btnadddesejados = new JButton();
	
	private JList jlr = new JList();
	private JList jlup = new JList();
	
	private int indexr =0;
	private int indexup =0;
	
	private ImagemDAO imagemDAO = new ImagemDAO();
	private PropostaDAO propostaDAO = new PropostaDAO();
	private Utilizador utilizador = new Utilizador();
	private Produto produto = new Produto();
	private EyeBuy eyeBuy = new EyeBuy();
	private LeilaoDAO leilaoDAO = new LeilaoDAO();
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private LocalidadeDAO localidadeDAO = new LocalidadeDAO();
	
	
	private JComboBox cbcategoria = new JComboBox();
	private JComboBox cblocalidade = new JComboBox();
	
	private int indeximagens = 0;
	private ArrayList<Imagem> imagens = new ArrayList<Imagem>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerProdutoLeilao frame = new VerProdutoLeilao(new Utilizador(),new Produto());
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
	 * @param produto 
	 * @throws SQLException 
	 */
	public VerProdutoLeilao(Utilizador user, Produto p) throws SQLException {
		produtoDAO.addObserver(this);
		localidadeDAO.addObserver(this);
		categoriaDAO.addObserver(this);
		utilizador.addObserver(this);
		imagemDAO.addObserver(this);
		eyeBuy.addObserver(this);
		propostaDAO.addObserver(this);
		
		produto = p;
		
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
		mnComunidade.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/comm2.png")));
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
		mnCarrinho.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/carr.png")));
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
				VerProdutoLeilao.this.setVisible(false);
				VerProdutoLeilao.this.dispose();
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
		mnUtilizador.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/user2.png")));
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
			VerProdutoLeilao.this.setVisible(false);
			VerProdutoLeilao.this.dispose();
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
				VerProdutoLeilao.this.setVisible(false);
				VerProdutoLeilao.this.dispose();
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
				VerProdutoLeilao.this.setVisible(false);
				VerProdutoLeilao.this.dispose();
			}
		});
		mnUtilizador.add(mntmHistrico);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende sair?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					VerProdutoLeilao.this.setVisible(false);
					VerProdutoLeilao.this.dispose();
				}
			}
		});
		mnUtilizador.add(mntmSair);
		
		JButton btnNewButton_1 = new JButton("Página Inicial");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Homev3(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				VerProdutoLeilao.this.setVisible(false);
				VerProdutoLeilao.this.dispose();
			}
		});
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/home2.png")));
		menuBar.add(btnNewButton_1);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Produtos Relacionados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u00DAltimas propostas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 733, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 279, Short.MAX_VALUE))
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE))
					.addGap(6))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(10))
		);
		
		JButton btactup = new JButton("");
		btactup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateultimaspropostas();
			}
		});
		btactup.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/actualizar.png")));
		btactup.setContentAreaFilled(false);
		btactup.setBorderPainted(false);
		
		JScrollPane scrollPane = new JScrollPane();
		
		btbaixup = new JButton("");
		btbaixup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcimup.setEnabled(true);
				indexup += 1;
				
				jlup.setSelectedIndex(indexup);
				jlup.ensureIndexIsVisible(indexup);
				
				try {
					if(propostaDAO.listaPropostas(produto.getId()).length == indexr+1){
						btbaixup.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btbaixup.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/setabaixo.png")));
		btbaixup.setContentAreaFilled(false);
		btbaixup.setBorderPainted(false);
		
		btcimup = new JButton("");
		btcimup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaixup.setEnabled(true);
				indexup-= 1;
				
				jlup.setSelectedIndex(indexup);
				jlup.ensureIndexIsVisible(indexup);
				
				if(indexup == 0){
					btcimup.setEnabled(false);
				}
			}
		});
		btcimup.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/setacima.png")));
		btcimup.setContentAreaFilled(false);
		btcimup.setBorderPainted(false);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(btcimup, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btbaixup, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btactup, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(14))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btactup, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btcimup, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btbaixup, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		jlup = new JList();
		jlup.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btcimup.setEnabled(true);
				btbaixup.setEnabled(true);
				
				indexup = jlup.getSelectedIndex();
				jlup.ensureIndexIsVisible(indexup);
				
				if(indexup == 0){
					btcimup.setEnabled(false);
				} else
					try {
						if(propostaDAO.listaPropostas(produto.getId()).length == indexr+1){
							btbaixup.setEnabled(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		scrollPane.setViewportView(jlup);
		jlup.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_2.setLayout(gl_panel_2);
		
		updateultimaspropostas();
		
		JButton btactr = new JButton("");
		btactr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updaterelacionados();
			}
		});
		btactr.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/actualizar.png")));
		btactr.setContentAreaFilled(false);
		btactr.setBorderPainted(false);
		
		btdirr = new JButton("");
		btdirr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto p = new Produto();
				try {
					p = produtoDAO.getProdutoRelacionadoListado((String)jlr.getSelectedValue());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				if(p.getMetodoVenda().equals("Leilão")){
					try {
						new VerProdutoLeilao(utilizador,p);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					try {
						new VerProduto(utilizador,p);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				VerProdutoLeilao.this.setVisible(false);
				VerProdutoLeilao.this.dispose();
			}
		});
		btdirr.setToolTipText("Ir para produto selecionado");
		btdirr.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/irpara.png")));
		btdirr.setContentAreaFilled(false);
		btdirr.setBorderPainted(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		btbaixr = new JButton("");
		btbaixr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcimr.setEnabled(true);
				indexr += 1;
				
				jlr.setSelectedIndex(indexr);
				jlr.ensureIndexIsVisible(indexr);
				
				try {
					if(produtoDAO.listaProdutosRelacionados(utilizador.getNomeUtilizador(),produto.getId()).length == indexr+1){
						btbaixr.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btbaixr.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/setabaixo.png")));
		btbaixr.setContentAreaFilled(false);
		btbaixr.setBorderPainted(false);
		
		btcimr = new JButton("");
		btcimr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaixr.setEnabled(true);
				indexr-= 1;
				
				jlr.setSelectedIndex(indexr);
				jlr.ensureIndexIsVisible(indexr);
				
				if(indexr == 0){
					btcimr.setEnabled(false);
				}
			}
		});
		btcimr.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/setacima.png")));
		btcimr.setContentAreaFilled(false);
		btcimr.setBorderPainted(false);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btcimr, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btbaixr, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btdirr, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btactr, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btactr, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btdirr, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
						.addComponent(btbaixr, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btcimr, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		jlr = new JList();
		jlr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Produto p = new Produto();
					try {
						p = produtoDAO.getProdutoRelacionadoListado((String)jlr.getSelectedValue());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
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
							new VerProduto(utilizador,p);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					VerProdutoLeilao.this.setVisible(false);
					VerProdutoLeilao.this.dispose();
				}
			}
		});
		jlr.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btcimr.setEnabled(true);
				btbaixr.setEnabled(true);
				
				indexr = jlr.getSelectedIndex();
				jlr.ensureIndexIsVisible(indexr);
				
				btdirr.setEnabled(true);
				if(jlr.getSelectedValue() == null){
					btdirr.setEnabled(false);
				}
				if(indexr == 0){
					btcimr.setEnabled(false);
				} else
					try {
						if(produtoDAO.listaProdutosRelacionados(utilizador.getNomeUtilizador(),produto.getId()).length == indexr+1){
							btbaixr.setEnabled(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		scrollPane_1.setViewportView(jlr);
		jlr.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setLayout(gl_panel_1);
		
		updaterelacionados();
		
		lfoto = new JLabel("");
		lfoto.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/esplanad.JPG")));
		
		updateimagens();
		
		JLabel lnome = new JLabel(produto.getNome());
		lnome.setFont(new Font("Dialog", Font.BOLD, 16));
		
		btdirf = new JButton("");
		btdirf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indeximagens += 1;
				try {
					updateimagens();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btdirf.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/setafrente.png")));
		btdirf.setContentAreaFilled(false);
		btdirf.setBorderPainted(false);
		
		btesqf = new JButton("");
		btesqf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indeximagens -=1;
				try {
					updateimagens();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btesqf.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/setaptras.png")));
		btesqf.setBorderPainted(false);
		btesqf.setContentAreaFilled(false);
		
		JLabel lblCategoria_1 = new JLabel("Categoria:");
		lblCategoria_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lcategoria = new JLabel(produto.getCategoria().getNome());
		lcategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lcategoria.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblSubcategoria = new JLabel("Subcategoria:");
		lblSubcategoria.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lsubcategoria = new JLabel(produto.getSubCategoria().getNome());
		lsubcategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lsubcategoria.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblDataColocao = new JLabel("Data colocação à venda:");
		lblDataColocao.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel ldata = new JLabel(eyeBuy.mostradata(produto.getDataColocacao()));
		ldata.setHorizontalAlignment(SwingConstants.RIGHT);
		ldata.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lpreco_1 = new JLabel("Preço Actual:");
		lpreco_1.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lpreco = new JLabel(leilaoDAO.getPrecoActual(produto.getId()) + " €");
		lpreco.setHorizontalAlignment(SwingConstants.RIGHT);
		lpreco.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JButton btnContactarVendedor = new JButton("");
		btnContactarVendedor.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnContactarVendedor.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/tele.png")));
		btnContactarVendedor.setContentAreaFilled(false);
		btnContactarVendedor.setBorderPainted(false);
		
		btnadddesejados = new JButton("");
		btnadddesejados.setToolTipText("Adicionar produto aos produtos desejados");
		btnadddesejados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					produtoDAO.addProdutoDesejado(utilizador.getNomeUtilizador(),produto.getId());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnadddesejados.setEnabled(false);
			}
		});
		btnadddesejados.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/addproddesej.png")));
		btnadddesejados.setContentAreaFilled(false);
		btnadddesejados.setBorderPainted(false);
		
		if(produtoDAO.estaNosDesejados(utilizador.getNomeUtilizador(),produto.getId()))
			btnadddesejados.setEnabled(false);
		
		tfbid = new JTextField(((int)(leilaoDAO.getPrecoActual(produto.getId())*1.1)) + "");
		tfbid.setHorizontalAlignment(SwingConstants.RIGHT);
		tfbid.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Data final: ");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lhorafinal = new JLabel(eyeBuy.mostradataehora(leilaoDAO.getDataFim(produto.getId())));
		lhorafinal.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnmandarmensagem = new JButton("");
		btnmandarmensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NovaMensagem(utilizador, null, produto.getUtilizador());
			}
		});
		btnmandarmensagem.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/mail.png")));
		btnmandarmensagem.setDefaultCapable(false);
		btnmandarmensagem.setContentAreaFilled(false);
		btnmandarmensagem.setBorder(null);
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		lblVendedor.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		lvendedor = new JLabel(produto.getUtilizador().getNomeUtilizador());
		lvendedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new VerPerfilUtilizadorv2(utilizador, produto.getUtilizador());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				VerProdutoLeilao.this.setVisible(false);
				VerProdutoLeilao.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lvendedor.setFont(new Font("Dialog", Font.BOLD, 15));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lvendedor.setFont(new Font("Dialog", Font.PLAIN, 15));
			}
		});
		lvendedor.setHorizontalAlignment(SwingConstants.RIGHT);
		lvendedor.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JButton btnfazerbid = new JButton("");
		btnfazerbid.setToolTipText("Fazer licitação");
		btnfazerbid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!eyeBuy.isDouble(tfbid.getText())){
					JOptionPane.showMessageDialog(null, "Introduza um valor válido", "Erro", JOptionPane.WARNING_MESSAGE );
				} else
					try {
						if(Double.parseDouble(tfbid.getText()) <= leilaoDAO.getPrecoActual(produto.getId())){
							JOptionPane.showMessageDialog(null, "Já existem propostas superiores", "Erro", JOptionPane.WARNING_MESSAGE );
						}else{
							
							Proposta p = new Proposta(0, Double.parseDouble(tfbid.getText()), utilizador, produto);
							
							try {
								if(propostaDAO.addProposta(p)){
									JOptionPane.showMessageDialog(null, "Proposta efectuada com sucesso", "Erro", JOptionPane.INFORMATION_MESSAGE );
									try {
										new VerProdutoLeilao(utilizador,produto);
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									VerProdutoLeilao.this.setVisible(false);
									VerProdutoLeilao.this.dispose();
								}else{
									JOptionPane.showMessageDialog(null, "Não foi possivél efectuar proposta", "Erro", JOptionPane.WARNING_MESSAGE );
									try {
										new VerProdutoLeilao(utilizador,produto);
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									VerProdutoLeilao.this.setVisible(false);
									VerProdutoLeilao.this.dispose();
								}
							} catch (HeadlessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnfazerbid.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/lida.png")));
		btnfazerbid.setContentAreaFilled(false);
		btnfazerbid.setBorderPainted(false);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblUsado = new JLabel(produto.getEstado());
		lblUsado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsado.setFont(new Font("Dialog", Font.PLAIN, 12));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(btesqf, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btdirf, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addGap(160)
							.addComponent(btnmandarmensagem, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnContactarVendedor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(30))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lfoto, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lblCategoria_1)
									.addGap(18)
									.addComponent(lcategoria, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lhorafinal, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lblEstado)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblUsado, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lpreco_1)
									.addGap(18)
									.addComponent(lpreco, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lblVendedor)
									.addGap(9)
									.addComponent(lvendedor, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lblDataColocao)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(ldata, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lblSubcategoria)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lsubcategoria, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
									.addComponent(tfbid, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnfazerbid)
									.addGap(18)
									.addComponent(btnadddesejados, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
							.addGap(24))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lnome, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lnome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCategoria_1)
								.addComponent(lcategoria, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSubcategoria)
								.addComponent(lsubcategoria, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDataColocao)
								.addComponent(ldata, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVendedor)
								.addComponent(lvendedor, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEstado)
								.addComponent(lblUsado, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(lhorafinal, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lpreco)
								.addComponent(lpreco_1))
							.addGap(43))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lfoto, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnmandarmensagem, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
								.addComponent(btnContactarVendedor, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btesqf, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btdirf, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(41))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnadddesejados, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(tfbid, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addGap(14)))
							.addContainerGap())
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(btnfazerbid, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
							.addContainerGap())))
		);
		panel_5.setLayout(gl_panel_5);
		
		JButton logo = new JButton("");
		logo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Homev3(utilizador);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				VerProdutoLeilao.this.setVisible(false);
				VerProdutoLeilao.this.dispose();
			}
		});
		logo.setBorderPainted(false);
		logo.setContentAreaFilled(false);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(VerProdutoLeilao.class.getResource("/Imagens/eyelogo.png")));
		
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
				VerProdutoLeilao.this.setVisible(false);
				VerProdutoLeilao.this.dispose();
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
				VerProdutoLeilao.this.setVisible(false);
				VerProdutoLeilao.this.dispose();
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
		
		updateimagens();
	}
	
	public void updaterelacionados(){
		btbaixr.setEnabled(true);
		indexr = 0;
		
		try {
			jlr.setModel(new AbstractListModel() {
				private static final long serialVersionUID = 1L;
				String[] values = produtoDAO.listaProdutosRelacionados(utilizador.getNomeUtilizador(),produto.getId());
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
		
		jlr.setSelectedIndex(indexr);
		jlr.ensureIndexIsVisible(indexr);
		
		if(jlup.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    jlup.setCellRenderer(renderer);
		}
		
		btcimr.setEnabled(false);
		btdirr.setEnabled(true);
		btbaixr.setEnabled(true);
		try {
			if (produtoDAO.listaProdutosRelacionados(utilizador.getNomeUtilizador(),produto.getId()).length < 2){
				btbaixr.setEnabled(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (jlr.getSelectedValue() == null){
			btdirr.setEnabled(false);
		}
	}
	
	public void updateultimaspropostas(){
		btbaixup.setEnabled(true);
		indexup = 0;
		
		try {
			jlup.setModel(new AbstractListModel() {
				private static final long serialVersionUID = 1L;
				String[] values = propostaDAO.listaPropostas(produto.getId());
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
		
		jlup.setSelectedIndex(indexup);
		jlup.ensureIndexIsVisible(indexup);
		
		if(jlup.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    jlup.setCellRenderer(renderer);
		}
		
		btcimup.setEnabled(false);
		btbaixup.setEnabled(true);
		try {
			if (propostaDAO.listaPropostas(produto.getId()).length < 2){
				btbaixup.setEnabled(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateimagens() throws SQLException{
		imagens = new ArrayList<Imagem>(imagemDAO.getImagens(produto.getId()).values());
		
		btdirf.setEnabled(true);
		btesqf.setEnabled(true);
		
		Blob blobmv_2 = imagens.get(indeximagens).getImagem();
		if (blobmv_2 != null){ 
			lfoto.setIcon(new ImageIcon(blobmv_2.getBytes(1, (int)blobmv_2.length())));
		}
		
		if(imagens.size() < 2){
			btdirf.setEnabled(false);
			btesqf.setEnabled(false);
		}else if(indeximagens == 0){
			btesqf.setEnabled(false);	
		}else if(indeximagens == imagemDAO.getImagens(produto.getId()).size()-1){
			btdirf.setEnabled(false);
		}
		
	}
	
	public void update() throws SQLException{
		updateimagens();
		updaterelacionados();
		updateultimaspropostas();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
