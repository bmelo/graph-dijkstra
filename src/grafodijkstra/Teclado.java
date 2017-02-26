/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafodijkstra;

import java.util.Scanner;

/**
 *
 * @author Bruno
 */
public class Teclado {
    static Scanner entrada = new Scanner(System.in).useDelimiter("\n");
    
    static String lerString(String texto){
        System.out.print(texto);
        return entrada.nextLine();
    }
    
    static double lerDouble(String texto){
        System.out.print(texto);
        String dado = entrada.nextLine();
		if(!dado.isEmpty()){
			return Double.parseDouble(dado);
		}
		return Double.NaN;
    }
    
    static int lerInt(String texto){
        System.out.print(texto);
		String dado = entrada.nextLine();
		if(!dado.isEmpty()){
			return Integer.parseInt(dado);
		}
		return 0;
    }

	static void pausar() {
		Teclado.lerString("\n<Enter> para continuar.");
	}
}