package edu.upc.prop.grup13_4.inventari;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @brief Tests unitaris de CtrlInventari
 *
 */
public class CtrlInventariTest {

    CtrlInventari ctrl;

    @Before
    public void setUp() {
        ctrl = CtrlInventari.getInstance();
        ctrl.connect_inventari(new Inventari("test","dummy"));
    }

    @Test
    public void crearInventariTest() {
        assertNotNull(ctrl.crearInventari("test","dummy"));
    }

    @Test
    public void removeProducteOk() {
        ctrl.addProducte("pastanaga");
    }
}