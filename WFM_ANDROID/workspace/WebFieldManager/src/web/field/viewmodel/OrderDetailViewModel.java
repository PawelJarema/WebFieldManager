package web.field.viewmodel;

import gueei.binding.observables.*;

public class OrderDetailViewModel {
	/* Order reference */
	public StringObservable OrderDetailTempId = new StringObservable();
	public StringObservable OrderTempId = new StringObservable();
	public IntegerObservable OrderDetailId = new IntegerObservable();
	public IntegerObservable OrderId = new IntegerObservable();
	
	/* Order Detail data */
	public DoubleObservable Discount = new DoubleObservable();
	public DoubleObservable Price = new DoubleObservable();
	public IntegerObservable Qty = new IntegerObservable();
	public IntegerObservable FreeQty = new IntegerObservable();
	public IntegerObservable Promo = new IntegerObservable();
	
	/* Product data */
	public IntegerObservable ProductId = new IntegerObservable();
	public StringObservable ProductDescription = new StringObservable();
	public StringObservable ProductCode = new StringObservable();
	
}
