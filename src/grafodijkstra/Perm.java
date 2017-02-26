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
public class Perm {

    List<Vertice> vertices; //Vértices que queremos encontrar a distância
    List<Vertice> percorridos = new ArrayList(); //Vértices que queremos encontrar a distância
    List<Vertice> naoPercorridos = new ArrayList(); //Vértices que queremos encontrar a distância
    List<Double> distancias = new ArrayList(); //Distância para cada vértice

    Perm(List<Vertice> vertices, Vertice verticePartida) {
        this.vertices = vertices;
        for (Vertice v : vertices) { //Inicializa os vetore
            if (v == verticePartida) {
                distancias.add(0.0);
                percorridos.add(v);
            } else {
                naoPercorridos.add(v);
                distancias.add(Double.MAX_VALUE);
            }
        }
    }
    
    void setDistancia(Vertice v, double distancia){
        distancias.set( vertices.indexOf(v), distancia );
    }
    
    double distancia(Vertice v){
        return distancias.get( vertices.indexOf(v) );
    }
    
    //Verifica qual o vertice não percorrido que possui a menor distancia
    Vertice proximoVertice(){
        Vertice menor = null; //Irá armazenar o menor vértice
        for(Vertice v:naoPercorridos){
            if(menor == null){
                menor = v;
            }else{
                if( distancia(menor) > distancia(v) ){ //Pega a distância do atual menor e compara com o que está sendo verificado
                    menor = v;
                }
            }
        }
        this.percorridos.add(menor); //Coloca o vértice na lista de percorridos
        this.naoPercorridos.remove(menor); //Remove o vértice da lista de não-percorridos
        return menor;
    }
    
    void escreverResultado(){
        for(int i=0; i<vertices.size(); i++){
            System.out.println(vertices.get(i)+" - "+distancias.get(i));
        }
    }
}
