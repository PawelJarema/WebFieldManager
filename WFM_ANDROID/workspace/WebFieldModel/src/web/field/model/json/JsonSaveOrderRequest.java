package web.field.model.json;

public class JsonSaveOrderRequest extends JsonRequest {
	private JsonOrder Order;

	public JsonOrder getOrder() {
		return Order;
	}

	public void setOrder(JsonOrder order) {
		Order = order;
	}

}
