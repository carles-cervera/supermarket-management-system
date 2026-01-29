/**
 * @file InvalidPositionException.java
 * @brief Excepció personalitzada per indicar una posició invàlida en la distribució.
 */

package edu.upc.prop.grup13_4.exceptions.distribucio;

/**
 * @class InvalidPositionException
 * @brief Excepció llançada quan una posició especificada no és vàlida en una distribució.
 *
 * Aquesta excepció es fa servir quan es produeix un intent de modificar la distribució
 * amb una posició que no és vàlida. Causada per intentar col·locar un producte
 * en una posició fora de l'abast de la llista de productes.
 */
public class InvalidPositionException extends RuntimeException {

    /**
     * @brief Constructor de la classe InvalidPositionException.
     * @param message Missatge d'error que descriu el motiu de l'excepció.
     */
    public InvalidPositionException(String message) {
        super(message);
    }
}
