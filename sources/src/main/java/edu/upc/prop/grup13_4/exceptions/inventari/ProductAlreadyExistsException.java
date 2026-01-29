package edu.upc.prop.grup13_4.exceptions.inventari;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
