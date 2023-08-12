package com.nopainnogame.myShop;

import com.nopainnogame.myShop.model.Item;

import java.math.BigDecimal;

public class CartItem {
    private Item item;
    private int counter;
    private BigDecimal price;

    public CartItem(Item item) {
        this.item = item;
        this.counter = 1;
        this.price = item.getPrice();
    }

    public void increaseCounter() {
        counter++;
        recalculate();
    }

    public void decreaseCounter() {
        if (counter > 0) {
            counter--;
            recalculate();
        }
    }

    public void recalculate() {
        price = item.getPrice().multiply(new BigDecimal(counter));
    }
}
