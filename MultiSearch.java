import edu.princeton.cs.algs4.*;

public class MultiSearch {
    
    private static boolean hasAll(ST<String, Integer> words) {
        for(String s: words){
            if (words.get(s) == 0) return false;            
        }
        return true;
    }
    
    public static void main(String[] args) {
        
        int size = Integer.MAX_VALUE;  // tamanho da menor frase encontrada com as seeds
        boolean achouTodas = false;  // flag para a execucao de um bloco que deve ser executado apenas apos outro
        
        String s = "NAO ACHOU NADA";  // String que armazena a menor frase encontrada
        String word = "";             // String auxiliar para a leitura da entrada padrao
        
        ST<String, Integer> seed = new ST<String, Integer>();  //iniciamos a ST que sera para comparacao das seeds rapidamente
        for (int i = 0; i < args.length; i++) {
            seed.put(args[i], 0);
        }
        
        // essa Queue serve para armazenarmos a frase atual, com todas as palavras lidas da entrada padrao
        Queue<String> frase = new Queue<String>();  
        
        while (!StdIn.isEmpty()) {
            
            word = StdIn.readString();
            word = word.replaceAll("[^A-Za-z]", ""); 
            
            if (seed.contains(word))        
                seed.put(word, seed.get(word) + 1);
            
            frase.enqueue(word);  // colocar na queue todas as palavras ate termos todas as seeds
            
            
            if (hasAll(seed)) {
                achouTodas = true;
                if (size > frase.size()) { // atualizar a menor frase que contem todas as seeds
                    size = frase.size();
                    s = "";
                    for (String w : frase){
                        s = s + " " + w;
                    }
                }
                
            }
            while (hasAll(seed)) {  // remover palavras ate uma palavra da seed
                word = frase.dequeue();
                if (seed.contains(word)) seed.put( word, seed.get(word) - 1);
            }
            if (achouTodas) {               // so deve ser executado apos a execucao do if acima
                if (size > frase.size()) {  // atualizar a menor frase que contem todas as seeds
                    size = frase.size();
                    s = word;
                    for (String w : frase){
                        s = s + " " + w;
                    }
                }
                achouTodas = false;  // atualiza a flag
            }
        }
        StdOut.println("\n" + s);
    }
}