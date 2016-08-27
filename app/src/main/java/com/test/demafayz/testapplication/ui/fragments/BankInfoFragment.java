package com.test.demafayz.testapplication.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.demafayz.testapplication.R;
import com.test.demafayz.testapplication.data.BankInfo;
import com.test.demafayz.testapplication.database.DBHelper;
import com.test.demafayz.testapplication.database.data.RealmBankInfo;
import com.test.demafayz.testapplication.utils.DataParser;
import com.test.demafayz.testapplication.utils.DateUtil;
import com.test.demafayz.testapplication.utils.sync.ApiHelper;

import io.realm.Realm;

/**
 * Created by demafayz on 26.08.16.
 */
public class BankInfoFragment extends BaseFragment {

    private static final String TAG = BankInfoFragment.class.getSimpleName();
    private static final String TAG_BIC = TAG + "_bic";
    private String bic;
    private BankInfo bankInfo;

    private ViewHolder vh;

    private class ViewHolder {
        public TextView tvName;
        public TextView tvBic;
        public TextView tvKS;
        public TextView tvTelephone;
        public TextView tvCity;
        public TextView tvAddress;
        public TextView tvDate;
        public LinearLayout llDateContainer;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initData(savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_bank_item, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateViewHolder(view);
    }

    private void populateViewHolder(View view) {
        vh = new ViewHolder();
        vh.tvName = (TextView) view.findViewById(R.id.tvName);
        vh.tvBic = (TextView) view.findViewById(R.id.tvBic);
        vh.tvKS = (TextView) view.findViewById(R.id.tvKS);
        vh.tvTelephone = (TextView) view.findViewById(R.id.tvTelephone);
        vh.tvCity = (TextView) view.findViewById(R.id.tvCity);
        vh.tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        vh.tvDate = (TextView) view.findViewById(R.id.tvDate);
        vh.llDateContainer = (LinearLayout) view.findViewById(R.id.llDateContainer);
    }

    private void initData(Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args == null) {
            args = savedInstanceState;
        }
        bic = args.getString(TAG_BIC);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TAG_BIC, bic);
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void doInBackground(Context context) {
        if (!DBHelper.bankInfoIsDownloaded(bic)) {
            String result = ApiHelper.getBankInfo(bic);
            bankInfo = DataParser.parseBankInfo(result);
            DBHelper.saveBankInfo(bankInfo);
        } else {
            bankInfo = DBHelper.getBankInfo(bic);
        }
    }

    @Override
    protected void onPostExecute() {
        showData();
    }

    private void showData() {
        vh.llDateContainer.setVisibility(View.VISIBLE);
        vh.tvName.setText(bankInfo.getName());
        vh.tvBic.setText(bankInfo.getBic());
        vh.tvKS.setText(bankInfo.getKs());
        vh.tvTelephone.setText(bankInfo.getTelephone());
        vh.tvCity.setText(bankInfo.getCity());
        vh.tvAddress.setText(bankInfo.getAddress());
        vh.tvDate.setText(DateUtil.longDateToString(bankInfo.getUpd(), DateUtil.UI_DATE_FORMAT));
    }

    public static BankInfoFragment newInstance(String bic) {

        Bundle args = new Bundle();
        args.putString(TAG_BIC, bic);
        BankInfoFragment fragment = new BankInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
