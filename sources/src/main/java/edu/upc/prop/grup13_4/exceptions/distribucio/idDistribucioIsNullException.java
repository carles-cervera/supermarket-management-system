/**
 * @file idDistribucioIsNullException.java
 * @brief Excepció personalitzada per indicar que l'ID de la distribució és nul.
 */

package edu.upc.prop.grup13_4.exceptions.distribucio;

/**
 * @class idDistribucioIsNullException
 * @brief Excepció llançada quan l'ID de la distribució és nul.
 *
 * Aquesta excepció es fa servir quan l'ID d'una distribució no s'ha definit correctament
 * (per exemple, quan és nul), i no es pot processar adequadament.
 */
public class idDistribucioIsNullException extends RuntimeException {

    /**
     * @brief Constructor de la classe idDistribucioIsNullException.
     * @param message Missatge d'error que descriu el motiu de l'excepció.
     */
    public idDistribucioIsNullException(String message) {
        super(message);
    }
}
