package com.aspirity.viperlistexample.presentation.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.aspirity.viperlistexample.R;
import com.aspirity.viperlistexample.presentation.BaseActivity;
import com.aspirity.viperlistexample.presentation.Layout;
import com.aspirity.viperlistexample.presentation.main.MainActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.Bind;
import carbon.widget.ImageView;
import carbon.widget.ProgressBar;

/**
 * Created by namtarr on 05.09.16.
 */

@Layout(R.layout.activity_details)
public class DetailsActivity extends BaseActivity {

    @Bind(R.id.details_image)
    ImageView imageView;

    @Bind(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String url = intent.getStringExtra(MainActivity.IMAGE_URL);
        String title = intent.getStringExtra(MainActivity.IMAGE_TITLE);
        if (title != null)
            getSupportActionBar().setTitle(title);
        if (url != null) {
            Glide.with(this).load(url).listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(DetailsActivity.this, getString(R.string.error), Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                               boolean isFromMemoryCache, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(imageView);
        }
    }


}
