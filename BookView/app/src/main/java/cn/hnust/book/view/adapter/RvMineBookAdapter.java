package cn.hnust.book.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.hnust.book.R;
import cn.hnust.book.bean.MineBookBean;

/**
 * Created by tjouyang on 2018/4/18.
 *
 * @author tjouyang
 */
public class RvMineBookAdapter extends RecyclerView.Adapter<RvMineBookAdapter.ViewHolder> {

    public interface OnPublishClickListener {
        void onBtnClick(int position);

        void onReturnClick(int position);
    }


    private List<MineBookBean.MineBookData> datas;
    private OnPublishClickListener listener;
    private int btnText;

    public RvMineBookAdapter(List<MineBookBean.MineBookData> mBooks, OnPublishClickListener listener) {
        this(mBooks, listener, R.string.btn_publish);
    }

    public RvMineBookAdapter(List<MineBookBean.MineBookData> mBooks, OnPublishClickListener listener, int btnText) {
        this.datas = mBooks;
        this.listener = listener;
        this.btnText = btnText;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MineBookBean.MineBookData item = datas.get(position);
        holder.bindValues(item, holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvBookName;
        TextView tvBookStatus;
        Button btn, btn2;

        ViewHolder(View itemView) {
            super(itemView);
            tvBookName = (TextView) itemView.findViewById(R.id.tv_item_mine_book_name);
            tvBookStatus = (TextView) itemView.findViewById(R.id.tv_item_mine_book_status);
            btn = (Button) itemView.findViewById(R.id.btn_item_mine_book);
            btn2 = (Button) itemView.findViewById(R.id.btn_item_return_book);
            if (btnText == R.string.btn_borrow) {
                btn2.setVisibility(View.GONE);
            }
        }

        void bindValues(MineBookBean.MineBookData item, final int position) {
            tvBookName.setText(item.getBookName());
            tvBookStatus.setText("" + item.getStatus() + "");
            btn.setText(btnText);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onBtnClick(position);
                    }
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onReturnClick(position);
                    }
                }
            });
        }
    }
}
