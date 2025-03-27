package com.chao.etax.adapter;

import android.graphics.Color;
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
    private Year selectedYear;

    public void setSelectedYear(Year year) {
        this.selectedYear = year;
        notifyDataSetChanged();
    }

    public YearAdapter(List<Year> items, Year defaultYear) {
        this.items = items;
        this.selectedYear = defaultYear; // 初始化时设置默认选中项
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
            ((YearViewHolder) holder).bind((Year) item, selectedYear!= null && selectedYear.equals(item));
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

        void bind(final Year year, boolean isSelected) {
            if (year.getValue() < 2019 || year.getValue() > 2025) {
                tvYear.setText("");
            } else {
                tvYear.setText(String.valueOf(year.getValue()));
            }
            if (isSelected){
                tvYear.setTextColor(Color.parseColor("#181f25"));
            } else {
                tvYear.setTextColor(Color.parseColor("#afb2b2"));
            }

        }
    }

}
