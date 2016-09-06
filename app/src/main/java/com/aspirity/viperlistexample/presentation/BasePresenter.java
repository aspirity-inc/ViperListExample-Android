package com.aspirity.viperlistexample.presentation;

/**
 * Created by namtarr on 26.04.16.
 */
public abstract class BasePresenter<View, Router> {

    private View view;
    private Router router;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public abstract void onStart();
    public abstract void onStop();
}
