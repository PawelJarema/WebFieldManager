package web.field.helpers;

import java.util.ArrayList;
import java.util.List;

import web.field.db.IDBAdapter;
import web.field.model.simple.ProductBrandSimple;
import web.field.model.simple.ProductCategorySimple;
import web.field.model.simple.ProductFamilySimple;
import web.field.model.simple.ProductManufacturerSimple;

public class DataHelpers {
	
	public List<String> getProductManufacturerNames(IDBAdapter db) {
		List<String> names = new ArrayList<String>();
		List<ProductManufacturerSimple> manufacturers = db.listProductManufactures();
		for (ProductManufacturerSimple item : manufacturers)
			names.add(item.getName());
		return names;
	}
	
	public List<String> getProductBrandNames(IDBAdapter db) {
		List<String> names = new ArrayList<String>();
		List<ProductBrandSimple> brands = db.listProductBrands();
		for (ProductBrandSimple item : brands)
			names.add(item.getName());
		return names;
	}
	
	public List<String> getProductFamilyNames(IDBAdapter db) {
		List<String> names = new ArrayList<String>();
		List<ProductFamilySimple> families = db.listProductFamilies();
		for (ProductFamilySimple item : families)
			names.add(item.getName());
		return names;
	}
	
	public List<String> getProductCategoryNames(IDBAdapter db) {
		List<String> names = new ArrayList<String>();
		List<ProductCategorySimple> categories = db.listProductCategories();
		for (ProductCategorySimple item : categories)
			names.add(item.getName());
		return names;
	}
}
