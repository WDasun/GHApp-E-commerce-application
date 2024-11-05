/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author ASUS
 */
public class OrderLineInfo {
    private String invoice;
    private String itemName;
    private String qty;
    private String price;
    private String productItemId;

    public OrderLineInfo() {
    }

    public OrderLineInfo(String invoice, String itemName, String qty, String price) {
        this.invoice = invoice;
        this.itemName = itemName;
        this.qty = qty;
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderLineInfo{" + "invoice=" + invoice + ", itemName=" + itemName + ", qty=" + qty + ", price=" + price + '}';
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(String productItemId) {
        this.productItemId = productItemId;
    }

    
    
}
