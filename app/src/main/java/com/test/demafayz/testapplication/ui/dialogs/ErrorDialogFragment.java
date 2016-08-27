package com.test.demafayz.testapplication.ui.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.test.demafayz.testapplication.R;

public class ErrorDialogFragment extends DialogFragment implements View.OnClickListener {

    private static final String TAG = ErrorDialogFragment.class.getSimpleName();
    private static final String TITLE_TAG = TAG + "_title";
    private static final String DESC_TAG = TAG + "_desc";
    private static final String POS_TAG = TAG + "_pos";
    private static final String NEG_TAG = TAG + "_neg";
    private String title;
    private String desc;
    private String pos;
    private String neg;

    private ViewHolder vh;
    private OnDialogClickListener onDialogClickListener;

    private class ViewHolder {
        public TextView tvTitle;
        public TextView tvDesc;
        public Button btnPositive;
        public Button btnNegative;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.fragment_dialog_edit_text, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateViewHolder(view);
        showData();
    }

    private void showData() {
        vh.tvTitle.setText(title);
        vh.tvDesc.setText(desc);
        if (pos != null)
        vh.btnPositive.setText(pos);
        if (neg != null)
        vh.btnNegative.setText(neg);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE_TAG, title);
        outState.putString(DESC_TAG, desc);
        outState.putString(NEG_TAG, neg);
        outState.putString(POS_TAG, pos);
    }

    private void initData(Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args == null) {
            args = savedInstanceState;
        }
        title = args.getString(TITLE_TAG, "");
        desc = args.getString(DESC_TAG, "");
        pos = args.getString(POS_TAG, null);
        neg = args.getString(NEG_TAG, null);
    }

    private void populateViewHolder(View itemView) {
        vh = new ViewHolder();
        vh.tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
        vh.tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);

        vh.btnNegative = (Button) itemView.findViewById(R.id.btnNegative);
        vh.btnPositive = (Button) itemView.findViewById(R.id.btnPositive);

        vh.btnPositive.setOnClickListener(this);
        vh.btnNegative.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnNegative:
                if (onDialogClickListener != null)
                    onDialogClickListener.onNegativeClickListener();
                break;
            case R.id.btnPositive:
                if (onDialogClickListener != null)
                    onDialogClickListener.onPositiveClickListener();
                break;
        }
    }

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    public interface OnDialogClickListener {

        public void onPositiveClickListener();

        public void onNegativeClickListener();
    }

    public static ErrorDialogFragment newInstance(String title, String desc, String positiveButtonTitle, String negativeButtonTitle) {

        Bundle args = new Bundle();
        args.putString(TITLE_TAG, title);
        args.putString(POS_TAG, positiveButtonTitle);
        args.putString(DESC_TAG, desc);
        args.putString(NEG_TAG, negativeButtonTitle);
        ErrorDialogFragment fragment = new ErrorDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
}