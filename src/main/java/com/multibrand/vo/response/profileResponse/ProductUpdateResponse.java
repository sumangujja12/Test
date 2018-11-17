package com.multibrand.vo.response.profileResponse;

import com.multibrand.vo.response.GenericResponse;

public class ProductUpdateResponse extends GenericResponse{
	private ProductDO[] productDO;

	public ProductDO[] getProductDO() {
		return productDO;
	}

	public void setProductDO(ProductDO[] productDO) {
		this.productDO = productDO;
	}

}
