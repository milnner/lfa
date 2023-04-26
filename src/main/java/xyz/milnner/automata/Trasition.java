package xyz.milnner.automata;

/**
 * Abstrai uma transição dum estado dum automato.
 * @author milnner
 */
// corrigir depois e voltar StateAutomata para final, tem q melhorar o algoritmo para unir os estados de equivalência
public class Trasition {
    private StateAutomata stateAutomata;
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
    public boolean equals (Object o)
    {
        return (this.symbol.equals(((Trasition)o).symbol) &&
                this.stateAutomata.equals(((Trasition)o).stateAutomata));


    }
    public void setStateAutomata(StateAutomata s) {
        this.stateAutomata = s;
    }

}