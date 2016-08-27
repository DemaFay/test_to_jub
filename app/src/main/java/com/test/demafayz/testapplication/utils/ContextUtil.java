package com.test.demafayz.testapplication.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.test.demafayz.testapplication.R;
import com.test.demafayz.testapplication.ui.activitys.MainActivity;
import com.test.demafayz.testapplication.ui.dialogs.ErrorDialogFragment;

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
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        ft.replace(fragmentContainerId, fragment);
        if (useBackStack) ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
        return true;
    }

    public static MaterialDialog showErrorDialog(Context context, String title, String text, String positiveTitle, String negativeTitle, final ErrorDialogFragment.OnDialogClickListener listener) {


        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(text)
                .positiveText(positiveTitle)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        listener.onPositiveClickListener();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        listener.onNegativeClickListener();
                    }
                })
                .negativeText(negativeTitle)
                .show();
        return materialDialog;
    }

    public static MaterialDialog showProgressDialog(Context context) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .canceledOnTouchOutside(false)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .show();
        return materialDialog;
    }

    @Deprecated
    public static ErrorDialogFragment showErrorDialog(FragmentManager fm, String title, String text, String positiveTitle, String negativeTitle, final ErrorDialogFragment.OnDialogClickListener listener) {
        ErrorDialogFragment dialogFragment = ErrorDialogFragment.newInstance(title, text, positiveTitle, negativeTitle);
        dialogFragment.setOnDialogClickListener(listener);
        dialogFragment.show(fm, dialogFragment.getClass().getSimpleName());
        return dialogFragment;
    }
}
