package web.field.viewmodel;

import gueei.binding.observables.IntegerObservable;
import gueei.binding.observables.StringObservable;

public class CustomerAddressViewModel {
	public IntegerObservable Id = new IntegerObservable();
	public StringObservable Address = new StringObservable();
}
