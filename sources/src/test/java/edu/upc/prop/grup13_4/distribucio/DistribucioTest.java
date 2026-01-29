package edu.upc.prop.grup13_4.distribucio;

import edu.upc.prop.grup13_4.exceptions.distribucio.InvalidPositionException;
import edu.upc.prop.grup13_4.exceptions.inventari.ProductNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

/** @brief Tests de la classe Distribucio
 */
public class DistribucioTest {

    @Test
    public void testClone() {
        List<List<String>> productes = new ArrayList<>();
        List<String> subllista1 = new ArrayList<>();
        subllista1.add("producte1");
        List<String> subllista2 = new ArrayList<>();
        subllista2.add("producte2");
        productes.add(subllista1);
        productes.add(subllista2);

        Distribucio original = new Distribucio("D1", productes, true);

        // Clonem la distribució
        Distribucio copia = original.clone();

        // Comprovem que la còpia no sigui el mateix objecte
        assertNotSame(original, copia);

        // Comprovem que els valors de la còpia siguin els mateixos
        assertEquals(original.getIdDistribucio(), copia.getIdDistribucio());
        assertEquals(original.getProductes(), copia.getProductes());

        // Modifiquem la còpia per comprovar que és independent
        copia.changeIdDistribucio("D2");
        assertNotEquals(original.getIdDistribucio(), copia.getIdDistribucio());
    }

    @Test
    public void testDeleteProducte() {
        List<List<String>> productes = new ArrayList<>();
        List<String> subllista = new ArrayList<>();
        subllista.add("producte1");
        subllista.add("producte2");
        productes.add(subllista);

        Distribucio distribucio = new Distribucio("D1", productes, true);

        try {
            // Eliminem un producte existent
            distribucio.deleteProducte("producte1");
            assertFalse(subllista.contains("producte1"));
        } catch (ProductNotFoundException e) {
            fail("No s'hauria d'haver llençat ProductNotFoundException per un producte existent.");
        }

        try {
            // Intentem eliminar un producte que no existeix
            distribucio.deleteProducte("producte3");
            fail("S'hauria d'haver llençat ProductNotFoundException per un producte inexistent.");
        } catch (ProductNotFoundException e) {
            // Excepció esperada
            assertEquals("El producte 'producte3' no s'ha trobat en la distribució.", e.getMessage());
        }
    }

    @Test
    public void testCanviarDeLloc() {
        List<List<String>> productes = new ArrayList<>();
        List<String> subllista = new ArrayList<>();
        subllista.add("producte1");
        subllista.add("producte2");
        productes.add(subllista);

        Distribucio distribucio = new Distribucio("D1", productes, true);

        try {
            // Canviem el lloc d'un producte existent
            distribucio.canviarDeLloc("producte1", 0, 1);
            assertEquals("producte1", subllista.get(1));
        } catch (ProductNotFoundException | InvalidPositionException e) {
            fail("No s'hauria d'haver llençat cap excepció per un canvi de lloc vàlid.");
        }

        try {
            // Intentem canviar el lloc d'un producte que no existeix
            distribucio.canviarDeLloc("producte3", 0, 1);
            fail("S'hauria d'haver llençat ProductNotFoundException per un producte inexistent.");
        } catch (ProductNotFoundException e) {
            // Excepció esperada
            assertEquals("El producte 'producte3' no s'ha trobat en la distribució.", e.getMessage());
        } catch (InvalidPositionException e) {
            fail("No s'hauria d'haver llençat InvalidPositionException per un producte inexistent.");
        }

        try {
            // Intentem canviar el lloc amb índexs invàlids
            distribucio.canviarDeLloc("producte2", 0, 3);
            fail("S'hauria d'haver llençat InvalidPositionException per una posició invàlida.");
        } catch (ProductNotFoundException e) {
            fail("No s'hauria d'haver llençat ProductNotFoundException per una posició invàlida.");
        } catch (InvalidPositionException e) {
            // Excepció esperada
            assertEquals("La posició 'y' especificada no és vàlida.", e.getMessage());
        }
    }

    @Test
    public void testMostrarDistribucio() {
        List<List<String>> productes = new ArrayList<>();
        List<String> subllista = new ArrayList<>();
        subllista.add("producte1");
        productes.add(subllista);

        Distribucio distribucio = new Distribucio("D1", productes, true);

        // Aquí comprovarem visualment que s'imprimeix la informació correcta
        // No hi ha una sortida automàtica per capturar la sortida de la consola, però podem verificar que el mètode no llanci excepcions
        distribucio.mostrarDistribucio();
    }

    @Test
    public void testChangeIdDistribucio() {
        List<List<String>> productes = new ArrayList<>();
        List<String> subllista = new ArrayList<>();
        subllista.add("producte1");
        productes.add(subllista);

        Distribucio distribucio = new Distribucio("D1", productes, true);

        // Canviem l'ID de la distribució
        distribucio.changeIdDistribucio("D2");
        assertEquals("D2", distribucio.getIdDistribucio());
    }

    @Test
    public void testGetProductes() {
        List<List<String>> productes = new ArrayList<>();
        List<String> subllista = new ArrayList<>();
        subllista.add("producte1");
        productes.add(subllista);

        Distribucio distribucio = new Distribucio("D1", productes, true);

        // Comprovem que obtenim les productes correctament
        assertEquals(productes, distribucio.getProductes());
    }

    @Test
    public void testGetIdDistribucio() {
        List<List<String>> productes = new ArrayList<>();
        List<String> subllista = new ArrayList<>();
        subllista.add("producte1");
        productes.add(subllista);

        Distribucio distribucio = new Distribucio("D1", productes, true);

        // Comprovem que l'ID sigui correcte
        assertEquals("D1", distribucio.getIdDistribucio());
    }
}
