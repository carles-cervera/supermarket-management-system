// MenuDistribucionPanel
// Author: Carles Cervera

package edu.upc.prop.grup13_4.presentacio.views;

import edu.upc.prop.grup13_4.presentacio.assets.AcceptButton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @brief Classe que defineix el panell gràfic per gestionar el menú de distribucions.
 */
public class MenuDistribucionPanel extends JPanel {
    // ATRIBUTS DE LA CLASSE

    /**
     * @brief Panell superior que conté el títol.
     */
    private JPanel topPanel;

    /**
     * @brief Panell central que conté les distribucions i la informació de cada una.
     */
    private JPanel centerPanel;

    /**
     * @brief Panell inferior per a botons d'acció.
     */
    private JPanel bottomPanel;

    /**
     * @brief Identificador de la distribució seleccionada.
     */
    public String idDisribucio;

    /**
     * @brief Llista de distribucions disponibles.
     */
    public JList<String> distributionsList;

    /**
     * @brief ScrollPane que conté la llista de distribucions.
     */
    private JScrollPane distributionsScrollPane;

    /**
     * @brief Panell que mostra la distribució seleccionada.
     */
    public DistributionPanel distributionPanel;

    /**
     * @brief Botó per veure la distribució seleccionada.
     */
    public JButton veureButton = new AcceptButton("Veure distribució");

    /**
     * @brief Llista de dades de la distribució seleccionada.
     */
    public List<List<String>> distribucio;

    /**
     * @brief Etiqueta per mostrar informació de la distribució seleccionada.
     */
    private JLabel distribucioLabel;

    /**
     * @brief Model de dades per a la llista de distribucions.
     */
    public DefaultListModel<String> listModel = new DefaultListModel<>();

    /**
     * @brief Botó per importar una distribució.
     */
    public JButton importar = new JButton("Importar");

    // CONSTRUCTOR

    /**
     * @brief Constructor de la classe MenuDistribucionPanel.
     *
     * @pre -
     * @post Inicialitza els components gràfics del panell.
     */
    public MenuDistribucionPanel() {
        topPanel = new JPanel();
        centerPanel = new JPanel();
        bottomPanel = new JPanel();
        distributionsList = new JList<>(listModel);
        distributionsScrollPane = new JScrollPane(distributionsList);
        distributionPanel = new DistributionPanel();
        distribucioLabel = new JLabel();
        initPanels();
    }

    // MÈTODES

    /**
     * @brief Actualitza el contingut del menú de distribucions amb noves dades.
     *
     * @pre Les dades proporcionades són vàlides.
     * @post Es mostra la llista actualitzada de distribucions.
     * @param idDistribucions Array d'identificadors de distribucions.
     */
    public void reload(String[] idDistribucions) {
        distributionsList.setListData(idDistribucions);
        distributionPanel.defaultDistributionPanel();
        distribucioLabel.setText("");
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     * @brief Configura l'identificador de la distribució seleccionada.
     *
     * @pre La distribució seleccionada és vàlida.
     * @post Es mostra el nou identificador de la distribució.
     */
    public void setIdDistribution() {
        distribucioLabel.setText(idDisribucio);
        distribucioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        distribucioLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
    }

    /**
     * @brief Inicialitza els panells i components del menú.
     *
     * @pre -
     * @post Els panells estan configurats i afegits al contenidor principal.
     */
    private void initPanels() {
        // Configuració del panell superior
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel idLabel = new JLabel("Menu Distribucions");
        idLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        topPanel.add(idLabel);

        // Configuració del panell central
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;

        JLabel distribucionsLabel = new JLabel("Distribucions");
        distribucionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        distribucionsLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 0;
        gbc.weightx = 0.5;
        centerPanel.add(distribucionsLabel, gbc);

        gbc.gridy = 0;
        gbc.gridx = 2;
        centerPanel.add(distribucioLabel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        distributionsList.setFixedCellHeight(50);
        centerPanel.add(distributionsScrollPane, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.gridy = 1;
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(2, 0));
        separator.setBackground(Color.GRAY);
        separator.setOpaque(true);
        separator.setOrientation(SwingConstants.VERTICAL);
        centerPanel.add(separator, gbc);

        gbc.gridx = 2;
        centerPanel.add(distributionPanel, gbc);

        // Configuració del panell inferior
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(importar);
        bottomPanel.add(veureButton);

        setUpDistributionList();
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

    /**
     * @brief Configura el renderitzat personalitzat per a la llista de distribucions.
     *
     * @pre -
     * @post Els elements de la llista es mostren amb estil personalitzat segons l'estat.
     */
    private void setUpDistributionList() {
        distributionsList.setCellRenderer(new ListCellRenderer<String>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel(value);
                label.setOpaque(true);

                if (isSelected) {
                    label.setBackground(Color.BLUE);
                    label.setForeground(Color.WHITE);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.BLACK);
                }

                return label;
            }
        });
    }
}
