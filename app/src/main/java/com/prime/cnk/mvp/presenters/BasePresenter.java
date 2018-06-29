package com.prime.cnk.mvp.presenters;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.prime.cnk.mvp.views.BaseView;

/**
 * Created by yepyaesonetun on 6/29/18.
 **/

public abstract class BasePresenter<T extends BaseView> extends ViewModel {

    protected T mView;
    protected MutableLiveData<String> mErrorLD;

    public void initPresenter(final T mView) {
        this.mView = mView;
        mErrorLD = new MutableLiveData<>();
    }

    public LiveData<String> getErrorLD() {
        return mErrorLD;
    }

}
