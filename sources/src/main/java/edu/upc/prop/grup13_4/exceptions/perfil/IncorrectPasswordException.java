/**
 * @file IncorrectPasswordException.java
 * @brief Excepció personalitzada per indicar errors de contrasenya incorrecta.
 */

package edu.upc.prop.grup13_4.exceptions.perfil;

/**
 * @class IncorrectPasswordException
 * @brief Excepció llançada quan una contrasenya proporcionada no és correcta.
 *
 * Aquesta excepció és utilitzada per indicar que la contrasenya introduïda no coincideix
 * amb la contrasenya guardada en el perfil actiu.
 */
public class IncorrectPasswordException extends RuntimeException {

    /**
     * @brief Constructor de la classe IncorrectPasswordException.
     * @param message Missatge d'error que descriu el motiu de l'excepció.
     */
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
