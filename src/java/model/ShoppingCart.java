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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * ShoppingCart generated by hbm2java
 */
@Entity
@Table(name="shopping_cart"
    ,catalog="ghecommerceappdb"
    , uniqueConstraints = @UniqueConstraint(columnNames="customer_id") 
)
public class ShoppingCart  implements java.io.Serializable {


     private Integer id;
     private Customer customer;
     private Set shoppingCartItems = new HashSet(0);

    public ShoppingCart() {
    }

	
    public ShoppingCart(Customer customer) {
        this.customer = customer;
    }
    public ShoppingCart(Customer customer, Set shoppingCartItems) {
       this.customer = customer;
       this.shoppingCartItems = shoppingCartItems;
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
    @JoinColumn(name="customer_id", unique=true, nullable=false)
    public Customer getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="shoppingCart")
    public Set getShoppingCartItems() {
        return this.shoppingCartItems;
    }
    
    public void setShoppingCartItems(Set shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }




}


