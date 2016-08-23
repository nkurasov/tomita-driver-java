package ru.spb.ifmo.tomita;

import static ru.spb.ifmo.fact.GroupWord.adj;
import static ru.spb.ifmo.fact.Tokens.seq;
import static ru.spb.ifmo.fact.Tokens.synonyms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;

import ru.spb.ifmo.fact.Fact;
import ru.spb.ifmo.fact.FactPattern;
import ru.spb.ifmo.fact.FactSearch;
import ru.spb.ifmo.fact.Token;

/**
 * @author nikit
 *
 */
public class Program {

    private Program() {
        // do nothing
    }

    public static void main(String[] args) {
        Token root = seq(adj(), synonyms("русский", "француз", "американец"));
        FactPattern pattern = new FactPattern(root);

        FactSearch factSearch = new TomitaSearch();
        getInputReader().ifPresent(
                in -> factSearch.search(in, pattern).stream()
                        .map(Fact::getText).forEach(System.out::println));
    }

    private static Optional<FileReader> getInputReader() {
        try {
            return Optional.of(new FileReader(
                    "/home/nikit/tmp/tomita-work/input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
