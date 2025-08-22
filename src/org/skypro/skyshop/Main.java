package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exceptions.BestResultNotFound;
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
                    "Отличный товар товар.");
            Article womanShlepkiArticle = new Article("Шлёпки.", "Хороший товар товар.");

            SearchEngine searchEngine = new SearchEngine(6);

            searchEngine.add(simpleProduct);
            searchEngine.add(discountedProduct);
            searchEngine.add(fixPriceProduct);
            searchEngine.add(sandalety);
            searchEngine.add(manShlepkiArticle);
            searchEngine.add(womanShlepkiArticle);
            System.out.println("-------------------------------------------------------");
            Searchable[] searchables = searchEngine.search("x");

            // Exceptions test
            try {
                Product incorrectSimpleProductName = new SimpleProduct(null , 3);
            }
            catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            try {
                Product incorrectSimpleProductPrice = new SimpleProduct("tapok", 0);
            }
            catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            try {
                Product incorrectProductDiscount = new DiscountedProduct("tapok", 4, 101);
            }
            catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            try {
                Searchable searchable = searchEngine.searchByMoreMatchEntry("товар товар");
                System.out.println("Найден 'элемент':\n" + searchable.getSearchTerm());

                searchable = searchEngine.searchByMoreMatchEntry("JJJ");
            } catch (BestResultNotFound ex) {
                System.out.println(ex.getMessage());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}