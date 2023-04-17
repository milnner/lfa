package automata;

/**
 * Abstrai uma transição dum estado dum automato.
 * @author milnner
 */
public class Trasition {
    private final StateAutomata stateAutomata;
    private final String symbol;

    /**
     * @param symbol tem o símbolo reconhecido na transição
     * @param stateAutomata é uma referência para o próximo estado do automato
     */
    public Trasition(String symbol, StateAutomata stateAutomata) {
        this.symbol = symbol;
        this.stateAutomata = stateAutomata;
    }

    /**
     *
     * @param symbol verifica se o símbolo é reconhecido pela transição
     * @return caso seja reconhecido o símbolo,
     * então retorna-se o estado seguinte,
     * se não, então retorna null
     */
    public StateAutomata getStateAutomata(String symbol) {
        if (this.symbol.equals(symbol))
            return stateAutomata;
        return null;
    }
    public String toString() {
        return "Trasition{" + symbol + " -> "+ stateAutomata.getName() + "}";
    }
    /**
     * @return retorna uma cópia do símbolo
     */
    public String getSymbol() {return this.symbol;}
}