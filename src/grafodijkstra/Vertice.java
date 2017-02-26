/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafodijkstra;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class Vertice {
    String nome;
    List<Aresta> arestas = new ArrayList(); //Lista de arestas que partem
    int id; //Número de identificação do vértice
    static private int numeroTotalVertices = 0;

    Vertice(String nome) {
        this.nome = nome;
        id = ++numeroTotalVertices;
    }
    
    void addAresta(Aresta aresta){
        arestas.add(aresta);
    }
    
    int getGrau(){ //Retorna o grau do vértice
        return arestas.size();
    }

    boolean equals(String nome) {
        return this.nome.equals(nome);
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
