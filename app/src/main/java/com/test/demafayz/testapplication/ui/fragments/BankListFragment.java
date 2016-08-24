package com.test.demafayz.testapplication.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.demafayz.testapplication.R;
import com.test.demafayz.testapplication.utils.AppUtil;
import com.test.demafayz.testapplication.utils.sync.ApiHelper;

/**
 * Created by demafayz on 25.08.16.
 */
public class BankListFragment extends BaseRecyclerFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_bank_list, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void doInBackground(Context context) {
        String result = ApiHelper.getBanks();
        AppUtil.dLog(BankListFragment.class, "result: " + result);
    }

    @Override
    protected void onPostExecute() {

    }

    public static BankListFragment newInstance() {
        BankListFragment fragment = new BankListFragment();
        return fragment;
    }
}
