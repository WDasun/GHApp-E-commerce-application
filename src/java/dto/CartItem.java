package dto;

import java.io.Serializable;
import model.ProductItem;
import model.Promotion;

/**
 *
 * @author Dasun Wimukthi
 */
public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L; // Add a unique serialVersionUID

    private ProductItem productItem;
    private int qty;
    private int dbCartItemId;
    private Promotion promotion;
    private boolean selected; // Adjusted casing for naming consistency
    private int shoppingCartItemId;

    // Getters and Setters
    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getDbCartItemId() {
        return dbCartItemId;
    }

    public void setDbCartItemId(int dbCartItemId) {
        this.dbCartItemId = dbCartItemId;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getShoppingCartItemId() {
        return shoppingCartItemId;
    }

    public void setShoppingCartItemId(int shoppingCartItemId) {
        this.shoppingCartItemId = shoppingCartItemId;
    }
}
