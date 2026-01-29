//MenuDistribucionsViewController
//Author: Carles Cervera


package edu.upc.prop.grup13_4.presentacio;

import edu.upc.prop.grup13_4.presentacio.views.MenuDistribucionPanel;
import edu.upc.prop.grup13_4.utils.ParseDistribucio;

import java.util.List;
/**
 * @brief Classe encarregada de gestionar la interacció entre el panell de distribucions i el controlador de presentació.
 */
public class MenuDistribucionsViewController {
    // ATRIBUTS DE CLASSE

    /**
     * @brief Instància del controlador de presentació.
     */
    private CtrlPresentacio cPresentacio;

    /**
     * @brief Panell associat a la vista del menú de distribucions.
     */
    private MenuDistribucionPanel menuDistribucionsView;

    /**
     * @brief Array que conté els identificadors de les distribucions disponibles.
     */
    private String[] idDistribucions;

    // CONSTRUCTOR

    /**
     * @brief Constructor de la classe. Inicialitza el panell i afegeix els listeners necessaris.
     *
     * @pre -
     * @post El panell de distribucions està inicialitzat i preparat per rebre esdeveniments.
     */
    public MenuDistribucionsViewController() {
        cPresentacio = CtrlPresentacio.getInstance();
        menuDistribucionsView = new MenuDistribucionPanel();
        addListeners();
    }

    // MÈTODES PRIVATS

    /**
     * @brief Inicialitza els identificadors de distribucions obtinguts del controlador de presentació.
     *
     * @pre -
     * @post Els identificadors de distribucions es carreguen en l'atribut idDistribucions.
     */
    private void init() {
        idDistribucions = cPresentacio.getIdDistribucions();
    }

    /**
     * @brief Obté la distribució associada a un identificador específic.
     *
     * @param id Identificador de la distribució.
     * @return Llista de llistes de cadenes que representen la distribució.
     *
     * @pre L'identificador és vàlid.
     * @post Es retorna la distribució associada a l'identificador.
     */
    private List<List<String>> getDistribucio(String id) {
        return cPresentacio.getDistribucio(id);
    }

    // MÈTODES PÚBLICS

    /**
     * @brief Actualitza el panell de distribucions amb les dades més recents.
     *
     * @pre -
     * @post El panell es reinicia i es mostra la llista d'identificadors de distribucions.
     */
    public void reloadMenuDistribucions() {
        init();
        menuDistribucionsView.reload(idDistribucions);
    }

    /**
     * @brief Afegeix els listeners als components gràfics del panell de distribucions.
     *
     * @pre El panell de distribucions està inicialitzat.
     * @post Els components gràfics tenen listeners assignats per gestionar esdeveniments.
     */
    public void addListeners() {
        // Listener per seleccionar una distribució de la llista
        menuDistribucionsView.distributionsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String id = menuDistribucionsView.idDisribucio = menuDistribucionsView.distributionsList.getSelectedValue();
                if (id != null) {
                    menuDistribucionsView.setIdDistribution();
                    menuDistribucionsView.distributionPanel.createDistributionPanel(getDistribucio(id));
                }
            }
        });

        // Listener per veure una distribució
        menuDistribucionsView.veureButton.addActionListener(e -> {
            String id = menuDistribucionsView.distributionsList.getSelectedValue();
            if (id != null) {
                cPresentacio.changeADistribucio(id);
                cPresentacio.goToDitribucio();
            }
        });

        // Listener per importar una distribució
        menuDistribucionsView.importar.addActionListener(e -> {
            new ParseDistribucio();
        });
    }

    /**
     * @brief Obté el panell de distribucions.
     *
     * @pre -
     * @post Es retorna el panell de distribucions.
     */
    public MenuDistribucionPanel getmenuDistribucionsView() {
        return menuDistribucionsView;
    }
}
