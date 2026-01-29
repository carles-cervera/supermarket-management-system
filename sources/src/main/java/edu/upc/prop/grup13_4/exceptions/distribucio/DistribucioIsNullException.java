/**
 * @file DistribucioIsNullException.java
 * @brief Excepció personalitzada per indicar que una distribució és null.
 */

package edu.upc.prop.grup13_4.exceptions.distribucio;

/**
 * @class DistribucioIsNullException
 * @brief Excepció llançada quan una distribució és null.
 *
 * Aquesta excepció es fa servir quan es intenta operar amb una distribució que no ha estat inicialitzada o és null.
 */
public class DistribucioIsNullException extends RuntimeException {

    /**
     * @brief Constructor de la classe DistribucioIsNullException.
     * @param message Missatge d'error que descriu el motiu de l'excepció.
     */
    public DistribucioIsNullException(String message) {
        super(message);
    }
}
