package com.aspirity.viperlistexample.presentation.main;

import com.aspirity.viperlistexample.data.Item;
import com.aspirity.viperlistexample.presentation.BaseView;

import java.util.List;

/**
 * Created by namtarr on 05.09.16.
 */

public interface ListView extends BaseView {

    void setItems(List<Item> items, boolean refresh);
}
