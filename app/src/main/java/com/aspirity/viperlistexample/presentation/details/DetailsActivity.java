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

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String url = intent.getStringExtra(MainActivity.IMAGE_URL);
        String title = intent.getStringExtra(MainActivity.IMAGE_TITLE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
        toolbar.setNavigationOnClickListener(v -> finish());

        displayFragment(PhoneDetailsFragment.newInstance(url), R.id.frame_container, true);
    }
}
