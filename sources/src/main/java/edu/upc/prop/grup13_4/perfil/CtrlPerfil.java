/**
 * @file CtrlPerfil.java
 * @brief Controlador de perfils per gestionar operacions relacionades amb el perfil d'usuari.
 */

package edu.upc.prop.grup13_4.perfil;

import edu.upc.prop.grup13_4.exceptions.perfil.*;

public class CtrlPerfil {

    private Perfil activeProfile; ///< Perfil actualment actiu.
    private static CtrlPerfil instance; ///< Instància singleton de CtrlPerfil.
    private boolean modifiedPerfil; ///< Indica si el perfil ha estat modificat.

    /**
     * @brief Constructor privat de la classe (patró Singleton).
     */
    private CtrlPerfil() {
        activeProfile = null;
        modifiedPerfil = false;
        instance = this;
    }

    /**
     * @brief Mètode per obtenir la instància del Singleton CtrlPerfil.
     * @return Retorna la instància única de CtrlPerfil.
     */
    public static CtrlPerfil getInstance() {
        if (instance == null) {
            instance = new CtrlPerfil();
        }
        return instance;
    }

    /**
     * @brief Verifica si una contrasenya compleix els requisits mínims.
     * @param password La contrasenya a validar.
     * @return Retorna true si la contrasenya compleix els requisits, false altrament.
     *
     * Requisits:
     * - Longitud mínima de 8 caràcters.
     * - Ha de contenir almenys una majúscula.
     * - Ha de contenir almenys un dígit.
     */
    public static boolean passwordRequirements(String password) {
        if (password.length() < 8) {
            return false; // La contrasenya és massa curta.
        }

        boolean hasUppercase = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        return hasUppercase && hasDigit;
    }

    /**
     * @brief Comprova si la contrasenya és correcta per al perfil donat.
     * @param P El perfil que s'està verificant.
     * @param password La contrasenya introduïda per l'usuari.
     * @throws IncorrectPasswordException Si la contrasenya no coincideix amb la del perfil.
     */
    public void checkPassword(Perfil P, String password) throws IncorrectPasswordException {
        if (!P.getPassword().equals(password)) {
            throw new IncorrectPasswordException("La contrasenya és incorrecta");
        }
        activeProfile = P;
        modifiedPerfil = false;
    }

    /**
     * @brief Canvia la contrasenya de l'usuari actiu.
     * @param newPassword La nova contrasenya.
     * @param oldPassword L'antiga contrasenya.
     * @throws InvalidPasswordException Si l'antiga contrasenya no coincideix o la nova no compleix els requisits.
     */
    public void changePassword(String oldPassword, String newPassword) throws InvalidPasswordException {
        if (!activeProfile.getPassword().equals(oldPassword)) {
            throw new InvalidPasswordException("La contrasenya antiga és incorrecta");
        }
        if (!passwordRequirements(newPassword)) {
            throw new InvalidPasswordException("La nova contrasenya no compleix amb els requisits");
        }
        activeProfile.setPassword(newPassword);
    }

    /**
     * @brief Registra un nou perfil.
     * @param realname Nom real de l'usuari.
     * @param username Nom d'usuari.
     * @param password Contrasenya de l'usuari.
     * @throws InvalidPasswordException Si la contrasenya no compleix els requisits mínims.
     */
    public void signUp(String realname, String username, String password) throws InvalidPasswordException {
        if (!passwordRequirements(password)) {
            throw new InvalidPasswordException("La contrasenya no compleix amb els requisits");
        }
        activeProfile = new Perfil(realname, username, password);
        modifiedPerfil = false;
    }

    /**
     * @brief Tanca la sessió de l'usuari actual.
     */
    public void signOut() {
        activeProfile = null;
    }

    /**
     * @brief Recorda la contrasenya de l'usuari actiu.
     * @return La contrasenya actual de l'usuari actiu.
     */
    public String remindPassword() {
        return activeProfile.getPassword();
    }

    /**
     * @brief Elimina el perfil actiu i retorna el nom d'usuari.
     * @return El nom d'usuari del perfil eliminat.
     */
    public String deletePerfil() {
        String username = activeProfile.getUsername();
        activeProfile = null;
        return username;
    }

    /**
     * @brief Obté el perfil actiu.
     * @return L'objecte Perfil que està actualment actiu.
     */
    public Perfil getActiveProfile() {
        return activeProfile;
    }

    /**
     * @brief Obté el nom real del perfil actiu.
     * @return El nom real de l'usuari actiu.
     */
    public String getRealnameActiveProfile() {
        return activeProfile.getRealname();
    }

    /**
     * @brief Obté el nom d'usuari del perfil actiu.
     * @return El nom d'usuari del perfil actiu.
     */
    public String getUsernameActivePerfil() {
        return activeProfile.getUsername();
    }

    /**
     * @brief Comprova si el perfil actiu ha estat modificat.
     * @return Retorna true si el perfil ha estat modificat, false altrament.
     */
    public boolean perfilModificat() {
        return modifiedPerfil;
    }
}
