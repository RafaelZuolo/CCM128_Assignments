/******************************************************************************
 *  
 * Ao utilizarmos a funcao sort(), acabamos por deixar as palavras ordenadas
 * lexicograficamente, e se String1 < String2, podemos entao fazer a aresta
 * dirigida: String1 --> String2
 * 
 * Compilation:  javac FasterWordLadder.java
 *  Execution:    java FasterWordLadder wordlist.txt
 *
 *  Data files:   http://algs4.cs.princeton.edu/41graph/words5.txt
 *                http://algs4.cs.princeton.edu/41graph/words6.txt
 *                http://algs4.cs.princeton.edu/41graph/words5-knuth.txt
 *
 *  Creates a minimum length word ladder connecting two words.
 *
 *  java FasterWordLadder words5.txt
 *  flirt break
 *  length = 11
 *  flirt
 *  flint
 *  fling
 *  cling
 *  clink
 *  click
 *  clock
 *  cloak
 *  croak
 *  creak
 *  break
 *
 *  allow brown
 *  NOT CONNECTED
 *
 *  white house
 *  length = 18
 *  white
 *  while
 *  whale
 *  shale
 *  shake
 *  slake
 *  slate
 *  plate
 *  place
 *  peace
 *  peach
 *  poach
 *  coach
 *  couch
 *  cough
 *  rough
 *  rouge
 *  rouse
 *  house
 *  
 *  % java FasterWordLadder words5-knuth.txt
 *  white house
 *  length = 9
 *  white
 *  whits
 *  shits
 *  shots
 *  soots
 *  roots
 *  routs
 *  route
 *  rouse
 *  house
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class FasterWordLadder
{
    public static boolean isNeighbor(RotatedString a, RotatedString b)
    {
        assert a.length() == b.length();
        for (int i = 0; i < a.length() - 1; i++)
            if (a.charAt(i) != b.charAt(i))
            return false;
        
        if (a.charAt(a.length() - 1) != b.charAt(a.length() - 1))
            return true;
        else
            return false;
    }
    
    public static boolean isPrefixNeighbor(RotatedString a, RotatedString b)
    {
        if (b.length() - a.length() != 1)
            return false;
        
        return b.startsWith(a);
    }
    
    public static void addSameLengthEdges(Digraph G, RotatedString[] words, int start, int end, int wordSize, IndexSET<String> indexSet)
    {
        RotatedString.resetRotation();
        
        for (int k = 0; k < wordSize; k++)
        {
            Arrays.sort(words, start, end);
            
            for (int i = start; i < end - 1; i++)
            {
                RotatedString word1 = words[i];
                
                int j = 1;
                do
                {
                    RotatedString word2 = words[i + j];
                    
                    if (word1.length() != word2.length())
                        throw new RuntimeException("Words have different lengths");
                    
                    if (isNeighbor(word1, word2))
                        G.addEdge(indexSet.indexOf(word1.originalString()), indexSet.indexOf(word2.originalString()));
                    j++;
                }
                while (i + j < end && isNeighbor(word1, words[i + j]));
            }
            
            RotatedString.rotate();
        }
    }
    
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        IndexSET<String> indexSet = new IndexSET<String>();
        
        while (!in.isEmpty())
        {
            String word = in.readString();
            indexSet.add(word);
        }
        
        int N = indexSet.size();
        RotatedString[] words = new RotatedString[N];
        for (int i = 0; i < N; i++)
            words[i] = new RotatedString(indexSet.keyOf(i));
        
        System.err.println("Finished reading word list");
        
        Digraph G = new Digraph(N);
        
        // palavras de mesmo tamanho
        Arrays.sort(words, RotatedString.separatedSizeOrder());
        for (int i = 0; i < N;)
        {
            int currentSize = words[i].length();
            int j = i;
            while (j < N && words[j].length() == currentSize)
                j++;
            addSameLengthEdges(G, words, i, j, currentSize, indexSet);
            i = j;
        } 
        RotatedString.resetRotation();
        
        // palavras de tamanho diferente
        Arrays.sort(words);
        for (int i = 0; i < N - 1; i++)
        {
            int j = i + 1;
            while (j < N && words[j].startsWith(words[i]))
            {
                if (isPrefixNeighbor(words[i], words[j]))
                    G.addEdge(indexSet.indexOf(words[i].originalString()), indexSet.indexOf(words[j].originalString()));
                j++;
            }
        }

        System.err.println("Finished building Digraph");
        
        /*******************************************************************
          *  Excucao do DAGcounter
          * Agradecemos ao grande e melhor monitor de comp que tivemos
          * no curso por fornecer seu codigo fonte e ampliar nossos
          * conhecimentos, como seu funcionamento e novas funcoes como o 
          * do..while()
          *******************************************************************/
        Topological order = new Topological(G);
        if (!order.hasOrder()) throw new RuntimeException("NAO EH DAG!");
        
        int start     = indexSet.indexOf(StdIn.readString());
        int end       = indexSet.indexOf(StdIn.readString());
        int[] pathNum = new int[G.V()];

        pathNum[start] = 1;
        for (int j : order.order()) {
                for (int i : G.adj(j)) {
                    pathNum[i] += pathNum[j];
                }
        }
        StdOut.println("O numero de caminhos eh: " + pathNum[end]);
        /*******************************************************************
          *  Run breadth first search
          *******************************************************************/
//        while (!StdIn.isEmpty())
//        {
//            String from = StdIn.readString();
//            String to   = StdIn.readString();
//            
//            if (!indexSet.contains(from))
//                throw new RuntimeException(from + " is not in word list");
//            if (!indexSet.contains(to))
//                throw new RuntimeException(to   + " is not in word list");
//            
//            BreadthFirstPaths bfs = new BreadthFirstPaths(G, indexSet.indexOf(from));
//            
//            if (bfs.hasPathTo(indexSet.indexOf(to)))
//            {
//                StdOut.println("length = " + bfs.distTo(indexSet.indexOf(to)));
//                for (int v : bfs.pathTo(indexSet.indexOf(to)))
//                    StdOut.println(indexSet.keyOf(v));
//            }
//            else
//                StdOut.println("NOT CONNECTED");
//            
//            StdOut.println();
//        }
    }
}
