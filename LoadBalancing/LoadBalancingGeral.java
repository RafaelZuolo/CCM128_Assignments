import edu.princeton.cs.algs4.*;
public class LoadBalancingGeral{
    
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        int max=0;
        
        Server[] servers = new Server[N];
        for (int i = 0; i < N; i++)
            servers[i] = new Server();
        
        // generate M random jobs and assign to a random processor
        for (int j = 0; j < M; j++) {
            String user = "user" + j;
            int i = StdRandom.uniform(N);
            servers[i].add(user);
        }
        
        for(int i=0; i<N; i++){
            if(max<servers[i].getLoad()) max = servers[i].getLoad();
        }        
        StdOut.println(max);
        
        // see how even the distribution is by printing out the
        // contents of each server
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