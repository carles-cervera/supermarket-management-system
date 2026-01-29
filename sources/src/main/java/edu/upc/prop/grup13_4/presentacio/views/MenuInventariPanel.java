package edu.upc.prop.grup13_4.presentacio.views;
import javax.swing.*;
import java.util.List;

/**
 * @brief Panel amb el menú dels inventaris.
 */
public class MenuInventariPanel extends JPanel {
    //CLASS ATRIBUTES
    /**
     * @brief Botó per anar a veure un inventari individual.
     */
    public JButton veureinv;
    /**
     * @brief Botó que permet crear un nou inventari.
     */
    public JButton crear;
    /**
     * @brief Etiqueta amb el nom de l'inventari seleccionat.
     */
    private JLabel nomInv;
    /**
     * @brief Títol.
     */
    private JLabel inventariLabel;
    /**
     * @brief Llista amb tots els inventaris de l'usuari.
     */
    public JList<String> listInventaris;
    /**
     * @brief Llista que mostra els productes del inventari seleccionat.
     */
    private JList<String> preview;
    /**
     * @brief Panel amb el títol de la part superior.
     */
    private JPanel panelTitol;
    /**
     * @brief Panel que conté els botons de la part inferior.
     */
    private JPanel panel_buttons;
    /**
     * @brief Scrollable per la llista d'inventaris.
     */
    private JScrollPane scrollable1;
    /**
     * @brief Llista que mostra els productes del inventari seleccionat.
     */
    private JScrollPane scrollable2;
    /**
     * @brief Separador que s'utilitza per separar les seccions de l'interfície.
     */
    private JSeparator separador1;
    /**
     * @brief Separador utilitzat per separar els botons en el panell superior.
     */
    private JSeparator separador2;

    /**
     * @brief Botó que permet importar un inventari.
     */
    public JButton importButton;

    //METHODS
    /**
     * @brief Constructora de la classe.
     */
    public MenuInventariPanel() {
        initComponents();
    }

    /**
     * @brief Torna a carregar la llista amb els inventaris de l'user per si ha hagut un canvi.
     * @pre Panel inicialitzat.
     * @post La llista d'inventaris reflecteix les dades actuals de l'usuari.
     * @param inventarios Llista de noms d'inventaris a mostrar.
     */
    public void recargascrollableinvs(List<String> inventarios) {
        listInventaris.setModel(new AbstractListModel<String>() {
            String [] strings = inventarios.toArray(new String[0]);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }

    /**
     * @brief Posa buit el panel de preview.
     * @pre Preview amb possible contingut.
     * @post Preview buida.
     */
    public void recargapreviewbuida() {
        preview.setModel(new AbstractListModel<String>() {
            String [] strings = null;
            public int getSize() { return 0; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }

    /**
     * @brief Carrega una llista amb els productes de l'inventari seleccionat a la llista d'inventaris.
     * @pre inv_seleccionat != null.
     * @post La preview mostra els productes d el'inventari seleccionat.
     * @param productos Llista d'identificadors dels productes a mostrar.
     */
    public void recargapreview(List<String> productos) {
        String aux = listInventaris.getModel().getElementAt(listInventaris.getSelectedIndex());
        nomInv.setText("Inventari seleccionat: " + aux);
        preview.setModel(new AbstractListModel<String>() {
            String [] strings = productos.toArray(new String[0]);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }

    /**
     * @brief Inicialitza els components gràfics de MenuInventariPanel.
     * @pre Components inicialitzats.
     * @post Els components estan inicialitzats i disposats adequadament.
     */
    private void initComponents() {

        panelTitol = new JPanel();
        veureinv = new JButton();
        separador2 = new JSeparator();
        importButton = new JButton();  // Nuevo botón
        crear = new JButton();
        panel_buttons = new JPanel();
        inventariLabel = new JLabel();
        separador1 = new JSeparator();
        scrollable1 = new JScrollPane();
        listInventaris = new JList<>();
        scrollable2 = new JScrollPane();
        preview = new JList<>();
        nomInv = new JLabel();

        setBackground(new java.awt.Color(230, 240, 255));
        veureinv.setBackground(new java.awt.Color(64, 124, 65));
        veureinv.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        veureinv.setForeground(new java.awt.Color(255, 255, 255));
        veureinv.setText("Veure ");
        veureinv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        importButton.setBackground(new java.awt.Color(59, 89, 182));  // Estilo del nuevo botón
        importButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        importButton.setForeground(new java.awt.Color(255, 255, 255));
        importButton.setText("Importar");  // Texto del botón
        importButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        separador2.setForeground(new java.awt.Color(0, 0, 0));

        crear.setBackground(new java.awt.Color(59, 89, 182));
        crear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        crear.setForeground(new java.awt.Color(255, 255, 255));
        crear.setText("Crear Inventari");
        crear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        GroupLayout panelTitolLayout = new GroupLayout(panelTitol);
        panelTitol.setLayout(panelTitolLayout);
        panelTitolLayout.setHorizontalGroup(
                panelTitolLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(separador2)
                        .addGroup(panelTitolLayout.createSequentialGroup()
                                .addContainerGap() // Esto asegura que los botones estén alineados a la izquierda
                                .addComponent(crear, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(importButton, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(veureinv, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
        );
        panelTitolLayout.setVerticalGroup(
                panelTitolLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, panelTitolLayout.createSequentialGroup()
                                .addComponent(separador2, GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelTitolLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(veureinv, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(crear, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(importButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)) // Alineación de los botones a la izquierda
                                .addGap(17, 17, 17))
        );

        inventariLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        inventariLabel.setText("Menu Inventaris");
        separador1.setForeground(new java.awt.Color(0, 0, 0));

        GroupLayout panel_buttonsLayout = new GroupLayout(panel_buttons);
        panel_buttons.setLayout(panel_buttonsLayout);
        panel_buttonsLayout.setHorizontalGroup(
                panel_buttonsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(separador1)
                        .addGroup(panel_buttonsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(inventariLabel))
        );
        panel_buttonsLayout.setVerticalGroup(
                panel_buttonsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panel_buttonsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(inventariLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separador1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollable1.setViewportView(listInventaris);
        scrollable2.setViewportView(preview);
        nomInv.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        nomInv.setText("Inventari Seleccionat:");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel_buttons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelTitol, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(scrollable1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollable2)
                                .addContainerGap())
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(nomInv)
                                .addComponent(panel_buttons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panel_buttons, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(nomInv)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollable1, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                                        .addComponent(scrollable2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelTitol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }
}
