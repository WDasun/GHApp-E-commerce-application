
package dto;

/**
 *
 * @author Dasun Wimukthi
 */
public class CustomerInfo {
    private int customerId;
    private String fname;
    private String lname;
    private String email;
    private String cntNumber;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCntNumber() {
        return cntNumber;
    }

    public void setCntNumber(String cntNumber) {
        this.cntNumber = cntNumber;
    }
}
