package web.field.logic;

import web.field.db.IDBAdapter;
import web.field.model.entity.OrderDetail;
import web.field.model.entity.OrderTemplate;
import web.field.model.entity.PromoThreshold;
import web.field.model.entity.PromoThresholdDetail;

public abstract class ThresholdVisitor implements IVisitor {
	
	protected IDBAdapter db;
	
	public ThresholdVisitor(IDBAdapter db){
		this.db = db;
	}

	@Override
	public void visit(IVisited visied){
		OrderDetail orderDetail = (OrderDetail) visied;
		// get order template
		
		int templateId = orderDetail.getOrder().getOrderTemplate().getOrdersTemplateId();
		OrderTemplate orderTemplate = this.db.getOrderTemplate(templateId);
		
		// check if should visit
		if(shouldVisit(orderTemplate)){
			
			PromoThreshold promoThreshold = orderTemplate.getPromoThreshold();
			
			// check if template has threshold assigned
			if(promoThreshold != null){
			
				// find corresponding threshold detail
				PromoThresholdDetail promoThresholdDetail = null;
				for(PromoThresholdDetail detail : promoThreshold.getPromoThresholdDetail()){
					if(detail.getProduct().getProductId() == orderDetail.getProduct().getProductId()){
						promoThresholdDetail = detail;
						break;
					}
				}

				// if threshold found, make visit
				if(promoThresholdDetail != null){
					makeVisit(orderDetail, promoThresholdDetail);
				}
			}
		}
	}
	
	protected boolean shouldVisit(OrderTemplate visited){
		return true;
	}
	
	public abstract OrderDetail makeVisit(OrderDetail visited, PromoThresholdDetail threshold);

}
