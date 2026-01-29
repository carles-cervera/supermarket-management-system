package edu.upc.prop.grup13_4.presentacio;

import edu.upc.prop.grup13_4.domini.CtrlDomini;
import edu.upc.prop.grup13_4.exceptions.inventari.InventariAlreadyExistsException;
import edu.upc.prop.grup13_4.presentacio.views.CrearInventariPanel;
import edu.upc.prop.grup13_4.presentacio.views.ProductesPanel;
import edu.upc.prop.grup13_4.presentacio.views.RelacionsPanel;
import edu.upc.prop.grup13_4.utils.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


/**
 * @brief Classe abstracta ProductesViewController.
 *
 * Aquesta classe és abstracta per a obligar al programador a declarar una classe concreta (normalment inner-class)
 * i ampliar, si cal, la funcionalitat oferida.
 *
 * Aquesta classe no està pensada per ser emprada en solitari.
 */
public abstract class ProductesViewController {

    /**
     * @brief Instància de ProductesPanel.
     */
    ProductesPanel panel;

    /**
     * @brief Instància de CtrlDomini.
     */
    CtrlDomini domini = CtrlDomini.getInstance();

    /**
     * @brief Constructora.
     */
    public ProductesViewController() {
    }

    /**
     * @brief Connectar amb una instància de ProductesPanel.
     */
    public void bindPanel (ProductesPanel panel) {
        this.panel = panel;
    }

    /**
     * @brief Actualitzar dinàmicament els element de la UI.
     *
     * Aquest mètode s'ha de cridar cada cop que es seleccioni un producte diferent.
     */
    public void dynamic_update() {
        List<String> prods = domini.getInfoProductes();

        panel.listModel.clear();

        for (String s : prods) {
            panel.addProducte(s);
        }
    }

    /**
     * @brief Afegir listeners als elements UI que ho requereixen.
     */
    public void addListeners() {

    }
}
