package com.aspirity.viperlistexample.presentation;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.aspirity.viperlistexample.presentation.injection.AppComponent;

import butterknife.ButterKnife;

/**
 * Created by namtarr on 26.04.16.
 */
public abstract class BaseFragment extends DialogFragment {

    private boolean isDialog = false;

    @LayoutRes
    private int layoutId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class aClass = getClass();
        if (!aClass.isAnnotationPresent(Layout.class))
            throw new IllegalArgumentException("Layout annotation is missing.");
        Layout layout = (Layout) aClass.getAnnotation(Layout.class);
        layoutId = layout.value();
        isDialog = layout.isDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (isDialog)
            return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(layoutId, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!isDialog)
            return null;
        final View view = View.inflate(getActivity(), layoutId, null);
        ButterKnife.bind(this, view);
        return new MaterialDialog.Builder(getActivity())
                .customView(view, false)
                .build();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inject();
        //noinspection unchecked
        getPresenter().setView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        //noinspection unchecked
        getPresenter().setRouter(null);
        getPresenter().setView(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @NonNull
    protected abstract BasePresenter getPresenter();

    protected abstract void inject();

    protected AppComponent getAppComponent() {
        return ((Application) getActivity().getApplication()).getComponent();
    }
}
