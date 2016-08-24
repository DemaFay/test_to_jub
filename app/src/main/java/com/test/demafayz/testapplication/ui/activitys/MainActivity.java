package com.test.demafayz.testapplication.ui.activitys;

import android.os.Bundle;

import com.test.demafayz.testapplication.R;
import com.test.demafayz.testapplication.ui.fragments.BankListFragment;
import com.test.demafayz.testapplication.utils.ContextUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showBankListFragment();
    }

    private void showBankListFragment() {
        BankListFragment fragment = BankListFragment.newInstance();
        ContextUtil.showFragment(this, fragment, false);
    }
}
