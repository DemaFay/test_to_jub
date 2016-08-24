package com.test.demafayz.testapplication.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.test.demafayz.testapplication.R;
import com.test.demafayz.testapplication.ui.activitys.MainActivity;

/**
 * Created by demafayz on 25.08.16.
 */
public class ContextUtil {

    public static boolean showFragment(FragmentActivity activity, Fragment fragment, boolean useBackStack) {
        int fragmentContainerId = 0;
        if (activity instanceof MainActivity) {
            fragmentContainerId = R.id.rlFragmentContainer;
        }
        if (fragmentContainerId == 0) return false;

        FragmentManager fm;
        FragmentTransaction ft;

        fm = activity.getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(fragmentContainerId, fragment);
        if (useBackStack) ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
        return true;
    }
}
