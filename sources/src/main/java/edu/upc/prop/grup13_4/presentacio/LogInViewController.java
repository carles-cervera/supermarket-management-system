//LogInViewController
//Author: Max Velasco

package edu.upc.prop.grup13_4.presentacio;

import edu.upc.prop.grup13_4.exceptions.perfil.IncorrectPasswordException;
import edu.upc.prop.grup13_4.exceptions.perfil.PerfilNotFoundException;
import edu.upc.prop.grup13_4.presentacio.views.LogInPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @brief Controlador per la vista d'inici de sessió
 *
 * Aquesta classe és un SINGLETON i gestiona la interacció entre la interfície gràfica del panell d'inici de sessió i la lògica d'aplicació,
 * permetent a l'usuari introduir credencials, mostrar o amagar la contrasenya i gestionar errors en el procés de validació.
 * També permet reiniciar el panell al seu estat inicial i redirigir l'usuari a la finestra principal de l'aplicació un cop completat l'inici de sessió amb èxit.
 */
public class LogInViewController {
    //CLASS ATRIBUTES

    /**
     * @brief Instància del Controlador de Presentació que permet la interactivitat entre classes.
     */
    private CtrlPresentacio cPresentacio = CtrlPresentacio.getInstance();
    /**
     * @brief Instància de la vista de LogIn.
     */
    private LogInPanel instLogInPanel;
    /**
     * @brief Instància d'aquesta classe.
     */
    private static LogInViewController instance;
    /**
     * @brief Boolean per saber si la contrasenya en aquest moment és visible o no.
     */
    private boolean visible = false;

    //METHODS

    /**
     * @brief Constructor de la classe LogInViewController.
     *
     * @pre -
     * @post Inicialitza el panell de logIn i afegeix els listeners necessaris.
     */
    private LogInViewController() {
        instLogInPanel = new LogInPanel();
        addListeners();
    }

    /**
     * @brief Afegeix els listeners als components gràfics del panell de log in.
     *
     * @pre El panell de log in està inicialitzat.
     * @post Els components gràfics del panell tenen listeners assignats per a gestionar esdeveniments.
     */
    public void addListeners() {
        instLogInPanel.logInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniSessio(evt);
            }
        });
        instLogInPanel.goToSignUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cPresentacio.goToSignUpPanel();
            }
        });
        instLogInPanel.userField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (instLogInPanel.userField.getText().equals("Introduïu el vostre nom d'usuari")) {
                    instLogInPanel.userField.setText(""); // Borrar el texto por defecto cuando recibe el foco
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (instLogInPanel.userField.getText().isEmpty()) {
                    instLogInPanel.userField.setText("Introduïu el vostre nom d'usuari"); // Restablecer el texto por defecto si no hay texto
                }
            }
        });
        instLogInPanel.passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (new String(instLogInPanel.passwordField.getPassword()).equals("Introduïu la vostra contrasenya")) {
                    instLogInPanel.passwordField.setText(""); // Borrar el texto por defecto cuando recibe el foco
                    instLogInPanel.passwordField.setEchoChar('*');
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (instLogInPanel.passwordField.getPassword().length == 0) {
                    instLogInPanel.passwordField.setText("Introduïu la vostra contrasenya"); // Restablecer el texto por defecto si no hay texto
                    instLogInPanel.passwordField.setEchoChar('\0');
                }
            }
        });
        instLogInPanel.viewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(visible & !(new String(instLogInPanel.passwordField.getPassword()).equals("Introduïu la vostra contrasenya"))) {
                    instLogInPanel.passwordField.setEchoChar('*');
                    instLogInPanel.viewPassword.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/Closed_Eye.png")));
                    visible = !visible;
                }else if(!visible & !(new String(instLogInPanel.passwordField.getPassword()).equals("Introduïu la vostra contrasenya"))){
                    instLogInPanel.passwordField.setEchoChar('\0');
                    instLogInPanel.viewPassword.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/Open_Eye.png")));
                    visible = !visible;
                }
            }
        });
    }

    /**
     * @brief Actualitza el panell de log in al seu estat inicial.
     *
     * @pre El panell de log in està inicialitzat.
     * @post El panell de log in es reinicia al seu estat predeterminat.
     */
    public void reloadLogInView() {
        instLogInPanel.reload();
    }

    /**
     * @brief Inicia la sessió de l'usuari amb les credencials proporcionades.
     *
     * @pre -
     * @post Si les credencials són correctes, l'usuari accedeix al panell principal. Si són incorrectes, es mostra un missatge d'error.
     * @param evt Esdeveniment que desencadena l'intent de log in.
     * @throws PerfilNotFoundException Si el perfil de l'usuari no es troba.
     * @throws IncorrectPasswordException Si la contrasenya és incorrecta.
     */
    private void iniSessio(ActionEvent evt) throws PerfilNotFoundException, IncorrectPasswordException{
        String user = instLogInPanel.userField.getText();
        String password = new String(instLogInPanel.passwordField.getPassword());
        if(user.equals("Introduïu el vostre nom d'usuari")) {
            showError("Introdueix un nom d'Usuari");
            return;
        }else if(password.equals("Introduïu la vostra contrasenya")) {
            showError("Introdueix una contrasenya");
            return;
        }
        try {
            cPresentacio.logIn(user,password);
            cPresentacio.goToPrincipalPanel();
        }catch (PerfilNotFoundException exception) {
            showError("El nom d'Usuari no existeix");
        }catch (IncorrectPasswordException exception) {
            showError("Contrasenya Incorrecta");
        }
    }

    /**
     * @brief Mostra un missatge d'error en una finestra emergent.
     *
     * @pre -
     * @post Es mostra un missatge d'error amb el text proporcionat.
     * @param error El text del missatge d'error a mostrar.
     */
    private void showError(String error) {
        JOptionPane.showMessageDialog(instLogInPanel, error ,"Error!",JOptionPane.ERROR_MESSAGE);
    }

    //GETTERS

    /**
     * @brief Obté la instància singleton de la classe LogInViewController.
     *
     * @pre -
     * @post Si no existeix cap instància, es crea i es retorna. Si ja existeix, es retorna aquesta.
     */
    public static LogInViewController getInstance() {
        if (instance == null) {
            instance = new LogInViewController();
        }
        return instance;
    }

    /**
     * @brief Obté la instància del logInPanel
     *
     * @pre -
     * @post Es retorna la instància del logInPanel
     */
    public JPanel getLogInView() {
        return instLogInPanel;
    }
}
