package com.ijse.onlineshoppingsys.dto;

public class ItemCategoryDTO {
    private int cat_id;
    private String category;


    public ItemCategoryDTO(int cat_id, String category) {
        this.cat_id = cat_id;
        this.category = category;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
