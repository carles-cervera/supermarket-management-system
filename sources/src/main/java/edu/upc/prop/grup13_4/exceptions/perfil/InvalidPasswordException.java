/**
 * @file InvalidPasswordException.java
 * @brief Excepció personalitzada per indicar errors de contrasenya invàlida.
 */

package edu.upc.prop.grup13_4.exceptions.perfil;

/**
 * @class InvalidPasswordException
 * @brief Excepció llançada quan una contrasenya no compleix els requisits de validació.
 *
 * Aquesta excepció és utilitzada quan una contrasenya introduïda no compleix els requisits mínims
 * de seguretat o no és vàlida segons les regles establertes.
 */
public class InvalidPasswordException extends RuntimeException {

    /**
     * @brief Constructor de la classe InvalidPasswordException.
     * @param message Missatge d'error que descriu el motiu de l'excepció.
     */
    public InvalidPasswordException(String message) {
        super(message);
    }
}
