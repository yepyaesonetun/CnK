package com.prime.cnk.root;

import android.app.Application;

import com.prime.cnk.data.models.NewProductModel;

/**
 * Created by yepyaesonetun on 6/29/18.
 **/

public class CnKApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        NewProductModel.initProductModel(getApplicationContext());
    }
}
