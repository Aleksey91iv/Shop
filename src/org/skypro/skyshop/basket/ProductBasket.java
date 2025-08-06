package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.Arrays;
import java.util.Objects;

public class ProductBasket {
    private final int size = 5;
    private Product[] basket = new Product[size];
    private int basketPrice = 0;
    private int productsCount = 0;

    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Некорректный продукт.");
            return;
        } else if (productsCount == size) {
            System.out.println("Корзина переполнена. Нет места для данного товара.");
            return;
        }

        for (int i = 0; i < size; i++) {
            if (basket[i] == null) {
                basket[i] = product;
                break;
            }
        }
        basketPrice += product.getPrice();
        productsCount++;
    }

    public int getBasketPrice() {
        return basketPrice;
    }

    public void printProductsInfo() {
        if (productsCount == 0) {
            System.out.println("В корзине пусто.");
            return;
        }

        for(Product product : basket) {
            if (product == null) {
                continue;
            }
            System.out.println(product.getName() + ":\t" + product.getPrice());
        }
        System.out.println("Итого: " + basketPrice);
    }

    public boolean isContains(String name) {
        return !Arrays.stream(basket)
                .filter(product ->
                    product != null && product.getName().equals(name))
                .toList().isEmpty();
    }

    public void cleanBasket() {
        Arrays.stream(basket).forEach(product -> product = null);
        basketPrice = 0;
        productsCount = 0;
    }
}
