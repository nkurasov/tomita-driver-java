package ru.spb.ifmo.tomita.dictionary.grammar;

/**
 * Оператор, определяемый над символами грамматики
 * 
 * @author nikit
 *
 */
public interface Operator extends RuleElement {

    /**
     * @return название оператора
     */
    String getSymbolicName();
}
