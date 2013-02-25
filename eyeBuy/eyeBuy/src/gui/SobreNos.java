/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;

/**
 *
 * @author 41
 */
public class SobreNos extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form Login
     */
    public SobreNos() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        initComponents();
        setVisible(true);
    }


    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setBorder(null);
        jTextPane1 = new javax.swing.JTextPane();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(293, 293));
        setResizable(false);

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(javax.swing.UIManager.getDefaults().getColor("ColorChooser.background"));
        jTextPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sobre Nós"));
        jTextPane1.setFont(new Font("Dialog", Font.PLAIN, 11)); // NOI18N
        jTextPane1.setForeground(new java.awt.Color(240, 240, 240));
        jTextPane1.setText("\nO EyeBuy é um projecto criado e desenvolvido por André Santos, Daniel Araújo, Daniel Carvalho, Helena Alves e Pedro Nunes enquadrado no plano de avaliação da cadeira de Desenvolvimento de Sistemas de Software.\n\nO seu objectivo é simular um sistema de troca e venda de produtos online rápido e de fácil acesso com o menor número possível de complicações para qualquer utilizador.");
        jTextPane1.setCaretColor(new java.awt.Color(255, 0, 0));
        jTextPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextPane1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextPane1.setEnabled(false);
        jTextPane1.setFocusable(false);
        jTextPane1.setHighlighter(null);
        jTextPane1.setOpaque(false);
        jScrollPane1.setViewportView(jTextPane1);

        jLabel4.setIcon(new ImageIcon(SobreNos.class.getResource("/Imagens/insatisfeito.jpg"))); // NOI18N
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Daniel Carvalho"));

        jLabel5.setIcon(new ImageIcon(SobreNos.class.getResource("/Imagens/gerente.jpg"))); // NOI18N
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel5.setBorder(javax.swing.BorderFactory.createTitledBorder("André Santos"));

        jLabel6.setIcon(new ImageIcon(SobreNos.class.getResource("/Imagens/treze.jpg"))); // NOI18N
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Daniel Araújo"));

        jLabel7.setIcon(new ImageIcon(SobreNos.class.getResource("/Imagens/kamikaze.jpg"))); // NOI18N
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Helena Alves"));

        jLabel8.setIcon(new ImageIcon(SobreNos.class.getResource("/Imagens/cenoura.jpg"))); // NOI18N
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedro Nunes"));
        
        JLayeredPane layeredPane = new JLayeredPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(filler2, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel5)
        					.addGap(16)
        					.addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
        			.addGap(51)
        			.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addContainerGap())
        		.addGroup(layout.createSequentialGroup()
        			.addGap(0, 433, Short.MAX_VALUE)
        			.addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(0, 286, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(6)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 166, Short.MAX_VALUE)
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        					.addGap(39))
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.UNRELATED, 22, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(filler2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addGap(81))
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jLabel5)
        								.addComponent(jLabel4)
        								.addComponent(jLabel6)
        								.addComponent(jLabel7)
        								.addComponent(jLabel8))
        							.addContainerGap())))))
        );
        
        JButton label_1 = new JButton("");
        label_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		SobreNos.this.setVisible(false);
        		SobreNos.this.dispose();
        	}
        });
        label_1.setBorderPainted(false);
        label_1.setContentAreaFilled(false);
        label_1.setIcon(new ImageIcon(SobreNos.class.getResource("/Imagens/sair32.png")));
        label_1.setBounds(235, 0, 35, 44);
        layeredPane.add(label_1);
        
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(SobreNos.class.getResource("/Imagens/eyelogo.png")));
        label.setName("logo");
        label.setInheritsPopupMenu(false);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Dialog", Font.PLAIN, 18));
        label.setBounds(0, 0, 270, 166);
        layeredPane.add(label);
        getContentPane().setLayout(layout);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-735)/2, (screenSize.height-335)/2, 743, 407);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SobreNos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SobreNos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SobreNos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SobreNos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SobreNos().setVisible(true);

            }
        });
    }

    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
}