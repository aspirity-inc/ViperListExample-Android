package com.aspirity.viperlistexample.presentation;

import com.aspirity.viperlistexample.presentation.injection.AppComponent;
import com.aspirity.viperlistexample.presentation.injection.DaggerAppComponent;
import com.aspirity.viperlistexample.presentation.injection.DataModule;
import com.aspirity.viperlistexample.presentation.injection.DomainModule;


/**
 * Created by namtarr on 04.04.16.
 */
public class Application extends android.app.Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().dataModule(new DataModule()).domainModule(new DomainModule()).build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
