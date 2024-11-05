package model;

import java.io.Serializable;

public class ProductConfiguration implements Serializable {
    private ProductItem productItem;
    private VariationOption variationOption;

    // Getters and setters
    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public VariationOption getVariationOption() {
        return variationOption;
    }

    public void setVariationOption(VariationOption variationOption) {
        this.variationOption = variationOption;
    }
}
