package org.skypro.skyshop.infrastructure.search;

public interface Searchable {

    String getSearchTerm();
    String getContentType();
    String getName();

    default String getStringRepresentation() {
        return this.getClass().getSimpleName() + " - " + this.getClass().getTypeName();
    }
}
