package edu.upc.prop.grup13_4.perfil;

import static org.junit.Assert.*;

import edu.upc.prop.grup13_4.exceptions.perfil.*;
import org.junit.Test;

/** @brief Tests de la classe CtrlPerfil
 */
public class CtrlPerfilTest {

    @Test
    public void testGetInstance() {
        CtrlPerfil instance1 = CtrlPerfil.getInstance();
        CtrlPerfil instance2 = CtrlPerfil.getInstance();
        assertSame(instance1, instance2); // Han de ser la mateixa instància
        assertNotNull(instance1); // La instància no ha de ser nul·la
    }

    @Test
    public void testPasswordRequirements() {
        assertFalse(CtrlPerfil.passwordRequirements("123Ab")); // Massa curta
        assertFalse(CtrlPerfil.passwordRequirements("abcdefg1")); // Sense majúscules
        assertFalse(CtrlPerfil.passwordRequirements("Password")); // Sense dígits
        assertTrue(CtrlPerfil.passwordRequirements("Valid123")); // Compleix tots els requisits
        assertTrue(CtrlPerfil.passwordRequirements("A1b2C3d4")); // Cas límit vàlid
    }

    @Test
    public void testCheckPassword() {
        CtrlPerfil ctrl = CtrlPerfil.getInstance();
        Perfil perfil = new Perfil("Joan Pujol", "joanpujol", "Correct123");

        try {
            ctrl.checkPassword(perfil, "ContrasenyaIncorrecta"); // Contrasenya incorrecta
            fail("S'esperava una IncorrectPasswordException");
        } catch (IncorrectPasswordException e) {
            // Excepció esperada
        }

        try {
            ctrl.checkPassword(perfil, "Correct123"); // Contrasenya correcta
            assertNotNull(ctrl.getActiveProfile()); // Perfil ha de ser actiu
            assertSame(perfil, ctrl.getActiveProfile()); // El perfil actiu ha de ser el correcte
        } catch (IncorrectPasswordException e) {
            fail("No s'hauria d'haver llançat l'excepció");
        }
    }

    @Test
    public void testChangePassword() {
        CtrlPerfil ctrl = CtrlPerfil.getInstance();
        Perfil perfil = new Perfil("Joan Pujol", "joanpujol", "OldPass123*");

        try {
            ctrl.checkPassword(perfil, "OldPass123*"); // Autenticar el perfil

            try {
                ctrl.changePassword("curta", "OldPass123*"); // Contrasenya nova no vàlida
                fail("S'esperava una InvalidPasswordException");
            } catch (InvalidPasswordException e) {
                // Excepció esperada
            }

            try {
                ctrl.changePassword("NewPass123*", "PassIncorrecta"); // Contrasenya antiga incorrecta
                fail("S'esperava una InvalidPasswordException");
            } catch (InvalidPasswordException e) {
                // Excepció esperada
            }
            try {
            ctrl.changePassword("NewPass123*", "OldPass123*");
            assertEquals("NewPass123*", perfil.getPassword()); // Verificar que la contrasenya s'ha actualitzat
            }
            catch (IncorrectPasswordException e){
                fail("No s'hauria d'haver llançat l'excepció");
            }

        } catch (IncorrectPasswordException e) {
            fail("No s'hauria d'haver llançat l'excepció");
        }
    }

    @Test
    public void testSignUp() {
        CtrlPerfil ctrl = CtrlPerfil.getInstance();
        try {
            ctrl.signUp("Anna Maria", "anna", "debil");// Contrasenya no vàlida, perfil no creat
            fail("S'hauria d'haver llançat l'excepció");
        }
        catch(InvalidPasswordException e) {
            // Excepció esperada
        }
        try {
            ctrl.signUp("Anna Maria", "anna", "FortPass1*"); // Perfil creat amb contrasenya vàlida
            assertEquals("anna", ctrl.getUsernameActivePerfil()); // Verificar el nom d'usuari
        }
        catch(PerfilAlreadyExistsException | InvalidPasswordException e) {
            fail("No s'hauria d'haver llançat l'excepció");
        }
        assertEquals("anna", ctrl.getUsernameActivePerfil()); // Verificar el nom d'usuari
    }

    @Test
    public void testSignOut() {
        CtrlPerfil ctrl = CtrlPerfil.getInstance();
        ctrl.signUp("Pere Garcia", "peregarcia", "Password123*");

        ctrl.signOut();
        assertNull(ctrl.getActiveProfile()); // No ha d'haver-hi cap perfil actiu després de tancar la sessió

    }

    @Test
    public void testRemindPassword() {
        CtrlPerfil ctrl = CtrlPerfil.getInstance();
        ctrl.signUp("Marta Lluch", "martalluch", "Secret123*");

        try {
            assertEquals("Secret123*", ctrl.remindPassword()); // Recordar contrasenya
        } catch (IncorrectPasswordException e) {
            fail("No s'hauria d'haver llançat l'excepció");
        }
    }

    @Test
    public void testDeletePerfil() {
        CtrlPerfil ctrl = CtrlPerfil.getInstance();
        try {
            ctrl.signUp("Jordi Ferrer", "jordiferrer", "Pass1234*");
            assertEquals("jordiferrer", ctrl.deletePerfil()); // Eliminar perfil
            assertNull(ctrl.getActiveProfile()); // No ha d'haver-hi cap perfil actiu després de la seva eliminació

        } catch (PerfilNotFoundException e) {
            fail("No s'hauria d'haver llançat l'excepció");
        }
    }

    @Test
    public void testGetActiveProfile() {
        CtrlPerfil ctrl = CtrlPerfil.getInstance();
        assertNull(ctrl.getActiveProfile()); // No ha d'haver-hi cap perfil actiu

        Perfil perfil = new Perfil("Lluís Puig", "lluispuig", "Password123*");

        try {
            ctrl.checkPassword(perfil, "Password123*"); // Autenticar el perfil
            assertNotNull(ctrl.getActiveProfile()); // Ara ha d'haver-hi un perfil actiu
            assertSame(perfil, ctrl.getActiveProfile()); // Ha de ser el perfil correcte
        } catch (IncorrectPasswordException e) {
            fail("No s'hauria d'haver llançat l'excepció");
        }
    }
}
