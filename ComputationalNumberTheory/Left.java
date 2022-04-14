public class Left implements Comparable<Left> {  // estrategia semelhante ao taxicab problem, Left eh o lado esquerdo da equacao
    public final int i;
    public final int j;
    public final double sum;   // i + 2*j^2, cached to avoid recomputation
    
    public Left(int i, int j) {
        this.i = i;
        this.j = j;
        this.sum  =  (double) i + (double) 2*j*j;
    }
    // compare by i + 2*j^2, breaking ties by i
    public int compareTo(Left that) {
        if      (this.sum < that.sum) return -1;
        else if (this.sum > that.sum) return +1;
        else if (this.i < that.i)     return -1;
        else if (this.i > that.i)     return +1;
        else                          return  0;
    }
    
    public String toString() {
        return "(" + i + ", " + j + ", ";
    }
}