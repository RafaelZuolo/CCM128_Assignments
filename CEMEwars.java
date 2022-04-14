/*
 * Para localizar as cidades seguras, basta descobrirmos o caminho mais curto
 * da cidade do heroi para todas as outras. Uma cidade eh dita segura se o 
 * caminho do heroi eh menor que o do feiticero. Se uma cidade tem o menor 
 * caminho para todos os herois que o do feiticero, ela pode ser um ponto de
 * encontro de todos os herois.
 */

import edu.princeton.cs.algs4.*;

public class CEMEwars {
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        int S = 0;
        Bag<Integer> safeCities = new Bag<Integer>();
        EdgeWeightedDigraph roads = new EdgeWeightedDigraph(in.readInt());
        int M = in.readInt();
        int K = in.readInt();
        DijkstraSP[] heroPaths = new DijkstraSP[K];
        
        for (int i = 0; i < M; i++) {
            DirectedEdge rua = new DirectedEdge(in.readInt(), in.readInt(), in.readDouble());
            roads.addEdge(rua);
        }
        // reino foi montado
        
        for (int i = 0; i < K; i++) {
            heroPaths[i] = new DijkstraSP(roads, in.readInt());
        }
        DijkstraSP evilPath = new DijkstraSP(roads, in.readInt());
        // Shortest Path computado
        
        for (int i = 0; i < roads.V(); i++) {
            boolean goodPath = true;
            double evilDist = evilPath.distTo(i);
            for (int j = 0; j < K; j++) {
                if (heroPaths[j].distTo(i) >= evilDist) {
                    goodPath = false;
                    break;
                }
            }
            if (goodPath) {
                S++;
                safeCities.add(i);
            }
        }
        // pontos de encontros calculados
        
        if (S > 0) {
            StdOut.println("VICTORY AND HAPPY EVER AFTER");
            StdOut.println(S);
            for (int i : safeCities)
                StdOut.print(i + " ");
            StdOut.println("");
        }
        else
            StdOut.println("DEMISE OF THE KINGDOM");
    }
}