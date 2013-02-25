package gui;


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
import dao.LocalidadeDAO;
import dao.ProdutoDAO;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class HistoricoGUI extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	
	private Utilizador utilizador = new Utilizador();
	private HistoricoDAO historicoDAO = new HistoricoDAO();
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private LocalidadeDAO localidadeDAO = new LocalidadeDAO();
	
	
	private JList jlgeral = new JList();
	private JList jlhistcompras = new JList();
	private JList jlhistvendas = new JList();
	private JButton btcimg = new JButton("");
	private JButton btbaixg = new JButton("");
	private JButton btactg = new JButton("");
	private JButton btacthc = new JButton("");
	private JButton btbaixhc = new JButton("");
	private JButton btcimhc = new JButton("");
	private JButton btacthv = new JButton("");
	private JButton btbaixhv = new JButton("");
	private JButton btcimhv = new JButton("");
	
	private int indexg = 0;
	private int indexhv = 0;
	private int indexhc = 0;
	
	private JLabel nrtotcev = new JLabel();
	private JLabel ltotalgasto = new JLabel();
	private JLabel ltotalganho = new JLabel();
	private JLabel lntotalcompras = new JLabel();
	private JLabel lntotalvendas = new JLabel();

	
	private JComboBox cblocalidade = new JComboBox();
	private JComboBox cbcategoria = new JComboBox();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoricoGUI frame = new HistoricoGUI(new Utilizador());
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
	public HistoricoGUI(Utilizador user) throws SQLException {
		produtoDAO.addObserver(this);
		historicoDAO.addObserver(this);
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
		mnNewMenu.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/comunidade.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSobreOEyebuy = new JMenuItem("Sobre o eyeBuy");
		mntmSobreOEyebuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		mnCarrinho.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/carr.png")));
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
				HistoricoGUI.this.setVisible(false);
				HistoricoGUI.this.dispose();
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
		mnUtilizador.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/user2.png")));
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
				HistoricoGUI.this.setVisible(false);
				HistoricoGUI.this.dispose();
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
				HistoricoGUI.this.setVisible(false);
				HistoricoGUI.this.dispose();
			}
		});
		mnUtilizador.add(mntmPerfilDeVendedor);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende sair?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					HistoricoGUI.this.setVisible(false);
					HistoricoGUI.this.dispose();
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
				HistoricoGUI.this.setVisible(false);
				HistoricoGUI.this.dispose();
			}
		});
		btnPginaInicial.setContentAreaFilled(false);
		btnPginaInicial.setBorderPainted(false);
		btnPginaInicial.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/home2.png")));
		menuBar.add(btnPginaInicial);
		
		JButton btPic = new JButton("");
		btPic.setContentAreaFilled(false);
		btPic.setBorderPainted(false);
		btPic.setOpaque(false);
		btPic.setIcon(new ImageIcon("/home/k41/projects/DSS12_eBay/Eclipse/eyeBuy/user.png"));
		menuBar.add(btPic);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setOpaque(false);
		contentPane.setFocusCycleRoot(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Geral", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Hist\u00F3rico de Compras", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JLabel lblNmeroTotalDe = new JLabel("Número total de compras:");
		lblNmeroTotalDe.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		lntotalcompras = new JLabel(historicoDAO.getNumeroCompras(utilizador.getNomeUtilizador()) + "");
		
		JLabel lblTotalGasto = new JLabel("Total gasto:");
		lblTotalGasto.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		ltotalgasto = new JLabel(historicoDAO.getTotalGasto(utilizador.getNomeUtilizador()) + "");
		
		btacthc = new JButton("");
		btacthc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatehc();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btacthc.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/actualizar.png")));
		btacthc.setContentAreaFilled(false);
		btacthc.setBorderPainted(false);
		
		btbaixhc = new JButton("");
		btbaixhc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcimhc.setEnabled(true);
				indexhc += 1;
				
				jlhistcompras.setSelectedIndex(indexhc);
				jlhistcompras.ensureIndexIsVisible(indexhc);
				
				try {
					if(historicoDAO.listaHistoricoCompras(utilizador.getNomeUtilizador()).length == indexhc+1){
						btbaixhc.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btbaixhc.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/setabaixo.png")));
		btbaixhc.setContentAreaFilled(false);
		btbaixhc.setBorderPainted(false);
		
		btcimhc = new JButton("");
		btcimhc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaixhc.setEnabled(true);
				indexhc -= 1;
				
				jlhistcompras.setSelectedIndex(indexhc);
				jlhistcompras.ensureIndexIsVisible(indexhc);
				
				if(indexhc == 0){
					btcimhc.setEnabled(false);
				}
			}
		});
		btcimhc.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/setacima.png")));
		btcimhc.setContentAreaFilled(false);
		btcimhc.setBorderPainted(false);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(lblNmeroTotalDe)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lntotalcompras, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(lblTotalGasto)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(ltotalgasto, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
							.addComponent(btcimhc, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btbaixhc, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btacthc, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNmeroTotalDe)
								.addComponent(lntotalcompras))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTotalGasto)
								.addComponent(ltotalgasto)))
						.addComponent(btacthc, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btbaixhc, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btcimhc, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		jlhistcompras = new JList();
		jlhistcompras.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btcimhc.setEnabled(true);
				btbaixhc.setEnabled(true);
				
				indexhc = jlhistcompras.getSelectedIndex();
				jlhistcompras.ensureIndexIsVisible(indexhc);
				
				if(indexhc == 0){
					btcimhc.setEnabled(false);
				} else
					try {
						if(historicoDAO.listaHistoricoCompras(utilizador.getNomeUtilizador()).length == indexhc+1){
							btbaixhc.setEnabled(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		updatehc();
		
		scrollPane_2.setViewportView(jlhistcompras);
		jlhistcompras.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Hist\u00F3rico de Vendas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JLabel lblNmeroTotalDe_1 = new JLabel("Número total de vendas:");
		lblNmeroTotalDe_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		lntotalvendas = new JLabel(historicoDAO.getNumeroVendas(utilizador.getNomeUtilizador()) + "");
		
		JLabel lblTotalGanho = new JLabel("Total ganho:");
		lblTotalGanho.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		ltotalganho = new JLabel(historicoDAO.getTotalGanho(utilizador.getNomeUtilizador()) + "");
		
		btacthv = new JButton("");
		btacthv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatehv();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btacthv.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/actualizar.png")));
		btacthv.setContentAreaFilled(false);
		btacthv.setBorderPainted(false);
		
		btbaixhv = new JButton("");
		btbaixhv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcimhv.setEnabled(true);
				indexhv += 1;
				
				jlhistvendas.setSelectedIndex(indexhv);
				jlhistvendas.ensureIndexIsVisible(indexhv);
				
				try {
					if(historicoDAO.listaUltimasVendas(utilizador.getNomeUtilizador()).length == indexhv+1){
						btbaixhv.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btbaixhv.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/setabaixo.png")));
		btbaixhv.setContentAreaFilled(false);
		btbaixhv.setBorderPainted(false);
		
		btcimhv = new JButton("");
		btcimhv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaixhv.setEnabled(true);
				indexhv -= 1;
				
				jlhistvendas.setSelectedIndex(indexhv);
				jlhistvendas.ensureIndexIsVisible(indexhv);
				
				if(indexhv == 0){
					btcimhv.setEnabled(false);
				}
			}
		});
		btcimhv.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/setacima.png")));
		btcimhv.setContentAreaFilled(false);
		btcimhv.setBorderPainted(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lblNmeroTotalDe_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lntotalvendas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel_5.createSequentialGroup()
									.addComponent(lblTotalGanho)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(ltotalganho, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
							.addComponent(btcimhv, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btbaixhv, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btacthv, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNmeroTotalDe_1)
								.addComponent(lntotalvendas))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTotalGanho)
								.addComponent(ltotalganho)))
						.addComponent(btacthv, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btbaixhv, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btcimhv, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		jlhistvendas = new JList();
		jlhistvendas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btcimhv.setEnabled(true);
				btbaixhv.setEnabled(true);
				
				indexhv = jlhistvendas.getSelectedIndex();
				jlhistvendas.ensureIndexIsVisible(indexhv);
				
				if(indexhv == 0){
					btcimhv.setEnabled(false);
				} else
					try {
						if(historicoDAO.listaUltimasVendas(utilizador.getNomeUtilizador()).length == indexhv+1){
							btbaixhv.setEnabled(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});

		updatehv();
		
		scrollPane_1.setViewportView(jlhistvendas);
		jlhistvendas.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_5.setLayout(gl_panel_5);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
							.addGap(12))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
								.addComponent(panel_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
					.addGap(21))
		);
		
		JLabel nrtotcv = new JLabel("Número total de compras e vendas:");
		nrtotcv.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		nrtotcev = new JLabel(historicoDAO.getNumeroHistoricos(utilizador.getNomeUtilizador()) + "");
		
		btactg = new JButton("");
		btactg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateg();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btactg.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/actualizar.png")));
		btactg.setContentAreaFilled(false);
		btactg.setBorderPainted(false);
		
		btbaixg = new JButton("");
		btbaixg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcimg.setEnabled(true);
				indexg += 1;
				
				jlgeral.setSelectedIndex(indexg);
				jlgeral.ensureIndexIsVisible(indexg);
				
				try {
					if(historicoDAO.listaHistorico(utilizador.getNomeUtilizador()).length == indexg+1){
						btbaixg.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btbaixg.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/setabaixo.png")));
		btbaixg.setContentAreaFilled(false);
		btbaixg.setBorderPainted(false);
		
		btcimg = new JButton("");
		btcimg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btbaixg.setEnabled(true);
				indexg -= 1;
				
				jlgeral.setSelectedIndex(indexg);
				jlgeral.ensureIndexIsVisible(indexg);
				
				if(indexg == 0){
					btcimg.setEnabled(false);
				}
			}
		});
		btcimg.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/setacima.png")));
		btcimg.setContentAreaFilled(false);
		btcimg.setBorderPainted(false);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(nrtotcv, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nrtotcev, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
							.addComponent(btcimg, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btbaixg, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btactg, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(btactg, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btbaixg, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btcimg, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(nrtotcev)
								.addComponent(nrtotcv))
							.addContainerGap())))
		);
		
		jlgeral = new JList();
		jlgeral.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				btcimg.setEnabled(true);
				btbaixg.setEnabled(true);
				
				indexg = jlgeral.getSelectedIndex();
				jlgeral.ensureIndexIsVisible(indexg);
				
				if(indexg == 0){
					btcimg.setEnabled(false);
				} else
					try {
						if(historicoDAO.listaHistorico(utilizador.getNomeUtilizador()).length == indexg+1){
							btbaixg.setEnabled(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		updateg();
		
		btcimg.setEnabled(false);
		
		scrollPane.setViewportView(jlgeral);
		jlgeral.setBorder(new LineBorder(Color.LIGHT_GRAY));
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
				HistoricoGUI.this.setVisible(false);
				HistoricoGUI.this.dispose();
			}
		});
		logo.setContentAreaFilled(false);
		logo.setBorderPainted(false);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(HistoricoGUI.class.getResource("/Imagens/eyelogo.png")));
		
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
				HistoricoGUI.this.setVisible(false);
				HistoricoGUI.this.dispose();
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
				HistoricoGUI.this.setVisible(false);
				HistoricoGUI.this.dispose();
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
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateg() throws SQLException{
		btbaixg.setEnabled(true);
		indexg = 0;
		
		jlgeral.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = historicoDAO.listaHistorico(utilizador.getNomeUtilizador());
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		jlgeral.setSelectedIndex(indexg);
		jlgeral.ensureIndexIsVisible(indexg);
		
		if(jlgeral.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    jlgeral.setCellRenderer(renderer);
		}
		
		btcimg.setEnabled(false);
		nrtotcev = new JLabel(historicoDAO.getNumeroHistoricos(utilizador.getNomeUtilizador()) + "");
		
		btbaixg.setEnabled(true);
		if (jlgeral.getSelectedValue() == null || historicoDAO.listaHistorico(utilizador.getNomeUtilizador()).length < 2){
			btbaixg.setEnabled(false);
		}
	}
	public void updatehc() throws SQLException{
		btbaixhc.setEnabled(true);
		indexhc = 0;
		
		jlhistcompras.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = historicoDAO.listaHistoricoCompras(utilizador.getNomeUtilizador());
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		jlhistcompras.setSelectedIndex(indexhc);
		jlhistcompras.ensureIndexIsVisible(indexhc);
		
		if(jlhistcompras.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    jlhistcompras.setCellRenderer(renderer);
		}
		
		btcimhc.setEnabled(false);
		lntotalcompras = new JLabel(historicoDAO.getNumeroCompras(utilizador.getNomeUtilizador()) + "");
		ltotalgasto = new JLabel(historicoDAO.getTotalGasto(utilizador.getNomeUtilizador()) + "");
		
		btbaixhc.setEnabled(true);
		if (jlhistcompras.getSelectedValue() == null || historicoDAO.listaHistoricoCompras(utilizador.getNomeUtilizador()).length < 2){
			btbaixhc.setEnabled(false);
		}
	}
	public void updatehv() throws SQLException{
		btbaixhv.setEnabled(true);
		indexhv = 0;
		
		jlhistvendas.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = historicoDAO.listaUltimasVendas(utilizador.getNomeUtilizador());
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		jlhistvendas.setSelectedIndex(indexhv);
		jlhistvendas.ensureIndexIsVisible(indexhv);
		
		if(jlhistvendas.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    jlhistvendas.setCellRenderer(renderer);
		}
		
		btcimhv.setEnabled(false);
		ltotalganho = new JLabel(historicoDAO.getTotalGanho(utilizador.getNomeUtilizador()) + "");
		lntotalvendas = new JLabel(historicoDAO.getNumeroVendas(utilizador.getNomeUtilizador()) + "");
		
		btbaixhv.setEnabled(true);
		if (jlhistvendas.getSelectedValue() == null || historicoDAO.listaUltimasVendas(utilizador.getNomeUtilizador()).length < 2){
			btbaixhv.setEnabled(false);
		}
	}
}
