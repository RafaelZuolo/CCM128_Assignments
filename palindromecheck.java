//execucao: java-introcs palindromecheck "palindromo a ser testado"
//
// precisa do Queue.java e Stack.java
import edu.princeton.cs.algs4.*;
//import edu.princeton.cs.algs4.Stack;
//import edu.princeton.cs.algs4.StdOut;

public class palindromecheck{

  public static void main(String[] args){
    
    boolean flag = true;    
    
    Queue<Character> fila = new Queue<Character>();
    Stack<Character> pilha = new Stack<Character>();    
    
    String palin = args[0];    
    palin = palin.replaceAll("[^a-zA-Z]", "").toLowerCase();  //soh deixa as letras para comparar
 
    for(int i=0; i < palin.length(); i++){      //guarda na fila e na pilha
      fila.enqueue(palin.charAt(i));
      pilha.push(palin.charAt(i));
    }
    for(int i=0; i < palin.length(); i++){     // compara a pilha e a fila, pois na pilha a palavra ta invertida
      if(fila.dequeue()!= pilha.pop()){
        flag = false;
        break;
      }
    }
    StdOut.println(flag);
  }
}