package com.prime.cnk.mvp.presenters;

import android.arch.lifecycle.LiveData;

import com.prime.cnk.data.models.NewProductModel;
import com.prime.cnk.data.vo.NewProductVO;
import com.prime.cnk.delegates.NewProductDetailDelegate;
import com.prime.cnk.mvp.views.NewProductDetailView;

/**
 * Created by yepyaesonetun on 6/29/18.
 **/

public class NewProductDetailPresenter extends BasePresenter<NewProductDetailView> implements NewProductDetailDelegate {

    @Override
    public void initPresenter(NewProductDetailView mView) {
        super.initPresenter(mView);
    }

    public LiveData<NewProductVO> onUiReady(int id) {
        return NewProductModel.getInstance().getNewProductByIdLD(id);
    }

    @Override
    public void onTapProductImage(String url) {
        mView.displayProductImage(url);
    }

    @Override
    public void onTapInfo(NewProductVO newProductVO) {
        mView.displayInfo(newProductVO);
    }

    @Override
    public void onTapColorChooser() {
        mView.displayColorChooser();
    }


}
