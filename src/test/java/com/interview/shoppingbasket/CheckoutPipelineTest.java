package com.interview.shoppingbasket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.interview.shoppingbasket.checkout.CheckoutPipeline;
import com.interview.shoppingbasket.checkout.steps.BasketConsolidationCheckoutStep;
import com.interview.shoppingbasket.checkout.steps.CheckoutStep;
import com.interview.shoppingbasket.checkout.steps.PaymentSummary;
import com.interview.shoppingbasket.checkout.steps.PromotionCheckoutStep;
import com.interview.shoppingbasket.checkout.steps.RetailPriceCheckoutStep;
import com.interview.shoppingbasket.checkout.steps.promocalculators.FifityPercentCalculator;
import com.interview.shoppingbasket.checkout.steps.promocalculators.TenPercentCalculator;
import com.interview.shoppingbasket.checkout.steps.promocalculators.TwoForOneCalculator;
import com.interview.shoppingbasket.model.Basket;
import com.interview.shoppingbasket.model.Promotion;
import com.interview.shoppingbasket.service.PricingService;
import com.interview.shoppingbasket.service.PromotionsService;

public class CheckoutPipelineTest {

	CheckoutPipeline checkoutPipeline;
	List<Promotion> promotions;

	@Mock
	Basket basket;

	@Mock
	CheckoutStep checkoutStep1;

	@Mock
	CheckoutStep checkoutStep2;

	@Mock
	CheckoutStep checkoutStep3;

	@Mock
	PricingService pricingService;

	@Mock
	PromotionsService promotionsService;

	@BeforeEach
	void setup() {
		checkoutPipeline = new CheckoutPipeline();
		pricingService = Mockito.mock(PricingService.class);
		promotionsService = Mockito.mock(PromotionsService.class);
	}

	@Test
	void returnZeroPaymentForEmptyPipeline() {
		PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);

		assertEquals(paymentSummary.getRetailTotal(), 0.0);
	}

	@Test
	void executeAllPassedCheckoutSteps() {
		// Exercise - implement testing passing through all checkout steps

		checkoutStep1 = new BasketConsolidationCheckoutStep();
		checkoutStep2 = new PromotionCheckoutStep(promotionsService);
		checkoutStep3 = new RetailPriceCheckoutStep(pricingService);

		checkoutPipeline.addStep(checkoutStep1);
		checkoutPipeline.addStep(checkoutStep2);
		checkoutPipeline.addStep(checkoutStep3);

		basket = new Basket();
		basket.add("product1", "myproduct1", 11);
		basket.add("product2", "myproduct2", 10);
		basket.add("product3", "myproduct3", 10);

		when(pricingService.getPrice("product1")).thenReturn(5.0);
		when(pricingService.getPrice("product2")).thenReturn(2.0);
		when(pricingService.getPrice("product3")).thenReturn(2.25);

		promotions = new ArrayList<Promotion>();
		promotions.add(new Promotion("product1", new TwoForOneCalculator()));
		promotions.add(new Promotion("product2", new FifityPercentCalculator()));
		promotions.add(new Promotion("product3", new TenPercentCalculator()));

		when(promotionsService.getPromotions(basket)).thenReturn(promotions);

		PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);
		assertEquals(paymentSummary.getRetailTotal(), 60.25);
	}

}
