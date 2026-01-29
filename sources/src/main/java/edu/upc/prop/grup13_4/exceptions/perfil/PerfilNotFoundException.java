/**
 * @file PerfilNotFoundException.java
 * @brief Excepció personalitzada per indicar que no s'ha trobat un perfil.
 */

package edu.upc.prop.grup13_4.exceptions.perfil;

/**
 * @class PerfilNotFoundException
 * @brief Excepció llançada quan no es troba un perfil a la base de dades o sistema.
 *
 * Aquesta excepció és utilitzada quan s'intenta accedir a un perfil que no existeix
 * al sistema, per exemple, quan un usuari intenta iniciar sessió amb un nom d'usuari
 * que no existeix.
 */
public class PerfilNotFoundException extends RuntimeException {

    /**
     * @brief Constructor de la classe PerfilNotFoundException.
     * @param message Missatge d'error que descriu el motiu de l'excepció.
     */
    public PerfilNotFoundException(String message) {
        super(message);
    }
}
