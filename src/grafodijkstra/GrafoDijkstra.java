/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafodijkstra;

/**
 *
 * @author Bruno
 */
public class GrafoDijkstra {

    private static void grafoTeste(Grafo grafo) {
        grafo.inserirVertice("S");
        grafo.inserirVertice("U");
        grafo.inserirVertice("V");
        grafo.inserirVertice("X");
        grafo.inserirVertice("Y");
        grafo.inserirAresta("S", "U", 10.0);
        grafo.inserirAresta("U", "V", 1.0);
        grafo.inserirAresta("V", "Y", 4.0);
        grafo.inserirAresta("Y", "S", 7.0);
        grafo.inserirAresta("S", "X", 5.0);
        grafo.inserirAresta("X", "V", 9.0);
        grafo.inserirAresta("X", "Y", 2.0);
        grafo.inserirAresta("X", "U", 3.0);
        grafo.inserirAresta("U", "X", 2.0);
        grafo.inserirAresta("Y", "V", 6.0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        //Criação de grafo para teste
        grafoTeste(grafo);
        //Interação com usuário
        grafo.menu();
    }
}
