package com.interview.shoppingbasket.checkout.steps;

import java.util.List;

import com.interview.shoppingbasket.checkout.context.CheckoutContext;
import com.interview.shoppingbasket.model.Basket;
import com.interview.shoppingbasket.model.Promotion;
import com.interview.shoppingbasket.service.PromotionsService;

public class PromotionCheckoutStep implements CheckoutStep {
	private PromotionsService promotionsService;

	public PromotionCheckoutStep(PromotionsService promotionsService) {
		this.promotionsService = promotionsService;
	}

	@Override
	public void execute(CheckoutContext checkoutContext) {
		Basket basket = checkoutContext.getBasket();
		List<Promotion> promotions = promotionsService.getPromotions(basket);

		checkoutContext.setPromotions(promotions);
	}
}
