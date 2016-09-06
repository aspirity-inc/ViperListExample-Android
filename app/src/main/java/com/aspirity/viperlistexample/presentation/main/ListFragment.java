package com.aspirity.viperlistexample.presentation.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aspirity.viperlistexample.R;
import com.aspirity.viperlistexample.data.Item;
import com.aspirity.viperlistexample.presentation.BaseFragment;
import com.aspirity.viperlistexample.presentation.BasePresenter;
import com.aspirity.viperlistexample.presentation.Layout;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import carbon.widget.ProgressBar;

/**
 * Created by namtarr on 05.09.16.
 */

@Layout(R.layout.fragment_list)
public class ListFragment extends BaseFragment implements ListView {

    @Inject
    ListPresenter presenter;

    @Bind(R.id.list_recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.progress)
    ProgressBar progressBar;

    @Bind(R.id.list_refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private ListAdapter adapter;
    private LinearLayoutManager layoutManager;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        adapter = new ListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics())));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisible == totalItemCount - 3) {
                    presenter.loadMoreItems();
                }
            }
        });
        adapter.setOnItemClickedListener(position -> presenter.onItemClicked(adapter.getItem(position)));
        refreshLayout.setOnRefreshListener(() -> presenter.refresh());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        //noinspection unchecked
        getPresenter().setRouter(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity) getActivity();
        activity.updateToolbar(getTitle());
    }

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject() {
        getAppComponent().inject(this);
    }

    @Override
    public void setItems(List<Item> items, boolean refresh) {
        adapter.setItems(items, refresh);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String localizedMessage) {
        Toast.makeText(getActivity(), localizedMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getTitle() {
        return getString(R.string.list_fragment_title);
    }
}
