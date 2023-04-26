package xyz;



import xyz.milnner.automata.Automata;
import xyz.milnner.automata.StateAutomata;
import xyz.milnner.automata.io.ReadAutomata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Automata a=null;

        ReadAutomata readerAutomata = null;
        try {
            for (String f : args) {
                System.out.println(f);
                readerAutomata = new ReadAutomata(f);
                readerAutomata.read();
                a = readerAutomata.createAutomata();
                List<List<StateAutomata>> le = a.equivalentsPrintTable();
                for (List<StateAutomata> s: le) {
                    System.out.println(s.get(0).getName() + " equivalente " + s.get(1).getName());
                }
                System.out.println();
            }


        } catch (IOException | NullPointerException e) {
            System.out.println("Erro: " + e);
        }

    }
}