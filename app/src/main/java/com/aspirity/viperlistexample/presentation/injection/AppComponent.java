package com.aspirity.viperlistexample.presentation.injection;


import com.aspirity.viperlistexample.presentation.main.ListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by namtarr on 04.04.16.
 */
@Singleton
@Component(modules = {DataModule.class, DomainModule.class})
public interface AppComponent {

    void inject(ListFragment listFragment);
}
