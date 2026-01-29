package edu.upc.prop.grup13_4.domini;

import edu.upc.prop.grup13_4.distribucio.CtrlDistribucio;
import edu.upc.prop.grup13_4.distribucio.Distribucio;
import edu.upc.prop.grup13_4.exceptions.inventari.*;
import edu.upc.prop.grup13_4.exceptions.perfil.*;
import edu.upc.prop.grup13_4.perfil.CtrlPerfil;
import edu.upc.prop.grup13_4.perfil.Perfil;
import edu.upc.prop.grup13_4.inventari.CtrlInventari;
import edu.upc.prop.grup13_4.inventari.Inventari;
import edu.upc.prop.grup13_4.algoritme.CtrlAlgoritme;
import edu.upc.prop.grup13_4.persistencia.CtrlPersistencia;
import edu.upc.prop.grup13_4.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief Controladora de la Capa de Domini.
 *
 * Aquesta classe és la controladora de la Capa de Domini. Els mètodes públics que es poden trobar corresponen
 * majoritàriament als casos d'ús definits al projecte.
 *
 * Aquesta classe és un singletó, per conseguent, per poder referenciar a la única instància d'aquest tipus s'ha
 * d'emprar el mètode estàtic *getInstance()*.
 *
 */
public class CtrlDomini {
    /**
     * @brief Instància pròpia.
     */
    private static CtrlDomini instance;
    /**
     * @brief Instància del controlador de perfils.
     */
    private final CtrlPerfil cperfil = CtrlPerfil.getInstance();
    /**
     * @brief Instància del controlador de distribucions.
     */
    private final CtrlDistribucio cdistribucio = CtrlDistribucio.getInstance();
    /**
     * @brief Instància del controlador de perisstència.
     */
    private final CtrlPersistencia persistencia = CtrlPersistencia.getInstance();
    /**
     * @brief Instància del controlador d'inventaris.
     */
    private final CtrlInventari cinventari = CtrlInventari.getInstance();
    /**
     * @brief Instància del controlador de l'algoritme.
     */
    private final CtrlAlgoritme calgoritme = CtrlAlgoritme.getInstance();

    //Constructors
    private CtrlDomini() {
    }

    /**
     * @brief Obtenir instància única de CtrlDomini.
     *
     * Retorna una referència a la instància única de CtrlDomini. En cas de no haver-se inicialitzat, s'inicialitza.
     *
     */
    public static CtrlDomini getInstance() {
        if (instance == null) {
            instance = new CtrlDomini();
        }
        return instance;
    }

    //Inventari

    /**
     * @brief Crear Inventari
     *
     * Aquest mètode crea una nova instància d'Inventari amb identificador *id* i la guarda a la capa de persistència.
     *
     * @param id Identificador de l'Inventari.
     * @param username Nom de l'usuari que ha creat aquest invnetari. No comprova res.
     *
     * @throws InventariAlreadyExistsException Si ja existeix un inventari amb el mateix identificador.
     *
     */
    public void crearInventari(String id, String username) {
        //1. Cridar al controlador de la classe Inventari per crear la instància i solicitar ser guardat

        //if (persistencia.inventariExists(id,username)) throw new InventariAlreadyExistsException(id);
        Inventari inv = cinventari.crearInventari(id,username);

        persistencia.strictSaveInventari(inv,username);
    }

    /**
     * @brief Seleccionar Inventari actiu
     *
     * Aquest mètode selecciona l'Inventari amb identificador *id* com a Inventari actiu.
     * Per tant, qualsevol operació d'Inventari executada posteriorment afectarà a l'Inventari seleccionat.
     *
     * @param id Identificador de l'Inventari.
     *
     * @throws InventariNotFoundException Si no existeix l'Inventari.
     *
     */
    public void seleccionarInventari(String id) {
        cinventari.connect_inventari(persistencia.getInventari(id, cperfil.getUsernameActivePerfil()));
    }

    /**
     * @brief Retorna l'identificador de l'inventari seleccionat actualment.
     */
    public String getIDActiveInventari() {
        return cinventari.getActiveId();
    }

    /**
     * @brief Guarda l'inventari seleccionat a persistència.
     * @pre Tenim un inventari seleccionat.
     * @post Inventari guardat a la capa de persistència.
     */
    public void guardarInventari() {
        persistencia.saveInventari(cinventari.getInventariSeleccionat(), cperfil.getUsernameActivePerfil());
    }

    /**
     * @brief Elimina l'inventari seleccionat de persistència.
     * @pre Tenim un inventari seleccionat.
     * @post Inventari eliminat de la capa de persistència.
     */
    public void eliminarInventari() {
        persistencia.deleteInventari(cinventari.getActiveId(), cperfil.getUsernameActivePerfil());
    }

    /**
     * @brief Retorna els identificadors dels inventaris de l'usuari actual.
     */
    public List<String> getInventarisIds (){
        return persistencia.getInventarisIds(cperfil.getUsernameActivePerfil());
    }
    /**
     * @brief Afegir Producte a l'Inventari actiu
     *
     * Aquest mètode afegeix un producte amb identificador *id* a l'Inventari actiu.
     *
     * @param id Identificador de l'Inventari.
     *
     * @throws ProductAlreadyExistsException Ja existeix un producte amb identifcador *id*.
     * @throws NoInventariConnectedException Si no hi ha cap Inventari actiu.
     *
     */
    public void afegirProducte(String id) {
        cinventari.addProducte(id);
    }

    /**
     * @brief Eliminar Producte de l'Inventari actiu.
     *
     * Aquest mètode elimina el procte amb identificador *id* de l'Inventari actiu.
     *
     * @param id Identificador de l'Inventari.
     *
     * @throws ProductNotFoundException Si no existeix un Producte amb identificador *id*.
     * @throws NoInventariConnectedException Si no hi ha cap Inventari actiu.
     *
     */
    public void eliminarProducte(String id) {
        cinventari.deleteProducte(id);
    }

    /**
     * @brief Modificar una Relació de l'Inventari actiu.
     *
     * Aquest mètode modifica una Relació entre dos productes de l'Inventari actiu.
     *
     * @param prod1 Identificador d'un producte de la Relació.
     * @param prod2 Identificador de l'altre producte de la Relació.
     * @param grau de similitud entre els Productes.
     *
     * @throws ProductNotFoundException Si no existeix un Producte amb identificador *prod1* p *prod2*.
     * @throws NoInventariConnectedException Si no hi ha cap Inventari actiu.
     *
     */
    public void modificarRelacio(String prod1, String prod2, int grau) {
        cinventari.modificarRelacio(prod1, prod2, grau);
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
        return cinventari.getInfoProductes();
    }

    /**
     * @brief Obtenir informació de les Relacions entre els Inventaris.
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
        return cinventari.getInfoRelacions(prodId);
    }

    /**
     * @brief Registre d'un nou perfil.
     *
     * Aquest mètode permet crear un nou perfil d'usuari amb les dades proporcionades.
     * Llença una excepció si el perfil ja existeix o si les dades proporcionades no són vàlides.
     *
     * @param realname Nom real de l'usuari.
     * @param username Nom d'usuari (identificador únic).
     * @param password Contrasenya del perfil.
     *
     * @throws PerfilAlreadyExistsException Si ja existeix un perfil amb el mateix nom d'usuari.
     * @throws InvalidPasswordException Si la contrasenya proporcionada no és vàlida.
     */
    public void signUp(String realname, String username, String password) throws PerfilAlreadyExistsException, InvalidPasswordException {
        cperfil.signUp(realname, username, password);
        persistencia.strictsavePerfil(cperfil.getActiveProfile());
    }

    /**
     * @brief Iniciar sessió en un perfil existent.
     *
     * Aquest mètode permet a un usuari iniciar sessió en un perfil amb el nom d'usuari
     * i contrasenya proporcionats. Llença una excepció si el perfil no es troba o
     * si la contrasenya és incorrecta.
     *
     * @param username Nom d'usuari del perfil.
     * @param password Contrasenya del perfil.
     *
     * @throws PerfilNotFoundException Si el perfil amb el nom d'usuari no existeix.
     * @throws IncorrectPasswordException Si la contrasenya no coincideix amb la guardada.
     */
    public void logIn(String username, String password) throws PerfilNotFoundException, IncorrectPasswordException {
        Perfil P = persistencia.getPerfil(username);
        cperfil.checkPassword(P, password);
    }
    /**
     * @brief Canviar la contrasenya del perfil actiu.
     *
     * Permet modificar la contrasenya del perfil actiu. Llença una excepció si
     * la nova contrasenya no compleix els criteris de validació.
     *
     * @param newPassword Nova contrasenya per al perfil.
     * @param oldPassword Contrasenya actual del perfil.
     *
     * @throws InvalidPasswordException Si la nova contrasenya no és vàlida.
     */
    public void changePassword(String newPassword, String oldPassword) throws InvalidPasswordException {
        cperfil.changePassword(newPassword, oldPassword);
        persistencia.savePerfil(cperfil.getActiveProfile());
    }

    /**
     * @brief Tancar sessió del perfil actiu.
     *
     * Aquest mètode guarda els canvis del perfil actiu si hi ha hagut modificacions
     * abans de tancar la sessió.
     */
    public void signOut() {
        if (cperfil.perfilModificat()) {
            persistencia.strictsavePerfil(cperfil.getActiveProfile());
        }
        cperfil.signOut();
    }

    /**
     * @brief Recordatori de la contrasenya.
     *
     * Proporciona una funcionalitat per recordar la contrasenya del perfil actiu.
     * Retorna una cadena de text amb la informació de recordatori.
     *
     * @return Una cadena amb el recordatori de la contrasenya.
     */
    public String remindPassword() {
        return cperfil.remindPassword();
    }

    /**
     * @brief Elimina el perfil actiu del sistema.
     *
     * Aquest mètode elimina el perfil que està actualment seleccionat en el sistema,
     * tant de la memòria activa com del sistema de persistència.
     */
    public void deletePerfil() {
        String username = cperfil.deletePerfil();
        persistencia.deletePerfil(username);
    }

    /**
     * @brief Retorna el nom real de l'usuari actual.
     */
    public String getRealNameActiveProfile() {
        return cperfil.getRealnameActiveProfile();
    }

    /**
     * @brief Retorna l'username d el'usuari actual.
     */
    public String getUsernameActiveProfile() {
        return cperfil.getUsernameActivePerfil();
    }


    /**
     * @brief Crea una nova distribució.0
     * 00
     * Aquest mètode crea una nova distribució amb l'ID proporcionat i els productes
     * especificats. Si ja hi havia una distribució activa prèvia, aquesta es guarda
     * automàticament al sistema de persistència abans de crear la nova.
     *
     * @param idDistribucio ID de la nova distribució.
     * @param importat Indica si la distribució s'ha importat d'una font externa.
     */

    public void createDistribucio(String idDistribucio, boolean importat, String mode, int numPrestatges) {
        String username = cperfil.getUsernameActivePerfil();
        List<List<String>> productes = new ArrayList<>();
        Inventari selected = cinventari.getInventariSeleccionat();
        if (mode == "Aproximat") calgoritme.solucioAproximate(selected, productes, numPrestatges, selected.getNumProductes());
        else calgoritme.solucioBacktracking(selected, productes, numPrestatges, selected.getNumProductes());
        persistencia.saveDistribucio(username, idDistribucio, cdistribucio.createDistribucio(idDistribucio, productes, importat));

    }

    /**
     * @brief Mostra totes les distribucions d'un perfil.
     *
     * Recupera i mostra totes les distribucions associades al perfil de l'usuari.
     *
     * @param username Nom d'usuari per identificar el perfil.
     */

    /**
     * @brief Elimina un producte de la distribució activa.
     *
     * Aquest mètode elimina un producte de la distribució seleccionada actualment.
     *
     * @param producte Nom del producte a eliminar.
     */
    public void deleteProducte(String producte) {
        cdistribucio.deleteProducte(producte);
    }

    /**
     * @brief Canvia de lloc un producte dins la distribució activa.
     *
     * Mou el producte especificat a una nova posició dins de la distribució activa.
     *
     * @param producte Nom del producte a moure.
     * @param x Nova posició X del producte.
     * @param y Nova posició Y del producte.
     */
    public void canviarDeLlocProducte(String producte, int x, int y) {
        cdistribucio.canviarDeLloc(producte, x, y);
    }

    /**
     * @brief Mostra la distribució activa actual.
     *
     * Aquest mètode visualitza la distribució que està seleccionada en aquest moment.
     */
    public void mostrarDistribucioActiva() {
        cdistribucio.mostrarDistribucioActiva();
    }

    /**
     * @brief A partir d'un arxiu extern, obté una distribució que es guardarà a persistència.
     * @pre El nom i contingut de la futura distribució són vàlids.
     * @post S'ha creat i guardat la distribució representada per l'arxiu entrant.
     * @param id el nom de la distribució, productes el seu contingut.
     */
    public void importarDistribucio(String id, List<List<String>> productes) {
        String username = cperfil.getUsernameActivePerfil();
        persistencia.saveDistribucio(username, id, cdistribucio.createDistribucio(id, productes, true));
    }

    /**
     * @brief Elimina una distribució d'un perfil.
     *
     * Desselecciona la distribució activa, si n'hi ha una, i elimina la distribució
     * especificada del sistema de persistència.
     *
     * @param idDistribucio ID de la distribució a eliminar.
     */
    public void deleteDistribucio(String idDistribucio) {
        String username = cperfil.getUsernameActivePerfil();
        cdistribucio.unselectDistribucio();
        persistencia.deleteDistribucio(username, idDistribucio);
    }

    /**
     * @brief Deselecciona la distribució que s'hagi escollit prèviament.
     * @pre Tenim una distribució seleccionat.
     * @post Ja no hi ha cap distribució seleccionada.
     */
    public void unselectDistribucio() {
        if(cdistribucio.getModifiedDistribucio()) {
            persistencia.deleteDistribucio(cperfil.getUsernameActivePerfil(), cdistribucio.getIdDistribucio());
            persistencia.saveDistribucio(cperfil.getUsernameActivePerfil(),cdistribucio.getIdDistribucio(), cdistribucio.getActiveDistribucio());
        }
        cdistribucio.unselectDistribucio();
    }

    /**
     * @brief Deselecciona l'inventari escollit prèviament.
     * @pre Tenim un inventari seleccionat.
     * @post Ja no hi ha cap inventari seleccionat.
     */
    public void unselectInventari() {
        cinventari.unselectInventari();
    }

    /**
     * @brief Canvia el nom d'una distribució.
     *
     * Aquest mètode canvia l'ID de la distribució activa al nou ID especificat.
     * La distribució original és eliminada i guardada amb el nou nom.
     *
     * @param NewidDistribucio Nou ID per a la distribució.
     */
    public void changeIdDistribucio(String NewidDistribucio) {
        String username = cperfil.getUsernameActivePerfil();
        persistencia.deleteDistribucio(username, cdistribucio.getIdDistribucio());
        cdistribucio.changeIdDistribucio(NewidDistribucio);
        persistencia.saveDistribucio(username, NewidDistribucio, cdistribucio.getActiveDistribucio());
    }

    /**
     * @brief Duplica una distribució activa amb un nou ID.
     *
     * Aquest mètode crea una còpia de la distribució activa i la guarda amb un nou ID.
     * La nova distribució també es selecciona automàticament.
     *
     * @param NewidDistribucio ID per a la nova distribució duplicada.
     */
    public void duplicarDistribucio(String NewidDistribucio) {
        String username = cperfil.getUsernameActivePerfil();
        Distribucio d = cdistribucio.duplicarDistribucio(NewidDistribucio);
        persistencia.saveDistribucio(username, NewidDistribucio, d);
        selectDistribucio(NewidDistribucio);
    }

    /**
     * @brief Selecciona una distribució d'un perfil.
     *
     * Carrega una distribució existent des del sistema de persistència i la selecciona.
     * Si hi havia una distribució activa prèviament modificada, aquesta es guarda abans de canviar.
     *
     * @param idDistribucio ID de la distribució a seleccionar.
     */
    public void selectDistribucio(String idDistribucio) {
        String username = cperfil.getUsernameActivePerfil();
        Distribucio d = persistencia.getDistribucio(username, idDistribucio);
        cdistribucio.selectDistribucio(d);
    }

    /**
     * @brief Retorna el contingut de la distribució demanada.
     * @param id el nom de la distribució.
     */
    public List<List<String>> getDistribucio(String id) {
        Distribucio d = persistencia.getDistribucio(cperfil.getUsernameActivePerfil(), id);
        return cdistribucio.getProductes(d);
    }
    /**
     * @brief Retorna els identificadors de les distribucions de l'usuari actiu.
     */
    public String[] getIdDistribucions() {
        return persistencia.getAllDistribucions(cperfil.getUsernameActivePerfil());
    }

    /**
     * @brief Retorna el contingut de la distribució seleccionad aactualment.
     */
    public List<List<String>> getActiveDistribution() {
        List<List<String>> distribuciones = cdistribucio.getActiveDistribucio().getProductes();
        return distribuciones;
    }

    /**
     * @brief Retorna el nom de la distribució seleccionada actualment.
     */
    public String getIdActiveDistribution() {
        return cdistribucio.getIdDistribucio();
    }

    /**
     * @brief Retorna el número de productes de l'inventari seleccionat.
     */
    public Integer getNumProductes() {
        return cinventari.getNumProductes();
    }
}


