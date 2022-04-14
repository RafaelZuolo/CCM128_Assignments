/*
 *  Simple polygon
 * 
 * execucao: java-algs4 SimplePolygon
 * 
 * o algoritmo de ordenacao foi inspirado no insertionX, que
 * faz menos troca de valores nos vetores
 */

import edu.princeton.cs.algs4.*;

public class SimplePolygon{
    
    public static void exch(double[] x, int a, int b){
        double swap = x[a];
        x[a]=x[b];
        x[b]=swap;
    }
    
    public static void main(String[] args){
        
        int N = StdIn.readInt();
        double Xmin = Double.POSITIVE_INFINITY, Ymin = Double.POSITIVE_INFINITY;// usamos dps Xmin e Ymin como nosso pont de referencia para os angulos em radianos
        double Xmax = Double.NEGATIVE_INFINITY, Ymax = Double.NEGATIVE_INFINITY;
        int referencePoint = 0;
        double[] Xpoints = new double[N];
        double[] Ypoints = new double[N];
        double[] angle   = new double[N];
        
        for(int i=0; i<N; i++){
            Xpoints[i] = StdIn.readDouble();
            Ypoints[i] = StdIn.readDouble();
            if (Xmin > Xpoints[i])
                Xmin = Xpoints[i];
            if (Ymin > Ypoints[i]){
                Ymin = Ypoints[i];
                referencePoint = i;}
            if (Xmax < Xpoints[i])
                Xmax = Xpoints[i];
            if (Ymax < Ypoints[i])
                Ymax = Ypoints[i];
        }        
        StdDraw.setYscale(Ymin-1, Ymax+1);
        StdDraw.setXscale(Xmin-1, Xmax+1);
        Xmin = Xpoints[referencePoint];
        Ymin = Ypoints[referencePoint];
        
        for(int i=0; i<N; i++){
            angle[i] = Math.atan((Ypoints[i]-Ymin)/(Xpoints[i]-Xmin));
        }
        // fazer uma insertion sort, nao esperamos receber poligonos de 20000 vertices, hahahha
        // esse insertion eh otimizado para fazer menos trocas nos vetores, pois toda troca aki eh feita em tres vetores
        //usar o menor valor de angle para iniciar
        for (int i = N-1; i > 0; i--){    
            if (angle[i] < angle[i-1]) {
                exch(angle, i, i-1);
                exch(Xpoints, i, i-1);
                exch(Ypoints, i, i-1);
            }
        }
        
        // insertion sort with half-exchanges
        for (int i = 2; i < N; i++) {
            double v = angle[i];
            double x = Xpoints[i];
            double y = Ypoints[i];
            int j = i;
            while (v < angle[j-1]) {
                angle[j] = angle[j-1];
                Xpoints[j] = Xpoints[j-1];
                Ypoints[j] = Ypoints[j-1];
                j--;
            }
            angle[j] = v;
            Xpoints[j] = x;
            Ypoints[j] = y;
        }
        StdDraw.show(1);
        StdDraw.polygon(Xpoints, Ypoints);
        StdDraw.show(1);
    }
}