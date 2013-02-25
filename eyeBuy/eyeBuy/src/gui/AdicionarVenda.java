package gui;


import gui.JFileChooserPreview.PreviewPane;

import java.awt.EventQueue;

import javax.sql.rowset.serial.SerialException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;

import model.Categoria;
import model.EyeBuy;
import model.Imagem;
import model.Produto;
import model.SubCategoria;
import model.Utilizador;
import model.transacoes.Leilao;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.CategoriaDAO;
import dao.ImagemDAO;
import dao.LeilaoDAO;
import dao.MetodoPagamentoDAO;
import dao.ProdutoDAO;
import dao.SubCategoriaDAO;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;



public class AdicionarVenda extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfnome;
	private JTextField tfpreco;
	private JTextField tfquantidade;
	
	private TreeMap<String,Imagem> imagens = new TreeMap<String,Imagem>();
	
	private EyeBuy eyeBuy = new EyeBuy(); 
	private Utilizador utilizador = new Utilizador(); 
	private CategoriaDAO categoriaDAO = new CategoriaDAO(); 
	private SubCategoriaDAO subcategoriaDAO = new SubCategoriaDAO(); 
	private ProdutoDAO produtoDAO = new ProdutoDAO(); 
	private LeilaoDAO leilaoDAO = new LeilaoDAO();
	private MetodoPagamentoDAO metodopagamentoDAO = new MetodoPagamentoDAO();
	
	private PaginaVendas pai;
	
	private JList jlfotos = new JList();
	
	private JButton btcim = new JButton();
	private JButton btbaix = new JButton();
	private JButton btrem = new JButton();
	
	private int index = 0;
	
	private JComboBox cbcategoria = new JComboBox();
	private JComboBox cbsubcategoria = new JComboBox();
	
	private JComboBox cbdia = new JComboBox();
	private JComboBox cbmes = new JComboBox();
	private JComboBox cbano = new JComboBox();
	private JComboBox cbhora = new JComboBox();
	private JComboBox cbmin = new JComboBox();
	private JComboBox cbestado = new JComboBox();
	private JComboBox cbtipovenda = new JComboBox();
	private JTextArea tadesc = new JTextArea();
	
	JComboBox cbmetodopagamento = new JComboBox();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarVenda frame = new AdicionarVenda(new Utilizador(), null);
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
	 */
	public AdicionarVenda(Utilizador user,PaginaVendas pv) {
		eyeBuy.addObserver(this);
		subcategoriaDAO.addObserver(this);
		categoriaDAO.addObserver(this);
		utilizador.addObserver(this);
		produtoDAO.addObserver(this);
		leilaoDAO.addObserver(this);
		
		pai = pv;
		
		if(user != null){
			this.utilizador = user.clone();
			utilizador.addObserver(this);
		}
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 456, 651);
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, 0, 0, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 557, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		
		JLabel lblTipoDeMensagem = new JLabel("Nome:");
		lblTipoDeMensagem.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfnome = new JTextField();
		tfnome.setColumns(10);
		
		JLabel lblMorada = new JLabel("Preço:");
		lblMorada.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfpreco = new JTextField();
		tfpreco.setColumns(10);
		
		JLabel label_1 = new JLabel("€");
		
		JLabel lblFotos = new JLabel("Descrição:");
		lblFotos.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JButton btadd = new JButton("");
		btadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file;
				JFileChooser chooser = new JFileChooser();
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", new String[]{"jpg","png"});    
				chooser.setFileFilter(filter);    
				chooser.setAcceptAllFileFilterUsed(false);  
				chooser.setMultiSelectionEnabled(false); 
				
				PreviewPane previewPane = new PreviewPane();
				chooser.setAccessory(previewPane);
				chooser.addPropertyChangeListener(previewPane);
				int option = chooser.showDialog(AdicionarVenda.this, "Selecionar Imagem");
				
				if(option == JFileChooser.APPROVE_OPTION){
					file = chooser.getSelectedFile();
					
					if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")) {
						
						Imagem img;
						try {
							img = new Imagem(file.getName(), eyeBuy.novaImagemProduto(file));
							if(imagens.put(img.getDescricao(),img.clone()) == null){ 
								updatefotos();
							}else{
								JOptionPane.showMessageDialog(null, "Imagem já existe", "Erro", JOptionPane.WARNING_MESSAGE );
							}
						} catch (SerialException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						
					}else{
						JOptionPane.showMessageDialog(null, "Arquivo não suportado", "Erro", JOptionPane.WARNING_MESSAGE );
					}
				}
			}
		});
		btadd.setToolTipText("Adicionar foto");
		btadd.setIcon(new ImageIcon(AdicionarVenda.class.getResource("/Imagens/adicionar.png")));
		btadd.setContentAreaFilled(false);
		btadd.setBorderPainted(false);
		
		btrem = new JButton("");
		btrem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					imagens.remove((String)jlfotos.getSelectedValue());
					updatefotos();
				}
			}
		});
		btrem.setToolTipText("Remover foto selecionada");
		btrem.setIcon(new ImageIcon(AdicionarVenda.class.getResource("/Imagens/remover.png")));
		btrem.setContentAreaFilled(false);
		btrem.setBorderPainted(false);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		cbcategoria = new JComboBox();
		cbcategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbcategoria.getSelectedIndex() != -1){
					try {
						cbsubcategoria.setModel(new DefaultComboBoxModel(subcategoriaDAO.getArraySubCategorias((String)cbcategoria.getSelectedItem())));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		try {
			cbcategoria.setModel(new DefaultComboBoxModel(categoriaDAO.getArrayCategorias()));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JLabel lblSubcategoria = new JLabel("Subcategoria:");
		lblSubcategoria.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblFotos_1 = new JLabel("Fotos:");
		lblFotos_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JButton btnaceitar = new JButton("");
		btnaceitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfnome.getText().equals("") ||
						cbcategoria.getSelectedIndex() == -1 ||
						cbsubcategoria.getSelectedIndex() == -1 ||
						tfpreco.getText().equals("") ||
						tfquantidade.getText().equals("") ||
						cbestado.getSelectedIndex() == -1 ||
						cbtipovenda.getSelectedIndex() == -1 ||
						tadesc.getText().equals("") ||
						imagens.isEmpty()){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.WARNING_MESSAGE );
				}
				else if(((String)cbtipovenda.getSelectedItem()).equals("Leilão") && (cbdia.getSelectedIndex() == -1 ||
						cbhora.getSelectedIndex() == -1 ||
						cbmes.getSelectedIndex() == -1 ||
						cbano.getSelectedIndex() == -1 ||
						cbmin.getSelectedIndex() == -1)){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.WARNING_MESSAGE );
				}
				else if(!eyeBuy.isDouble(tfpreco.getText())){
					JOptionPane.showMessageDialog(null, "Introduza um preço válido", "Erro", JOptionPane.WARNING_MESSAGE );
				}
				else if(!eyeBuy.isInt(tfquantidade.getText())){
					JOptionPane.showMessageDialog(null, "Introduza uma quantidade válida", "Erro", JOptionPane.WARNING_MESSAGE );
				}
				else{
					Produto novo = new Produto(tfnome.getText(), 
							Double.parseDouble(tfpreco.getText()), tadesc.getText(), 
							(String) cbestado.getSelectedItem(), Integer.parseInt(tfquantidade.getText()), 
							imagens.size(), (String) cbtipovenda.getSelectedItem(), 
							new SubCategoria((String) cbsubcategoria.getSelectedItem(),
							new Categoria((String) cbcategoria.getSelectedItem())),
							new Categoria((String) cbcategoria.getSelectedItem()), 
							utilizador, utilizador.getLocalidade());
					
					int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende por a venda o produto?", "Aviso", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION){
						int add = -1;
						try {
							add = produtoDAO.novoProduto(novo);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						if(add != -1){
							try {
								ImagemDAO.updateImagens(add,imagens);
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							if(((String)cbtipovenda.getSelectedItem()).equals("Leilão")){
								Leilao novoleilao = new Leilao(add, new GregorianCalendar(Integer.parseInt((String)cbano.getSelectedItem()),Integer.parseInt((String)cbmes.getSelectedItem()), Integer.parseInt((String)cbdia.getSelectedItem()),Integer.parseInt((String)cbhora.getSelectedItem()), Integer.parseInt((String)cbmin.getSelectedItem())), Double.parseDouble(tfpreco.getText()));
								try {
									leilaoDAO.addLeilao(novoleilao);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							
							if(cbmetodopagamento.getSelectedItem().equals("Todos")){
								try {
									metodopagamentoDAO.addMetodoPagamento(add,"Paypal");
									metodopagamentoDAO.addMetodoPagamento(add,"MBNet");
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}else{
								try {
									metodopagamentoDAO.addMetodoPagamento(add,(String)cbmetodopagamento.getSelectedItem());
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							
							JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE );
							try {
								pai.update();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							AdicionarVenda.this.setVisible(false);
							AdicionarVenda.this.dispose();
						}
						else{
							JOptionPane.showMessageDialog(null, "Não foi possível adicionar o produto", "Erro", JOptionPane.WARNING_MESSAGE );
						}
					}
				}
			}
		});
		btnaceitar.setIcon(new ImageIcon(AdicionarVenda.class.getResource("/Imagens/lida.png")));
		btnaceitar.setContentAreaFilled(false);
		btnaceitar.setBorderPainted(false);
		
		JButton btnsair = new JButton("");
		btnsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende cancelar?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					AdicionarVenda.this.setVisible(false);
					AdicionarVenda.this.dispose();
				}
			}
		});
		btnsair.setIcon(new ImageIcon(AdicionarVenda.class.getResource("/Imagens/sair32.png")));
		btnsair.setContentAreaFilled(false);
		btnsair.setBorderPainted(false);
		
		cbestado = new JComboBox();
		cbestado.setModel(new DefaultComboBoxModel(new String[] {"Novo ", "Usado"}));
		
		JLabel lblTipoDeVenda = new JLabel("Tipo de venda:");
		lblTipoDeVenda.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		cbtipovenda = new JComboBox();
		cbtipovenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!((String)cbtipovenda.getSelectedItem()).equals("Leilão")) {
					cbdia.setEnabled(false);
					cbmes.setEnabled(false);
					cbano.setEnabled(false);
					cbhora.setEnabled(false);
					cbmin.setEnabled(false);
				}
				else{
					cbdia.setEnabled(true);
					cbmes.setEnabled(true);
					cbano.setEnabled(true);
					cbhora.setEnabled(true);
					cbmin.setEnabled(true);
				}
			}
		});
		cbtipovenda.setModel(new DefaultComboBoxModel(new String[] {"Venda directa", "Leilão"}));
		
		JLabel lblDatFinalLeilo = new JLabel("Data final leilão:");
		lblDatFinalLeilo.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		cbdia = new JComboBox();
		cbdia.setEnabled(false);
		cbdia.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		
		cbmes = new JComboBox();
		cbmes.setEnabled(false);
		cbmes.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		
		cbano = new JComboBox();
		cbano.setEnabled(false);
		cbano.setModel(new DefaultComboBoxModel(new String[] {"2013", "2014"}));
		
		JLabel lblHoraFinalLeilo = new JLabel("Hora final leilão:");
		lblHoraFinalLeilo.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		cbhora = new JComboBox();
		cbhora.setEnabled(false);
		cbhora.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "00", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		cbhora.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblH = new JLabel("h");
		
		cbmin = new JComboBox();
		cbmin.setEnabled(false);
		cbmin.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		
		JLabel lblM = new JLabel("m");
		
		cbsubcategoria = new JComboBox();
		if(cbcategoria.getSelectedIndex() != -1){
			try {
				cbsubcategoria.setModel(new DefaultComboBoxModel(subcategoriaDAO.getArraySubCategorias((String)cbcategoria.getSelectedItem())));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		tfquantidade = new JTextField();
		tfquantidade.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		btbaix = new JButton("");
		btbaix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btcim.setEnabled(true);
				index += 1;
				
				jlfotos.setSelectedIndex(index);
				jlfotos.ensureIndexIsVisible(index);
				
				if(listaImagens().length == index+1){
					btbaix.setEnabled(false);
				}
			}
		});
		btbaix.setIcon(new ImageIcon(AdicionarVenda.class.getResource("/Imagens/setabaixo.png")));
		btbaix.setContentAreaFilled(false);
		btbaix.setBorderPainted(false);
		
		btcim = new JButton("");
		btcim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btbaix.setEnabled(true);
				index -= 1;
				
				jlfotos.setSelectedIndex(index);
				jlfotos.ensureIndexIsVisible(index);
				
				if(index == 0){
					btcim.setEnabled(false);
				}
			}
		});
		btcim.setIcon(new ImageIcon(AdicionarVenda.class.getResource("/Imagens/setacima.png")));
		btcim.setContentAreaFilled(false);
		btcim.setBorderPainted(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatefotos();
			}
		});
		button.setIcon(new ImageIcon(AdicionarVenda.class.getResource("/Imagens/actualizar.png")));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		cbmetodopagamento = new JComboBox();
		cbmetodopagamento.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Paypal", "MBNet"}));
		
		JLabel lblMetodosDePagamento = new JLabel("<html>Metodos de pagamento");
		lblMetodosDePagamento.setFont(new Font("Dialog", Font.PLAIN, 12));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEstado, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuantidade)
								.addComponent(lblMorada)
								.addComponent(lblSubcategoria)
								.addComponent(lblCategoria)
								.addComponent(lblTipoDeMensagem)
								.addComponent(lblTipoDeVenda))
							.addGap(21)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(cbsubcategoria, 0, 284, Short.MAX_VALUE)
								.addComponent(tfnome, 284, 284, Short.MAX_VALUE)
								.addComponent(cbcategoria, 0, 284, Short.MAX_VALUE)
								.addComponent(cbestado, 0, 284, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(tfpreco, 0, 0, Short.MAX_VALUE)
										.addComponent(tfquantidade, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addComponent(cbtipovenda, 0, 284, Short.MAX_VALUE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFotos)
								.addComponent(lblFotos_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(11)
									.addComponent(btadd, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btrem, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btcim, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btbaix, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
									.addComponent(btnsair, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnaceitar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblHoraFinalLeilo)
								.addComponent(lblMetodosDePagamento, 0, 0, Short.MAX_VALUE)
								.addComponent(lblDatFinalLeilo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(cbhora, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cbdia, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(18)
											.addComponent(cbmes, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(4)
											.addComponent(lblH)
											.addGap(5)
											.addComponent(cbmin, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(cbano, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(3)
											.addComponent(lblM))))
								.addComponent(cbmetodopagamento, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfnome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoDeMensagem))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategoria)
						.addComponent(cbcategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSubcategoria)
						.addComponent(cbsubcategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstado)
						.addComponent(cbestado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblQuantidade)
						.addComponent(tfquantidade, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMorada)
						.addComponent(tfpreco, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTipoDeVenda)
						.addComponent(cbtipovenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblMetodosDePagamento, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbmetodopagamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbdia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbmes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbano, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDatFinalLeilo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbhora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblH, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
						.addComponent(cbmin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblM)
						.addComponent(lblHoraFinalLeilo))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFotos)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFotos_1)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btadd, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btrem, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnsair, 0, 0, Short.MAX_VALUE)
								.addComponent(btnaceitar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btcim, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btbaix, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(50))
		);
		
		tadesc = new JTextArea();
		scrollPane_1.setViewportView(tadesc);
		

		
		jlfotos = new JList();
		jlfotos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btcim.setEnabled(true);
				btbaix.setEnabled(true);
				
				index = jlfotos.getSelectedIndex();
				jlfotos.ensureIndexIsVisible(index);
				
				if(index == 0){
					btcim.setEnabled(false);
				} else if(index+1 == imagens.size()){
					btbaix.setEnabled(false);
				}
			}
		});
		scrollPane.setViewportView(jlfotos);
		jlfotos.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		updatefotos();
		
		JPopupMenu popupMenu_3 = new JPopupMenu();
		addPopup(cbmin, popupMenu_3);
		
		JPopupMenu popupMenu_2 = new JPopupMenu();
		addPopup(cbhora, popupMenu_2);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(cbtipovenda, popupMenu_1);
		
		JMenuItem mntmLeilo = new JMenuItem("Leilão");
		popupMenu_1.add(mntmLeilo);
		
		JMenuItem mntmVendaDirecta = new JMenuItem("Venda directa");
		popupMenu_1.add(mntmVendaDirecta);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(cbestado, popupMenu);
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		popupMenu.add(mntmNovo);
		
		JMenuItem mntmUsado = new JMenuItem("Usado");
		popupMenu.add(mntmUsado);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblSugestoDeTroca = new JLabel("Colocar produto à venda");
		lblSugestoDeTroca.setFont(new Font("Dialog", Font.BOLD, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSugestoDeTroca)
					.addContainerGap(513, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSugestoDeTroca)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
	
	private void updatefotos(){
		btrem.setEnabled(true);
		btbaix.setEnabled(true);
		index = 0;
		
		jlfotos.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] values = listaImagens();
			
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		jlfotos.setSelectedIndex(index);
		jlfotos.ensureIndexIsVisible(index);
		
		btcim.setEnabled(false);
		btbaix.setEnabled(true);
		if (listaImagens().length < 2){
			btbaix.setEnabled(false);
		}
		if (jlfotos.getSelectedValue() == null){
			btrem.setEnabled(false);
		}
	}
	
	public String[] listaImagens(){
		ArrayList<String> coll = new ArrayList<String>(imagens.keySet());
		String[] res = new String[coll.size()];
		
		for(int i=0; i<coll.size();i++){
			res[i] = coll.get(i);
		}
		
		return res;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
