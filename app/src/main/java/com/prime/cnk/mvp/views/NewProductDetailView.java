package com.prime.cnk.mvp.views;

import com.prime.cnk.data.vo.NewProductVO;

/**
 * Created by yepyaesonetun on 6/28/18.
 **/

public interface NewProductDetailView extends BaseView {

    void displayNewProductData(NewProductVO newProductVO);

    void displayInfo(NewProductVO newProductVO);

    void displayProductImage(String url);

    void displayColorChooser();

}
