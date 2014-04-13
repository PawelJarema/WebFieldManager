package web.field.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.j256.ormlite.dao.Dao;

import web.field.helpers.Converter;
import web.field.model.entity.*;
import web.field.model.json.*;
import web.field.model.simple.*;

public class DBAdapter implements IDBAdapter {

	static final String TAG = "DBAdapter";
	static final String DATABASE_NAME = "WebFieldManagerDB";
	static final int DATABASE_VERSION = 1;
	private IDaoProvider daoProvider;

	public DBAdapter(IDaoProvider dbHelper) {
		this.daoProvider = dbHelper;
	}

	@Override
	public Customer getCustomer(int customerId) {
		try {
			Dao<Customer, Integer> customerDao = daoProvider.getCustomerDao();
			return customerDao.queryForId(customerId);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<CustomerSimple> listCustomers() {
		List<CustomerSimple> result = new ArrayList<CustomerSimple>();

		try {
			Dao<Customer, Integer> customerDao = daoProvider.getCustomerDao();
			for (Customer customer : customerDao) {
				CustomerSimple info = new CustomerSimple();
				info.setCustomerId(customer.getCustomerId());
				info.setCustomerName(customer.getCustomerName());
				info.setVatId(customer.getVatId());
				info.setActive(customer.isActive());
				result.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void loadCustomers(List<JsonCustomer> customers) {

		try {
			Dao<Customer, Integer> customerDao = daoProvider.getCustomerDao();
			Dao<CustomerCategory, Integer> customerCategoryDao = daoProvider
					.getCustomerCategoryDao();
			Dao<CustomerAddress, Integer> customerAddressDao = daoProvider
					.getCustomerAddressDao();
			// map json on entity and save
			for (JsonCustomer jsoncust : customers) {

				// customer entity
				Customer customer = new Customer();
				customer.setCustomerId(jsoncust.getCustomerId());
				customer.setCustomerName(jsoncust.getCustomerName());
				customer.setVatId(jsoncust.getVatId());
				customer.setTenantId(jsoncust.getTenantId());
				customer.setFlagTemp(jsoncust.isFlagTemp());
				customer.setStartDate(jsoncust.getStartDate());
				customer.setEndDate(jsoncust.getEndDate());
				customer.setCustomerERPCode(jsoncust.getCustomerERPCode());

				// customer category entity
				CustomerCategory customerCategory = new CustomerCategory();
				customerCategory.setCustomerCategoryId(jsoncust
						.getCustomerCategoryId());
				customerCategory.setCategoryDescription(jsoncust
						.getCustomerCategory().getCategoryDescription());
				customerCategory.setFlagValid(jsoncust.getCustomerCategory()
						.isFlagValid());
				customerCategory.setTenantId(jsoncust.getCustomerCategory()
						.getTenantId());

				customer.setCustomerCategory(customerCategory);

				// save
				customerDao.createOrUpdate(customer);
				customerCategoryDao.createOrUpdate(customerCategory);

				// customer addresses
				for (JsonCustomerAddress jsonaddr : jsoncust
						.getCustomerAddress()) {
					CustomerAddress address = new CustomerAddress();
					address.setCustomerAddressId(jsonaddr
							.getCustomerAddressId());
					address.setBillToFlag(jsonaddr.isBillToFlag());
					address.setShipToFlag(jsonaddr.isShipToFlag());
					address.setCity(jsonaddr.getCity());
					address.setCodeCountryId(jsonaddr.getCodeCountryId());
					address.setCustomerAddressDescription(jsonaddr
							.getCustomerAddressDescription());
					address.setDistrict(jsonaddr.getDistrict());
					address.setEmail(jsonaddr.getEmail());
					address.setEndDate(jsonaddr.getEndDate());
					address.setFax(jsonaddr.getFax());
					address.setPhone1(jsonaddr.getPhone1());
					address.setPhone2(jsonaddr.getPhone2());
					address.setPostalCode(jsonaddr.getPostalCode());
					address.setStartDate(jsonaddr.getStartDate());
					address.setStreet(jsonaddr.getStreet());
					address.setTenantId(jsonaddr.getTenantId());
					address.setERPCode(jsonaddr.getERPCode());
					address.setCustomer(customer);

					// save
					customerAddressDao.createOrUpdate(address);
				}
			}

		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void loadProducts(List<JsonProduct> products) {
		try {
			Dao<Product, Integer> productDao = daoProvider.getProductDao();
			Dao<ProductManufacturer, Integer> productManufacturerDao = daoProvider
					.getProductManufacturerDao();
			Dao<ProductBrand, Integer> productBrandDao = daoProvider
					.getProductBrandDao();
			Dao<ProductCategory, Integer> productCategoryDao = daoProvider
					.getProductCategoryDao();
			Dao<ProductFamily, Integer> productFamilyDao = daoProvider
					.getProductFamilyDao();
			for (JsonProduct jsonprod : products) {
				ProductManufacturer productManufacturer = new ProductManufacturer();
				productManufacturer.setProductManufacturerId(jsonprod
						.getProductManufacturerId());
				productManufacturer.setFlagValid(jsonprod
						.getProductManufacturers().isFlagValid());
				productManufacturer
						.setManufacturerDescription(jsonprod
								.getProductManufacturers()
								.getManufacturerDescription());
				productManufacturer.setTenantId(jsonprod
						.getProductManufacturers().getTenantId());

				// save
				productManufacturerDao.createOrUpdate(productManufacturer);

				// product brand entity
				ProductBrand productBrand = new ProductBrand();
				productBrand.setProductBrandId(jsonprod.getProductBrandId());
				productBrand.setBrandDescription(jsonprod.getProductBrands()
						.getBrandDescription());
				productBrand.setFlagValid(jsonprod.getProductBrands()
						.isFlagValid());
				productBrand.setTenantId(jsonprod.getProductBrands()
						.getTenantId());

				// save
				productBrandDao.createOrUpdate(productBrand);

				// product category entity
				ProductCategory productCategory = new ProductCategory();
				productCategory.setProductCategoryId(jsonprod
						.getProductCategoryId());
				productCategory.setTenantId(jsonprod.getProductCategory()
						.getTenantId());
				productCategory.setCategoryDescription(jsonprod
						.getProductCategory().getCategoryDescription());
				productCategory.setFlagValid(jsonprod.getProductCategory()
						.isFlagValid());
				if (!productCategoryDao.idExists(productCategory
						.getProductCategoryId())) {
					productCategoryDao.create(productCategory);
				}

				// product famili entity
				ProductFamily productFamiliy = new ProductFamily();
				productFamiliy
						.setProductFamilyId(jsonprod.getProductFamilyId());
				productFamiliy.setTenantId(jsonprod.getProductFamilys()
						.getTenantId());
				productFamiliy.setFamilyDescription(jsonprod
						.getProductFamilys().getFamilyDescription());
				productFamiliy.setFlagValid(jsonprod.getProductFamilys()
						.isFlagValid());

				// save
				productFamilyDao.createOrUpdate(productFamiliy);

				// product entity
				Product product = new Product();
				product.setProductId(jsonprod.getProductId());
				product.setCode(jsonprod.getCode());
				product.setFlagActive(jsonprod.isFlagActive());
				product.setPrice(jsonprod.getPrice());
				product.setTenantId(jsonprod.getTenantId());
				product.setProductManufacturer(productManufacturer);
				product.setProductBrands(productBrand);
				product.setProductCategory(productCategory);
				product.setProductFamily(productFamiliy);
				product.setProductDescription(jsonprod.getProductDescription());

				productDao.createOrUpdate(product);
			}

		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadOrderTemplates(List<JsonOrderTemplate> orderTemplates) {
		try {
			Dao<OrderTemplate, Integer> orderTemplateDao = daoProvider
					.getOrderTemplateDao();
			Dao<OrderTemplateDetail, Integer> orderTemplateDetailDao = daoProvider
					.getOrderTemplateDetailDao();
			Dao<Product, Integer> productDao = daoProvider.getProductDao();
			Dao<PromoThreshold, Integer> promoThresholdDao = daoProvider
					.getPromoThresholdDao();
			Dao<OrderTemplateThreshold, Integer> orderTemplateThresholdDao = daoProvider
					.getOrderTemplateThresholdDao();

			for (JsonOrderTemplate jsontem : orderTemplates) {
				// order template entity
				OrderTemplate template = new OrderTemplate();
				template.setOrdersTemplateId(jsontem.getOrdersTemplateId());
				template.setDeliveriesDateEnd(jsontem.getDeliveriesDateEnd());
				template.setDeliveriesDateStart(jsontem
						.getDeliveriesDateStart());
				template.setDescription(jsontem.getDescription());
				template.setDiscount(jsontem.getDiscount());
				template.setFlagActive(jsontem.isFlagActive());
				template.setFlagCanceled(jsontem.isFlagCanceled());
				template.setFlagDiscountVisible(jsontem.isFlagDiscountVisible());
				template.setFlagFreeQtyVisible(jsontem.isFlagFreeQtyVisible());
				template.setFlagQtyVisible(jsontem.isFlagQtyVisible());
				template.setName(jsontem.getName());
				template.setSplitsInBetweenDays(jsontem
						.getSplitsInBetweenDays());
				template.setSplitsMax(jsontem.getSplitsMax());
				template.setSplitsMin(jsontem.getSplitsMin());
				template.setTenantId(jsontem.getTenantId());
				template.setTotalMinValue(jsontem.getTotalMinValue());
				template.setVisibleDateEnd(jsontem.getVisibleDateEnd());
				template.setVisibleDateStart(jsontem.getVisibleDateStart());

				// promo threshold
				if (jsontem.getPromoThresholdId() != null) {
					PromoThreshold promoThreshold = promoThresholdDao
							.queryForId(jsontem.getPromoThresholdId());
					template.setPromoThreshold(promoThreshold);
				}

				// order template threshold
				OrderTemplateThreshold orderTemplateThreshold = orderTemplateThresholdDao
						.queryForId(jsontem.getOrdersTemplateThresholdId());
				template.setOrderTemplateThreshold(orderTemplateThreshold);

				// save
				orderTemplateDao.createOrUpdate(template);

				// order template details
				for (JsonOrderTemplateDetail jsondet : jsontem
						.getOrdersTemplateDetails()) {
					OrderTemplateDetail templateDetail = new OrderTemplateDetail();
					templateDetail.setFlagNoEdit(jsondet.isFlagNoEdit());
					templateDetail.setFlagNoPromo(jsondet.isFlagNoPromo());
					templateDetail.setOrdersTemplateDetailId(jsondet
							.getOrdersTemplateDetailId());
					templateDetail.setQtyMax(jsondet.getQtyMax());
					templateDetail.setQtyMin(jsondet.getQtyMin());
					templateDetail.setQtyMultiples(jsondet.getQtyMultiples());
					templateDetail.setQtyProposal(jsondet.getQtyProposal());
					templateDetail.setTenantId(jsondet.getTenantId());
					templateDetail.setOrdersTemplate(template);

					Product product = productDao.queryForId(jsondet
							.getProductId());
					templateDetail.setProduct(product);

					orderTemplateDetailDao.createOrUpdate(templateDetail);
				}

			}
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ProductSimple> listProducts() {
		List<ProductSimple> result = new ArrayList<ProductSimple>();

		try {
			Dao<Product, Integer> productDao = daoProvider.getProductDao();
			for (Product product : productDao) {
				ProductSimple info = new ProductSimple();
				info.setProductId(product.getProductId());
				info.setProductCode(product.getCode());
				info.setProductDescription(product.getProductDescription());
				info.setActive(product.isFlagActive());
				result.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<OrderTemplateSimple> listOrderTemplates() {
		List<OrderTemplateSimple> result = new ArrayList<OrderTemplateSimple>();

		try {
			Dao<OrderTemplate, Integer> templateDao = daoProvider
					.getOrderTemplateDao();
			for (OrderTemplate template : templateDao) {
				OrderTemplateSimple info = new OrderTemplateSimple();
				info.setTemplateId(template.getOrdersTemplateId());
				info.setActive(template.isFlagActive());
				info.setTemplateName(template.getName());
				info.setTemplateDescription(template.getDescription());
				result.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void loadOrders(List<JsonOrder> orders) {
		try {
			Dao<Order, String> orderDao = daoProvider.getOrderDao();
			Dao<OrderDetail, String> orderDetailDao = daoProvider
					.getOrderDetailDao();
			Dao<CustomerAddress, Integer> customerAddressDao = daoProvider
					.getCustomerAddressDao();
			Dao<Product, Integer> productDao = daoProvider.getProductDao();
			for (JsonOrder jsonor : orders) {
				// order
				Order order = new Order();
				order.setOrderTempId(jsonor.getOrderTempId());
				order.setComment(jsonor.getComment());
				order.setDeliveryDate(jsonor.getDeliveryDate());
				order.setFlagError(jsonor.isFlagError());
				order.setFlagValid(jsonor.isFlagValid());
				order.setOrderDate(jsonor.getOrderDate());
				order.setOrderId(jsonor.getOrderId());
				order.setDiscountHeaderPayterms(jsonor
						.getDiscountHeaderPayterms());
				order.setDiscountHeaderTemplate(jsonor
						.getDiscountHeaderTemplate());
				order.setDiscountHeaderThreshold(jsonor
						.getDiscountHeaderThreshold());

				order.setTenantId(jsonor.getTenantId());

				// set bill to address
				CustomerAddress billTo = customerAddressDao.queryForId(jsonor
						.getBillToId());
				order.setBillTo(billTo);

				// set ship to address
				CustomerAddress shipTo = customerAddressDao.queryForId(jsonor
						.getShipToId());
				order.setShipTo(shipTo);

				// set customer
				order.setCustomer(billTo.getCustomer());

				orderDao.createOrUpdate(order);

				for (JsonOrderDetail jsondet : jsonor.getOrdersDetail()) {
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrderDetailTempId(jsondet
							.getOrderDetailTempId());

					orderDetail.setDiscount(jsondet.getDiscount());
					orderDetail.setFreeQty(jsondet.getFreeQty());

					orderDetail.setOrderId(jsondet.getOrderId());
					orderDetail.setPrice(jsondet.getPrice());
					orderDetail.setQty(jsondet.getQty());
					orderDetail.setTenantId(jsondet.getTenantId());

					Product product = productDao.queryForId(jsondet
							.getProductId());
					orderDetail.setProduct(product);
					orderDetail.setOrder(order);

					orderDetailDao.createOrUpdate(orderDetail);

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<OrderSimple> listOrders(Integer customerId) {
		List<OrderSimple> result = new ArrayList<OrderSimple>();
		try {
			Dao<Order, String> orderDao = daoProvider.getOrderDao();
			Dao<CustomerAddress, Integer> customerAddressDao = daoProvider
					.getCustomerAddressDao();
			List<Order> queryResult = null;
			if (customerId == null || customerId == 0) {
				queryResult = orderDao.queryForAll();

			} else {
				queryResult = orderDao.queryBuilder().where()
						.eq("customer_id", customerId).query();
			}
			for (Order order : queryResult) {
				OrderSimple simple = new OrderSimple();
				simple.setOrderId(order.getOrderId());
				simple.setOrderDate(Converter.secondsToDateString(order
						.getOrderDate()));
				if (order.getShipTo() != null) {
					CustomerAddress addr = customerAddressDao.queryForId(order
							.getShipTo().getCustomerAddressId());
					simple.setOrderSummary(addr.fullAddress());
				} else if (order.getBillTo() != null) {
					CustomerAddress addr = customerAddressDao.queryForId(order
							.getBillTo().getCustomerAddressId());
					simple.setOrderSummary(addr.fullAddress());
				}
				result.add(simple);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<PromoGroupSimple> listPromoGroups() {
		List<PromoGroupSimple> result = new ArrayList<PromoGroupSimple>();
		try {
			Dao<PromoGroup, Integer> promoGroupDao = daoProvider
					.getPromoGroupDao();
			List<PromoGroup> queryResult = null;

			queryResult = promoGroupDao.queryForAll();

			for (PromoGroup entity : queryResult) {
				PromoGroupSimple simple = new PromoGroupSimple();
				simple.setPromoGroupId(entity.getPromoGroupId());
				simple.setDescription(entity.getDescription());
				simple.setFlagActive(entity.isFlagActive());
				result.add(simple);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<PromoPayTermSimple> listPromoPayTerms() {
		List<PromoPayTermSimple> result = new ArrayList<PromoPayTermSimple>();
		try {
			Dao<PromoPayTerm, Integer> dao = daoProvider.getPromoPayTermDao();
			List<PromoPayTerm> queryResult = null;

			queryResult = dao.queryForAll();

			for (PromoPayTerm entity : queryResult) {
				PromoPayTermSimple simple = new PromoPayTermSimple();
				simple.setPromoPayTermsId(entity.getPromoPayTermsId());
				simple.setPromoPayTermDescription(entity
						.getPromoPayTermDescription());
				simple.setFlagActive(entity.isFlagActive());
				result.add(simple);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<PromoThresholdSimple> listPromoTresholdSimple() {
		List<PromoThresholdSimple> result = new ArrayList<PromoThresholdSimple>();
		try {
			Dao<PromoThreshold, Integer> dao = daoProvider
					.getPromoThresholdDao();
			List<PromoThreshold> queryResult = null;

			queryResult = dao.queryForAll();

			for (PromoThreshold entity : queryResult) {
				PromoThresholdSimple simple = new PromoThresholdSimple();
				simple.setPromoThresholdId(entity.getPromoThresholdId());
				simple.setThresholdDescription(entity.getThresholdDescription());
				simple.setFlagActive(entity.isFlagActive());
				result.add(simple);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<OrdersTemplateThresholdSimple> listOrderTemplatesThresholds() {
		List<OrdersTemplateThresholdSimple> result = new ArrayList<OrdersTemplateThresholdSimple>();
		try {
			Dao<OrderTemplateThreshold, Integer> dao = daoProvider
					.getOrderTemplateThresholdDao();
			List<OrderTemplateThreshold> queryResult = null;

			queryResult = dao.queryForAll();

			for (OrderTemplateThreshold entity : queryResult) {
				OrdersTemplateThresholdSimple simple = new OrdersTemplateThresholdSimple();
				simple.setOrdersTemplateThresholdId(entity
						.getOrdersTemplateThresholdId());
				simple.setName(entity.getName());
				simple.setFlagActive(entity.isFlagActive());
				result.add(simple);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void loadPromoGroups(List<JsonPromoGroup> promoGroups) {
		try {
			Dao<PromoGroup, Integer> dao = daoProvider.getPromoGroupDao();
			Dao<PromoGroupDetail, Integer> daoDetails = daoProvider
					.getPromoGroupDetailDao();
			Dao<Product, Integer> daoProduct = daoProvider.getProductDao();

			for (JsonPromoGroup json : promoGroups) {
				PromoGroup entity = new PromoGroup();
				entity.setCreateDate(json.getCreateDate());
				entity.setCreateUserId(json.getCreateUserId());
				entity.setDescription(json.getDescription());
				entity.setFlagActive(json.isFlagActive());
				entity.setModifiedDate(json.getModifiedDate());
				entity.setModifiedUserId(json.getModifiedUserId());
				entity.setPromoGroupId(json.getPromoGroupsId());
				entity.setTenantId(json.getTenantId());

				// save
				dao.createOrUpdate(entity);

				for (JsonPromoGroupDetail jsonDetail : json
						.getPromoGroupsDetail()) {
					PromoGroupDetail entityDetail = new PromoGroupDetail();
					entityDetail.setCreateDate(jsonDetail.getCreateDate());
					entityDetail.setCreateUserId(jsonDetail.getCreateUserId());
					entityDetail.setDiscount(jsonDetail.getDiscount());
					entityDetail.setFreeQty(jsonDetail.getFreeQty());
					entityDetail.setModifiedDate(jsonDetail.getModifiedDate());
					entityDetail.setModifiedUserId(jsonDetail
							.getModifiedUserId());
					entityDetail.setQty(jsonDetail.getQty());
					entityDetail.setTenantId(jsonDetail.getTenantId());

					// product
					Product prod = daoProduct.queryForId(jsonDetail
							.getProductId());
					entityDetail.setProduct(prod);

					// promo group
					entityDetail.setPromoGroup(entity);

					// save
					daoDetails.createOrUpdate(entityDetail);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void loadPromoPayTerms(List<JsonPromoPayTerm> promoPayTerms) {
		try {
			Dao<PromoPayTerm, Integer> dao = daoProvider.getPromoPayTermDao();
			Dao<PromoPayTermDetail, Integer> daoDetails = daoProvider
					.getPromoPayTermsDetailDao();

			for (JsonPromoPayTerm json : promoPayTerms) {
				PromoPayTerm entity = new PromoPayTerm();
				entity.setCreateDate(json.getCreateDate());
				entity.setCreateUserId(json.getCreateUserId());
				entity.setTenantId(json.getTenantId());
				entity.setModifiedDate(json.getModifiedDate());
				entity.setModifiedUserId(json.getModifiedUserId());
				entity.setFlagActive(json.isFlagActive());

				entity.setPromoPayTermDescription(json
						.getPromoPayTermDescription());
				entity.setPromoPayTermsId(json.getPromoPayTermId());

				// save
				dao.createOrUpdate(entity);

				for (JsonPromoPayTermDetail jsonDetail : json
						.getPromoPayTermDetails()) {
					PromoPayTermDetail entityDetail = new PromoPayTermDetail();
					entityDetail.setCreateDate(jsonDetail.getCreateDate());
					entityDetail.setCreateUserId(jsonDetail.getCreateUserId());
					entityDetail.setModifiedDate(jsonDetail.getModifiedDate());
					entityDetail.setModifiedUserId(jsonDetail
							.getModifiedUserId());
					entityDetail.setTenantId(jsonDetail.getTenantId());

					entityDetail.setDiscount(jsonDetail.getDiscount());
					entityDetail.setExternalId_1(jsonDetail.getExternalId_1());

					// pay term
					entityDetail.setPromoPayTerm(entity);

					// save
					daoDetails.createOrUpdate(entityDetail);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void loadPromoThreshold(List<JsonPromoThreshold> promoThresholds) {
		try {
			Dao<PromoThreshold, Integer> dao = daoProvider
					.getPromoThresholdDao();
			Dao<PromoThresholdDetail, Integer> daoDetails = daoProvider
					.getPromoThresholdDetailDao();
			Dao<Product, Integer> daoProduct = daoProvider.getProductDao();

			for (JsonPromoThreshold json : promoThresholds) {
				PromoThreshold entity = new PromoThreshold();
				entity.setCreateDate(json.getCreateDate());
				entity.setCreateUserId(json.getCreateUserId());
				entity.setTenantId(json.getTenantId());
				entity.setModifiedDate(json.getModifiedDate());
				entity.setModifiedUserId(json.getModifiedUserId());
				entity.setFlagActive(json.isFlagActive());

				entity.setPromoThresholdId(json.getPromoThresholdId());
				entity.setThresholdDescription(json.getThresholdDescription());

				// save
				dao.createOrUpdate(entity);

				for (JsonPromoThresholdDetail jsonDetail : json
						.getPromoThresholdDetail()) {
					PromoThresholdDetail entityDetail = new PromoThresholdDetail();
					entityDetail.setCreateDate(jsonDetail.getCreateDate());
					entityDetail.setCreateUserId(jsonDetail.getCreateUserId());
					entityDetail.setModifiedDate(jsonDetail.getModifiedDate());
					entityDetail.setModifiedUserId(jsonDetail
							.getModifiedUserId());
					entityDetail.setTenantId(jsonDetail.getTenantId());

					entityDetail.setThresholdDiscount(jsonDetail
							.getThresholdDiscount());
					entityDetail.setPromoThresholdDetailId(jsonDetail
							.getPromoThresholdDetailId());
					entityDetail.setThresholdFixedFreeQty(jsonDetail
							.getThresholdFixedFreeQty());
					entityDetail.setThresholdFreeQty(jsonDetail
							.getThresholdFreeQty());
					entityDetail.setThresholdMaxValue(jsonDetail
							.getThresholdMaxValue());
					entityDetail.setThresholdMinValue(jsonDetail
							.getThresholdMinValue());
					entityDetail
							.setThresholdType(jsonDetail.getThresholdType());

					// product
					Product prod = daoProduct.queryForId(jsonDetail
							.getProductId());
					entityDetail.setProduct(prod);

					// promo threshold
					entityDetail.setPromoThreshold(entity);

					// save
					daoDetails.createOrUpdate(entityDetail);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void loadOrderTemplateThresholds(
			List<JsonOrderTemplateThreshold> thresholds) {
		try {
			Dao<OrderTemplateThreshold, Integer> dao = daoProvider
					.getOrderTemplateThresholdDao();
			Dao<OrderTemplateThresholdDetail, Integer> daoDetails = daoProvider
					.getOrdersTemplateThresholdDetailsDao();

			for (JsonOrderTemplateThreshold json : thresholds) {
				OrderTemplateThreshold entity = new OrderTemplateThreshold();
				entity.setCreateDate(json.getCreateDate());
				entity.setCreateUserId(json.getCreateUserId());
				entity.setTenantId(json.getTenantId());
				entity.setModifiedDate(json.getModifiedDate());
				entity.setModifiedUserId(json.getModifiedUserId());
				entity.setFlagActive(json.isFlagActive());

				entity.setFlagCanceled(json.isFlagCanceled());
				entity.setName(json.getName());
				entity.setOrdersTemplateThresholdId(json
						.getOrderTemplateThresholdId());

				// save
				dao.createOrUpdate(entity);

				for (JsonOrderTemplateThresholdDetails jsonDetail : json
						.getOrderTemplateThresholdDetails()) {
					OrderTemplateThresholdDetail entityDetail = new OrderTemplateThresholdDetail();
					entityDetail.setCreateDate(jsonDetail.getCreateDate());
					entityDetail.setCreateUserId(jsonDetail.getCreateUserId());
					entityDetail.setModifiedDate(jsonDetail.getModifiedDate());
					entityDetail.setModifiedUserId(jsonDetail
							.getModifiedUserId());
					entityDetail.setTenantId(jsonDetail.getTenantId());

					// template threshold
					entityDetail.setOrdersTemplateThreshold(entity);

					// save
					daoDetails.createOrUpdate(entityDetail);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public OrderTemplateThreshold getOrderTemplateThreshold(int id) {
		try {
			Dao<OrderTemplateThreshold, Integer> dao = daoProvider
					.getOrderTemplateThresholdDao();
			return dao.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public PromoGroup getPromoGroup(int id) {
		try {
			Dao<PromoGroup, Integer> dao = daoProvider.getPromoGroupDao();
			return dao.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public PromoPayTerm getPromoPayTerm(int id) {
		try {
			Dao<PromoPayTerm, Integer> dao = daoProvider.getPromoPayTermDao();
			return dao.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public PromoThreshold getPromoThreshold(int id) {
		try {
			Dao<PromoThreshold, Integer> dao = daoProvider
					.getPromoThresholdDao();

			PromoThreshold threshold = dao.queryForId(id);

			return threshold;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Order getOrder(String tmpId) {
		try {
			Dao<Order, String> dao = daoProvider.getOrderDao();
			return dao.queryForId(tmpId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Order getOrder(int id) {
		try {
			Dao<Order, String> dao = daoProvider.getOrderDao();

			List<Order> queryResult = dao.queryBuilder().where()
					.eq("OrderId", id).query();

			for (Order order : queryResult) {
				return order;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Order saveOrder(Order order) {
		try {
			Dao<Order, String> dao = daoProvider.getOrderDao();
			dao.createOrUpdate(order);

			return order;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public OrderTemplate getOrderTemplate(int id) {
		try {
			Dao<OrderTemplate, Integer> dao = daoProvider.getOrderTemplateDao();

			// get order template
			OrderTemplate orderTemplate = dao.queryForId(id);

			// get promo threshold
			if (orderTemplate.getPromoThreshold() != null) {
				orderTemplate.setPromoThreshold(getPromoThreshold(orderTemplate
						.getPromoThreshold().getPromoThresholdId()));
			}

			return orderTemplate;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Outbound saveOutbound(Outbound outbound) {
		try {
			Dao<Outbound, Long> dao = daoProvider.getOutboundDao();

			dao.createOrUpdate(outbound);

			return outbound;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<CustomerSimple> listCustomersNearMe(double latitude,
			double longitude) {
		// TODO replace with real search
		return listCustomers();
	}

	@Override
	public Order saveOrderFromTemplate(Order order, OrderTemplate orderTemplate) {
		// add order lines basing on template data
		for (OrderTemplateDetail templateDetail : orderTemplate
				.getOrdersTemplateDetails()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(order);
			orderDetail.setProduct(templateDetail.getProduct());
			orderDetail.setOrderDetailTempId(UUID.randomUUID().toString());
			orderDetail.setQty(templateDetail.getQtyProposal());
			orderDetail.setTenantId(order.getTenantId());
		}

		return order;
	}

	@Override
	public User getUser(String token) {
		try {
			Dao<User, Integer> dao = daoProvider.getUserDao();
			
			List<User> userResult = dao.queryBuilder().where()
					.eq("Token", token).query();

			for (User user : userResult) {
				return user;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void loadUsers(List<JsonUser> users) {
		try {
			Dao<User, Integer> dao = daoProvider.getUserDao();
			for (JsonUser json : users) {
				User entity = new User();
				entity.setCreateDate(json.getCreateDate());
				entity.setCreateUserId(json.getCreateUserId());
				entity.setEmail(json.getEmail());
				entity.setEndDate(json.getEndDate());
				entity.setFirstName(json.getFirstName());
				entity.setFlagActive(json.isFlagActive());
				entity.setFlagTemp(json.getFlagTemp());
				entity.setLastName(json.getLastName());
				entity.setModifiedDate(json.getModifiedDate());
				entity.setModifiedUserId(json.getModifiedUserId());
				entity.setStartDate(json.getStartDate());
				entity.setTenantId(json.getTenantId());
				entity.setTitle(json.getTitle());
				entity.setUserId(json.getUserId());
				entity.setVAT(json.getVAT());
				entity.setToken(json.getToken());
				// save
				dao.createOrUpdate(entity);
			}
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
