package com.chao.etax.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chao.etax.model.Detail;
import com.chao.etax.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    private ArrayList<Detail> details;
    private Context mContext;
    private LayoutInflater mInflater;

    public ItemAdapter(Context context, ArrayList<Detail> details) {
        this.details = details;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return details.size();
    }

    @Override
    public Object getItem(int position) {
        return details.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        DecimalFormat df = new DecimalFormat("#.00");
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.layout_tax_detail, null);
            holder.month = convertView.findViewById(R.id.tv_month);
            holder.cat = convertView.findViewById(R.id.tv_cat);
            holder.company = convertView.findViewById(R.id.tv_company);
            holder.salary = convertView.findViewById(R.id.tv_salary);
            holder.tax = convertView.findViewById(R.id.tv_tax);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Detail detail = details.get(position);
        holder.month.setText(detail.month);
        holder.company.setText("扣缴义务人:  " + detail.company);
        if (detail.tax == 0) {
            holder.tax.setText("已申报税额:  " + "0.00元");
        } else {
            holder.tax.setText("已申报税额:  " + df.format(detail.tax) + "元");
        }
        holder.salary.setText("收入:  " + df.format(detail.salary) + "元");
        holder.cat.setText("所得项目小类:  " + detail.littleCat);
        return convertView;
    }


    static class ViewHolder {
        TextView month;
        TextView cat;
        TextView company;
        TextView salary;
        TextView tax;
    }
}
