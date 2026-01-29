package edu.upc.prop.grup13_4.presentacio.views;
import edu.upc.prop.grup13_4.presentacio.ProducteIndividualViewController;
import edu.upc.prop.grup13_4.presentacio.RelacionsViewController;
import javax.swing.*;

/**
 * @brief Mostra les relacions d'un producte determinat.
 */
public class ProducteIndividualPanel extends JDialog {
    //CLASS ATRIBUTES

    /**
     * @brief Botó per tancar el panel.
     */
    public JButton tancar;
    /**
     * @brief Etiqueta amb text estàtic.
     */
    private JLabel relDe;
    /**
     * @brief Etiqueta amb el nom del producte.
     */
    private JLabel prod1;
    /**
     * @brief Ídem a prod1.
     */
    private JLabel prod2;
    /**
     * @brief Etiqueta amb text estàtic.
     */
    private JLabel prodLabel;
    /**
     * @brief Panel superior.
     */
    private JPanel panel1;
    /**
     * @brief Panel central.
     */
    private JPanel panel2;
    /**
     * @brief Panel inferior.
     */
    private JPanel panel3;
    /**
     * @brief Organitza els elements superiors.
     */
    private JSeparator separador1;
    /**
     * @brief Organitza els elements inferiors.
     */
    private JSeparator separador2;
    /**
     * @brief Instància pròpia de RelacionsPanel.
     */
    private RelacionsPanel relPanel;

    //METHODS
    /**
     * @brief Constructora de la classe
     * @param parent exigit per JDialog
     * @param modal perquè no es pugui interactuar amb el parent mentre el panel propi estigui obert.
     * @param nomprod el nom del producte que es mostrarà.
     */
    public ProducteIndividualPanel(JFrame parent, boolean modal, String nomprod) {
        super(parent,modal);
        relPanel = new RelacionsPanel();
        initComponents();
        prod1.setText(nomprod);
        prod2.setText(nomprod);
        this.setLocationRelativeTo(null);
    }

    /**
     * @brief Vincula el controlador del panel de relacions amb el propi panel.
     * @param rController El controlador.
     */
    public void bindViewController(RelacionsViewController rController) {
        ProducteIndividualViewController.getInstance().relacionsController.bindPanel(relPanel);
        relPanel.bindViewController(rController);
        relPanel.setUI();
    }

    /**
     * @brief Inicialitza els components gràfics de la finestra.
     * @pre Panel inicialitzat.
     * @post Els components s'ham afegit exitosament al ProducteIndividualPanel.
     */
    private void initComponents() {

        panel1 = new JPanel();
        panel3 = new JPanel();
        prod2 = new JLabel();
        separador1 = new JSeparator();
        prodLabel = new JLabel();
        panel2 = new JPanel();
        separador2 = new JSeparator();
        tancar = new JButton();
        relDe = new JLabel();
        prod1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        panel1.setBackground(new java.awt.Color(230, 240, 255));

        prod2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        prod2.setText("poma");

        separador1.setForeground(new java.awt.Color(0, 0, 0));

        prodLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        prodLabel.setText("Producte:");

        GroupLayout panel3Layout = new GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(separador1, GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(prodLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(prod2)
                                .addGap(98, 98, 98))
        );
        panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(prod2)
                                        .addComponent(prodLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separador1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        separador2.setForeground(new java.awt.Color(0, 0, 0));

        tancar.setBackground(new java.awt.Color(255, 0, 0));
        tancar.setFont(new java.awt.Font("Tahoma", 1, 12));
        tancar.setForeground(new java.awt.Color(255, 255, 255));
        tancar.setText("Tancar");
        tancar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        GroupLayout panel2Layout = new GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(separador2, GroupLayout.Alignment.TRAILING)
                        .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tancar)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(separador2, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(tancar))
                                .addGap(8, 8, 8))
        );

        relDe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        relDe.setText("Relacions de ");

        prod1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        prod1.setText("poma");

        GroupLayout panel1Layout = new GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(panel1Layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addComponent(relDe)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(prod1))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(relPanel, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(26, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(panel3, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(relDe)
                                        .addComponent(prod1))
                                .addGap(18, 18, 18)
                                .addComponent(relPanel, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        setResizable(false);

        pack();
    }
}
