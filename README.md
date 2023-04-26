# xyz.milnner.automata
O pacote xyz.milnner.automata é uma abstração de um autômato, ele foi desenvolvido para a disciplina de Linguagens formais e automatos; O intuito do trabalho é somente implementar um minimizador, sendo o restante excesso. Entretanto o meu é de modelar um automato em um pacote, para um aprofundamento da disciplina.
### Sendo desenvolvido no Linux
_Versão 17 do Java_

```shell
 
java -jar nomeDoExecutavel.jar automato1.dat automato2.dat automatoN.dat 
```

### Observações

Ubuntu e derivados

- Necessário o JDK, não somente JRE, devido a necessidade de compilação.
- Caso não tenha o java 17, instale-a
```shell
    sudo apt installopenjdk-17-jdk
```   
- Caso tenha mais de uma versão do Java, selecione a 17  
Como no comando seguinte
```shell
    sudo apt update-alternatives --config java
  > [sudo] senha para $USER: *******
  > Existem 3 escolhas para a alternativa java (disponibiliza /usr/bin/java).

  > Selecção   Caminho                                         Prioridade Estado
  > ------------------------------------------------------------
  > * 0            /usr/lib/jvm/java-17-openjdk-amd64/bin/java      1711      modo automático
  > 1            /usr/lib/jvm/java-11-openjdk-amd64/bin/java      1111      modo manual
  > 2            /usr/lib/jvm/java-17-openjdk-amd64/bin/java      1711      modo manual
  > 3            /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java   1081      modo manual

  > Pressione <enter> para manter a escolha actual[*], ou digite o número da selecção: 
```   
- Agora basta setar o JAVA_HOME substitua (versão_do_java)
```shell
export JAVA_HOME=/usr/lib/jvm/(versão_do_java)
```
