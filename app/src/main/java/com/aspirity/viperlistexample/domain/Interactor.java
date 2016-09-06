package com.aspirity.viperlistexample.domain;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by namtarr on 05.09.16.
 */

public abstract class Interactor<ResultType, ParameterType> {
    private final CompositeSubscription subscription = new CompositeSubscription();
    protected final Scheduler jobScheduler;
    private final Scheduler uiScheduler;

    protected Interactor(Scheduler jobScheduler, Scheduler uiScheduler) {
        this.jobScheduler = jobScheduler;
        this.uiScheduler = uiScheduler;
    }

    protected abstract Observable<ResultType> buildObservable(ParameterType parameterType);

    public void execute(ParameterType parameter, Subscriber<ResultType> subscriber) {
        subscription.add(buildObservable(parameter)
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler)
                .subscribe(subscriber));
    }

    public void execute(Subscriber<ResultType> subscriber) {
        execute(null, subscriber);
    }

    public void unsubscribe() {
        subscription.clear();
    }
}