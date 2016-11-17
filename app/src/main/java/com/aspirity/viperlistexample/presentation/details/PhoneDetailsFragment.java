package com.aspirity.viperlistexample.presentation.details;


import com.aspirity.viperlistexample.R;
import com.aspirity.viperlistexample.presentation.Layout;

/**
 * Created by namtarr on 16.11.16.
 */

@Layout(value = R.layout.fragment_details)
public class PhoneDetailsFragment extends BaseDetailsFragment {

    public static PhoneDetailsFragment newInstance(String url) {
        return (PhoneDetailsFragment) BaseDetailsFragment.newInstance(new PhoneDetailsFragment(), url);
    }
}
