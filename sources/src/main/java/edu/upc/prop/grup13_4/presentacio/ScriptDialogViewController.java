//ScriptDialogViewController
//Author: Max Velasco

package edu.upc.prop.grup13_4.presentacio;

import edu.upc.prop.grup13_4.inventari.InventariParser;
import edu.upc.prop.grup13_4.presentacio.dialogs.ScriptDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @brief
 * La classe ScriptDialogViewController és un SINGLETON i gestiona la interacció entre la finestra ScriptDialog
 * i la lògica de presentació. S'encarrega d'afegir els esdeveniments als components de la finestra,
 * importar el script introduït a través del textArea, i tancar la finestra quan sigui necessari.
 */
public class ScriptDialogViewController {
    //CLASS ATRIBUTES

    /**
     * @brief Instància del Controlador de Presentació que permet la interactivitat entre classes.
     */
    private CtrlPresentacio cPresentacio = CtrlPresentacio.getInstance();
    /**
     * @brief Instància de la finestra ScriptDialog.
     */
    private ScriptDialog scriptDialog;
    /**
     * @brief Instància d'aquesta classe.
     */
    private static ScriptDialogViewController instance;

    //METHODS

    /**
     * @brief Constructora de la classe ScriptDialogViewController.
     *
     * @pre -
     * @post -
     */
    private ScriptDialogViewController() {
    }

    /**
     * @brief Afegeix els listeners als components gràfics de la finestra ScriptDialog.
     *
     * @pre El dialog del Script està inicialitzat.
     * @post Els components gràfics del dialog tenen listeners assignats per a gestionar esdeveniments com tancar el dialog o crear una Distribució.
     */
    private void addListeners() {
        scriptDialog.crearInvButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importar();
                close();
            }
        });
        scriptDialog.closeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                close();
            }
        });
    }

    /**
     * @brief Tanca el dialog
     *
     * @pre El dialog esta present en la pantalla
     * @post El dialog es tanca i surt la pantalla de MenuInventari
     */
    public void close() {
        cPresentacio.goToMenuInventari();
        scriptDialog.dispose();
    }

    /**
     * @brief Inicialitza la finestra ScriptDialog i afegeix els listeners als seus components.
     *
     * @pre La instància de ScriptDialogViewController no ha estat inicialitzada.
     * @post Es crea una nova finestra ScriptDialog, s'afegeixen els listeners per gestionar esdeveniments i es mostra la finestra a la pantalla.
     */
    public void setScriptDialog() {
        scriptDialog = new ScriptDialog(cPresentacio.mainFrame,true);
        addListeners();
        scriptDialog.setLocationRelativeTo(null);
        scriptDialog.setVisible(true);

    }

    /**
     * @brief Importa el script introduït a través del textArea i l'analitza.
     *
     * @pre El text del script ha de ser introduït a la finestra de text (textArea).
     * @post Si el text no és buit, es crea un parser per analitzar el script i es criden les funcions per processar-lo.
     *       Si el text està buit, es genera un error.
     */
    public void importar() {
        if(scriptDialog.textArea.getText().isEmpty()) {
            //tirar error
        }
        InventariParser parser = new InventariParser(scriptDialog.textArea.getText());
        InventariParser.scripting(parser);
    }

    //GETTERS

    /**
     * @brief Obté la instància singleton de la classe ScriptDialogViewController.
     *
     * @pre -
     * @post Si no existeix cap instància, es crea i es retorna. Si ja existeix, es retorna aquesta.
     */
    public static ScriptDialogViewController getInstance() {
        if (instance == null) {
            instance = new ScriptDialogViewController();
        }
        return instance;
    }
}
