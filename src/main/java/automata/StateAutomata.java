package automata;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
   /* StateAutomata abstrai os estados de um automato
    * o nome é um identificador inteiro
    */
public class StateAutomata implements Comparable {
    private boolean begin = false;
    private boolean end = false;
    private int nameIntegerPart;
    private List<Trasition> transitions;
    public StateAutomata(int nameIntegerPart, Trasition[] transitions) {
        this.transitions = new ArrayList<Trasition>();
        for (int i = 0; i < transitions.length; i++) {
            this.transitions.add(transitions[i]);
        }
        this.nameIntegerPart = nameIntegerPart;
    }

    public StateAutomata(int nameIntegerPart) {
        this.nameIntegerPart = nameIntegerPart;
        this.transitions = new ArrayList<Trasition>();
    }

    public StateAutomata(boolean begin, int nameIntegerPart, Trasition[] transitions) {
        this.transitions = new ArrayList<Trasition>(transitions.length);
        for (int i = 0; i < transitions.length; i++) {
            this.transitions.set(i,transitions[i]);
        }
        this.nameIntegerPart = nameIntegerPart;
        this.begin = begin;
    }

    public StateAutomata(int nameIntegerPart, Trasition[] transitions, boolean end) {
        this.transitions = new ArrayList<Trasition>(transitions.length);
        for (int i = 0; i < transitions.length; i++) {
            this.transitions.set(i,transitions[i]);
        }
        this.nameIntegerPart = nameIntegerPart;
        this.end = end;
    }

    public StateAutomata(boolean begin, int nameIntegerPart, Trasition[] transitions, boolean end) {
        this.transitions = new ArrayList<Trasition>(transitions.length);
        for (int i = 0; i < transitions.length; i++) {
            this.transitions.set(i,transitions[i]);
        }
        this.nameIntegerPart = nameIntegerPart;
        this.begin = begin;
        this.end = end;
    }

    public void addTranstiotion(Trasition trasition) {
        this.transitions.add(trasition); // fazer check de exception depois
    }

    public boolean equals(StateAutomata a) {
        return a.nameIntegerPart == this.nameIntegerPart;
    }

    public String getName() {
        return "q"+Integer.toString(nameIntegerPart);
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public void setBegin(boolean begin) {
        this.begin = begin;
    }

    public void setTransitions(Trasition[] transitions) {
        this.transitions = new ArrayList<Trasition>(transitions.length);
        for (int i = 0; i < transitions.length; i++) {
            this.transitions.set(i,transitions[i]);
        };
    }

    public List<Trasition> getTransitions() {
        return transitions;
    }

    @Override
    public int compareTo(Object o) {

        if (this.nameIntegerPart < ((StateAutomata)o).nameIntegerPart) {
            return -1;
        }
        if (this.nameIntegerPart > ((StateAutomata)o).nameIntegerPart) {
            return 1;
        }
        return 0;
    }

    static public class StateAutomataComparator implements Comparator {
        public int compare(Object st1, Object st2) {
            return ((StateAutomata)st1).compareTo((StateAutomata)st2);
        }
    }

    public boolean isBegin() {
        return this.begin;
    }

    public boolean isEnd() {
        return this.end;
    }
}

