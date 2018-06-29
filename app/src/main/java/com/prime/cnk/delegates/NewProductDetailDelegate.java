package com.prime.cnk.delegates;

import com.prime.cnk.data.vo.NewProductVO;

/**
 * Created by yepyaesonetun on 6/29/18.
 **/

public interface NewProductDetailDelegate {

    void onTapProductImage(String url);

    void onTapInfo(NewProductVO newProductVO);

    void onTapColorChooser();
}
