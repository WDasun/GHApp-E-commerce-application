package model;
// Generated Oct 22, 2024 5:07:01 PM by Hibernate Tools 4.3.1


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

/**
 * ShippingType generated by hbm2java
 */
@Entity
@Table(name="shipping_type"
    ,catalog="ghecommerceappdb"
)
public class ShippingType  implements java.io.Serializable {


     private Integer id;
     private String name;
     private double price;
     private Set customerOrders = new HashSet(0);

    public ShippingType() {
    }

	
    public ShippingType(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public ShippingType(String name, double price, Set customerOrders) {
       this.name = name;
       this.price = price;
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

    
    @Column(name="name", nullable=false, length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="price", nullable=false, precision=22, scale=0)
    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="shippingType")
    public Set getCustomerOrders() {
        return this.customerOrders;
    }
    
    public void setCustomerOrders(Set customerOrders) {
        this.customerOrders = customerOrders;
    }




}


