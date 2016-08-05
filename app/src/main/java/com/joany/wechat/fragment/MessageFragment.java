package com.joany.wechat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.joany.wechat.R;

/**
 * Created by joany on 2016/7/28.
 */
public class MessageFragment extends Fragment {

    private ListView listView;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message,container,false);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        ListView lv = (ListView) view.findViewById(R.id.listview);
        return view;
    }
}
