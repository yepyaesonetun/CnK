package com.prime.cnk.network.responses;

import com.google.gson.annotations.SerializedName;
import com.prime.cnk.data.vo.NewProductVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yepyaesonetun on 6/29/18.
 **/

public class GetNewProductResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private int pageNo;

    @SerializedName("newProducts")
    private List<NewProductVO> newProductList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public int getPageNo() {
        return pageNo;
    }

    public List<NewProductVO> getNewProductList() {
        if (newProductList == null) {
            newProductList = new ArrayList<>();
        }
        return newProductList;
    }
}
