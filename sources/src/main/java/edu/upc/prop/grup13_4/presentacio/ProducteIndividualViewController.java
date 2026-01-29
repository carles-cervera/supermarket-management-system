package edu.upc.prop.grup13_4.presentacio;
import edu.upc.prop.grup13_4.presentacio.views.ProducteIndividualPanel;
import edu.upc.prop.grup13_4.presentacio.views.RelacionsPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @brief Controlador de Producte Individual, habilita l'ús d'un RelacionsPanel.
 */
public class ProducteIndividualViewController {
    //CLASS ATRIBUTES
    /**
     * @brief Instància del ProducteIndividualPanel associat.
     */
    private ProducteIndividualPanel popup;
    /**
     * @brief Instància pròpia.
     */
    private static ProducteIndividualViewController instance;
    /**
     * @brief Controlador per utilitzar RelacionsPanel.
     */
    public RelacionsSubViewController relacionsController;
    /**
     * @brief Instància de control presentació per comunicar-se amb la resta de classes.
     */
    private CtrlPresentacio cPresentacio = CtrlPresentacio.getInstance();

    //METHODS
    /**
     * @brief Constructora de la classe.
     */
    private ProducteIndividualViewController() {
        relacionsController = new RelacionsSubViewController();
    }

    /**
     * @brief Estableix el panell emergent per mostrar la informació d'un producte individual.
     * @pre prodId != null.
     * @post Es mostra el panel amb les relacions del producte i el seu identificador.
     * @param prodId Identificador del producte a mostrar.
     */
    public void setPopup(String prodId) {
        this.popup = new ProducteIndividualPanel(cPresentacio.mainFrame,true, prodId);
        goBack();
        popup.bindViewController(relacionsController);
        if (relacionsController.dynamic_update(prodId)) {
            popup.setVisible(true);
        }
    }

    /**
     * @brief Configura el botó de tancar el panel.
     * @pre tancar != null.
     * @post tancar es interactiu i descarta el panel quan es clica.
     */
    public void goBack() {
        popup.tancar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popup.dispose();
            }
        });
    }

    /**
     * @brief Tractament de la classe abstracta RelacionsViewController
     */
    public class RelacionsSubViewController extends RelacionsViewController {
        public RelacionsSubViewController() {
            super();
        }

        public RelacionsSubViewController(RelacionsPanel panel) {
            super(panel);
        }
    }

    //GETTERS
    /**
     * @brief Getter de la única instància possible de la classe.
     */
    public static ProducteIndividualViewController getInstance() {
        if (instance == null) {
            instance = new ProducteIndividualViewController();
        }
        return instance;
    }

}
