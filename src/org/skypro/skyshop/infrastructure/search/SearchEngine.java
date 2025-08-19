package org.skypro.skyshop.infrastructure.search;

import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class SearchEngine {

    private Searchable[] elements;

    public SearchEngine(int supportedElementsSearhCount) throws RuntimeException {
        if (supportedElementsSearhCount <= 0) {
            throw new RuntimeException("Некорретное значение поддерживаемого поиском количества элементов.");
        }
        elements = new Searchable[supportedElementsSearhCount];
    }

    public void add(Searchable searchable) {
        if (searchable == null ) {
            throw new RuntimeException("Превышен лимит поддерживаемых поиском элементов.");
        }

        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                elements[i] = searchable;
                break;
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

    public Searchable searchByMoreMatchEntry(String pattern) throws BestResultNotFound {
        Searchable[] searchables = Arrays.stream(elements)
                .filter(item -> item != null && item.getSearchTerm()
                        .contains(pattern)).toArray(Searchable[]::new);

        Searchable searchable = Arrays.stream(elements)
                .filter(item -> item != null && item.getSearchTerm()
                        .contains(pattern))
                .max(Comparator.comparing(s -> countSubstring(s.getSearchTerm(), pattern))).orElse(null);

        if(searchable == null) {
            throw new BestResultNotFound("Не найден элемент, содержащий в искомом контенте подстроку \"" + pattern + "\"");
        }

        return searchable;
    }

    public int countSubstring(String original, String substring) {

        int lastIndex = 0;
        int count = 0;
        while(lastIndex != -1) {
            lastIndex = original.indexOf(substring, lastIndex);

            if(lastIndex != -1) {
                count++;
                lastIndex += substring.length();
            }
        }

        return count;
    }
}
