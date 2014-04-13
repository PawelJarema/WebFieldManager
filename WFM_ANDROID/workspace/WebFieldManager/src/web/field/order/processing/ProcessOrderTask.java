package web.field.order.processing;

import java.math.BigDecimal;

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
		BigDecimal fullValue = new BigDecimal(0);
		for (OrderDetail detail : calculationRequest.getOrderDetails()) {
			BigDecimal detailValue = detail.getProduct().getPrice()
					.multiply(new BigDecimal(detail.getQty()));
			fullValue = fullValue.add(detailValue);
		}

		// get threshold for total order value
		BigDecimal orderTemplateThresholdDiscount = new BigDecimal(0);
		OrderTemplateThresholdDetail thresholdDetail = cache
				.getOrderTemplateThresholdDetailForOrder(result.getOrderTotal());
		if (thresholdDetail != null) {
			orderTemplateThresholdDiscount = new BigDecimal(
					thresholdDetail.getDiscount());
		}

		// get template discount
		BigDecimal templateDiscount = new BigDecimal(0);
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
				BigDecimal value = detail.getProduct().getPrice()
						.multiply(new BigDecimal(qty));

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
					BigDecimal freeQty = promoThresholdDetail
							.getThresholdFreeQty()
							.multiply(new BigDecimal(qty));
					detail.setFreeQty(freeQty.intValue());
				}

				if (promoThresholdDetail != null) {
					BigDecimal discount = promoThresholdDetail
							.getThresholdDiscount().multiply(value);
					detail.setDiscount(discount);
				}
				
				// apply all discounts
				BigDecimal zero = new BigDecimal(0);
				BigDecimal one = new BigDecimal(1);
				BigDecimal lineValueAfterDiscounts = value;
				BigDecimal lineDiscount = detail.getDiscount();
				
				// try to apply line discount
				if(lineDiscount.compareTo(zero) != 0){
					BigDecimal discountMultiplier = one.subtract(lineDiscount);
					lineValueAfterDiscounts = lineValueAfterDiscounts.multiply(discountMultiplier);
				}
				
				// try to apply template discount
				if(templateDiscount.compareTo(zero) != 0){
					BigDecimal discountMultiplier = one.subtract(templateDiscount);
					lineValueAfterDiscounts = lineValueAfterDiscounts.multiply(discountMultiplier);
				}
				
				// try to apply template threshold discount
				if(orderTemplateThresholdDiscount.compareTo(zero) != 0){
					BigDecimal discountMultiplier = one.subtract(orderTemplateThresholdDiscount);
					lineValueAfterDiscounts = lineValueAfterDiscounts.multiply(discountMultiplier);
				}
				
				// try to apply pay term discount
				if(order.getDiscountHeaderPayterms().compareTo(zero) != 0){
					BigDecimal discountMultiplier = one.subtract(order.getDiscountHeaderPayterms());
					lineValueAfterDiscounts = lineValueAfterDiscounts.multiply(discountMultiplier);
				}

			}

		}

		return result;
	}

}
