package com.aspirity.viperlistexample.presentation.main;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by namtarr on 04.08.15.
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpanCount;
    private int mSpacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing) {
        this.mSpanCount = spanCount;
        this.mSpacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % mSpanCount; // item column

        outRect.left = mSpacing - column * mSpacing / mSpanCount; // mSpacing - column * ((1f / mSpanCount) * mSpacing)
        outRect.right = (column + 1) * mSpacing / mSpanCount; // (column + 1) * ((1f / mSpanCount) * mSpacing)

        if (position < mSpanCount) { // top edge
            outRect.top = mSpacing;
        }
        outRect.bottom = mSpacing; // item bottom
    }
}
