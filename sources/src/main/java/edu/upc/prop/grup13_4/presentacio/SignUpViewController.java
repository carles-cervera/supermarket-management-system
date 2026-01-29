//SignUpViewController
//Author: Max Velasco

package edu.upc.prop.grup13_4.presentacio;

import edu.upc.prop.grup13_4.exceptions.perfil.InvalidPasswordException;
import edu.upc.prop.grup13_4.exceptions.perfil.PerfilAlreadyExistsException;
import edu.upc.prop.grup13_4.presentacio.views.SignUpPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @brief Controlador per la vista de registre d'usuari
 *
 * Aquesta classe és un SINGLETON i s'encarrega de gestionar la interacció entre la interfície gràfica del panell de registre d'usuari i la lògica de l'aplicació.
 * Proporciona funcionalitats per registrar un nou usuari, gestionar la visibilitat de la contrasenya, validar les dades introduïdes i mostrar errors en cas necessari.
 * També permet reiniciar el panell al seu estat inicial i redirigir l'usuari al panell d'inici de sessió un cop completat el registre amb èxit.
 */
public class SignUpViewController {
    //CLASS ATRIBUTES

    /**
     * @brief Instància del Controlador de Presentació que permet la interactivitat entre classes.
     */
    private CtrlPresentacio cPresentacio = CtrlPresentacio.getInstance();
    /**
     * @brief Instància de la vista de SignUp.
     */
    private SignUpPanel instSignUpPanel;
    /**
     * @brief Instància d'aquesta classe.
     */
    private static SignUpViewController instance;
    /**
     * @brief Boolean per saber si la contrasenya en aquest moment és visible o no.
     */
    private boolean visible = false;

    //METHODS

    /**
     * @brief Constructor de la classe SignUpViewController.
     *
     * @pre -
     * @post Inicialitza el panell de registre i afegeix els listeners necessaris.
     */
    private SignUpViewController() {
        instSignUpPanel = new SignUpPanel();
        addListeners();
    }

    /**
     * @brief Afegeix els listeners als components gràfics del panell de registre.
     *
     * @pre El panell de registre està inicialitzat.
     * @post Els components gràfics del panell tenen listeners assignats per a gestionar esdeveniments.
     */
    public void addListeners() {
        instSignUpPanel.signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrar(evt);
            }
        });
        instSignUpPanel.goToLogInLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cPresentacio.goToLogInPanel();
            }
        });
        instSignUpPanel.nameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (instSignUpPanel.nameField.getText().equals("Introduïu el vostre nom real")) {
                    instSignUpPanel.nameField.setText(""); // Borrar el texto por defecto cuando recibe el foco
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (instSignUpPanel.nameField.getText().isEmpty()) {
                    instSignUpPanel.nameField.setText("Introduïu el vostre nom real"); // Restablecer el texto por defecto si no hay texto
                }
            }
        });
        instSignUpPanel.userField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (instSignUpPanel.userField.getText().equals("Introduïu el vostre nom d'usuari")) {
                    instSignUpPanel.userField.setText(""); // Borrar el texto por defecto cuando recibe el foco
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (instSignUpPanel.userField.getText().isEmpty()) {
                    instSignUpPanel.userField.setText("Introduïu el vostre nom d'usuari"); // Restablecer el texto por defecto si no hay texto
                }
            }
        });
        instSignUpPanel.passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (new String(instSignUpPanel.passwordField.getPassword()).equals("Introduïu la vostra contrasenya")) {
                    instSignUpPanel.passwordField.setText(""); // Borrar el texto por defecto cuando recibe el foco
                    instSignUpPanel.passwordField.setEchoChar('*');
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (instSignUpPanel.passwordField.getPassword().length == 0) {
                    instSignUpPanel.passwordField.setText("Introduïu la vostra contrasenya"); // Restablecer el texto por defecto si no hay texto
                    instSignUpPanel.passwordField.setEchoChar('\0');
                }
            }
        });
        instSignUpPanel.viewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(visible & !(new String(instSignUpPanel.passwordField.getPassword()).equals("Introduïu la vostra contrasenya"))) {
                    instSignUpPanel.passwordField.setEchoChar('*');
                    instSignUpPanel.viewPassword.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/Closed_Eye.png")));
                    visible = !visible;
                }else if(!visible & !(new String(instSignUpPanel.passwordField.getPassword()).equals("Introduïu la vostra contrasenya"))){
                    instSignUpPanel.passwordField.setEchoChar('\0');
                    instSignUpPanel.viewPassword.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/Open_Eye.png")));
                    visible = !visible;
                }
            }
        });
    }

    /**
     * @brief Registra un nou usuari amb les dades proporcionades.
     *
     * @pre -
     * @post Si les dades són vàlides, es crea un nou perfil d'usuari i es redirigeix al panell de log in. Si hi ha errors, es mostra un missatge d'error.
     * @param evt Esdeveniment que desencadena el registre.
     * @throws PerfilAlreadyExistsException Si ja existeix un perfil amb el mateix nom d'usuari.
     * @throws InvalidPasswordException Si la contrasenya no compleix els requisits de validació.
     */
    private void registrar(ActionEvent evt) throws PerfilAlreadyExistsException, InvalidPasswordException {
        String realName = instSignUpPanel.nameField.getText();
        String user = instSignUpPanel.userField.getText();
        String password = new String(instSignUpPanel.passwordField.getPassword());

        if(realName.equals("Introduïu el vostre nom real")) {
            showError("Introdueix el vostre nom");
            return;
        }else if(user.equals("Introduïu el vostre nom d'usuari")) {
            showError("Introdueix un nom d'Usuari");
            return;
        }else if(password.equals("Introduïu la vostra contrasenya")) {
            showError("Introdueix una contrasenya");
            return;
        }
        
        try {
            cPresentacio.signUp(realName,user,password);
            cPresentacio.goToLogInPanel();
        }catch (PerfilAlreadyExistsException exception) {
            showError("Ja existeix un perfil amb aquest nom d'Usuari");
        }catch (InvalidPasswordException exception) {
            showError("La contrasenya ha de tenir una majúscula, un caràcter especial i un numero com a mínim");
        }
    }

    /**
     * @brief Actualitza el panell de registre al seu estat inicial.
     *
     * @pre El panell de registre està inicialitzat.
     * @post El panell de registre es reinicia al seu estat predeterminat.
     */
    public void reloadSignUpView() {
        instSignUpPanel.reload();
    }

    /**
     * @brief Mostra un missatge d'error en una finestra emergent.
     *
     * @pre -
     * @post Es mostra un missatge d'error amb el text proporcionat.
     * @param error El text del missatge d'error a mostrar.
     */
    private void showError(String error) {
        JOptionPane.showMessageDialog(instSignUpPanel, error ,"Error!",JOptionPane.ERROR_MESSAGE);
    }

    //GETTERS

    /**
     * @brief Obté la instància singleton de la classe SignUpViewController.
     *
     * @pre -
     * @post Si no existeix cap instància, es crea i es retorna. Si ja existeix, es retorna aquesta.
     */
    public static SignUpViewController getInstance() {
        if (instance == null) {
            instance = new SignUpViewController();
        }
        return instance;
    }

    /**
     * @brief Obté la instància del logInPanel
     *
     * @pre -
     * @post Es retorna la instància del logInPanel
     */
    public JPanel getSignUpView(){
        return instSignUpPanel;
    }
}
