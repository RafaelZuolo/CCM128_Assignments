/*
 *   Vamos usar uma estrategia semelhante a do CubeSum e TaxiCab, utilizando dessa vez duas MinPQ, uma
 * para cada membro da equacao, para evitar de fazer comparacoes desnecessarias como o caso (3, 0, 0, 0), sendo 
 * que sabemos que o caso (2, 0, 0, 0) ja nos mostrou que o lado esquerdo eh maior que o direito, assim mantendo as
 * comparacoes sempre em ordem semelhante, fazendo sempre as comparacoes menos desnecessarias
 *   Estimo que o tempo de execucao desse programa devera ser quadratico, pois temos duas PQ onde passarao N^2 dados
 * que serao comparados entre si. Possivel que seja maior, algo entre N^2 log N.
 * 
 *   Esse SolFinder NAO concatena solucoes iguais
 * 
 * Execucao: java-algs4 SolFinder <int> 
 * onde <int> eh o numero limite para a, b, c, d
 */

import edu.princeton.cs.algs4.*;

public class SolFinderTESTE {
    
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Left lastL;                              // estrutura de dados para os membros das esquacoes
        Right lastR;        
        
        MinPQ<Right> pqRight = new MinPQ<Right>();
        MinPQ<Left>  pqLeft  = new MinPQ<Left>();
        
        for(int i = 0; i <= N; i++) {
            pqRight.insert(new Right(0,i));         // iniciamos as pq
            pqLeft.insert(new Left(0, i));
        }
        
        lastL = pqLeft.delMin();                           // primeiro conjunto de possiveis solucoes
        lastR = pqRight.delMin();

        StdOut.println("As solucoes (a, b, c, d) tal que a + 2*b^2 = 3*c^3 + 4*d^4 sao:");
        
        while(!pqRight.isEmpty() && !pqLeft.isEmpty()) {
            
             if (lastL.sum == lastR.sum){
                StdOut.println(lastR.sum + " = " + lastL + lastR);

                if (lastR.sum == pqRight.min().sum && lastR.i <= N) {         // verificar se Right 
                    pqRight.insert(new Right(lastR.i + 1, lastR.j));
                    lastR = pqRight.delMin();
                }
                else if (lastL.i <= N){
                    pqLeft.insert(new Left(lastL.i + 1, lastL.j));
                    lastL = pqLeft.delMin();
                }
             }             
            if (lastL.sum < lastR.sum) {
                if (lastL.i < N)                                      //atualizando a pq esquerda, pois L < R
                    pqLeft.insert(new Left(lastL.i + 1, lastL.j));
                lastL = pqLeft.delMin();
            }
            if (lastL.sum > lastR.sum){
                if (lastR.i < N)                                      //atualizando a pq direita, pois L > R
                    pqRight.insert(new Right(lastR.i + 1, lastR.j));
                lastR = pqRight.delMin();
            }
        }
    }
}