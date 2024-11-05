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
 * OrderStatus generated by hbm2java
 */
@Entity
@Table(name="order_status"
    ,catalog="ghecommerceappdb"
)
public class OrderStatus  implements java.io.Serializable {


     private Integer id;
     private String value;
     private Set customerOrders = new HashSet(0);

    public OrderStatus() {
    }

	
    public OrderStatus(String value) {
        this.value = value;
    }
    public OrderStatus(String value, Set customerOrders) {
       this.value = value;
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

    
    @Column(name="value", nullable=false, length=20)
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="orderStatus")
    public Set getCustomerOrders() {
        return this.customerOrders;
    }
    
    public void setCustomerOrders(Set customerOrders) {
        this.customerOrders = customerOrders;
    }




}

