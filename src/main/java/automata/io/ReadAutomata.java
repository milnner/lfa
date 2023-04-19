package automata.io;

import automata.Alphabet;
import automata.Automata;
import automata.StateAutomata;
import automata.Trasition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

// extends InputStream ?

/**
 * Tenha muito cuidado com essa classe ainda existem muitos bugs para serem corrigidos
 * @author milnner
 */
public class ReadAutomata {
    private String[] stateAutomatasNames;
    private String[] endStateAutomatasNames;
    private String beginStateAutomata;

    private Alphabet alphabet;
    private Map<String, List<String[]>> mapTrasitions;
    private RandomAccessFile reader;
    private boolean readed;


    /**
     *
     * @param f é o caminho para o arquivo que contém o automato.
     * @throws FileNotFoundException Caso não exista o arquivo.
     */
    public ReadAutomata(String f) throws FileNotFoundException {
        this.reader = new RandomAccessFile(f, "r");
    }

    /**
     * Lê a linha do alfabeto e grava um objeto Alphabet na memória, isto
     * para uso na geração de um objeto Automata no futuro.
     * @throws IOException Caso haja problema na leitura do arquivo.
     */
    private void readAlphabet() throws IOException{
        this.alphabet = new Alphabet(this.reader.readLine());
    }

    /**
     * Lê os automatos e grava um array na memória,
     * isto para futura geração de um objeto StateAutomata.
     * @throws IOException Caso haja problema na leitura do arquivo.
     */
    private void readAutomatas() throws IOException {
        this.stateAutomatasNames = this.reader.readLine().split(",");
        Arrays.sort(this.stateAutomatasNames);
    }

    /**
     * Mapeia as transições de para cada stado do automato no arquivo.
     * No fim o <b>mapTransitions</b> estará carregado.
     * @throws IOException Caso haja problema na leitura do arquivo.
     */

    private void readTrasitions() throws IOException {
        long previousLinePosition = this.reader.getChannel().position();
        String[] transitionsArr;
        List<String[]> transitionsStringList;
        this.mapTrasitions = new HashMap<>();

        for (String line = this.reader.readLine();
             line.charAt(0) != '>';
             line = this.reader.readLine())
        {
            previousLinePosition = this.reader.getChannel().position();
            transitionsArr = line.split(",");
            transitionsStringList = this.mapTrasitions.get(transitionsArr[0]);
            if (transitionsStringList == null) {
                this.mapTrasitions.put(transitionsArr[0], new LinkedList<>());
                transitionsStringList = this.mapTrasitions.get(transitionsArr[0]);
            }
            transitionsStringList.add(transitionsArr);
        }
        this.reader.seek(previousLinePosition);
    }

    /**
     * Faz a leitura da parte do arquivo que contém um estado inicial
     * @throws IOException
     */
    private void readBeginState() throws IOException {
        this.beginStateAutomata = this.reader.readLine()
                .substring(1);
    }

    /**
     * Faz a leitura da parte do arquivo que contém os estados finais do automato
     * @throws IOException
     */
    private void readEndStates() throws IOException {
        this.endStateAutomatasNames = this.reader.readLine()
                .substring(1).split(",");
        Arrays.sort(this.endStateAutomatasNames);
    }

    /**
     * Cria um automato após a leitura de um aquivo
     * @return um objeto <b>Automata</b>
     */
    public Automata createAutomata() {
        if (!readed) return null; // a leitura não foi realizada, ou teve uma exceção
        List<StateAutomata> stateAutomatas = new ArrayList<>(this.stateAutomatasNames.length);
        for (String s : this.stateAutomatasNames) {
            stateAutomatas.add(
                    new StateAutomata(
                            s.equals(this.beginStateAutomata),
                            Integer.parseInt(s.substring(1)),
                            0 <= (Arrays.binarySearch(this.endStateAutomatasNames, s))
                    )
            );
        }

        SortedSet<String> keys = new TreeSet<>( this.mapTrasitions.keySet());
        List<Trasition> transitions;
        List<String[]> transitionsStringList;

        for (String s : keys) {
            transitionsStringList = this.mapTrasitions.get(s);
            transitions = new ArrayList<>(transitionsStringList.size());
            int stateAutomataFromPosition = StateAutomata.search(stateAutomatas, s);
            if (0 <= stateAutomataFromPosition) { // existe o estado no arquivo
                for (String[] t : transitionsStringList) {
                    int stateAutomataToPosition = StateAutomata.search(stateAutomatas, t[2]);
                    if (0 <= stateAutomataToPosition
                            && this.alphabet.contains(t[1])) {
                        transitions.add(new Trasition(t[1],
                                stateAutomatas.get(stateAutomataToPosition)));
                    }
                }
            }
            stateAutomatas.get(stateAutomataFromPosition).setTransitions(transitions);
        }
        return new Automata(this.alphabet, stateAutomatas);
    }

    /**
     * Executa a leitura de todas as partes do arquivo
     * @throws IOException
     */
    public void read() throws IOException {
        // Precisa fazer checagem para
        // os casos onde existem estados nas transições
        // que não existem nos estados declarados
        // (Ou com ignorando, ou adicionando esses novos estados)

        this.readAutomatas();
        this.readAlphabet();
        this.readTrasitions();
        this.readBeginState();
        this.readEndStates();
        readed = true;
    }
}
