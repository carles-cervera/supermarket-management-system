// DistributionPanel
// Author: Carles Cervera

package edu.upc.prop.grup13_4.presentacio.views;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @brief Classe que defineix el panell gràfic per mostrar distribucions de productes.
 */
public class DistributionPanel extends JPanel {

    // CONSTRUCTOR

    /**
     * @brief Constructor de la classe DistributionPanel.
     *
     * @pre -
     * @post Inicialitza el panell amb una configuració predeterminada.
     */
    public DistributionPanel() {
        defaultDistributionPanel();
    }

    // MÈTODES

    /**
     * @brief Configura el panell per mostrar un missatge predeterminat quan no hi ha distribució seleccionada.
     *
     * @pre -
     * @post El panell mostra un missatge d'informació indicant que no hi ha cap distribució seleccionada.
     */
    public void defaultDistributionPanel() {
        // Eliminar tots els components del panell
        removeAll();

        // Configurar el disseny del panell
        setLayout(new BorderLayout());

        // Crear i configurar l'etiqueta informativa
        JLabel label = new JLabel("No hi ha cap Distribucio seleccionada", JLabel.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.black));

        // Afegir l'etiqueta al panell
        add(label, BorderLayout.CENTER);

        // Actualitzar el panell gràficament
        revalidate();
        repaint();
    }

    /**
     * @brief Configura el panell per mostrar una distribució de productes.
     *
     * @pre La llista de productes és vàlida i conté dades organitzades en files.
     * @post Es mostren les dades de la distribució en un format gràfic.
     * @param products Llista de llistes que representa les files i columnes de productes a la distribució.
     */
    public void createDistributionPanel(List<List<String>> products) {
        // Eliminar tots els components del panell
        removeAll();

        // Configurar el disseny del panell
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Crear i afegir components per a cada fila de productes
        for (List<String> filaProductos : products) {
            // Crear un panell per a cada fila
            JPanel filaPanel = new JPanel();
            filaPanel.setLayout(new GridLayout(1, filaProductos.size())); // Fila amb tantes columnes com productes a la fila

            // Crear i afegir etiquetes per a cada producte de la fila
            for (String producto : filaProductos) {
                JLabel label = new JLabel(producto, JLabel.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Afegir un borde negre
                filaPanel.add(label);
            }

            // Afegir el panell de fila al panell principal
            add(filaPanel);
        }

        // Actualitzar el panell gràficament
        revalidate();
        repaint();
    }
}
