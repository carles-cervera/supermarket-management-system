package edu.upc.prop.grup13_4.algoritme;

import edu.upc.prop.grup13_4.exceptions.algoritme.inventarioAlgoritmeNullException;
import edu.upc.prop.grup13_4.exceptions.algoritme.InvalidNumPrestatgesException;
import edu.upc.prop.grup13_4.inventari.Inventari;

import java.util.ArrayList;
import java.util.List;


//SINGLETON
/** @brief Controlador de la classe Algoritme
 */
public class CtrlAlgoritme {
    //Class attributes
    private static CtrlAlgoritme instance;
    private Algoritme algo;

    //Constructors

    /** @brief Constructora de ctrlAlgoritme
     */
    private CtrlAlgoritme() {
        algo = new Algoritme();
        algo.reset_bestCost();
        instance = this;
    }

    //Methods

    /** @brief Retorna la instancia de ctrlAlgoritme
     */
    public static CtrlAlgoritme getInstance() {
        if (instance == null) {
            instance = new CtrlAlgoritme();
        }
        return instance;
    }

    /** @brief Funció que cridarà a backtracking, obtindrà i tractarà la solució per poder crear una distribució
     * @param inv Instància d'Inventari
     * @param listaDeListas Productes ordenats en prestatges
     * @param numPrestatges Nombre de prestatges
     * @param productes Nombre de productes*
     *
     * @pre listaDelistas és buida
     * @post listaDelistas té els productes repartits en prestatges
     */
    public void solucioBacktracking(Inventari inv, List<List<String>> listaDeListas, int numPrestatges, int productes) {
        if(inv == null) throw new inventarioAlgoritmeNullException("El inventari del algoritme es null");
        if(productes % numPrestatges != 0) throw new InvalidNumPrestatgesException("Numero de Prestatges incorrectes");
        algo.backtracking(inv);
        montaPrestatge(inv,listaDeListas,numPrestatges,productes);
    }

    /** @brief Funció que cridarà a aproximmate, obtindrà i tractarà la solució per poder crear una distribució
     * @param inv Instància d'Inventari
     * @param listaDeListas Productes ordenats en prestatges
     * @param numPrestatges Nombre de prestatges
     * @param productes Nombre de productes*
     *
     * @pre listaDelistas és buida
     * @post listaDelistas té els productes repartits en prestatges
     */
    public void solucioAproximate(Inventari inv, List<List<String>> listaDeListas, int numPrestatges, int productes) {
        if(inv == null) throw new inventarioAlgoritmeNullException("El inventari del algoritme es null");
        if(productes % numPrestatges != 0) throw new InvalidNumPrestatgesException("Numero de Prestatges incorrectes");
        algo.aproximate(inv);
        montaPrestatge(inv,listaDeListas,numPrestatges,productes);
    }

    /** @brief Encapsula els productes ja ordenats en prestatges
     * @param inv Instància d'Inventari
     * @param listaDeListas Productes ordenats en prestatges
     * @param numPrestatges Nombre de prestatges
     * @param productes Nombre de productes
     *
     * @pre listaDelistas és buida
     * @post listaDelistas té els productes repartits en prestatges
     */
    public void montaPrestatge(Inventari inv, List<List<String>> listaDeListas, int numPrestatges, int productes) {
        int[] idProducts = algo.getBestcycle().clone();
        int szPrestatge = productes / numPrestatges;

        for(int i = 0; i < numPrestatges; ++i) {
            List<String> prestatge = new ArrayList<>();

            for(int j = 0; j < szPrestatge; ++j) {
                int index = (i * szPrestatge) + j;
                if(i % 2 == 0) {//Normal
                    prestatge.add(inv.getProducteIndex(idProducts[index]));
                }else {//Inversa
                    prestatge.add(0,inv.getProducteIndex(idProducts[index]));
                }
            }
            listaDeListas.add(prestatge);
        }
    }

    //Getters

    /** @brief Retorna el bestCost de la instància algoritme
     */
    public int getBestCostx() {
        return algo.getBestCost();
    }
}