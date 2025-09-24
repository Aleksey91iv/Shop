package org.skypro.skyshop.product;

import org.skypro.skyshop.infrastructure.search.*;

import java.util.Objects;

public final class Article implements Searchable {
    private String title;
    private String content;

    public Article (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getName() {
        return title;
    }

    public String getSearchTerm() {
        return title + content;
    }

    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String toString() {
        return title + ": " + content;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj instanceof Article article ? title.equals(article.title) : false;
    }
}