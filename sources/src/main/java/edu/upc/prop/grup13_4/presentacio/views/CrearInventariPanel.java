package edu.upc.prop.grup13_4.presentacio.views;

import edu.upc.prop.grup13_4.exceptions.inventari.InventariAlreadyExistsException;
import edu.upc.prop.grup13_4.exceptions.inventari.ProductIndexOutOfRangeException;
import edu.upc.prop.grup13_4.exceptions.inventari.ProductNotFoundException;
import edu.upc.prop.grup13_4.presentacio.CrearInventariViewController;
import edu.upc.prop.grup13_4.presentacio.ProductesViewController;
import edu.upc.prop.grup13_4.presentacio.RelacionsViewController;
import edu.upc.prop.grup13_4.presentacio.assets.AcceptButton;
import edu.upc.prop.grup13_4.presentacio.assets.TextPrompt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.TreeSet;


/**
 * @brief Panel per crear un Inventari.
 */
public class CrearInventariPanel extends JPanel {

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        CrearInventariViewController controller = new CrearInventariViewController();
        JFrame frame = new JFrame("Crear Producte");
        controller.dynamicUpdate();
        JPanel panel = controller.getCrearInventariView();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.setSize(1000,600);
        frame.setContentPane(panel);

        frame.setVisible(true);

    }

    /**
     * @brief Identificador de l'inventari
     *
     */
    private String idInventari;

    //Main panel elements

    /**
     * @brief Panel superior.
     */
    private JPanel topPanel;

    /**
     * @brief Panel de productes i relacions de l'inventari.
     *
     * Aquest panel contindrà dos subpanels, un per als productes i l'altre per a les relacions.
     */
    private JPanel prodPanel;

    /**
     * @brief Panel per afegir un producte a l'inventari.
     */
    private JPanel addProdPanel;

    /**
     * @brief Panel inferior.
     */
    private JPanel bottomPanel;

    /**
     * @brief Panel de productes de l'inventari.
     */
    public ProductesPanel productesPanel;

    /**
     * @brief Panel de relacions per a un producte determinat de l'inventari.
     */
    private RelacionsPanel relacions;

    //addProdPanel elements

    /**
     * @brief Botó per afegir un producte.
     */
    public JButton addButton = new JButton("Afegir Producte");

    /**
     * @brief Botó per indicar l'identificador d'un producte.
     */
    public JTextField prodId = new JTextField(10);

    //bottomPanel

    /**
     * @brief Botó per crear l'inventari.
     */
    public JButton createButton = new AcceptButton("Crear");

    /**
     * @brief Botó per cancel·lar la creació de l'inventari.
     */
    public JButton cancelButton = new JButton("Cancelar");

    /**
     * @brief Label per mostrar l'identificador de l'inventari configurat.
     */
    JLabel idLabel = new JLabel();

    //ViewControllers

    /**
     * @brief ViewController de la instància de CrearInvenariPanel.
     */
    CrearInventariViewController controller;


    /**
     * @brief Constructora.
     */
    public CrearInventariPanel() {
        productesPanel = new ProductesPanel();
        relacions = new RelacionsPanel();

        topPanel = new JPanel();
        prodPanel = new JPanel();
        addProdPanel = new JPanel();
        bottomPanel = new JPanel();
    }

    /**
     * @brief Enllaçar ViewControllers amb l'instància CrearInventariPanel
     *
     * Aquest mètode enllaça pertinentment els ViewControllers de tots els panels que en requereixen un.
     *
     * @param c CrearInventariViewController de CrearInventariPanel
     * @param r RelacionsViewController per al subpanel RelacionsPanel
     * @param p ProductesViewController per al subpanel ProductesPanel
     *
     */
    public void bindViewController(CrearInventariViewController c, RelacionsViewController r, ProductesViewController p) {
        //Bind view controller
        this.controller = c;
        //Bind view controller and start UI for relacions
        this.controller.relacionsController.bindPanel(relacions);
        relacions.bindViewController(r);
        relacions.setUI();
        //Bind view controller and start UI for productes
        this.controller.productesController.bindPanel(productesPanel);
        productesPanel.bindViewController(p);
        productesPanel.setUI();
    }

    /**
     * @brief Actualitzar informació.
     *
     * Aquest mètode actualitza la informació visual necessària per mostrar a l'usuari l'identificador de l'Inventari.
     *
     * @param idInventari Identificador de l'inventari a mostrar.
     *
     */
    public void updateText(String idInventari) {
        this.idInventari = idInventari;
        idLabel.setText(idInventari);
    }

    /**
     * @brief Actualitzar interfície gràfica
     *
     * Aquest mètode configura tots els elements de la intefície gràfica.
     *
     */
    public void setUI() {
        //topPanel configuration
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        idLabel.setText(idInventari);
        idLabel.setFont(new Font("Tahoma", Font.BOLD,16));
        topPanel.add(idLabel);

        //prodPanel conifugration
        prodPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;

        JLabel productesLabel = new JLabel("Productes");
        JLabel relacionsLabel = new JLabel("Relacions");

        productesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        relacionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        productesLabel.setFont(new Font("Tahoma", Font.ITALIC,16));
        relacionsLabel.setFont(new Font("Tahoma", Font.ITALIC,16));

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 0;
        gbc.weightx = 0.5;
        prodPanel.add(productesLabel,gbc);

        gbc.gridy = 0;
        gbc.gridx = 2;
        gbc.weighty = 0;
        gbc.weightx = 0.5;
        prodPanel.add(relacionsLabel,gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        productesPanel.setFixedCellHeight(50);
        prodPanel.add(productesPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.gridy = 1;
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(2,0));
        separator.setBackground(Color.GRAY);
        separator.setOpaque(true);
        separator.setOrientation(SwingConstants.VERTICAL);
        prodPanel.add(separator, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.5;
        prodPanel.add(relacions,gbc);

        //addProdPanel configuration
        addProdPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        TextPrompt placeholder = new TextPrompt("Identificador P.", prodId);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
        addProdPanel.add(prodId);
        addProdPanel.add(addButton);
        addProdPanel.add(Box.createHorizontalGlue());

        //bottomPanel configuration
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(cancelButton);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(createButton);

        controller.addListeners();

        setUpPanel();
        setSize(1000, 700);
        setVisible(true);
    }

    /**
     * @brief Confgurar panel.
     *
     * Aquest mètode configura la intefície gràfica de CrearInventariPanel.
     *
     */
    private void setUpPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.0;
        topPanel.setPreferredSize(new Dimension(400, 40));
        add(topPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 1.0;
        add(prodPanel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.0;
        addProdPanel.setPreferredSize(new Dimension(400, 40));
        add(addProdPanel, gbc);

        gbc.gridy = 4;
        gbc.weighty = 0.0;
        bottomPanel.setPreferredSize(new Dimension(400, 40));
        add(bottomPanel, gbc);
    }

}
