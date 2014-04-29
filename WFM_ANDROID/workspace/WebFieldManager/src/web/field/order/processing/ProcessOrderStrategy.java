package web.field.order.processing;

import web.field.model.entity.*;

public class ProcessOrderStrategy implements IProcessOrderStrategy {

	@Override
	public OrderCalculationResult process(
			OrderCalculationRequest calculationRequest) {
		OrderCalculationResult result = new OrderCalculationResult();
		OrderCache cache = calculationRequest.getOrderCashe();
		Order order = calculationRequest.getOrder();

		// first count full order value without discounts
		double fullValue = 0;
		for (OrderDetail detail : calculationRequest.getOrderDetails()) {
			double detailValue = detail.getProduct().getPrice()
					* detail.getQty();
			fullValue += detailValue;
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

		double discountsValue = 0;
		// process order details
		for (OrderDetail detail : calculationRequest.getOrderDetails()) {

			// get qty and value
			int qty = detail.getQty();
			double value = detail.getProduct().getPrice() * qty;
			// apply all discounts
			double lineValueAfterDiscounts = value;
			double lineDiscount = detail.getDiscount();

			// if we got here, we can apply promo stuff

			OrderCalculationDetaiResult detailCalculationResult = new OrderCalculationDetaiResult();
			detailCalculationResult.setProductId(detail.getProduct()
					.getProductId());

			// get corresponding template detail
			OrderTemplateDetail templateDetail = cache
					.getOrderTemplateDetailForOrderDetail(detail);

			// check flag no promo
			if (templateDetail != null && !templateDetail.isFlagNoPromo()) {

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

			detailCalculationResult
					.setValueAfterDiscounts(lineValueAfterDiscounts);
			detailCalculationResult.setValueBeforeDiscounts(value);

			discountsValue += (value - lineValueAfterDiscounts);

		}

		result.setTotalDiscountsValue(discountsValue);

		return result;
	}

}
