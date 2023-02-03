package com.interview.shoppingbasket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
	private List<BasketItem> items = new ArrayList<>();

	public void add(String productCode, String productName, int quantity) {
		BasketItem basketItem = new BasketItem();
		basketItem.setProductCode(productCode);
		basketItem.setProductName(productName);
		basketItem.setQuantity(quantity);

		items.add(basketItem);
	}

	public List<BasketItem> getItems() {
		return items;
	}

	public void consolidateItems() {
		// Exercise - implement this function

		if (items.isEmpty()) {
			System.out.println("Basket has no items");
			return;
		}

		Map<String, BasketItem> itemsMap = new HashMap<String, BasketItem>();

		for (BasketItem item : items) {
			String code = item.getProductCode();

			if (!itemsMap.containsKey(code)) {
				itemsMap.put(code, item);
			} else {
				int sumOfQuantity = itemsMap.get(code).getQuantity() + item.getQuantity();
				itemsMap.get(code).setQuantity(sumOfQuantity);
			}
		}

		items = new ArrayList<BasketItem>(itemsMap.values());
	}
}
