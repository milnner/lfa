package xyz.milnner.automata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;

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
        Arrays.sort(s);
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

    public List<String> getSymbols() {
        return symbols;
    }

    /**
     * Este método realiza a permutação dos símbolos do alfabeto
     * retornando uma palavra ao receber um índice.
     * e. g. para um alfabeto ordenado sigma = {a, b},
     * getWordFromSetOfPermutations(1) = a, getWordFromSetOfPermutations(2) = b,
     * getWordFromSetOfPermutations(3) = aa, getWordFromSetOfPermutations(4) = bb,
     * getWordFromSetOfPermutations(5) = ab, getWordFromSetOfPermutations(6) = ba.
     * Este comportamento se repete. Uma particularidade deste método é o cáculo do
     * tamanho do array para armazenar a palavra. Para evitar desperdicio de processamento
     * na realocação de memória, aproveita-se o comportamento de crescimento  da permutação
     * das palavras (progressão geométrica). Através do indice da palavra é possível calcular
     * a quantidade necessária de memória, para isso, a formúla conhecida de somatória de uma progressão
     * geométrica, isto é, <b>Sn = ( a1 * ( (q^n) - 1) ) / ( q - 1 )</b>, onde <b>a1</b> é o primeiro termo desta soma,
     * <b>q</b> é a razão, <b>n</b> o enésimo exponte, para um <b>q = a1</b>, e <b>Sn</b> a somatória de todos os elementos
     * da progressão geométrica orientada pelos termos anteriores, se, e somente se, <b>( ( Sn * ( q - 1 ) / a1) + 1 ) = q^n</b>.
     * Está proposição é útil, visto que para um alfabeto qualquer sigma2, existe a quantidade de permutações para
     * <b>n</b> símbolos deste alfabeto, dada por <b>|sigma2|^n</b>. Sendo verdade <b>( ( Sn * ( q - 1 ) / a1) + 1 ) = q^n</b>
     * e <b>( Sn * ( q - 1 ) / a1) + 1 ) = u</b>, tem-se <b>q^n = u</b>, uma defição dos logarítmos, para <b>loga(b) = x < = > a^x = b</b>
     * então é verdade que <b>logq() = n</b>. Com isto, dado um  <b>Sn</b>, <b> n-1 < logq(( ( Sn * ( q - 1 ) / a1) + 1 )) <= n </b>,
     * portanto, o tamanho <b>n</b> de uma palavra que esteja no índice <b>Sn</b>, do conjunto de permutações infinitas, dos símbolos de um
     * alfabeto 𝛴 é dado por ⎡log|𝛴|((Sn * ( |𝛴| - 1 ) / |𝛴|) + 1)⎤ = n. obs. |𝛴| é a base do logarítmo.
     * @param i
     */
    public String[] getWordFromSetOfPermutations(int i) {
        final int q = this.symbols.size();
        final int a1 = q;
        final int u = (((q-1)*i)/a1) + 1;
        final int n = (int) Math.ceil(Math.log10(u) / Math.log10(q));
        String[] word = new String[n];
        int x = i - 1;
        for (int z = 0; z < n; z++) {
            int j = x % q;
            word[n - z - 1] = this.symbols.get(j);
            x = (x-j)/q;
        }
        return word;
    }
}
