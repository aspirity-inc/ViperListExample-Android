package com.aspirity.viperlistexample.presentation.injection;


import com.aspirity.viperlistexample.data.DataProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by namtarr on 04.04.16.
 */
@Module
public class DataModule {

    @Singleton
    @Provides
    public DataProvider provideDataProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://demo6609798.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(DataProvider.class);
    }
}
