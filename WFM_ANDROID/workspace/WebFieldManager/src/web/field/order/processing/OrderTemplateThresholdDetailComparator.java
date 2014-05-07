package web.field.order.processing;

import java.util.Comparator;

import web.field.model.entity.OrderTemplateThresholdDetail;

public class OrderTemplateThresholdDetailComparator implements
		Comparator<OrderTemplateThresholdDetail> {
	
	@Override
	public int compare(OrderTemplateThresholdDetail o1,
			OrderTemplateThresholdDetail o2) {
		return Double.compare(o2.getOrderTotal(), o1.getOrderTotal());
	}
}
