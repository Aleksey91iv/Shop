package org.skypro.skyshop.product;

import org.skypro.skyshop.infrastructure.search.*;

public final class Article implements Searchable {
    private String title;
    private String content;

    public Article (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getSearchTerm() {
        return title + content;
    }

    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String toString() {
        return title + "\n" + content;
    }
}