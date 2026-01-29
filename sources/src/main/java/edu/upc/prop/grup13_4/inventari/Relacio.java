//Conjunt Productes
//Author: Dylan Bautista Cases

package edu.upc.prop.grup13_4.inventari;

import edu.upc.prop.grup13_4.exceptions.inventari.ProductNotFoundException;

import java.util.Objects;

/**
 * @brief Classe de Relació.
 *
 */
public class Relacio  implements Comparable<Relacio>{
    //Class attributes
    Producte prod1;
    Producte prod2;
    int grau;

    //Constructors

    /**
     * @brief Constructora de Relació.
     *
     * @param prod1 Identificador d'un producte de la relació.
     * @param prod2 Identificador de l'altre producte de la relació.
     * @param grau Grau de la relació entre els productes.
     *
     */
    public Relacio(Producte prod1, Producte prod2, int grau) {
        if (prod1.getId().compareTo(prod2.getId()) < 0) {
            this.prod1 = prod1;
            this.prod2 = prod2;
        } else {
            this.prod1 = prod2;
            this.prod2 = prod1;
        }
        this.grau = grau;
    }

    //Getters

    /**
     * @brief Obtenir el grau del paràmetre implícit.
     *
     */
    public int getGrau() {
        return grau;
    }

    /**
     * @brief Obtenir el producte complementari.
     *
     * Donat un producte *p* de la relació, retorna el producte complementari.
     *
     * @throws ProductNotFoundException El producte proporcionat no pertany a la relació.
     *
     */
    public Producte getOtherProduct(Producte prod) {
        if (prod.equals(prod1)) return prod2;
        if (prod.equals(prod2)) return prod1;
        throw new ProductNotFoundException("Producte no contingut a la relaciño");
    }

    //Setters

    @Override
    public int compareTo(Relacio other) {
        int prod1Comparison = this.prod1.compareTo(other.prod1);
        int prod2Comparison = this.prod2.compareTo(other.prod2);

        // Si `prod1` y `prod2` son los mismos que en `other` (sin importar el orden), consideramos los objetos iguales
        boolean sameProducts = (prod1Comparison == 0 && prod2Comparison == 0) ||
                (this.prod1.compareTo(other.prod2) == 0 && this.prod2.compareTo(other.prod1) == 0);

        if (sameProducts) {
            return 0; // Son iguales para `TreeSet`
        }

        // Si los productos son diferentes, ordenamos por `grau`
        int grauComparison = Integer.compare(this.grau, other.grau);
        if (grauComparison != 0) {
            return grauComparison;
        }

        //Criteri addicional, si tenen el mateix grau. Basarem la cerca en l'ordre alfabetic dels ids dels productes.
        String thisCombined = this.prod1.getId() + this.prod2.getId();
        String otherCombined = other.prod1.getId() + other.prod2.getId();

        return thisCombined.compareTo(otherCombined);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Relacio)) return false;
        Relacio other = (Relacio) obj;
        return (this.prod1.equals(other.prod1) && this.prod2.equals(other.prod2));
    }

    @Override
    public int hashCode() {
        return Objects.hash(prod1, prod2);
    }
}