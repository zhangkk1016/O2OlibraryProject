package cn.hnust.book.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.hnust.book.R;
import cn.hnust.book.bean.FriendBookAllBean;
import cn.hnust.book.constants.Constants;

/**
 * Created by tjouyang on 2018/4/21.
 *
 * @author tjouyang
 */

public class RvFriendBookAllAdapter extends RecyclerView.Adapter<RvFriendBookAllAdapter.ViewHolder> {

    private List<FriendBookAllBean.FriendBookAllData> datas;

    public RvFriendBookAllAdapter(List<FriendBookAllBean.FriendBookAllData> datas) {
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FriendBookAllBean.FriendBookAllData data = datas.get(position);
        holder.bindValues(data, position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFriendBook;

        ViewHolder(View itemView) {
            super(itemView);
            tvFriendBook = (TextView) itemView.findViewById(R.id.tv_item_friend_book);
        }

        void bindValues(FriendBookAllBean.FriendBookAllData data, int position) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(data.getBookId());
            stringBuilder.append(Constants.SPLIT_MIDDLE_LINE);
            stringBuilder.append(data.getBookName());
            stringBuilder.append(Constants.SPLIT_MIDDLE_LINE);
            stringBuilder.append(data.getFriendName());
            stringBuilder.append(Constants.SPLIT_MIDDLE_LINE);
            stringBuilder.append(data.getStatus());
            tvFriendBook.setText(stringBuilder.toString());
        }
    }


}
