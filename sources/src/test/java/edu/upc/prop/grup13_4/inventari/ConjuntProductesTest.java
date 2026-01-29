package edu.upc.prop.grup13_4.inventari;

import edu.upc.prop.grup13_4.exceptions.inventari.ProductAlreadyExistsException;
import edu.upc.prop.grup13_4.exceptions.inventari.ProductNotFoundException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @brief Tests unitaris de ConjuntProductes
 *
 */
public class ConjuntProductesTest {

    @Test
    public void addTwoProductesInfinity() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("pera");
        cp.addProducte(a);
        cp.addProducte(b);
        Relacio r = cp.getRelacio(a,b);
        assertEquals(r.getGrau(), Integer.MAX_VALUE); //Valor per defecte
    }

    @Test
    public void relacioProductesIsSymmetric() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("pera");
        cp.addProducte(a);
        cp.addProducte(b);
        Relacio r = cp.getRelacio(a,b);
        assertNotEquals(r, null);
        assertEquals(r, cp.getRelacio(b,a));
    }

    @Test
    public void addTwoProductesIndexTest() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("pera");
        Producte c = new Producte("gentilles");
        cp.addProducte(a);
        cp.addProducte(b);
        cp.addProducte(c);
        assertEquals(cp.getIndexProducte(a), 0);
        assertEquals(cp.getIndexProducte(b), 1);
        assertEquals(cp.getIndexProducte(c), 2);
    }

    @Test
    public void addTwoEqualProductes() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("massana");
        cp.addProducte(a);
        assertThrows(ProductAlreadyExistsException.class, () -> {cp.addProducte(b);});

    }

    @Test
    public void productIndexOK() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("pera");
        Producte c = new Producte("gentilles");
        Producte d = new Producte("carlota");
        Producte e = new Producte("bajoca");
        cp.addProducte(a);
        cp.addProducte(b);
        cp.addProducte(c);
        cp.addProducte(d);
        cp.addProducte(e);
        assertEquals(0, cp.getIndexProducte(a));
        assertEquals(1, cp.getIndexProducte(b));
        assertEquals(2, cp.getIndexProducte(c));
        assertEquals(3, cp.getIndexProducte(d));
        assertEquals(4, cp.getIndexProducte(e));
    }

    @Test
    public void relationConsistency() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("pera");
        Producte c = new Producte("gentilles");
        Producte d = new Producte("carlota");
        Producte e = new Producte("bajoca");
        cp.addProducte(a);
        cp.addProducte(b);
        cp.addProducte(c);
        cp.addProducte(d);
        cp.addProducte(e);

        assertEquals(4, cp.getNumRealcions(e));
    }

    @Test
    public void getAdjIsWellOrdered() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("carbasso");
        Producte c = new Producte("gentilles");
        Producte d = new Producte("carlota");
        Producte e = new Producte("bajoca");
        cp.addProducte(a);
        cp.addProducte(b);
        cp.addProducte(c);
        cp.addProducte(d);
        cp.addProducte(e);
        cp.modRelacio(a,d,100);
        cp.modRelacio(d,b,5);
        cp.modRelacio(b,c,100);

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                assertTrue(cp.getAdj(i,j-1).second <= cp.getAdj(i,j).second);
            }
        }
    }

    @Test
    public void getNumProductesOk() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("pera");
        Producte c = new Producte("gentilles");
        Producte d = new Producte("carlota");
        Producte e = new Producte("bajoca");
        cp.addProducte(a);
        cp.addProducte(b);
        cp.addProducte(c);
        cp.addProducte(d);
        cp.addProducte(e);
        assertEquals(5, cp.getSize());
    }

    @Test
    public void removeProductConsistency() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("pera");
        Producte c = new Producte("gentilles");
        Producte d = new Producte("carlota");
        Producte e = new Producte("bajoca");
        cp.addProducte(a);
        cp.addProducte(b);
        cp.addProducte(c);
        cp.addProducte(d);
        cp.addProducte(e);
        int prevSize = cp.getSize();
        cp.removeProducte(c);
        //Assert that size has decreased
        assertEquals(prevSize - 1, cp.getSize());
        //Assert that all tail productes get decremented
        assertEquals(2, cp.getIndexProducte(d));
        assertEquals(3, cp.getIndexProducte(e));
        //Assert that prior products are intact
        assertEquals(0, cp.getIndexProducte(a));
        assertEquals(1, cp.getIndexProducte(b));
        //Assert that if a product is added, it has index 4
        Producte f = new Producte("mandarina");
        cp.addProducte(f);
        assertEquals(prevSize - 1, cp.getIndexProducte(f));
    }

    @Test
    public void removeLastProductConsistency() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("pera");
        Producte c = new Producte("gentilles");
        Producte d = new Producte("carlota");
        Producte e = new Producte("bajoca");
        cp.addProducte(a);
        cp.addProducte(b);
        cp.addProducte(c);
        cp.addProducte(d);
        cp.addProducte(e);
        int prevSize = cp.getSize();
        cp.removeProducte(e);
        //Assert that size has decreased
        assertEquals(prevSize - 1, cp.getSize());
        //Assert that all tail productes get decremented
        //Assert that prior products are intact
        assertEquals(0, cp.getIndexProducte(a));
        assertEquals(1, cp.getIndexProducte(b));
        assertEquals(2, cp.getIndexProducte(c));
        assertEquals(3, cp.getIndexProducte(d));
        //Assert that if a product is added, it has index 4
        Producte f = new Producte("mandarina");
        cp.addProducte(f);
        assertEquals(prevSize - 1, cp.getIndexProducte(f));
    }

    @Test
    public void removeNonExistantProducte() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("pera");
        Producte c = new Producte("gentilles");
        Producte d = new Producte("carlota");
        Producte e = new Producte("bajoca");
        cp.addProducte(a);
        cp.addProducte(b);
        cp.addProducte(c);
        cp.addProducte(d);
        cp.addProducte(e);
        Producte f = new Producte("mandarina");
        assertThrows(ProductNotFoundException.class, () -> cp.removeProducte(f));
    }

    @Test
    public void removeAllProducts() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("pera");
        Producte c = new Producte("gentilles");
        Producte d = new Producte("carlota");
        Producte e = new Producte("bajoca");
        cp.addProducte(a);
        cp.addProducte(b);
        cp.addProducte(c);
        cp.addProducte(d);
        cp.addProducte(e);
        assertEquals(5, cp.getSize());
        cp.removeProducte(a);
        cp.removeProducte(b);
        cp.removeProducte(c);
        cp.removeProducte(d);
        cp.removeProducte(e);
        assertEquals(0, cp.getSize());
    }

    @Test
    public void idProducteOk() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("pera");
        cp.addProducte(a);
        cp.addProducte(b);
        assertEquals(a, cp.getProducte("massana"));
        assertEquals(b, cp.getProducte("pera"));
        assertThrows(ProductNotFoundException.class, () -> cp.getProducte("gentilles"));
        cp.removeProducte(a);
        assertThrows(ProductNotFoundException.class, () -> cp.getProducte("massana"));
    }

    @Test
    public void modRelacioOk() {
        ConjuntProductes cp = new ConjuntProductes();
        Producte a = new Producte("massana");
        Producte b = new Producte("pera");
        Producte c = new Producte("canalons");
        cp.addProducte(a);
        cp.addProducte(b);
        cp.addProducte(c);
        cp.modRelacio(c,a, 10);
        assertEquals(10, cp.getRelacio(a,c).getGrau());
        cp.modRelacio(a,b,11);
        assertEquals(11, cp.getRelacio(b,a).getGrau());
    }

}
