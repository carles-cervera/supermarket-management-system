    package edu.upc.prop.grup13_4.persistencia;

    import edu.upc.prop.grup13_4.exceptions.distribucio.DistribucioNotFoundException;
    import edu.upc.prop.grup13_4.inventari.Inventari;
    import edu.upc.prop.grup13_4.distribucio.Distribucio;
    import edu.upc.prop.grup13_4.perfil.Perfil;

    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    /**
     * @brief Stub de CtrlPersistència
     *
     * Aquesta classe implementa funcionalitats trivials que pertanyen a la capa de persistència.
     *
     * Els algorismes emprats aquí són molt senzills i sabem que funcionen correctament doncs empren
     * funcions primitives dels mapes o dels vectors.
     *
     */
    public class CtrlPersistencia {
        //SINGLETON

        private static CtrlPersistencia instance;
        private CtrlJson json = CtrlJson.getInstance();

        //gson object, can be used arbitrarely for any serailization.

        //Object containers
        Map<String, Inventari> idInventariMap = new HashMap<>();
        Map<String, Perfil> Perfils;

        //Constructors
        private CtrlPersistencia() {
            Perfils = new HashMap<>();

        }

        public static CtrlPersistencia getInstance() {
            if (instance == null) {
                instance = new CtrlPersistencia();
            }
            return instance;
        }

        /**
         * @brief Guardar Inventari.
         *
         */
        public void saveInventari(Inventari i, String username) {
            //json.searchInventari(i.getId(), username);
            json.verifyPerfil(username);
            json.saveInventari(i, username);
            //idInventariMap.put(i.getId(), i);
        }

        /**
         * @brief Strict Save l'inventari demanat.
         *
         */
        public void strictSaveInventari(Inventari i, String username) {
            json.verifyPerfil(username);
            json.strictSaveInventari(i,username);
        }

        /**
         * @brief Obtenir Inventari.
         *
         */
        public Inventari getInventari(String id, String username) {
            json.verifyPerfil(username);
            return json.getInventari(id, username);
        }

        /**
         * @brief Consultar si l'inventari existeix.
         *
         */
        public boolean inventariExists(String id, String username) {
            return idInventariMap.containsKey(id);
        }

        // Guardar un perfil
        /**
         * @brief Strict Save el perfil demanat.
         *
         */
        public void strictsavePerfil(Perfil perfil) {
            json.strictSavePerfil(perfil);
        }

        /**
         * @brief Guarda el perfil demanat.
         *
         */
        public void savePerfil(Perfil perfil) {
            json.savePerfil(perfil);
        }

        /**
         * @brief Esborra el perfil demanat.
         *
         */
        public void deletePerfil(String username) {
            json.deletePerfil(username);
        }

        /**
         * @brief Retorna el perfil amb l'username demanat.
         *
         */
        public Perfil getPerfil(String username) {
            return json.getPerfil(username);
        }

        /**
         * @brief Retorna els ids de totes les distribucions de l'usuari demanat.
         *
         */
        public String[] getAllDistribucions(String username) {
            return json.getDistribucioIds(username);
        }

        /**
         * @brief Guarda la distribució demanada.
         *
         */
        public void saveDistribucio(String nomUsuari, String idDistribucio, Distribucio distribucio) {
            // Crear un mapa si no existeix per a l'usuari
            // Guardar o actualitzar la distribució
            json.verifyPerfil(nomUsuari);
            json.saveDistribucio(distribucio, nomUsuari);
        }

        /**
         * @brief Esborra la distribució demanada.
         *
         */
        public void deleteDistribucio(String nomUsuari, String idDistribucio) {
            json.verifyPerfil(nomUsuari);
            json.deleteDistribucio(idDistribucio, nomUsuari);
        }

        /**
         * @brief Obtenir una distribució específica.
         *
         */
        public Distribucio getDistribucio(String nomUsuari, String idDistribucio) {
            json.verifyPerfil(nomUsuari);
            return json.getDistribucio(idDistribucio,nomUsuari);
        }

        /**
         * @brief Retorna els ids dels inventaris de l'usuari demanat.
         *
         */
        public List<String> getInventarisIds(String username) {
            return json.getInventarisIds(username);
        }

        /**
         * @brief Esborra l'inventari demanat.
         *
         */
        public void deleteInventari(String id, String username) {
            json.deleteInventari(id, username);
        }
    }
