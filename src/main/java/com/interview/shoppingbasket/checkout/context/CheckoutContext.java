package com.interview.shoppingbasket.checkout.context;

import java.util.List;

import com.interview.shoppingbasket.checkout.steps.PaymentSummary;
import com.interview.shoppingbasket.model.Basket;
import com.interview.shoppingbasket.model.Promotion;

public class CheckoutContext {
	private Basket basket;
	private double retailPriceTotal = 0.0;
	private List<Promotion> promotions;

	public void setRetailPriceTotal(double retailPriceTotal) {
		this.retailPriceTotal = retailPriceTotal;
	}

	public CheckoutContext(Basket basket) {
		this.basket = basket;
	}

	public PaymentSummary paymentSummary() {
		return new PaymentSummary(retailPriceTotal);
	}

	public Basket getBasket() {
		return basket;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}
}
