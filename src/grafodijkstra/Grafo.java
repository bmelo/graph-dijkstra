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
public class Grafo {

    List<Vertice> vertices = new ArrayList();
    List<Aresta> arestas = new ArrayList();

    int verticeExiste(String nome) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(nome)) {
                return i;
            }
        }
        return -1;
    }
    
    Vertice getVertice(String nome) {
        int posV = verticeExiste(nome);
        if (posV > -1) {
            return vertices.get(posV);
        }
        return null;
    }

    void inserirVertice(String nome) {
        this.vertices.add(new Vertice(nome));
    }

    void inserirVertices() {
        String nomeV = Teclado.lerString("Informe o vértice (0 para encerrar): ");
        while (!nomeV.equals("0")) {
            if (verticeExiste(nomeV) > -1) {
                System.out.println("Vértice já cadastrado!");
            } else {
                this.inserirVertice(nomeV);
            }
            nomeV = Teclado.lerString("Informe o vértice (0 para encerrar): ");
        }
    }

    void inserirAresta(String vOrigem, String vDestino, double peso) {
        Aresta novaAresta = new Aresta(vOrigem, vDestino, peso);
        arestas.add(novaAresta);
        getVertice(vOrigem).addAresta(novaAresta);//Como o grafo é dirigido, aresta adicionada apenas no vértice de origem
    }

    void inserirArestas() {
        String vOrigem = Teclado.lerString("Informe o vértice de origem (0 para encerrar): ");
        while (!vOrigem.equals("0")) {
            String vDestino = Teclado.lerString("Informe o vértice de destino: ");
            if ((verticeExiste(vOrigem) > -1) && (verticeExiste(vDestino) > -1)) {
                double peso = Teclado.lerDouble("Informe o peso: ");
                this.inserirAresta(vOrigem, vDestino, peso);
            } else {
                System.out.println("Os vértices da aresta precisam estar presentes no grafo.");
            }
            vOrigem = Teclado.lerString("Informe o vértice de origem (0 para encerrar): ");
        }
    }

    void visualizar() {
        System.out.print("\nVértices = {");
        for (Vertice vertice : vertices) {
            System.out.print(vertice + ",");
        }
        System.out.println("}");

        System.out.println("Arestas = {");
        for (Aresta aresta : arestas) {
            System.out.println("{ " + aresta.verticeOrigem + ", " + aresta.verticeDestino + ", " + aresta.peso + " },");
        }
        System.out.println("}");
        Teclado.pausar();
    }

    boolean existePesoNegativo() {
        for (Aresta aresta : arestas) {
            if (aresta.peso < 0) {
                return true;
            }
        }
        return false;
    }

    void algoritmoDijkstra(String verticeInicial) {
        Vertice vAtual = getVertice(verticeInicial); //Define o vertice inicial
        Vertice vAdj = null;
        Perm conjPerm = new Perm(this.vertices, vAtual); //Inicializa o conjunto de distancias
        while(vAtual != null){ //Enquanto existir vértice a ser verificado irá executar
            for( int i=0; i < vAtual.getGrau(); i++){ //Cálculo principal do algoritmo
                vAdj = getVertice( vAtual.arestas.get(i).getDestino() ) ; //Recebe o vértice de destino que vai ser avaliado
                double somaDist = conjPerm.distancia(vAtual) + vAtual.arestas.get(i).getPeso(); //Soma as distâncias para depois compará-las e atualizar, se for o caso
                if( conjPerm.distancia(vAdj) > somaDist ){
                    conjPerm.setDistancia(vAdj, somaDist);
                }
            }
            vAtual = conjPerm.proximoVertice(); //Recebe o próximo vértice a ser avaliado, seguindo a definição do algoritmo
        }
        //Mostra na tela o resultado obtido
        System.out.println("Resultado da execução do algoritmo de Dijkstra: ");
        conjPerm.escreverResultado();
    }

    int exibeMenu() {
        System.out.println("\n#### MENU - GRAFO ####");
        System.out.println(" 1 - Inserir Vértices");
        System.out.println(" 2 - Inserir Arestas");
        System.out.println(" 3 - Visualizar Grafo");
        System.out.println(" 4 - Desenhar Grafo");
        System.out.println(" 5 - Algoritmo de Dijkstra");
        System.out.println(" 6 - Sair");
        return Teclado.lerInt("Digite sua opção: ");
    }

    void menu() {
        String confirmacao, vertice;
        int opcao = exibeMenu();
        while (true) {
            if (opcao == 6) {
                confirmacao = Teclado.lerString("Você tem certeza que deseja sair? (Digite S para sair): ");
                if (confirmacao.equals("S") || confirmacao.equals("s")) {
                    break;
                }
            }
            switch (opcao) {
                case 1:
                    inserirVertices();
                    break;
                case 2:
                    inserirArestas();
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    Dot.desenharGrafo(this);
                    break;
                case 5:
                    vertice = Teclado.lerString("Informe o vértice de partida: ");
                    if ((verticeExiste(vertice) > -1) && !existePesoNegativo()) {
                        algoritmoDijkstra(vertice);
                    } else {
                        System.out.println("Algoritmo não pode ser executado por algum destes motivos:");
                        System.out.println("O vértice de partida não faz parte do grafo;");
                        System.out.println("Existe alguma aresta com peso negativo.");
                    }
                    break;
                default:
                    System.out.println("Valor inválido");
            }
            opcao = exibeMenu();
        }
    }
}

/*
     public class ListMapx  {     
       private int id;  
       private String ip;  
      
       public ListMapx(int i, String string) {  
          this.id = i;  
          this.ip = string;  
       }  
      
       public static void main(String[] args) {  
          List list = new ArrayList();  
            
          list.add(new ListMapx(1,"10.0.0.110"));  
          list.add(new ListMapx(2,"10.0.0.111"));  
          list.add(new ListMapx(2,"10.0.0.112"));  
          list.add(new ListMapx(3,"10.0.0.113"));  
          list.add(new ListMapx(4,"10.0.0.114"));  
            
          Map map = new HashMap();  
          map.put("1", list);  
            
          for (Iterator i=map.values().iterator(); i.hasNext(); ) {  
                List ok = (List) i.next();  
                for(Iterator o=ok.iterator(); o.hasNext();) {  
                      ListMapx matriz = (ListMapx) o.next();  
                         System.out.println(matriz.id);  
                           System.out.println(matriz.ip);  
              }  
           }  
       }  
    }  
      
    Resultado:   
      
    1  
    10.0.0.110  
    2  
    10.0.0.111  
    2  
    10.0.0.112  
    3  
    10.0.0.113  
    4  
    10.0.0.114  
 */