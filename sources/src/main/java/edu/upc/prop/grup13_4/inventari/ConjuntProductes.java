//Conjunt Productes
//Author: Dylan Bautista Cases

package edu.upc.prop.grup13_4.inventari;

import java.lang.reflect.Type;
import java.util.*;

import com.google.gson.reflect.TypeToken;
import edu.upc.prop.grup13_4.exceptions.inventari.*;
import edu.upc.prop.grup13_4.utils.Pair;

/**
 * @brief Conjunt de Productes
 *
 * Aquesta classe és una classe de suport (helper) d'Inventari. El que pretèn representar és un conjunt que emmagatzema
 * productes relacionats completament entre si.
 *
 * La invariant de la classe, per tant, és complir les definicions matemàtiques d'un conjunt i de les relacions
 * binàries entre els elements d'un mateix conjunt (endorrelacions). Un resum intuitiu és el seguent:
 *
 * - No poden existir dos Productes iguals al conjunt.
 * - No poden existir dues Relacions entre el mateix parell de Productes.
 * - Les Relacions són simètriques, no són transitives i, en aquest cas particular, no són reflexives.
 *
 * Aquesta classe a més incorpora optimitzacions per mantenir els productes ordenats amb el fi de poder-los
 * consultar eficientment. A més, proveeix al programador d'un mecanisme adaptador que permet simular un accés
 * matricial dels productes i facilitar, així, la implementació d'algorismes.
 *
 */
public class ConjuntProductes {
    //Class attributes
    private final Map<Producte, TreeSet<Relacio>> relacions = new HashMap<>();
    private final List<Producte> indexProductes = new ArrayList<>();
    private final Map<Producte, Integer> producteIndex = new HashMap<>();
    private final Map<Integer, Producte> indexProducteMap = new HashMap<>();
    private final Map<String, Producte> idProductes = new HashMap<>();

    /**
     * @brief Afegir un producte al conjunt.
     *
     * Aquest mètode afegeix un producte *prid* al conjunt de productes. Crea una relació per cada producte
     * ja existent al conjunt. Per tant, quan s'afegeix un producte, existeix una, i només una, relació entre
     * tot parell de productes.
     *
     * Per defecte, quan s'afegeix un producte les relacions que es creen implícitament tenen un valor
     * Integer.MAX_VALUE.
     *
     * @param prod Instància a afegir al conjunt.
     *
     * @throws ProductAlreadyExistsException La instància ja està continguda al conjunt de productes.
     *
     */
    public void addProducte(Producte prod) {
        if (!producteIndex.containsKey(prod)) {
            relacions.putIfAbsent(prod, new TreeSet<>());
            //Afegir per cada producte ja existent al conjunt, una relació infinita
            /*
             *  Notar que s'aprofita la propietat de clausura transitiva.
             *  Que no seria possible si fessim us d'una matriu d'adjacència.
             */
            for (Producte b : indexProductes) {
                Relacio relacio = new Relacio(prod,b, Integer.MAX_VALUE);
                relacions.computeIfAbsent(prod, _ -> new TreeSet<>()).add(relacio);
                relacions.computeIfAbsent(b, _ -> new TreeSet<>()).add(relacio);
            }
            //Afegir index a productes
            indexProductes.add(prod);
            int index = indexProductes.size() - 1;
            indexProducteMap.put(index, prod); //Integer - product mapping
            producteIndex.put(prod, index); //Product - integer mapping
            idProductes.put(prod.getId(),prod); //String id - product mapping
        } else throw new ProductAlreadyExistsException(prod.getId());
    }

    /**
     * @brief Esborrar un producte del conjunt.
     *
     * Aquest mètode esborra un producte del conjunt. Implícitament, també esborra totes les relacions existents
     * amb aquest producte.
     *
     * Esborrar un producte, a més, pot modificar els índexs dels altres productes. És a dir, no s'assegura
     * que un producte sempre tingui el mateix índex si s'esborra algun durant una operació concreta. És
     * responsabilitat del programador assegurar que això no passi.
     *
     * @param prod Instància a afegir al conjunt.
     *
     * @throws ProductNotFoundException El producte a esborrar no existeix.
     *
     * @remark Cost *O(2n)* on n es el nombre de productes existents al conjunt.
     *
     */
    public void removeProducte(Producte prod) {
        if (producteIndex.containsKey(prod)) {
            //Eliminar totes les relacions amb els altres productes
            TreeSet<Relacio> rel = relacions.get(prod);
            for (Relacio r : rel) { //For each related product
                Producte other = r.getOtherProduct(prod);
                relacions.remove(other,rel);
            }
            relacions.remove(prod);

            //Eliminar producte
            ListIterator<Producte> it = indexProductes.listIterator(getIndexProducte(prod));

            while (it.hasNext()) {
                Producte np = it.next();
                int np_index = getIndexProducte(np);
                //Replace posterior products, with decreased index
                producteIndex.put(np, np_index - 1);
                //Replace decreased indexes, with posterior products
                indexProducteMap.put(np_index - 1, np);
            }

            //Eliminate gratest correspondance, since it does not exist
            indexProducteMap.remove(indexProductes.size() - 1);

            //Remove prod correspondance
            producteIndex.remove(prod);

            //Remove product from list
            indexProductes.remove(prod);

            //Remove id mapping
            idProductes.remove(prod.getId());
        } else {
            throw new ProductNotFoundException(prod.getId());
        }
    }

    /*! @brief Modificar relació entre productes

    Afegeix el producte prod i el relaciona amb els productes ja existents amb una relació amb grau INFINIT.

    \pre i >= 0 ^ j >= 0 ^ i <= n ^ j < n
    \post S'afegeix una nova instància al sistema.

     */

    /**
     * @brief Modifica una Relacio.
     *
     * Aquest mètode modifica una relació existent entre dos productes (*prod1* i *prod2*) amb un *grau*.
     * Notar que tots els productes estan relacionats entre si per defecte, amb un valor Integer.MAX_VALUE.
     * Notar que no importa l'ordre en que s'especifiquin els productes (propietat simètrica).
     *
     * @param a Instància del primer producte de la relació.
     * @param b Instància del segon producte de la relació.
     * @param grau Grau de la relació.
     *
     * @throws RelationNotFoundException La relació no existeix entre els productes especificats.
     *
     */
    public void modRelacio(Producte a, Producte b, int grau) {
        //No estic segur de que funcioni
        Relacio relacio = new Relacio(a,b, grau);
        TreeSet<Relacio> relA = relacions.get(a);
        TreeSet<Relacio> relB = relacions.get(b);

        Relacio old = getRelacio(a,b);

        if (relA.contains(old) && relB.contains(old)) {
            if (old.grau == grau) {throw new RelacioNotChangedException();}

            relA.remove(getRelacio(a,b));
            relB.remove(getRelacio(b,a));

            relA.add(relacio);
            relB.add(relacio);

            if (old.grau != Integer.MAX_VALUE) {throw new RelacioUpdateException();} //If value has been updated, notify it.
        } else throw new RelationNotFoundException();

    }

    /**
     * @brief Obtenir dades relació - accés matricial ordenat.
     *
     * Donat un producte i, obtenir les dades de les relacions 0 < j < (n - 1) ordenades segons el seu grau
     * de similitud (de forma creixent). On n és el nombre de productes del conjunt.
     *
     * Es retorna un objecte Pair, on first és l'identificador de l'altre objecte relacionat i on second és
     * el grau de similitud entre el productes.
     *
     * Un producte mai està relacionat en si mateix.
     *
     * Aquesta funció és útil per a simular que el conjunt és una matriu d'adjacència i facilitar així la
     * implementació de qualsevol algorisme que vulgui fer ús dels productes del conjunt.
     *
     * @param i Índex del producte.
     * @param j j-èssim producte amb grau més petit relacionat amb el producte *i*.
     *
     * @throws ProductNotFoundException El producte amb índex *i* o *j* no existeix al conjunt.
     *
     * @remark Cost *O(j)*
     *
     */
    public Pair<Integer, Integer> getAdj(int i, int j) {
        Producte a =indexProductes.get(i);
        //Producte b =indexProductes.get(j);
        TreeSet<Relacio> relA = relacions.get(a);

        if (j >= relA.size()) { throw new ProductNotFoundException(j + " is too far!");}

        if (relA != null) {
            Iterator<Relacio> it = relA.iterator();
            for (int ii = 0; ii < j; ii++) {
                it.next();
            }
            Relacio r = it.next();
            int indexOtherProduct = producteIndex.get(r.getOtherProduct(a));
            return new Pair<>(indexOtherProduct, r.getGrau());
        }
        throw new ProductNotFoundException("With index: " + i);
    }

    /**
     * @brief Obtenir relació.
     *
     * Aquest mètode retorna la instància de la relació entre els productes *a* i *b*.
     *
     * Notar que l'ordre en que s'especifiquin els productes no importa. (Propietat simètrica)
     *
     * @param a Un producte de la relació.
     * @param b L'altre producte de la relació.
     *
     * @throws RelationNotFoundException La relació entre els productes *a* i *b* no existeix.
     *
     */
    public Relacio getRelacio(Producte a, Producte b) {
        TreeSet<Relacio> relA = relacions.get(a);
        for (Relacio rel : relA) {
            if (rel.getOtherProduct(a).equals(b)) {
                return rel;
            }
        }
        throw new RelationNotFoundException();
    }

    /**
     * @brief Obtenir relació.
     *
     * Aquest mètode retorna la instància de la relació entre els productes *a* i *b*.
     *
     * Notar que l'ordre en que s'especifiquin els productes no importa. (Propietat simètrica)
     *
     * @param i L'identificador d'un producte de la relació.
     * @param j L'altre identificador del producte de la relació.
     *
     * @throws RelationNotFoundException La relació entre els productes *a* i *b* no existeix.
     *
     */
    public int getRelacio(int i, int j) {
        Producte a = indexProducteMap.get(i);
        Producte b = indexProducteMap.get(j);
        return getRelacio(a,b).getGrau();
    }

    /**
     * @brief Obtenir índex d'un producte.
     *
     * Aquest mètode retorna l'índex d'un producte. Notar que no s'assegura que un producte tingui sempre
     * el mateix índex.
     *
     * @param a L'identificador del producte.
     *
     * @throws ProductNotFoundException El producte no està contingut al conjunt.
     *
     */
    public int getIndexProducte(Producte a) {
        return producteIndex.get(a);
    }

    /**
     * @brief Obtenir producte d'un índex.
     *
     * Aquest mètode retorna el producte corresponent a un índex. Notar que no s'assegura que un producte tingui sempre
     * el mateix índex.
     *
     * @param i Índex del producte.
     *
     */
    public String getProducteIndex(int i) {
        return indexProductes.get(i).getId();
    }


    /**
     * @brief Obtenir el nombre de productes del conjunt.
     *
     * Aquest mètode retorna la cardinalitat del conjunt, és a dir, el nombre de productes que hi ha al conjunt.
     *
     */
    public int getSize() {
        return indexProductes.size();
    }

    /**
     * @brief Obtenir el nombre de relacions del conjunt.
     *
     * Aquest mètode retorna el nombre de relacions que té un producte *p*.
     *
     * @param p El producte que es vol consultar.
     *
     * @throws ProductNotFoundException El producte no está contingut al conjunt de produtes.
     *
     */
    public int getNumRealcions(Producte p) {
        return relacions.get(p).size();
    }


    /**
     * @brief Obtenir un producte a partir del seu identificador.
     *
     * Aquest mètode retorna la instància producte que li correspon l'identificador *id*.
     *
     * @param id L'identificador del producte.
     *
     * @throws ProductNotFoundException El producte amb identificador *id* no está contingut al conjunt de produtes.
     *
     */
    public Producte getProducte(String id) {
        if(idProductes.containsKey(id)) {
            return idProductes.get(id);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    /**
     * @brief Obtenir informació dels Productes de l'Inventari.
     *
     * Aquest mètode retorna una llista amb els identificadors dels Productes presents a l'Inventari.
     *
     */
    public List<String> getInfoProductes() {
        List<String> list = new ArrayList<>();

        for (Producte p : indexProductes) {
            list.add(p.getId());
        }

        return list;
    }

    /**
     * @brief Obtenir informació de les Relacions entre els Productes.
     *
     * Aquest mètode mostra pel canal estàndard de sortida una representació gràfica de les relacions
     * entre els productes d'Inventari que són menors a Integer.MAX_VALUE (infinit).
     *
     */
    public List<Pair<String, Integer>> getInfoRelacions(String prodId) {
        Producte p = getProducte(prodId);
        if (relacions.get(p) != null) {
            List<Pair<String, Integer>> list = new ArrayList<>();
            for (Relacio r : relacions.get(p)) {
                list.add(new Pair<>(r.getOtherProduct(p).getId(), r.getGrau()));
            }
            return list;
        }
        return null;
    }
}