package com.interview.shoppingbasket;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.interview.shoppingbasket.checkout.context.CheckoutContext;
import com.interview.shoppingbasket.checkout.steps.PromotionCheckoutStep;
import com.interview.shoppingbasket.checkout.steps.promocalculators.FifityPercentCalculator;
import com.interview.shoppingbasket.checkout.steps.promocalculators.TenPercentCalculator;
import com.interview.shoppingbasket.checkout.steps.promocalculators.TwoForOneCalculator;
import com.interview.shoppingbasket.model.Basket;
import com.interview.shoppingbasket.model.Promotion;
import com.interview.shoppingbasket.service.PromotionsService;

public class PromotionCheckoutStepTest {

	PromotionsService promotionsService;
	CheckoutContext checkoutContext;
	List<Promotion> promotions;
	Basket basket;

	@BeforeEach
	void setup() {
		promotionsService = Mockito.mock(PromotionsService.class);
		checkoutContext = Mockito.mock(CheckoutContext.class);
		promotions = new ArrayList<Promotion>();
		basket = new Basket();

		when(checkoutContext.getBasket()).thenReturn(basket);
	}

	@Test
	void setPromotionsTest() {

		promotions.add(new Promotion("product1", new TwoForOneCalculator()));
		promotions.add(new Promotion("product2", new FifityPercentCalculator()));
		promotions.add(new Promotion("product3", new TenPercentCalculator()));

		when(promotionsService.getPromotions(basket)).thenReturn(promotions);

		PromotionCheckoutStep checkoutStep = new PromotionCheckoutStep(promotionsService);
		checkoutStep.execute(checkoutContext);

		Mockito.verify(checkoutContext).setPromotions(promotions);
	}
}
