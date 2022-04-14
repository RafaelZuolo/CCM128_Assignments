import edu.princeton.cs.algs4.*;

public class COREimetro {
    
    private static boolean isLinked(int a, int b, Digraph D) {
        for (int i : D.adj(a)) {
            if (i == b) return true;
        }
        return false;
    }
    
    public static void main(String[] args){
        int first = 0;
        In in = new In(args[0]);
        Digraph dig = new Digraph(in);
        
//usaremos os id do SCC como nossos vertices no kernel
        KosarajuSharirSCC scc = new KosarajuSharirSCC(dig);
        Digraph kanil = new Digraph(scc.count());  // sera o kernel
        
        for (int i = 0; i < dig.V(); i++) {
            for(int j : dig.adj(i)) {
                if (scc.id(i) != scc.id(j) && !isLinked(scc.id(i), scc.id(j), kanil))
                    kanil.addEdge(scc.id(i), scc.id(j));
            }
        }
        Topological top = new Topological(kanil);
        for (int i : top.order()) {
            first = i;
            break;
        }
        BreadthFirstDirectedPaths rotaKanil = new BreadthFirstDirectedPaths(kanil, first);
 // se o primeiro vertice na ordem topologica nao for CORE, nenhum sera
        for (int i : top.order()) {
            if (!rotaKanil.hasPathTo(i)) {
                StdOut.println("NO CORE VERTICES");
                return;
            }
        }
        StdOut.println("Os Core vertices sao:");
        for (int i = 0; i < dig.V(); i++) {
            if (top.rank(scc.id(i)) == 0)
                StdOut.println(i);
        }
    }
}