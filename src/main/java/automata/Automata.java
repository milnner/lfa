package automata;

import java.util.ArrayList;
import java.util.List;
/* Automata é uma classe que contém um alfabeto
 * e uma lista de estados
 */
public class Automata {
    private Alphabet alphabet;
    // talvaz seja interessante colocar uma lista com os estados finais e outra com o inicial
    private List<StateAutomata> stateAutomatas;
    public Automata(Alphabet alphabet, List<StateAutomata> stateAutomatas) {
        this.alphabet = alphabet;
        this.stateAutomatas = stateAutomatas;
    }

    public Automata(Alphabet alphabet, StateAutomata[] stateAutomatas) {
        this.alphabet = alphabet;
        this.stateAutomatas = new ArrayList<StateAutomata>(stateAutomatas.length);
        for (int i = 0; i < stateAutomatas.length; i++) {
            this.stateAutomatas.add(stateAutomatas[i]);
        }
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public List<StateAutomata> getStateAutomatas() {
        return this.stateAutomatas;
    }



}
