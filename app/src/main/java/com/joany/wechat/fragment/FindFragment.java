package com.joany.wechat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joany.wechat.R;
import com.joany.wechat.activity.PengYouQuanActivity;
import com.joany.wechat.utils.Utils;

/**
 * Created by joany on 2016/7/28.
 */
public class FindFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.pengyouquan).setOnClickListener(this);
        view.findViewById(R.id.scan).setOnClickListener(this);
        view.findViewById(R.id.yaoyiyao).setOnClickListener(this);
        view.findViewById(R.id.nearby).setOnClickListener(this);
        view.findViewById(R.id.shop).setOnClickListener(this);
        view.findViewById(R.id.game).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pengyouquan:
                Utils.utilStartActivity(getActivity(), PengYouQuanActivity.class);
                break;
            case R.id.scan:
                break;
            case R.id.yaoyiyao:
                break;
            case R.id.nearby:
                break;
            case R.id.shop:
                break;
            case R.id.game:
                break;
            default:
                break;
        }
    }
}
