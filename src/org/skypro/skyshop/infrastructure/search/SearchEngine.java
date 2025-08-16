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

    public boolean add(Searchable searchable) {
        if (searchable == null ) {
            return false;
        }

        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                elements[i] = searchable;
                return true;
            }
        }

        return false;
    }

    public Searchable[] search(String pattern) {
        Searchable[] result = Arrays.stream(elements)
            .filter(item -> item != null && item.getSearchTerm().contains(pattern)).toArray(Searchable[]::new);

        return Arrays.stream(elements)
            .filter(item -> item != null && item.getSearchTerm()
            .contains(pattern))
            .limit(5)
            .toArray(Searchable[]::new);
    }
}
