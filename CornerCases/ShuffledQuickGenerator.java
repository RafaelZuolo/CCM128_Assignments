/*
 * Esse eh o gerador de instancias que tambem faz o doubling test com os tres tipos de vetor
 * com cada tipo de algoritmo de ordenacao. esse embaralha os vetores!
 */

import edu.princeton.cs.algs4.*;
public class ShuffledQuickGenerator{
    
    public static void halfRandom(Integer[] a){
        int N = a.length;
        
        for(int i=0; i<N/2; i++)
            a[i] = 0;
        
        for(int i=N/2; i<N; i++)
            a[i] = StdRandom.uniform(N);
        
        StdRandom.shuffle(a);           // aki embaralhamos
    }
    
    public static void half01(Integer[] a){
        int N = a.length;
        
        for(int i=0; i<N; i++)
            a[i] = i%2;
        
        StdRandom.shuffle(a);           // aki embaralhamos
    }
    
    public static void geometric(Integer[] a){
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
        StdRandom.shuffle(a);           // aki embaralhamos
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
            StdOut.printf("\nN= %d -- T= %4f para o half01 COM shuffling do Sedgewick", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %4f para o geometric COM shuffling do Sedgewick", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %4f para o halfRandom COM shuffling do Sedgewick", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %2f para o half01 COM shuffling do Dijkstra", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %2f para o geometric COM shuffling do Dijkstra", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %2f para o halfRandom COM shuffling do Dijkstra", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %2f para o half01 COM shuffling do BentleyMcIlroy", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %2f para o geometric COM shuffling do BentleyMcIlroy", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %2f para o halfRandom COM shuffling do BentleyMcIlroy", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %2f para o half01 COM shuffling do KernighanRitchi", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %2f para o geometric COM shuffling do KernighanRitchi", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %2f para o halfRandom COM shuffling do KernighanRitchi", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %2f para o half01 COM shuffling do DualPivot", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %2f para o geometric COM shuffling do DualPivot", N, (media/nTestes));
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
            StdOut.printf("\nN= %d -- T= %2f para o halfRandom COM shuffling do DualPivot", N, (media/nTestes));
            N *=2;
        }
        N = 1000;
        StdOut.print("\n\n\n");

    }
}