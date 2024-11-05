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
public class RoleInfo {
    private String roleId;
    private String roleName;
    private String roleDescription;
    private boolean f1;
    private boolean f2;
    private boolean f3;
    private boolean f4;
    private boolean f5;
    private boolean f6;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public boolean isF1() {
        return f1;
    }

    public void setF1(boolean f1) {
        this.f1 = f1;
    }

    public boolean isF2() {
        return f2;
    }

    public void setF2(boolean f2) {
        this.f2 = f2;
    }

    public boolean isF3() {
        return f3;
    }

    public void setF3(boolean f3) {
        this.f3 = f3;
    }

    public boolean isF4() {
        return f4;
    }

    public void setF4(boolean f4) {
        this.f4 = f4;
    }

    public boolean isF5() {
        return f5;
    }

    public void setF5(boolean f5) {
        this.f5 = f5;
    }

    public boolean isF6() {
        return f6;
    }

    public void setF6(boolean f6) {
        this.f6 = f6;
    }

    @Override
    public String toString() {
        return "RoleInfo{" + "roleId=" + roleId + ", roleName=" + roleName + ", roleDescription=" + roleDescription + ", f1=" + f1 + ", f2=" + f2 + ", f3=" + f3 + ", f4=" + f4 + ", f5=" + f5 + ", f6=" + f6 + '}';
    }
    
    
    
}
