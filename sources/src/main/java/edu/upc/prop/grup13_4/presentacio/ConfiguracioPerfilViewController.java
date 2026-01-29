//ConfiguracioPerfilViewController
//Author: Carles Cervera

package edu.upc.prop.grup13_4.presentacio;

import edu.upc.prop.grup13_4.exceptions.perfil.InvalidPasswordException;
import edu.upc.prop.grup13_4.presentacio.views.ConfiguracioPerfilPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @class ConfiguracioPerfilViewController
 * @brief Gestiona la interacció entre la vista de configuració de perfil i el model de dades.
 */
public class ConfiguracioPerfilViewController {
    /**
     * @brief Instància del controlador de presentació.
     */
    private CtrlPresentacio cPresentacio;

    /**
     * @brief Panell de configuració del perfil.
     */
    private ConfiguracioPerfilPanel ConfiguracioPerfilView;

    /**
     * @brief Nom real de l'usuari.
     */
    private String nomReal;

    /**
     * @brief Nom d'usuari.
     */
    private String username;

    /**
     * @brief Contrasenya de l'usuari.
     */
    private String contrasenya;

    /**
     * @brief Constructor de la classe ConfiguracioPerfilViewController.
     *
     * @pre -
     * @post Inicialitza el controlador de presentació, el panell de configuració de perfil i afegeix els listeners necessaris.
     */
    public ConfiguracioPerfilViewController() {
        cPresentacio = CtrlPresentacio.getInstance();
        ConfiguracioPerfilView = new ConfiguracioPerfilPanel();
        addListeners();
    }

    /**
     * @brief Inicialitza les dades de l'usuari actiu.
     *
     * @pre -
     * @post Les dades de l'usuari actiu són carregades al controlador.
     */
    private void init() {
        nomReal = cPresentacio.getRealNameActiveProfile();
        username = cPresentacio.getUsernameActiveProfile();
        contrasenya = cPresentacio.remindPassword();
    }

    /**
     * @brief Reinicia la vista de configuració de perfil amb les dades actualitzades.
     *
     * @pre -
     * @post La vista de configuració de perfil es reinicia amb les dades més recents.
     */
    public void reloadConfiguracioPerfil() {
        init();
        ConfiguracioPerfilView.reload(nomReal, username);
    }

    /**
     * @brief Retorna la vista de configuració de perfil.
     *
     * @pre -
     * @post Retorna el panell de configuració de perfil.
     * @return ConfiguracioPerfilPanel Panell de configuració del perfil.
     */
    public ConfiguracioPerfilPanel getConfiguracioPerfilView() {
        return ConfiguracioPerfilView;
    }

    /**
     * @brief Afegeix els listeners als components gràfics del panell de configuració de perfil.
     *
     * @pre El panell de configuració està inicialitzat.
     * @post Els components gràfics tenen listeners assignats per gestionar esdeveniments.
     */
    private void addListeners() {
        ConfiguracioPerfilView.remindPasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ConfiguracioPerfilView.passwordLabel.setText("Contrasenya: " + contrasenya);
                ConfiguracioPerfilView.remindPasswordButton.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/Open_Eye.png")));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ConfiguracioPerfilView.passwordLabel.setText("Contrasenya: ********");
                ConfiguracioPerfilView.remindPasswordButton.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/Closed_Eye.png")));
            }
        });

        ConfiguracioPerfilView.changePasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String contrasenyaActual = JOptionPane.showInputDialog(
                        ConfiguracioPerfilView,
                        "Introdueix la teva contrasenya actual:"
                );

                if (contrasenyaActual == null || contrasenyaActual.trim().isEmpty()) {
                    return;
                }

                String contrasenyaNova = JOptionPane.showInputDialog(
                        ConfiguracioPerfilView,
                        "Introdueix la nova contrasenya:"
                );

                if (contrasenyaNova == null || contrasenyaNova.trim().isEmpty()) {
                    return;
                }

                try {
                    cPresentacio.changePassword(contrasenyaActual, contrasenyaNova);
                    contrasenya = contrasenyaNova;
                } catch (InvalidPasswordException et) {
                    JOptionPane.showMessageDialog(
                            ConfiguracioPerfilView,
                            et.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                JOptionPane.showMessageDialog(
                        ConfiguracioPerfilView,
                        "La contrasenya ha estat canviada.",
                        "Confirmació",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        ConfiguracioPerfilView.eliminarPerfilButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    ConfiguracioPerfilView,
                    "Estas segur que vols eliminar el perfil?",
                    "Advertència",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                cPresentacio.deletePerfil();
            }
        });

        ConfiguracioPerfilView.SignOut.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    ConfiguracioPerfilView,
                    "Estas segur que vols sortir?",
                    "Confirmació",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                cPresentacio.sigOut();
            }
        });
    }
}
