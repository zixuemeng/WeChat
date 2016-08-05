package com.joany.wechat.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by joany on 2016/7/29.
 */
public class SideBar extends View{

    private char[] sideBarChar = { '#', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z'};

    private ListView listView;
    private int itemH = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            15,getContext().getResources().getDisplayMetrics());

    public SideBar(Context context) {
        this(context,null);
    }

    public SideBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(0xff000000);
        paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
                getContext().getResources().getDisplayMetrics()));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
        float x = getMeasuredWidth()/2;
        for(int i=0; i<sideBarChar.length;i++) {
            canvas.drawText(String.valueOf(sideBarChar[i]),x,itemH*(i+1),paint);
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                //getY:y in view, getRawY:y in screen
                int y = (int) event.getY();
                int index = y/itemH;
                if(index > sideBarChar.length-1) {
                    index = sideBarChar.length-1;
                } else if(index < 0){
                    index = 0;
                }
                listView.setSelection(index);
                break;
        }

        return true;
    }
}
