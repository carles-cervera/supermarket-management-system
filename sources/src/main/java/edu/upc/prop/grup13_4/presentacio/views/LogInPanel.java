//LogInPanel
//Author: Max Velasco

package edu.upc.prop.grup13_4.presentacio.views;

import javax.swing.*;

/**
 * @brief Panel per la vista d'iniciar sessió
 *
 * Aquest panell proporciona una interfície gràfica per iniciar sessió, amb camps per al nom d'usuari i la contrasenya,
 * així com opcions per mostrar la contrasenya o anar a la vista per registrar-se.
 */
public class LogInPanel extends JPanel {
    //CLASS ATRIBUTES

    /**
     * @brief Botó per Iniciar Sessió.
     */
    public JButton logInButton;
    /**
     * @brief Botó per fer visible o ocultar la contrasenya.
     */
    public JToggleButton viewPassword;
    /**
     * @brief Etiqueta interactiva per anar a la vista de SignUp.
     */
    public JLabel goToSignUpLabel;
    /**
     * @brief Etiqueta títol per la contrasenya.
     */
    private JLabel passwordLabel;
    /**
     * @brief Etiqueta títol per l'usuari.
     */
    private JLabel userLabel;
    /**
     * @brief Etiqueta que pregunta si no tens compte.
     */
    private JLabel questionLabel;
    /**
     * @brief Títol de l'aplicació FreshMart
     */
    private JLabel tittle;
    /**
     * @brief Icono del logo de FreshMart
     */
    private JLabel logo;
    /**
     * @brief Panel de l'esquerre on estan situats els camps de dades
     */
    private JPanel leftPanel;
    /**
     * @brief Panel de la dreta on està situat el logo de l'aplicació
     */
    private JPanel rightPanel;
    /**
     * @brief Camp de dades per introduïr la contrasenya
     */
    public JPasswordField passwordField;
    /**
     * @brief Camp de dades per introduïr l'usuari
     */
    public JTextField userField;
    /**
     * @brief Separador per separar els dos panels de la dreta i de l'esquerra
     */
    private JSeparator separator1;
    /**
     * @brief Separador per "subrallar" el camp de dades de l'usuari
     */
    private JSeparator separator2;
    /**
     * @brief Separador per "subrallar" el camp de dades de la contrasenya
     */
    private JSeparator separator3;


    //METHODS

    /**
     * @brief Constructora per el LogInPanel.
     *
     * @pre -
     * @post Crea un LogIn panel amb tots els components iniciatlitzats.
     */
    public LogInPanel() {
        initComponents();
    }

    /**
     * @brief Restableix els camps del panell als valors per defecte.
     *
     * @pre Els components gràfics han estat inicialitzats
     * @post La vista s'ha restablert per defecte
     */
    public void reload() {
        passwordField.setText("Introduïu la vostra contrasenya");
        passwordField.setEchoChar('\0');
        userField.setText("Introduïu el vostre nom d'usuari");
        viewPassword.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/Closed_Eye.png")));
    }

    /**
     * @brief Inicialitza els components gràfics del panell.
     *
     * @pre -
     * @post Els components gràfics del panell són inicialitzats i organitzats.
     */
    private void initComponents() {

        leftPanel = new JPanel();
        rightPanel = new JPanel();

        tittle = new JLabel();
        logo = new JLabel();
        userLabel = new JLabel();
        passwordLabel = new JLabel();
        goToSignUpLabel = new JLabel();
        questionLabel = new JLabel();

        userField = new JTextField();

        passwordField = new JPasswordField();

        logInButton = new JButton();
        viewPassword = new JToggleButton();

        separator1 = new JSeparator();
        separator2 = new JSeparator();
        separator3 = new JSeparator();


        leftPanel.setBackground(new java.awt.Color(230, 240, 255));

        passwordLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        passwordLabel.setText("CONTRASENYA:");

        userLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        userLabel.setText("USUARI:");

        userField.setEditable(true);
        userField.setBackground(new java.awt.Color(230, 240, 255));
        userField.setForeground(new java.awt.Color(51, 51, 51));
        userField.setFont(new java.awt.Font("Tahoma", 0, 12));
        userField.setText("Introduïu el vostre nom d'usuari");
        userField.setBorder(null);
        userField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        passwordField.setEditable(true);
        passwordField.setBackground(new java.awt.Color(230, 240, 255));
        passwordField.setFont(new java.awt.Font("Tahoma", 0, 12));
        passwordField.setText("Introduïu la vostra contrasenya");
        passwordField.setBorder(null);
        passwordField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        passwordField.setEchoChar('\0');

        logInButton.setBackground(new java.awt.Color(59, 89, 182));
        logInButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        logInButton.setForeground(new java.awt.Color(255, 255, 255));
        logInButton.setText("Inicia Sessió");
        logInButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        questionLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        questionLabel.setText("No tens compte?");

        goToSignUpLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        goToSignUpLabel.setForeground(new java.awt.Color(59, 69, 255));
        goToSignUpLabel.setText("Registra't");
        goToSignUpLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        separator1.setForeground(new java.awt.Color(0, 0, 0));

        separator2.setForeground(new java.awt.Color(0, 0, 0));

        separator3.setForeground(new java.awt.Color(0, 0, 0));
        separator3.setOrientation(SwingConstants.VERTICAL);

        rightPanel.setBackground(new java.awt.Color(189, 243, 189));

        logo.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/logo.png")));

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
                rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rightPanelLayout.createSequentialGroup()
                                .addComponent(separator3, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45))
        );
        rightPanelLayout.setVerticalGroup(
                rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(separator3)
                        .addGroup(rightPanelLayout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(170, Short.MAX_VALUE))
        );

        tittle.setFont(new java.awt.Font("Tahoma", 1, 18));
        tittle.setText("INICIAR SESSIÓ");

        viewPassword.setBackground(new java.awt.Color(230, 240, 255));
        viewPassword.setBorder(null);
        viewPassword.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/Closed_Eye.png")));

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
                leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(leftPanelLayout.createSequentialGroup()
                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                                                .addGap(0, 122, Short.MAX_VALUE)
                                                .addComponent(questionLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(goToSignUpLabel)
                                                .addGap(133, 133, 133))
                                        .addGroup(leftPanelLayout.createSequentialGroup()
                                                .addGap(62, 62, 62)
                                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(separator2)
                                                        .addComponent(userLabel)
                                                        .addComponent(passwordLabel)
                                                        .addComponent(tittle)
                                                        .addComponent(userField, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                                        .addComponent(separator1)
                                                        .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addComponent(viewPassword)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(138, 138, 138)))
                                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        leftPanelLayout.setVerticalGroup(
                leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(leftPanelLayout.createSequentialGroup()
                                .addContainerGap(75, Short.MAX_VALUE)
                                .addComponent(tittle)
                                .addGap(60, 60, 60)
                                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(passwordLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(viewPassword))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(goToSignUpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(questionLabel))
                                .addGap(30, 30, 30))
                        .addComponent(rightPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(leftPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
}