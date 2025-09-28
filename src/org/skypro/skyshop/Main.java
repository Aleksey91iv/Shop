package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.infrastructure.search.*;
import org.skypro.skyshop.product.*;
import java.util.Set;

import org.skypro.skyshop.exceptions.BestResultNotFound;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            ProductBasket productBasket = new ProductBasket();

            Product simpleProduct = new SimpleProduct("Сандалеты Размер 42", 100);
            Product simpleProduct1 = new SimpleProduct("Сандалеты Размер 42", 105);
            Product discountedProduct = new DiscountedProduct("Сандалеты Размер 47", 200, 50);
            Product fixPriceProduct = new FixPriceProduct("Шлёпки");
            Product simpleProduct2 = new SimpleProduct("Вилла", 100000);

            productBasket.addProduct(simpleProduct);
            productBasket.addProduct(discountedProduct);
            productBasket.addProduct(fixPriceProduct);
            productBasket.addProduct(simpleProduct1);
            productBasket.addProduct(simpleProduct2);

            Article sandalety = new Article("Сандалеты.", "Удобный товар.");
            Article manShlepkiArticle = new Article("Шлёпки", "Отличный товар товар.");
            Article womanShlepkiArticle = new Article("Шлёпки", "Хороший товар товар.");

            SearchEngine searchEngine = new SearchEngine(6);

            searchEngine.add(simpleProduct);
            searchEngine.add(simpleProduct1);
            searchEngine.add(simpleProduct2);
            searchEngine.add(discountedProduct);
            searchEngine.add(fixPriceProduct);
            searchEngine.add(sandalety);
            searchEngine.add(manShlepkiArticle);
            searchEngine.add(womanShlepkiArticle);

            System.out.println("Test_________________________________________________________________");
            Set<Searchable> searchResult = searchEngine.search("л");
            for (Searchable searchable : searchResult){
                System.out.println(searchable.toString());
            }
            System.out.println("_________________________________________________________________");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}