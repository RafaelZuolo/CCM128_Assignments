/*LoadBalancing escolhendo apenas um server aleatorio
* precisa do server.java
* 
* no fim da execucao ele printa o maior numero de servicos em um server
* e depois mostra uma imagem em barplot do uso dos servidores
* 
* execucao: java-algs4 LoadBalancing <número de servidores>
*/

import edu.princeton.cs.algs4.*;
public class LoadBalancing{
    
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int max = 0;
        
        Server[] servers = new Server[N];
        for (int i = 0; i < N; i++)
            servers[i] = new Server();
        
        // generate N random jobs and assign to a random processor
        for (int j = 0; j < N; j++) {
            String user = "user" + j;
            int i = StdRandom.uniform(N);
            servers[i].add(user);
        }
        
        // see how even the distribution is by printing out the
        // contents of each server
        for(int i=0; i<N; i++){
            if(max<servers[i].getLoad()) max = servers[i].getLoad();
        }
        
        StdOut.println(max);
        
        double[] load = new double[N];
        for(int i=0; i<N; i++){
            load[i] = ((double)servers[i].getLoad())/max;
        }
        StdDraw.setCanvasSize(1024, 600);
        StdDraw.show(1);
        StdStats.plotBars(load);
        StdDraw.show(1);
    }
}