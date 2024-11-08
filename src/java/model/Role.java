package model;
// Generated Oct 22, 2024 5:07:01 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Role generated by hbm2java
 */
@Entity
@Table(name="role"
    ,catalog="ghecommerceappdb"
)
public class Role  implements java.io.Serializable {


     private String roleId;
     private String roleName;
     private String roleDescription;
     private Set firstLevelAccesses = new HashSet(0);
     private Set systemUsers = new HashSet(0);

    public Role() {
    }

	
    public Role(String roleId, String roleName, String roleDescription) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }
    public Role(String roleId, String roleName, String roleDescription, Set firstLevelAccesses, Set systemUsers) {
       this.roleId = roleId;
       this.roleName = roleName;
       this.roleDescription = roleDescription;
       this.firstLevelAccesses = firstLevelAccesses;
       this.systemUsers = systemUsers;
    }
   
     @Id 

    
    @Column(name="role_id", unique=true, nullable=false, length=20)
    public String getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    
    @Column(name="role_name", nullable=false, length=45)
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    
    @Column(name="role_description", nullable=false)
    public String getRoleDescription() {
        return this.roleDescription;
    }
    
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="role")
    public Set getFirstLevelAccesses() {
        return this.firstLevelAccesses;
    }
    
    public void setFirstLevelAccesses(Set firstLevelAccesses) {
        this.firstLevelAccesses = firstLevelAccesses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="role")
    public Set getSystemUsers() {
        return this.systemUsers;
    }
    
    public void setSystemUsers(Set systemUsers) {
        this.systemUsers = systemUsers;
    }




}


