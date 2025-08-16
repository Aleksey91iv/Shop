package org.skypro.skyshop.infrastructure.search;

public interface Searchable {

    String getSearchTerm();
    String getContentType();

    default String getStringRepresentation() {
        return this.getClass().getSimpleName() + " - " + this.getClass().getTypeName();
    }
}
