package edu.upc.prop.grup13_4.presentacio.views;

import edu.upc.prop.grup13_4.presentacio.CrearInventariViewController;
import edu.upc.prop.grup13_4.presentacio.InventariViewController;
import edu.upc.prop.grup13_4.presentacio.ProductesViewController;
import edu.upc.prop.grup13_4.presentacio.RelacionsViewController;
import edu.upc.prop.grup13_4.presentacio.assets.AcceptButton;
import edu.upc.prop.grup13_4.presentacio.assets.TextPrompt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


/**
 * @brief Panel per visualitzar un inventari.
 */
public class InventariPanel extends JPanel {

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

        InventariViewController controller = new InventariViewController();
        JFrame frame = new JFrame("Inventari Individual");
        JPanel panel = controller.getView();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.setSize(1000,600);
        frame.setContentPane(panel);

        frame.setVisible(true);

    }

    //Main panel elements

    /**
     * @brief Panel superior.
     */
    private JPanel topPanel;

    /**
     * @brief Panel de productes de l'inventari seleccionat.
     */
    public ProductesPanel productesPanel;

    /**
     * @brief Panel per poder afegir productes a l'inventari seleccionat.
     */
    private JPanel addProdPanel;

    /**
     * @brief Panel inferiror.
     */
    private JPanel bottomPanel;

    /**
     * @brief Botó per crear la distribució.
     */
    public JButton crearDistribucioButton = new AcceptButton("Crear distribució");

    //addProdPanel elements

    /**
     * @brief Botó per eliminar l'inventari del perfil.
     */
    public JButton eraseButton = new JButton("Eliminar");

    /**
     * @brief Botó per guardar l'inventari al perfil.
     */
    public JButton saveButton = new AcceptButton("Guardar");

    /**
     * @brief Botó per a afegir un producte.
     */
    public JButton addButton = new JButton("Afegir Producte");

    /**
     * @brief TextField per indicar l'identificador del nou producte.
     */
    public JTextField prodId = new JTextField(10);

    /**
     * @brief Label per indicar l'identificador de l'inventari.
     */
    public JLabel idLabel = new JLabel();

    //bottomPanel

    //ViewControllers

    /**
     * @brief ViewController de la instància de InventariPanel.
     */
    InventariViewController controller;


    /**
     * @brief Constructora
     */
    public InventariPanel() {
        productesPanel = new ProductesPanel();
        topPanel = new JPanel();
        productesPanel = new ProductesPanel();
        addProdPanel = new JPanel();
        bottomPanel = new JPanel();
    }

    /**
     * @brief Enllaçar ViewControllers amb l'instància InventariPanel
     *
     * Aquest mètode enllaça pertinentment els ViewControllers de tots els panels que en requereixen un.
     *
     * @param c CrearInventariViewController de CrearInventariPanel
     * @param p ProductesViewController per al subpanel ProductesPanel
     *
     */
    public void bindViewController(InventariViewController c, ProductesViewController p) {
        //Bind view controller
        this.controller = c;

        //Bind view controller and start UI for productes
        this.controller.productesController.bindPanel(productesPanel);
        productesPanel.bindViewController(p);
        productesPanel.setUI();
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
        idLabel.setFont(new Font("Tahoma", Font.BOLD,16));
        topPanel.add(idLabel);

        //prodPanel conifugration


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
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(crearDistribucioButton);
        bottomPanel.add(saveButton);
        eraseButton.setBackground(Color.RED);
        eraseButton.setForeground(Color.WHITE);
        bottomPanel.add(eraseButton);

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
        productesPanel.setFixedCellHeight(50);
        add(productesPanel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.0;
        addProdPanel.setPreferredSize(new Dimension(400, 40));
        add(addProdPanel, gbc);

        gbc.gridy = 4;
        gbc.weighty = 0.0;
        bottomPanel.setPreferredSize(new Dimension(400, 40));
        add(bottomPanel, gbc);
    }

    /**
     * @brief Actualitzar label d'identificador d'inventari.
     *
     * Aquest mètode actualitza dinàmicament l'identificador de l'inventari mostrat.
     *
     */
    public void reload(String idInventari) {
        idLabel.setText(idInventari);
    }
}
