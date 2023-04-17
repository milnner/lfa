package automata;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe tipifica como deve ser um alfabeto qualquer,
 * ela ira armazenar symbolos (Strings) em uma lista.
 * Ela não é modificável, gere outro se preciso
 * @author milnner
 */
public class Alphabet {
    private final List<String> symbols;

    /**
     * @param symbols array com os símbolos
     */
    public Alphabet(String[] symbols) {
        this.symbols = new ArrayList<>(symbols.length);
        insertArrayAsAlphabet(symbols);
    }

    /**
     * @param symbols string com os símbolos separados por vírgula
     */
    public Alphabet(String symbols) {
        String[] s = symbols.split(",");
        this.symbols = new ArrayList<>(s.length);
        insertArrayAsAlphabet(s);
    }

    /**
     * Insere um Array de símbolos no alfabeto
     * @param symbols é  Array com os simbolos
     */
    private void insertArrayAsAlphabet(String[] symbols) {
        this.symbols.clear();
        for (String value : symbols) {
            this.addSymbolIntoSymbols(value);
        }
    }

    /**
     * Verifica se um símbolo está contido no alfabeto,
     * isto com um algoritmo de busca binária.
     * @param symbol é o símbolo para comparação
     * @return retorna true para contido e false para não contido
     */
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

    /**
     * Adiciona um símbolo sempre mantendo a ordem crescente do alfabeto,
     * isto é importante para o algoritmo que verificará se um símbolo está contido no alfabeto.
     * @param symbol um símbolo que irá ser adicionado no alfabeto,
     *               ao final da execução o alfabeto estará ordenado.
     */
    private void addSymbolIntoSymbols(String symbol) {
        String other = null;
        for (int i = 0; i < this.symbols.size(); i++) {
            int c = this.symbols.get(i).compareTo(symbol);
            if (c > 0) {
                other = this.symbols.get(i);
                this.symbols.set(i,symbol);
                symbol = other;
            } if (c == 0)return;// c == 0 ignora, deve ser tratado em um verificador do arquivo
        }
        this.symbols.add(symbol);
    }

    /**
     * @return retorna uma <b></b>
     * @deprecated usada somente para realizar testes
     */
    @Deprecated
    public List<String> copySymbols() {
        List<String> symbols = new ArrayList<>(this.symbols.size());
        symbols.addAll(this.symbols);
        return symbols;
    }

    /**
     *
     * @return um objeto <B>String</B> mostrando o conteúdo do objeto <b>Automata</b>
     */
    @Override
    public String toString() {
        return "Alphabet{" +
                "symbols=" + symbols +
                '}';
    }
}
