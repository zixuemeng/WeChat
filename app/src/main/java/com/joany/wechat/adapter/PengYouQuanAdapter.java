package com.joany.wechat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.joany.wechat.R;

/**
 * Created by joany on 2016/8/2.
 */
public class PengYouQuanAdapter extends BaseAdapter{
    private Context context;

    public PengYouQuanAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.pengyouquan_list_item,
                    viewGroup,false);
        }
        return view;
    }
}
