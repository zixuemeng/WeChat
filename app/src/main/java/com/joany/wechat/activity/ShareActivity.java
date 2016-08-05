package com.joany.wechat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.joany.wechat.R;
import com.joany.wechat.utils.Utils;

/**
 * Created by joany on 2016/8/2.
 */
public class ShareActivity extends Activity implements View.OnClickListener, TextWatcher{

    private ImageView backImg;
    private TextView titleTv;
    private ImageView rightImg;
    private Button rightBtn;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_share);
        initData();
        initView();
    }

    private void initData(){
        backImg = (ImageView) findViewById(R.id.backImg);
        titleTv = (TextView) findViewById(R.id.titleTv);
        rightImg = (ImageView) findViewById(R.id.rightImg);
        rightBtn = (Button) findViewById(R.id.rightBtn);
        editText = (EditText) findViewById(R.id.editText);
    }

    private void initView(){
        titleTv.setVisibility(View.GONE);
        rightImg.setVisibility(View.GONE);
        rightBtn.setVisibility(View.VISIBLE);
        backImg.setOnClickListener(this);
        rightBtn.getBackground().setAlpha((int) (255 * 0.2f));
        //检测文本内容变化
        editText.addTextChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backImg:
                Utils.finish(this);
                break;
            case R.id.rightBtn:
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.d("zixuemeng","onTextChanged");
        if(TextUtils.isEmpty(charSequence)){
            rightBtn.getBackground().setAlpha((int) (255*0.2f));
            rightBtn.setOnClickListener(null);
        } else {
            rightBtn.getBackground().setAlpha(255);
            rightBtn.setOnClickListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
