package com.prime.cnk.data.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.StringDef;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yepyaesonetun on 6/26/18.
 **/
@Entity(tableName = "newProducts")
public class NewProductVO {

    @PrimaryKey
    @SerializedName("product-id")
    int productId;

    @SerializedName("product-image")
    String productImage;

    @SerializedName("product-title")
    String productTitle;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
