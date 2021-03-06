package web.field.logic;

import web.field.db.IDBAdapter;
import web.field.model.entity.OrderDetail;
import web.field.model.entity.OrderTemplate;
import web.field.model.entity.PromoThresholdDetail;

public class ApplyDiscountVisitor extends ThresholdVisitor {

	public ApplyDiscountVisitor(IDBAdapter db) {
		super(db);
	}

	@Override
	public OrderDetail makeVisit(OrderDetail orderDetail,
			PromoThresholdDetail threshold) {
		int orderQty = orderDetail.getQty();
		int promoMax = threshold.getThresholdMaxValue();
		int promoMin = threshold.getThresholdMinValue();
		double discount = threshold.getThresholdDiscount();

		if (orderQty <= promoMax && orderQty >= promoMin) {
			orderDetail.setDiscount(discount);
		}
		
		return orderDetail;

	}
	
	@Override
	protected boolean shouldVisit(OrderTemplate visited){
		return visited.isFlagDiscountVisible();
	}

}
