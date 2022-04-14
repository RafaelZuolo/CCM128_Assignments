/*  vamos considerar a nossa matriz numerada da forma direta, como
 *  um vetor enorme: ex n=3 
 *                            0 1 2
 *                            3 4 5
 *                            6 7 8
 * 
 * e representamos a ligacao como o par p q de casas adjacentes
 * então basta gerarmos todas as n*(n-1) ligaçoes horizontais e n*(n-1) verticais
 * 
 */ 

import edu.princeton.cs.algs4.*;

public class RandomGridGenerator {
    
    private static Connection temp;  
    
    public static Connection[] Generate(int n){        
        Connection[] links = new Connection[2*(n-1)*n];
        RandomBag<Connection> bag = new RandomBag<Connection>();
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n-1; j++){
                if (Math.random()<0.5)                        // orienta a ligacao aleatoriamente
                    temp = new Connection(j+n*i,j+n*i+1);   // gera as ligacoes horizontais
                else
                    temp = new Connection(j+n*i+1,j+n*i);
                bag.add(temp);                         
            }
        }
        for(int i=0; i<n-1; i++){
            for(int j=0; j<n; j++){
                if (Math.random()<0.5)                        // orienta a ligacao aleatoriamente
                    temp = new Connection(j+i*n,j+n*i+n);  // gera as ligacoes verticais
                else
                    temp = new Connection(j+i*n+n,j+n*i);  
                bag.add(temp);                         
            }
        }
        {
            int i = 0;                           // limitar o escopo da variavel i, why not
            for(Connection a : bag){
                links[i] = a;
                i++;
            }
        }
        return links;
    }
    
    private static class Connection{           
        int p;
        int q;  
        
        public Connection(int p, int q)
        {   this.p = p;   this.q = q;   }
        
        public String toString(){
            return p + " " + q;
        }
    }
    
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        Connection[] link = new Connection[2*N*(N-1)];
        link = Generate(N);
        
        for(int i = 0; i<2*N*(N-1); i++)
            StdOut.println(link[i]);                    // printar todas as connections
    }
}