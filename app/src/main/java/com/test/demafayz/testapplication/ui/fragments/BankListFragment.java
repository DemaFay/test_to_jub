package com.test.demafayz.testapplication.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.demafayz.testapplication.R;
import com.test.demafayz.testapplication.data.BicCode;
import com.test.demafayz.testapplication.database.DBHelper;
import com.test.demafayz.testapplication.ui.adapters.BankListAdapter;
import com.test.demafayz.testapplication.ui.dialogs.ErrorDialogFragment;
import com.test.demafayz.testapplication.utils.AppUtil;
import com.test.demafayz.testapplication.utils.ContextUtil;
import com.test.demafayz.testapplication.utils.DataParser;
import com.test.demafayz.testapplication.utils.sync.ApiHelper;

/**
 * Created by demafayz on 25.08.16.
 */
public class BankListFragment extends BaseFragment implements BankListAdapter.OnRecyclerItemClickListener,ErrorDialogFragment.OnDialogClickListener {

    private BankListAdapter adapter;
    private BicCode bicCode;

    private ViewHolder vh;
    private ErrorDialogFragment dialogFragment;
    private int clickPosition;

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

    @Override
    protected String setFragmentTitle() {
        return getString(R.string.bank_list_fragment__title);
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
        if (!DBHelper.bankListIsDownloaded()) {
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
        adapter.setOnRecyclerItemClickListener(this);
        vh.rvBankList.setLayoutManager(manager);
        vh.rvBankList.setHasFixedSize(true);
        vh.rvBankList.setAdapter(adapter);
        vh.rvBankList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onRecyclerItemClickListener(View itemView) {
        clickPosition = vh.rvBankList.getChildAdapterPosition(itemView);
        showBankInfo(clickPosition);
    }

    private void showBankInfo(int position) {
        if (AppUtil.isNetworkAvailable(getContext())) {
            openBankInfo(position);
        } else {
            if (bicCode.getRecord(position).isDownloaded()) {
                openBankInfo(position);
            } else {
                dialogFragment = ContextUtil.showErrorDialog(getChildFragmentManager(),
                        getString(R.string.error_dialog_internet_error),
                        null,
                        getString(R.string.error_dialog_internet_pos),
                        getString(R.string.error_dialog_internet_neg),
                        this);
            }
        }
    }

    @Override
    public void onPositiveClickListener() {
        if (dialogFragment != null)
        dialogFragment.dismiss();
        showBankInfo(clickPosition);
    }

    @Override
    public void onNegativeClickListener() {
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }

    private void openBankInfo(int position) {
        BankInfoFragment fragment = BankInfoFragment.newInstance(bicCode.getRecord(position).getBic());
        ContextUtil.showFragment(getActivity(), fragment, true);
    }

    public static BankListFragment newInstance() {
        BankListFragment fragment = new BankListFragment();
        return fragment;
    }
}
