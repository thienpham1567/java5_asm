package com.asm.interfaces;

import com.asm.entities.DbOrderDetail;
import com.asm.entities.DbProduct;
import java.util.Collection;

public interface ICartService {
	void add(int quantity, DbProduct entity);
	void remove(int productId);
	void update(int productId, int quantity);
	void clear();
	Collection<DbOrderDetail> getOrder();
	int getCount();
	double getAmount();
}
