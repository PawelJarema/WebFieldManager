package web.field.viewmodel;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;
import gueei.binding.Command;
import gueei.binding.Observable;
import gueei.binding.collections.ArrayListObservable;
import gueei.binding.observables.*;

public class OrderViewModel {

	private Activity context;

	public OrderViewModel(Activity context) {
		this.context = context;
	}

	public StringObservable OrderTempId = new StringObservable();
	public IntegerObservable OrderId = new IntegerObservable();
	public StringObservable OrderDate = new StringObservable();
	public StringObservable DeliveryDate = new StringObservable();
	public StringObservable OrderTime = new StringObservable();
	public StringObservable DeliveryTime = new StringObservable();
	public Observable<CustomerAddressViewModel> BillToAddress = new Observable<CustomerAddressViewModel>(
			CustomerAddressViewModel.class);
	public Observable<CustomerAddressViewModel> ShipToAddress = new Observable<CustomerAddressViewModel>(
			CustomerAddressViewModel.class);
	public StringObservable Comments = new StringObservable();

	public BooleanObservable BillToChangeEnabled = new BooleanObservable(true);
	public BooleanObservable ShipToChangeEnabled = new BooleanObservable(true);

	public IntegerObservable CustomerId = new IntegerObservable();

	public StringObservable Customer = new StringObservable();

	public ArrayListObservable<OrderDetailViewModel> OrderDetails = new ArrayListObservable<OrderDetailViewModel>(
			OrderDetailViewModel.class);

}
