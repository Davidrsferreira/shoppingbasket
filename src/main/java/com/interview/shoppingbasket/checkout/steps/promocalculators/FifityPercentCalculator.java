package com.interview.shoppingbasket.checkout.steps.promocalculators;

import com.interview.shoppingbasket.model.BasketItem;

public class FifityPercentCalculator implements PromotionCalculator {

	@Override
	public double calculate(BasketItem item, double price) {
		return price - (price * 0.5);
	}

}
