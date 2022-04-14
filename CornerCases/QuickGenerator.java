/*
 * Esse eh o gerador de instancias que tambem faz o doubling test com os tres tipos de vetor
 * com cada tipo de algoritmo de ordenacao. ele nao embaralha os vetores
 * 
 * percebemos que no caso do algoritmo de particao de Sedgewick, o sufling apenas aumentou um pouco
 * o tempo de execucao, mas o processo foi linearitmico nos dois casos
 * 
 * para o algoritmo de 3-way Dijkstra foi semelhante, com suffling ou sem, o algoritmo foi linearitmico
 * 
 * Para o de BentleyMclloy foi o mesmo
 * 
 * Agora para o algoritmo de particao de KernighanRitchie, houve uma pequena melhora na execucao com o suffling mas
 * o algotimo foi quadratico nos dois casos
 * 
 * Por fim, o mais interessante de todos, o DualPivot, sem o suffling inicial, com o half01(metade 0, metade 1) e com
 * o geometric(metade 0, metade da metade 1, metade da metade da metade 2, etc) ele foi quatratic e dava stackoverflow
 * Nesses dois a melhora na execucao foi notavel com o suffling, deixando de ser quatratico para ser linearitmico
 * mas o halfRandom(metade zero e metade random int) nao houve muita diferenca, os dois foram linearitmicos
 */

import edu.princeton.cs.algs4.*;
public class QuickGenerator{
    
    public static void halfRandom(Integer[] a){   //metade zero e metade random int
        int N = a.length;
        
        for(int i=0; i<N/2; i++)
            a[i] = 0;
        
        for(int i=N/2; i<N; i++)
            a[i] = StdRandom.uniform(N);
    }
    
    public static void half01(Integer[] a){  //metade 0, metade 1
        int N = a.length;
        
        for(int i=0; i<N; i++)
            a[i] = i%2;
    }
    
    public static void geometric(Integer[] a){  //metade 0, metade da metade 1, metade da metade da metade 2, etc
        int N = a.length;
        int k = 0;
        int half = N/2;
        int inicio = 0;
        
        while(half > 0){
            for(int i=inicio; i<inicio+half && i<N; i++){
                a[i] = k;
            }
            inicio += half;
            if (half%2 == 1 && half != 1)
                half = half/2 + 1;
            else
                half /=2;
            k++;
        }
    }
    
    public static void main(String[] args){
        int N = 1000;
        Integer[] teste = new Integer[N];
        int nTestes = Integer.parseInt(args[0]);
        
        while(N<4000000){
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                half01(teste);
                Stopwatch timer = new Stopwatch();
                QuickSedgewick.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %4f para o half01 sem shuffling do Sedgewick", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        StdOut.print("\n\n\n");
        
        while(N<4000000){
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                geometric(teste);
                Stopwatch timer = new Stopwatch();
                QuickSedgewick.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %4f para o geometric sem shuffling do Sedgewick", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        StdOut.print("\n\n\n");
        
        while(N<4000000){
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                halfRandom(teste);
                Stopwatch timer = new Stopwatch();
                QuickSedgewick.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %4f para o halfRandom sem shuffling do Sedgewick", N, (media/nTestes));
            N *=2;
        }
        N = 1000;      
        StdOut.print("\n\n\n");
        // TESTE do Dijkstra
        
        while(N<4000000){
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                half01(teste);
                Stopwatch timer = new Stopwatch();
                QuickDijkstra.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %2f para o half01 sem shuffling do Dijkstra", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        
        StdOut.print("\n\n\n");
        
        while(N<4000000){
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                geometric(teste);
                Stopwatch timer = new Stopwatch();
                QuickDijkstra.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %2f para o geometric sem shuffling do Dijkstra", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        
         StdOut.print("\n\n\n");
        
        while(N<4000000){
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                halfRandom(teste);
                Stopwatch timer = new Stopwatch();
                QuickDijkstra.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %2f para o halfRandom sem shuffling do Dijkstra", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        
         //TESTE do BentleyMcIlroy
        
        while(N<4000000){
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                half01(teste);
                Stopwatch timer = new Stopwatch();
                QuickBentleyMcIlroy.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %2f para o half01 sem shuffling do BentleyMcIlroy", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        StdOut.print("\n\n\n");
        
        while(N<4000000){
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                geometric(teste);
                Stopwatch timer = new Stopwatch();
                QuickBentleyMcIlroy.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %2f para o geometric sem shuffling do BentleyMcIlroy", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        StdOut.print("\n\n\n");
        
        while(N<4000000){
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                halfRandom(teste);
                Stopwatch timer = new Stopwatch();
                QuickBentleyMcIlroy.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %2f para o halfRandom sem shuffling do BentleyMcIlroy", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        StdOut.print("\n\n\n");
        
        //TESTE do KernighanRitchi
        
        while(N<40000){
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                half01(teste);
                Stopwatch timer = new Stopwatch();
                QuickKernighanRitchi.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %2f para o half01 sem shuffling do KernighanRitchi", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        StdOut.print("\n\n\n");
        
        while(N<40000){
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                geometric(teste);
                Stopwatch timer = new Stopwatch();
                QuickKernighanRitchi.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %2f para o geometric sem shuffling do KernighanRitchi", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        StdOut.print("\n\n\n");
        
        while(N<40000){
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                halfRandom(teste);
                Stopwatch timer = new Stopwatch();
                QuickKernighanRitchi.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %2f para o halfRandom sem shuffling do KernighanRitchi", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        StdOut.print("\n\n\n");
        
        // Teste do DualPivot
        
        while(N<30000){   //     RISCO DE STACK OVERFLOW
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                half01(teste);
                Stopwatch timer = new Stopwatch();
                QuickDualPivot.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %2f para o half01 sem shuffling do DualPivot", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        StdOut.print("\n\n\n");
        
        while(N<30000){           // RISCO DE STACKOVERFLOW
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                geometric(teste);
                Stopwatch timer = new Stopwatch();
                QuickDualPivot.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %2f para o geometric sem shuffling do DualPivot", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        StdOut.print("\n\n\n");
        
        while(N<800000){ 
            double media = 0;
            for(int i=0; i<nTestes; i++){
                teste = new Integer[N];
                halfRandom(teste);
                Stopwatch timer = new Stopwatch();
                QuickDualPivot.sort(teste);
                media += timer.elapsedTime();
            }
            StdOut.printf("\nN= %d -- T= %2f para o halfRandom sem shuffling do DualPivot", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        StdOut.print("\n\n\n");

    }
}
