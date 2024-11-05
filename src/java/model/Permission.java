package model;
// Generated Oct 22, 2024 5:07:01 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Permission generated by hbm2java
 */
@Entity
@Table(name="permission"
    ,catalog="ghecommerceappdb"
)
public class Permission  implements java.io.Serializable {


     private String permissionId;
     private String permissionName;
     private String permissionDescription;

    public Permission() {
    }

    public Permission(String permissionId, String permissionName, String permissionDescription) {
       this.permissionId = permissionId;
       this.permissionName = permissionName;
       this.permissionDescription = permissionDescription;
    }
   
     @Id 

    
    @Column(name="permission_id", unique=true, nullable=false, length=20)
    public String getPermissionId() {
        return this.permissionId;
    }
    
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    
    @Column(name="permission_name", nullable=false, length=20)
    public String getPermissionName() {
        return this.permissionName;
    }
    
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    
    @Column(name="permission_description", nullable=false, length=500)
    public String getPermissionDescription() {
        return this.permissionDescription;
    }
    
    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }




}

