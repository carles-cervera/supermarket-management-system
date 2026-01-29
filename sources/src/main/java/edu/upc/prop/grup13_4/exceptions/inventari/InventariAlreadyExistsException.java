package edu.upc.prop.grup13_4.exceptions.inventari;

public class InventariAlreadyExistsException extends RuntimeException {
    public InventariAlreadyExistsException(String message) {
        super(message);
    }
}
