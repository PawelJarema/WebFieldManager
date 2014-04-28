package web.field.tests;

import java.util.*;

import org.junit.*;

import web.field.model.entity.*;
import web.field.order.processing.*;

public class OrderProcessingTest {

	private OrderCache orderCache;
	private Order order;
	private OrderTemplate orderTemplate;
	private List<PromoPayTermDetail> promoPayTermDetails = new ArrayList<PromoPayTermDetail>();
	private OrderTemplateThreshold orderTemplateThreshold;
	private PromoThreshold promoThreshold;
	
	private ProcessOrderTask task;
	
	private OrderCalculationRequest calculationRequest;
	

	@Test
	public void simpleOrderPriocessTest(){
		
		// create promo payterm
		PromoPayTermDetail promoPayTemrDetail = new PromoPayTermDetail();
		
		
	}
	
}
