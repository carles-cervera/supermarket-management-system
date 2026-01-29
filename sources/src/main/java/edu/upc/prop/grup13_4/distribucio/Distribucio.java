/**
 * @file Distribucio.java
 * @brief Classe que representa una distribució de productes.
 */

package edu.upc.prop.grup13_4.distribucio;

import edu.upc.prop.grup13_4.exceptions.distribucio.InvalidPositionException;
import edu.upc.prop.grup13_4.exceptions.inventari.ProductNotFoundException;
import java.util.Collections;
import java.util.List;

/**
 * @brief Classe Distribució.
 */
public class Distribucio implements Cloneable{
    private String idDistribucio; ///< Identificador de la distribució.
    private final List<List<String>> productes; ///< Llista de llistes de productes.
    private final boolean Importat; ///< Indica si la distribució és importada.

    /**
     * @brief Constructor de la classe Distribucio.
     * @param idDistribucio Identificador de la distribució.
     * @param productes Llista de llistes de productes.
     * @param importat Indica si la distribució és importada.
     */
    public Distribucio(String idDistribucio, List<List<String>> productes, boolean importat) {
        this.idDistribucio = idDistribucio;
        this.productes = productes;
        this.Importat = importat;
    }

    /**
     * @brief Mètode per clonar la distribució actual.
     * @return Una còpia de la distribució.
     */
    @Override
    public Distribucio clone() {
        try {
            return (Distribucio) super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println("Error al clonar la distribució: " + e.getMessage());
            return null;
        }
    }

    /**
     * @brief Elimina un producte d'una de les subllistes de la distribució.
     * @param producte Nom del producte a eliminar.
     * @throws ProductNotFoundException Si el producte no es troba en la distribució.
     */
    public void deleteProducte(String producte) throws ProductNotFoundException {
        boolean producteTrobat = false;
        for (List<String> subllista : productes) {
            if (subllista.contains(producte)) {
                subllista.remove(producte);
                producteTrobat = true;
                break;
            }
        }

        if (!producteTrobat) {
            throw new ProductNotFoundException("El producte '" + producte + "' no s'ha trobat en la distribució.");
        }
    }

    /**
     * @brief Canvia la posició d'un producte dins de les subllistes.
     * @param producte Nom del producte a moure.
     * @param x Nova subllista on es col·locarà el producte (fila).
     * @param y Nova posició dins de la subllista (columna).
     * @throws ProductNotFoundException Si el producte no es troba en la distribució.
     * @throws InvalidPositionException Si la posició especificada no és vàlida.
     */

    public void canviarDeLloc(String producte, int x, int y) {
        int index1 = 0;
        for (List<String> subllista : productes) {
            if (subllista.contains(producte)) {
                int index2 = subllista.indexOf(producte);

                // Si la nova posició és a la mateixa subllista
                if (index1 == x && index2 + 1 == y) {
                    Collections.swap(subllista, y, index2);
                    return;
                }

                // Verifiquem si les posicions indicades són vàlides
                if (x >= 0 && x < productes.size()) {
                    List<String> subllista1 = productes.get(x);
                    int size_List = subllista1.size();

                    if (y >= 0 && size_List >= y) {
                        subllista.remove(producte); // Eliminem el producte de la subllista anterior
                        if (y == size_List) {
                            subllista1.add(producte); // Afegim al final si y == size_List
                        } else {
                            subllista1.add(y, producte); // Afegim a la posició indicada
                        }
                        return;
                    } else {
                        throw new InvalidPositionException("La posició 'y' especificada no és vàlida.");
                    }
                }
                throw new InvalidPositionException("La posició 'x' especificada no és vàlida.");
            }
            index1++;
        }

        throw new ProductNotFoundException("El producte '" + producte + "' no s'ha trobat en la distribució.");
    }

    /**
     * @brief Mostra el contingut de la distribució.
     */
    public void mostrarDistribucio() {
        System.out.println("ID de la distribució: " + this.getIdDistribucio());
        System.out.println("Importada: " + (this.Importat ? "Sí" : "No"));
        System.out.println("Productes:");

        for (List<String> subLlista : this.getProductes()) {
            System.out.println("[" + String.join(" , ", subLlista) + "]");
        }
        System.out.println("\n");
    }

    /**
     * @brief Canvia l'identificador de la distribució.
     * @param idDistribucio Nou identificador de la distribució.
     */
    public void changeIdDistribucio(String idDistribucio) {
        this.idDistribucio = idDistribucio;
    }

    /**
     * @brief Obté la llista de llistes de productes.
     * @return Llista de llistes de productes.
     */
    public List<List<String>> getProductes() {
        return productes;
    }

    /**
     * @brief Obté l'identificador de la distribució.
     * @return Identificador de la distribució.
     */
    public String getIdDistribucio() {
        return idDistribucio;
    }
}
