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
public class CardDetailCustomerProfile {

    private int cardDetailsId;
    private String cardType;
    private String cardNumber;
    private String nameOnCard;

    @Override
    public String toString() {
        return "CardDetailCustomerProfile{" + "cardDetailsId=" + cardDetailsId + ", cardType=" + cardType + ", cardNumber=" + cardNumber + ", nameOnCard=" + nameOnCard + ", expYear=" + expYear + ", expMonth=" + expMonth + '}';
    }
    private String expYear;
    private String expMonth;

    public CardDetailCustomerProfile(int cardDetailsId, String cardType, String cardNumber, String nameOnCard, String expYear, String expMonth) {
        this.cardDetailsId = cardDetailsId;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.expYear = expYear;
        this.expMonth = expMonth;
    }

    public int getCardDetailsId() {
        return cardDetailsId;
    }

    public void setCardDetailsId(int cardDetailsId) {
        this.cardDetailsId = cardDetailsId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
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


}
