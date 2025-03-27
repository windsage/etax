package com.chao.etax;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.recyclerview.widget.RecyclerView;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private Paint paint;

    public DividerItemDecoration(Context context) {
        paint = new Paint();
        paint.setColor(Color.parseColor("#E1E5e9"));
        paint.setStrokeWidth(2); // 线条宽度
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int width = parent.getWidth();
        int middleY = parent.getHeight() / 2;
        int offset = parent.getChildAt(0).getHeight() / 2;

        // 画上分割线
        c.drawLine(0, middleY - offset, width, middleY - offset, paint);
        // 画下分割线
        c.drawLine(0, middleY + offset, width, middleY + offset, paint);
    }
}

