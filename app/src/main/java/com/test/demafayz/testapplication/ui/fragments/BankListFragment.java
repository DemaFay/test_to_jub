package com.test.demafayz.testapplication.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.demafayz.testapplication.R;
import com.test.demafayz.testapplication.data.BicCode;
import com.test.demafayz.testapplication.utils.AppUtil;
import com.test.demafayz.testapplication.utils.DataParser;
import com.test.demafayz.testapplication.utils.sync.ApiHelper;

import java.nio.charset.Charset;

/**
 * Created by demafayz on 25.08.16.
 */
public class BankListFragment extends BaseRecyclerFragment {

    private BicCode bicCode;

    private ViewHolder vh;

    private class ViewHolder {
        public TextView tvTest;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_bank_list, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateViewHolder(view);
    }

    private void populateViewHolder(View view) {
        vh = new ViewHolder();
        vh.tvTest = (TextView) view.findViewById(R.id.tvTest);
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void doInBackground(Context context) {
        String result = ApiHelper.getBanks();
        bicCode = DataParser.parseBanks(result);
    }

    @Override
    protected void onPostExecute() {
        String text = bicCode.getRecord(0).getShortName();
        vh.tvTest.setText(text);
    }

    public static BankListFragment newInstance() {
        BankListFragment fragment = new BankListFragment();
        return fragment;
    }
}
