/*
 *  O menor produto escalar ocorrera na permutacao onde o maior valor de v1 for multiplicado
 * pelo menor valor de v2, o segundo maior de v1 pelo segundo menor de v2, e assim por diante.
 * assim, para encontrar uma permutacao que minimize o produto escalar, basta ordenarmos em ordem
 * crescente um vetor e em ordem decrescente o outro.
 * 
 * Isso é provado pelo teoremada desigualdade do rearranjo, que enuncia que se tivermos dois conjuntos
 * finitos com mesmo numero de elementos A = {a1, a2, ..., an} e B = {b1, b2, ..., bn}, com a1<a2<...<an e
 * b1<b2<...bn, entao dada uma permutacao k dos elementos de A, a soma dos produtos ak1*b1 + ak2*b2 + ... akn*bn tem a 
 * propriedade:
 * 
 * an*b1 + a(n-1)*b2 + ... + a1*bn <= ak1*b1 + ak2*b2 + ... akn*bn <= a1*b1 + a2*b2 + ... + an*bn
 * 
 * execucao:
 *  java-algs4 MinimumDotProduct
 */

import edu.princeton.cs.algs4.*;

public class MinimumDotProduct{
    
    public static void main(String[] args){
        int N = StdIn.readInt();
        double dProd = 0;
        
        Double[] v1 = new Double[N];
        Double[] v2 = new Double[N];
        
        for(int i=0; i<N; i++)
            v1[i] = StdIn.readDouble();
        for(int i=0; i<N; i++)
            v2[i] = -(StdIn.readDouble()); // usamos -v2 para ordenarmos v2 em ordem decrescente
        
        Merge.sort(v1);
        Merge.sort(v2);
        
        StdOut.print("As permutacoes minimas sao:\nv1 = "); // acertamos v2, e na mesma iteracao fazemos o prod escalar
        for(int i=0; i<N; i++){
            v2[i] = -v2[i];
            StdOut.print(v1[i] + " ");
            dProd += v1[i]*v2[i];
        }
        StdOut.print("\nv2 = ");
        for(int i=0; i<N; i++)
            StdOut.print(v2[i] + " ");
        
        StdOut.print("\nO menor produto escalar eh: " + dProd);
    }
}