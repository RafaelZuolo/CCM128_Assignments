/* 
 * Vamos utilizar um algoritmo de contagem de caminhos pelo vertice inicial,
 * que se trata de colocar o DAG em ordem topologica, atribuir um vetor de int
 * para essa ordem, e outro para os valores, os possiveis caminhos, para os
 * vertices.
 * 
 * Inicializando o vetor com zero, vemos que do primeiro vertice ateh ele mesmo
 * so existe um caminho, e portanto, para os adjacentes, um caminho, e 
 * atualizamos os caminhos desses vertices. E iteramos dessa forma na ordem
 * topologica, somando o numero de caminhos possiveis nos vertices adjacentes,
 * ateh o vertice desejado.
 */

import edu.princeton.cs.algs4.*;

public class DAGcounter {

    public static void main(String[] args) {
        In in             = new In(args[0]);
        Digraph dag       = new Digraph(in);
        Topological order = new Topological(dag);
        
        int start     = StdIn.readInt();
        int end       = StdIn.readInt();
        int[] pathNum = new int[dag.V()];

        pathNum[start] = 1;
        for (int j : order.order()) {
                for (int i : dag.adj(j)) {
                    pathNum[i] += pathNum[j];
                }
        }
        StdOut.println("O numero de caminhos eh: " + pathNum[end]);
    }
}



//