package web.field.viewmodel;

import java.util.*;

import android.app.Activity;
import web.field.helpers.*;
import web.field.model.entity.*;

public class ViewModelFactory {

	private Activity activity;

	public ViewModelFactory(Activity activity) {
		this.activity = activity;
	}

	/**
	 * Creates Order view model with basic data
	 * 
	 * @return
	 */
	public OrderViewModel CreateOrderViewModel() {
		OrderViewModel vm = new OrderViewModel(this.activity);
		vm.OrderTempId.set(UUID.randomUUID().toString());

		long now = Converter.dateToSeconds(new Date());
		String date = Converter.secondsToDateString(now).split(" ")[0];
		String time = Converter.secondsToDateString(now).split(" ")[1];

		// set order date and time
		vm.OrderDate.set(date);
		vm.OrderTime.set(time);

		// set delivery date and time
		vm.DeliveryDate.set(date);
		vm.DeliveryTime.set(time);

		return vm;
	}

	/**
	 * Creates Order view model with basic customer data
	 * 
	 * @return
	 */
	public OrderViewModel CreateOrderViewModel(Customer customer) {
		OrderViewModel vm = CreateOrderViewModel();
		CustomerAddress[] addresses = customer.getAddresses().toArray(
				new CustomerAddress[] {});

		// check if customer has only one address
		if (customer.getAddresses().size() < 2) {
			vm.BillToChangeEnabled.set(false);
			vm.ShipToChangeEnabled.set(false);
		}

		CustomerAddressViewModel billToVM = CreateCustomerAddressViewModel(addresses[0]);
		vm.BillToAddress.set(billToVM);

		CustomerAddressViewModel shipToVM = CreateCustomerAddressViewModel(addresses[0]);
		vm.ShipToAddress.set(shipToVM);

		return vm;
	}

	/**
	 * Creates Order view model based on Order entity
	 * 
	 * @param order
	 * @return
	 */
	public OrderViewModel CreateOrderViewModel(Order order) {
		OrderViewModel vm = CreateOrderViewModel();

		// copy data from order to view model //

		vm.OrderTempId.set(order.getOrderTempId());
		vm.OrderId.set(order.getOrderId());

		// use String vars to help in debuging
		String orderFullDate = Converter.secondsToDateString(order
				.getOrderDate());
		String orderDate = orderFullDate.split(" ")[0];
		String orderTime = orderFullDate.split(" ")[1];
		vm.OrderDate.set(orderDate);
		vm.OrderTime.set(orderTime);

		String deliveryFullDate = Converter.secondsToDateString(order
				.getDeliveryDate());
		String deliveryDate = deliveryFullDate.split(" ")[0];
		String deliveryTime = deliveryFullDate.split(" ")[1];
		vm.DeliveryDate.set(deliveryDate);
		vm.DeliveryTime.set(deliveryTime);

		CustomerAddressViewModel billToVM = CreateCustomerAddressViewModel(order
				.getBillTo());
		CustomerAddressViewModel shipToVM = CreateCustomerAddressViewModel(order
				.getShipTo());

		vm.BillToAddress.set(billToVM);
		vm.ShipToAddress.set(shipToVM);

		// set customer
		vm.CustomerId.set(order.getCustomer().getCustomerId());
		vm.Customer.set(order.getCustomer().getCustomerName());

		// check if customer has only one address
		if (order.getCustomer().getAddresses().size() < 2) {
			vm.BillToChangeEnabled.set(false);
			vm.ShipToChangeEnabled.set(false);
		}

		// copy order details
		if (order.getOrderDetails() != null) {
			OrderDetail[] orderDetails = order.getOrderDetails().toArray(
					new OrderDetail[] {});
			for (OrderDetail orderDetail : orderDetails) {

				OrderDetailViewModel orderDetailVM = new OrderDetailViewModel();

				// identifiers
				orderDetailVM.OrderDetailTempId.set(orderDetail
						.getOrderDetailTempId());
				orderDetailVM.OrderDetailId.set(orderDetail.getOrderDetailId());
				orderDetailVM.OrderId.set(orderDetail.getOrderId());
				orderDetailVM.OrderTempId.set(orderDetail.getOrder()
						.getOrderTempId());

				// product
				orderDetailVM.ProductId.set(orderDetail.getProduct()
						.getProductId());
				orderDetailVM.ProductDescription.set(orderDetail.getProduct()
						.getProductDescription());
				orderDetailVM.ProductCode.set(orderDetail.getProduct()
						.getCode());

				// values
				orderDetailVM.Discount.set(orderDetail.getDiscount());
				orderDetailVM.FreeQty.set(orderDetail.getFreeQty());
				orderDetailVM.Price.set(orderDetail.getPrice());
				orderDetailVM.Qty.set(orderDetail.getQty());

				vm.OrderDetails.add(orderDetailVM);
			}
		}

		return vm;
	}

	/**
	 * Creates Customer Address view model based on Customer Address entity
	 * 
	 * @param address
	 * @return
	 */
	public static CustomerAddressViewModel CreateCustomerAddressViewModel(
			CustomerAddress address) {
		CustomerAddressViewModel addressVM = new CustomerAddressViewModel();
		addressVM.Id.set(address.getCustomerAddressId());
		addressVM.Address.set(address.fullAddress());

		return addressVM;

	}
}
