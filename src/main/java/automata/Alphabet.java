package automata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/*
 * Esta classe tipifica como deve ser um alfabeto qualquer,
 * ela ira armazenar symbolos (Strings) em uma lista.
 * Ela é composta por dois métodos construtores
 * alfabetos não são modificáveis, gere outro se preciso
 */

public class Alphabet {
    private List<String> symbols;
    public Alphabet(String[] symbols) {
        this.symbols = new ArrayList<String>(symbols.length);
        insertArrayAsAlphabet(symbols);

    }
    public Alphabet(String symbols) {
        String[] s = symbols.split(",");
        this.symbols = new ArrayList<String>(s.length);
        insertArrayAsAlphabet(s);
    }
    private void insertArrayAsAlphabet(String[] symbols) {
        this.symbols.clear();
        for (String value : symbols) {
            try {
                this.addSymbolIntoSymbols(value);
            } catch (NewSymbolContainedInSymbols e) {
                System.err.println(e.getMessage() + " --> This symbol " + value + ", it's already in alphabet.");
            }
        }
    }
    public List<String> getSymbols() {
        return this.symbols;
    }
    public boolean contains(String symbol) {
        // binarySort
        int left = 0;
        int right = this.symbols.size()-1;
        while (left <= right) {
            int middle = (left+right)/2;
            int c = this.symbols.get(middle).compareTo(symbol);
            if (c < 0) {
                left = middle+1;
            } else if (c > 0) {
                right = middle-1;
            } else {return true;}
        }
        return false;
    }
    // Insere em ordem crescente,
    // caso contrario a classe não irá funcionar adequadamente,
    // isto pela necessidade de ordem para o método contains
    private void addSymbolIntoSymbols(String symbol) throws NewSymbolContainedInSymbols {
        String other = null;
        for (int i = 0; i < this.symbols.size(); i++) {
            // (a = this.symbols.get(i)) e (b = symbol) então se (a < b retorna -1) ou se (a > b retorna 1) senão (retorna 0)
            int c = this.symbols.get(i).compareTo(symbol);
            if (c > 0) {
                other = this.symbols.get(i);
                this.symbols.set(i,symbol);
                symbol = other;
            } else if  (c == 0){
                throw new NewSymbolContainedInSymbols();

            }
        }
        this.symbols.add(symbol);
    }
    static private class NewSymbolContainedInSymbols extends Exception {
        @Override
        public String getMessage() {
            return "Attempted insert new symbol into symbols, but it has happened before";
        }
    };
}
