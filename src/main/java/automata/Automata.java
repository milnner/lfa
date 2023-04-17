package automata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstrai um automato de estados.
 * @author milnner
 */
public class Automata {
    private Alphabet alphabet;
    private List<StateAutomata> stateAutomatas;
    private StateAutomata initial;
    /**
     * @param alphabet o conjunto dos símbolos reconhecidos pelo automato.
     * @param stateAutomatas é o conjunto dos estados que de um automato, neste caso já no formato de lista.
     * @author milnner
     */
    public Automata(Alphabet alphabet, List<StateAutomata> stateAutomatas) {
        this.alphabet = alphabet;
        this.stateAutomatas = stateAutomatas;
    }
    /**
     * @param alphabet o conjunto dos símbolos reconhecidos pelo automato.
     * @param stateAutomatas é o conjunto dos estados que de um automato, ainda nescessário colocar no formato List<StateAutomata>.
     * @author milnner
     */
    public Automata(Alphabet alphabet, StateAutomata[] stateAutomatas) {
        this.alphabet = alphabet;
        this.stateAutomatas = new ArrayList<>(stateAutomatas.length);
        this.stateAutomatas.addAll(Arrays.asList(stateAutomatas));
    }

    // Quais abstrações são necessárias para que não seja preciso retornar o alfabeto ou uma cópia?
    // Quais abstrações são necessárias para que não seja preciso retornar a lista dos automatos ou uma cópia?

    /**
     * @return retorna a o alfabeto
     */
    public Alphabet getAlphabet() {
        return this.alphabet;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Automata{" + alphabet.toString() + ",");
        for (StateAutomata a : stateAutomatas) s.append(" ").append(a.toString());
        return s + "}";
    }

    /**
     * @return retorna uma cópia dos estados
     * @deprecated usada somente para realizar testes
     */
    @Deprecated
    public List<StateAutomata> getStateAutomatas() {
        List<StateAutomata> stateAutomatas = new ArrayList<>(this.stateAutomatas.size());
        stateAutomatas.addAll(this.stateAutomatas);
        return stateAutomatas;
    }

}
