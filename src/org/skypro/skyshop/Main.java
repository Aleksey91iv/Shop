package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.infrastructure.search.*;
import org.skypro.skyshop.product.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            ProductBasket productBasket = new ProductBasket();

            Product simpleProduct = new SimpleProduct("Сандалеты Размер 42", 100);
            Product discountedProduct = new DiscountedProduct("Сандалеты Размер 47", 200, 50);
            Product fixPriceProduct = new FixPriceProduct("Шлёпки");

            productBasket.addProduct(simpleProduct);
            productBasket.addProduct(discountedProduct);
            productBasket.addProduct(fixPriceProduct);

            productBasket.printProductsInfo();

            Article sandalety = new Article("Сандалеты.","Удобный товар.");
            Article manShlepkiArticle = new Article(
                    "Шлёпки.",
                    "Отличный товар.");
            Article womanShlepkiArticle = new Article("Шлёпки.", "Хороший товар.");

            SearchEngine searchEngine = new SearchEngine(6);

            searchEngine.add(simpleProduct);
            searchEngine.add(discountedProduct);
            searchEngine.add(fixPriceProduct);
            searchEngine.add(sandalety);
            searchEngine.add(manShlepkiArticle);
            searchEngine.add(womanShlepkiArticle);
            System.out.println("-------------------------------------------------------");
            Searchable[] searchables = searchEngine.search("x");
            Arrays.stream(searchables).filter(Objects::nonNull).forEach(item -> System.out.println(item.getSearchTerm()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}