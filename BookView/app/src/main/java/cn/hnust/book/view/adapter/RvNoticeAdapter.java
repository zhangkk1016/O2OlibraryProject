package cn.hnust.book.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.hnust.book.R;
import cn.hnust.book.bean.NoticeBean;

/**
 * Created by tjouyang on 2018/4/19.
 *
 * @author tjouyang
 */

public class RvNoticeAdapter extends RecyclerView.Adapter<RvNoticeAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener;

    private List<NoticeBean.NoticeData> datas;


    public RvNoticeAdapter(List<NoticeBean.NoticeData> datas, OnItemClickListener listener) {
        this.datas = datas;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NoticeBean.NoticeData data = datas.get(position);
        holder.bindValue(data, position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemNoticePosition;
        TextView tvItemNoticeStatus;
        ImageView ivItemNoticeFlag;

        ViewHolder(View itemView) {
            super(itemView);
            tvItemNoticePosition = (TextView) itemView.findViewById(R.id.tv_item_notice_position);
            tvItemNoticeStatus = (TextView) itemView.findViewById(R.id.tv_item_notice_status);
            ivItemNoticeFlag = (ImageView) itemView.findViewById(R.id.iv_item_notice_flag);
        }

        void bindValue(NoticeBean.NoticeData data, final int position) {
            int status = data.getStatus();
            if ((status & 1) == 0) {
                ivItemNoticeFlag.setImageResource(R.mipmap.back);
            } else {
                ivItemNoticeFlag.setImageResource(R.mipmap.ic_launcher);
            }
            tvItemNoticePosition.setText("消息" + (position + 1) + "");
            tvItemNoticeStatus.setText("" + status + "");
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
