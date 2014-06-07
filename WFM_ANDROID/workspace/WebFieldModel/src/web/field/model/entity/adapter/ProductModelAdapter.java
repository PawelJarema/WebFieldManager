package web.field.model.entity.adapter;

import java.util.Hashtable;

import web.field.model.entity.*;

public class ProductModelAdapter {
	private Product product;
	
	private Hashtable<String, Double> maxPromos;
	
	public ProductModelAdapter(Product product){
		this.product = product;
	}
	
	public String getProductBrandDescription() {
		return product.getProductBrand().getBrandDescription();
	}


	public String getProductCategoryDescription() {
		return product.getProductCategory().getCategoryDescription();
	}


	public String getProductFamilyDescription() {
		return product.getProductFamily().getFamilyDescription();
	}



	public String getProductManufacturerDescription() {
		return product.getProductManufacturer().getManufacturerDescription();
	}


	public int getProductId() {
		return product.getProductId();
	}



	public int getTenantId() {
		return product.getProductId();
	}


	public String getCode() {
		return product.getCode();
	}


	public String getProductDescription() {
		return product.getProductDescription();
	}


	public double getPrice() {
		return product.getPrice();
	}



	public boolean isFlagActive() {
		return product.isFlagActive();
	}

	public Hashtable<String, Double> getMaxPromos() {
		return maxPromos;
	}

	public void setMaxPromos(Hashtable<String, Double> maxPromos) {
		this.maxPromos = maxPromos;
	}

	
}
