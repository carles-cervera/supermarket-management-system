package edu.upc.prop.grup13_4.inventari;

import edu.upc.prop.grup13_4.exceptions.inventari.ProductNotFoundException;

import java.util.Objects;

/**
 * @brief Classe de Producte
 *
 */
public class Producte implements Comparable<Producte> {
    //Class attributes
    private final String id;

    /**
     * @brief Constructora de Producte.
     *
     * @param id Identificador del producte.
     *
     */
    public Producte(String id) {
        this.id = id;
    }

    //Getters

    /**
     * @brief Obtenir l'identificador del Producte.
     *
     * Aquest mètode retorna l'identificador del paràmetre implícit.
     *
     */
    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Producte o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producte producte = (Producte) o;
        return id.equals(producte.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    } //Alternative way to get id
}
