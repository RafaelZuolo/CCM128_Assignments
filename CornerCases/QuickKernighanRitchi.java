public class QuickKernighanRitchi{
    
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) { 
        if (hi <= lo) return;
        exch(a, lo, (lo + hi) / 2);  // use middle element as partition
        int last = lo;
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[lo])) exch(a, ++last, i);
        exch(a, lo, last);
        sort(a, lo, last-1);
        sort(a, last+1, hi);
    }
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    
}