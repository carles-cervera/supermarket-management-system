package edu.upc.prop.grup13_4.distribucio;

import edu.upc.prop.grup13_4.exceptions.distribucio.DistribucioAlreadyExistsException;
import edu.upc.prop.grup13_4.exceptions.distribucio.DistribucioIsNullException;
import edu.upc.prop.grup13_4.exceptions.distribucio.InvalidPositionException;
import edu.upc.prop.grup13_4.exceptions.distribucio.idDistribucioIsNullException;
import edu.upc.prop.grup13_4.exceptions.inventari.ProductNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

/** @brief Tests de la classe CtrlDistribucio
 */
public class CtrlDistribucioTest {
    @Test
    public void testGetInstance() {
        // Cas extrem: Verificar el singleton, sempre retorna la mateixa instància
        CtrlDistribucio instance1 = CtrlDistribucio.getInstance();
        CtrlDistribucio instance2 = CtrlDistribucio.getInstance();
        assertNotNull(instance1);
        assertSame(instance1, instance2);
    }

    @Test
    public void testCreateDistribucio() {
        // Cas extrem: Crear una distribució amb una llista de productes buida
        CtrlDistribucio ctrl = CtrlDistribucio.getInstance();
        List<List<String>> productes = new ArrayList<>();
        try {
            Distribucio distribucio = ctrl.createDistribucio("D1", productes, false);
            assertNotNull(distribucio);
            assertEquals("D1", distribucio.getIdDistribucio());
            assertEquals(0, distribucio.getProductes().size());
        }
        catch (DistribucioAlreadyExistsException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDeleteProducte() {
        // Cas extrem: Eliminar un producte que no existeix
        CtrlDistribucio ctrl = CtrlDistribucio.getInstance();
        List<List<String>> productes = new ArrayList<>();
        List<String> subllista = new ArrayList<>();
        subllista.add("ProducteA");
        productes.add(subllista);
        try {
            ctrl.createDistribucio("D1", productes, false);
            ctrl.deleteProducte("ProducteInexistent");
            fail("No s'ha eliminat el producte correctament");
        }
        catch (ProductNotFoundException e) {
            assertEquals("El producte 'ProducteInexistent' no s'ha trobat en la distribució.", e.getMessage());
        }

        // Cas extrem: Eliminar un producte d'una distribució buida
        try {
            ctrl.createDistribucio("D2", new ArrayList<>(), false);
            ctrl.deleteProducte("ProducteA");
            fail("No s'ha eliminat el producte correctament");
        }
        catch (ProductNotFoundException e) {
            assertEquals("El producte 'ProducteA' no s'ha trobat en la distribució.", e.getMessage());
        }
    }

    @Test
    public void testCanviarDeLloc() {
        // Cas extrem: Canviar un producte a una posició invàlida
        CtrlDistribucio ctrl = CtrlDistribucio.getInstance();
        List<List<String>> productes = new ArrayList<>();
        List<String> subllista = new ArrayList<>();
        subllista.add("ProducteA");
        productes.add(subllista);

        try {
            ctrl.createDistribucio("D1", productes, false);
            ctrl.canviarDeLloc("ProducteA", 0, 5); // Posició fora de rang
            fail("No s'ha gestionat l'error de la posició correctament");
        } catch  (ProductNotFoundException | InvalidPositionException e){

            assertEquals("La posició 'y' especificada no és vàlida.", e.getMessage());
        }

        // Cas extrem: El producte no existeix
        try {
            ctrl.canviarDeLloc("ProducteInexistent", 0, 0);
            fail("No s'ha gestionat l'error del producte correctament");
        }catch  (ProductNotFoundException | InvalidPositionException e){
            assertEquals("El producte 'ProducteInexistent' no s'ha trobat en la distribució.", e.getMessage());
        }
    }

    @Test
    public void testMostrarDistribucioActiva() {
        // Cas extrem: Mostrar una distribució buida
        CtrlDistribucio ctrl = CtrlDistribucio.getInstance();
        List<List<String>> productes = new ArrayList<>();
        ctrl.createDistribucio("D1", productes, false);

        // No esperem cap excepció
        try {
            ctrl.mostrarDistribucioActiva();
        } catch (Exception e) {
            fail("No hauria de llençar una excepció en mostrar una distribució buida");
        }
    }

    @Test
    public void testChangeNameDistribucio() {
        // Cas extrem: Canviar el nom a una cadena buida
        CtrlDistribucio ctrl = CtrlDistribucio.getInstance();
        List<List<String>> productes = new ArrayList<>();
        ctrl.createDistribucio("D1", productes, false);

        try {
            ctrl.changeIdDistribucio("D1");
            assertEquals("D1", ctrl.getActiveDistribucio().getIdDistribucio());
        }
        catch (Exception e) {
            fail("No hauria de llençar una excepció");
        }
        // Cas extrem: Canviar el nom a null (hauria de llençar una excepció)
        try {
            ctrl.changeIdDistribucio(null);
        } catch (idDistribucioIsNullException | DistribucioIsNullException e) {
            // Excepció esperada
            assertEquals("El nom de la distribució no pot ser null", e.getMessage());
        }
    }

    @Test
    public void testSelectDistribucio() {
        // Cas extrem: Seleccionar una distribució nul·la
        CtrlDistribucio ctrl = CtrlDistribucio.getInstance();
        try {
            ctrl.selectDistribucio(null);
            fail("Hauria de llençar una excepció");
        }
        catch (DistribucioIsNullException e) {
            // Excepció esperada
            assertEquals("La distribució no pot ser null", e.getMessage());
        }
    }

    @Test
    public void testGetActiveDistribucio() {
        // Cas extrem: No s'ha creat cap distribució
        CtrlDistribucio ctrl = CtrlDistribucio.getInstance();
        Distribucio activeDistribucio = ctrl.getActiveDistribucio();
        assertNull(activeDistribucio);
    }

    @Test
    public void testGetModifiedDistribucio() {
        // Cas extrem: Comprovar l'estat modificat abans de fer canvis
        CtrlDistribucio ctrl = CtrlDistribucio.getInstance();
        ctrl.createDistribucio("D1", new ArrayList<>(), false);
        boolean modificat = ctrl.getModifiedDistribucio();
        assertFalse(modificat);

        // Després de fer canvis
        try {
            ctrl.deleteProducte("ProducteInexistent");
        }
        catch (ProductNotFoundException e) {
            //Excepcio esperada
        }
        modificat = ctrl.getModifiedDistribucio();
        assertFalse(modificat);

        ctrl.changeIdDistribucio("D2");
        modificat = ctrl.getModifiedDistribucio();
        assertTrue(modificat);
    }

    @Test
    public void testGetIdDistribucio() {
        // Cas extrem: Obtenir l'ID d'una distribució activa nul·la
        CtrlDistribucio ctrl = CtrlDistribucio.getInstance();
        ctrl.unselectDistribucio();
        // Comprovem que es llença l'excepció
        try {
            ctrl.getIdDistribucio(); // Ha de llançar DistribucioIsNullException
            fail("S'hauria d'haver llençat una excepció DistribucioIsNullException"); // Si no s'escapa l'excepció, el test fallarà
        } catch (DistribucioIsNullException e) {
            // Comprovem que l'excepció és la correcta
            assertEquals("No hi ha cap distribució activa", e.getMessage());
        }

        // Cas estàndard: Obtenir l'ID d'una distribució creada
        List<List<String>> productes = new ArrayList<>();
        ctrl.createDistribucio("D1", productes, false);
        String idDistribucio = ctrl.getIdDistribucio();
        assertEquals("D1", idDistribucio); // Ha de retornar l'ID de la distribució creada
    }
}
