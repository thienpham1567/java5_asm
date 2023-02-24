package com.asm.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;

import org.springframework.stereotype.Service;

import com.asm.entities.DbOrderDetail;
import com.asm.entities.DbProduct;
import com.asm.interfaces.ICartService;

@Service
public class CartService implements ICartService {
	public static Map<Integer, DbOrderDetail> map = new HashMap<>();

	@Override
	public void add(int quantity, DbProduct product) {
		DbOrderDetail orderDetail = new DbOrderDetail();
		if (this.map.containsKey(product.getProductId())) {
			this.update(product.getProductId(), quantity);
		} else {
			orderDetail.setProduct(product);
			orderDetail.setQuantity(quantity);
			map.put(product.getProductId(), orderDetail);
		}
	}

	@Override
	public void remove(int id) {
		map.remove(id);
	}

	@Override
	public void update(int productId, int quantity) {
		DbOrderDetail orderDetail = map.get(productId);
		orderDetail.setQuantity(quantity + orderDetail.getQuantity());
		orderDetail.setDetailPrice(orderDetail.getProduct().getPrice() * orderDetail.getQuantity());
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Collection<DbOrderDetail> getOrder() {
		return map.values();
	}

	@Override
	public int getCount() {
		return map.size();
	}

	@Override
	public double getAmount() {
		Set<Integer> keys = map.keySet();
		double amount = 0;
		for (Integer productId : keys) {
			amount += map.get(productId).getDetailPrice();
		}
		return amount;
	}
}
