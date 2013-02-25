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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JList;

import model.EyeBuy;
import model.Imagem;
import model.Produto;
import model.Utilizador;
import model.transacoes.Leilao;

import javax.swing.JScrollPane;

import dao.ImagemDAO;
import dao.LeilaoDAO;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;


public class EditarProduto extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfnome;
	private JTextField tfpreco;
	private JTextArea tfdescricao;
	
	private JList jlfotos = new JList();
	
	private JButton btcim = new JButton();
	private JButton btbaix = new JButton();
	private JButton btrem = new JButton();
	private JButton btact = new JButton();
	
	private JComboBox cbdia = new JComboBox();
	private JComboBox cbmes = new JComboBox();
	private JComboBox cbano = new JComboBox();
	private JComboBox cbhora = new JComboBox();
	private JComboBox cbmin = new JComboBox();
	
	private EyeBuy eyeBuy = new EyeBuy(); 
	private Produto produto = new Produto();
	private ImagemDAO imagemDAO = new ImagemDAO();
	private LeilaoDAO leilaoDAO = new LeilaoDAO();
	private Utilizador utilizador = new Utilizador();
	
	private int index = 0;
	private TreeMap<String,Imagem> imagens = new TreeMap<String,Imagem>();
	
	PaginaVendas pai = null;
	private JTextField tfquantidade;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarProduto frame = new EditarProduto(new Utilizador(),new Produto(),null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param produto 
	 * @param utilizador 
	 * @throws SQLException 
	 */
	public EditarProduto(Utilizador user, Produto p, PaginaVendas pv) throws SQLException {
		eyeBuy.addObserver(this);
		leilaoDAO.addObserver(this);
		
		pai = pv;
		
		produto = p;
		
		if(user != null){
			this.utilizador = user.clone();
			utilizador.addObserver(this);
		}
		
		
		imagens = new TreeMap<String,Imagem>(imagemDAO.getImagens(produto.getId()));

		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 459, 533);
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
					.addContainerGap(337, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 435, Short.MAX_VALUE))
		);
		
		JLabel lblTipoDeMensagem = new JLabel("Nome:");
		lblTipoDeMensagem.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfnome = new JTextField(produto.getNome());
		tfnome.setColumns(10);
		
		JLabel lblMorada = new JLabel("Preço:");
		lblMorada.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfpreco = new JTextField(produto.getPreco() + "");
		tfpreco.setColumns(10);
		
		if(produto.getMetodoVenda().equals("Leilão") && leilaoDAO.getPrecoActual(produto.getId()) != 0){
			tfpreco.setEnabled(false);
		}
		
		JLabel lblDescrio = new JLabel("Data final leilão:\n");
		lblDescrio.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JButton btnguardar = new JButton("");
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfnome.getText().equals("") ||
						tfpreco.getText().equals("") ||
						tfdescricao.getText().equals("") ||
						tfquantidade.getText().equals("") ||
						imagens.isEmpty()){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.WARNING_MESSAGE );
				}
				else if(produto.getMetodoVenda().equals("Leilão") && 
						(cbdia.getSelectedIndex() == -1 ||
						cbhora.getSelectedIndex() == -1 ||
						cbmes.getSelectedIndex() == -1 ||
						cbano.getSelectedIndex() == -1 ||
						cbmin.getSelectedIndex() == -1)){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.WARNING_MESSAGE );
				}else if(!eyeBuy.isDouble(tfpreco.getText())){
					JOptionPane.showMessageDialog(null, "Introduza um preço válido", "Erro", JOptionPane.WARNING_MESSAGE );
				}else if(!eyeBuy.isInt(tfquantidade.getText())){
					JOptionPane.showMessageDialog(null, "Introduza uma quantidade válida", "Erro", JOptionPane.WARNING_MESSAGE );
				}else{
					int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende editar o produto?", "Aviso", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION){
						produto.setNome(tfnome.getText());
						produto.setPreco(Double.parseDouble(tfpreco.getText()));
						produto.setQuantidade(Integer.parseInt(tfquantidade.getText()));
						produto.setDescricao(tfdescricao.getText());
						
						try {
							ImagemDAO.updateImagens(produto.getId(),imagens);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						if(produto.getMetodoVenda().equals("Leilão")){
							Leilao leilao = new Leilao();
							try {
								leilao = leilaoDAO.getLeilao(produto.getId());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							leilao.setDatafim(new GregorianCalendar((Integer)cbano.getSelectedItem(), (Integer)cbmes.getSelectedItem(), (Integer)cbdia.getSelectedItem(), (Integer)cbhora.getSelectedItem(), (Integer)cbmin.getSelectedItem()));
							leilao.setPrecoActual(Double.parseDouble(tfpreco.getText()));
							
							try {
								leilaoDAO.editLeilao(leilao);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						try {
							pai.update();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						EditarProduto.this.setVisible(false);
						EditarProduto.this.dispose();
					}
				}
			}
		});
		btnguardar.setIcon(new ImageIcon(EditarProduto.class.getResource("/Imagens/guardar32.png")));
		btnguardar.setBorderPainted(false);
		btnguardar.setContentAreaFilled(false);
		
		JLabel label_1 = new JLabel("€");
		
		JLabel lblFotos = new JLabel("Fotos:");
		lblFotos.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JButton btadd = new JButton("");
		btadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file;
				JFileChooser chooser = new JFileChooser();
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", new String[]{"jpg","png"});    
				chooser.setFileFilter(filter);    
				chooser.setAcceptAllFileFilterUsed(false);  
				chooser.setMultiSelectionEnabled(false); 
				
				PreviewPane previewPane = new PreviewPane();
				chooser.setAccessory(previewPane);
				chooser.addPropertyChangeListener(previewPane);
				int option = chooser.showDialog(EditarProduto.this, "Selecionar Imagem");
				
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
		btadd.setIcon(new ImageIcon(EditarProduto.class.getResource("/Imagens/adicionar.png")));
		btadd.setContentAreaFilled(false);
		btadd.setBorderPainted(false);
		
		btrem = new JButton("");
		btrem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende remover?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					imagens.remove((String)jlfotos.getSelectedValue());
					try {
						updatefotos();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btrem.setToolTipText("Remover foto selecionada");
		btrem.setIcon(new ImageIcon(EditarProduto.class.getResource("/Imagens/remover.png")));
		btrem.setContentAreaFilled(false);
		btrem.setBorderPainted(false);
		
		JButton btnsair = new JButton("");
		btnsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende cancelar?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					EditarProduto.this.setVisible(false);
					EditarProduto.this.dispose();
				}
			}
		});
		btnsair.setIcon(new ImageIcon(EditarProduto.class.getResource("/Imagens/sair32.png")));
		btnsair.setContentAreaFilled(false);
		btnsair.setBorderPainted(false);
		
		
		
		cbdia = new JComboBox();
		cbdia.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		
		cbmes = new JComboBox();
		cbmes.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		
		cbano = new JComboBox();
		cbano.setModel(new DefaultComboBoxModel(new String[] {"2013", "2014"}));
		
		JLabel lblHoraFinalLeilo = new JLabel("Hora final leilão:");
		lblHoraFinalLeilo.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		cbhora = new JComboBox();
		cbhora.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "00", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		
		JLabel lblH = new JLabel("h");
		
		cbmin = new JComboBox();
		cbmin.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		
		JLabel lblM = new JLabel("m");
		
		
		if(produto.getMetodoVenda().equals("Leilão")){
			cbdia.setEnabled(true);
			cbdia.setSelectedItem((leilaoDAO.getDataFim(produto.getId())).get(GregorianCalendar.DAY_OF_MONTH));
			
			cbmes.setEnabled(true);
			cbmes.setSelectedItem((leilaoDAO.getDataFim(produto.getId())).get(GregorianCalendar.MONTH));
			
			cbano.setEnabled(true);
			cbano.setSelectedItem((leilaoDAO.getDataFim(produto.getId())).get(GregorianCalendar.YEAR));
			
			cbhora.setEnabled(true);
			cbhora.setSelectedItem((leilaoDAO.getDataFim(produto.getId())).get(GregorianCalendar.HOUR_OF_DAY));
			
			cbmin.setEnabled(true);
			cbmin.setSelectedItem((leilaoDAO.getDataFim(produto.getId())).get(GregorianCalendar.MINUTE));
			
			if(leilaoDAO.getPrecoActual(produto.getId()) != 0){
				cbdia.setEnabled(false);			
				cbmes.setEnabled(false);			
				cbano.setEnabled(false);			
				cbhora.setEnabled(false);			
				cbmin.setEnabled(false);
			}
		
		
		}else{
			cbdia.setEnabled(false);			
			cbmes.setEnabled(false);			
			cbano.setEnabled(false);			
			cbhora.setEnabled(false);			
			cbmin.setEnabled(false);
		}
		
		JLabel lblDescrio_1 = new JLabel("Descrição:");
		lblDescrio_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		btcim = new JButton("");
		btcim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btbaix.setEnabled(true);
				index -= 1;
				
				jlfotos.setSelectedIndex(index);
				jlfotos.ensureIndexIsVisible(index);
				
				if(index == 0){
					btcim.setEnabled(false);
				}
			}
		});
		btcim.setIcon(new ImageIcon(EditarProduto.class.getResource("/Imagens/setacima.png")));
		btcim.setContentAreaFilled(false);
		btcim.setBorderPainted(false);
		
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
		btbaix.setIcon(new ImageIcon(EditarProduto.class.getResource("/Imagens/setabaixo.png")));
		btbaix.setContentAreaFilled(false);
		btbaix.setBorderPainted(false);
		
		btact = new JButton("");
		btact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updatefotos();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btact.setIcon(new ImageIcon(EditarProduto.class.getResource("/Imagens/actualizar.png")));
		btact.setEnabled(true);
		btact.setContentAreaFilled(false);
		btact.setBorderPainted(false);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfquantidade = new JTextField(produto.getQuantidade() + "");
		tfquantidade.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDescrio)
								.addComponent(lblHoraFinalLeilo))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(cbhora, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cbdia, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
							.addGap(3)
							.addComponent(lblH)
							.addGap(6)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cbmin, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cbmes, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cbano, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(4)
									.addComponent(lblM)))
							.addContainerGap(92, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnsair, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnguardar, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(28))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTipoDeMensagem)
										.addComponent(lblMorada)
										.addComponent(lblQuantidade, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(tfpreco, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(tfquantidade, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 210, Short.MAX_VALUE))
										.addComponent(tfnome, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblFotos)
										.addComponent(lblDescrio_1))
									.addGap(47)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
										.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(btadd, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btrem, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btcim, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btbaix, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btact, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))))
							.addGap(22))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoDeMensagem)
						.addComponent(tfnome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMorada)
						.addComponent(tfpreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(tfquantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addComponent(lblQuantidade)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbdia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbano, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbmes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescrio))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbhora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblH)
								.addComponent(cbmin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblM)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblHoraFinalLeilo)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescrio_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFotos))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btrem, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(btadd, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addComponent(btcim, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(btbaix, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btact, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnguardar)
						.addComponent(btnsair))
					.addGap(32))
		);
		
		jlfotos = new JList();
		scrollPane_1.setViewportView(jlfotos);
		jlfotos.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		updatefotos();
		
		tfdescricao = new JTextArea(produto.getDescricao());
		scrollPane.setViewportView(tfdescricao);
		tfdescricao.setColumns(10);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblSugestoDeTroca = new JLabel("Editar Produto");
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
		updatefotos();
	}
	
	private void updatefotos() throws SQLException{
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
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}	

