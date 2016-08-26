package com.test.demafayz.testapplication.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.demafayz.testapplication.R;
import com.test.demafayz.testapplication.data.Record;
import com.test.demafayz.testapplication.utils.DateUtil;

import java.util.List;

/**
 * Created by demafayz on 26.08.16.
 */
public class BankListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Record> records;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvTitle;
        private TextView tvBic;
        private TextView tvDu;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvBic = (TextView) itemView.findViewById(R.id.tvBic);
            tvDu = (TextView) itemView.findViewById(R.id.tvDu);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onRecyclerItemClickListener != null)
                onRecyclerItemClickListener.onRecyclerItemClickListener(view);
        }
    }

    public BankListAdapter(List<Record> records) {
        this.records = records;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_back_list_item, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        vh.tvTitle.setText(records.get(position).getShortName());
        vh.tvDu.setText(DateUtil.longDateToString(records.get(position).getDu(), null));
        vh.tvBic.setText("BIC: " + records.get(position).getBic());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public interface OnRecyclerItemClickListener {
        public void onRecyclerItemClickListener(View itemView);
    }
}
