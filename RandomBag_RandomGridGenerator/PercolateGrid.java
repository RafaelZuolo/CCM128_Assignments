/*
 * Para usar esse prgrama é necessario fazer uma pipe do RandomGridGenerator 
 * para esse programa, como por exemplo:
 *  
 *  java-algs4 RandomGridGenerator 3 | java-algs4 PercolateGrid 3
 *
 * vamos usar a estrutura do union-find para descobrir a percolacao.
 * consideramos a casa N*N (parte superior da matriz - sup) como ligada
 * as N primeiras casas, e a N*N+1 (parte inferior da matriz - inf) como
 * ligada as N ultimas casas, assim para verificar se houve percolacao basta
 * ver se N*N esta unido com N*N+1
 * 
 */
import edu.princeton.cs.algs4.*;

public class PercolateGrid{

    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int sup = N*N;
        int inf = N*N+1;
        int count = 0;
        
        WeightedQuickUnionUF matriz = new WeightedQuickUnionUF(N*N+2);
        
        for(int i=0; i<N; i++){
            matriz.union(sup, i);
            matriz.union(inf, N*(N-1)+i);
        }
        while(!matriz.connected(sup, inf)){
            matriz.union(StdIn.readInt(), StdIn.readInt());  // as coneccoes sao sorteadas e printadas
            count ++;                                        // pelo RandomGridGenerator
        }
        StdOut.println(count);
        StdOut.println("A razao eh: " + (double)count/(2*N*N));
    }
}
/*
 * apos iterar esse programa varias vezes com N na ordem de 1000, 2000,
 * a razao M/2N^2 resulta em algo em torno de 0.49
 * 
 */