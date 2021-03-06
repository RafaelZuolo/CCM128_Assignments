/*
 * Modificamos o codigo fonte do ResizingArrayBag, por ser mais facil
 * embaralhar arrays do que listas ligadas
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;
/**
 *  The <tt>ResizingArrayBag</tt> class represents a bag (or multiset) of 
 *  generic items. It supports insertion and iterating over the 
 *  items in arbitrary order.
 *  <p>
 *  This implementation uses a resizing array.
 *  See {@link LinkedBag} for a version that uses a singly-linked list.
 *  The <em>add</em> operation takes constant amortized time; the
 *  <em>isEmpty</em>, and <em>size</em> operations
 *  take constant time. Iteration takes time proportional to the number of items.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 * 
 * 
 * 
 * 
 *  modifications: Rafael Zuolo Coppini Lima
 * 
 * 
 */
public class RandomBag<Item> implements Iterable<Item> {
    private Item[] a;         // array of items
    private int N;            // number of elements on stack

    /**
     * Initializes an empty bag.
     */
    public RandomBag() {
        a = (Item[]) new Object[2];
        N = 0;
    }

    /**
     * Is this bag empty?
     * @return true if this bag is empty; false otherwise
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the number of items in this bag.
     * @return the number of items in this bag
     */
    public int size() {
        return N;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    /**
     * Adds the item to this bag.
     * @param item the item to add to this bag
     */
    public void add(Item item) {
        if (N == a.length) resize(2*a.length);    // double size of array if necessary
        a[N++] = item;                            // add item
    }


    /**
     * Returns an iterator that iterates over the items in the bag in arbitrary order.
     * @return an iterator that iterates over the items in the bag in arbitrary order
     * 
     * So embaralhamos o vetor quando o iterador for chamado.
     * Lembrar que devemos embaralhar apenas a parte ocupada da bag, de 0 a N-1
     */
    public Iterator<Item> iterator() {
        
        for (int j = 0; j < N; j++) {
            int r = j + StdRandom.uniform(N-j);    
            Item temp = a[j];
            a[j] = a[r];
            a[r] = temp;        
        }
        
        return new ArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {
        
        private int i = 0;
        public boolean hasNext()  { return i < N;                               }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i++];
        }
    }

    /**
     * Unit tests the <tt>ResizingArrayBag</tt> data type.
     */
    public static void main(String[] args) {
        RandomBag<String> bag = new RandomBag<String>();
        bag.add("Hello");
        bag.add("World");
        bag.add("how");
        bag.add("are");
        bag.add("you");
 
        for (String s : bag)
            StdOut.println(s);
    }

}