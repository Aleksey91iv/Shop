package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.infrastructure.search.*;
import org.skypro.skyshop.product.*;
import java.util.List;
import java.util.Map;
import org.skypro.skyshop.exceptions.BestResultNotFound;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            ProductBasket productBasket = new ProductBasket();

            Product simpleProduct = new SimpleProduct("Сандалеты Размер 42", 100);
            Product discountedProduct = new DiscountedProduct("Сандалеты Размер 47", 200, 50);
            Product fixPriceProduct = new FixPriceProduct("Шлёпки");

            productBasket.addProduct(simpleProduct);
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

            List<Product> removedProducts = productBasket.removeProductsByName(simpleProduct.getName());

            if (removedProducts != null) {
                System.out.println("_________________________________________________________________");
                System.out.println("Удалённые продукты (" + removedProducts.stream().count() + " шт.) :");

                removedProducts.forEach(product -> System.out.println(product.toString()));
                System.out.println("_________________________________________________________________");
                System.out.println("Корзина после удаления:");
                productBasket.printProductsInfo();
                System.out.println("_________________________________________________________________");
            }

            removedProducts = productBasket.removeProductsByName("Верёвка");
            if (removedProducts != null) {
                System.out.println("Список удалённых продуктов \"Верёвка\": ");
                System.out.println(
                        removedProducts.isEmpty()
                                ? "Список пуст" : "Список не пуст");
                System.out.println("_________________________________________________________________");
            }

            try {
                Searchable searchable = searchEngine.searchByMoreMatchEntry("товар товар");
                System.out.println("Найден 'элемент':\n" + searchable.getSearchTerm());
                System.out.println("_________________________________________________________________");
                searchable = searchEngine.searchByMoreMatchEntry("JJJ");
            } catch (BestResultNotFound ex) {
                System.out.println(ex.getMessage());
                System.out.println("_________________________________________________________________");
            }

            Map<String, Searchable> searchResult = searchEngine.search("Сандалеты");
            for (Map.Entry<String, Searchable> entry : searchResult.entrySet()){
                System.out.println(entry.getValue().toString());
            }

            System.out.println("_________________________________________________________________");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}