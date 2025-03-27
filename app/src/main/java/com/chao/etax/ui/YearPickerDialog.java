package com.chao.etax.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chao.etax.DividerItemDecoration;
import com.chao.etax.R;
import com.chao.etax.adapter.YearAdapter;
import com.chao.etax.model.Year;

import java.util.ArrayList;
import java.util.List;

public class YearPickerDialog extends DialogFragment {
    private OnYearSelectedListener listener;
    private RecyclerView recyclerView;
    private YearAdapter adapter;
    private Year selectedYear;
    private RecyclerView.LayoutManager layoutManager;

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
        for (int i = 2016; i <= 2028; i++) items.add(new Year(i));
        Log.e("xuchao", "setupRecyclerView defaultYear = " + defaultYear);
        for (Year item : items) {
            if (item.getValue() == defaultYear) {
                selectedYear = item;
                break;
            }
        }
        recyclerView = rootView.findViewById(R.id.rv_year);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new YearAdapter(items, selectedYear);
        adapter.setSelectedYear(selectedYear);
        recyclerView.setAdapter(adapter);

        int position = items.indexOf(selectedYear);
        if (position != -1) {
            // 使用 scrollToPositionWithOffset 居中滚动
            layoutManager.scrollToPositionWithOffset(position, 0);

            // 延迟执行确保布局完成后再调整居中
            recyclerView.post(() -> {
                View targetView = layoutManager.findViewByPosition(position);
                if (targetView != null) {
                    int centerOffset = (recyclerView.getHeight() - targetView.getHeight()) / 2 - 40;
                    layoutManager.scrollToPositionWithOffset(position, centerOffset);
                }
            });
        }
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));

        // 让滚动停止在中间位置
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        // 定位到当前选中的年份
//        int position = items.indexOf(selectedYear);
//        recyclerView.scrollToPosition(position);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    View centerView = snapHelper.findSnapView(layoutManager);
                    if (centerView != null) {
                        int position = layoutManager.getPosition(centerView);
                        selectedYear = items.get(position);
                        adapter.setSelectedYear(selectedYear);
                    }
                }
            }
        });
    }

    private void setupButtons(View rootView) {
        rootView.findViewById(R.id.btn_cancel).setOnClickListener(v -> dismiss());
        rootView.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && selectedYear != null) {
                    listener.onYearSelected(selectedYear.getValue());
                }
                dismiss();
            }
        });
    }


    private static final String ARG_DEFAULT_YEAR = "default_year";
    private int defaultYear; // 新增字段

    public static YearPickerDialog newInstance(int defaultYear) {
        YearPickerDialog fragment = new YearPickerDialog();
        Bundle args = new Bundle();
        args.putInt(ARG_DEFAULT_YEAR, defaultYear);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            defaultYear = getArguments().getInt(ARG_DEFAULT_YEAR, 2023);
        }
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
            params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 1117,  // 对应280dp
                    getResources().getDisplayMetrics());
            params.horizontalMargin = 0; // 移除横向边距
            window.setAttributes(params);
            window.setGravity(Gravity.BOTTOM);
        }
        return dialog;
    }
}