package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private Map<String, LinkedList<Product>> basket = new HashMap<>();

    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Некорректный продукт.");
            return;
        }

        if (basket.containsKey(product.getName())) {
            basket.get(product.getName()).add(product);
        } else {
            LinkedList<Product> productsPosition = new LinkedList<Product>();
            productsPosition.add(product);
            basket.put(product.getName(), productsPosition);
        }
    }

    public List<Product> removeProductsByName(String productName) {
        return basket.remove(productName);
    }

    public int getBasketPrice() {
        int basketPrice = 0;
        for (Map.Entry<String,LinkedList<Product>> entry : basket.entrySet()) {
            basketPrice += entry.getValue()
                .stream()
                .filter(Objects::nonNull)
                .mapToInt(Product::getPrice)
                .sum();
        }

        return basketPrice;
    }

    public void printProductsInfo() {
        if (basket.size() == 0) {
            System.out.println("В корзине пусто.");
            return;
        }

        for (Map.Entry<String,LinkedList<Product>> entry : basket.entrySet()) {
            entry.getValue()
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(item -> System.out.println(item.getName() + ":\t" + item.getPrice()));
        }

        System.out.println("Итого: " + getBasketPrice());
    }

    public boolean isContains(String name) {
        return basket.containsKey(name) && basket.getOrDefault(name, new LinkedList<>()).isEmpty();
    }

    public void cleanBasket() {
        basket.clear();
    }
}
