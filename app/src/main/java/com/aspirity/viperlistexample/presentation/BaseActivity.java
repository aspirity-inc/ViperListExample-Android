package com.aspirity.viperlistexample.presentation;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aspirity.viperlistexample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by namtarr on 26.04.16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        Class aClass = getClass();
        if (!aClass.isAnnotationPresent(Layout.class)) {
            throw new IllegalArgumentException("No @Layout annotation found on activity" + aClass.getSimpleName());
        }
        Layout layout = (Layout) aClass.getAnnotation(Layout.class);
        if (layout.isDialog()) {
            throw new IllegalArgumentException("Activity should not be marked as dialog.");
        }
        setContentView(layout.value());
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int flag = getWindow().getDecorView().getSystemUiVisibility();
            getWindow().getDecorView().setSystemUiVisibility(flag | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        getWindow().setBackgroundDrawable(null);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        try {
            android.widget.TextView titleTextView = (android.widget.TextView) toolbar.getChildAt(0);
            if (titleTextView == null) return;
            titleTextView.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf"));
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected final void displayFragment(BaseFragment fragment, @IdRes int container, boolean skipIfExists) {
        if (skipIfExists && getFragmentManager()
                .findFragmentByTag(fragment.getClass().getSimpleName()) != null) return;
        getFragmentManager()
                .beginTransaction()
                .replace(container, fragment, fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }
}
