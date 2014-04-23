package web.field.sync;

import web.field.model.entity.Order;

public interface ISendOrderStrategy {
	public void sendOrder(Order order);
}
