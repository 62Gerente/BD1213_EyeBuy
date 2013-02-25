package gui;

import model.Localidade;
import model.Utilizador;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.border.LineBorder;

import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;

import dao.LocalidadeDAO;
import dao.UtilizadorDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import javax.swing.UIManager;
import javax.swing.JLayeredPane;

public class Login extends JFrame implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfnomeut_email;
	private JPasswordField pwdpasslogin;
	private JTextField tfprimeironome;
	private JTextField tfnomeut;
	private JTextField tfemail;
	private JPasswordField pwdpass;
	private JComboBox cbLocalidade;
	private JComboBox cbDia;
	private JComboBox cbMes;
	private JComboBox cbAno;
	private UtilizadorDAO utilizadorDAO = new UtilizadorDAO();
	private LocalidadeDAO localidadeDAO = new LocalidadeDAO();
	private JLabel lvavisopassue;
	private JLabel lvavisonue;
	private JLabel lvavisop;
	private JLabel lvavisoe;
	private JLabel lvavisol;
	private JLabel lvavisonu;
	private JLabel lvavison;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		utilizadorDAO.addObserver(this);
		localidadeDAO.addObserver(this);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 788);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setOpaque(false);
		contentPane.setFocusCycleRoot(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JLabel lblRegistar = new JLabel("Registar\n");
		lblRegistar.setFont(new Font("Dialog", Font.BOLD, 20));
		
		tfprimeironome = new JTextField();
		tfprimeironome.setColumns(10);
		
		JLabel lblPrimeiroNome = new JLabel("Nome");
		
		JLabel lblNomeDeUtilizador = new JLabel("Nome de utilizador");
		
		tfnomeut = new JTextField();
		tfnomeut.setColumns(10);
		
		JLabel lblEmail = new JLabel("Data de nascimento");
		
		cbDia = new JComboBox();
		cbDia.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		
		cbMes = new JComboBox();
		cbMes.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		
		cbAno = new JComboBox();
		cbAno.setModel(new DefaultComboBoxModel(new String[] {"1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950"}));
		
		JLabel lblEmail_1 = new JLabel("E-mail");
		
		tfemail = new JTextField();
		tfemail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		
		pwdpass = new JPasswordField();
		pwdpass.setText("xxxxxx");
		
		JLabel lblLocalidade = new JLabel("Localidade");
		
		cbLocalidade = new JComboBox();
		try {
			cbLocalidade.setModel(new DefaultComboBoxModel(localidadeDAO.getArrayLocalidades()));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		lvavison = new JLabel("*");
		lvavison.setVisible(false);
		lvavison.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		lvavisonu = new JLabel("*");
		lvavisonu.setVisible(false);
		lvavisonu.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		lvavisol = new JLabel("*");
		lvavisol.setVisible(false);
		lvavisol.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		lvavisoe = new JLabel("*");
		lvavisoe.setVisible(false);
		lvavisoe.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		lvavisop = new JLabel("*");
		lvavisop.setVisible(false);
		lvavisop.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		JButton btnEfectuarRegisto = new JButton("Efectuar Registo");
		btnEfectuarRegisto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lvavisopassue.setVisible(false);
				lvavisonue.setVisible(false);
				lvavisop.setVisible(false);
				lvavisoe.setVisible(false);
				lvavisol.setVisible(false);
				lvavisonu.setVisible(false);
				lvavison.setVisible(false);
				
				String password = new String(pwdpass.getPassword());
				if(tfprimeironome.getText().equals("")
						|| tfemail.getText().equals("")
						|| tfnomeut.getText().equals("")
						|| password.equals("")
						|| cbLocalidade.getSelectedIndex() == -1
						|| password.equals("xxxxxx")){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.WARNING_MESSAGE );
					
					if(tfprimeironome.getText().equals("")){
						lvavison.setVisible(true);
					}
					if(tfemail.getText().equals("")){
						lvavisoe.setVisible(true);
					}
					if(tfnomeut.getText().equals("")){
						lvavisonu.setVisible(true);
					}
					if(password.equals("") || password.equals("xxxxxx")){
						lvavisop.setVisible(true);
					}
					if(cbLocalidade.getSelectedIndex() == -1){
						lvavisol.setVisible(true);
					}
				} else if(!password.equals(new String (passwordField.getPassword()))){
					lvavisop.setVisible(true);
					JOptionPane.showMessageDialog(null, "Password tem de coincidir", "Erro", JOptionPane.WARNING_MESSAGE );
					
				} else {
					try {
						if(utilizadorDAO.verificaExistenciaNomeUtilizador(tfnomeut.getText())){
							JOptionPane.showMessageDialog(null, "Nome de utilizador já está a ser utilizado", "Erro", JOptionPane.WARNING_MESSAGE );
							lvavisonu.setVisible(true);
						}
						else if (utilizadorDAO.verificaExistenciaEmail(tfemail.getText())){
							JOptionPane.showMessageDialog(null, "Email já está a ser utilizado", "Erro", JOptionPane.WARNING_MESSAGE );
							lvavisoe.setVisible(true);
						}
						else if (pwdpass.getPassword().length < 5){
							JOptionPane.showMessageDialog(null, "Insira uma password com mais de 5 caracteres", "Erro", JOptionPane.WARNING_MESSAGE );
							lvavisop.setVisible(true);
						}
						else{
							Utilizador newuser = new Utilizador(tfnomeut.getText(), tfprimeironome.getText(), 
									tfemail.getText(), new String(pwdpass.getPassword()), 
									new GregorianCalendar(1994 - cbAno.getSelectedIndex(), 
									cbMes.getSelectedIndex() +1 , 
									cbDia.getSelectedIndex() +1 ), 
									new Localidade((String)cbLocalidade.getSelectedItem()));
							
							try {
								if(utilizadorDAO.novoUtilizador(newuser)){
									new Homev3(newuser);
									Login.this.setVisible(false);
									Login.this.dispose();
								}
								else{
									JOptionPane.showMessageDialog(null, "Occoreu um erro, tente novamente", "Erro", JOptionPane.WARNING_MESSAGE );
									
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		passwordField = new JPasswordField();
		passwordField.setText("xxxxxx");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(cbLocalidade, 0, 263, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblRegistar, Alignment.LEADING)
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 91, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(cbDia, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cbMes, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cbAno, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 64, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(lblEmail_1, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
									.addComponent(lvavisoe, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(lblPassword)
									.addPreferredGap(ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
									.addComponent(lvavisop, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(lblNomeDeUtilizador, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
									.addComponent(lvavisonu, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(tfprimeironome, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
									.addGap(13))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(lblPrimeiroNome, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
									.addGap(58)
									.addComponent(lvavison, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(pwdpass, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
									.addGap(13))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(tfemail, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
									.addGap(12))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(lblLocalidade)
									.addPreferredGap(ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
									.addComponent(lvavisol, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(tfnomeut, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
									.addGap(13))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
									.addGap(13)))
							.addGap(0))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnEfectuarRegisto, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
							.addGap(13))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRegistar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrimeiroNome)
						.addComponent(lvavison))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tfprimeironome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomeDeUtilizador)
						.addComponent(lvavisonu))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tfnomeut, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblEmail)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLocalidade)
							.addGap(10))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lvavisol)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbLocalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail_1)
						.addComponent(lvavisoe))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tfemail, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(lvavisop))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pwdpass, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEfectuarRegisto, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(46))
		);
		
		JPopupMenu popano = new JPopupMenu();
		addPopup(cbAno, popano);
		
		JPopupMenu popmes = new JPopupMenu();
		addPopup(cbMes, popmes);
		
		JPopupMenu popdia = new JPopupMenu();
		addPopup(cbDia, popdia);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
						.addComponent(panel, 0, 0, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 439, Short.MAX_VALUE)))
					.addGap(39))
		);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Imagens/eyelogo.png")));
		
		JButton btnTakeATour = new JButton("Take a tour\n");
		
		JLabel lblOEyebuy = new JLabel("<html>\n<center>\nTroque o que tem pelo que precisa!\n<p>\n<p> \nSeja bem vindo ao EyeBuy!\n</p> \n<p>\n<p> \nSe ainda não faz parte da nossa comunidade do que está à espera? O nosso registo é fácil, rápido e gratuito.\n</center>\n</p>\n</html>\n");
		lblOEyebuy.setFont(new Font("Dialog", Font.BOLD, 15));
		lblOEyebuy.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnajuda = new JButton("Ajuda\n");
		btnajuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Ajuda();
			}
		});
		btnajuda.setFont(new Font("Dialog", Font.BOLD, 9));
		
		JButton btnSobreOEyebuy = new JButton("Sobre o eyeBuy");
		btnSobreOEyebuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SobreNos();
			}
		});
		btnSobreOEyebuy.setFont(new Font("Dialog", Font.BOLD, 9));
		
		JLayeredPane layeredPane = new JLayeredPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(181)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnajuda, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSobreOEyebuy, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
						.addComponent(btnTakeATour, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
					.addGap(177))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(148, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
					.addGap(136))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(89)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(layeredPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
						.addComponent(lblOEyebuy, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE))
					.addGap(85))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 126, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOEyebuy, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnTakeATour, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnajuda, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSobreOEyebuy, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
				URL url = null;
				try {
					url = new URL("https://dl.dropbox.com/u/13351850/0001.mpg");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					desktop.browse(url.toURI());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setIcon(new ImageIcon(Login.class.getResource("/Imagens/video.png")));
		btnNewButton_2.setBounds(432, 0, 75, 76);
		layeredPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_1.setBounds(0, 0, 507, 259);
		layeredPane.add(btnNewButton_1);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
				URL url = null;
				try {
					url = new URL("https://dl.dropbox.com/u/13351850/0001.mpg");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					desktop.browse(url.toURI());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Login.class.getResource("/Imagens/videologin.png")));
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblIniciarSesso = new JLabel("Iniciar Sessão");
		lblIniciarSesso.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JLabel lblIntroduzaNomeUtilizador = new JLabel("Nome utilizador ou e-mail");
		lblIntroduzaNomeUtilizador.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfnomeut_email = new JTextField();
		tfnomeut_email.setColumns(10);
		
		JLabel lblIntroduzaPassorwd = new JLabel("Password");
		
		pwdpasslogin = new JPasswordField();
		pwdpasslogin.setText("xxxxxx");
		
		JButton btnNewButton = new JButton("Esqueci-me password ou nome de utilizador");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnNewButton.setToolTipText("");
		
		lvavisonue = new JLabel("*");
		lvavisonue.setVisible(false);
		lvavisonue.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		lvavisopassue = new JLabel("*");
		lvavisopassue.setVisible(false);
		lvavisopassue.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.this.lvavisopassue.setVisible(false);
				Login.this.lvavisonue.setVisible(false);
				Login.this.lvavisop.setVisible(false);
				Login.this.lvavisoe.setVisible(false);
				Login.this.lvavisol.setVisible(false);
				Login.this.lvavisonu.setVisible(false);
				Login.this.lvavison.setVisible(false);
				
				String password = new String(pwdpasslogin.getPassword());
				
				if(tfnomeut_email.getText().equals("") || password.equals("") || 
						password.equals("xxxxxx")){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.WARNING_MESSAGE );
					
					if(tfnomeut_email.getText().equals("")){
						Login.this.lvavisonue.setVisible(true);
					}
					if(password.equals("") || password.equals("xxxxxx")){
						Login.this.lvavisopassue.setVisible(true);
					}
				}
				else{
					Utilizador res = null;
					try {
						res = utilizadorDAO.verificaLogin(tfnomeut_email.getText(),new String(pwdpasslogin.getPassword()));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					if(res != null){
						try {
							new Homev3(res);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Login.this.setVisible(false);
						Login.this.dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "Dados inválidos", "Erro", JOptionPane.WARNING_MESSAGE );
						Login.this.lvavisonue.setVisible(true);
						Login.this.lvavisopassue.setVisible(true);
					}
				}
			}
		});
		

		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(tfnomeut_email, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
						.addComponent(btnEntrar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
						.addComponent(pwdpasslogin, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(lblIntroduzaPassorwd)
							.addPreferredGap(ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
							.addComponent(lvavisopassue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(lblIntroduzaNomeUtilizador)
							.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
							.addComponent(lvavisonue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblIniciarSesso))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIniciarSesso)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIntroduzaNomeUtilizador)
						.addComponent(lvavisonue, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tfnomeut_email, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIntroduzaPassorwd)
						.addComponent(lvavisopassue))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pwdpasslogin, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEntrar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		panel_4.setLayout(gl_panel_4);
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
	}
}
