/**
 * @file DistribucioAlreadyExistsException.java
 * @brief Excepció personalitzada per indicar que una distribució ja existeix.
 */

package edu.upc.prop.grup13_4.exceptions.distribucio;

/**
 * @class DistribucioAlreadyExistsException
 * @brief Excepció llançada quan una distribució ja existeix al sistema.
 *
 * Aquesta excepció es fa servir quan es vol crear una nova distribució amb un identificador
 * que ja existeix en el sistema, i es vol evitar duplicar distribucions amb el mateix ID.
 */
public class DistribucioAlreadyExistsException extends RuntimeException {

    /**
     * @brief Constructor de la classe DistribucioAlreadyExistsException.
     * @param message Missatge d'error que descriu el motiu de l'excepció.
     */
    public DistribucioAlreadyExistsException(String message) {
        super(message);
    }
}
