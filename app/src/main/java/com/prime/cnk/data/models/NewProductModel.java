package com.prime.cnk.data.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.prime.cnk.data.vo.NewProductVO;
import com.prime.cnk.network.responses.GetNewProductResponse;
import com.prime.cnk.utils.AppConstants;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yepyaesonetun on 6/29/18.
 **/

public class NewProductModel extends BaseModel {

    private static NewProductModel mSObjInstance;
    private int mmNewProductPageIndex = 1;

    private NewProductModel(Context context) {
        super(context);
    }

    public static void initProductModel(Context context) {
        mSObjInstance = new NewProductModel(context);
    }

    public static NewProductModel getInstance() {
        if (mSObjInstance == null) {
            throw new RuntimeException("NewProductModel is being invoked before initializing.");
        }
        return mSObjInstance;
    }

    public void startLoadingNewProducts(MutableLiveData<List<NewProductVO>> newProductListLD,
                                        MutableLiveData<String> errorLD) {

        theAPI.loadNewProduct(mmNewProductPageIndex, AppConstants.ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetNewProductResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetNewProductResponse getNewProductResponse) {
                        if (getNewProductResponse != null
                                && getNewProductResponse.getNewProductList().size() > 0) {

                            // save to persistence
                            persistNewProductList(getNewProductResponse.getNewProductList());

                            // set response data to lD
                            newProductListLD.setValue(getNewProductResponse.getNewProductList());

                            mmNewProductPageIndex = getNewProductResponse.getPageNo() + 1;

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // set error to LD
                        errorLD.setValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void persistNewProductList(List<NewProductVO> newProductList) {

        theDB.newProductDao().insertNewProduct(newProductList);
    }

    public LiveData<NewProductVO> getNewProductByIdLD(int id) {
        final MutableLiveData<NewProductVO> newProductLD = new MutableLiveData<>();
        theDB.newProductDao().getNewProductByIdLD(id).observeForever(newProductVO -> {
            if (newProductVO != null) {
                newProductLD.setValue(newProductVO);
            }
        });
        return newProductLD;
    }
}
