package edu.upc.prop.grup13_4.presentacio.views;
import javax.swing.*;
import java.awt.*;

/**
 * @brief Panel de benvinguda a l'aplicació amb una petita descripció.
 */
public class WelcomePanel extends JPanel {
    //CLASS ATRIBUTES
    /**
     * @brief Icona de benvinguda amb el logo del supermercat.
     */
    private JLabel welcomeMessage;
    /**
     * @brief Permet el desplaçament sobre el text de la descripció si és necessari.
     */
    private JScrollPane jScrollPane1;
    /**
     * @brief Caixa amb text informatiu sobre l'aplicació.
     */
    private JTextArea description;
    /**
     * @brief Constructora de la classe.
    */

    //METHODS
    public WelcomePanel() {
        initComponents();
    }

    /**
     * @brief Inicialitza els components gràfics del WelcomePanel.
     * @pre WelcomePanel buit.
     * @post Components inicialitzats i disposats al panel.
     */
    private void initComponents() {

        welcomeMessage = new JLabel();
        jScrollPane1 = new JScrollPane();
        description = new JTextArea();

        welcomeMessage.setIcon(new ImageIcon(getClass().getResource("/presentacio/imatges/logo.png")));
        welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);

        description.setColumns(20);
        description.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        description.setLineWrap(true);
        description.setRows(5);
        description.setText("Benvinguts a l'aplicació de gestió de FreshMart amb eines per organitzar qualsevol inventari del supermercat i distribuir-lo eficientment. En aquesta aplicació podràs:\n\n- Crear una distrubució òptima a partir d'un Inventari.\n\n- Crear inventaris a partir de productes i les seves relacions\n\n- Modficar els inventaris i distribucions\n\n- Importar i guardar aquestes dades\n");
        description.setEditable(false);
        description.setBackground(new java.awt.Color(230, 240, 255));
        jScrollPane1.setViewportView(description);

        setBackground(new java.awt.Color(230, 240, 255));
        setLayout(new BorderLayout());
        add(welcomeMessage, BorderLayout.NORTH);
        add(jScrollPane1, BorderLayout.CENTER);
    }
}
