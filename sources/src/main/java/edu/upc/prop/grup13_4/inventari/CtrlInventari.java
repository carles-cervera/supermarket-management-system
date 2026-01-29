package edu.upc.prop.grup13_4.inventari;

import edu.upc.prop.grup13_4.domini.CtrlDomini;
import edu.upc.prop.grup13_4.exceptions.inventari.NoInventariConnectedException;
import edu.upc.prop.grup13_4.exceptions.inventari.ProductAlreadyExistsException;
import edu.upc.prop.grup13_4.exceptions.inventari.ProductIndexOutOfRangeException;
import edu.upc.prop.grup13_4.exceptions.inventari.ProductNotFoundException;
import edu.upc.prop.grup13_4.utils.Pair;

import java.util.List;

/**
 * @brief Controladora de la classe Inventari.
 *
 * Aquesta classe representa la controladora de la classe Inventari. S'encarrega d'emmagatzemar i modificar
 * l'inventari actiu, de crear instàncies de tipus Inventari i de communicar-se amb la controladora general de la
 * Capa de Domini.
 *
 *  @remarks Aquesta classe és un singletó, per conseguent, per poder referenciar a la única instància d'aquest tipus s'ha
 *  * d'emprar el mètode estàtic *getInstance()*.
 *
 */
public class CtrlInventari {
    //Class attributes
    private static CtrlInventari instance;
    private Inventari inv_seleccionat;

    //Constructors

    /*! @brief Constructora de CtrolInventari

    \post Es retorna un conjunt d'identificadors.

     */
    private CtrlInventari() {
    }

    //Methods

    /**
     * @brief Obtenir instància única de CtrlInventari.
     *
     * Retorna una referència a la instància única de CtrlInventari. En cas de no haver-se inicialitzat, s'inicialitza.
     *
     */
    public static CtrlInventari getInstance() {
        if (instance == null) {
            instance = new CtrlInventari();
        }
        return instance;
    }

    public String getRelatedUsername() {
        return inv_seleccionat.getRelatedUsername();
    }

    public String getActiveId() {
        return inv_seleccionat.getId();
    }

    /*! @brief Connectar controlador a un Inventari

    Connecta el controlador a una instància control Inventari.
    No verifica res.

    \post El controlador es connectarà amb l'instància inv.
     */


    public void unselectInventari() {
        inv_seleccionat = null;
    }
    /**
     * @brief Connectar la controladora a un Inventari.
     *
     * Connecta la controladora a una instància d'Inventari concreta. Totes les operacions afectaran
     * a l'Inventari seleccionat.
     *
     * @param inv Una instància d'Inventari ben inicialitzada.
     *
     * @throws NullPointerException Si la instància *inv* és nula.
     *
     */
    public void connect_inventari(Inventari inv) {
        if (inv == null) throw new NullPointerException("Inventari is null");
        inv_seleccionat = inv;
    }


    //Instance methods

    /**
     * @brief Test general.
     *
     * Test general que comprova que hi hagi un inventari seleccionat.
     *
     * @throws NoInventariConnectedException Si no hi ha un inventari seleccionat.
     *
     */
    private void generalCheck() {
        if (inv_seleccionat == null) {
            throw new NoInventariConnectedException();
        }
    }

    /**
     * @brief Crear una instància d'Inventari.
     *
     * Aquest mètode s'encarrega de crear una instància d'Inventari correctament. Normalment, aquest mètode
     * hauria de ser emprat per CtrlDomini per obtenir una instància ben inicialitzada d'Inventari.
     *
     * @param id Identificador del nou inventari.
     * @param username Identificador de l'usuari que crea l'Inventari. (No comprova res)
     *
     * @throws IllegalArgumentException L'identificador proveït conté espais.
     *
     */
    public Inventari crearInventari(String id, String username) {
        if (id.contains(" ")) { throw new IllegalArgumentException("L'identificador de l'inventari no pot" +
                " contenir espais"); }
        //Create instance
        return new Inventari(id,username);
    }

    /**
     * @brief Afegir Producte a l'Inventari actiu
     *
     * Aquest mètode afegeix un producte amb identificador *id* a l'Inventari seleccionat.
     *
     * @param id Identificador de l'Inventari.
     *
     * @throws ProductAlreadyExistsException Ja existeix un producte amb identifcador *id*.
     * @throws NoInventariConnectedException Si no hi ha cap Inventari actiu.
     * @throws IllegalArgumentException L'identificador proveït conté espais.
     * @throws NoInventariConnectedException Si no hi ha un inventari seleccionat.
     *
     */
    public void addProducte(String id) {
        generalCheck();
        if (id.contains(" ")) { throw new IllegalArgumentException("L'identificador del producte no pot" +
                " contenir espais"); }
        Producte p = new Producte(id);
        inv_seleccionat.addProducte(p);
    }

    /**
     * @brief Eliminar Producte de l'Inventari actiu.
     *
     * Aquest mètode elimina el procte amb identificador *id* de l'Inventari actiu.
     *
     * @param id Identificador del producte a eliminar.
     *
     * @throws ProductNotFoundException Si no existeix un Producte amb identificador *id*.
     * @throws NoInventariConnectedException Si no hi ha un inventari seleccionat.
     *
     */
    public void deleteProducte(String id) {
        generalCheck();
        inv_seleccionat.eliminarProducte(id);
    }

    /**
     * @brief Modificar una Relació de l'Inventari actiu.
     *
     * Aquest mètode modifica el *grau* de la Relació existent entre *prod1* i *prod2*
     *
     * @param id1 Identificador d'un producte de la Relació.
     * @param id2 Identificador de l'altre producte de la Relació.
     * @param grau de similitud entre els Productes.
     *
     * @throws ProductNotFoundException Si no existeix un Producte amb identificador *prod1* p *prod2*.
     * @throws NoInventariConnectedException Si no hi ha cap Inventari seleccionat.
     *
     */
    public void modificarRelacio(String id1, String id2, int grau) {
        generalCheck();
        inv_seleccionat.modRelacio(id1, id2, grau);
    }

    /**
     * @brief Obtenir informació dels Productes de l'Inventari actiu.
     *
     * Aquest mètode retorna una llista amb els identificadors dels Productes presents a l'Inventari.
     *
     * @throws NoInventariConnectedException Si no hi ha cap Inventari actiu.
     *
     */
    public List<String> getInfoProductes() {
        generalCheck();
        return inv_seleccionat.getInfoProductes();
    }

    /**
     * @brief Obtenir informació de les Relacions entre els Productes.
     *
     * Aquest mètode mostra pel canal estàndard de sortida una representació gràfica de les relacions
     * entre els productes d'Inventari que són menors a Integer.MAX_VALUE (infinit).
     *
     * @throws NoInventariConnectedException Si no hi ha cap Inventari actiu.
     *
     * @remark Aquest mètode no forma part dels casos d'ús del programa. Tanmateix, és útil per als drivers.
     * S'eliminarà un cop el programa funcioni gràficament.
     *
     */
    public List<Pair<String, Integer>> getInfoRelacions(String prodId) {
        generalCheck();
        return inv_seleccionat.getInfoRelacions(prodId);
    }


    /**
     * @brief Obtenir Inventari seleccionat.
     *
     * Aquest mètode retorna una referència a la instància Inventari seleccionada.
     *
     */
    public Inventari getInventariSeleccionat() {
        return inv_seleccionat;
    }

    public Integer getNumProductes() {
        generalCheck();
        return inv_seleccionat.getNumProductes();
    }
}
