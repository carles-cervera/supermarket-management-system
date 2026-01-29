// ParseDistribucio
// Author: Carles Cervera

package edu.upc.prop.grup13_4.utils;

import edu.upc.prop.grup13_4.exceptions.distribucio.DistribucioAlreadyExistsException;
import edu.upc.prop.grup13_4.presentacio.CtrlPresentacio;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Classe utilitària per a la importació i processament de fitxers de distribució de productes.
 */
public class ParseDistribucio {
    private String id; // Identificador de la distribució
    private List<List<String>> productes; // Llista de productes organitzada en files
    private final CtrlPresentacio cPresentacio = CtrlPresentacio.getInstance(); // Controlador de presentació

    // CONSTRUCTOR

    /**
     * @brief Constructor de la classe ParseDistribucio.
     *
     * @pre -
     * @post Inicialitza la llista de productes i inicia el procés d'importació del fitxer.
     */
    public ParseDistribucio() {
        productes = new ArrayList<>(); // Inicialitza la llista de productes
        importarArxiu(); // Assegura que el diàleg per importar fitxers és modal
    }

    // MÈTODES

    /**
     * @brief Mostra un diàleg per seleccionar un fitxer i importar-ne el contingut.
     *
     * @pre -
     * @post Llegeix el contingut del fitxer seleccionat, processa la informació i la carrega al sistema.
     */
    public void importarArxiu() {
        JFileChooser selectorArxiu = new JFileChooser();
        selectorArxiu.setDialogTitle("Selecciona un fitxer per importar");

        int resultat = selectorArxiu.showOpenDialog(null);

        if (resultat == JFileChooser.APPROVE_OPTION) {
            File arxiuSeleccionat = selectorArxiu.getSelectedFile();
            try {
                Path rutaArxiu = arxiuSeleccionat.toPath();
                List<String> linies = Files.readAllLines(rutaArxiu); // Llegeix les línies del fitxer

                processarContingut(linies); // Processa el contingut del fitxer

                JOptionPane.showMessageDialog(null, "Fitxer importat correctament.", "Èxit", JOptionPane.INFORMATION_MESSAGE);
            } catch(DistribucioAlreadyExistsException exception) {
                showError("Ja existeix una Distribució amb aquest nom");

            }
            catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error en importar el fitxer: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Importació cancel·lada.", "Informació", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * @brief Processa el contingut del fitxer importat.
     *
     * @pre La llista de línies no és buida.
     * @post Processa, organitza les dades del fitxer i les envia al controlador de presentació.
     * @param linies Llista de línies del fitxer llegit.
     */
    private void processarContingut(List<String> linies) {
        if (linies.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El fitxer està buit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // La primera línia conté l'identificador de la distribució
        id = linies.get(0).trim();

        // Les línies restants contenen els productes
        for (int i = 1; i < linies.size(); i++) {
            String linia = linies.get(i).trim();
            if (!linia.isEmpty()) {
                String[] productesLinia = linia.split("\\s+"); // Divideix els productes per espais
                List<String> producteIndividual = new ArrayList<>();
                for (String producte : productesLinia) {
                    producteIndividual.add(producte);
                }
                productes.add(producteIndividual); // Afegeix la fila de productes
            }
        }

        // Validació per assegurar que 'productes' no sigui null
        if (productes == null || productes.isEmpty()) {
            showError("Error: La llista de productes no és vàlida després de processar el fitxer.");
            return;
        }

        // Importa la distribució processada al controlador
        cPresentacio.importarDistribucio(id, productes);
        // Retorna al menú de distribucions
        cPresentacio.goToDitribucio();
    }

    private void showError(String error) {
        JOptionPane.showMessageDialog(null, error ,"Error!",JOptionPane.ERROR_MESSAGE);
    }
}
