/******************************************************************************
 *  Compilation:  javac DirectedDFS.java
 *  Execution:    java DirectedDFS V E
 *  Dependencies: Digraph.java Bag.java In.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/42digraph/tinyDG.txt
 *
 *  Determine single-source or multiple-source reachability in a digraph
 *  using depth first search.
 *  Runs in O(E + V) time.
 *
 *  % java DirectedDFS tinyDG.txt 1
 *  1
 *
 *  % java DirectedDFS tinyDG.txt 2
 *  0 1 2 3 4 5
 *
 *  % java DirectedDFS tinyDG.txt 1 2 6
 *  0 1 2 3 4 5 6 8 9 10 11 12 
 *
 ******************************************************************************/
/**
 *  The <tt>DirectedDFS</tt> class represents a data type for 
 *  determining the vertices reachable from a given source vertex <em>s</em>
 *  (or set of source vertices) in a digraph. For versions that find the paths,
 *  see {@link DepthFirstDirectedPaths} and {@link BreadthFirstDirectedPaths}.
 *  <p>
 *  This implementation uses depth-first search.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>
 *  (in the worst case),
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  <p>
 *  For additional documentation,
 *  see <a href="http://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
import edu.princeton.cs.algs4.*; 
 
public class DirectedDFS {
    private int[] dady;        // vetor dos pais dos vertices, se =-1, nao tem pai
	private boolean[] marked;  // marked[v] = true if v is reachable
                               // from source (or sources)
    private int count;         // number of vertices reachable from s

    /**
     * Computes the vertices in digraph <tt>G</tt> that are
     * reachable from the source vertex <tt>s</tt>.
     * @param G the digraph
     * @param s the source vertex
     */
    public DirectedDFS(Digraph G, int s) {
		dady = new int[G.V()];
		for(int i = 0; i < G.V(); i++) dady[i] = -1;
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    /**
     * Computes the vertices in digraph <tt>G</tt> that are
     * connected to any of the source vertices <tt>sources</tt>.
     * @param G the graph
     * @param sources the source vertices
     */
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
		dady = new int[G.V()];
		for(int i = 0; i < G.V(); i++) dady[i] = -1;
        marked = new boolean[G.V()];
        for (int v : sources) {
            if (!marked[v]) dfs(G, v);
        }
    }
	
    private void dfs(Digraph G, int v) { 
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
				dady[w] = v;
				dfs(G, w);
			}
        }
    }

    /**
     * Is there a directed path from the source vertex (or any
     * of the source vertices) and vertex <tt>v</tt>?
     * @param v the vertex
     * @return <tt>true</tt> if there is a directed path, <tt>false</tt> otherwise
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * Returns the number of vertices reachable from the source vertex
     * (or source vertices).
     * @return the number of vertices reachable from the source vertex
     *   (or source vertices)
     */
    public int count() {
        return count;
    }
	
	public boolean hasFather(int v) {
		return dady[v] != -1;
	}
	
	/**
	 * Retorna o Pai do vertice v, se existir
	 */
	public int myFatherIs(int v) {
		return dady[v];
	}
	
    /**
     * Unit tests the <tt>DirectedDFS</tt> data type.
     */
    public static void main(String[] args) {

        // read in digraph from command-line argument
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        // read in sources from command-line arguments
        Bag<Integer> sources = new Bag<Integer>();
        for (int i = 1; i < args.length; i++) {
            int s = Integer.parseInt(args[i]);
            sources.add(s);
        }

        // multiple-source reachability
        DirectedDFS dfs = new DirectedDFS(G, sources);

        // print out vertices reachable from sources
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) StdOut.println(v + " " + dfs.myFatherIs(v));
        }
        StdOut.println();
    }

}
