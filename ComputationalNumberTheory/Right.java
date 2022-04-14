public class Right implements Comparable<Right> {  // estrategia semelhante ao taxicab problem, Right eh o lado direito da equacao
    public final int i;
    public final int j;
    public final double sum;   // 3*i^3 + 4*j^4, cached to avoid recomputation
    
    public Right(int i, int j) {
        this.i = i;
        this.j = j;
        this.sum  = (double) 3*i*i*i + (double) 4*j*j*j*j;
    }
    // compare by 3*i^3 + 4*j^4, breaking ties by i
    public int compareTo(Right that) {
        if      (this.sum < that.sum) return -1;
        else if (this.sum > that.sum) return +1;
        else if (this.i < that.i)     return -1;
        else if (this.i > that.i)     return +1;
        else                          return  0;
    }
    
    public String toString() {
        return i + ", " + j + ")";
    }
}