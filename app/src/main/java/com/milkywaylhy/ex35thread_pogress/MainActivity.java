package com.milkywaylhy.ex35thread_pogress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    int gauge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {
        if (dialog != null) return;

        //wheel type prograss dialog
        dialog = new ProgressDialog(this);
        dialog.setTitle("Wheel Dialog");
        dialog.setMessage("downloding...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        //3초후에 다이얼로그 종료
        dialog.setMax(100);

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        dialog.setProgress(gauge);
        Thread t= new Thread(){
            @Override
            public void run() {
                gauge=0;
                while (gauge<=100){
                    gauge++;
                    dialog.setProgress(gauge);

                    //0.05ch eorl
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
                dialog=null;
            }
        };
        t.start();
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    public void clickBtn2(View view) {
        if (dialog != null) return;
        //bar type progress dialog
        dialog = new ProgressDialog(this);
        dialog.setTitle("막대바 다이얼로그");
        dialog.setMessage("다운로드중");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();


    }

    //멤버변수 위치
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            dialog.dismiss();
            dialog = null;
        }
    };
}
