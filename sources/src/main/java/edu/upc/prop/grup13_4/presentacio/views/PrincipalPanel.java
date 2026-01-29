package edu.upc.prop.grup13_4.presentacio.views;
import javax.swing.*;

/**
 * @brief Panel que afegeix navegabilitat entre la resta.
 */
public class PrincipalPanel extends JPanel {
    //CLASS ATRIBUTES
    /**
     * @brief Títol.
     */
    private JLabel jLabel1;
    /**
     * @brief Etiqueta per anar al menú d'inventaris.
     */
    public JLabel InvLabel;
    /**
     * @brief Etiqueta per anar al panel de benvinguda.
     */
    public JLabel WelcomeLabel;
    /**
     * @brief Etiqueta per anar a la configuració del perfil.
     */
    public JLabel PerfilLabel;
    /**
     * @brief Etiqueta per anar al menú de distribucions.
     */
    public JLabel DistroLabel;
    /**
     * @brief Icona del menú de benvinguda.
     */
    private JLabel home_icon;
    /**
     * @brief Icona del menú d'inventaris.
     */
    private JLabel stock_icon;
    /**
     * @brief Icona de la configuració del perfil.
     */
    private JLabel profile_icon;
    /**
     * @brief Icona del menú de distribucions.
     */
    private JLabel distr_icon;
    /**
     * @brief Panel de color diferent al background principal, on es posen les icones.
     */
    private JPanel jPanel1;
    /**
     * @brief Separador que delimita les seccions del panel.
     */
    private JSeparator jSeparator1;
    //METHODS
    /**
     * @brief Constructora de la classe.
     */
    public PrincipalPanel() {
        initComponents();
    }

    /**
     * @brief Configura els components gràfics del PrincipalPanel.
     * @pre Components del panel no inicialitzats.
     * @post Tots els components organitzats i alineats en el panel.
     */
    private void initComponents() {

        jLabel1 = new JLabel();
        jSeparator1 = new JSeparator();
        jPanel1 = new JPanel();
        InvLabel = new JLabel();
        WelcomeLabel = new JLabel();
        PerfilLabel = new JLabel();
        DistroLabel = new JLabel();
        home_icon = new JLabel();
        stock_icon = new JLabel();
        profile_icon = new JLabel();
        distr_icon = new JLabel();

        setBackground(new java.awt.Color(30, 130, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FreshMart");
        jSeparator1.setEnabled(false);
        jPanel1.setBackground(new java.awt.Color(90, 205, 60));

        InvLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        InvLabel.setForeground(new java.awt.Color(255, 255, 255));
        InvLabel.setText("Inventaris");
        InvLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        WelcomeLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        WelcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        WelcomeLabel.setText("Benvinguda");
        WelcomeLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        PerfilLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        PerfilLabel.setForeground(new java.awt.Color(255, 255, 255));
        PerfilLabel.setText("Perfil");
        PerfilLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        DistroLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        DistroLabel.setForeground(new java.awt.Color(255, 255, 255));
        DistroLabel.setText("Distribucions");
        DistroLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        home_icon.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/CasaIcon.png")));
        stock_icon.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/StockIcon.png")));
        profile_icon.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/PerfilIcon.png")));
        distr_icon.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/DistIcon.png")));
        
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(21, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(profile_icon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10))
                                        .addComponent(distr_icon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(home_icon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stock_icon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(InvLabel)
                                        .addComponent(WelcomeLabel)
                                        .addComponent(DistroLabel)
                                        .addComponent(PerfilLabel))
                                .addGap(67, 67, 67))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(WelcomeLabel, GroupLayout.Alignment.TRAILING)
                                        .addComponent(stock_icon, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(InvLabel)
                                        .addComponent(home_icon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(distr_icon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(DistroLabel))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(PerfilLabel, GroupLayout.Alignment.TRAILING)
                                        .addComponent(profile_icon, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(61, 61, 61)
                                                .addComponent(jLabel1)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(177, Short.MAX_VALUE))
        );
    }
}
