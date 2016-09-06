package com.aspirity.viperlistexample.domain;

import com.aspirity.viperlistexample.data.DataProvider;
import com.aspirity.viperlistexample.data.Item;
import com.aspirity.viperlistexample.data.ResponseItem;
import com.aspirity.viperlistexample.presentation.injection.DomainModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by namtarr on 05.09.16.
 */

public class GetItemsInteractor extends Interactor <ResponseItem, Integer> {

    private final DataProvider provider;

    @Inject
    public GetItemsInteractor(@Named(DomainModule.JOB) Scheduler jobScheduler,
                              @Named(DomainModule.UI) Scheduler uiScheduler,
                              DataProvider provider) {
        super(jobScheduler, uiScheduler);
        this.provider = provider;
    }

    @Override
    protected Observable<ResponseItem> buildObservable(Integer page) {
        return provider.getItemsForPage(page);
    }
}
