package org.skypro.skyshop.infrastructure.search;

import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.*;

public class SearchEngine {

    private Set<Searchable> elements;

    public SearchEngine(int supportedElementsSearhCount) throws RuntimeException {
        if (supportedElementsSearhCount <= 0) {
            throw new RuntimeException("Некорретное значение поддерживаемого поиском количества элементов.");
        }
        elements = new HashSet<Searchable>();
    }

    public void add(Searchable searchable) {
        if (searchable == null ) {
            throw new RuntimeException("Превышен лимит поддерживаемых поиском элементов.");
        }

        elements.add(searchable);
    }

    public Set<Searchable> search(String pattern) {
        Set<Searchable>  searchProductPositions = new TreeSet<Searchable>(new SearchableComparator());
        elements.stream()
            .filter(item -> item != null && item.getSearchTerm()
            .contains(pattern))
            .forEach(item -> searchProductPositions.add(item));

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
