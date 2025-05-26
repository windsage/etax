package com.chao.etax.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chao.etax.R;
import com.chao.etax.adapter.ItemAdapter;
import com.chao.etax.model.Detail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Detail> dataList = new ArrayList<>();

    private TextView mTotalSalary, mTotalTax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.detail);
        mTotalSalary = findViewById(R.id.tv_total_salary);
        mTotalTax = findViewById(R.id.tv_total_tax);
        Intent intent = getIntent();
        int year = intent.getIntExtra("year", 2025);
        if (year == 2023) {
            initData(R.array.array_month_2023, R.array.array_salary_2023, R.array.array_tax_2023, R.array.array_company_2023, R.array.array_type_2023);
        } else if (year == 2024) {
            initData(R.array.array_month_2024, R.array.array_salary_2024, R.array.array_tax_2024, R.array.array_company_2024, R.array.array_type_2024);
        } else if (year == 2025) {
            initData(R.array.array_month_2025, R.array.array_salary_2025, R.array.array_tax_2025, R.array.array_company_2025, R.array.array_type_2025);
        }
        ItemAdapter adapter = new ItemAdapter(this, dataList);
        listView.setAdapter(adapter);
    }


    private void initData(int monthResId, int salaryResId, int taxResId, int companyResId, int typeResId) {

        String[] months = getResources().getStringArray(monthResId);
        String[] salaryStr = getResources().getStringArray(salaryResId);
        String[] taxStr = getResources().getStringArray(taxResId);
        String[] companyStr = getResources().getStringArray(companyResId);
        String[] typeStr = getResources().getStringArray(typeResId);
        String totalSalary = String.valueOf(sum(salaryResId));
        mTotalSalary.setText(totalSalary + "元");
        String totalTax = String.valueOf(sum(taxResId));
        mTotalTax.setText(totalTax + "元");

        Detail detail;
        for (int i = months.length - 1; i >= 0; i--) {
            detail = new Detail();
            detail.company = companyStr[i];
            detail.month = months[i];
            detail.littleCat = typeStr[i];
            detail.salary = Double.parseDouble(salaryStr[i]);
            detail.tax = Double.parseDouble(taxStr[i]);
            dataList.add(detail);
        }
    }

    public double sum(int resId) {
        String[] salaryStr = getResources().getStringArray(resId);
        BigDecimal sum = BigDecimal.ZERO;

        for (String s : salaryStr) {
            sum = sum.add(new BigDecimal(s));
        }
        return sum.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}