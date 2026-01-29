//SignUpPanel
//Author: Max Velasco

package edu.upc.prop.grup13_4.presentacio.views;

import javax.swing.*;
import java.awt.*;

/**
 * @brief Panel per la vista de Registrar-se
 *
 * Aquest panell proporciona una interfície gràfica per registrar-se, amb camps per al nom real, nom d'usuari i la contrasenya,
 * així com opcions per mostrar la contrasenya o anar a la vista per iniciar sessió.
 */
public class SignUpPanel extends JPanel {
    //CLASS ATRIBUTES

    /**
     * @brief Botó per registrar-se
     */
    public JButton signUpButton;
    /**
     * @brief Botó per fer visible o ocultar la contrasenya
     */
    public JToggleButton viewPassword;
    /**
     * @brief Etiqueta interactiva per anar a la vista de LogIn
     */
    public JLabel goToLogInLabel;
    /**
     * @brief Etiqueta títol per la contrasenya
     */
    private JLabel passwordLabel;
    /**
     * @brief Etiqueta títol per l'usuari
     */
    private JLabel userLabel;
    /**
     * @brief Etiqueta que pregunta si ja tens un compte creat.
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
     * @brief Etiqueta títol pel nom
     */
    private JLabel nameLabel;
    /**
     * @brief Panel de l'esquerre on està situat els camps de dades
     */
    private JPanel leftPanel;
    /**
     * @brief Panel de la dreta on està situat el logo de l'aplicació
     */
    private JPanel rightPanel;
    /**
     * @brief Camp de dades per l'usuari
     */
    public JTextField userField;
    /**
     * @brief Camp de dades pel nom
     */
    public JTextField nameField;
    /**
     * @brief Camp de dades per la contrasenya
     */
    public JPasswordField passwordField;
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
    /**
     * @brief Separador per "subrallar" el camp de dades del nom
     */
    private JSeparator separator4;

    //METHODS

    /**
     * @brief Constructora per el SignUpPanel.
     *
     * @pre -
     * @post Crea un SignUp panel amb tots els components iniciatlitzats.
     */
    public SignUpPanel() {
        initComponents();
    }

    /**
     * @brief Restableix els camps del panell als valors per defecte.
     *
     * @pre Els components gràfics han estat inicialitzats
     * @post La vista s'ha restablert per defecte
     */
    public void reload() {
        nameField.setText("Introduïu el vostre nom real");
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
        nameLabel = new JLabel();
        passwordLabel = new JLabel();
        goToLogInLabel = new JLabel();
        questionLabel = new JLabel();

        nameField = new JTextField();
        userField = new JTextField();
        passwordField = new JPasswordField();

        signUpButton = new JButton();

        viewPassword = new JToggleButton();

        separator1 = new JSeparator();
        separator2 = new JSeparator();
        separator3 = new JSeparator();
        separator4 = new JSeparator();

        leftPanel.setBackground(new java.awt.Color(230, 240, 255));

        passwordLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
        passwordLabel.setText("CONTRASENYA:");

        userLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
        userLabel.setText("USUARI:");

        nameField.setEditable(true);
        nameField.setBackground(new java.awt.Color(230, 240, 255));
        nameField.setForeground(new java.awt.Color(51, 51, 51));
        nameField.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 12));
        nameField.setText("Introduïu el vostre nom real");
        nameField.setBorder(null);
        nameField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        userField.setEditable(true);
        userField.setBackground(new java.awt.Color(230, 240, 255));
        userField.setForeground(new java.awt.Color(51, 51, 51));
        userField.setFont(new java.awt.Font("Tahoma", 0, 12));
        userField.setText("Introduïu el vostre nom d'usuari");
        userField.setBorder(null);
        userField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        signUpButton.setBackground(new java.awt.Color(59, 89, 182));
        signUpButton.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
        signUpButton.setForeground(new java.awt.Color(255, 255, 255));
        signUpButton.setText("Registrar-se");
        signUpButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        questionLabel.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 12));
        questionLabel.setText("Ja tens compte?");

        goToLogInLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        goToLogInLabel.setForeground(new java.awt.Color(59, 69, 255));
        goToLogInLabel.setText("Inicia Sessió");
        goToLogInLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 45, Short.MAX_VALUE))
        );
        rightPanelLayout.setVerticalGroup(
                rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(separator3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(rightPanelLayout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        passwordField.setEditable(true);
        passwordField.setBackground(new java.awt.Color(230, 240, 255));
        passwordField.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 12));
        passwordField.setText("Introduïu la vostra contrasenya");
        passwordField.setBorder(null);
        passwordField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        tittle.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18));
        tittle.setText("REGISTRAR-SE");

        viewPassword.setBackground(new java.awt.Color(230, 240, 255));
        viewPassword.setBorder(null);
        viewPassword.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/Closed_Eye.png")));

        nameLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
        nameLabel.setText("NOM:");

        separator4.setForeground(new java.awt.Color(0, 0, 0));


        separator4.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
                leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(leftPanelLayout.createSequentialGroup()
                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                                                                .addComponent(questionLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(goToLogInLabel)
                                                                .addGap(179, 179, 179))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                                                                .addComponent(signUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(193, 193, 193))))
                                        .addGroup(leftPanelLayout.createSequentialGroup()
                                                .addGap(62, 62, 62)
                                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                                                                .addComponent(userField)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                        .addGroup(leftPanelLayout.createSequentialGroup()
                                                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(separator4)
                                                                        .addGroup(leftPanelLayout.createSequentialGroup()
                                                                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(userLabel)
                                                                                        .addComponent(passwordLabel)
                                                                                        .addComponent(tittle)
                                                                                        .addComponent(nameLabel))
                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                        .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
                                                                .addGap(127, 127, 127))
                                                        .addGroup(leftPanelLayout.createSequentialGroup()
                                                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(separator1, javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(separator2, javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(passwordField, javax.swing.GroupLayout.Alignment.LEADING))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(viewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(79, 79, 79)))))
                                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        leftPanelLayout.setVerticalGroup(
                leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(leftPanelLayout.createSequentialGroup()
                                .addContainerGap(86, Short.MAX_VALUE)
                                .addComponent(tittle)
                                .addGap(54, 54, 54)
                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(passwordLabel)
                                .addGap(8, 8, 8)
                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(leftPanelLayout.createSequentialGroup()
                                                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(separator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(viewPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(signUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(questionLabel)
                                        .addComponent(goToLogInLabel))
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
