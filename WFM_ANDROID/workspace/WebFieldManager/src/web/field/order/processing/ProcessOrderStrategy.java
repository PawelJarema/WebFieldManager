package web.field.order.processing;

import web.field.helpers.Converter;
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

		// get threshold for full total order value
		double orderTemplateThresholdDiscount = order
				.getDiscountHeaderThreshold();
		OrderTemplateThresholdDetail thresholdDetail = cache
				.getOrderTemplateThresholdDetailForOrder(fullValue);
		if (thresholdDetail != null) {
			orderTemplateThresholdDiscount = thresholdDetail.getDiscount();
			result.setOrderTemplateThresholdDiscount(orderTemplateThresholdDiscount);
		} else {
			result.setOrderTemplateThresholdDiscount(0);
		}

		// get template discount
		double templateDiscount = 0;
		if (order.getOrderTemplate() != null) {
			templateDiscount = calculationRequest.getOrder().getOrderTemplate()
					.getDiscount();
		}

		double discountsValue = 0;
		double freeProducts = 0;
		double totalValue = 0;
		// process order details
		for (OrderDetail detail : calculationRequest.getOrderDetails()) {

			// get qty and value
			int qty = detail.getQty();
			double value = detail.getProduct().getPrice() * qty;
			// apply all discounts
			double lineValueAfterDiscounts = value;

			OrderCalculationDetaiResult detailCalculationResult = new OrderCalculationDetaiResult();
			detailCalculationResult.setProductId(detail.getProduct()
					.getProductId());

			// get corresponding template detail
			OrderTemplateDetail templateDetail = cache
					.getOrderTemplateDetailForOrderDetail(detail);

			// check flag no promo
			boolean applyDiscounts = true;
			if (templateDetail != null) {
				applyDiscounts = !templateDetail.isFlagNoPromo();
			}

			if (applyDiscounts) {
				// if we got here, we can apply promo stuff
				if (templateDetail != null) {
					// check if max qty is provided and if extended
					if (templateDetail.getQtyMax() != 0) {

						detailCalculationResult
								.setAboweMaxValue(qty > templateDetail
										.getQtyMax());
					}

					// check if min qty is provided and if abowe
					if (templateDetail.getQtyMin() != 0) {
						detailCalculationResult
								.setBelowMinValue(qty < templateDetail
										.getQtyMin());
					}
				}

				// get free qty and discount
				PromoThresholdDetail promoThresholdDetail = cache
						.getPromoThresholdDetailForOrderDetail(detail);
				if (promoThresholdDetail != null) {
					double freeQty = promoThresholdDetail.getThresholdFreeQty()
							* qty;
					detail.setFreeQty((int) freeQty);

					double discount = Converter
							.percentToDouble(promoThresholdDetail
									.getThresholdDiscount())
							* value;
					detail.setDiscount(discount);

					// apply discount
					lineValueAfterDiscounts = lineValueAfterDiscounts
							* Converter.percentToDouble(promoThresholdDetail
									.getThresholdDiscount());

					// free products
					freeProducts = 0;
					if (promoThresholdDetail.getThresholdFixedFreeQty() != 0) {
						freeProducts += promoThresholdDetail
								.getThresholdFixedFreeQty();
					} else {
						freeProducts += promoThresholdDetail
								.getThresholdFreeQty();
					}
				}

				// try to apply template discount, but only if product is in
				// template
				if (templateDiscount != 0
						&& cache.isOrderDetailInTemplate(detail)) {
					lineValueAfterDiscounts = lineValueAfterDiscounts
							- (lineValueAfterDiscounts * Converter
									.percentToDouble(templateDiscount));
				}

				// try to apply template threshold discount, but only if product
				// is in template
				if (orderTemplateThresholdDiscount != 0
						&& cache.isOrderDetailInTemplate(detail)) {
					lineValueAfterDiscounts = lineValueAfterDiscounts - (lineValueAfterDiscounts
							* Converter
									.percentToDouble(orderTemplateThresholdDiscount));
				}

				// try to apply pay term discount
				if (order.getDiscountHeaderPayterms() != 0) {
					lineValueAfterDiscounts = lineValueAfterDiscounts - (lineValueAfterDiscounts
							* Converter.percentToDouble(order
									.getDiscountHeaderPayterms()));
					;
				}
			}

			// round calculation
			lineValueAfterDiscounts = Math
					.round(lineValueAfterDiscounts * 100.0) / 100.0;
			value = Math.round(value * 100.0) / 100.0;

			totalValue += lineValueAfterDiscounts;
			detailCalculationResult
					.setValueAfterDiscounts(lineValueAfterDiscounts);
			detailCalculationResult.setValueBeforeDiscounts(value);

			discountsValue += (value - lineValueAfterDiscounts);

		}

		result.setTotalDiscountsValue(discountsValue);
		result.setOrderTotal(totalValue);
		result.setFreeProducts(freeProducts);

		return result;
	}
}
