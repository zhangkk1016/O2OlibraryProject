package cn.hnust.book.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.hnust.book.R;
import cn.hnust.book.bean.FriendBean;
import cn.hnust.book.view.NewFriendListActivity;

/**
 * Created by tjouyang on 2018/4/25.
 *
 * @author tjouyang
 */
public class RvNewFriendAdapter extends RecyclerView.Adapter<RvNewFriendAdapter.ViewHolder> {

    public interface OnBtnClickListener {
        // 拒绝
        void onRefuse(int position);

        // 同意
        void onAgree(int position);
    }

    private List<FriendBean.FriendData> datas;
    private OnBtnClickListener listener;


    public RvNewFriendAdapter(List<FriendBean.FriendData> mData, OnBtnClickListener listener) {
        this.datas = mData;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_friend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindValues(datas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameId;
        Button btnRefuse, btnAgree;

        ViewHolder(View itemView) {
            super(itemView);
            tvNameId = (TextView) itemView.findViewById(R.id.tv_item_new_friend_name_id);
            btnAgree = (Button) itemView.findViewById(R.id.btn_item_new_friend_agree);
            btnRefuse = (Button) itemView.findViewById(R.id.btn_item_new_friend_refuse);
        }

        void bindValues(FriendBean.FriendData data, final int position) {

            tvNameId.setText(data.getFriendName());

            btnRefuse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onRefuse(position);
                    }
                }
            });
            btnAgree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onAgree(position);
                    }
                }
            });
        }
    }
}
