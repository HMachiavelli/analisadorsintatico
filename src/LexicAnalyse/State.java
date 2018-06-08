package LexicAnalyse;

import java.util.HashMap;
import java.util.Map;

//Estado do Automato Finito Deterministico
public class State {

  private boolean accept;                           // Estado de aceitacao
  private Map<Character, Integer> transitions;      // Transicao de estados

  // Construtor
  public State(boolean accept)
  {
    this.accept = accept;
    transitions = new HashMap<>();
  }

  // Retornar se o estado é de aceitacao
  public boolean isAccept()
  {
    return accept;
  }

  // Adicionar uma transicao de estado
  public void addTransition(Character symbol, Integer state)
  {
    transitions.put(symbol, state);
  }

  // Retornar o novo estado
  public Integer getTransition(Character symbol)
  {
    return transitions.get(symbol);
  }
}
