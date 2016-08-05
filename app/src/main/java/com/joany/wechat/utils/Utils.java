package com.joany.wechat.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.joany.wechat.R;

/**
 * Created by joany on 2016/8/2.
 */
public class Utils {

    public static void showLongToast(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String text) {
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }

    public static void finish(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public static void utilStartActivity(Activity activity,Class<?> clazz) {
        Intent intent = new Intent(activity,clazz);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    public static void showDialog(Context context){
        final Dialog mDialog = new Dialog(context,R.style.FullScreenDialog);
        mDialog.setContentView(R.layout.dialog_qrcode);
        Bitmap bitmap =  generateQRCode("123456789012345678");
        ImageView qrcodeImg = (ImageView) mDialog.getWindow().getDecorView()
                .findViewById(R.id.qrcodeImg);
        qrcodeImg.setImageBitmap(bitmap);
        mDialog.getWindow().getDecorView().findViewById(R.id.qrcodell)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mDialog.isShowing()){
                            mDialog.dismiss();
                        }
                    }
                });
        mDialog.show();
    }

    public static Bitmap generateQRCode(String string) {

        if(string == null || "".equals(string) || string.length() < 1) {
            return null;
        }

        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(string, BarcodeFormat.QR_CODE,500,500);

            //下面横向逐个扫描生成二维码的图片，生成二维码
            int qrH = bitMatrix.getHeight();
            int qrW = bitMatrix.getWidth();

            int[] pixels = new int[qrW * qrH];

            for(int y = 0; y < qrH; y++) {
                for(int x = 0; x < qrW; x++) {
                    if(bitMatrix.get(x,y)){
                        pixels[y*qrW+x] = 0xff000000;
                    } else {
                        pixels[y*qrW+x] = 0xffffffff;
                    }
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(qrW,qrH, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels,0,qrW,0,0,qrW,qrH);
            return bitmap;

        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
