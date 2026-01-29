package edu.upc.prop.grup13_4.exceptions.inventari;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}