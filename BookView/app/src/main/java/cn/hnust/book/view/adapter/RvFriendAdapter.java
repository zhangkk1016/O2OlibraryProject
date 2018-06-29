package cn.hnust.book.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.hnust.book.R;
import cn.hnust.book.bean.FriendBean;

/**
 * Created by tjouyang on 2018/4/21.
 *
 * @author tjouyang
 */

public class RvFriendAdapter extends RecyclerView.Adapter<RvFriendAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private List<FriendBean.FriendData> datas;
    private OnItemClickListener listener;

    public RvFriendAdapter(List<FriendBean.FriendData> datas, OnItemClickListener listener) {
        this.datas = datas;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friends, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FriendBean.FriendData data = datas.get(position);
        holder.bindValues(data, position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFriendName;

        ViewHolder(View itemView) {
            super(itemView);
            tvFriendName = (TextView) itemView.findViewById(R.id.tv_item_friend);
        }

        void bindValues(FriendBean.FriendData data, final int position) {
            tvFriendName.setText(data.getFriendName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

}
