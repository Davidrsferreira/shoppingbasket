package com.interview.shoppingbasket.model;

import com.interview.shoppingbasket.checkout.steps.promocalculators.PromotionCalculator;

public class Promotion {
	private String productCode;
	private PromotionCalculator calculator;

	public Promotion(String productCode, PromotionCalculator calculator) {
		this.productCode = productCode;
		this.calculator = calculator;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public void setCalculator(PromotionCalculator calculator) {
		this.calculator = calculator;
	}

	public double applyPromotion(BasketItem item, double price) {
		return calculator.calculate(item, price);
	}
}
