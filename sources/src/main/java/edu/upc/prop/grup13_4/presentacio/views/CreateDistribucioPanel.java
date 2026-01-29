//CreateDistribucioPanel
//Author: Max Velasco

package edu.upc.prop.grup13_4.presentacio.views;

import javax.swing.*;
import java.awt.*;

/**
 * @brief Panell per crear una distribució
 *
 * Aquest panell proporciona una interfície gràfica per introduir l'ID de la distribució, seleccionar un mètode
 * (Aproximat o Backtracking) i especificar el nombre de prestatges. Inclou opcions per crear la Distribució o tancar aquesta finestra.
 */
public class CreateDistribucioPanel extends JFrame {
    //CLASS ATRIBUTES

    /**
     * @brief Botó per tancar la finestra.
     */
    public JButton closeButton;
    /**
     * @brief Botó per crear la Distribució.
     */
    public JButton createDistribucioButton;
    /**
     * @brief Etiqueta títol pel mètode.
     */
    private JLabel metodeLabel;
    /**
     * @brief Etiqueta títol per l'ID de la Distribució.
     */
    private JLabel idLabel;
    /**
     * @brief Títol de la finestra.
     */
    private JLabel tittle;
    /**
     * @brief Panel de fons on està situat tot.
     */
    private JPanel fondo;
    /**
     * @brief Panel de dalt on està situat el títol.
     */
    private JPanel topPanel;
    /**
     * @brief Panel de baix on està situat el botó de tancar.
     */
    private JPanel bottomPanel;
    /**
     * @brief Separador per separar el panel de dalt amb el de fons.
     */
    private JSeparator separator1;
    /**
     * @brief Separador per separar el panel de baix amb el de fons.
     */
    private JSeparator separator2;
    /**
     * @brief Camp de dades per introduïr l'ID de la Distribució.
     */
    public JTextField idField;
    /**
     * @brief Botó per indicar que vols la distribució feta per l'algorisme Aproximat.
     */
    public JToggleButton aproximatButton;
    /**
     * @brief Botó per indicar que vols la distribució feta per l'algorisme Backtracking.
     */
    public JToggleButton backtrackingButton;
    /**
     * @brief Spinner per introduïr el nombre de prestatges.
     */
    public JSpinner nPrestatges;
    /**
     * @brief Etiqueta títol que diu "prestatges:".
     */
    private JLabel prestatgesLabel;

    //METHODS

    /**
     * @brief Constructor de la classe CreateDistribucioPanel.
     *
     * @pre -
     * @post Inicialitza els components del panell i centra la finestra a la pantalla.
     */
    public CreateDistribucioPanel() {

        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * @brief Reinicia l'estat del panell a la seva configuració inicial.
     *
     * @pre El panell ha estat inicialitzat.
     * @post El camp d'ID queda buit, els botons de selecció es desactiven i l'Spinner es reinicia al seu valor per defecte.
     */
    public void reload() {
        nPrestatges.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        idField.setText("");
        aproximatButton.setSelected(false);
        backtrackingButton.setSelected(false);
    }

    /**
     * @brief Inicialitza i configura els components gràfics del panell.
     *
     * @pre -
     * @post Tots els components es creen, s'estilitzen i s'organitzen dins del disseny del panell.
     */
    private void initComponents() {

        fondo = new JPanel();
        metodeLabel = new JLabel();
        aproximatButton = new JToggleButton();
        backtrackingButton = new JToggleButton();
        createDistribucioButton = new JButton();
        topPanel = new JPanel();
        separator2 = new JSeparator();
        closeButton = new JButton();
        bottomPanel = new JPanel();
        tittle = new JLabel();
        separator1 = new JSeparator();
        idLabel = new JLabel();
        idField = new JTextField();
        nPrestatges = new JSpinner();
        prestatgesLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        fondo.setBackground(new java.awt.Color(230, 240, 255));

        metodeLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
        metodeLabel.setText("SELECCIONA UN MÈTODE");

        aproximatButton.setBackground(new java.awt.Color(176, 190, 197));
        aproximatButton.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        aproximatButton.setForeground(new java.awt.Color(0, 0, 0));
        aproximatButton.setText("Aproximat");

        backtrackingButton.setBackground(new java.awt.Color(176, 190, 197));
        backtrackingButton.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        backtrackingButton.setForeground(new java.awt.Color(0, 0, 0));
        backtrackingButton.setText("Backtracking");

        prestatgesLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
        prestatgesLabel.setText("NOMBRE DE PRESTATGES");

        createDistribucioButton.setBackground(new java.awt.Color(59, 89, 182));
        createDistribucioButton.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18));
        createDistribucioButton.setForeground(new java.awt.Color(255, 255, 255));
        createDistribucioButton.setText("Crear Distribució");
        createDistribucioButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        separator2.setForeground(new java.awt.Color(0, 0, 0));

        closeButton.setBackground(new java.awt.Color(255, 0, 0));
        closeButton.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        closeButton.setForeground(new java.awt.Color(255, 255, 255));
        closeButton.setText("Tancar");

        GroupLayout topPanelLayout = new GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
                topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(separator2, GroupLayout.Alignment.TRAILING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(closeButton)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
                topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addComponent(separator2, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                                                .addComponent(closeButton)
                                                .addContainerGap())))
        );

        tittle.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18));
        tittle.setText("Crear Distribució");

        separator1.setForeground(new java.awt.Color(0, 0, 0));

        nPrestatges.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));

        GroupLayout bottomPanelLayout = new GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
                bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(bottomPanelLayout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(tittle)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(separator1)
        );
        bottomPanelLayout.setVerticalGroup(
                bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(bottomPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tittle)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        idLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
        idLabel.setText("IDENTIFICADOR DE LA DISTRIBUCIÓ");

        GroupLayout fondoLayout = new GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
                fondoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(topPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bottomPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(fondoLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(backtrackingButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addComponent(aproximatButton)
                                .addGap(49, 49, 49))
                        .addGroup(fondoLayout.createSequentialGroup()
                                .addGroup(fondoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(fondoLayout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addGroup(fondoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(nPrestatges, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(idLabel)
                                                        .addComponent(metodeLabel)
                                                        .addComponent(idField, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(prestatgesLabel)))
                                        .addGroup(fondoLayout.createSequentialGroup()
                                                .addGap(96, 96, 96)
                                                .addComponent(createDistribucioButton)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fondoLayout.setVerticalGroup(
                fondoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(fondoLayout.createSequentialGroup()
                                .addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(idLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(prestatgesLabel)
                                .addGap(18, 18, 18)
                                .addComponent(nPrestatges, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(metodeLabel)
                                .addGap(18, 18, 18)
                                .addGroup(fondoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(backtrackingButton)
                                        .addComponent(aproximatButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addComponent(createDistribucioButton)
                                .addGap(20, 20, 20)
                                .addComponent(topPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fondo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fondo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
}
