package com.chao.etax.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chao.etax.R;
import com.chao.etax.model.Year;

import java.util.List;

// YearAdapter.java
public class YearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Year> items;
    private OnYearSelectedListener listener;

    public interface OnYearSelectedListener {
        void onYearSelected(int year);
    }

    public YearAdapter(List<Year> items, OnYearSelectedListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_year, parent, false);
        return new YearViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Year item = items.get(position);
        if (holder instanceof YearViewHolder) {
            ((YearViewHolder) holder).bind((Year) item, listener);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ViewHolder 实现
    static class YearViewHolder extends RecyclerView.ViewHolder {
        private TextView tvYear;

        YearViewHolder(View itemView) {
            super(itemView);
            tvYear = itemView.findViewById(R.id.tv_year);
        }

        void bind(final Year year, final OnYearSelectedListener listener) {
            tvYear.setText(String.valueOf(year.getValue()));
            tvYear.setSelected(year.isSelected());
            tvYear.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onYearSelected(year.getValue());
                }
            });
        }
    }

}
