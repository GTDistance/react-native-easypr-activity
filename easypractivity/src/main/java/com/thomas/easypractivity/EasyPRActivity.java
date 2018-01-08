package com.thomas.easypractivity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fosung.libeasypr.view.EasyPRPreSurfaceView;
import com.fosung.libeasypr.view.EasyPRPreView;

/**
 * 车牌识别页面
 */
public class EasyPRActivity extends Activity {
    private EasyPRPreView easyPRPreView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
        } else {
            permissionGranted();
        }
//        initData();
    }

    private void permissionGranted() {
        initData();
    }

    private void permissionDenied() {
        Toast.makeText(this, "请赋予本程序拍照权限！", Toast.LENGTH_LONG)
                .show();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 100 && permissions != null && grantResults != null && permissions.length > 0 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionGranted();
            } else {
                permissionDenied();
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (easyPRPreView != null) {
            easyPRPreView.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (easyPRPreView != null) {
            easyPRPreView.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (easyPRPreView != null) {
            easyPRPreView.onDestroy();
        }
    }

    private void initData() {
        easyPRPreView = (EasyPRPreView) findViewById(R.id.preSurfaceView);
//        easyPRPreView.setFocusable(true);

        final Button btnCamera = (Button) findViewById(R.id.btnShutter);
        final TextView tvRecogResult = (TextView) findViewById(R.id.lblRecogResult);
        final ImageView iv = (ImageView) findViewById(R.id.iv);


        easyPRPreView
                .setRecognizedListener(new EasyPRPreSurfaceView.OnRecognizedListener() {
                    @Override
                    public void onRecognized(final String text) {
                        if (text == null || text.equals("0")) {
                            Toast.makeText(EasyPRActivity.this, "换个姿势试试", Toast.LENGTH_SHORT)
                                 .show();
                        } else {
                            tvRecogResult.setText(text);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    NativeCallback.getInstance().getCallback().invoke(text);
                                    EasyPRActivity.this.finish();
                                }
                            }).start();

                        }
                    }
                })
                .setPictureTakenListener(new EasyPRPreSurfaceView.OnPictureTakenListener() {
                    @Override
                    public void onPictureTaken(String[] files) {
                        //                              Bitmap map = BitmapUtil.decodeBitmap(path);
                        //                              iv.setImageBitmap(map);
                    }
                });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyPRPreView.recognize();
            }
        });
    }
}
