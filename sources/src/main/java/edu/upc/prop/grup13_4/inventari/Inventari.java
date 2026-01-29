//Inventari
//Author: Dylan Bautista Cases

package edu.upc.prop.grup13_4.inventari;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.upc.prop.grup13_4.exceptions.inventari.*;
import edu.upc.prop.grup13_4.utils.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/*! @brief Definició de la classe Inventari

 Aquest arxiu defineix la classe Inventari.
 */
public class Inventari {
    //Class attributes
    private final String id;
    private final String username;
    private final ConjuntProductes productes;

    //Constructors
    public Inventari(String id, String username) {
        this.id = id;
        this.username = username;
        productes = new ConjuntProductes();
    }


    //Getters

    /**
     * @brief Obtenir identificador de l'Inventari
     *
     * Aquest mètode retorna l'identificador de l'Inventari.
     */
    public String getId() {
        return id;
    }

    /**
     * @brief Obtenir dades relació - accés matricial ordenat.
     *
     * Donat un objecte *i*, obtenir les dades de les relacions *0 < j < (n - 1)* ordenades segons el seu grau
     * de similitud. On n és el nombre de productes del conjunt.
     *
     * Es retorna un objecte Pair, on *first* és l'identificador de l'altre objecte relacionat i on *second* és
     * el grau de similitud entre el productes.
     *
     * Un producte mai està relacionat amb si mateix.
     *
     * @param i Índex del producte.
     * @param j j-èssim producte relacionat amb grau més petit.
     *
     * @throws ProductNotFoundException Si no existeix un producte amb índex *i*.
     * @throws ProductIndexOutOfRangeException Si el producte *j-èssim* d'*i* no exiteix.
     *
     */
    public Pair<Integer, Integer> getAdj(int i, int j) {
        return productes.getAdj(i, j);
    }

    /**
     * @brief Obtenir relació a partir dels seus índexs.
     *
     * Aquest mètode permet obtenir la relació entre dos productes, mitjançant els seus índexs.
     *
     * Cal notar que es retornarà la mateixa Relació indiferentment de l'ordre en què s'indiquin
     * els índexs dels productes (propietat simètrica).
     *
     * @param i Índex del primer producte.
     * @param j Índex del segon producte.
     *
     * @throws ProductNotFoundException Si no existeix un producte amb índex *i* o *j*.
     *
     */
    public int getRelacio(int i, int j) {
        return productes.getRelacio(i, j);
    }

    /**
     * @brief Obtenir relació a partir dels seus identificadors.
     *
     * Aquest mètode permet obtenir la relació entre dos productes, mitjançant els seus identificadors.
     *
     * Cal notar que es retornarà la mateixa Relació indiferentment de l'ordre en què s'indiquin
     * els identificadors dels productes (propietat simètrica).
     *
     * @param id1 Identificador del primer producte.
     * @param id2 Identificador del segon producte.
     *
     * @throws ProductNotFoundException Si no existeix un producte amb identificador *id1* o *id2*.
     *
     */
    public int getRelacio(String id1, String id2) {
        return productes.getRelacio(productes.getProducte(id1), productes.getProducte(id2)).getGrau();
    }

    /**
     * @brief Obtenir nombre de productes existents.
     *
     * Aquest mètode retorna el nombre de productes existents a l'Inventari (paràmtre implícit).
     *
     */
    public int getNumProductes() {
        return productes.getSize();
    }


    /**
     * @brief Obtenir nom d'usuari creador de l'Inventari.
     *
     * Aquest mètode retorna el nom de l'usuari (no es comprova) que va crear l'Inventari.
     *
     */
    public String getRelatedUsername() {
        return username;
    }

    /**
     * @brief Obtenir identificador d'un Producte a partir del seu índex.
     *
     * Aquest mètode retorna l'identificador del producte amb índex *i*.
     *
     */
    public String getProducteIndex(int i) {
        return productes.getProducteIndex(i);
    }

    /**
     * @brief Obtenir informació dels Productes de l'Inventari.
     *
     * Aquest mètode retorna una llista amb els identificadors dels Productes presents a l'Inventari.
     *
     */
    public List<String> getInfoProductes() {
        return productes.getInfoProductes();
    }

    /**
     * @brief Obtenir informació de les Relacions entre els Productes.
     *
     * Aquest mètode mostra pel canal estàndard de sortida una representació gràfica de les relacions
     * entre els productes d'Inventari que són menors a Integer.MAX_VALUE (infinit).
     *
     */
    public List<Pair<String, Integer>> getInfoRelacions(String prodId) {
        return productes.getInfoRelacions(prodId);
    }

    //Setters

    /**
     * @brief Modificar una Relació de l'Inventari actiu.
     *
     * Aquest mètode modifica el *grau* de la Relació existent entre el producte *id1* i el producte *id2*.
     *
     * @param id1 Identificador d'un producte de la Relació.
     * @param id2 Identificador de l'altre producte de la Relació.
     * @param grau de similitud entre els Productes.
     *
     * @throws ProductNotFoundException Si no existeix un Producte amb identificador *id1* p *id2*.
     * @throws NoInventariConnectedException Si no hi ha cap Inventari seleccionat.
     *
     */
    public void modRelacio(String id1, String id2, int grau) {
        Producte a = productes.getProducte(id1);
        Producte b = productes.getProducte(id2);
        productes.modRelacio(a, b, grau);
    }

    /**
     * @brief Modificar una Relació de l'Inventari actiu.
     *
     * Aquest mètode modifica el *grau* de la Relació existent entre *prod1* i *prod2*
     *
     * @param prod1 Identificador d'un producte de la Relació.
     * @param prod2 Identificador de l'altre producte de la Relació.
     * @param grau de similitud entre els Productes.
     *
     * @throws ProductNotFoundException Si no existeix un Producte *prod1* p *prod2*.
     *
     */
    public void modRelacio(Producte prod1, Producte prod2, int grau) {
        productes.modRelacio(prod1, prod2, grau);
    }

    /**
     * @brief Afegir Producte a l'Inventari.
     *
     * Aquest mètode afegeix el producte *p* a l'Inventari.
     *
     * @param p Instància del producte a afegir.
     *
     * @throws ProductAlreadyExistsException Ja existeix un producte *p*.
     * @throws IllegalArgumentException L'identificador proveït conté espais.
     *
     */
    public void addProducte(Producte p) {
        productes.addProducte(p);
    }


    /**
     * @brief Eliminar Producte a l'Inventari.
     *
     * Aquest mètode elimina un producte amb identificador *id*.
     *
     * @param id Identificador del producte a eliminar.
     *
     * @throws ProductAlreadyExistsException Ja existeix un producte *p*.
     * @throws IllegalArgumentException L'identificador proveït conté espais.
     *
     */
    public void eliminarProducte(String id) {
        Producte prod = productes.getProducte(id);
        productes.removeProducte(prod);
    }
}
