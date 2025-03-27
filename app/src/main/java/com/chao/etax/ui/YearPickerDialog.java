package com.chao.etax.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chao.etax.R;
import com.chao.etax.adapter.YearAdapter;
import com.chao.etax.model.Year;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class YearPickerDialog extends DialogFragment {
    private OnYearSelectedListener listener;

    public interface OnYearSelectedListener {
        void onYearSelected(int year);
    }

    public void setOnYearSelectedListener(OnYearSelectedListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_year_picker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView(view);
        setupButtons(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setupRecyclerView(View rootView) {
        List<Year> items = new ArrayList<>();
        // 添加年份数据
        for (int i = 2019; i <= 2025; i++) items.add(new Year(i));

        RecyclerView recyclerView = rootView.findViewById(R.id.rv_year);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new YearAdapter(items, year -> {
            // 更新选中状态
            for (Year item : items) {
                if (item != null) {
                    ((Year) item).setSelected(((Year) item).getValue() == year);
                }
            }
            Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
            if (listener != null) listener.onYearSelected(year);
        }));
    }

    private void setupButtons(View rootView) {
        rootView.findViewById(R.id.btn_cancel).setOnClickListener(v -> dismiss());
        rootView.findViewById(R.id.btn_confirm).setOnClickListener(v -> dismiss());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 关键代码：设置对话框主题
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomDialogTheme);
    }

    @Override
    public void onStart() {
        super.onStart();
        // 显示遮罩层
        View overlay = requireActivity().findViewById(R.id.overlay);
        if (overlay != null) {
            overlay.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        // 隐藏遮罩层
        View overlay = requireActivity().findViewById(R.id.overlay);
        if (overlay != null) {
            overlay.setVisibility(View.GONE);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250,  // 对应280dp
                    getResources().getDisplayMetrics());
            params.horizontalMargin = 0; // 移除横向边距
            window.setAttributes(params);
            window.setGravity(Gravity.BOTTOM);
        }
        return dialog;
    }
}