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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Address generated by hbm2java
 */
@Entity
@Table(name="address"
    ,catalog="ghecommerceappdb"
)
public class Address  implements java.io.Serializable {


     private Integer id;
     private Country country;
     private Customer customer;
     private String addressLine1;
     private String addressLine2;
     private String city;
     private String stateOrDistrict;
     private String postalCode;
     private Date createdAt;
     private Date updatedAt;
     private Set customerOrders = new HashSet(0);

    public Address() {
    }

	
    public Address(Country country, Customer customer, String addressLine1, String city, String stateOrDistrict, String postalCode, Date createdAt, Date updatedAt) {
        this.country = country;
        this.customer = customer;
        this.addressLine1 = addressLine1;
        this.city = city;
        this.stateOrDistrict = stateOrDistrict;
        this.postalCode = postalCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Address(Country country, Customer customer, String addressLine1, String addressLine2, String city, String stateOrDistrict, String postalCode, Date createdAt, Date updatedAt, Set customerOrders) {
       this.country = country;
       this.customer = customer;
       this.addressLine1 = addressLine1;
       this.addressLine2 = addressLine2;
       this.city = city;
       this.stateOrDistrict = stateOrDistrict;
       this.postalCode = postalCode;
       this.createdAt = createdAt;
       this.updatedAt = updatedAt;
       this.customerOrders = customerOrders;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="country_id", nullable=false)
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false)
    public Customer getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
    @Column(name="address_line1", nullable=false)
    public String getAddressLine1() {
        return this.addressLine1;
    }
    
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    
    @Column(name="address_line2")
    public String getAddressLine2() {
        return this.addressLine2;
    }
    
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    
    @Column(name="city", nullable=false, length=100)
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    
    @Column(name="state_or_district", nullable=false, length=100)
    public String getStateOrDistrict() {
        return this.stateOrDistrict;
    }
    
    public void setStateOrDistrict(String stateOrDistrict) {
        this.stateOrDistrict = stateOrDistrict;
    }

    
    @Column(name="postal_code", nullable=false, length=20)
    public String getPostalCode() {
        return this.postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="address")
    public Set getCustomerOrders() {
        return this.customerOrders;
    }
    
    public void setCustomerOrders(Set customerOrders) {
        this.customerOrders = customerOrders;
    }




}


