package com.interview.shoppingbasket.checkout.steps.promocalculators;

import com.interview.shoppingbasket.model.BasketItem;

public class TwoForOneCalculator implements PromotionCalculator {

	@Override
	public double calculate(BasketItem item, double price) {
		if (item.getQuantity() % 2 == 1) {
			price = ((price * (item.getQuantity() - 1)) / 2) + price;
			return price / item.getQuantity();
		}

		return price / 2;
	}

}
