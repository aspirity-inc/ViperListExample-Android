package com.aspirity.viperlistexample.presentation.details;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.aspirity.viperlistexample.R;
import com.aspirity.viperlistexample.presentation.Layout;
import com.aspirity.viperlistexample.presentation.main.MainActivity;

import butterknife.Bind;

/**
 * Created by namtarr on 16.11.16.
 */
@Layout(value = R.layout.fragment_details, isDialog = true)
public class TabletDetailsFragment extends BaseDetailsFragment {

    private String title;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public static TabletDetailsFragment newInstance(String title, String url) {
        BaseDetailsFragment f = BaseDetailsFragment.newInstance(new TabletDetailsFragment(), url);
        f.getArguments().putString(MainActivity.IMAGE_TITLE, title);
        return (TabletDetailsFragment) f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(MainActivity.IMAGE_TITLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        int width = getResources().getDimensionPixelSize(R.dimen.description_dialog_width);
        Window w = getDialog().getWindow();
        w.setLayout(width, width);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (title != null) {
            toolbar.setTitle(title);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_close_light);
        toolbar.setNavigationOnClickListener(v -> dismiss());
    }
}
