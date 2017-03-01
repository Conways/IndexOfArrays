package com.conways.indexofarrays;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Conways on 2017/2/27.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {

    private List<DataEntity> list;
    private Context context;


    public MyAdapter(List<DataEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_list_value, parent, false));
    }

    @Override
    public void onBindViewHolder(MyAdapter.Holder holder, int position) {
        holder.tv.setText(list.get(position).getNickName());
        String head=list.get(position).getPinYinNickName();
        if (position==0||!list.get(position-1).getPinYinNickName().equals(head)){
            holder.head.setVisibility(View.VISIBLE);
            holder.head.setText(head+"");
        }else{
            holder.head.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class Holder extends RecyclerView.ViewHolder {
        TextView head;
        TextView tv;

        public Holder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            head=(TextView) itemView.findViewById(R.id.head);
        }
    }
}

