package org.skypro.skyshop.product;

public class FixPriceProduct  extends Product {
    private final int FIX_PRICE;

    public FixPriceProduct (String name) {
        super(name);
        FIX_PRICE = 50;
    }

    @Override
    public int getPrice() {
        return FIX_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return super.getName() + ": Фиксированная цена " + FIX_PRICE;
    }
}
