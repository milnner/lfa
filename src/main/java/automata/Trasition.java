package automata;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.lang.StringBuilder;
    /*
     * Uma transição é tipificada por um estado seguinte e
     * um simbolo,
     */
public class Trasition {
    private final StateAutomata stateAutomata;
    private final String symbol;
    public Trasition(String symbol, StateAutomata stateAutomata) {
        this.symbol = symbol;
        this.stateAutomata = stateAutomata;
    }

    public StateAutomata getStateAutomata(String symbol) {
        if (this.symbol.equals(symbol))
            return stateAutomata;
        return null;
    }
    public String getSymbol() {
        return this.symbol;
    }
}