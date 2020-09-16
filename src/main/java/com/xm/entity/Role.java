package com.xm.entity;

/**
 * @author lz
 * @date 2020/9/15 - 15:39
 * @function
 */
public class Role {

    private Integer id;
    private String rolename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Role() {
    }

    public Role(Integer id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}