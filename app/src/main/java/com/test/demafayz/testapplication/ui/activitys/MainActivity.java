package com.test.demafayz.testapplication.ui.activitys;

import android.os.Bundle;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.test.demafayz.testapplication.R;
import com.test.demafayz.testapplication.database.DBHelper;
import com.test.demafayz.testapplication.ui.dialogs.ErrorDialogFragment;
import com.test.demafayz.testapplication.ui.fragments.BankListFragment;
import com.test.demafayz.testapplication.utils.AppUtil;
import com.test.demafayz.testapplication.utils.ContextUtil;

public class MainActivity extends BaseActivity implements ErrorDialogFragment.OnDialogClickListener {

    private MaterialDialog dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startApp();
    }

    private void startApp() {
        if (AppUtil.isNetworkAvailable(this)) {
            showBankListFragment();
        } else {
            if (DBHelper.bankListIsDownloaded()) {
                showBankListFragment();
            } else {
                dialogFragment = ContextUtil.showErrorDialog(this,
                        getString(R.string.error_dialog_internet_error),
                        getString(R.string.error_dialog_internet_desc),
                        getString(R.string.error_dialog_internet_pos),
                        getString(R.string.error_dialog_internet_neg),
                        this);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

    @Override
    public void onPositiveClickListener() {
        dialogFragment.dismiss();
        startApp();
    }

    @Override
    public void onNegativeClickListener() {
        dialogFragment.dismiss();
        onBackPressed();
    }

    private void showBankListFragment() {
        BankListFragment fragment = BankListFragment.newInstance();
        ContextUtil.showFragment(this, fragment, false);
    }
}
