package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ProductBasket {
    private List<Product> basket = new LinkedList();

    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Некорректный продукт.");
            return;
        }

        basket.add(product);
    }

    public List<Product> removeProductsByName(String productName) {
        List<Product> removedProducts = basket.stream()
            .filter(product -> product.getName().equals(productName))
            .toList();
        basket.removeIf(product -> product.getName().equals(productName));

        return removedProducts;
    }

    public int getBasketPrice() {
        return basket.stream()
            .filter(Objects::nonNull)
            .mapToInt(Product::getPrice)
            .sum();
    }

    public void printProductsInfo() {
        if (basket.stream().count() == 0) {
            System.out.println("В корзине пусто.");
            return;
        }

        basket.stream()
            .filter(Objects::nonNull)
            .forEach(item -> System.out.println(item.getName() + ":\t" + item.getPrice()));

        System.out.println("Итого: "
            + basket.stream()
            .filter(Objects::nonNull)
            .mapToInt(Product::getPrice)
            .sum());
    }

    public boolean isContains(String name) {
        return basket.stream()
            .filter(Objects::nonNull)
            .anyMatch(product -> product.getName().equals(name));
    }

    public void cleanBasket() {
        basket.clear();
    }
}
