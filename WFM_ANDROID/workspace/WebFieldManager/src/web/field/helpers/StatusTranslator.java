package web.field.helpers;

import android.content.Context;
import web.field.R;
import web.field.model.entity.OrderStatus;

public class StatusTranslator {
	public static String getOrderStatus(int status, Context context) {
		switch (status) {
		case OrderStatus.DRAFT:
			return context.getResources().getString(R.string.order_status_draft);
		case OrderStatus.OUTBOX:
			return context.getResources().getString(R.string.order_status_outbox);
		case OrderStatus.SENT:
			return context.getResources().getString(R.string.order_status_sent);
		case OrderStatus.APPROVAL:
			return context.getResources().getString(R.string.order_status_approval);
		case OrderStatus.APPROVED:
			return context.getResources().getString(R.string.order_status_approved);
		case OrderStatus.EDIT:
			return context.getResources().getString(R.string.order_status_edit);
		case OrderStatus.PROCESSED:
			return context.getResources().getString(R.string.order_status_processed);
		case OrderStatus.CANCELED:
			return context.getResources().getString(R.string.order_status_canceled);
		}

		return String.valueOf(status);
	}
}
