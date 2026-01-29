//ScriptDialog
//Author: Max Velasco

package edu.upc.prop.grup13_4.presentacio.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * @brief
 * La classe ScriptDialog és un SINGLETON i extén JDialog i representa una finestra modal que permet a l'usuari
 * introduir un script per crear un inventari.
 * Conté una àrea de text per escriure el script, botons per tancar la finestra i crear l'inventari,
 * així com una interfície gràfica organitzada amb panells i separadors.
 */
public class ScriptDialog extends JDialog {
    //CLASS ATRIBUTES

    /**
     * @brief Panel de baix on està situat el botó de tancar la finestra
     */
    private JPanel bottomPanel;
    /**
     * @brief Botó per tancar la finestra.
     */
    public JButton closeButton;
    /**
     * @brief Botó per crear l'inventari amb el Script
     */
    public JButton crearInvButton;
    /**
     * @brief Panel de fons on esta situat tot.
     */
    private JPanel fondo;
    /**
     * @brief ScrollPanel associat al textArea.
     */
    private JScrollPane scrollPane;
    /**
     * @brief TextArea per introduïr el Script per crear l'Inventari.
     */
    public JTextArea textArea;
    /**
     * @brief Separador per separar el panel de baix amb el de fons.
     */
    private JSeparator separator1;
    /**
     * @brief Separador per separar el panel de dalt amb el de fons.
     */
    private JSeparator separator2;
    /**
     * @brief Etiqueta títol de la finestra.
     */
    private JLabel tittle;
    /**
     * @brief Panel de dalt.
     */
    private JPanel topPanel;
    
    //METHODS

    /**
     * @brief Constructor de la finestra ScriptDialog.
     *
     * @pre El panell pare de la finestra i l'estat modal s'han de passar com a paràmetres.
     * @post S'ha creat una instància de ScriptDialog amb els components gràfics inicialitzats.
     */
    public ScriptDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * @brief Inicialitza els components gràfics del panell.
     *
     * @pre -
     * @post Els components gràfics del panell són inicialitzats i organitzats.
     */
    private void initComponents() {

        fondo = new JPanel();
        crearInvButton = new JButton();
        topPanel = new JPanel();
        tittle = new JLabel();
        separator2 = new JSeparator();
        bottomPanel = new JPanel();
        separator1 = new JSeparator();
        closeButton = new JButton();
        scrollPane = new JScrollPane();
        textArea = new JTextArea();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        fondo.setBackground(new java.awt.Color(230, 240, 255));

        crearInvButton.setBackground(new java.awt.Color(59, 89, 182));
        crearInvButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        crearInvButton.setForeground(new java.awt.Color(255, 255, 255));
        crearInvButton.setText("Crear Inventari");
        crearInvButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tittle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tittle.setText("Afegeix un SCRIPT per crear un inventari ");

        separator2.setForeground(new java.awt.Color(0, 0, 0));

        GroupLayout topPanelLayout = new GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
                topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(tittle)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(separator2, GroupLayout.Alignment.TRAILING)
        );
        topPanelLayout.setVerticalGroup(
                topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(tittle)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(separator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        separator1.setForeground(Color.BLACK);

        closeButton.setBackground(new java.awt.Color(255, 0, 0));
        closeButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        closeButton.setForeground(new java.awt.Color(255, 255, 255));
        closeButton.setText("Tancar");

        GroupLayout bottomPanelLayout = new GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
                bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(separator1, GroupLayout.Alignment.TRAILING)
                        .addGroup(bottomPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(closeButton)
                                .addContainerGap(383, Short.MAX_VALUE))
        );
        bottomPanelLayout.setVerticalGroup(
                bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(bottomPanelLayout.createSequentialGroup()
                                .addComponent(separator1, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(closeButton)
                                .addGap(8, 8, 8))
        );

        textArea.setColumns(20);
        textArea.setRows(5);
        scrollPane.setViewportView(textArea);

        GroupLayout fondoLayout = new GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
                fondoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(topPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bottomPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(fondoLayout.createSequentialGroup()
                                .addGroup(fondoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(fondoLayout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(fondoLayout.createSequentialGroup()
                                                .addGap(162, 162, 162)
                                                .addComponent(crearInvButton)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fondoLayout.setVerticalGroup(
                fondoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(fondoLayout.createSequentialGroup()
                                .addComponent(topPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(crearInvButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
