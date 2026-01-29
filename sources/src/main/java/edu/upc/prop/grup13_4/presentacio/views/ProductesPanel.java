package edu.upc.prop.grup13_4.presentacio.views;

import edu.upc.prop.grup13_4.inventari.Producte;
import edu.upc.prop.grup13_4.presentacio.ProductesViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * @brief Panel per visualitzar els productes d'un Inventari.
 */
public class ProductesPanel extends JPanel {

    /**
     * @brief Llista de productes mostrats de l'inventari.
     */
    public JList<String> list;

    /**
     * @brief DefaultListModel de la llista de productes mostrats de l'inventari.
     */
    public DefaultListModel listModel = new DefaultListModel();

    /**
     * @brief ScrollPane de la llista de productes mostrats de l'inventari.
     */
    private JScrollPane scrollPane;

    /**
     * @brief ViewController de la instància de ProductesPanel.
     */
    private ProductesViewController controller;

    /**
     * @brief MenuItem per poder modificar un producte.
     */
    public JMenuItem modProd;

    /**
     * @brief MenuItem per poder esborrar un producte de l'inventari.
     */
    public JMenuItem removeProd;


    /**
     * @brief Constructora.
     */
    public ProductesPanel() {
        list = new JList(listModel);
        scrollPane = new JScrollPane(list);
        modProd = new JMenuItem("Modificar");
        removeProd = new JMenuItem("Eliminar");
    }

    /**
     * @brief Actualitzar interfície gràfica
     *
     * Aquest mètode configura tots els elements de la intefície gràfica.
     *
     */
    public void setUI() {
        setLayout(new GridLayout(1,1));
        add(scrollPane);

        list.setCellRenderer(new ListCellRenderer<String>() {
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
        controller.addListeners();
        setVisible(true);
    }

    /**
     * @brief Enllaçar ViewControllers amb l'instància ProductesPanel
     *
     * Aquest mètode enllaça pertinentment els ViewControllers de tots els panels que en requereixen un.
     *
     * @param c ProductesViewController de la instància de ProductesPanel.
     *
     */
    public void bindViewController(ProductesViewController c) {
        this.controller = c;
    }

    /**
     * @brief Afegir un producte a la llista de proudctes mostrada.
     *
     */
    public void addProducte(String id) {
        listModel.addElement(id);
    }


    /**
     * @brief Configurar el setFixedCellHeight de la llista de productes.
     *
     */
    public void setFixedCellHeight(int n) {
        list.setFixedCellHeight(n);
    }

    /**
     * @brief Mostrar un PopUp menu en la posició especificada.
     *
     */
    public void showPopupMenu(java.awt.Component invoker, int x, int y, int index) {
        JPopupMenu popup = new JPopupMenu();
        popup.add(modProd);
        popup.add(removeProd);
        modProd.putClientProperty("index",index);
        removeProd.putClientProperty("index",index);
        popup.show(invoker, x, y);
    }
}
