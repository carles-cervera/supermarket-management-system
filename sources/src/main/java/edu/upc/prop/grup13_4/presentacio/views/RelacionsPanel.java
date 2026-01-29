package edu.upc.prop.grup13_4.presentacio.views;

import edu.upc.prop.grup13_4.presentacio.assets.TextPrompt;
import edu.upc.prop.grup13_4.presentacio.RelacionsViewController;

import javax.swing.*;
import java.awt.*;

/**
 * @brief Panel per visualitzar les realcions d'un Inventari.
 */
public class RelacionsPanel extends JPanel {

    /**
     * @brief ScrollPane per a la llista de relacions mostrada del producte seleccionat.
     */
    private JScrollPane scrollPane;

    /**
     * @brief DefaultListModel de la llista de relacions mostrada del producte seleccionat.
     */
    public DefaultListModel<RelacioElement> listModel;

    /**
     * @brief Llista de relacions del producte seleccionat.
     */
    public JList<RelacioElement> relatedProd;

    /**
     * @brief Panel inferior.
     */
    public JPanel bottomPanel;

    /**
     * @brief TextField per indicar l'identificador del producte a relacionar.
     */
    public JTextField relatedProdName;

    /**
     * @brief TextField per indicar el grau al què es vol relacionar.
     */
    public JTextField relatedProdGrau;

    /**
     * @brief Button per afegir una relació al producte seleccionat.
     */
    public JButton relatedProdAdd;

    //PopupMenu

    /**
     * @brief MenuItem per esborrar una relació.
     */
    public JMenuItem removeRel;

    /**
     * @brief MenuItem per modificar una relació.
     */
    public JMenuItem modRel;

    //ViewController

    /**
     * @brief Controlador de la instància de RelacionsPanel.
     */
    RelacionsViewController controller;

    /**
     * @brief Constructora buida.
     */
    public RelacionsPanel() {

    }


    public RelacionsPanel(RelacionsViewController controller) { //Empty panel
        this.controller = controller;
    }


    /**
     * @brief Actualitzar interfície gràfica
     *
     * Aquest mètode configura tots els elements de la intefície gràfica.
     *
     */
    public void setUI() {
        setBackground(Color.LIGHT_GRAY);
        bottomPanel = new JPanel();
        relatedProdName = new JTextField(10);
        relatedProdAdd = new JButton("Afegir");
        relatedProdGrau = new JTextField(10);
        setProductes();
        controller.addListeners();
    }


    /**
     * @brief Inicialitzar la llista de relacions.
     */
    private void setProductes() {
        listModel = new DefaultListModel<>();

        relatedProd = new JList<>(listModel);
        scrollPane = new JScrollPane(relatedProd);


        relatedProd.setCellRenderer(new ListCellRenderer<RelacioElement>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends RelacioElement> list, RelacioElement value, int index, boolean isSelected, boolean cellHasFocus) {

                if (isSelected) {
                    value.setBackground(Color.GRAY);
                    value.setForeground(Color.WHITE);
                } else {
                    value.setBackground(Color.CYAN);
                    value.setForeground(Color.BLACK);
                }
                return value;
            }
        });

        relatedProd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setBackground(Color.WHITE);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        add(scrollPane, c);

        c.gridy = 1;
        c.weighty = 0;
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(0, 2));
        separator.setOpaque(true);
        separator.setBackground(Color.GRAY);
        add(separator,c);

        c.weighty = 0.0;
        c.gridy = 2;
        JPanel addRelTitle = new JPanel();
        addRelTitle.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        addRelTitle.add(new JLabel("Afegir relació"));
        add(addRelTitle,c);

        c.weighty = 0.0;
        c.gridy = 3;
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2,1));
        TextPrompt idPH = new TextPrompt("Identificador P.", relatedProdName);
        idPH.changeAlpha(0.75f);
        idPH.changeStyle(Font.ITALIC);
        textPanel.add(relatedProdName);
        TextPrompt grauPH = new TextPrompt("Grau", relatedProdGrau);
        grauPH.changeAlpha(0.75f);
        grauPH.changeStyle(Font.ITALIC);
        textPanel.add(relatedProdGrau);
        bottomPanel.add(textPanel);

        bottomPanel.add(relatedProdAdd);
        bottomPanel.add(Box.createHorizontalGlue());
        add(bottomPanel, c);

        //Popup menu
        removeRel = new JMenuItem("Eliminar");
        modRel = new JMenuItem("Modificar");
    }

    /**
     * @brief Mostrar PopUpMenu donada una posició i un índex.
     *
     */
    public void showPopupMenu(java.awt.Component invoker, int x, int y, int index) {
        JPopupMenu popup = new JPopupMenu();
        popup.add(modRel);
        popup.add(removeRel);
        modRel.putClientProperty("index",index);
        removeRel.putClientProperty("index",index);
        popup.show(invoker, x, y);
    }

    /**
     * @brief Enllaçar ViewControllers amb l'instància RelacionsPanel
     *
     * Aquest mètode enllaça pertinentment els ViewControllers de tots els panels que en requereixen un.
     *
     * @param c RealcionsViewController de la instància de ProductesPanel.
     *
     */
    public void bindViewController(RelacionsViewController c) {
        controller = c;
    }


    /**
     * @brief Classe interna que representa una filera de la llista de relacions.
     *
     * Aquest panel msostra la informació necessària d'una relació. És a dir, el producte al què està relacionat
     * i el grau de relació.
     *
     */
    public static class RelacioElement extends JPanel {

        private JLabel id;
        private JLabel grau;


        /**
         * @brief Constructora.
         *
         * @param id Identificador del producte relacionat.
         * @param grau Grau de la relació.
         */
        public RelacioElement(String id, String grau) {
            super();
            this.id = new JLabel(id);
            this.grau = new JLabel(grau);

            setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
            setBackground(Color.CYAN);
            add(this.id);
            add(this.grau);
            setVisible(true);
        }

        /**
         * @brief Modificar grau mostrat.
         */
        public void modGrau(int grau) {
            this.grau.setText(grau+"");
        }

        /**
         * @brief Obtenir identificador del producte relacionat.
         */
        public String getProductId() {
            return id.getText();
        }

        @Override
        public void setForeground(Color color) {
            super.setForeground(color);

            if (id != null) {
                id.setForeground(color);
            }
            if (grau != null) {
                grau.setForeground(color);
            }
        }
    }
}


