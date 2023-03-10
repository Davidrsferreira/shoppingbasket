package com.interview.shoppingbasket;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.interview.shoppingbasket.checkout.context.CheckoutContext;
import com.interview.shoppingbasket.checkout.steps.BasketConsolidationCheckoutStep;
import com.interview.shoppingbasket.model.Basket;

public class BasketConsolidationCheckoutStepTest {

    @Test
    void basketConsolidationCheckoutStepTest() {

        CheckoutContext checkoutContext = Mockito.mock(CheckoutContext.class);
        Basket basket = Mockito.mock(Basket.class);

        when(checkoutContext.getBasket()).thenReturn(basket);

        BasketConsolidationCheckoutStep basketConsolidationCheckoutStep = new BasketConsolidationCheckoutStep();
        basketConsolidationCheckoutStep.execute(checkoutContext);

        Mockito.verify(checkoutContext).getBasket();

        Mockito.verify(basket).consolidateItems();
    }

}
