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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JList;
import javax.swing.border.TitledBorder;

import dao.CategoriaDAO;
import dao.LocalidadeDAO;
import dao.ProdutoDAO;

import model.Utilizador;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;



public class ConsultarCarrinho extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	
	private Utilizador utilizador = new Utilizador();
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private LocalidadeDAO localidadeDAO = new LocalidadeDAO();
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	
	private JButton btremc = new JButton();
	private JButton btlimpc = new JButton();
	
	private JButton btbaixc = new JButton();
	private JButton btcimc = new JButton();
	private JButton btactc = new JButton();
	private JButton btbaixp = new JButton();
	private JButton btcimp = new JButton();
	private JButton btactp = new JButton();
	private JButton btbaixm = new JButton();
	private JButton btcimm = new JButton();
	private JButton btactm = new JButton();

	private JButton btlimpm = new JButton();
	private JButton btlimpp = new JButton();
	private JButton btpagarp = new JButton();
	private JButton btpagarm = new JButton();
	
	private JList jlcarrinho = new JList();
	private JList jlpaypal = new JList();
	private JList jlmbnet = new JList();
	
	private int indexc = 0;
	private int indexm = 0;
	private int indexp = 0;
	
	private JLabel label_4 = new JLabel();
	private JLabel label_3 = new JLabel();
	private JLabel label_2 = new JLabel();
	
	JComboBox cbcategoria = new JComboBox();
	
	JComboBox cblocalidade = new JComboBox();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarCarrinho frame = new ConsultarCarrinho(new Utilizador());
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
	public ConsultarCarrinho(Utilizador user) throws SQLException {
		produtoDAO.addObserver(this);
		utilizador.addObserver(this);

		categoriaDAO.addObserver(this);
		localidadeDAO.addObserver(this);
		
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
		mnNewMenu.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/comunidade.png")));
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
			public void actionPerformed(ActionEvent arg0) {
				new Ajuda();
			}
		});
		mnNewMenu.add(mntmAjuda);
		
		JMenu mnCarrinho = new JMenu("Carrinho ▼  ");
		mnCarrinho.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/carr.png")));
		menuBar.add(mnCarrinho);
		
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
					try {
						update();
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
		
		mnUtilizador.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/home2.png")));
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
				ConsultarCarrinho.this.setVisible(false);
				ConsultarCarrinho.this.dispose();
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
				ConsultarCarrinho.this.setVisible(false);
				ConsultarCarrinho.this.dispose();
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
				ConsultarCarrinho.this.setVisible(false);
				ConsultarCarrinho.this.dispose();
			}
		});
		mnUtilizador.add(mntmHistrico);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende sair?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					ConsultarCarrinho.this.setVisible(false);
					ConsultarCarrinho.this.dispose();
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
				ConsultarCarrinho.this.setVisible(false);
				ConsultarCarrinho.this.dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/home2.png")));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
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
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Carrinho de compras", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Pagamentos por PayPal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		jlpaypal = new JList();
		jlpaypal.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btcimp.setEnabled(true);
				btbaixp.setEnabled(true);
				
				indexp = jlpaypal.getSelectedIndex();
				jlpaypal.ensureIndexIsVisible(indexp);
				
				btcimp.setEnabled(false);
				
				btbaixp.setEnabled(true);
				btpagarp.setEnabled(true);
				btlimpp.setEnabled(true);
				if(indexp == 0){
					btcimp.setEnabled(false);
				}
				try {
					if (produtoDAO.listaCarrinhoPaypal(utilizador.getNomeUtilizador()).length < 2){
						btbaixp.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (jlpaypal.getSelectedValue() == null){
					btpagarp.setEnabled(false);
					btlimpp.setEnabled(false);
				}
			}
		});
		
		updatep();
		
		btcimp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaixp.setEnabled(true);
				indexp -= 1;
				
				jlpaypal.setSelectedIndex(indexp);
				jlpaypal.ensureIndexIsVisible(indexp);
				
				if(indexp == 0){
					btcimp.setEnabled(false);
				}
			}
		});
		btcimp.setEnabled(false);
		
		jlpaypal.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btlimpp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover do carrinho?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					try {
						produtoDAO.removeProdutoCarrinhoListado(utilizador.getNomeUtilizador(),(String)jlpaypal.getSelectedValue());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						update();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		btlimpp.setToolTipText("Remover produto selecionado do carrinho");
		btlimpp.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/vazio32.png")));
		btlimpp.setContentAreaFilled(false);
		btlimpp.setBorderPainted(false);
		btpagarp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende comprar todos os produtos do carrinho com pagamento por paypal?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					
					if (utilizador.getContaPaypal().equals("") || utilizador.getContaPaypal() == null){
						int reply2 = JOptionPane.showConfirmDialog(null, "Ainda nao definiu uma conta paypal, pretende editar o perfil agora?", "Aviso", JOptionPane.YES_NO_OPTION);
						if(reply2 == JOptionPane.YES_OPTION){
							try {
								new EditarPerfil(utilizador,null);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}else{
					
						try {
							if(!produtoDAO.compraProdutosPaypal(utilizador.getNomeUtilizador())){
								JOptionPane.showMessageDialog(null, "Ocorreu um erro, tente novamente", "Erro", JOptionPane.WARNING_MESSAGE );
								try {
									update();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else{
								JOptionPane.showMessageDialog(null, "Compra efectuada com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE );
								try {
									update();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						} catch (HeadlessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		btpagarp.setToolTipText("Efectuar pagamento/compra de todos os produtos com metodo de pagamento Paypal");
		btpagarp.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/euro32.png")));
		btpagarp.setContentAreaFilled(false);
		btpagarp.setBorderPainted(false);
		btactp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatep();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

		btactp.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/act32.png")));
		btactp.setContentAreaFilled(false);
		btactp.setBorderPainted(false);
		btbaixp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcimp.setEnabled(true);
				indexp += 1;
				
				jlpaypal.setSelectedIndex(indexp);
				jlpaypal.ensureIndexIsVisible(indexp);
				
				try {
					if(produtoDAO.listaCarrinhoPaypal(utilizador.getNomeUtilizador()).length == indexp+1){
						btbaixp.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

		btbaixp.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/baix32.png")));
		btbaixp.setContentAreaFilled(false);
		btbaixp.setBorderPainted(false);
		

		btcimp.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/cim32.png")));
		btcimp.setContentAreaFilled(false);
		btcimp.setBorderPainted(false);
		
		JLabel lblTotal = new JLabel("Montante Total:");
		
		label_4 = new JLabel(produtoDAO.getCustoTotalCarrinhoPaypal(utilizador.getNomeUtilizador()) + " €");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblTotal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_4, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btcimp, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btbaixp, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btactp, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btpagarp, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btlimpp, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(8))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(jlpaypal, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(jlpaypal, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(btactp, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(btpagarp, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(btlimpp, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(btcimp, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(btbaixp, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTotal)
								.addComponent(label_4))))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Pagamentos por MBNet", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		jlmbnet = new JList();
		jlmbnet.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btcimm.setEnabled(true);
				btbaixm.setEnabled(true);
				
				indexm = jlmbnet.getSelectedIndex();
				jlmbnet.ensureIndexIsVisible(indexm);
				
				btcimm.setEnabled(false);
				
				btbaixm.setEnabled(true);
				btpagarm.setEnabled(true);
				btlimpm.setEnabled(true);
				if(indexm == 0){
					btcimm.setEnabled(false);
				}
				try {
					if (produtoDAO.listaCarrinhoMBNet(utilizador.getNomeUtilizador()).length < 2){
						btbaixm.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (jlmbnet.getSelectedValue() == null){
					btpagarm.setEnabled(false);
					btlimpm.setEnabled(false);
				}
			}
		});
		
		updatem();
		
		btcimm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaixm.setEnabled(true);
				indexm -= 1;
				
				jlmbnet.setSelectedIndex(indexm);
				jlmbnet.ensureIndexIsVisible(indexm);
				
				if(indexm == 0){
					btcimm.setEnabled(false);
				}
			}
		});
		btcimm.setEnabled(false);
		
		jlmbnet.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btactm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatem();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

		btactm.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/act32.png")));
		btactm.setContentAreaFilled(false);
		btactm.setBorderPainted(false);
		btpagarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende comprar todos os produtos do carrinho com pagamento por MBNet?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					
					if (utilizador.getContaPaypal().equals("") || utilizador.getContaPaypal() == null){
						int reply2 = JOptionPane.showConfirmDialog(null, "Ainda nao definiu uma conta MBNet, pretende editar o perfil agora?", "Aviso", JOptionPane.YES_NO_OPTION);
						if(reply2 == JOptionPane.YES_OPTION){
							try {
								new EditarPerfil(utilizador,null);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}else{
					
						try {
							if(!produtoDAO.compraProdutosMBNet(utilizador.getNomeUtilizador())){
								JOptionPane.showMessageDialog(null, "Ocorreu um erro, tente novamente", "Erro", JOptionPane.WARNING_MESSAGE );
								try {
									update();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else{
								JOptionPane.showMessageDialog(null, "Compra efectuada com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE );
								try {
									update();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						} catch (HeadlessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		btpagarm.setToolTipText("Efectuar pagamento/compra de todos os produtos com metodo de pagamento MBNet");
		btpagarm.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/euro32.png")));
		btpagarm.setContentAreaFilled(false);
		btpagarm.setBorderPainted(false);
		btlimpm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover do carrinho?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					try {
						produtoDAO.removeProdutoCarrinhoListado(utilizador.getNomeUtilizador(),(String)jlmbnet.getSelectedValue());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						update();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		btlimpm.setToolTipText("Remover produto selecionado do carrinho");
		btlimpm.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/vazio32.png")));
		btlimpm.setContentAreaFilled(false);
		btlimpm.setBorderPainted(false);
		btbaixm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcimm.setEnabled(true);
				indexm += 1;
				
				jlmbnet.setSelectedIndex(indexm);
				jlmbnet.ensureIndexIsVisible(indexm);
				
				try {
					if(produtoDAO.listaCarrinhoMBNet(utilizador.getNomeUtilizador()).length == indexm+1){
						btbaixm.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

		btbaixm.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/baix32.png")));
		btbaixm.setContentAreaFilled(false);
		btbaixm.setBorderPainted(false);
		

		btcimm.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/cim32.png")));
		btcimm.setContentAreaFilled(false);
		btcimm.setBorderPainted(false);
		
		JLabel label = new JLabel("Montante Total:");
		
		label_3 = new JLabel(produtoDAO.getCustoTotalCarrinhoMBNet(utilizador.getNomeUtilizador()) + " €");
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(jlmbnet, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_3, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btcimm, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btbaixm, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btactm, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btpagarm, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btlimpm, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(jlmbnet, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(btcimm, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(btbaixm, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(btactm, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(btpagarm, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(btlimpm, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(label_3))
							.addGap(20))))
		);
		panel_4.setLayout(gl_panel_4);
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
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 470, Short.MAX_VALUE)
								.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(4))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)))
					.addGap(7))
		);
		
		jlcarrinho = new JList();
		jlcarrinho.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btcimc.setEnabled(true);
				btbaixc.setEnabled(true);
				
				indexc = jlcarrinho.getSelectedIndex();
				jlcarrinho.ensureIndexIsVisible(indexc);

				btbaixc.setEnabled(true);
				btremc.setEnabled(true);
				btlimpc.setEnabled(true);
				if(indexc == 0){
					btcimc.setEnabled(false);
				}
				try {
					if (produtoDAO.listaCarrinho(utilizador.getNomeUtilizador()).length < 2){
						btbaixc.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (jlcarrinho.getSelectedValue() == null){
					btremc.setEnabled(false);
					btlimpc.setEnabled(false);
				}
			}
		});

		updatec();
		
		btcimc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaixc.setEnabled(true);
				indexc -= 1;
				
				jlcarrinho.setSelectedIndex(indexc);
				jlcarrinho.ensureIndexIsVisible(indexc);
				
				if(indexc == 0){
					btcimc.setEnabled(false);
				}
			}
		});
		btcimc.setEnabled(false);
		
		jlcarrinho.setBorder(new LineBorder(Color.LIGHT_GRAY));
		btlimpc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que esvaziar o seu carrinho?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					try {
						produtoDAO.limpaCarrinho(utilizador.getNomeUtilizador());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						update();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		btlimpc.setToolTipText("Limpar carrinho de compras");
		btlimpc.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/remcarrinho32.png")));
		btlimpc.setContentAreaFilled(false);
		btlimpc.setBorderPainted(false);
		

		btremc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover do carrinho?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					try {
						produtoDAO.removeProdutoCarrinhoListado(utilizador.getNomeUtilizador(),(String)jlcarrinho.getSelectedValue());
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						update();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btremc.setToolTipText("Remover produto selecionado do carrinho");
		btremc.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/vazio32.png")));
		btremc.setContentAreaFilled(false);
		btremc.setBorderPainted(false);
		btactc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatec();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btactc.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/act32.png")));
		btactc.setContentAreaFilled(false);
		btactc.setBorderPainted(false);
		btbaixc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcimc.setEnabled(true);
				indexc += 1;
				
				jlcarrinho.setSelectedIndex(indexc);
				jlcarrinho.ensureIndexIsVisible(indexc);
				
				try {
					if(produtoDAO.listaCarrinho(utilizador.getNomeUtilizador()).length == indexc+1){
						btbaixc.setEnabled(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btbaixc.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/baix32.png")));
		btbaixc.setContentAreaFilled(false);
		btbaixc.setBorderPainted(false);
		
		btcimc.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/cim32.png")));
		btcimc.setContentAreaFilled(false);
		btcimc.setBorderPainted(false);
		
		JLabel label_1 = new JLabel("Montante Total:");
		
		label_2 = new JLabel(produtoDAO.getCustoTotalCarrinho(utilizador.getNomeUtilizador()) +  " €");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
							.addComponent(btcimc, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btbaixc, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btactc, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btremc, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btlimpc, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addComponent(jlcarrinho, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(jlcarrinho, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btremc, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(btlimpc)
								.addComponent(btactc, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(btbaixc, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(btcimc, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(label_2))))
					.addContainerGap())
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
				ConsultarCarrinho.this.setVisible(false);
				ConsultarCarrinho.this.dispose();
			}
		});
		logo.setContentAreaFilled(false);
		logo.setBorderPainted(false);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(ConsultarCarrinho.class.getResource("/Imagens/eyelogo.png")));
		
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
				ConsultarCarrinho.this.setVisible(false);
				ConsultarCarrinho.this.dispose();
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
				ConsultarCarrinho.this.setVisible(false);
				ConsultarCarrinho.this.dispose();
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
	
	public void updatec() throws SQLException{
		btbaixc.setEnabled(true);
		indexc = 0;
		
		jlcarrinho.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = produtoDAO.listaCarrinho(utilizador.getNomeUtilizador());
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		jlcarrinho.setSelectedIndex(indexc);
		jlcarrinho.ensureIndexIsVisible(indexc);
		
		if(jlcarrinho.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    jlcarrinho.setCellRenderer(renderer);
		}
		
		btcimc.setEnabled(false);
		btbaixc.setEnabled(true);
		btremc.setEnabled(true);
		btlimpc.setEnabled(true);
		try {
			if (produtoDAO.listaCarrinho(utilizador.getNomeUtilizador()).length < 2){
				btbaixc.setEnabled(false);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (jlcarrinho.getSelectedValue() == null){
			btremc.setEnabled(false);
			btlimpc.setEnabled(false);
		}
		
		try {
			label_2 = new JLabel(produtoDAO.getCustoTotalCarrinho(utilizador.getNomeUtilizador()) +  " €");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatem() throws SQLException{
		btpagarm.setEnabled(true);
		btbaixm.setEnabled(true);
		btlimpm.setEnabled(true);
		indexm = 1;
		
		jlmbnet.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = produtoDAO.listaCarrinhoMBNet(utilizador.getNomeUtilizador());
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		jlmbnet.setSelectedIndex(indexm);
		jlmbnet.ensureIndexIsVisible(indexm);
		
		if(jlmbnet.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    jlmbnet.setCellRenderer(renderer);
		}
		
		btcimm.setEnabled(false);
		
		btbaixm.setEnabled(true);
		btpagarm.setEnabled(true);
		btlimpm.setEnabled(true);
		
		if (produtoDAO.listaCarrinhoMBNet(utilizador.getNomeUtilizador()).length < 2){
			btbaixm.setEnabled(false);
		}
		if (jlmbnet.getSelectedValue() == null){
			btpagarm.setEnabled(false);
			btlimpm.setEnabled(false);
		}
		
		label_3 = new JLabel(produtoDAO.getCustoTotalCarrinhoMBNet(utilizador.getNomeUtilizador()) + " €");
	}
	
	public void updatep() throws SQLException{
		btpagarp.setEnabled(true);
		btbaixp.setEnabled(true);
		indexp = 0;
		
		jlpaypal.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = produtoDAO.listaCarrinhoPaypal(utilizador.getNomeUtilizador());
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		jlpaypal.setSelectedIndex(indexp);
		jlpaypal.ensureIndexIsVisible(indexp);
		
		if(jlpaypal.getSelectedValue() != null){
		    TabListCellRenderer renderer = new TabListCellRenderer(80);
		    jlpaypal.setCellRenderer(renderer);
		}
		
		btcimp.setEnabled(false);
		
		btbaixp.setEnabled(true);
		btpagarp.setEnabled(true);
		btlimpp.setEnabled(true);
		if (produtoDAO.listaCarrinhoPaypal(utilizador.getNomeUtilizador()).length < 2){
			btbaixp.setEnabled(false);
		}
		if (jlpaypal.getSelectedValue() == null){
			btpagarp.setEnabled(false);
			btlimpp.setEnabled(false);
		}
		
		label_4 = new JLabel(produtoDAO.getCustoTotalCarrinhoPaypal(utilizador.getNomeUtilizador()) + " €");
	}

	public void update() throws SQLException{
		updatec();
		updatem();
		updatep();
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
