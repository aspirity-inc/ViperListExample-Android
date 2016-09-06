package com.aspirity.viperlistexample.presentation.main;

import android.util.Log;

import com.aspirity.viperlistexample.data.Item;
import com.aspirity.viperlistexample.data.ResponseItem;
import com.aspirity.viperlistexample.domain.GetItemsInteractor;
import com.aspirity.viperlistexample.presentation.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by namtarr on 05.09.16.
 */

public class ListPresenter extends BasePresenter<ListView, MainRouter> {

    private final GetItemsInteractor interactor;
    private int page = 1;
    private int pages;
    private boolean isLoading = false;

    @Inject
    public ListPresenter(GetItemsInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onStart() {
        if (page == 1)
            getItems();
    }

    private void getItems() {
        isLoading = true;
        interactor.execute(page, new Subscriber<ResponseItem>() {
            @Override
            public void onCompleted() {
                isLoading = false;
            }

            @Override
            public void onError(Throwable e) {
                getView().showError(e.getLocalizedMessage());
            }

            @Override
            public void onNext(ResponseItem items) {
                getView().setItems(items.getList(), (page == 1));
                pages = items.getPager().getPages();
            }
        });
    }

    @Override
    public void onStop() {
        interactor.unsubscribe();
    }

    public void onItemClicked(Item item) {
        getRouter().showFullSizeImage(item.getUrl(), item.getName());
    }

    public void refresh() {
        page = 1;
        getItems();
    }

    public void loadMoreItems() {
        if (!isLoading && page < pages) {
            page++;
            getItems();
            Log.d("MORE", String.valueOf(page));
        }
    }
}
