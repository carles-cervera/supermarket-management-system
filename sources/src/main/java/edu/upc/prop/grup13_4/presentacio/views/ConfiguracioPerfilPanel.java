// ConfiguracioPerfilPanel
// Author: Carles Cervera

package edu.upc.prop.grup13_4.presentacio.views;

import javax.swing.*;
import java.awt.*;

/**
 * @brief Classe que defineix el panell gràfic per a la configuració del perfil d'usuari.
 */
public class ConfiguracioPerfilPanel extends JPanel {
    // ATRIBUTS DE LA CLASSE

    /**
     * @brief Panell superior que conté el títol.
     */
    public JPanel topPanel;

    /**
     * @brief Panell inferior per a botons d'acció.
     */
    private final JPanel bottomPanel;

    /**
     * @brief Panell central per mostrar i gestionar les dades del perfil.
     */
    public JPanel centerPanel;

    /**
     * @brief Panell per mostrar les dades del perfil.
     */
    public JPanel DadesPerfilPanel;

    /**
     * @brief Botó per tancar sessió.
     */
    public JButton SignOut;

    /**
     * @brief Etiqueta per mostrar el nom de l'usuari.
     */
    public JLabel nomLabel;

    /**
     * @brief Etiqueta per mostrar el nom d'usuari.
     */
    public JLabel usernameLabel;

    /**
     * @brief Etiqueta per mostrar la contrasenya.
     */
    public JLabel passwordLabel;

    /**
     * @brief Botó per recordar la contrasenya.
     */
    public JButton remindPasswordButton;

    /**
     * @brief Botó per canviar la contrasenya.
     */
    public JButton changePasswordButton;

    /**
     * @brief Botó per eliminar el perfil.
     */
    public JButton eliminarPerfilButton;

    /**
     * @brief Panell per gestionar la visualització i accions de la contrasenya.
     */
    public JPanel passwordPanel;

    // CONSTRUCTOR

    /**
     * @brief Constructor de la classe ConfiguracioPerfilPanel.
     *
     * @pre -
     * @post Inicialitza els components gràfics del panell.
     */
    public ConfiguracioPerfilPanel() {
        // Inicialització d'atributs
        nomLabel = new JLabel();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel("Contrasenya: ********");
        eliminarPerfilButton = new JButton("Eliminar Perfil");
        topPanel = new JPanel();
        SignOut = new JButton("Sign Out");
        bottomPanel = new JPanel();
        centerPanel = new JPanel();
        passwordPanel = new JPanel();
        DadesPerfilPanel = new JPanel();
        remindPasswordButton = new JButton();
        changePasswordButton = new JButton("Canviar Contrasenya");

        // Inicialització dels panells
        initPanels();
    }

    // MÈTODES

    /**
     * @brief Actualitza les dades del perfil mostrades en el panell.
     *
     * @pre Les dades proporcionades són vàlides.
     * @post Es mostren les noves dades del perfil.
     * @param realname Nom real de l'usuari.
     * @param username Nom d'usuari.
     */
    public void reload(String realname, String username) {
        nomLabel.setText("Nom: " + realname);
        usernameLabel.setText("Usuari: " + username);
    }

    /**
     * @brief Inicialitza els panells i components del menú.
     *
     * @pre -
     * @post Els panells estan configurats i afegits al contenidor principal.
     */
    private void initPanels() {
        // Configuració del panell superior
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("Configuració Perfil");
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        topPanel.add(label);

        // Configuració del botó de recordatori de contrasenya
        remindPasswordButton.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/Closed_Eye.png")));

        // Configuració del panell central
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 5, 20, 5);

        // Etiqueta de dades
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1;
        JLabel dadesLabel = new JLabel("DADES:");
        dadesLabel.setHorizontalAlignment(SwingConstants.LEADING);
        dadesLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        centerPanel.add(dadesLabel, gbc);

        // Nom
        gbc.gridy = 1;
        nomLabel.setHorizontalAlignment(SwingConstants.LEADING);
        nomLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        centerPanel.add(nomLabel, gbc);

        // Usuari
        gbc.gridy++;
        usernameLabel.setHorizontalAlignment(SwingConstants.LEADING);
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        centerPanel.add(usernameLabel, gbc);

        // Contrasenya
        gbc.gridy++;
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordLabel.setHorizontalAlignment(SwingConstants.LEADING);
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordPanel.add(passwordLabel, gbc);
        passwordPanel.add(new JLabel("           "), gbc);
        passwordPanel.add(remindPasswordButton, gbc);
        passwordPanel.add(changePasswordButton, gbc);
        centerPanel.add(passwordPanel, gbc);

        // Espaiador
        gbc.gridy++;
        gbc.weighty = 1;
        JLabel spaceLabel = new JLabel();
        centerPanel.add(spaceLabel, gbc);

        // Configuració del botó d'eliminació del perfil
        eliminarPerfilButton.setBackground(Color.RED);
        eliminarPerfilButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        eliminarPerfilButton.setForeground(Color.WHITE);
        eliminarPerfilButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Configuració del botó de tancament de sessió
        SignOut.setBackground(new Color(59, 89, 182));
        SignOut.setFont(new Font("Tahoma", Font.BOLD, 20));
        SignOut.setForeground(Color.WHITE);
        SignOut.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Configuració del panell inferior
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(SignOut);
        bottomPanel.add(eliminarPerfilButton);

        // Configuració general del panell principal
        setUpPanel();
        setSize(1000, 700);
        setVisible(true);
    }

    /**
     * @brief Configura el panell principal amb els panells internament definits.
     *
     * @pre -
     * @post Els panells es mostren segons la disposició definida.
     */
    private void setUpPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // Afegir el panell superior
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.0;
        topPanel.setPreferredSize(new Dimension(400, 40));
        add(topPanel, gbc);

        // Afegir el panell central
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        add(centerPanel, gbc);

        // Afegir el panell inferior
        gbc.gridy = 4;
        gbc.weighty = 0.0;
        bottomPanel.setPreferredSize(new Dimension(400, 40));
        add(bottomPanel, gbc);
    }
}
