package com.xm.form;

/**
 * @author lz
 * @date 2020/9/21 - 10:45
 * @function
 */
public class ProductTypeForm {

    private Integer typeId;
    private String typeName;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public ProductTypeForm() {
    }

    public ProductTypeForm(Integer typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }
}
