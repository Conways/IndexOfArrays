package com.conways.indexofarrays;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IndexView.TouchCallBack {

    public static final String[] arrays = {"阿宝", "波波", "曹县", "倾听", "鹅肝", "发财", "倾听", "哈哈", "爱情",
            "n", "咔咔", "乐视", "倾听", "尼玛", "咪咪", "噢噢", "倾听", "倾听", "倾听", "死了", "李四", "倾听", "李四",
            "傻吊", "网达标", "哈哈", "啊啊"};

    private static final String TAG = "app";
    private RecyclerView rv;
    private IndexView iv;
    private MyAdapter myAdapter;
    private List<DataEntity> list;
    private TextView tvTag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        update();
    }

    private void init() {
        list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            DataEntity dataEntit = new DataEntity();
            dataEntit.setUserName(arrays[i]);
            dataEntit.setNickName(arrays[i]);
            list.add(dataEntit);
        }
        rv = (RecyclerView) this.findViewById(R.id.rv);
        rv.addItemDecoration(new LinearItemDecoration(LinearItemDecoration.VERTICAL_LIST, this));
        iv = (IndexView) this.findViewById(R.id.iv);
        rv.setLayoutManager(new MLinearLayoutManager(this));
        iv.setTouchCallBack(this);
        tvTag = (TextView) this.findViewById(R.id.tag);
    }

    private void update() {
        Collections.sort(list);
        if (myAdapter == null) {
            myAdapter = new MyAdapter(list, this);
            rv.setAdapter(myAdapter);
        } else {
            myAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void callBack(String str, boolean show) {
        if (show) {
            if (tvTag.getVisibility() == View.GONE)
                tvTag.setVisibility(View.VISIBLE);
            tvTag.setText(str);
            int position=((MLinearLayoutManager)rv.getLayoutManager()).findFirstVisibleItemPosition();
            for (int i = 0; i <list.size() ; i++) {
                if (str.equals(list.get(i).getPinYinNickName())&&position!=i){
                    ((MLinearLayoutManager) rv.getLayoutManager()).scrollToPositionWithOffset(i,0);
                    break;
                }
            }

        } else {
            tvTag.setVisibility(View.GONE);
        }
//        if (str.equals("D")) {
//            int position=((MLinearLayoutManager)rv.getLayoutManager()).findFirstVisibleItemPosition();
//            if (!arrays[position].equals("日子")){
//            }
//        }
    }


}
