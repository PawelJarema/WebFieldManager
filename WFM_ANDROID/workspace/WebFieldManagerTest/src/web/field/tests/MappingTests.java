package web.field.tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import com.j256.ormlite.dao.ForeignCollection;

import web.field.model.entity.*;
import web.field.model.json.*;
import web.field.model.mapping.ModelMapping;

public class MappingTests {

	private static final double DELTA = 1e-15;

	@Test
	public void order_mapping_test() {

		ModelMapper mapper = ModelMapping.mapper();

		Order src = new Order();

		// ids //
		src.setOrderId(100);
		src.setTenantId(8);
		src.setUserId(99);
		src.setOrderTempId("test");

		// dates //
		src.setDeliveryDate(1000);
		src.setOrderDate(2000);
		src.setCreateDate(3000);

		// flags //
		src.setFlagError(false);
		src.setFlagValid(true);

		// discs //
		src.setDiscountHeaderPayterms(999);
		src.setDiscountHeaderTemplate(888);
		src.setDiscountHeaderThreshold(777);

		// rest
		src.setStatus(1);
		src.setComment("test comment");

		// address bill to //
		CustomerAddress billTo = new CustomerAddress();

		// address ids //
		billTo.setTenantId(8);
		billTo.setCustomerAddressId(101);

		// address flags //
		billTo.setBillToFlag(true);
		billTo.setShipToFlag(true);

		// address data //
		billTo.setCity("test bill city");
		billTo.setCustomerAddressDescription("test bill CustomerAddressDescription");
		billTo.setDistrict("test bill district");
		billTo.setEmail("test.bill@email.com");
		billTo.setFax("test bill fax");
		billTo.setLatitude(Double.valueOf(200));
		billTo.setLongitude(Double.valueOf(300));
		billTo.setPhone1("test bill phone 1");
		billTo.setPhone2("test bill phone 2");
		billTo.setPostalCode("test bill postal code");
		billTo.setStreet("test bill street");
		billTo.setCodeCountryId(9);

		// address dates //
		billTo.setStartDate(567);
		billTo.setEndDate(765);

		// address other //
		billTo.setERPCode("test bill erp code");

		// address set //
		src.setBillTo(billTo);

		// address ship to
		CustomerAddress shipTo = new CustomerAddress();

		// address ids //
		shipTo.setTenantId(8);
		shipTo.setCustomerAddressId(101);

		// address flags //
		shipTo.setBillToFlag(true);
		shipTo.setShipToFlag(true);

		// address data //
		shipTo.setCity("test ship city");
		shipTo.setCustomerAddressDescription("test ship CustomerAddressDescription");
		shipTo.setDistrict("test ship district");
		shipTo.setEmail("test.ship@email.com");
		shipTo.setFax("test ship fax");
		shipTo.setLatitude(Double.valueOf(200));
		shipTo.setLongitude(Double.valueOf(300));
		shipTo.setPhone1("test ship phone 1");
		shipTo.setPhone2("test ship phone 2");
		shipTo.setPostalCode("test ship postal code");
		shipTo.setStreet("test ship street");
		shipTo.setCodeCountryId(9);

		// address dates //
		shipTo.setStartDate(890);
		shipTo.setEndDate(990);

		// address other //
		shipTo.setERPCode("test ship erp code");

		// address set //
		src.setShipTo(shipTo);

		// customer //
		Customer customer = new Customer();

		// customer ids //
		customer.setCustomerId(575);

		// order details
		ForeignCollection<OrderDetail> details = new TestForeignCollection<OrderDetail>();

		OrderDetail det1 = new OrderDetail();
		det1.setOrderId(src.getOrderId());
		det1.setOrder(src);
		det1.setDiscount(0.05);
		det1.setFreeQty(231);
		det1.setOrderDetailTempId("test detail temp id");
		det1.setPrice(45.67);

		Product p1 = new Product();
		p1.setProductId(121);
		det1.setProduct(p1);

		det1.setQty(678);
		det1.setTenantId(8);
		
		details.add(det1);
		src.setOrderDetails(details);

		JsonOrder dest = mapper.map(src, JsonOrder.class);
		mapper.map(src, dest);

		// map collection
		List<JsonOrderDetail> jsonDetails = new ArrayList<JsonOrderDetail>();
		for(OrderDetail det : details){
			jsonDetails.add(mapper.map(det, JsonOrderDetail.class));
		}
		
		dest.setOrdersDetail(jsonDetails);

		// ids //
		assertEquals(src.getOrderId(), dest.getOrderId());
		assertEquals(src.getTenantId(), dest.getTenantId());
		assertEquals(src.getUserId(), dest.getUserId());
		assertEquals(src.getOrderTempId(), dest.getOrderTempId());

		// dates //
		assertEquals(src.getDeliveryDate(), dest.getDeliveryDate());
		assertEquals(src.getOrderDate(), dest.getOrderDate());
		assertEquals(src.getCreateDate(), dest.getCreateDate());

		// flags //
		assertEquals(src.isFlagError(), dest.isFlagError());
		assertEquals(src.isFlagValid(), dest.isFlagValid());

		// discs //
		assertEquals(src.getDiscountHeaderPayterms(),
				dest.getDiscountHeaderPayterms(), DELTA);
		assertEquals(src.getDiscountHeaderTemplate(),
				dest.getDiscountHeaderTemplate(), DELTA);
		assertEquals(src.getDiscountHeaderThreshold(),
				dest.getDiscountHeaderThreshold(), DELTA);

		// rest
		assertEquals(src.getStatus(), dest.getStatus());
		assertEquals(src.getComment(), dest.getComment());

		assertNotEquals(0, src.getOrderDetails().size());

		for (OrderDetail srcDet : src.getOrderDetails()) {
			for (JsonOrderDetail destDet : dest.getOrdersDetail()) {
				if (destDet.getOrderDetailTempId().compareToIgnoreCase(
						srcDet.getOrderDetailTempId()) == 0) {
					assertEquals(srcDet.getOrderId(), destDet.getOrderId());
					assertEquals(srcDet.getDiscount(), destDet.getDiscount(),
							DELTA);
					assertEquals(srcDet.getFreeQty(), destDet.getFreeQty());
					assertEquals(srcDet.getOrderDetailTempId(),
							destDet.getOrderDetailTempId());
					assertEquals(srcDet.getPrice(), destDet.getPrice(), DELTA);
					assertEquals(srcDet.getProduct().getProductId(),
							destDet.getProductId());
					assertEquals(srcDet.getQty(), destDet.getQty());
					assertEquals(srcDet.getTenantId(), destDet.getTenantId());
				}
			}
		}

	}
}
