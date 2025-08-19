package org.skypro.skyshop.infrastructure.search;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class SearchEngine {

    private Searchable[] elements;

    public SearchEngine(int supportedElementsSearhCount) throws Exception {
        if (supportedElementsSearhCount <= 0) {
            throw new Exception("Некорретное значение поддерживаемого поиском количества элементов.");
        }
        elements = new Searchable[supportedElementsSearhCount];
    }

    public void add(Searchable searchable) throws RuntimeException {
        if (searchable == null ) {
            throw new RuntimeException("Превышен лимит поддерживаемых поиском элементов.");
        }

        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                elements[i] = searchable;
            }
        }
    }

    public Searchable[] search(String pattern) {

        return Arrays.stream(elements)
            .filter(item -> item != null && item.getSearchTerm()
            .contains(pattern))
            .limit(5)
            .toArray(Searchable[]::new);
    }
}
