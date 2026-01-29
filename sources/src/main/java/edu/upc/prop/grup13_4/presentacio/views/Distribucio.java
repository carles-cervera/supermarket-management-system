//Distribucio
//Author: Carles Cervera

package edu.upc.prop.grup13_4.presentacio.views;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @brief Classe que defineix la interfície gràfica per a la distribució dels elements en un panell.
 */
public class Distribucio extends JPanel {
    // ATRIBUTS DE LA CLASSE

    /**
     * @brief Panell superior de la distribució.
     */
    public JPanel topPanel;

    /**
     * @brief Panell inferior de la distribució.
     */
    private JPanel bottomPanel;

    /**
     * @brief Panell central de la distribució.
     */
    public JPanel centerPanel;

    /**
     * @brief Panell per a botons addicionals situat sota el panell central.
     */
    private JPanel extendCenterPanel;

    /**
     * @brief Etiqueta que mostra l'ID de la distribució.
     */
    public JLabel id;

    /**
     * @brief Panell que representa la distribució dels productes.
     */
    public DistributionPanel distributionPanel;

    /**
     * @brief Botó per a modificar la distribució.
     */
    public JButton modificarButton = new JButton("Modificar distribució");

    /**
     * @brief Botó per a clonar la distribució.
     */
    public JButton clonarButton = new JButton("Clonar distribució");

    /**
     * @brief Botó per a eliminar un producte de la distribució.
     */
    public JButton eliminarButton = new JButton("Eliminar producte");

    /**
     * @brief Botó per a canviar el nom de la distribució.
     */
    public JButton canviarIdBitton = new JButton("Canviar nom distribució");

    /**
     * @brief Botó per a eliminar completament la distribució.
     */
    public JButton eliminarDistribucioButton = new JButton("Eliminar Distribució");

    // CONSTRUCTOR

    /**
     * @brief Constructor de la classe Distribucio.
     *
     * @pre -
     * @post Inicialitza els panells i els seus components gràfics.
     */
    public Distribucio() {
        topPanel = new JPanel();
        id = new JLabel();
        centerPanel = new JPanel();
        extendCenterPanel = new JPanel();
        bottomPanel = new JPanel();
        distributionPanel = new DistributionPanel();
        initPanels();
    }

    // MÈTODES

    /**
     * @brief Actualitza la distribució amb un nou identificador i llista de productes.
     *
     * @pre La distribució ha estat inicialitzada.
     * @post El panell de distribució es reinicia amb els nous valors proporcionats.
     * @param idDistribucio Identificador de la distribució.
     * @param products Llista de productes per a mostrar en la distribució.
     */
    public void reload(String idDistribucio, List<List<String>> products) {
        distributionPanel.createDistributionPanel(products);
        id.setText(idDistribucio);
    }

    /**
     * @brief Inicialitza els panells de la classe i configura els seus components gràfics.
     *
     * @pre -
     * @post Els panells de la distribució estan configurats i afegits al contenidor principal.
     */
    private void initPanels() {
        // Configuració del panell superior
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        id.setFont(new Font("Tahoma", Font.BOLD, 16));
        topPanel.add(id);

        // Configuració del panell central
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        centerPanel.add(distributionPanel, gbc);

        // Configuració del panell extendCenterPanel
        extendCenterPanel.setLayout(new GridLayout());
        extendCenterPanel.add(modificarButton);
        extendCenterPanel.add(eliminarButton);
        extendCenterPanel.add(clonarButton);
        extendCenterPanel.add(canviarIdBitton);
        extendCenterPanel.add(eliminarDistribucioButton);

        // Configuració del panell inferior (bottomPanel)
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        // Configuració global del panell principal
        setUpPanel();
        setSize(1000, 700);
        setVisible(true);
    }

    /**
     * @brief Configura la disposició dels panells dins del contenidor principal.
     *
     * @pre Els panells han estat inicialitzats.
     * @post Els panells estan organitzats segons el disseny definit.
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

        // Afegir el panell estès central
        gbc.gridy = 2;
        gbc.weighty = 0.0;
        extendCenterPanel.setPreferredSize(new Dimension(400, 40));
        add(extendCenterPanel, gbc);

        // Afegir el panell inferior
        gbc.gridy = 4;
        gbc.weighty = 0.0;
        bottomPanel.setPreferredSize(new Dimension(400, 40));
        add(bottomPanel, gbc);
    }
}
