package com.aspirity.viperlistexample.presentation.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aspirity.viperlistexample.R;
import com.aspirity.viperlistexample.presentation.BaseFragment;
import com.aspirity.viperlistexample.presentation.BasePresenter;
import com.aspirity.viperlistexample.presentation.main.MainActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.Bind;
import carbon.widget.ImageView;
import carbon.widget.ProgressBar;

/**
 * Created by namtarr on 15.11.16.
 */

public abstract class BaseDetailsFragment extends BaseFragment {

    protected String url;

    @Bind(R.id.details_image)
    ImageView imageView;

    @Bind(R.id.progress)
    ProgressBar progressBar;

    public static BaseDetailsFragment newInstance(BaseDetailsFragment f, String url) {
        Bundle b = new Bundle();
        b.putString(MainActivity.IMAGE_URL, url);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString(MainActivity.IMAGE_URL);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (url != null) {
            Glide.with(this).load(url).listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    onError();
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                               boolean isFromMemoryCache, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(imageView);
        } else {
            onError();
        }
        return view;
    }

    void onError() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return new BasePresenter() {
            @Override
            public void onStart() {
                //STUB
            }

            @Override
            public void onStop() {
                //STUB
            }
        };
    }

    @Override
    protected void inject() {
    }
}
