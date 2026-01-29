package edu.upc.prop.grup13_4.presentacio;
import edu.upc.prop.grup13_4.presentacio.views.PrincipalPanel;
import javax.swing.*;
import java.awt.event.*;

/**
 * @brief Controlador del PrincipalPanel per gestionar la navegabilitat a les latres vistes.
 */
public class PrincipalViewController {
    //CLASS ATRIBUTES
    /**
     * @brief Finestra de PrincipalPanel associada al controlador.
     */
    private PrincipalPanel instancePrincipalPanel;
    /**
     * @brief Instància del controlador de presentació per poder comunicar-se amb les altres classes.
     */
    private final CtrlPresentacio cPresentacio;

    //METHODS
    /**
     * @brief Constructora de la classe.
     */
    public PrincipalViewController() {
        this.cPresentacio = CtrlPresentacio.getInstance();
        instancePrincipalPanel = new PrincipalPanel();
        addListeners();
    }

    /**
     * @brief Afegeix els listeners als labels clicables del panel per poder navegar entre els menús de l'aplicació.
     * @pre Els components del PrincipalPanel han d'estar inicialitzats.
     * @post Tenim assignats MouseListener per quan es cliqui cada component dessignat.
     */
    public void addListeners() {
        instancePrincipalPanel.PerfilLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cPresentacio.goToPerfil();
            }
        });
        instancePrincipalPanel.InvLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cPresentacio.goToMenuInventari();
            }
        });
        instancePrincipalPanel.DistroLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cPresentacio.goToMenuDistribucions();
            }
        });
        instancePrincipalPanel.WelcomeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cPresentacio.goToWelcome();
            }
        });
    }

    //GETTERS
    /**
     * @brief PrincipalPanel getter.
     */
    public JPanel getPanel() {
        return instancePrincipalPanel;
    }
}