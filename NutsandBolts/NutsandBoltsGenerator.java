// gerador de vetores aleatorios de tamanho N

import edu.princeton.cs.algs4.*;
public class NutsandBoltsGenerator{
    
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int[] vetor = new int[N];
        StdOut.println(N);
        
        for(int i=0; i<N; i++){
            vetor[i] = i;
        }
        StdRandom.shuffle(vetor);
        for(int i=0; i<N; i++){
            StdOut.print(vetor[i]+" ");
        }
        StdRandom.shuffle(vetor);
        for(int i=0; i<N; i++){
            StdOut.print(vetor[i]+" ");
        }
    }
}