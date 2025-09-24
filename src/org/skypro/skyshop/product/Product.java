package org.skypro.skyshop.product;

import org.skypro.skyshop.infrastructure.search.*;

import java.util.Objects;

public abstract class Product implements Searchable {
    private String name;

    public Product(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Некорректное именование продукта.");
        }
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

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj instanceof Product product ? name.equals(product.name) : false;
    }
}
