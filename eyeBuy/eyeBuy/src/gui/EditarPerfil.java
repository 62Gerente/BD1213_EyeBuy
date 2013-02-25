package gui;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.sql.rowset.serial.SerialException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;

import model.EyeBuy;
import model.Imagem;
import model.Localidade;
import model.Utilizador;
import javax.swing.JScrollPane;

import dao.LocalidadeDAO;
import dao.UtilizadorDAO;
import gui.JFileChooserPreview.PreviewPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.UIManager;


public class EditarPerfil extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfnome;
	private JTextField tfmorada;
	private JTextField tfcodigopostal;
	private JTextField tfemail;
	private JTextField tftelemovel;
	private JTextArea tfdescricao;
	private JTextField tfpaypal;
	private JTextField tfmbnet;
	private JPasswordField tfantigapass;
	private JPasswordField tfnovapass;
	
	private LocalidadeDAO localidadeDAO = new LocalidadeDAO();
	private Utilizador utilizador = new Utilizador();
	private Imagem imagem;
	private EyeBuy eyeBuy = new EyeBuy();
	private UtilizadorDAO utilizadorDAO = new UtilizadorDAO();
	
	
	private PerfilUtilizador pai = null;
	
	private JComboBox cblocalidade = new JComboBox();
	private JLabel foto = new JLabel();
	
	private JButton btnsair	 = new JButton();
	private JButton btnmudarfoto = new JButton();
	
	private JLabel avisonome = new JLabel();
	private JLabel avisoemail = new JLabel();
	private JLabel avisoantigapass = new JLabel();
	private JLabel avisonovapass = new JLabel();
	private JLabel avisolocalidade = new JLabel();
	private JLabel avisopaypal = new JLabel();
	private JLabel avisombnet = new JLabel();
	private JLabel avisotelemovel = new JLabel();
	private JLabel avisodesc = new JLabel();
	private JLabel avisocp = new JLabel();
	private JLabel avisomorada = new JLabel();
	private JPasswordField passwordField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarPerfil frame = new EditarPerfil(new Utilizador(),null);
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
	public EditarPerfil(Utilizador user, PerfilUtilizador pu) throws SQLException {
		localidadeDAO.addObserver(this);
		utilizador.addObserver(this);
		eyeBuy.addObserver(this);
		utilizadorDAO.addObserver(this);
		
		pai = pu;
		
		if(user != null){
			this.utilizador = user.clone();
			utilizador.addObserver(this);
		}
		
		imagem = new Imagem(utilizador.getNomeUtilizador(), utilizador.getImagem());
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 576);
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
		
		foto = new JLabel("");
		foto.setIcon(new ImageIcon(EditarPerfil.class.getResource("/Imagens/user1.png")));
		
		if (utilizador != null){
			Blob blobmv_2 = utilizador.getImagem();
			if (blobmv_2 != null){ 
				foto.setIcon(new ImageIcon(blobmv_2.getBytes(1, (int)blobmv_2.length())));
			}
		}
		
		btnmudarfoto = new JButton("");
		btnmudarfoto.addActionListener(new ActionListener() {
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
				int option = chooser.showDialog(EditarPerfil.this, "Selecionar Imagem");
				
				if(option == JFileChooser.APPROVE_OPTION){
					file = chooser.getSelectedFile();
					
					if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")) {
						
						Imagem img;
						try {
							img = new Imagem(file.getName(), eyeBuy.novaImagemUtilizador(file));
							Blob blobmv_2 = img.getImagem();
							if (blobmv_2 != null){ 
								foto.setIcon(new ImageIcon(blobmv_2.getBytes(1, (int)blobmv_2.length())));
								imagem = img.clone();
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
		btnmudarfoto.setIcon(new ImageIcon(EditarPerfil.class.getResource("/Imagens/mudarfoto.png")));
		btnmudarfoto.setContentAreaFilled(false);
		btnmudarfoto.setBorderPainted(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, 0, 0, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnmudarfoto, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(foto, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(foto, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnmudarfoto, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		JLabel lblTipoDeMensagem = new JLabel("Nome:");
		lblTipoDeMensagem.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfnome = new JTextField(utilizador.getNome());
		tfnome.setColumns(10);
		
		JLabel lblMorada = new JLabel("Morada:");
		lblMorada.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfmorada = new JTextField(utilizador.getMorada());
		tfmorada.setColumns(10);
		
		JLabel lblCdigopostal = new JLabel("Código-postal:");
		lblCdigopostal.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfcodigopostal = new JTextField(utilizador.getCodigoPostal());
		tfcodigopostal.setColumns(10);
		
		JLabel lblLocalidade = new JLabel("Localidade:");
		lblLocalidade.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfemail = new JTextField(utilizador.getEmail());
		tfemail.setColumns(10);
		
		JLabel lblTelemvel = new JLabel("Telemóvel:");
		lblTelemvel.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tftelemovel = new JTextField(utilizador.getTelemovel());
		tftelemovel.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblContaPaypal = new JLabel("Conta PayPal:\n");
		lblContaPaypal.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfpaypal = new JTextField(utilizador.getContaPaypal());
		tfpaypal.setColumns(10);
		
		JLabel lblContaMbnet = new JLabel("Conta MBNet:\n");
		lblContaMbnet.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfmbnet = new JTextField(utilizador.getContaMBNet());
		tfmbnet.setColumns(10);
		
		JLabel lblAntigaPassword = new JLabel("Password Actual");
		lblAntigaPassword.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfantigapass = new JPasswordField();
		tfantigapass.setColumns(10);
		
		JLabel lblNovaPassword = new JLabel("Nova password (Opcional) :");
		lblNovaPassword.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		tfnovapass = new JPasswordField();
		tfnovapass.setColumns(10);
		
		JButton btnguardar = new JButton("");
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avisoantigapass.setVisible(false);
				avisocp.setVisible(false);
				avisodesc.setVisible(false);
				avisoemail.setVisible(false);
				avisolocalidade.setVisible(false);
				avisombnet.setVisible(false);
				avisomorada.setVisible(false);
				avisonome.setVisible(false);
				avisonovapass.setVisible(false);
				avisopaypal.setVisible(false);
				avisotelemovel.setVisible(false);
				
				
				if(tfnome.getText().equals("") ||
						tfantigapass.getPassword().length == 0 ||
						tfcodigopostal.getText().equals("") ||
						tfdescricao.getText().equals("") ||
						tfemail.getText().equals("") ||
						tfmbnet.getText().equals("") ||
						tfmorada.getText().equals("") ||
						tfpaypal.getText().equals("") ||
						tftelemovel.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.WARNING_MESSAGE );
					
					if(tfnome.getText().equals("")){
						avisonome.setVisible(true);
					}
					if(tfantigapass.getPassword().length == 0){
						avisoantigapass.setVisible(true);
					}
					if(tfcodigopostal.getText().equals("")){
						avisocp.setVisible(true);
					}
					if(tfdescricao.getText().equals("")){
						avisodesc.setVisible(true);
					}
					if(tfemail.getText().equals("")){
						avisoemail.setVisible(true);
					}
					if(tfmbnet.getText().equals("") || tfpaypal.getText().equals("")){
						avisombnet.setVisible(true);
						avisopaypal.setVisible(false);
					}
					if(tfmorada.getText().equals("")){
						avisomorada.setVisible(true);
					}
					if(tftelemovel.getText().equals("")){
						avisotelemovel.setVisible(true);
					}
					
				}else{
					try {
						if(!tfemail.getText().equals(utilizador.getEmail())){
							if(utilizadorDAO.verificaExistenciaEmail(tfemail.getText())){
								JOptionPane.showMessageDialog(null, "Email já está a ser utilizado", "Erro", JOptionPane.WARNING_MESSAGE );
								avisoemail.setVisible(true);
							}
						}else if(tfnovapass.getPassword().length != 0 && (tfnovapass.getPassword().length < 5)){
								JOptionPane.showMessageDialog(null, "Insira uma nova password com mais de 5 caracteres", "Erro", JOptionPane.WARNING_MESSAGE );
								avisonovapass.setVisible(true);
						}else if(tfnovapass.getPassword().length != 0 && (!tfnovapass.getPassword().equals(passwordField.getPassword()))){
							JOptionPane.showMessageDialog(null, "Nova password não coincide", "Erro", JOptionPane.WARNING_MESSAGE );
							avisonovapass.setVisible(true);
						} else if(utilizadorDAO.verificaLogin(utilizador.getNomeUtilizador(), new String(tfantigapass.getPassword())) == null){
							JOptionPane.showMessageDialog(null, "Password inválida", "Erro", JOptionPane.WARNING_MESSAGE );
							avisoantigapass.setVisible(true);
						} else {
							
							String password;
							
							if(tfnovapass.getPassword().length != 0){
								password = new String(tfnovapass.getPassword());
							}else{
								password = utilizador.getPassword();
							}
							
							Utilizador newuser = new Utilizador(utilizador.getNomeUtilizador(), 
									tfnome.getText(), tfemail.getText(), 
									password, tfmorada.getText(), tftelemovel.getText(), 
									tfcodigopostal.getText(), utilizador.getDataNascimento(), 
									tfpaypal.getText(), tfmbnet.getText(), 
									utilizador.getAvaliacaoComprador(), 
									imagem.getImagem(), tfdescricao.getText(), 
									utilizador.getNrVendas(), utilizador.getNrCompras(), 
									utilizador.getTotalGasto(), utilizador.getTotalGanho(), 
									utilizador.getTotalAVender(), utilizador.getAvaliacoesPositivas(), 
									utilizador.getAvaliacoesNegativas(), utilizador.getDataRegisto(),
									new Localidade((String)cblocalidade.getSelectedItem()), 
									utilizador.getAvaliacaoVendedor(), utilizador.getNrAvaliacoesVendaNeg(), 
									utilizador.getNrAvaliacoesVendaPos(), true, true);
							
							utilizadorDAO.editaUtilizador(newuser.clone());
							
							
							
							if (pai!=null)
								pai.update();
							
							EditarPerfil.this.setVisible(false);
							EditarPerfil.this.dispose();
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				}
				
				
			}
		});
		btnguardar.setIcon(new ImageIcon(EditarPerfil.class.getResource("/Imagens/guardar32.png")));
		btnguardar.setBorderPainted(false);
		btnguardar.setContentAreaFilled(false);
		
		cblocalidade = new JComboBox();
		cblocalidade.setModel(new DefaultComboBoxModel(localidadeDAO.getArrayLocalidades()));
		
		cblocalidade.setSelectedItem(utilizador.getLocalidade().getNome());
		
		btnsair = new JButton("");
		btnsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende cancelar?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					EditarPerfil.this.setVisible(false);
					EditarPerfil.this.dispose();
				}
			}
		});
		btnsair.setIcon(new ImageIcon(EditarPerfil.class.getResource("/Imagens/sair32.png")));
		btnsair.setContentAreaFilled(false);
		btnsair.setBorderPainted(false);
		
		JScrollPane scrollPane = new JScrollPane();
		
		avisonome = new JLabel("*");
		avisonome.setVisible(false);
		avisonome.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		avisomorada = new JLabel("*");
		avisomorada.setVisible(false);
		avisomorada.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		avisocp = new JLabel("*");
		avisocp.setVisible(false);
		avisocp.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		avisolocalidade = new JLabel("*");
		avisolocalidade.setVisible(false);
		avisolocalidade.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		avisoemail = new JLabel("*");
		avisoemail.setVisible(false);
		avisoemail.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		avisotelemovel = new JLabel("*");
		avisotelemovel.setVisible(false);
		avisotelemovel.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		avisodesc = new JLabel("*");
		avisodesc.setVisible(false);
		avisodesc.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		avisopaypal = new JLabel("*");
		avisopaypal.setVisible(false);
		avisopaypal.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		avisombnet = new JLabel("*");
		avisombnet.setVisible(false);
		avisombnet.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		avisoantigapass = new JLabel("*");
		avisoantigapass.setVisible(false);
		avisoantigapass.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		avisonovapass = new JLabel("*");
		avisonovapass.setVisible(false);
		avisonovapass.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(192)
							.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addComponent(lblContaMbnet, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfmbnet, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addComponent(lblContaPaypal, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfpaypal, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
						.addComponent(lblTipoDeMensagem, Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addComponent(lblTelemvel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tftelemovel, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
							.addGap(105))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfemail, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMorada)
								.addComponent(lblCdigopostal, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLocalidade, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(tfcodigopostal, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addComponent(cblocalidade, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfmorada)
								.addComponent(tfnome, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addComponent(lblDescrio, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addComponent(lblNovaPassword, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfnovapass, 0, 0, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addComponent(lblAntigaPassword)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfantigapass, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(avisoantigapass)
							.addGap(19)
							.addComponent(btnsair, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnguardar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addGap(1)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addComponent(avisonome, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
							.addComponent(avisomorada, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
							.addComponent(avisocp, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
							.addComponent(avisolocalidade, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
							.addComponent(avisoemail, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
							.addComponent(avisotelemovel, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
							.addComponent(avisodesc, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
							.addComponent(avisopaypal, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE))
						.addComponent(avisombnet, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
						.addComponent(avisonovapass, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE))
					.addGap(8))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoDeMensagem)
						.addComponent(tfnome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(avisonome))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMorada)
						.addComponent(tfmorada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(avisomorada))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCdigopostal)
						.addComponent(tfcodigopostal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(avisocp))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLocalidade)
						.addComponent(cblocalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(avisolocalidade))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(tfemail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(avisoemail))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelemvel)
						.addComponent(tftelemovel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(avisotelemovel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDescrio)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblContaPaypal)
								.addComponent(tfpaypal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(avisopaypal))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblContaMbnet)
								.addComponent(tfmbnet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(avisombnet))
							.addGap(30)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNovaPassword)
								.addComponent(tfnovapass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(avisonovapass)))
						.addComponent(avisodesc))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnsair, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfantigapass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAntigaPassword)
								.addComponent(avisoantigapass)))
						.addComponent(btnguardar, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		tfdescricao = new JTextArea(utilizador.getDescricao());
		scrollPane.setViewportView(tfdescricao);
		tfdescricao.setColumns(10);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(cblocalidade, popupMenu);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblSugestoDeTroca = new JLabel("Editar Perfil");
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
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
