//DistribucioViewControler
//Author: Carles Cervera

package edu.upc.prop.grup13_4.presentacio;

import edu.upc.prop.grup13_4.exceptions.distribucio.DistribucioAlreadyExistsException;
import edu.upc.prop.grup13_4.exceptions.distribucio.InvalidPositionException;
import edu.upc.prop.grup13_4.exceptions.inventari.ProductNotFoundException;
import edu.upc.prop.grup13_4.presentacio.views.Distribucio;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * @brief Classe que controla la interacció entre la vista de distribució i el controlador de presentació.
 */
public class DistribucioViewControler {
    // ATRIBUTS DE CLASSE
    /**
     * @brief Instància del controlador de presentació.
     */
    private CtrlPresentacio cPresentacio;
    /**
     * @brief Instància de la vista de distribució.
     */
    private Distribucio distribucioView;

    // MÈTODES

    /**
     * @brief Constructor de la classe DistribucioViewControler.
     *
     * @pre -
     * @post Inicialitza la vista de distribució i afegeix els listeners necessaris.
     */
    public DistribucioViewControler() {
        cPresentacio = CtrlPresentacio.getInstance();
        distribucioView = new Distribucio();
        addListeners();
    }

    /**
     * @brief Reinicia la vista de distribució al seu estat actualitzat.
     *
     * @pre La distribució activa està definida al controlador de presentació.
     * @post La vista de distribució es reinicia mostrant les dades actualitzades.
     */
    public void reloadDistribucioView() {
        distribucioView.reload(cPresentacio.getIdActiveDistribution(), cPresentacio.getActiveDistribution());
        distribucioView.centerPanel.revalidate();
        distribucioView.centerPanel.repaint();
    }

    /**
     * @brief Afegeix els listeners als components de la vista per gestionar esdeveniments de l'usuari.
     *
     * @pre La vista de distribució està inicialitzada.
     * @post Els components gràfics de la vista tenen listeners assignats.
     */
    public void addListeners() {
        // Listener per eliminar productes
        distribucioView.eliminarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                String idProducte = (String) JOptionPane.showInputDialog(
                        distribucioView,
                        "Indiqui el nom del producte:",
                        "Eliminar Producte",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        ""
                );

                if (idProducte != null && !idProducte.trim().isEmpty()) {
                    int confirmacion = JOptionPane.showConfirmDialog(
                            distribucioView,
                            "Estàs segur que desitges eliminar el producte \"" + idProducte + "\"? Aquesta acció no es podrà desfer.",
                            "Advertència",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        try {
                            cPresentacio.deleteProducte(idProducte);
                        } catch (ProductNotFoundException evt) {
                            System.err.println("Error: " + evt.getMessage());
                        }

                        distribucioView.distributionPanel.createDistributionPanel(cPresentacio.getActiveDistribution());
                        distribucioView.centerPanel.revalidate();
                        distribucioView.centerPanel.repaint();

                        JOptionPane.showMessageDialog(
                                distribucioView,
                                "El producte \"" + idProducte + "\" ha estat eliminat.",
                                "Confirmació",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                distribucioView,
                                "Eliminació cancel·lada.",
                                "Cancel·lació",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            distribucioView,
                            "Nom del producte no vàlid. Operació cancel·lada.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        // Listener per clonar distribucio
        distribucioView.clonarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String NewidProducte = (String) JOptionPane.showInputDialog(
                        distribucioView,
                        "Indiqui el nom de la nova distribució:",
                        "Clonar Distribució",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        ""
                );
                if (NewidProducte != null && !NewidProducte.trim().isEmpty()) {

                    int confirmacion = JOptionPane.showConfirmDialog(
                            distribucioView,
                            "Estàs segur que desitges clonar la distribució",
                            "Advertència",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        try {
                            cPresentacio.duplicarDistribucio(NewidProducte);

                        } catch (ProductNotFoundException evt) {
                            System.err.println("Error: " + evt.getMessage());
                        }

                        distribucioView.id.setText(NewidProducte);
                        distribucioView.distributionPanel.createDistributionPanel(cPresentacio.getDistribucio(NewidProducte));
                        distribucioView.topPanel.revalidate();
                        distribucioView.topPanel.repaint();
                        distribucioView.centerPanel.revalidate();
                        distribucioView.centerPanel.repaint();

                        JOptionPane.showMessageDialog(
                                distribucioView,
                                "La distribució ha estat clonada.",
                                "Confirmació",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                distribucioView,
                                "Clonació cancel·lada.",
                                "Cancel·lació",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                } else {

                    JOptionPane.showMessageDialog(
                            distribucioView,
                            "Nom de la nova distribució no es valid. Operació cancel·lada.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                }
            }
        });

        // Listener per modificar distribucio
        distribucioView.modificarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String producte = JOptionPane.showInputDialog(null, "Introdueix el nom del producte que vols moure:");

                if (producte != null && !producte.trim().isEmpty()) {

                String inputX = JOptionPane.showInputDialog(null, "Introdueix la nova coordenada X.\n" +
                        "Instruccions:\nM = numero de files\nX = Fila0...FilaM-1");
                int x;
                try {
                    x = Integer.parseInt(inputX);
                } catch (NumberFormatException et) {
                    JOptionPane.showMessageDialog(null, "La coordenada X no és vàlida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String inputY = JOptionPane.showInputDialog(null, "Introdueix la nova coordenada Y:" +
                        "\nN = numero de columnas\nY = Columna0...ColumnaN" +
                        "\nInstruccions:");

                int y;
                try {
                    y = Integer.parseInt(inputY);
                } catch (NumberFormatException et) {
                    JOptionPane.showMessageDialog(null, "La coordenada Y no és vàlida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    cPresentacio.canviarDeLlocProducte(producte, x, y);
                    distribucioView.distributionPanel.createDistributionPanel(cPresentacio.getActiveDistribution());
                    distribucioView.centerPanel.revalidate();
                    distribucioView.centerPanel.repaint();
                } catch (InvalidPositionException et) {
                    JOptionPane.showMessageDialog(null, et.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "El nom del producte no pot estar buit.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        });

        // Listener per canviar el id de la distribució
        distribucioView.canviarIdBitton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String NewIdDistribucio = JOptionPane.showInputDialog(null, "Introdueix el nou nom de la distribució:");

                if (NewIdDistribucio != null && !NewIdDistribucio.trim().isEmpty()) {

                    try {
                        cPresentacio.changeIdDistribucio(NewIdDistribucio);
                        distribucioView.id.setText(NewIdDistribucio);
                        distribucioView.topPanel.revalidate();
                        distribucioView.topPanel.repaint();
                    } catch (DistribucioAlreadyExistsException et) {
                        JOptionPane.showMessageDialog(null, "Ja existeix una distribució " +
                                "amb aquest nom", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Listener per eliminar la distribució
        distribucioView.eliminarDistribucioButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                    int confirmacion = JOptionPane.showConfirmDialog(
                            distribucioView,
                            "Estàs segur que desitges eliminar la distribució?\nAquesta acció no es podrà desfer.",
                            "Advertència",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (confirmacion == JOptionPane.YES_OPTION) {

                        try {
                            cPresentacio.deleteDistribucio(distribucioView.id.getText());
                            cPresentacio.goToMenuDistribucions();
                        } catch (ProductNotFoundException evt) {
                            System.err.println("Error: " + evt.getMessage());
                        }

                        JOptionPane.showMessageDialog(
                                distribucioView,
                                "La distribució ha estat eliminada.",
                                "Confirmació",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                distribucioView,
                                "Eliminació cancel·lada.",
                                "Cancel·lació",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
            }
        });

    }

    /**
     * @brief Obté la vista de distribució associada a aquest controlador.
     *
     * @pre La vista de distribució està inicialitzada.
     * @post Retorna la vista de distribució.
     * @return La instància de la vista de distribució.
     */
    public Distribucio getDistribucioView() {
        return distribucioView;
    }

}
