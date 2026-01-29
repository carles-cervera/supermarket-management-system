/**
 * @file PerfilAlreadyExistsException.java
 * @brief Excepció personalitzada per indicar que un perfil ja existeix.
 */

package edu.upc.prop.grup13_4.exceptions.perfil;

/**
 * @class PerfilAlreadyExistsException
 * @brief Excepció llançada quan es crea un perfil que ja existeix.
 *
 * Aquesta excepció és utilitzada quan s'intenta crear un perfil amb un nom d'usuari
 * únic que ja està registrada al sistema.
 */
public class PerfilAlreadyExistsException extends RuntimeException {

    /**
     * @brief Constructor de la classe PerfilAlreadyExistsException.
     * @param message Missatge d'error que descriu el motiu de l'excepció.
     */
    public PerfilAlreadyExistsException(String message) {
        super(message);
    }
}
