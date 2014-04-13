package web.field.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.field.db.IDBAdapter;
import web.field.helpers.Converter;
import web.field.helpers.DateHelper;
import web.field.helpers.EntityToJsonConverter;
import web.field.model.entity.EntityType;
import web.field.model.entity.Order;
import web.field.model.entity.OrderDetail;
import web.field.model.entity.Outbound;
import web.field.model.json.JsonOrder;

public class OrderManager implements IOrderManager{
	
	private IDBAdapter db;
	
	public OrderManager(IDBAdapter db){
		this.db = db;
	}

	@Override
	public Order saveOrder(Order order) {
		
		// process order before save
		ApplyDiscountVisitor discountVisitor = new ApplyDiscountVisitor(db);
		ApplyFreeQtyVisitor freeQtyVisitor = new ApplyFreeQtyVisitor(db);
		
		for(OrderDetail detail : order.getOrderDetails()){
			detail.accept(freeQtyVisitor);
			detail.accept(discountVisitor);
		}
		
		// save order
		db.saveOrder(order);
		
		// if it is not draft, put in outbound table
		if(order.getStatus() != 0){
			Gson gson = new GsonBuilder().create();
			JsonOrder jsonOrder = EntityToJsonConverter.ConvertOrder(order);
			Outbound outbound = new Outbound();
			outbound.setJsonData(gson.toJson(jsonOrder));
			outbound.setEntityId(order.getOrderTempId());
			outbound.setEntityType(EntityType.Order);
			
			SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
			long seconds = new Date().getTime();
			
			// try to get utc seconds
			try {
				Date now = DateHelper.getUtcNow();
				seconds = Converter.dateToSeconds(now);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			outbound.setCreateDate(seconds);
			outbound.setModifyDate(seconds);
			
			db.saveOutbound(outbound);
		}
		
		return order;
	}

}
