package web.field.logic;

import java.math.BigDecimal;

import web.field.db.IDBAdapter;
import web.field.model.entity.OrderDetail;
import web.field.model.entity.OrderTemplate;
import web.field.model.entity.PromoThresholdDetail;

public class ApplyFreeQtyVisitor extends ThresholdVisitor{
	
	public ApplyFreeQtyVisitor(IDBAdapter db){
		super(db);
	}

	@Override
	public OrderDetail makeVisit(OrderDetail orderDetail,
			PromoThresholdDetail threshold) {
		int orderQty = orderDetail.getQty();
		int promoMax = threshold.getThresholdMaxValue();
		int promoMin = threshold.getThresholdMinValue();
		BigDecimal freeQty = threshold.getThresholdFreeQty();

		if (orderQty <= promoMax && orderQty >= promoMin) {
			orderDetail.setFreeQty(freeQty.intValue());
		}
		
		return orderDetail;
	}
	
	@Override
	protected boolean shouldVisit(OrderTemplate visited){
		return visited.isFlagFreeQtyVisible();
	} 


}
