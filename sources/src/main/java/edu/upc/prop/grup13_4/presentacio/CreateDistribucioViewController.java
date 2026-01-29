//CreateDistribucioViewController
//Author: Max Velasco

package edu.upc.prop.grup13_4.presentacio;

import edu.upc.prop.grup13_4.exceptions.algoritme.InvalidNumPrestatgesException;
import edu.upc.prop.grup13_4.exceptions.distribucio.DistribucioAlreadyExistsException;
import edu.upc.prop.grup13_4.presentacio.views.CreateDistribucioPanel;

import javax.swing.*;

/**
 * @brief Controlador per la vista de creació de distribucions
 *
 * Aquesta classe és un SINGLETON i gestiona la interacció entre la interfície gràfica del panell de creació de distribucions i la lògica
 * d'aplicació, permetent seleccionar el mètode, definir el nombre de prestatges i gestionar els errors associats.
 */
public class CreateDistribucioViewController {
    //CLASS ATRIBUTES

    /**
     * @brief Instància del Controlador de Presentació que permet la interactivitat entre classes.
     */
    private CtrlPresentacio cPresentacio = CtrlPresentacio.getInstance();
    /**
     * @brief Instància de la finestra CreateDistribucio.
     */
    private CreateDistribucioPanel instCreateDistribucioPanel;
    /**
     * @brief Instància d'aquesta classe.
     */
    private static CreateDistribucioViewController instance;
    /**
     * @brief String que indica el tipus d'algorisme que s'ha d'utilitzar per crear la Distribució
     */
    private String algoritme = "";

    //METHODS

    /**
     * @brief Constructor de la classe CreateDistribucioViewController.
     *
     * @pre -
     * @post Inicialitza el panell de creació de distribució i afegeix els listeners als components gràfics.
     */
    private CreateDistribucioViewController() {
        instCreateDistribucioPanel = new CreateDistribucioPanel();
        addListeners();
    }

    /**
     * @brief Afegeix els listeners als components gràfics del panell de creació de distribució.
     *
     * @pre El panell de creació de distribució està inicialitzat.
     * @post Els components gràfics del panell tenen listeners assignats per a gestionar esdeveniments.
     */
    public void addListeners() {
        instCreateDistribucioPanel.aproximatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algoritme = "Aproximat";
                instCreateDistribucioPanel.aproximatButton.setBackground(new java.awt.Color(76, 175, 80));

                instCreateDistribucioPanel.backtrackingButton.setSelected(false);
                instCreateDistribucioPanel.backtrackingButton.setBackground(new java.awt.Color(176, 190, 197));
            }
        });
        instCreateDistribucioPanel.backtrackingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algoritme = "Backtracking";
                instCreateDistribucioPanel.backtrackingButton.setBackground(new java.awt.Color(76, 175, 80));

                instCreateDistribucioPanel.aproximatButton.setSelected(false);
                instCreateDistribucioPanel.aproximatButton.setBackground(new java.awt.Color(176, 190, 197));
            }
        });
        instCreateDistribucioPanel.createDistribucioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String id = instCreateDistribucioPanel.idField.getText();
                int prestatges = (int) instCreateDistribucioPanel.nPrestatges.getValue();

                if(id.isEmpty()) {
                    showError("Introdueix el identificador de la Distribucio");
                    return;
                }else if(algoritme.isEmpty()) {
                    showError("Selecciona un Mètode");
                    return;
                }
                if(prestatges > cPresentacio.getNumProductes()) showError("El nombre de Prestatges no pot ser més gran que el nombre de Productes");
                try {
                    cPresentacio.createDistribucio(id,false,algoritme,prestatges);
                    instCreateDistribucioPanel.setVisible(false);
                    cPresentacio.goToDitribucio();
                }catch(DistribucioAlreadyExistsException exception) {
                    showError("Ja existeix una Distribució amb aquest nom");
                }catch(InvalidNumPrestatgesException exception) {
                    showError("El nombre de prestatges ha de ser un múltiple del nombre de productes (" + String.valueOf(cPresentacio.getNumProductes()) +")");
                }

            }
        });
        instCreateDistribucioPanel.closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instCreateDistribucioPanel.setVisible(false);
            }
        });
    }

    /**
     * @brief Reinicia l'estat del panell de creació de distribució.
     *
     * @pre El panell de creació de distribució està inicialitzat.
     * @post Tots els components del panell es reinicien al seu estat inicial.
     */
    public void reloadCreateDistribucioFrame() {
        instCreateDistribucioPanel.reload();
    }

    /**
     * @brief Mostra un missatge d'error en una finestra emergent.
     *
     * @pre El missatge d'error està definit.
     * @post Es mostra una finestra emergent amb el missatge d'error proporcionat.
     *
     * @param error El missatge d'error que es mostrarà.
     */
    private void showError(String error) {
        JOptionPane.showMessageDialog(instCreateDistribucioPanel, error ,"Error!",JOptionPane.ERROR_MESSAGE);
    }

    //GETTERS

    /**
     * @brief Obté la instància singleton de la classe CreateDistribucioViewController.
     *
     * @pre -
     * @post Retorna la instància singleton de CreateDistribucioViewController.
     */
    public static CreateDistribucioViewController getInstance() {
        if (instance == null) {
            instance = new CreateDistribucioViewController();
        }
        return instance;
    }

    /**
     * @brief Obté la vista del panell de creació de distribució.
     *
     * @pre El panell de creació de distribució està inicialitzat.
     * @post Retorna la finestra JFrame associada al panell de creació de distribució.
     */
    public JFrame getCreateDistribucioView() {
        return instCreateDistribucioPanel;
    }
}
