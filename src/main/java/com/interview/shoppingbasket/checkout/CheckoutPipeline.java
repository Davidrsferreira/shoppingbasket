package com.interview.shoppingbasket.checkout;

import java.util.ArrayList;
import java.util.List;

import com.interview.shoppingbasket.checkout.context.CheckoutContext;
import com.interview.shoppingbasket.checkout.steps.CheckoutStep;
import com.interview.shoppingbasket.checkout.steps.PaymentSummary;
import com.interview.shoppingbasket.model.Basket;

public class CheckoutPipeline {

    private List<CheckoutStep> steps = new ArrayList<>();

    public PaymentSummary checkout(Basket basket) {
        CheckoutContext checkoutContext = new CheckoutContext(basket);
        for (CheckoutStep checkoutStep : steps) {
            checkoutStep.execute(checkoutContext);
        }

        return checkoutContext.paymentSummary();
    }

    public void addStep(CheckoutStep checkoutStep) {
        steps.add(checkoutStep);
    }
}
