package edu.upc.prop.grup13_4.persistencia;

import edu.upc.prop.grup13_4.inventari.CtrlInventari;
import edu.upc.prop.grup13_4.inventari.Inventari;
import org.junit.Before;

public class CtrlPersistenciaTest {

    CtrlInventari ctrlInventari;
    Inventari inventari;

    @Before
    public void setUp() {
        ctrlInventari = ctrlInventari.getInstance();
    }

}