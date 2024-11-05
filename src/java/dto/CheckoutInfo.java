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
public class CheckoutInfo {
    private int cardTypeId;
    private String cardNumber;
    private String nameOnCard;
    private String expYear;
    private String expMonth;
    private int cvv;
    private int saveCardInfo;
    private int shippingTypeId;
    private int addressId;

   

    @Override
    public String toString() {
        return "checkoutInfo{" + "cardTypeId=" + cardTypeId + ", cardNumber=" + cardNumber + ", nameOnCard=" + nameOnCard + ", expYear=" + expYear + ", expMonth=" + expMonth + ", cvv=" + cvv + ", saveCardInfo=" + saveCardInfo + ", shippingTypeId=" + shippingTypeId + ", addressId=" + addressId + '}';
    }

    public int getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(int cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getSaveCardInfo() {
        return saveCardInfo;
    }

    public void setSaveCardInfo(int saveCardInfo) {
        this.saveCardInfo = saveCardInfo;
    }

    public int getShippingTypeId() {
        return shippingTypeId;
    }

    public void setShippingTypeId(int shippingTypeId) {
        this.shippingTypeId = shippingTypeId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

   
    
}
