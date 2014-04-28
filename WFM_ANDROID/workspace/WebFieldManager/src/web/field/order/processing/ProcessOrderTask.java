package web.field.order.processing;


import web.field.model.entity.*;
import android.os.AsyncTask;

/**
 * Process order basing on product prices
 * 
 * @author Bro
 * 
 */
public class ProcessOrderTask extends
		AsyncTask<OrderCalculationRequest, Void, OrderCalculationResult> {

	@Override
	protected OrderCalculationResult doInBackground(
			OrderCalculationRequest... requests) {
		OrderCalculationResult result = new OrderCalculationResult();
		OrderCalculationRequest calculationRequest = requests[0];
		OrderCache cache = calculationRequest.getOrderCashe();
		Order order = calculationRequest.getOrder();

		// first count full order value without discounts
		double fullValue = 0;
		for (OrderDetail detail : calculationRequest.getOrderDetails()) {
			double detailValue = detail.getProduct().getPrice()
					* detail.getQty();
			fullValue += fullValue;
		}
		result.setFullValue(fullValue);

		// get threshold for total order value
		double orderTemplateThresholdDiscount = 0;
		OrderTemplateThresholdDetail thresholdDetail = cache
				.getOrderTemplateThresholdDetailForOrder(result.getOrderTotal());
		if (thresholdDetail != null) {
			orderTemplateThresholdDiscount = thresholdDetail.getDiscount();
			result.setOrderTemplateThresholdDiscount(orderTemplateThresholdDiscount);
		}
		

		// get template discount
		double templateDiscount = 0;
		if (calculationRequest.getOrder().getOrderTemplate() != null) {
			templateDiscount = calculationRequest.getOrder().getOrderTemplate()
					.getDiscount();
		}
		// process order details
		for (OrderDetail detail : calculationRequest.getOrderDetails()) {

			// get corresponding template detail
			OrderTemplateDetail templateDetail = cache
					.getOrderTemplateDetailForOrderDetail(detail);
			if (templateDetail != null) {

				// check flag no promo
				if (templateDetail.isFlagNoPromo()) {

					// ignore this row
					continue;
				}

				// if we got here, we can apply promo stuff
				OrderCalculationDetaiResult detailCalculationResult = new OrderCalculationDetaiResult();
				detailCalculationResult.setProductId(detail.getProduct()
						.getProductId());

				// get qty and value
				int qty = detail.getQty();
				double value = detail.getProduct().getPrice() * qty;

				// check if max qty is provided and if extended
				if (templateDetail.getQtyMax() != 0) {

					detailCalculationResult
							.setAboweMaxValue(qty > templateDetail.getQtyMax());
				}

				// check if min qty is provided and if abowe
				if (templateDetail.getQtyMin() != 0) {
					detailCalculationResult
							.setBelowMinValue(qty < templateDetail.getQtyMin());
				}

				// get free qty and discount
				PromoThresholdDetail promoThresholdDetail = cache
						.getPromoThresholdDetailForOrderDetail(detail);
				if (promoThresholdDetail != null) {
					double freeQty = promoThresholdDetail.getThresholdFreeQty()
							* qty;
					detail.setFreeQty((int) freeQty);

					double discount = promoThresholdDetail
							.getThresholdDiscount() * value;
					detail.setDiscount(discount);
				}
				
				// apply all discounts
				double lineValueAfterDiscounts = value;
				double lineDiscount = detail.getDiscount();
				
				// try to apply line discount
				if (lineDiscount != 0) {
					double discountMultiplier = 1 - lineDiscount;
					lineValueAfterDiscounts = lineValueAfterDiscounts
							* discountMultiplier;
				}
				
				// try to apply template discount
				if (templateDiscount != 0) {
					double discountMultiplier = 1 - templateDiscount;
					lineValueAfterDiscounts = lineValueAfterDiscounts
							- discountMultiplier;
				}
				
				// try to apply template threshold discount
				if (orderTemplateThresholdDiscount != 0) {
					double discountMultiplier = 1 - orderTemplateThresholdDiscount;
					lineValueAfterDiscounts = lineValueAfterDiscounts
							* discountMultiplier;
				}
				
				// try to apply pay term discount
				if (order.getDiscountHeaderPayterms() != 0) {
					double discountMultiplier = 1 - order
							.getDiscountHeaderPayterms();
					lineValueAfterDiscounts = lineValueAfterDiscounts
							* discountMultiplier;
				}

			}

		}

		return result;
	}

}
