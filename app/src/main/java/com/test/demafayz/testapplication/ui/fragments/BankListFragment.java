package com.test.demafayz.testapplication.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.demafayz.testapplication.R;
import com.test.demafayz.testapplication.data.BicCode;
import com.test.demafayz.testapplication.database.DBHelper;
import com.test.demafayz.testapplication.ui.adapters.BankListAdapter;
import com.test.demafayz.testapplication.utils.AppUtil;
import com.test.demafayz.testapplication.utils.DataParser;
import com.test.demafayz.testapplication.utils.sync.ApiHelper;

import java.nio.charset.Charset;

import io.realm.Realm;

/**
 * Created by demafayz on 25.08.16.
 */
public class BankListFragment extends BaseRecyclerFragment {

    private BankListAdapter adapter;
    private BicCode bicCode;

    private ViewHolder vh;

    private class ViewHolder {
        public RecyclerView rvBankList;
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
        vh.rvBankList = (RecyclerView) view.findViewById(R.id.rvBankList);
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void doInBackground(Context context) {

        if (DBHelper.bankListIsDownloaded()) {
            String result = ApiHelper.getBanks();
            bicCode = DataParser.parseBanks(result);
            DBHelper.saveBicCode(bicCode);
        } else {
            bicCode = DBHelper.getBanks();
        }
    }

    @Override
    protected void onPostExecute() {
        createNewAdapter();
    }

    private void createNewAdapter() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new BankListAdapter(bicCode.getRecords());
        vh.rvBankList.setLayoutManager(manager);
        vh.rvBankList.setHasFixedSize(true);
        vh.rvBankList.setAdapter(adapter);
    }

    public static BankListFragment newInstance() {
        BankListFragment fragment = new BankListFragment();
        return fragment;
    }
}
