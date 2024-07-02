package com.chao.etax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public class LoadingActivity extends AppCompatActivity {

    private MyHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        mHandler = new MyHandler(this);
        mHandler.sendEmptyMessageDelayed(0, 3000L);
    }

    private static class MyHandler extends Handler {

        private WeakReference<LoadingActivity> mActivity;

        private MyHandler(LoadingActivity activity) {
            super();
            this.mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (mActivity.get() == null) {
                return;
            }
            LoadingActivity activity = mActivity.get();
            activity.startActivity(new Intent(activity, HomeActivity.class));
        }
    }
}