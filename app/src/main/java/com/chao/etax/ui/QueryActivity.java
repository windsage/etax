package com.chao.etax.ui;

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
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chao.etax.R;

import java.lang.ref.WeakReference;

public class QueryActivity extends AppCompatActivity {

    private ImageView loading, bgYear;
    private MyHandler myHandler;
    private int year;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        Intent intent = getIntent();
        year = intent.getIntExtra("year", 2025);
        myHandler = new MyHandler(this);
        Button button = findViewById(R.id.query);
//        initYearPicker();

        loading = findViewById(R.id.iv_loading);
        bgYear = findViewById(R.id.iv_year);
        if (year == 2024) {
            bgYear.setImageResource(R.drawable.query_2024);
        } else if (year == 2023) {
            bgYear.setImageResource(R.drawable.query_2023);
        } else if (year == 2025) {
            bgYear.setImageResource(R.drawable.query_2025);
        }
        loading.setImageResource(R.drawable.web_loading);
//        button.setOnClickListener(v -> myHandler.sendEmptyMessage(0));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YearPickerDialog dialog = new YearPickerDialog();
                dialog.setOnYearSelectedListener(year ->
                        Toast.makeText(QueryActivity.this, "已选择：" + year, Toast.LENGTH_SHORT).show()
                );
                dialog.show(getSupportFragmentManager(), "YearPicker");
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
            intent.putExtra("year", activity.year);
            activity.startActivity(intent);
        }
    }
}