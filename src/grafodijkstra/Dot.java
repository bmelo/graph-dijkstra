/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafodijkstra;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Bruno
 * Documentação: http://www.graphviz.org/Documentation.php
 */
public class Dot {
    
    static private void gravar(Grafo g, String nomeArq) throws IOException {
        
         FileWriter x = new FileWriter(nomeArq);  
         int vO, vD;
                 
         x.write("graph G {\n");
         x.write("node [color=salmon shape=circle height=0.0 width=0.0 peripheries=2];\n");
         for(Vertice v:g.vertices){ //Percorre a lista de vértices
             x.write(v.id+" [label=\""+ v.nome +"\"]\n"); //Escreve no arquivo gerador do grafo o vértice percorrido
         }
         for(Aresta a:g.arestas){
             vO = g.getVertice(a.verticeOrigem).id;
             vD = g.getVertice(a.verticeDestino).id;
             x.write(vO+" -- "+ vD +"[arrowhead=normal label=\""+a.peso+"\"]\n"); //Escreve no arquivo gerador do grafo o vértice percorrido
         }
         x.write("}");
         x.close(); // cria o arquivo
    }
    
    static public void desenharGrafo(Grafo g){
        try{
            Dot.gravar(g, "grafo.dot");
            Process proc = Runtime.getRuntime().exec("programaDot\\dot -Tgif grafo.dot -o grafo.gif");
            proc.waitFor();
            proc = Runtime.getRuntime().exec("mspaint grafo.gif");
        }catch(Exception e){
            System.out.println("ERRO AO TENTAR GERAR A IMAGEM DO GRAFO!");
        }
    }   
}