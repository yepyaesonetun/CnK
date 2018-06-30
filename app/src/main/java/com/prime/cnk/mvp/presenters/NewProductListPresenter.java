package com.prime.cnk.mvp.presenters;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.prime.cnk.data.models.NewProductModel;
import com.prime.cnk.data.vo.NewProductVO;
import com.prime.cnk.delegates.NewProductItemDelegate;
import com.prime.cnk.mvp.views.NewProductListView;

import java.util.List;

/**
 * Created by yepyaesonetun on 6/29/18.
 **/

public class NewProductListPresenter extends BasePresenter<NewProductListView> implements NewProductItemDelegate {

    private MutableLiveData<List<NewProductVO>> mNewProductListLD;

    @Override
    public void initPresenter(NewProductListView mView) {
        super.initPresenter(mView);
        mNewProductListLD = new MutableLiveData<>();
        loadData(1);
    }

    public void loadData(int pageNo){
        NewProductModel.getInstance().startLoadingNewProducts(pageNo, mNewProductListLD, mErrorLD);
    }

    public LiveData<List<NewProductVO>> getNewProductsLD() {
        return mNewProductListLD;
    }

    @Override
    public void onTapNewProduct(int id) {
        mView.navigateToDetail(id);
    }
}
