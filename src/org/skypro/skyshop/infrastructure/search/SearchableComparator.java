package org.skypro.skyshop.infrastructure.search;

import java.util.Comparator;

public class SearchableComparator implements Comparator<Searchable> {
    @Override
    public int compare(Searchable o1, Searchable o2) {
         int lengthCompareResult = Integer.compare(o2.getName().length(), o1.getName().length());
         return lengthCompareResult == 0 ? o1.getName().compareTo(o2.getName()) : lengthCompareResult;
    }
}
