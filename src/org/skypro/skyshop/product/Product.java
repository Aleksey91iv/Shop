package org.skypro.skyshop.product;

import org.skypro.skyshop.infrastructure.search.*;

public abstract class Product implements Searchable {
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    public String getSearchTerm() {
        return name;
    }

    public String getContentType() {
        return "PRODUCT";
    }
}
