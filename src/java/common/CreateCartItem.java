/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import dao.ItemDAO;
import dao.PromotionDAO;
import dto.CartItem;
import java.util.Date;
import java.util.Set;
import model.ProductItem;
import model.Promotion;
import model.ShoppingCartItem;

/**
 *
 * @author ASUS
 */
public class CreateCartItem {

    private ProductItem productItem;
    private int qty;
    private boolean Selected;
    private int shoppingCartItemId;

    public CreateCartItem(ShoppingCartItem shoppingCartItem) {
        this.productItem = shoppingCartItem.getProductItem();
        this.qty = shoppingCartItem.getQty();
        this.Selected = shoppingCartItem.isSelected();
        this.shoppingCartItemId = shoppingCartItem.getId();
    }
    
    public CreateCartItem(ProductItem productItem, int qty) {
        this.productItem = productItem;
        this.qty = qty;
    }

    public CartItem createCartItem() throws Exception {

        CartItem cItem = new CartItem();
        
        // Search promotions in the item and setPromotion
        Promotion relatedPromotion = new ItemDAO().getRelatedPromotion(productItem.getId());  

        // Add details to the cartItem                              
        cItem.setProductItem(productItem);
        cItem.setQty(qty);
        cItem.setPromotion(relatedPromotion);
        cItem.setSelected(Selected);
        cItem.setShoppingCartItemId(shoppingCartItemId);

        return cItem;

    }

}
