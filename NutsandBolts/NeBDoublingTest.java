/*
 *   Doubling test do NutsandBolts, analisando os resultados, vide imagem, vemos na primeira execucao
 * o teste para muitos parafusos e porcas iguais, 25% delas, usando o i%4.
 *   Na segunda execucao foi testado valores sem repeticoes. Sem repeticoes o programa rodou bem mais lento do que 
 * com repeticoes, ou seja, ele eh melhor quando ha repeticoes de porcas e parafusos.
 *   Analisando os tempos de execucao vemos q o algoritmo eh linearitmico nos dois casos.
 * 
 * obs o args[0] se trata do numero de casos q estamos testando para fazer a media do tempo deles.
 */

import edu.princeton.cs.algs4.*;

public class NeBDoublingTest{
    
    public static void main(String[] args){
        
        int N = 100;
        int nTestes = Integer.parseInt(args[0]);
        
        while(true){
            int[] bolts = new int[N];
            int[] nuts  = new int[N];
            
            for(int i=0; i<N; i++)
                bolts[i] = i;       
                //bolts[i] = i%4;
            
            for(int i=0; i<N; i++)
                nuts[i] = i;        
                //nuts[i] = i%4;
            
            double media = 0;
            for(int i=0; i<nTestes; i++){
                
                Stopwatch time = new Stopwatch();                
                StdRandom.shuffle(bolts);
                StdRandom.shuffle(nuts);
            
                NutsandBolts.sort(nuts, bolts);
            
                media += time.elapsedTime();
            }
            StdOut.printf("\nN= %d com tempo de = %g", N, (media/nTestes));
            N *=2;
        }
    }
}