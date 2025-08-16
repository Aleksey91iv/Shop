package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;

public class Main {
    public static void main(String[] args) {
        ProductBasket productBasket = new ProductBasket();

        Product simpleProduct = new SimpleProduct("Сандалеты Размер 42", 100);
        Product discountedProduct = new DiscountedProduct("Сандалеты Размер 47", 200, 50);
        Product fixPriceProduct = new FixPriceProduct("Шлёпки");

        productBasket.addProduct(simpleProduct);
        productBasket.addProduct(discountedProduct);
        productBasket.addProduct(fixPriceProduct);

        productBasket.printProductsInfo();
    }
}