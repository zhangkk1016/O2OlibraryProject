package cn.hnust.book.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.hnust.book.R;
import cn.hnust.book.bean.FindBookBean.FindBookData;

/**
 * Created by tjouyang on 2018/4/18.
 *
 * @author tjouyang
 */
public class RvMarketAdapter extends RecyclerView.Adapter<RvMarketAdapter.ViewHolder> {
    public interface OnOperateClickListener {
        void onOperateClickListener(int position);
    }

    private List<FindBookData> datas;
    private OnOperateClickListener listener;

    public RvMarketAdapter(List<FindBookData> datas, OnOperateClickListener listener) {
        this.datas = datas;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_research_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FindBookData data = datas.get(position);
        holder.bindValues(data, position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBookName;
        TextView tvBookStatus;
        TextView tvBookLocation;
        Button btnOperate;

        ViewHolder(View itemView) {
            super(itemView);
            tvBookName = (TextView) itemView.findViewById(R.id.tv_item_research_book_name);
            tvBookStatus = (TextView) itemView.findViewById(R.id.tv_item_research_book_status);
            tvBookLocation = (TextView) itemView.findViewById(R.id.tv_item_research_book_location);
            btnOperate = (Button) itemView.findViewById(R.id.btn_item_research_book_operate);
        }

        void bindValues(FindBookData data, final int position) {
            tvBookName.setText(data.getBookId() + data.getBookName());
            tvBookStatus.setText("" + data.getStatus() + "");
            tvBookLocation.setText(data.getAddress());
            btnOperate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onOperateClickListener(position);
                    }
                }
            });
        }
    }
}
