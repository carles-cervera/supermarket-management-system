package edu.upc.prop.grup13_4.presentacio;
import edu.upc.prop.grup13_4.presentacio.views.MenuInventariPanel;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;

/**
 * @brief Classe per gestionar la interactivitat de MenuInventariPanel.
 */
public class MenuInventariViewController {
    //CLASS ATRIBUTES
    /**
     * @brief Instància del panell `MenuInventariPanel` que conté la vista de l'interfície d'usuari.
     */
    private static MenuInventariViewController instance;
    /**
     * @brief Instància de MenuInventariPanel associada al controller.
     */
    private MenuInventariPanel minv;
    /**
     * @brief Instància del controlador de presentació per permetre comunicació amb les altres classes.
     */
    private CtrlPresentacio cPresentacio = CtrlPresentacio.getInstance();

    /**
     * @brief Constructora de la classe.
     */
    private MenuInventariViewController() {
        minv = new MenuInventariPanel();
        addListeners();
    }

    //METHODS
    /**
     * @brief Torna a carregar la llista amb els inventaris de l'usuari.
     * @pre Panel inicialitzat.
     * @post La llista reflecteix la informació obtinguda amb getInventarisIds.
     */
    public void recargapanel() {
        List<String> inventarios = cPresentacio.getInventarisIds();
        minv.recargascrollableinvs(inventarios);
    }

    /**
     * @brief Recarrega el panel de preview d'inventaris.
     * @pre Panel inicialitzat.
     * @post Panel buit.
     */
    public void recargapreviewbuida() {
        minv.recargapreviewbuida();
    }

    /**
     * @brief Mostra els productes de l'inventari seleccionat.
     * @pre inv_seleccionat != null.
     * @post Tenim una preview de l'inventari seleccionat amb la informació obtinguda amb getInfoProductes.
     */
    public void recargapreview() {
        List<String> productos = cPresentacio.getInfoProductes();
        minv.recargapreview(productos);
    }

    /**
     * @brief Afegeix els listeners als buttons pertinents i a la llista d'inventaris.
     * @pre Els components del MenuInventariPanel han d'estar inicialitzats.
     * @post Tenim assignats MouseListener per quan es cliqui cada component dessignat.
     */
    public void addListeners() {
        minv.crear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cPresentacio.goToCrearInventari();
            }
        });

        minv.listInventaris.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if (!minv.listInventaris.isSelectionEmpty()) {
                    cPresentacio.seleccionarInventari(minv.listInventaris.getSelectedValue());
                    recargapreview();
                }
            }
        });

        minv.veureinv.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!minv.listInventaris.isSelectionEmpty()) {
                    cPresentacio.seleccionarInventari(minv.listInventaris.getSelectedValue());
                    cPresentacio.goToInventariIndividual();
                }
                else {
                    showError("Si us plau, seleccioni un inventari");
                }
            }
        });
        minv.importButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cPresentacio.setScriptDialog();
            }
        });
    }

    /**
     * @brief Mostra un missatge d'error en una finestra emergent.
     * @pre El missatge d'error està definit.
     * @post Es mostra una finestra emergent amb el missatge d'error proporcionat.
     * @param error El missatge d'error que es mostrarà.
     */
    private void showError(String error) {
        JOptionPane.showMessageDialog(minv, error ,"Error!",JOptionPane.ERROR_MESSAGE);
    }

    //GETTERS
    /**
     * @brief Getter de la única instància possible de MenuInventariViewController.
     */
    public static MenuInventariViewController getInstance() {
        if (instance == null) {
            instance = new MenuInventariViewController();
        }
        return instance;
    }

    /**
     * @brief MenuInventariPanel getter
     */
    public JPanel getmenuInventarisView() {
        return minv;
    }
}