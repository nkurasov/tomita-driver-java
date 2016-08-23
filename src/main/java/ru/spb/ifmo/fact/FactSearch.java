package ru.spb.ifmo.fact;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.Set;

public interface FactSearch {
    
    FactDictionary getDictionary();

    Set<Fact> search(Reader in, FactPattern pattern);

    default Set<Fact> search(String input, FactPattern pattern) {
        if (input == null) {
            return Collections.emptySet();
        }

        return search(new StringReader(input), pattern);
    }
}
