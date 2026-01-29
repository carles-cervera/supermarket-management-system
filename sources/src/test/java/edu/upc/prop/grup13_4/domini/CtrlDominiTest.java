package edu.upc.prop.grup13_4.domini;

import edu.upc.prop.grup13_4.exceptions.distribucio.DistribucioIsNullException;
import edu.upc.prop.grup13_4.exceptions.distribucio.DistribucioNotFoundException;
import edu.upc.prop.grup13_4.exceptions.inventari.InventariNotFoundException;
import edu.upc.prop.grup13_4.exceptions.inventari.ProductNotFoundException;
import edu.upc.prop.grup13_4.exceptions.perfil.*;
import edu.upc.prop.grup13_4.inventari.CtrlInventari;
import edu.upc.prop.grup13_4.inventari.Inventari;
import edu.upc.prop.grup13_4.persistencia.CtrlPersistencia;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

/** @brief Tests de la classe CtrlDomini
 */
public class CtrlDominiTest {

    CtrlInventari CI;
    CtrlPersistencia CP;
    private CtrlDomini ctrlDomini;

    @Before
    public void setUp() throws Exception {
        CI = CtrlInventari.getInstance();
        CP = CtrlPersistencia.getInstance();
        Inventari new_inv = CI.crearInventari("test1", "dummy");
        assertNotNull(new_inv);
        CI.connect_inventari(new_inv);
        ctrlDomini = CtrlDomini.getInstance();
    }

    @Test
    public void ctrlInventariCheckIdWrong() {
        assertThrows(IllegalArgumentException.class, () -> CI.crearInventari("separated id", "dummy"));
        assertThrows(IllegalArgumentException.class, () -> CI.crearInventari("very    separated         id", "dummy"));
    }

    @Test
    public void ctrlPersSavesInventariOK() {
        Inventari inv = CI.crearInventari("test", "dummy");
        CP.saveInventari(inv, "dummy");
        CP.getInventari("test", "dummy");
        assertThrows(InventariNotFoundException.class, () -> CP.getInventari("noExists","dummy"));
    }

    @Test
    public void testSignUp() {
        try {
            ctrlDomini.signUp("John Doe", "johndoe", "Password1");
        } catch (PerfilAlreadyExistsException | InvalidPasswordException e) {
            fail("No s'esperava cap excepció: " + e.getMessage());
        }
    }

    @Test
    public void testLogIn() {
        try {
            ctrlDomini.signUp("Jane Doe", "janedoe", "Password1");
            ctrlDomini.logIn("janedoe", "Password1");
        } catch (PerfilNotFoundException | IncorrectPasswordException | InvalidPasswordException e) {
            fail("No s'esperava cap excepció: " + e.getMessage());
        }
    }

    @Test
    public void testChangePassword() {
        try {
            ctrlDomini.signUp("Jane Smith", "janesmith", "OldPassword1");
            ctrlDomini.changePassword("NewPassword1", "OldPassword1");
            // Prova de fer un login amb la nova contrasenya
            ctrlDomini.logIn("janesmith", "NewPassword1");
        } catch (InvalidPasswordException | PerfilNotFoundException | IncorrectPasswordException e) {
            fail("No s'esperava cap excepció: " + e.getMessage());
        }
    }

    @Test
    public void testSignOut() {
        try {
            ctrlDomini.signUp("Alice", "alice123", "SecurePassword1");
            ctrlDomini.logIn("alice123", "SecurePassword1");
            ctrlDomini.signOut();
        } catch (PerfilAlreadyExistsException | InvalidPasswordException | PerfilNotFoundException | IncorrectPasswordException e) {
            fail("No s'esperava cap excepció: " + e.getMessage());
        }
    }

    @Test
    public void testRemindPassword() {
        try {
            ctrlDomini.signUp("Bob", "bobusername", "MySecret123");
            String password = ctrlDomini.remindPassword();
            assertEquals("MySecret123", password);
        } catch (InvalidPasswordException e) {
            fail("No s'esperava cap excepció: " + e.getMessage());
        }
    }

    @Test
    public void testDeletePerfil() {
        try {
            ctrlDomini.signUp("Chris", "chris123", "ChrisPassword1");
            ctrlDomini.deletePerfil();
        } catch (InvalidPasswordException e) {
            fail("No s'esperava cap excepció: " + e.getMessage());
        }
    }

    @Test
    public void testCreateDistribucio() {
        try {
            ctrlDomini.createDistribucio("distribucio123", "user123", null, false);
        } catch (Exception e) {
            fail("No s'esperava cap excepció: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteProducte() {
        try {
            List<List<String>> distrib = new ArrayList<>();
            distrib.add(new ArrayList<>(Arrays.asList("Llimona", "Cirera", "Maduixa")));
            distrib.add(new ArrayList<>(Arrays.asList("Pèsols", "Mongetes", "Llenties")));
            distrib.add(new ArrayList<>(Arrays.asList("Salmó", "Tonyina", "Sardina")));
            distrib.add(new ArrayList<>(Arrays.asList("Oli d'oliva", "Vinagre", "Sal")));
            ctrlDomini.createDistribucio("distribucio1234", "user123", distrib, false);
            ctrlDomini.deleteProducte("producte123");
            fail("Ha de saltar excepció");
        } catch (ProductNotFoundException e) {
            assertEquals("El producte 'producte123' no s'ha trobat en la distribució.", e.getMessage());
        }
    }


    @Test
    public void testCanviarDeLlocProducte() {
        try {
            List<List<String>> distrib = new ArrayList<>();
            distrib.add(new ArrayList<>(Arrays.asList("Llimona", "Cirera", "Maduixa")));
            distrib.add(new ArrayList<>(Arrays.asList("Pèsols", "Mongetes", "Llenties")));
            distrib.add(new ArrayList<>(Arrays.asList("Salmó", "Tonyina", "Sardina")));
            distrib.add(new ArrayList<>(Arrays.asList("Oli d'oliva", "Vinagre", "Sal")));
            ctrlDomini.createDistribucio("distribucio1235", "user123", distrib, false);
            ctrlDomini.canviarDeLlocProducte("producte123", 5, 10);
            fail("Ha de saltar excepció");
        } catch (ProductNotFoundException e) {
            assertEquals("El producte 'producte123' no s'ha trobat en la distribució.", e.getMessage());
        }
    }

    @Test
    public void testMostrarDistribucioActiva() {
        try {
            List<List<String>> distrib = new ArrayList<>();
            distrib.add(new ArrayList<>(Arrays.asList("Llimona", "Cirera", "Maduixa")));
            distrib.add(new ArrayList<>(Arrays.asList("Pèsols", "Mongetes", "Llenties")));
            distrib.add(new ArrayList<>(Arrays.asList("Salmó", "Tonyina", "Sardina")));
            distrib.add(new ArrayList<>(Arrays.asList("Oli d'oliva", "Vinagre", "Sal")));
            ctrlDomini.createDistribucio("distribucio1236", "user123", distrib, false);
            ctrlDomini.mostrarDistribucioActiva();

        } catch (Exception e) {
            fail("No ha de saltar excepció");
        }
    }

    @Test
    public void testDeleteDistribucio() {
        try {
            ctrlDomini.signUp("user123", "anna", "deBil***1");

            List<List<String>> distrib = new ArrayList<>();
            distrib.add(new ArrayList<>(Arrays.asList("Llimona", "Cirera", "Maduixa")));
            distrib.add(new ArrayList<>(Arrays.asList("Pèsols", "Mongetes", "Llenties")));
            distrib.add(new ArrayList<>(Arrays.asList("Salmó", "Tonyina", "Sardina")));
            distrib.add(new ArrayList<>(Arrays.asList("Oli d'oliva", "Vinagre", "Sal")));
            ctrlDomini.createDistribucio("distribucio123", "user123", distrib, false);
            ctrlDomini.deleteDistribucio("user123", "distribucio123");
        } catch (Exception e) {
            fail("No s'esperava cap excepció: " + e.getMessage());
        }
    }

    @Test
    public void testChangeIdDistribucio() {
        try {
            ctrlDomini.changeIdDistribucio("user123", "newDistribucioId");
        } catch (Exception e) {
            fail("No s'esperava cap excepció: " + e.getMessage());
        }
    }

    @Test
    public void testDuplicarDistribucio() {
        try {
            List<List<String>> distrib = new ArrayList<>();
            distrib.add(new ArrayList<>(Arrays.asList("Llimona", "Cirera", "Maduixa")));
            distrib.add(new ArrayList<>(Arrays.asList("Pèsols", "Mongetes", "Llenties")));
            distrib.add(new ArrayList<>(Arrays.asList("Salmó", "Tonyina", "Sardina")));
            distrib.add(new ArrayList<>(Arrays.asList("Oli d'oliva", "Vinagre", "Sal")));
            ctrlDomini.createDistribucio("distribucio1237", "user123", distrib, false);
            ctrlDomini.duplicarDistribucio("user123", "duplicatedDistribucioId");
        } catch (Exception e) {
            fail("No s'esperava cap excepció: " + e.getMessage());
        }
    }

    @Test
    public void testSelectDistribucio() {
        try {
            ctrlDomini.selectDistribucio("user123", "distribucioId");
        } catch (DistribucioNotFoundException e) {
            assertEquals("Distribució no trobada: distribucioId", e.getMessage());
        }
    }
}