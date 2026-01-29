/**
 * @file Perfil.java
 * @brief Classe que representa un perfil d'usuari amb informació bàsica com nom real, nom d'usuari i contrasenya.
 */

package edu.upc.prop.grup13_4.perfil;

public class Perfil {
    private final String realname;  ///< Nom real de l'usuari.
    private final String username;  ///< Nom d'usuari del perfil.
    private String password;  ///< Contrasenya del perfil.

    /**
     * @brief Constructor de la classe Perfil.
     * @param realname El nom real de l'usuari.
     * @param username El nom d'usuari.
     * @param password La contrasenya de l'usuari.
     */
    public Perfil(String realname, String username, String password) {
        this.realname = realname;
        this.username = username;
        this.password = password;
    }

    /**
     * @brief Obté el nom real de l'usuari.
     * @return El nom real de l'usuari.
     */
    public String getRealname() {
        return realname;
    }

    /**
     * @brief Obté el nom d'usuari.
     * @return El nom d'usuari.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @brief Obté la contrasenya de l'usuari.
     * @return La contrasenya de l'usuari.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @brief Actualitza la contrasenya de l'usuari.
     * @param password La nova contrasenya a establir.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
