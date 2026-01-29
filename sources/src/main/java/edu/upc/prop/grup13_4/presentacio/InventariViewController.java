package edu.upc.prop.grup13_4.presentacio;

import edu.upc.prop.grup13_4.exceptions.inventari.InventariAlreadyExistsException;
import edu.upc.prop.grup13_4.presentacio.views.InventariPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * @brief ViewController d'una instància de InventariPanel.
 */
public class InventariViewController {

    /**
     * @brief SubViewController utilitzat per un panel ProductesPanel.
     */
    public ProductesSubViewController productesController;

    /**
     * @brief Instància de InventariPanel.
     */
    private InventariPanel panel;

    /**
     * @brief Instància de CtrlPresentacio.
     */
    private CtrlPresentacio presentacio = CtrlPresentacio.getInstance();

    /**
     * @brief Constructora.
     */
    public InventariViewController() {
        productesController = new ProductesSubViewController();
        panel = new InventariPanel();

        panel.bindViewController(this, productesController);
        panel.setUI();
    }

    /**
     * @brief Actualitzar dinàmicament el contingut de la vista.
     */
    public void reloadInventariPanel() {
        productesController.dynamic_update();
        panel.reload(presentacio.getIdActiveInventari());
    }


    /**
     * @brief Afegir els listeners als elements UI que ho requereixen.
     */
    public void addListeners() {
        panel.addButton.addActionListener(e -> { //Add product
            String productID = panel.prodId.getText();
            try {
                presentacio.afegirProducte(productID);
            } catch (InventariAlreadyExistsException exception) {
                //System.err.println("Ja existeix un Inventari amb aquest nom");
                JOptionPane.showMessageDialog(panel, "Ja existeix un producte amb aquest nom",
                        "Error!",JOptionPane.ERROR_MESSAGE);
                return;
            } catch (RuntimeException exception) {
                System.err.println(exception.getMessage());
                JOptionPane.showMessageDialog(panel, "Algun error desconegut ha succeit al " +
                        "crear l'Inventari","Error!",JOptionPane.ERROR_MESSAGE);
                return;
            }
            panel.productesPanel.addProducte(productID); //Add product graphically
        });

        panel.crearDistribucioButton.addActionListener(e -> {
            presentacio.goToCrearDistribucio();
        });
        panel.saveButton.addActionListener(e -> {
            System.out.println("Inventari Guardat");
            presentacio.guardarInventari();
        });
        panel.eraseButton.addActionListener(e -> {
            presentacio.deleteInventari();
        });
    }

    /**
     * @brief Obtenir instància de InventariPanel.
     */
    public InventariPanel getView() {
        return panel;
    }

    /**
     * @brief Classe concreta de ProductesViewController.
     */
    public class ProductesSubViewController extends ProductesViewController {
        public ProductesSubViewController() {
            super();
        }

        @Override
        public void addListeners() {
            super.addListeners();

            panel.list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        //Interact with relacions panel: update ui
                        if (panel.list.getSelectedValue() != null) {
                            presentacio.popProducte(panel.list.getSelectedValue().toString());
                        }
                    }
                }

            });
        }

    }
}
