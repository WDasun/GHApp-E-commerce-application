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
import javax.persistence.UniqueConstraint;

/**
 * CreditOrDebitCardType generated by hbm2java
 */
@Entity
@Table(name="credit_or_debit_card_type"
    ,catalog="ghecommerceappdb"
    , uniqueConstraints = @UniqueConstraint(columnNames="value") 
)
public class CreditOrDebitCardType  implements java.io.Serializable {


     private Integer id;
     private String value;
     private Set cardDetailses = new HashSet(0);

    public CreditOrDebitCardType() {
    }

	
    public CreditOrDebitCardType(String value) {
        this.value = value;
    }
    public CreditOrDebitCardType(String value, Set cardDetailses) {
       this.value = value;
       this.cardDetailses = cardDetailses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="value", unique=true, nullable=false, length=45)
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="creditOrDebitCardType")
    public Set getCardDetailses() {
        return this.cardDetailses;
    }
    
    public void setCardDetailses(Set cardDetailses) {
        this.cardDetailses = cardDetailses;
    }




}


