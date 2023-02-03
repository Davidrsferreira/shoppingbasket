package com.interview.shoppingbasket.checkout.steps.promocalculators;

import com.interview.shoppingbasket.model.BasketItem;

public interface PromotionCalculator {
	double calculate(BasketItem item, double price);
}
