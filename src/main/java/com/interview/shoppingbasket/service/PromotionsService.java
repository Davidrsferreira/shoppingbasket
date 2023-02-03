package com.interview.shoppingbasket.service;

import java.util.List;

import com.interview.shoppingbasket.model.Basket;
import com.interview.shoppingbasket.model.Promotion;

public interface PromotionsService {
	List<Promotion> getPromotions(Basket basket);
}
