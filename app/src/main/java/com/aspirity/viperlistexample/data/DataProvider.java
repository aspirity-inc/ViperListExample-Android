package com.aspirity.viperlistexample.data;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by namtarr on 05.09.16.
 */

public interface DataProvider {

    @GET("images")
    Observable<ResponseItem> getItemsForPage(@Query("page") int page);
}
