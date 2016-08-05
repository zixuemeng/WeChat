package com.joany.wechat.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.joany.wechat.R;

/**
 * Created by joany on 2016/7/28.
 */
public class IconTextView extends View {

    private final int DEFAULT_COLOR = 0x7B7877;
    private final int DEFAULT_PRESSED_COLOR = 0x45C01A;
    private final int DEFAULT_TEXT_SIZE = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,12,getResources().getDisplayMetrics());

    private Bitmap normalIcon;
    private Bitmap pressedIcon;
    private String title;
    private int textColor;
    private int pressedTextColor;
    private int textSize;

    private Paint iconPaint;
    private Rect iconRect;
    private Paint textPaint;
    private Rect textRect;

    private int alpha;


    public IconTextView(Context context) {
        this(context,null);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ar = context.getResources().obtainAttributes(attrs,R.styleable.IconTextView);
        textColor = ar.getColor(R.styleable.IconTextView_textColor, DEFAULT_COLOR);
        pressedTextColor = ar.getColor(R.styleable.IconTextView_pressedTextColor, DEFAULT_PRESSED_COLOR);
        title = ar.getString(R.styleable.IconTextView_text);
        textSize = ar.getDimensionPixelSize(R.styleable.IconTextView_textSize, DEFAULT_TEXT_SIZE);
        normalIcon = ((BitmapDrawable) ar.getDrawable(R.styleable.IconTextView_normalIcon)).getBitmap();
        pressedIcon = ((BitmapDrawable) ar.getDrawable(R.styleable.IconTextView_pressedIcon)).getBitmap();
        ar.recycle();

        textPaint = new Paint();
        textRect = new Rect();
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        textPaint.getTextBounds(title, 0, title.length(), textRect);
    }

    public void setViewAlpha(int alpha) {
        this.alpha = alpha;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iconW = (int) Math.min(normalIcon.getWidth(), TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,30,getResources().getDisplayMetrics()));
        int iconH = (int) Math.min(normalIcon.getHeight(), TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,30,getResources().getDisplayMetrics()));
        int left = (getMeasuredWidth() - iconW)/2;
        int top = (getMeasuredHeight() - iconH)/2 - 5;
        iconRect = new Rect(left,top,left+iconW,top+iconH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBitmap(canvas, alpha);
        drawText(canvas, alpha);
    }

    private void drawBitmap(Canvas canvas, int alpha) {
        //关闭硬件加速，否则设置xfermode后可能透明变黑色
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        iconPaint = new Paint();
        iconPaint.setAntiAlias(true);
        iconPaint.setDither(true);

        iconPaint.setAlpha(255 - alpha);
        canvas.drawBitmap(normalIcon,null,iconRect,iconPaint);

        if(alpha < 85) {
        } else if(alpha < 170) {
            iconPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            iconPaint.setAlpha(alpha);
            canvas.drawBitmap(pressedIcon,null,iconRect,iconPaint);
            iconPaint.setXfermode(null);
        } else {
            iconPaint.setAlpha(alpha);
            canvas.drawBitmap(pressedIcon,null,iconRect,iconPaint);
        }

        //直接叠加两图，类似钉钉
        /*iconPaint.setAlpha(alpha);
        canvas.drawBitmap(pressedIcon,null,iconRect,iconPaint);*/
    }

    private void drawText(Canvas canvas, int alpha) {
        textPaint.setColor(textColor);
        textPaint.setAlpha(255 - alpha);
        //textPaint设置align为center时使用下面的x取值，反之使用上面的
        //int x = (int) ((getMeasuredWidth() - textPaint.measureText(title))/2);
        int x = getMeasuredWidth()/2;
        int y = iconRect.bottom + textRect.height();
        canvas.drawText(title,x,y,textPaint);

        textPaint.setColor(pressedTextColor);
        textPaint.setAlpha(alpha);
        canvas.drawText(title, x, y, textPaint);
    }

    private static final String STATUS_ALPHA = "status_alpha";
    private static final String INSTANCE_STATUS = "instance_status";

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS,super.onSaveInstanceState());
        bundle.putInt(STATUS_ALPHA,alpha);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            alpha = bundle.getInt(STATUS_ALPHA);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
