//Algoritme
//Authors: Aleix Ortigosa, Max Velasco

package edu.upc.prop.grup13_4.algoritme;

import edu.upc.prop.grup13_4.inventari.Inventari;
import edu.upc.prop.grup13_4.utils.Pair;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/** @brief Classe encarregada dels càlculs necessaris per obtenir una distribució
*/
public class Algoritme {
    //Class Atributes

    private int bestCost;
    private int[] bestCycle;

    //Methods

    /** @brief Funció que cridarà a Backtracking per trobar la distribució òptima
     *
     * @pre bestCost i bestCycle tenen els valors per defecte
     * @post El cicle per la distribució òptima estarà guardat en bestCycle
     * @remark Complexitat: O(!n)

     */
    public void backtracking(Inventari inv) {
        reset_bestCost();
        int n = inv.getNumProductes();
        if (n == 1) { //Cas Base
            bestCost = 0;
            bestCycle = new int[1];
        }
        else {
            int[] cicle = new int[n];
            boolean[] visited = new boolean[n];
            backtracking_i(inv,0,n,n,0,cicle,visited);
        }
    }

    /** @brief Algorisme Backtracking per trobar la distribució òptima

     * @param inv Instància de la classe Inventari
     * @param actNode Node actual
     * @param n Nombre de productes o nodes
     * @param remain Nombre de produtes o nodes restants per afegir al cicle
     * @param totalCost Cost total del cicle que tenim fins al moment
     * @param cycle Cicle que tenim fins al moment
     * @param visited True si el node i està visitat, False en cas contrari

     * @pre
     * @post El cicle per la distribució òptima estarà guardat en bestCycle
     * @remark Complexitat: O(!n)
     */
    public void backtracking_i(Inventari inv, int actNode, int n, int remain, int totalCost, int[] cycle,
                               boolean[] visited) {
        if (remain == 1) {
            cycle[n - remain] = actNode;
            int wFinal = inv.getRelacio(actNode,0) + totalCost;
            if(wFinal < bestCost) { //Hem trobat una solució millor
                bestCost = wFinal;
                bestCycle = cycle.clone();
            }
            cycle[n - remain] = -1;
            return;
        }
        if (totalCost < bestCost) { //Condició de poda
            cycle[n - remain] = actNode;
            visited[actNode] = true;
            for (int i = 0; i < n - 1; i++) { //Fem backtracking amb tots els nodes adjacents no visitats
                Pair<Integer,Integer> arista = inv.getAdj(actNode,i);
                int nextNode = arista.first;

                if (!visited[nextNode]) {
                    backtracking_i(inv,nextNode,n,remain - 1,
                            totalCost + arista.second, cycle, visited);
                }
            }
            //Restaurem variables
            visited[actNode] = false;
            cycle[n - remain] = -1;
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /** @brief Aquesta funció donarà la distribució aproximada eficientment

     * @param inv Instància de la classe Inventari

     * @pre
     * @post bestCycle i bestCost contindran el millor cicle i cost trobats
     * @remark Complexitat: O(n^2)
     */

    public void aproximate(Inventari inv) {
        reset_bestCost();
        int n = inv.getNumProductes();
        if (n == 1) { //Cas Base
            bestCost = 0;
            bestCycle = new int[1];
        }
        else {
            ArrayList<Pair<Integer, Integer>>[] mst = prim(inv, n);
            digraph(mst);
            ArrayList<Integer> eulerianCycle = findEulerianCycle(mst, 0);
            findHamiltonianCycle(inv,eulerianCycle, n);
        }
    }

    /** @brief Algorisme de Prim per trobar el Minimum Spanning Tree
      *
      *
      * @param inv Instància de la classe Inventari
      * @param n Nombre de productes o nodes

      * @pre
      * @post res contindrà el MST
      * @remark Complexitat: O(n^2  * log(n))
      */
    public ArrayList<Pair<Integer,Integer>>[] prim(Inventari inv, int n) {

        PriorityQueue<Pair<Integer, Pair<Integer, Integer>>> edgeQueue = new PriorityQueue<>(
                Comparator.comparingInt(p -> p.second.second)
        );
        boolean[] visited = new boolean[n];
        int count = 0;
        visited[0] = true;
        @SuppressWarnings("unchecked")
        ArrayList<Pair<Integer, Integer>>[] res = new ArrayList[n];

        for (int i = 0; i < res.length; i++) {
            res[i] = new ArrayList<>();  // Crea un arrayList buit per cada node
        }

        for (int i = 0; i < n - 1; i++) {
            edgeQueue.add(new Pair<>(0,inv.getAdj(0,i))); //Afegim les arestes del primer node a la cola de prioritat
        }

        while (count < n - 1) { //Hem de veure quan ja tenim n-1 edges (serà arbre)
            Pair<Integer,Pair<Integer,Integer>> act = edgeQueue.poll();
            if (!(visited[act.first] && visited[act.second.first])){ //Si es compleix és que forma cicle
                add_to_result(res,act);
                int next = act.second.first;
                visited[next] = true;
                for (int j = 0; j < n - 1; ++j) {
                    edgeQueue.add(new Pair<>(next,inv.getAdj(next,j))); //Encuem les arestes del nou node
                }
                count++;
            }
        }
        return res;
    }

    /** @brief Aquesta funció ens afegeix l'aresta a la nostra llista d'adjacència

     * @param res Llista d'adjacència
     * @param act Aresta actual

     * @pre
     * @post res contindrà el MST
     * @remark Complexitat: O(1)
     */
    public void add_to_result(ArrayList<Pair<Integer,Integer>>[] res, Pair<Integer,Pair<Integer,Integer>> act) {
        if (act.first < act.second.first) res[act.first].add(new Pair<>(act.second.first,act.second.second));
        else {
            res[act.second.first].add(new Pair<>(act.first,act.second.second));
        }
    }

    /** @brief Aquesta funció ens duplica les aristes de MST

     * @param G Minimum Spanning Tree

     * @pre
     * @post MST ara es un graf dirigit
     * @remark Complexitat: O(n^2)
     */
    public void digraph(ArrayList<Pair<Integer,Integer>>[] G) {
        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G[i].size(); j++) {
                Pair<Integer,Integer> selected = G[i].get(j);
                if (i < selected.first) { //Ens assegurem de només complementar les arestes originals del graf
                    G[selected.first].add(new Pair<>(i,selected.second));
                }
            }
        }
    }

    /** @brief Troba el cicle eulerià en el graf dirigit

     * @param T Llista d'adjacència
     * @param startingNode Node per on començarem

     * @pre T és el MST dirigit amb arestes duplicades
     * @post Retorna el cicle eulerià de T
     * @remark Complexitat: O(n^2)
     */
    public ArrayList<Integer> findEulerianCycle(ArrayList<Pair<Integer,Integer>>[] T, int startingNode) {
        ArrayList<Integer> eulerianCycle = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startingNode); //Afegim el Primer node a la pila
        while (!stack.isEmpty()) {
            int currNode = stack.peek();
            eulerianCycle.add(currNode);
            if(!T[currNode].isEmpty()) { //Si curr_node té alguna adjacència
                Pair<Integer,Integer> edge = T[currNode].getFirst();
                int v = edge.first;
                stack.push(v);
                T[currNode].removeFirst(); //Eliminem l'arista curr_node -> v
                removeEdge(T, v, currNode); //Eliminem l'arista v -> curr_node
            }
            else {
                stack.pop();
            }
        }
        return eulerianCycle;
    }

    /** @brief Obté el cicle hamiltonià sobre el cicle eurlerià

    * @param n El nombre de productes
    * @pre eulerianCycle és el nostre cicle eulerià
    * @post Retorna el cicle hamiltonià
    * @remark Complexitat: O(n^2)
    */
    public void findHamiltonianCycle(Inventari inv, ArrayList<Integer> eulerianCycle, int n) {
        int eulerianSize = eulerianCycle.size();
        int[] hamiltonianCycle = new int[n];
        boolean[] existeix = new boolean[n];
        int cost;

        for (int desfase = 0; desfase < eulerianSize; ++desfase) { //desfase fara que puguem començar a fer el cicle hamiltonià des de totes les posicions
            Arrays.fill(existeix, false);
            cost = 0;
            int j = 0;

            for (int x = 0; x < eulerianSize; ++x) {
                int newX = (x + desfase) % eulerianSize;
                int currentNode = eulerianCycle.get(newX);

                if (!existeix[currentNode] && j < n) {
                    hamiltonianCycle[j] = currentNode;
                    existeix[currentNode] = true;
                    if (j > 0) {
                        cost += inv.getRelacio(hamiltonianCycle[j], hamiltonianCycle[j - 1]); //Afegim el pes entre dos nodes al cost
                    }
                    ++j;
                }
            }

            if (j == n) {
                cost += inv.getRelacio(hamiltonianCycle[0], hamiltonianCycle[n - 1]);

                if (cost < bestCost) { //Si cost es menor a bestCost hem trobat una solució millor
                    bestCost = cost;
                    bestCycle = hamiltonianCycle.clone();
                }
            }
        }
    }

    /** @brief Elimina la aresta v -> u

     * @param T Llista d'adjacència

     * @pre u i v son nodes adjacents
     * @post L'aresta de v -> u queda eliminada
     * @remark Complexitat: O(n)
     */
    public void removeEdge(ArrayList<Pair<Integer,Integer>>[] T, int u, int v) {
        for (int i = 0; i < T[v].size(); i++) {
            if (T[v].get(i).first == u) {
                T[v].remove(i);
                break;
            }
        }
    }

    //Getters

    /** @brief Obté el cost del millor cicle
     */
    public int getBestCost() {
        return bestCost;
    }

    /** @brief Obté el millor cicle
     */
    public int[] getBestcycle() {
        return bestCycle;
    }

    /** @brief Reinicialitza bestCost
     */
    public void reset_bestCost() {
        bestCost = Integer.MAX_VALUE;
    }

}