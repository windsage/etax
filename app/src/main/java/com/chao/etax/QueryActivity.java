package com.chao.etax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class QueryActivity extends AppCompatActivity {

    private ImageView loading;
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        myHandler = new MyHandler(this);
        Button button = findViewById(R.id.query);
        loading = findViewById(R.id.iv_loading);
        loading.setImageResource(R.drawable.web_loading);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHandler.sendEmptyMessage(0);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loading.setVisibility(View.INVISIBLE);
    }

    private static class MyHandler extends Handler {
        private WeakReference<QueryActivity> mActivity;

        private MyHandler(QueryActivity activity) {
            super();
            this.mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (mActivity.get() == null) return;
            QueryActivity activity = mActivity.get();
            activity.loading.setVisibility(View.VISIBLE);
            Drawable drawable = activity.loading.getDrawable();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                if (drawable instanceof AnimatedImageDrawable) {
                    ((AnimatedImageDrawable) drawable).setRepeatCount(2);
                    ((AnimatedImageDrawable) drawable).start();
                }
            }
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra("year", 2023);
            activity.startActivity(intent);
        }
    }
}