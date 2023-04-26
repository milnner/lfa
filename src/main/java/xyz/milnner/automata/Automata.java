package xyz.milnner.automata;

import java.util.*;

/**
 * Abstrai um automato de estados.
 * @author milnner
 */
public class Automata {

    private String lambda = new String(new char[]{0x03BB});
    private Alphabet alphabet;
    private List<StateAutomata> stateAutomatas;
    private List<StateAutomata> beginStateAutomatas;

    private List<StateAutomata> endStateAutomatas;

    /**
     * @param alphabet o conjunto dos símbolos reconhecidos pelo automato.
     * @param stateAutomatas é o conjunto dos estados que de um automato, neste caso já no formato de lista.
     * @author milnner
     */
    public Automata(Alphabet alphabet, List<StateAutomata> stateAutomatas, List<StateAutomata> beginStateAutomatas, List<StateAutomata> endStateAutomatas) {
        this.alphabet = alphabet;
        this.stateAutomatas = stateAutomatas;
        this.beginStateAutomatas = beginStateAutomatas;
        this.endStateAutomatas = endStateAutomatas;
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
        StringBuilder s = new StringBuilder("Automata{" + this.alphabet.toString() + ",");
        for (StateAutomata a : this.stateAutomatas) s.append(" ").append(a.toString());
        return s + "}";
    }
    public String format() {
        StringBuilder s = new StringBuilder("Automata{\n" + alphabet.toString() + ",");
        for (StateAutomata a : stateAutomatas) s.append("\n").append(a.toString().replace("L", "\nL"));
        return s + "}";
    }

    /**
     * @return retorna uma cópia dos estados
     * @deprecated usada somente para realizar testes
     */
    @Deprecated
    public List<StateAutomata> getStateAutomatas() {
//        List<StateAutomata> stateAutomatas = new ArrayList<>(this.stateAutomatas.size());
//        stateAutomatas.addAll(this.stateAutomatas);
        return this.stateAutomatas;
    }
    public StateAutomata getBegiState() {
        if (beginStateAutomatas.size() == 1) return beginStateAutomatas.get(0);
        return null;
    }
    public boolean isDeterministc() {

        if ((beginStateAutomatas.size() == 1)
                && stateAutomatas != null
                && alphabet != null) {

            for (StateAutomata s : stateAutomatas) {
                if (s.containTransition(lambda) != 0) {
                    return false;
                }
                for (String a : alphabet.getSymbols()) {
                    if (s.containTransition(a) > 1) {
                        return false;
                    }
                }
            }
        }
            return true;
    }


    public boolean isFullyAccessible() { // depois corrigir os pontos onde possam ocorrer NullExeption
        if (getBegiState() == null) return false;
        Map<String, Boolean> mapVisited = new HashMap<>();
        for (StateAutomata a: stateAutomatas) {
            mapVisited.put(a.getName(), false);
        }

        Queue<StateAutomata> q = new LinkedList<>();
        q.add(beginStateAutomatas.get(0));
        mapVisited.replace(beginStateAutomatas.get(0).getName(), true);
        StateAutomata s;
        while (!q.isEmpty()) {
            s = q.poll();
            if (s.getTransitions() == null) return false;
            for (Trasition t: s.getTransitions()) {
                if (!mapVisited.get(
                        t.getStateAutomata(t.getSymbol()).getName()
                ).booleanValue())
                {
                    mapVisited.replace(
                            t.getStateAutomata(t.getSymbol()).getName(),
                            true);
                    q.add(t.getStateAutomata(t.getSymbol()));
                }
            }
        }
        for (StateAutomata s1: stateAutomatas) {
            if (!mapVisited.get(s1.getName()).booleanValue()) {
                return false;
            }
        }


        return true;
    }
    public boolean isMinimal() {
        return true;
    }

    /**
     * Este código possui custo O(n²), pelo custo do terceiro for aninhado ser despresível,
     * ele somente substitui uma condicional muito extensa que fica mais legivel assim,
     * x = 1 e x = 2 são as palavras de entrada para um t,
     * para checagem do estado de término, isto para saber se é final ou não final,
     * de x = 3 a x = 6 as palavras de entrada para um t + 1
     * @return pares de estados equivalentes
     */
    // modificar depois e melhorar o algoritmo para n log n
    public List<List<StateAutomata>> equivalentsPrintTable() {
        final int n = stateAutomatas.size();
        final int o = n-1;
        int index = 0;
        List<List<StateAutomata>> table = new LinkedList<>();
        List<StateAutomata> pair = null;
        for (int i = 1; i < n; i++) {
            System.out.print(stateAutomatas.get(i).getName());
            for (int j = 0; j < i; j++) {
                boolean equivalent = true;
                if (stateAutomatas.get(i).isEnd() == stateAutomatas.get(j).isEnd()) {
                    for (int x = 1; x <= 6; x++) {
                        if (this.readWord(stateAutomatas.get(i), alphabet.getWordFromSetOfPermutations(x)).isEnd() !=
                                this.readWord(stateAutomatas.get(j), alphabet.getWordFromSetOfPermutations(x)).isEnd()) {
                            equivalent = false;
                            System.out.print("\t[X]");
                            break;
                        }
                    }
                } else {
                    System.out.print("\t[X]");
                    equivalent = false;
                }
                if (equivalent) {
                    System.out.print("\t[O]");
                    pair = new ArrayList<>(2);
                    pair.add(stateAutomatas.get(i));
                    pair.add(stateAutomatas.get(j));
                    table.add(pair);

//                    System.out.println(stateAutomatas.get(i).getName() + "  "  + stateAutomatas.get(j).getName());
                }
            }
            System.out.println();

        }
        for (StateAutomata s: stateAutomatas) {
            System.out.print("\t["+s.getName()+"]");
        }
        System.out.println();
        return table;
    }
    public List<List<StateAutomata>> equivalents() {
        final int n = stateAutomatas.size();
        final int o = n-1;
        int index = 0;
        List<List<StateAutomata>> table = new LinkedList<>();
        List<StateAutomata> pair = null;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                boolean equivalent = true;
                if (stateAutomatas.get(i).isEnd() == stateAutomatas.get(j).isEnd()) {
                    for (int x = 1; x <= 6; x++) {
                        if (this.readWord(stateAutomatas.get(i), alphabet.getWordFromSetOfPermutations(x)).isEnd() !=
                                this.readWord(stateAutomatas.get(j), alphabet.getWordFromSetOfPermutations(x)).isEnd()) {
                            equivalent = false;
                            break;
                        }
                    }
                } else {
                    equivalent = false;
                }
                if (equivalent) {
                    pair = new ArrayList<>(2);
                    pair.add(stateAutomatas.get(i));
                    pair.add(stateAutomatas.get(j));
                    table.add(pair);

//                    System.out.println(stateAutomatas.get(i).getName() + "  "  + stateAutomatas.get(j).getName());
                }
            }
        }
        return table;
    }
//    public void minimize() {
//        List<List<StateAutomata>> sts = this.equivalents();
//        int index = Integer.parseInt((stateAutomatas.get(stateAutomatas.size()-1).getName()).substring(1));
//        if (sts != null) {
//            StateAutomata newStateAutomata = null;
//            for (List<StateAutomata> e : sts) {
//                newStateAutomata = new StateAutomata(e.get(0).isBegin(),index++,e.get(0).isEnd());
//
//                for (StateAutomata s : stateAutomatas) {
//                    for (Trasition t : s.getTransitions()){
//                        if (t.getStateAutomata(t.getSymbol()).equals(e.get(0))
//                                || t.getStateAutomata(t.getSymbol()).equals(e.get(1))) {
//                            t.setStateAutomata(newStateAutomata);
//                        }
//                    }
//                    if (s.equals(e.get(0)) || s.equals(e.get(1))) {
//                        int stateAutomataFromPosition = StateAutomata.search(stateAutomatas, e.get(0).getName());
//                        stateAutomatas.set(stateAutomataFromPosition,null);
//                        stateAutomataFromPosition = StateAutomata.search(stateAutomatas, e.get(1).getName());
//                        stateAutomatas.set(stateAutomataFromPosition,newStateAutomata);
//
//                    }
//                }
//            }
//        }
//    }

    public StateAutomata readSymbol(StateAutomata s, String symbol) {
        if (this.isDeterministc()) {
            for (Trasition t: s.getTransitions()) {
                if (t.getSymbol().equals(symbol)) return t.getStateAutomata(symbol);
            }
        }
        return null;
    }
    public StateAutomata readWord(StateAutomata s, String[] world) {

        if (s != null && this.isDeterministc()) {
            for (String symbol: world) {
                s = this.readSymbol(s,symbol);
                if (s == null) return null;
            }
        }
        return s;
    }
}
