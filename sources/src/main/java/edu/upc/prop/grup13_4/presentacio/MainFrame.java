package edu.upc.prop.grup13_4.presentacio;
import edu.upc.prop.grup13_4.presentacio.views.LogInPanel;
import edu.upc.prop.grup13_4.presentacio.views.PrincipalPanel;
import edu.upc.prop.grup13_4.presentacio.views.SignUpPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {
    public static void main(String[] args) {
        CtrlPresentacio presentacio = CtrlPresentacio.getInstance();
        presentacio.init();

    }

    //Atributos
    public CardLayout cardLayout;
    public JPanel menuPanel;
    public JPanel dynamicPanel;

    public MainFrame(JPanel menuPanel) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogInPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        this.menuPanel = menuPanel;
        cardLayout = new CardLayout();
        dynamicPanel = new JPanel(cardLayout);
    }

    public void init() {

        setTitle("FreshMart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setResizable(true);
        setLocationRelativeTo(null);

        ImageIcon icono = new ImageIcon(getClass().getResource("/presentacio/imatges/logo.png"));
        setIconImage(icono.getImage());

        dynamicPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //menuPanel.setPreferredSize(new Dimension(200, 0));
        menuPanel.setMinimumSize(new Dimension(300, 0));
        menuPanel.setVisible(true);

        // Crear un CardLayout para manejar los paneles


        // Mostrar el panel inicial
        //cardLayout.show(dynamicPanel, "Perfil");
        //setVisible(true);
        setUI();
    }

    public void setUI() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);

        c.fill = GridBagConstraints.BOTH;

        c.weightx = 0.0;
        c.weighty = 1.0;
        c.gridy = 0;
        c.gridx = 0;
        add(menuPanel, c);

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 1;
        add(dynamicPanel, c);
    }

    public void showMenuCL(String id) {
        this.cardLayout.show(dynamicPanel, id);
    }

}

