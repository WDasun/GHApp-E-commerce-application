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
public class OrderDetail {
    private String invoiceId;
    private String orderId;
    private String orderTotal;
    private String orderedDate;
    private String status;
    private String shippingType;
    private String shippingAddress;
    private String fname;
    private String lname;
    private String customerId;

    public OrderDetail(String invoiceId, String orderId, String orderTotal, String orderedDate, String status, String shippingType, String shippingAddress) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.orderTotal = orderTotal;
        this.orderedDate = orderedDate;
        this.status = status;
        this.shippingType = shippingType;
        this.shippingAddress = shippingAddress;
    }

    public OrderDetail() {
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "invoiceId=" + invoiceId + ", orderId=" + orderId + ", orderTotal=" + orderTotal + ", orderedDate=" + orderedDate + ", status=" + status + ", shippingType=" + shippingType + ", shippingAddress=" + shippingAddress + '}';
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
