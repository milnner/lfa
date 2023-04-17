package automata;

import java.util.ArrayList;
import java.util.List;
/**
 * StateAutomata abstrai os estados de um automato.
 * @author milnner
 */
public class StateAutomata implements Comparable {
    private final boolean begin;
    private final boolean end;
    private final int nameIntegerPart;
    private List<Trasition> transitions;

    /**
     * @param begin se verdadeiro, o estado é inicial.
     * @param end se verdadeiro, o estado é falso.
     * @param nameIntegerPart é o nome do estado, ele é individual,
     *                        no get o retorno é a letra q contatenada ao nameIntegerPart.
     */
    public StateAutomata(boolean begin, int nameIntegerPart, boolean end) {
        this.nameIntegerPart = nameIntegerPart;
        this.begin = begin;
        this.end = end;
    }

    /**
     * Verifica se um estado tem o mesmo nome,
     * o que caracteriza igualdade,
     * ou pelo menos uma falta de integridade.
     * @param a automato de comparação.
     * @return true para verdade.
     */
    public boolean equals(StateAutomata a) {
        return a.nameIntegerPart == this.nameIntegerPart;
    }

    /**
     *
     * @return o nome como uma String, concatenando q e a parte inteira.
     */
    public String getName() {
        return "q"+ this.nameIntegerPart;
    }

    /**
     *
     * @return uma string com o formato de como está o interior do objeto
     */
    public String toString() {
        StringBuilder s = new StringBuilder("StateAutomata{name=q"
                + this.nameIntegerPart
                + ", begin="+ (this.begin?"true": "false" )
                + ", end="+ (this.end?"true":"false")
                + ", List<Transition>");
        for (Trasition t : transitions) {
            s.append(", ").append(t.toString());
        }
        return s + "}";
    }

    /**
     *
     * @return nameIntegerPart
     */
    public int getNameIntegerPart() {
        return nameIntegerPart;
    }


    /**
     *
     * @param transitions uma lista de transições para inserir no objeto
     */
    // preciso de um jeito mais inteligente para acessar este método
    public void setTransitions(List<Trasition> transitions) {
        this.transitions = transitions;
    }
    /**
     * @return uma cópia das transições.
     * @deprecated usado soomente para testes.
     */
    @Deprecated
    public List<Trasition> copyTransitions() {
        List<Trasition> trasitions = new ArrayList<>(this.transitions.size());
        trasitions.addAll(this.transitions);
        return transitions;
    }

    /**
     * Sobrescreve o método compareTo.
     * @see Comparable
     * @param o o objeto pelo qual o método é chamado será comparado em relação a nameIntegerPart do objeto passado.
     * @return -1 para menor que, 1 para maior que, e 0, se igual.
     */
    @Override
    public int compareTo(Object o) {

        return Integer.compare(this.nameIntegerPart, ((StateAutomata) o).nameIntegerPart);
    }

    /**
     *
     * @return verdadeiro caso o estado seja inicial
     */

    public boolean isBegin() {
        return this.begin;
    }

    /**
     *
     * @return verdadeiro se o estado é final
     */
    public boolean isEnd() {
        return this.end;
    }

    /**
     *
     * @param stateAutomatas é a lista de estados
     * @param s é o nome do estado
     * @return a posição do estado caso esteja na lista,
     * se não retorna o negativo da posição onde ele estaria menos 1
     */
    public static int search(List<StateAutomata> stateAutomatas, String s) {
        int left = 0;
        int middle = 0;
        int right = stateAutomatas.size()-1;
        while (left <= right) {
            middle = (left+right)/2;
            int c = stateAutomatas.get(middle).getName().compareTo(s);
            if (c < 0) {
                left = middle+1;
            } else if (c > 0) {
                right = middle-1;
            } else {return middle;}
        }
        return -middle-1;
    }
}

