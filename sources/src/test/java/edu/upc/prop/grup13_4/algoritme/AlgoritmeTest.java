package edu.upc.prop.grup13_4.algoritme;

import edu.upc.prop.grup13_4.inventari.Inventari;
import edu.upc.prop.grup13_4.inventari.Producte;
import static org.junit.Assert.*;
import org.junit.Test;

 /** @brief Tests de la classe Algoritme
 */
public class AlgoritmeTest {

    private Algoritme algoritme;
    private Inventari inventario;

     /** @brief Genera un inventari a partir d'una matriu
      * @param matrix Matriu de sinèrgies de productes
      *
      * @pre Matriu diferent de null
      * @post Inventari amb els productes i relacions creat d'acord amb la matriu
    */
    public static Inventari generateInventariFromMatrix(int[][] matrix) {
        Inventari inventario = new Inventari("GeneratedInventari", "dummy");

        int n = matrix.length;
        Producte[] productes = new Producte[n];

        for (int i = 0; i < n; i++) {
            productes[i] = new Producte("producto" + i);
            inventario.addProducte(productes[i]);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                inventario.modRelacio(productes[i], productes[j], matrix[i][j]);
            }
        }
        return inventario;
    }

    //Test per Bactracking
    //Mirarem si el cost de Bactracking és IGUAL al cost òptim, ja que aquest algorisme ens ha de donar la millor solució

    @Test
    public void Backtracking1() {
        inventario = new Inventari("test1", "dummy");
        algoritme = new Algoritme();
        algoritme.reset_bestCost();
        Producte p0 = new Producte("Ginebra");
        inventario.addProducte(p0);
        algoritme.backtracking(inventario);
        int expectedCost = 0;
        assertEquals(expectedCost, algoritme.getBestCost());
    }

    @Test
    public void Backtracking3() {
        algoritme = new Algoritme();
        algoritme.reset_bestCost();
        int[][] matrix = {
                {0,10,30},
                {10,0,20},
                {30,20,0}
        };
        inventario = generateInventariFromMatrix(matrix);
        algoritme.backtracking(inventario);
        int expectedCost = 60;
        assertEquals(expectedCost, algoritme.getBestCost());
    }

    @Test
    public void Backtracking9() {
        algoritme = new Algoritme();
        algoritme.reset_bestCost();
        int[][] matrix = {
                {  0,29,82,46,68,52,72,42,51},
                { 29, 0,55,46,42,43,43,23,23},
                { 82,55, 0,68,46,55,23,43,41},
                { 46,46,68, 0,82,15,72,31,62},
                { 68,42,46,82, 0,74,23,52,21},
                { 52,43,55,15,74, 0,61,23,55},
                { 72,43,23,72,23,61, 0,42,23},
                { 42,23,43,31,52,23,42, 0,33},
                { 51,23,41,62,21,55,23,33, 0}
        };
        inventario = generateInventariFromMatrix(matrix);
        algoritme.backtracking(inventario);
        int expectedCost = 246;
        assertEquals(expectedCost, algoritme.getBestCost());
    }

    @Test
    public void Backtracking15() {
        algoritme = new Algoritme();
        algoritme.reset_bestCost();
        int[][] matrix = {
                {0, 29, 82, 46, 68, 52, 72, 42, 51, 55, 29, 74, 23, 72, 46},
                {29, 0, 55, 46, 42, 43, 43, 23, 23, 31, 41, 51, 11, 52, 21},
                {82, 55, 0, 68, 46, 55, 23, 43, 41, 29, 79, 21, 64, 31, 51},
                {46, 46, 68, 0, 82, 15, 72, 31, 62, 42, 21, 51, 51, 43, 64},
                {68, 42, 46, 82, 0, 74, 23, 52, 21, 46, 82, 58, 46, 65, 23},
                {52, 43, 55, 15, 74, 0, 61, 23, 55, 31, 33, 37, 51, 29, 59},
                {72, 43, 23, 72, 23, 61, 0, 42, 23, 31, 77, 37, 51, 46, 33},
                {42, 23, 43, 31, 52, 23, 42, 0, 33, 15, 37, 33, 33, 31, 37},
                {51, 23, 41, 62, 21, 55, 23, 33, 0, 29, 62, 46, 29, 51, 11},
                {55, 31, 29, 42, 46, 31, 31, 15, 29, 0, 51, 21, 41, 23, 37},
                {29, 41, 79, 21, 82, 33, 77, 37, 62, 51, 0, 65, 42, 59, 61},
                {74, 51, 21, 51, 58, 37, 37, 33, 46, 21, 65, 0, 61, 11, 55},
                {23, 11, 64, 51, 46, 51, 51, 33, 29, 41, 42, 61, 0, 62, 23},
                {72, 52, 31, 43, 65, 29, 46, 31, 51, 23, 59, 11, 62, 0, 59},
                {46, 21, 51, 64, 23, 59, 33, 37, 11, 37, 61, 55, 23, 59, 0}
        };
        inventario = generateInventariFromMatrix(matrix);
        algoritme.backtracking(inventario);
        int expectedCost = 291;
        assertEquals(expectedCost, algoritme.getBestCost());
    }

    //Test per a l'algorisme d'aproximació
    //Mirarem si el cost és com a molt dues vegades l'òptim, ja que ens ha de proporcionar una solució 2-aproximada i eficient

    @Test
    public void Aproximate1() {
        algoritme = new Algoritme();
        algoritme.reset_bestCost();
        inventario = new Inventari("test1", "dummy");
        Producte p0 = new Producte("Ginebra");
        inventario.addProducte(p0);
        algoritme.aproximate(inventario);
        int expectedCost = 0;
        assertEquals(expectedCost, algoritme.getBestCost());
    }

    @Test
    public void Aproximate3() {
        algoritme = new Algoritme();
        algoritme.reset_bestCost();
        int[][] matrix = {
                {0,10,30},
                {10,0,20},
                {30,20,0}
        };
        inventario = generateInventariFromMatrix(matrix);
        algoritme.aproximate(inventario);
        int expectedCost = 60;
        assertTrue(algoritme.getBestCost() <= 2*expectedCost);
    }

    @Test
    public void Aproximate9() {
        algoritme = new Algoritme();
        algoritme.reset_bestCost();
        int[][] matrix = {
                {  0,29,82,46,68,52,72,42,51},
                { 29, 0,55,46,42,43,43,23,23},
                { 82,55, 0,68,46,55,23,43,41},
                { 46,46,68, 0,82,15,72,31,62},
                { 68,42,46,82, 0,74,23,52,21},
                { 52,43,55,15,74, 0,61,23,55},
                { 72,43,23,72,23,61, 0,42,23},
                { 42,23,43,31,52,23,42, 0,33},
                { 51,23,41,62,21,55,23,33, 0}
        };
        inventario = generateInventariFromMatrix(matrix);
        algoritme.aproximate(inventario);
        int expectedCost = 246;
        assertTrue(algoritme.getBestCost() <= 2*expectedCost);
    }

    @Test
    public void Aproximate15() {
        algoritme = new Algoritme();
        algoritme.reset_bestCost();
        int[][] matrix = {
                {0, 29, 82, 46, 68, 52, 72, 42, 51, 55, 29, 74, 23, 72, 46},
                {29, 0, 55, 46, 42, 43, 43, 23, 23, 31, 41, 51, 11, 52, 21},
                {82, 55, 0, 68, 46, 55, 23, 43, 41, 29, 79, 21, 64, 31, 51},
                {46, 46, 68, 0, 82, 15, 72, 31, 62, 42, 21, 51, 51, 43, 64},
                {68, 42, 46, 82, 0, 74, 23, 52, 21, 46, 82, 58, 46, 65, 23},
                {52, 43, 55, 15, 74, 0, 61, 23, 55, 31, 33, 37, 51, 29, 59},
                {72, 43, 23, 72, 23, 61, 0, 42, 23, 31, 77, 37, 51, 46, 33},
                {42, 23, 43, 31, 52, 23, 42, 0, 33, 15, 37, 33, 33, 31, 37},
                {51, 23, 41, 62, 21, 55, 23, 33, 0, 29, 62, 46, 29, 51, 11},
                {55, 31, 29, 42, 46, 31, 31, 15, 29, 0, 51, 21, 41, 23, 37},
                {29, 41, 79, 21, 82, 33, 77, 37, 62, 51, 0, 65, 42, 59, 61},
                {74, 51, 21, 51, 58, 37, 37, 33, 46, 21, 65, 0, 61, 11, 55},
                {23, 11, 64, 51, 46, 51, 51, 33, 29, 41, 42, 61, 0, 62, 23},
                {72, 52, 31, 43, 65, 29, 46, 31, 51, 23, 59, 11, 62, 0, 59},
                {46, 21, 51, 64, 23, 59, 33, 37, 11, 37, 61, 55, 23, 59, 0}
        };
        inventario = generateInventariFromMatrix(matrix);
        algoritme.aproximate(inventario);
        int expectedCost = 291;
        assertTrue(algoritme.getBestCost() <= 2*expectedCost);
    }

    @Test
    public void Aproximate26() {
        algoritme = new Algoritme();
        algoritme.reset_bestCost();
        int[][] matrix = {
                {0, 83, 93, 129, 133, 139, 151, 169, 135, 114, 110, 98, 99, 95, 81, 152, 159, 181, 172, 185, 147, 157, 185, 220, 127, 181},
                {83, 0, 40, 53, 62, 64, 91, 116, 93, 84, 95, 98, 89, 68, 67, 127, 156, 175, 152, 165, 160, 180, 223, 268, 179, 197},
                {93, 40, 0, 42, 42, 49, 59, 81, 54, 44, 58, 64, 54, 31, 36, 86, 117, 135, 112, 125, 124, 147, 193, 241, 157, 161},
                {129, 53, 42, 0, 11, 11, 46, 72, 65, 70, 88, 100, 89, 66, 76, 102, 142, 156, 127, 139, 155, 180, 228, 278, 197, 190},
                {133, 62, 42, 11, 0, 9, 35, 61, 55, 62, 82, 95, 84, 62, 74, 93, 133, 146, 117, 128, 148, 173, 222, 272, 194, 182},
                {139, 64, 49, 11, 9, 0, 39, 65, 63, 71, 90, 103, 92, 71, 82, 100, 141, 153, 124, 135, 156, 181, 230, 280, 202, 190},
                {151, 91, 59, 46, 35, 39, 0, 26, 34, 52, 71, 88, 77, 63, 78, 66, 110, 119, 88, 98, 130, 156, 206, 257, 188, 160},
                {169, 116, 81, 72, 61, 65, 26, 0, 37, 59, 75, 92, 83, 76, 91, 54, 98, 103, 70, 78, 122, 148, 198, 250, 188, 148},
                {135, 93, 54, 65, 55, 63, 34, 37, 0, 22, 39, 56, 47, 40, 55, 37, 78, 91, 62, 74, 96, 122, 172, 223, 155, 128},
                {114, 84, 44, 70, 62, 71, 52, 59, 22, 0, 20, 36, 26, 20, 34, 43, 74, 91, 68, 82, 86, 111, 160, 210, 136, 121},
                {110, 95, 58, 88, 82, 90, 71, 75, 39, 20, 0, 18, 11, 27, 32, 42, 61, 80, 64, 77, 68, 92, 140, 190, 116, 103},
                {98, 98, 64, 100, 95, 103, 88, 92, 56, 36, 18, 0, 11, 34, 31, 56, 63, 85, 75, 87, 62, 83, 129, 178, 100, 99},
                {99, 89, 54, 89, 84, 92, 77, 83, 47, 26, 11, 11, 0, 23, 24, 53, 68, 89, 74, 87, 71, 93, 140, 189, 111, 107},
                {95, 68, 31, 66, 62, 71, 63, 76, 40, 20, 27, 34, 23, 0, 15, 62, 87, 106, 87, 100, 93, 116, 163, 212, 132, 130},
                {81, 67, 36, 76, 74, 82, 78, 91, 55, 34, 32, 31, 24, 15, 0, 73, 92, 112, 96, 109, 93, 113, 158, 205, 122, 130},
                {152, 127, 86, 102, 93, 100, 66, 54, 37, 43, 42, 56, 53, 62, 73, 0, 44, 54, 26, 39, 68, 94, 144, 196, 139, 95},
                {159, 156, 117, 142, 133, 141, 110, 98, 78, 74, 61, 63, 68, 87, 92, 44, 0, 22, 34, 38, 30, 53, 102, 154, 109, 51},
                {181, 175, 135, 156, 146, 153, 119, 103, 91, 91, 80, 85, 89, 106, 112, 54, 22, 0, 33, 29, 46, 64, 107, 157, 125, 51},
                {172, 152, 112, 127, 117, 124, 88, 70, 62, 68, 64, 75, 74, 87, 96, 26, 34, 33, 0, 13, 63, 87, 135, 186, 141, 81},
                {185, 165, 125, 139, 128, 135, 98, 78, 74, 82, 77, 87, 87, 100, 109, 39, 38, 29, 13, 0, 68, 90, 136, 186, 148, 79},
                {147, 160, 124, 155, 148, 156, 130, 122, 96, 86, 68, 62, 71, 93, 93, 68, 30, 46, 63, 68, 0, 26, 77, 128, 80, 37},
                {157, 180, 147, 180, 173, 181, 156, 148, 122, 111, 92, 83, 93, 116, 113, 94, 53, 64, 87, 90, 26, 0, 50, 102, 65, 27},
                {185, 223, 193, 228, 222, 230, 206, 198, 172, 160, 140, 129, 140, 163, 158, 144, 102, 107, 135, 136, 77, 50, 0, 51, 64, 58},
                {220, 268, 241, 278, 272, 280, 257, 250, 223, 210, 190, 178, 189, 212, 205, 196, 154, 157, 186, 186, 128, 102, 51, 0, 93, 107},
                {127, 179, 157, 197, 194, 202, 188, 188, 155, 136, 116, 100, 111, 132, 122, 139, 109, 125, 141, 148, 80, 65, 64, 93, 0, 90},
                {181, 197, 161, 190, 182, 190, 160, 148, 128, 121, 103, 99, 107, 130, 130, 95, 51, 51, 81, 79, 37, 27, 58, 107, 90, 0}
        };
        inventario = generateInventariFromMatrix(matrix);
        algoritme.aproximate(inventario);
        int expectedCost = 937;
        assertTrue(algoritme.getBestCost() <= 2*expectedCost);
    }

}