/*
 * Algoritmo do bolts and nuts
 * 
 */

import edu.princeton.cs.algs4.*;
public class NutsandBolts{
    
    private static int partition(int[] comp, int[] a, int lo, int hi) { // especie de quicksort, mas adaptada
        int i = lo;
        int j = hi + 1;
        int index = lo;
        
        while (comp[lo] !=a[index] && index < hi){
            if (comp[lo] == a[index]) break;      // a e comp devem ter o mesmo pivo
            index++;
        }
        exch(a, lo, index);           // agora a[lo] e comp[lo] formam par nut bolt, sao iguais
        index = lo;
        while (true) { 

            // find item on lo to swap
            while (a[++i] < comp[index]){
                if (i == hi) break;
            }
            // find item on hi to swap
            while (comp[index] < a[--j])
                if (j == lo) break;      

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);
        index = j;
        
        i = lo;      // resetar i e j para particionar comp
        j = hi + 1;
        // fazemos o mesmo para particionar o vetor comp simultaneamente
        while (true) { 

            // find item on lo to swap
            while (comp[++i] < a[index]){
                if (i == hi) break;
            }
            // find item on hi to swap
            while (a[index] < comp[--j])
                if (j == lo) break;      
            // check if pointers cross
            if (i >= j) break;

            exch(comp, i, j);
        }

        exch(comp, lo, j);

        // agora, comp[lo .. j-1] <= comp[j] <= comp[j+1 .. hi]
        return j;
    }
    
    private static void exch(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public static void sort(int comp[], int a[]){     
        sort(comp, a, 0, comp.length - 1);
    }
    
    private static void sort(int comp[], int a[], int lo, int hi){
        if(hi<=lo) return;
        int j = partition(comp, a, lo, hi);
        sort(comp, a, lo, j-1);
        sort(comp, a, j+1, hi);
    }
    
    public static void main(String[] args){
        
        int N = StdIn.readInt();
        int[] bolts = new int[N];
        int[] nuts  = new int[N];
        
        for(int i=0; i<N; i++)
            bolts[i] = StdIn.readInt();

        for(int i=0; i<N; i++)
            nuts[i] = StdIn.readInt();
        
        StdRandom.shuffle(bolts);
        StdRandom.shuffle(nuts);
        
        StdOut.print("\n");
        StdOut.print("\n");
        
        sort(nuts, bolts);     
        
        for(int i=0; i<N; i++)
            StdOut.print(bolts[i]+" ");
        StdOut.print("\n");
        for(int i=0; i<N; i++)
            StdOut.print(nuts[i]+" ");        
    }
}