package model;
// Generated Oct 22, 2024 5:07:01 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table(name="customer"
    ,catalog="ghecommerceappdb"
    , uniqueConstraints = @UniqueConstraint(columnNames="email") 
)
public class Customer  implements java.io.Serializable {


     private Integer customerId;
     private String fname;
     private String lname;
     private String email;
     private String password;
     private String cntNumber;
     private Date dob;
     private Date createdAt;
     private Date updatedAt;
     private boolean status;
     private Set reviews = new HashSet(0);
     private Set customerOrders = new HashSet(0);
     private Set shoppingCarts = new HashSet(0);
     private Set cardDetailses = new HashSet(0);
     private Set recoveries = new HashSet(0);
     private Set addresses = new HashSet(0);
     private Set wishLists = new HashSet(0);

    public Customer() {
    }

	
    public Customer(String fname, String lname, String email, String password, String cntNumber, Date dob, Date createdAt, Date updatedAt, boolean status) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.cntNumber = cntNumber;
        this.dob = dob;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
    public Customer(String fname, String lname, String email, String password, String cntNumber, Date dob, Date createdAt, Date updatedAt, boolean status, Set reviews, Set customerOrders, Set shoppingCarts, Set cardDetailses, Set recoveries, Set addresses, Set wishLists) {
       this.fname = fname;
       this.lname = lname;
       this.email = email;
       this.password = password;
       this.cntNumber = cntNumber;
       this.dob = dob;
       this.createdAt = createdAt;
       this.updatedAt = updatedAt;
       this.status = status;
       this.reviews = reviews;
       this.customerOrders = customerOrders;
       this.shoppingCarts = shoppingCarts;
       this.cardDetailses = cardDetailses;
       this.recoveries = recoveries;
       this.addresses = addresses;
       this.wishLists = wishLists;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="customer_id", unique=true, nullable=false)
    public Integer getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    
    @Column(name="fname", nullable=false, length=50)
    public String getFname() {
        return this.fname;
    }
    
    public void setFname(String fname) {
        this.fname = fname;
    }

    
    @Column(name="lname", nullable=false, length=50)
    public String getLname() {
        return this.lname;
    }
    
    public void setLname(String lname) {
        this.lname = lname;
    }

    
    @Column(name="email", unique=true, nullable=false, length=100)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="password", nullable=false)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="cnt_number", nullable=false, length=20)
    public String getCntNumber() {
        return this.cntNumber;
    }
    
    public void setCntNumber(String cntNumber) {
        this.cntNumber = cntNumber;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="dob", nullable=false, length=10)
    public Date getDob() {
        return this.dob;
    }
    
    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable=false, length=19)
    public Date getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at", nullable=false, length=19)
    public Date getUpdatedAt() {
        return this.updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    
    @Column(name="status", nullable=false)
    public boolean isStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    public Set getReviews() {
        return this.reviews;
    }
    
    public void setReviews(Set reviews) {
        this.reviews = reviews;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    public Set getCustomerOrders() {
        return this.customerOrders;
    }
    
    public void setCustomerOrders(Set customerOrders) {
        this.customerOrders = customerOrders;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    public Set getShoppingCarts() {
        return this.shoppingCarts;
    }
    
    public void setShoppingCarts(Set shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    public Set getCardDetailses() {
        return this.cardDetailses;
    }
    
    public void setCardDetailses(Set cardDetailses) {
        this.cardDetailses = cardDetailses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    public Set getRecoveries() {
        return this.recoveries;
    }
    
    public void setRecoveries(Set recoveries) {
        this.recoveries = recoveries;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    public Set getAddresses() {
        return this.addresses;
    }
    
    public void setAddresses(Set addresses) {
        this.addresses = addresses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="customer")
    public Set getWishLists() {
        return this.wishLists;
    }
    
    public void setWishLists(Set wishLists) {
        this.wishLists = wishLists;
    }




}


