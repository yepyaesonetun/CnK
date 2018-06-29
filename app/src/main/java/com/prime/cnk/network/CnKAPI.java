package com.prime.cnk.network;


import com.prime.cnk.network.responses.GetNewProductResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by yepyaesonetun on 6/29/18.
 **/

public interface CnKAPI {

    @FormUrlEncoded
    @POST("padc-5/ck/getNewProducts.php")
    Observable<GetNewProductResponse> loadNewProduct(
            @Field("page") int pageIndex,
            @Field("access_token") String accessToken);

}
