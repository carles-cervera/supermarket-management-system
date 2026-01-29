package edu.upc.prop.grup13_4.presentacio;

import edu.upc.prop.grup13_4.domini.CtrlDomini;
import edu.upc.prop.grup13_4.exceptions.inventari.ProductNotFoundException;
import edu.upc.prop.grup13_4.exceptions.inventari.RelacioNotChangedException;
import edu.upc.prop.grup13_4.exceptions.inventari.RelacioUpdateException;
import edu.upc.prop.grup13_4.exceptions.inventari.RelationNotFoundException;
import edu.upc.prop.grup13_4.presentacio.views.RelacionsPanel;
import edu.upc.prop.grup13_4.utils.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

//Although it has no abstract methods, this class is made abstract in order to force the developer
//to create a concrete class with it. It is not designed to be used isolated.


/**
 * @brief Classe abstracta RelacionsViewController.
 *
 * Aquesta classe és abstracta per a obligar al programador a declarar una classe concreta (normalment inner-class)
 * i ampliar, si cal, la funcionalitat oferida.
 *
 * Aquesta classe no està pensada per ser emprada en solitari.
 */
public abstract class RelacionsViewController {

    /**
     * @brief Instància de RelacionsPanel.
     */
    RelacionsPanel panel;

    /**
     * @brief Instància de CtrlDomini.
     */
    CtrlDomini domini = CtrlDomini.getInstance();

    /**
     * @brief Identificador del producte seleccionat.s
     */
    String selectedProd;

    /**
     * @brief Constructora.
     */
    public RelacionsViewController() {
    }

    /**
     * @brief Constructora.
     *
     * @param panel Instància de RelacionsPanel a connectar.
     */
    public RelacionsViewController(RelacionsPanel panel) {
        this.panel = panel;
    }

    /**
     * @brief Connectar amb una instància de RelacionsPanel.
     */
    public void bindPanel(RelacionsPanel panel) {
        this.panel = panel;
    }

    /**
     * @brief Actualitzar dinàmicament els element de la UI.
     *
     * Aquest mètode s'ha de cridar cada cop que es seleccioni un producte diferent.
     */
    public boolean dynamic_update(String prodID) {
        selectedProd = prodID;
        List<Pair<String, Integer>> rel = domini.getInfoRelacions(prodID);

        panel.listModel.clear();

        if (rel != null) {
            for (Pair<String, Integer> pair : rel) {
                if (pair.second < Integer.MAX_VALUE) {
                    panel.listModel.addElement(new RelacionsPanel.RelacioElement(pair.first, String.valueOf(pair.second)));
                }
            }
            return true;
        } else {
            JOptionPane.showMessageDialog(panel, "El producte no té relacions",
                    "Atenció", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    /**
     * @brief Esborrar contingut visual de les relacions mostrades.
     */
    public void set_empty() {
        selectedProd = null;
        panel.listModel.clear();
    }

    /**
     * @brief Afegir listeners als elements UI que ho requereixen.
     */
    public void addListeners() {
        panel.relatedProd.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = panel.relatedProd.locationToIndex(e.getPoint());

                if (SwingUtilities.isRightMouseButton(e)) {
                    if(index != -1 && panel.relatedProd.isSelectedIndex(index)) {
                        panel.showPopupMenu(e.getComponent(), e.getX(), e.getY(), index);
                    }
                }
            }
        });

        panel.removeRel.addActionListener(e -> {
            Object indexProperty = ((JMenuItem) e.getSource()).getClientProperty("index");
            if (indexProperty instanceof Integer index) {
                String otherProd = panel.listModel.get(index).getProductId();
                try {
                    System.out.println("Se ha modificado la relación: " + selectedProd + " " + otherProd);
                    domini.modificarRelacio(selectedProd, otherProd, Integer.MAX_VALUE);
                } catch (ProductNotFoundException e1) {
                    JOptionPane.showMessageDialog(panel, "Producte no existeix",
                            "Error de valor", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (RelationNotFoundException e1) {
                    JOptionPane.showMessageDialog(panel, "Producte no existeix",
                            "Error de valor", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (RelacioUpdateException e1) {
                    //Do nothing, it is OK to update it
                } catch (RelacioNotChangedException e2) {
                    return;
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                    System.exit(1);
                }
                System.out.println(index);
                panel.listModel.remove(index);
            }
        });

        panel.modRel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object indexProperty = ((JMenuItem) e.getSource()).getClientProperty("index");
                if(indexProperty instanceof Integer index) {
                    String nouValor = (String) JOptionPane.showInputDialog(panel,"Indiqui el nou grau de " +
                                    "la realció:","Modificar Relació"
                            ,JOptionPane.PLAIN_MESSAGE, null,null, "10");
                    if (nouValor != null) {
                        if (!esNumero(nouValor)) {
                            JOptionPane.showMessageDialog(panel, "El valor introduit no és vàlid.",
                                    "Error de valor", JOptionPane.ERROR_MESSAGE);
                        } else {

                            String otherProd = panel.listModel.get(index).getProductId();
                            try {
                                domini.modificarRelacio(selectedProd, otherProd, Integer.parseInt(nouValor));
                            } catch (ProductNotFoundException e1) {
                                JOptionPane.showMessageDialog(panel, "Producte seleccionat no existeix",
                                        "Error de valor", JOptionPane.ERROR_MESSAGE);
                                return;
                            } catch (RelationNotFoundException e1) {
                                JOptionPane.showMessageDialog(panel, "Producte seleccionat no existeix",
                                        "Error de valor", JOptionPane.ERROR_MESSAGE);
                                return;
                            } catch (RelacioUpdateException e1) {
                                //Do nothing, it is OK to update it
                            } catch (RelacioNotChangedException e2) {
                                return;
                            } catch (RuntimeException exception) {
                                exception.printStackTrace();
                                System.exit(1);
                            }

                            panel.listModel.get(index).modGrau(Integer.parseInt(nouValor));
                        }
                    }
                }
            }
        });

        panel.relatedProdAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panel.relatedProdGrau.getText() != null && esNumero(panel.relatedProdGrau.getText())) {
                    String prod1 = panel.relatedProdName.getText();
                    Integer grau = Integer.parseInt(panel.relatedProdGrau.getText());

                    try {
                        domini.modificarRelacio(prod1, selectedProd, grau);
                    } catch (ProductNotFoundException exception) {
                        JOptionPane.showMessageDialog(panel, "Producte seleccionat no existeix",
                                "Error de valor", JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (RelacioNotChangedException exception) {
                        return;
                    } catch (RelacioUpdateException exception) {
                        //panel.listModel.get.modGrau(Integer.parseInt(grau));
                        return;
                    } catch (RelationNotFoundException e1) {
                        JOptionPane.showMessageDialog(panel, "Producte seleccionat no existeix",
                                "Error de valor", JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (RuntimeException exception) {
                        exception.printStackTrace();
                        System.exit(1);
                    }

                    panel.listModel.addElement(new RelacionsPanel.RelacioElement(prod1, String.valueOf(grau)));
                }
            }
        });

    }

    /**
     * @brief Indica si el String passat com a paràmtre és un enter.
     */
    private boolean esNumero(String str) {
        return str.matches("-?\\d+");
    }

}
