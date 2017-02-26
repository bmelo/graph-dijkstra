/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafodijkstra;

/**
 *
 * @author Bruno
 */
public class Aresta {
	String verticeOrigem, verticeDestino;
	double peso;

	Aresta(String verticeOrigem, String verticeDestino, double peso) {
		this.verticeOrigem = verticeOrigem;
		this.verticeDestino = verticeDestino;
		this.peso = peso;
	}
        
        double getPeso(){
            return this.peso;
        }
        
        String getDestino(){
            return this.verticeDestino;
        }
}
