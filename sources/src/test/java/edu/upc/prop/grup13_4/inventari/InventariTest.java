package edu.upc.prop.grup13_4.inventari;

import edu.upc.prop.grup13_4.exceptions.inventari.RelationNotFoundException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @brief Tests unitaris d'Inventari.
 *
 */
public class InventariTest {

    private Inventari inv;
    private Producte a,b;

    @Before
    public void setUp() {
        inv = new Inventari("test", "dummy");
        a = new Producte("pastanaga");
        b = new Producte("arros");
        inv.addProducte(a);
        inv.addProducte(b);
    }

    @Test
    public void modRelacioOk() {
        inv.modRelacio("pastanaga","arros",10);
        assertEquals(10, inv.getRelacio("pastanaga","arros"));
        inv.modRelacio("pastanaga","arros",100);
        assertEquals(100, inv.getRelacio("arros","pastanaga"));
    }


    @Test
    public void notReflexiveRelation() {
        //We test the only not possible relation: the reflexive relation
        assertThrows(RelationNotFoundException.class, () -> inv.modRelacio("arros","arros",10));
    }

  
}