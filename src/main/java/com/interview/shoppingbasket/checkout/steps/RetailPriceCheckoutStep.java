package com.interview.shoppingbasket.checkout.steps;

import java.util.Optional;

import com.interview.shoppingbasket.checkout.context.CheckoutContext;
import com.interview.shoppingbasket.model.Basket;
import com.interview.shoppingbasket.model.BasketItem;
import com.interview.shoppingbasket.model.Promotion;
import com.interview.shoppingbasket.service.PricingService;

public class RetailPriceCheckoutStep implements CheckoutStep {
	private PricingService pricingService;
	private double retailTotal;

	public RetailPriceCheckoutStep(PricingService pricingService) {
		this.pricingService = pricingService;
	}

	@Override
	public void execute(CheckoutContext checkoutContext) {
		Basket basket = checkoutContext.getBasket();
		retailTotal = 0.0;

		for (BasketItem basketItem : basket.getItems()) {
			int quantity = basketItem.getQuantity();
			double price = pricingService.getPrice(basketItem.getProductCode());

			if (!checkoutContext.getPromotions().isEmpty()) {
				Optional<Promotion> promotion = checkoutContext.getPromotions().stream()
						.filter(x -> x.getProductCode().equals(basketItem.getProductCode())).findFirst();
				price = applyPromotion(promotion.get(), basketItem, price);
			}

			basketItem.setProductRetailPrice(price);
			retailTotal += quantity * price;
		}

		checkoutContext.setRetailPriceTotal(retailTotal);
	}

	public double applyPromotion(Promotion promotion, BasketItem item, double price) {
		return promotion.applyPromotion(item, price);
	}
}
