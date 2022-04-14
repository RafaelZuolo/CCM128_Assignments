import edu.princeton.cs.algs4.*;

public class pontimetro {

    public static void main(String[] args) {
        In G = new In(args[0]);
        Graph graph = new Graph(G);
        Digraph dir = new Digraph(graph.V());
        
        // Fazendo as coneccoes corretas no Digraph
        PonteFirstSearch detector = new PonteFirstSearch(graph, dir, 0);
        
        // detectando as componentes fortemente conexas
        KosarajuSharirSCC dirForte = new KosarajuSharirSCC(dir);
        
        if (dirForte.count() == 1) 
            StdOut.println("NAO EXISTEM PONTES");
        else {
            StdOut.println("Existem pontes que ligam " + dirForte.count() + " \"ilhas\"");
            for (int i = 0; i < graph.V(); i++){
                for (int a : dir.adj(i)) {
                    if (!dirForte.stronglyConnected(i, a))
                        StdOut.println("Ponte de " + i + " ateh " + a );
                }
            }
        }
    }
}