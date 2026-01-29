package edu.upc.prop.grup13_4.distribucio;

import edu.upc.prop.grup13_4.exceptions.distribucio.DistribucioIsNullException;
import edu.upc.prop.grup13_4.exceptions.distribucio.idDistribucioIsNullException;
import java.util.List;

/**
 * @brief Controladora Distibució.
 */
public class CtrlDistribucio {
    private Distribucio activeDistribucio; ///< Distribució activa actual.
    private static CtrlDistribucio instance; ///< Instància singleton de CtrlDistribucio.
    private boolean modifiedDistribucio; ///< Indica si la distribució ha estat modificada.

    /**
     * @brief Constructor privat per implementar el patró Singleton.
     */
    private CtrlDistribucio() {
        activeDistribucio = null;
        instance = this;
        modifiedDistribucio = false;
    }

    /**
     * @brief Obté la instància singleton de CtrlDistribucio.
     * @return La instància de CtrlDistribucio.
     */
    public static CtrlDistribucio getInstance() {
        if (instance == null) {
            instance = new CtrlDistribucio();
        }
        return instance;
    }

    /**
     * @brief Crea una nova distribució de productes.
     * @param idDistribucio Identificador de la distribució.
     * @param productes Llista de productes organitzada en categories.
     * @param importat Indica si la distribució és importada.
     * @return La nova distribució creada.
     */
    public Distribucio createDistribucio(String idDistribucio, List<List<String>> productes, boolean importat) {
        Distribucio d = new Distribucio(idDistribucio, productes, importat);
        activeDistribucio = d;
        modifiedDistribucio = false;
        return d;
    }

    /**
     * @brief Elimina un producte de la distribució activa.
     * @param producte Nom del producte a eliminar.
     */
    public void deleteProducte(String producte) {
        activeDistribucio.deleteProducte(producte);
        modifiedDistribucio = true;
    }

    /**
     * @brief Canvia la posició d'un producte dins de la distribució activa.
     * @param producte Nom del producte a moure.
     * @param x Nova posició X (fila).
     * @param y Nova posició Y (columna).
     */
    public void canviarDeLloc(String producte, int x, int y) {
        activeDistribucio.canviarDeLloc(producte, x, y);
        modifiedDistribucio = true;
    }

    /**
     * @brief Mostra el contingut d'una distribució específica.
     * @param d Distribució a mostrar.
     */
    public void mostrarDistribucio(Distribucio d) {
        d.mostrarDistribucio();
    }

    /**
     * @brief Mostra el contingut de la distribució activa.
     */
    public void mostrarDistribucioActiva() {
        activeDistribucio.mostrarDistribucio();
    }

    /**
     * @brief Canvia l'identificador de la distribució activa.
     * @param idDistribucio Nou identificador de la distribució.
     * @throws idDistribucioIsNullException Si el nou identificador és null.
     */
    public void changeIdDistribucio(String idDistribucio) {
        if (idDistribucio == null) {
            throw new idDistribucioIsNullException("El nom de la distribució no pot ser null");
        }
        activeDistribucio.changeIdDistribucio(idDistribucio);
        modifiedDistribucio = true;
    }

    /**
     * @brief Duplica la distribució activa amb un nou identificador.
     * @param NewIdDistribucio Nou identificador per a la distribució duplicada.
     * @return La nova distribució duplicada.
     */
    public Distribucio duplicarDistribucio(String NewIdDistribucio) {
        Distribucio d = activeDistribucio.clone();
        d.changeIdDistribucio(NewIdDistribucio);
        return d;
    }

    /**
     * @brief Selecciona una distribució com a activa.
     * @param d Distribució a seleccionar.
     * @throws DistribucioIsNullException Si la distribució és null.
     */
    public void selectDistribucio(Distribucio d) {
        if (d == null) {
            throw new DistribucioIsNullException("La distribució no pot ser null");
        }
        activeDistribucio = d;
    }

    /**
     * @brief Desselecciona la distribució activa.
     */
    public void unselectDistribucio() {
        activeDistribucio = null;
    }

    /**
     * @brief Obté la distribució activa.
     * @return La distribució activa.
     */
    public Distribucio getActiveDistribucio() {
        return activeDistribucio;
    }

    /**
     * @brief Indica si la distribució activa ha estat modificada.
     * @return True si la distribució ha estat modificada, false en cas contrari.
     */
    public boolean getModifiedDistribucio() {
        return modifiedDistribucio;
    }

    /**
     * @brief Obté l'identificador de la distribució activa.
     * @return L'identificador de la distribució activa.
     * @throws DistribucioIsNullException Si no hi ha cap distribució activa.
     */
    public String getIdDistribucio() {
        if (activeDistribucio == null) {
            throw new DistribucioIsNullException("No hi ha cap distribució activa");
        }
        return activeDistribucio.getIdDistribucio();
    }

    public static List<List<String>> getProductes(Distribucio d) {
        return d.getProductes();
    }
}
