package edu.upc.prop.grup13_4.presentacio;

import edu.upc.prop.grup13_4.domini.CtrlDomini;
import edu.upc.prop.grup13_4.exceptions.inventari.InventariAlreadyExistsException;
import edu.upc.prop.grup13_4.exceptions.inventari.InventariNotFoundException;
import edu.upc.prop.grup13_4.exceptions.inventari.ProductAlreadyExistsException;
import edu.upc.prop.grup13_4.presentacio.views.CrearInventariPanel;
import edu.upc.prop.grup13_4.presentacio.views.RelacionsPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @brief ViewController d'un panel CrearInventariPanel.
 */
public class CrearInventariViewController {

    //Instance of Views

    /**
     * @brief Instància de CtrlDomini.
     */
    private CtrlDomini cDomini = CtrlDomini.getInstance();

    /**
     * @brief Instància de CtrlPresentació.
     */
    private CtrlPresentacio cPresentacio = CtrlPresentacio.getInstance();

    /**
     * @brief Instància de CrearInventariPanel.
     */
    private CrearInventariPanel crearInventariView;

    /**
     * @brief SubViewController utilitzat per un panel RelacionsPanel.
     */
    public RelacionsSubViewController relacionsController;

    /**
     * @brief SubViewController utilitzat per un panel ProductesPanel.
     */
    public ProductesSubViewController productesController;

    /**
     * @brief Identificador de l'inventari actiu.
     */
    private String invID;

    /**
     * @brief Constructora.
     */
    public CrearInventariViewController() {
        relacionsController = new RelacionsSubViewController();
        productesController = new ProductesSubViewController();
        crearInventariView = new CrearInventariPanel();
        crearInventariView.bindViewController(this,relacionsController,productesController);
        crearInventariView.setUI();
    }

    /**
     * @brief Obtenir la instància de CrearInventariView continguda.
     */
    public CrearInventariPanel getCrearInventariView() {
        return crearInventariView;
    }

    /**
     * @brief Actualització dinàmica de la informació de l'inventari.
     *
     *  Cada cop que es canvia d'Inventari actiu, aquesta funció s'ha d'executar per actualitzar la informació gràfica.
     */
    public boolean dynamicUpdate() {
        showInitialDialog();
        if (invID != null) {
            crearInventariView.updateText(invID);
            productesController.dynamic_update();
            relacionsController.set_empty();
        } else {
            return false;
        }
        return true;
    }

    /**
     * @brief Crear Inventari
     *
     * Delega la funcionalitat a CtrlDomini per crear un inventari.
     */
    public void crearInventari(String id) {
        cDomini.crearInventari(id, cDomini.getUsernameActiveProfile());
        cDomini.seleccionarInventari(id);
    }


    /**
     * @brief Afegir producte
     *
     * Delega la funcionalitat a CtrlDomini per afegir un producte.
     */
    public void afegirProducte(String id) {
        cDomini.afegirProducte(id);
    }

    /**
     * @brief Guardar inventari.
     *
     * Delega la funcionalitat a CtrlDomini per guardar un inventari.
     */
    public void guardarInventari() {
        cDomini.guardarInventari();
    }


    /**
     * @brief Mostrar menú inicial per a indicar el nom del nou inventari.
     *
     */
    private void showInitialDialog() {
        invID = (String) JOptionPane.showInputDialog(null, "Indiqui l'identificador " +
                        "de l'Inventari a crear.", "Crear Inventari"
                , JOptionPane.QUESTION_MESSAGE, null, null, "Inventari");

        if (invID == null) {
            return;
        }

        try {
            this.crearInventari(invID);
        } catch (InventariAlreadyExistsException exception) {
            JOptionPane.showMessageDialog(crearInventariView, "Ja existeix un Inventari amb aquest nom",
                    "Inventari Duplicat", JOptionPane.ERROR_MESSAGE);
            showInitialDialog(); //Repeat
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(crearInventariView, "El format de l'identificador " +
                            "no és vàlid. L'identificador no pot contenir espais.",
                    "Format Invàlid",JOptionPane.ERROR_MESSAGE);
            showInitialDialog(); //Repeat
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(crearInventariView, "Algun error desconegut ha succeit al " +
                    "crear l'Inventari","Error!",JOptionPane.ERROR_MESSAGE);
            showInitialDialog(); //Repeat
        }
    }

    /**
     * @brief Afegeix els listeners als elements UI pertinents.
     */
    public void addListeners() {
        crearInventariView.addButton.addActionListener(e -> {
            String productID = crearInventariView.prodId.getText();

            try {
                this.afegirProducte(productID);
            } catch (ProductAlreadyExistsException exception) {
                JOptionPane.showMessageDialog(crearInventariView, "Ja exiteix un producte " +
                        "amb aquest nom.","Ja existeix producte",JOptionPane.WARNING_MESSAGE);
                return;
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(crearInventariView, "El format de l'identificador " +
                        "no és vàlid. L'identificador no pot contenir espais.",
                        "Format Invàlid",JOptionPane.WARNING_MESSAGE);
                return;
            } catch (RuntimeException exception) { //May happen if no Inventari is selected in CtrlInventari...
                exception.printStackTrace();
                JOptionPane.showMessageDialog(crearInventariView, "Algun error desconegut ha succeit al " +
                        "crear l'Inventari","Error!",JOptionPane.ERROR_MESSAGE);
                return;
            }

            crearInventariView.productesPanel.addProducte(productID); //Add validated product to the UI.
        });

        crearInventariView.createButton.addActionListener(e -> {
            guardarInventari();
            cPresentacio.goToMenuInventari();
        });
    }


    /**
     * @brief Classe concreta de RelacionsViewController.
     */
    public class RelacionsSubViewController extends RelacionsViewController {
        public RelacionsSubViewController() {
            super();
        }

        public RelacionsSubViewController(RelacionsPanel panel) {
            super(panel);
        }
    }


    /**
     * @brief Classe concreta de ProducteSubViewController.
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
                            relacionsController.dynamic_update(panel.list.getSelectedValue().toString());
                        }
                    }
                }
            });
        }
    }
}
