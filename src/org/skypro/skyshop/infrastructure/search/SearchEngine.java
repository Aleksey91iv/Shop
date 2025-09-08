package org.skypro.skyshop.infrastructure.search;

import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

public class SearchEngine {

    private List<Searchable> elements;

    public SearchEngine(int supportedElementsSearhCount) throws RuntimeException {
        if (supportedElementsSearhCount <= 0) {
            throw new RuntimeException("Некорретное значение поддерживаемого поиском количества элементов.");
        }
        elements = new LinkedList<>();
    }

    public void add(Searchable searchable) {
        if (searchable == null ) {
            throw new RuntimeException("Превышен лимит поддерживаемых поиском элементов.");
        }

        elements.add(searchable);
    }

    public Map<String, Searchable> search(String pattern) {
        Map<String, Searchable>  searchProductPositions = new HashMap<>();
        elements.stream()
            .filter(item -> item != null && item.getSearchTerm()
            .contains(pattern))
            .forEach(item -> searchProductPositions.put(item.getStringRepresentation(), item));

        return  searchProductPositions;
    }

    public Searchable searchByMoreMatchEntry(String pattern) throws BestResultNotFound {
        Searchable[] searchables = elements.stream()
                .filter(item -> item != null && item.getSearchTerm()
                        .contains(pattern)).toArray(Searchable[]::new);

        Searchable searchable = elements.stream()
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
