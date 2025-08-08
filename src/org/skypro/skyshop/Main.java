package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class Main {
    public static void main(String[] args) {
        ProductBasket productBasket = new ProductBasket();

        Product product = null;
        productBasket.addProduct(product);

        for (int i = 40; i < 46; i++) {
            product = new Product("Сандалеты Размер " + i, 250 + i);
            productBasket.addProduct(product);
        }

        productBasket.printProductsInfo();
        System.out.println(productBasket.getBasketPrice());

        System.out.println(productBasket.isContains("Сандалеты Размер 44"));
        System.out.println(productBasket.isContains("Сандалеты Размер 45"));

        productBasket.cleanBasket();
        productBasket.printProductsInfo();
        System.out.println(productBasket.getBasketPrice());
        System.out.println(productBasket.isContains("Сандалеты Размер 43"));
    }
}