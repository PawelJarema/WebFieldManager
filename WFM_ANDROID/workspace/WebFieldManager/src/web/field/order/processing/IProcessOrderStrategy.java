package web.field.order.processing;

public interface IProcessOrderStrategy {
	public OrderCalculationResult process(OrderCalculationRequest request);

}
