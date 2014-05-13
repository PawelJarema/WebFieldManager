package web.field.model.mapping;


import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import web.field.model.entity.*;
import web.field.model.json.*;

public class ModelMapping {

	private static ModelMapper builder;

	public static ModelMapper mapper() {
		if (builder == null) {
			builder = new ModelMapper();
		}

		PropertyMap<Order, JsonOrder> orderMap = new PropertyMap<Order, JsonOrder>() {
			  protected void configure() {
			    map().setCustomerId(source.getCustomer().getCustomerId());
			  }
			};
			
			PropertyMap<OrderDetail, JsonOrderDetail> orderDetailMap = new PropertyMap<OrderDetail, JsonOrderDetail>() {
				  protected void configure() {
				    map().setProductId(source.getProduct().getProductId());
				    map().setOrderTempId(source.getOrder().getOrderTempId());
				  }
				};	
			
		builder.addMappings(orderMap);
		builder.addMappings(orderDetailMap);
		
		return builder;
	}

}
