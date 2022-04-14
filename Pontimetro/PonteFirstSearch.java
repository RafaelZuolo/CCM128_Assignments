import edu.princeton.cs.algs4.*;

public class PonteFirstSearch {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int count;           // number of vertices connected to s

    /**
     * Computes the vertices in graph <tt>G</tt> that are
     * connected to the source vertex <tt>s</tt>.
     * @param G the graph
     * @param s the source vertex
     */
    public PonteFirstSearch(Graph G, Digraph D, int s) {
        marked = new boolean[G.V()];
        pfs(G, D, s);
    }

    // depth first search from v
    private void pfs(Graph G, Digraph D, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                D.addEdge(v, w);
                pfs(G, D, w);
            }
            else {
                boolean connected = false;
                for (int x : D.adj(w)) {
                    if (x == v) connected = true;
                }
                if (!connected) D.addEdge(v, w);
            }
        }
    }

    /**
     * Is there a path between the source vertex <tt>s</tt> and vertex <tt>v</tt>?
     * @param v the vertex
     * @return <tt>true</tt> if there is a path, <tt>false</tt> otherwise
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * Returns the number of vertices connected to the source vertex <tt>s</tt>.
     * @return the number of vertices connected to the source vertex <tt>s</tt>
     */
    public int count() {
        return count;
    }
}