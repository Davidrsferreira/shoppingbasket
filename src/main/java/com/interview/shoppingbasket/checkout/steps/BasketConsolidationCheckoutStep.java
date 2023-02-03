package com.interview.shoppingbasket.checkout.steps;

import com.interview.shoppingbasket.checkout.context.CheckoutContext;
import com.interview.shoppingbasket.model.Basket;

public class BasketConsolidationCheckoutStep implements CheckoutStep {

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        basket.consolidateItems();
    }

}
