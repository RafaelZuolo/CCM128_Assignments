import edu.princeton.cs.algs4.*;

public class PrintLevelTester {

    /**
     *  testa o comando howDeep() e printLevel() da BST com:
     *  H C S A E R X
     *  S E A R C H X
     */
    public static void main(String[] args) {
        
        BST<String, Integer> st1 = new BST<String, Integer>();
        BST<String, Integer> st2 = new BST<String, Integer>();
        
        String test1 = "HCSAERX";                              // output esperado: C S A E R X
        String test2 = "SEARCHX";                              // output esperado: E X A R C H
        for(int i = 0; i < test1.length(); i++){
            st1.put(test1.substring(i, i+1), i);
            st2.put(test2.substring(i, i+1), i);
        }
        
        StdOut.println("\n" + st1.howDeep() + " " + st2.howDeep()); // altura de cada uma das arvores esperada: 3, 4
        st1.printLevel();
        StdOut.println();
        st2.printLevel();
    }
}