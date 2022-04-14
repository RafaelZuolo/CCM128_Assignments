/*
 *   Uma lista implementada de forma semelhante com a list do java, mas com operacoes
 * mais eficientes
 *   
 *   As operacoes de add e del levam tempo k*log, pois envolvem um numero constante de acessos
 * a BST rubro-negras
 *   As operacoes de isEmpty e size sao de tempo constante pois sao variaveis de instancia, que
 * mantem o track desses valores
 * 
 *   Para implementar o add permitindo repeticoes de keys, foi necessario fazer uma ST de key 
 * item e val outra ST de key Double(determina a posicao relativa do Item) e val o proprio item da primeira ST
 */

import java.util.Iterator;
import edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;

public class List<Item extends Comparable<Item>> implements Iterable<Item> {
    
    private int N = 0;   // tamanho da lista
    private final double first = 100;
    private RedBlackBST<Item, RedBlackBST<Double, Item>> stItem = new RedBlackBST<Item, RedBlackBST<Double, Item>>();
    private RedBlackBST<Double, Item> stDoub = new RedBlackBST<Double, Item>();
    
    // inicializa a lista
    public List() {
    }
    
    public void addFront(Item item) {
        if (N == 0){
            stItem.put(item, new RedBlackBST<Double, Item>());
            stItem.get(item).put(first, item);
            stDoub.put(first, item);
        }
        else {
            double temp = stItem.get(stItem.min()).min() - 1;
            stItem.get(stItem.min()).put(temp, item);
            stDoub.put(temp, item);
        }
        N++;
    }
    
    public void addBack(Item item) {
        if (N == 0){
            stItem.put(item, new RedBlackBST<Double, Item>());
            stItem.get(item).put(first, item);
            stDoub.put(first, item);
        }
        else {
            double temp = stItem.get(stItem.max()).max() + 1;
            stItem.get(stItem.min()).put(temp, item);
            stDoub.put(temp, item);
        }
        N++;
    }
    
    public Item deleteFront() {
        if (N == 0) throw new NoSuchElementException("Cant delete from empty List");
        
        Item temp = stDoub.get(stDoub.min());
        
        if (stItem.get(temp).size() == 1)       // se temos apenas um Item, podemos deletar a BST q o representa.
            stItem.delete(temp);
        else
            stItem.get(temp).deleteMin();      // se nao, deletamos apenas o min, que eh o primeiro
        
        stDoub.deleteMin();
        N--;
        return temp;
    }

    
    public Item deleteBack() {
        if (N == 0) throw new NoSuchElementException("Cant delete from empty List");
        
        Item temp = stDoub.get(stDoub.max());
        
        if (stItem.get(temp).size() == 1)
            stItem.delete(temp);
        else
            stItem.get(temp).deleteMax();
        
        stDoub.deleteMax();
        N--;
        return temp;
        
    }
    
    public void delete(Item item) {     // deleta a primeira ocorrencia do item
        if (N == 0)          throw new NoSuchElementException("Cant delete from empty List");
        if (!contains(item)) throw new NoSuchElementException("No item found");
        
        double temp = stItem.get(item).min();
        
        if (stItem.get(item).size() == 1)
            stItem.delete(item);
        else
            stItem.get(item).deleteMin();
        
        stDoub.delete(temp);
        N--;
    }
    public void add(int i, Item item) {
        if (i <= 0 || i> N+1)  if (N == 0) throw new IndexOutOfBoundsException("Out of range");
        if (i == 1)
            addFront(item);
        else {
            double temp = (stDoub.select(i-1) + stDoub.select(i)) / 2;
            if (!stItem.contains(item))
                stItem.put(item, new RedBlackBST<Double, Item>());
            stItem.get(item).put(temp, item);
            stDoub.put(temp, item);
        }
        N++;
    }
    
    public Item delete(int i) {
        if (i <= 0 || i> N) throw new IndexOutOfBoundsException("Out of range");
        
        if (N == 0) { throw new NoSuchElementException("Cant delete from empty List");}
        
        double temp = stDoub.select(i-1);
        Item item = stDoub.get(temp);
        
        if (stItem.get(item).size() == 1)
            stItem.delete(item);
        else
            stItem.get(item).delete(temp);
        stDoub.delete(temp);
        N--;
        return item;
    }
    
    public Item get(int i) {
        if (i <= 0 || i> N) throw new IndexOutOfBoundsException("Out of range");        
        if (N == 0)  throw new NoSuchElementException("Cant get from empty List");
        
        return stDoub.get(stDoub.select(i-1));
    }
    public boolean contains(Item item) {
        if (N == 0) throw new NoSuchElementException("Lisa vazia");
        
        return stItem.contains(item);
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator<Item>();
    }
    
    private class ListIterator<Item> implements Iterator<Item> {
        private int i = 0;
        
        public ListIterator() {
        }
        public boolean hasNext() { return i < N;                              }
        public void remove()     { throw new UnsupportedOperationException(); }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            
            Item item = (Item) stDoub.get(stDoub.select(i));
            i++;
            return item;
        }        
    }
    
    
  // unit test  
    public static void main(String[] args) {
        List<String> list = new List<String>();
        
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String item = StdIn.readString();
            list.addBack(item);
        }
        StdOut.println("Size: " + list.size());
        int i = 1;
        for(String item : list) {
            StdOut.println(i + " " + item);
            i++;
        }
    }
}











//