package edu.upc.prop.grup13_4.inventari;

import edu.upc.prop.grup13_4.domini.CtrlDomini;
import edu.upc.prop.grup13_4.exceptions.inventari.*;
import edu.upc.prop.grup13_4.presentacio.CtrlPresentacio;

import java.util.Scanner;

public class InventariParser {

    public static void main(String[] args) {
        InventariParser pars = new InventariParser("asd");
        InventariParser.scripting(pars);
    }

    private final CtrlPresentacio presentacio;
    private final CtrlDomini domini;
    final private Scanner scanner;

    public InventariParser(String input) {
        presentacio = CtrlPresentacio.getInstance();
        domini = CtrlDomini.getInstance();
        scanner = new Scanner(input);
        //domini.logIn("dylanBB", "Aleix1234!");
    }

    public void crearInventariTest() {
        String opcio;
        opcio = scanner.next();

        try {
            domini.crearInventari(opcio, domini.getUsernameActiveProfile());
            domini.seleccionarInventari(opcio);
        } catch (InventariAlreadyExistsException e) {
            System.err.println("Ja existeix un Inventari amb aquest nom");
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.err.println("Algun error desconegut ha succeit al crear l'Inventari!");
        }
    }

    public void addProducteTest() {
        String opcio;
        opcio = scanner.next();

        try {
            domini.afegirProducte(opcio);
        } catch (ProductAlreadyExistsException e) {
            System.err.println("Ja existeix un producte amb identificador " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Algun error desconegut ha succeit a l'afegir el nou Producte");
        }
    }

    public void eliminarProducteTest() {
        String opcio;
        opcio = scanner.next();

        try {
            domini.eliminarProducte(opcio);
        } catch (ProductNotFoundException e) {
            System.err.println("Producte id: " + e.getMessage() + " no existeix.");
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    public void modRelacioWithidTest() {
        String id1, id2, grau;

        id1 = scanner.next();
        id2 = scanner.next();
        grau = scanner.next();

        try {
            domini.modificarRelacio(id1, id2, Integer.parseInt(grau));
        } catch (RelacioUpdateException e) {
            //Do nothing
        } catch (RelacioNotChangedException e2) {
            //Do nothing
        } catch (ProductNotFoundException e) {
            System.err.println("Producte id: " + e.getMessage() + " no existeix.");
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.err.println("Algun error desconegut ha succeit a l'afegir el nou Producte");
        }
    }

    public void guardarInventari() {
        domini.guardarInventari();
    }

    public static void scripting(InventariParser driver) {
        String token;

        do {
            token = driver.scanner.next();
            switch (token) {
                case "crear":
                    driver.crearInventariTest();
                    break;
                case "configurar":
                    do {
                        token = driver.scanner.next();
                        try {
                            switch (token) {
                                case "addProd":
                                    driver.addProducteTest();
                                    break;
                                case "deleteProd":
                                    driver.eliminarProducteTest();
                                    break;
                                case "modRel":
                                    driver.modRelacioWithidTest();
                                    break;
                                case "guardarInventari":
                                    driver.guardarInventari();
                                    break;
                                case "end":
                                    break;
                                default:
                                    System.err.println("Token no-vàlid.");
                                    break;
                            }
                        } catch (RuntimeException e) {
                            System.err.println("Error al modificar Inventari");
                        }
                    } while (!token.equals("end"));
                    token = "";
                    break;
                default:
                    break;
            }
        } while (!token.equals("end"));
    }

}
