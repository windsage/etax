package com.chao.etax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String MY_COMPANY = "上海铂星科技有限公司";
    private static final String MY_NORMAL_CAT = "正常工资薪金";
    private static final String[] ARRAY_MONTH = {"2023-01", "2023-02", "2023-03", "2023-04", "2023-04",
            "2023-05", "2023-06", "2023-07", "2023-08", "2023-09", "2023-10", "2023-11", "2023-12"};

    private static final double[] ARRAY_SALARY = {54133.00, 61097.18, 54369.44, 144000.00, 108624.67, 63386.34, 55394.78, 55484.34, 55394.78, 55394.78, 61623.65, 61482.09, 55271.25
    };

    private static final double[] ARRAY_TAX = {1324.11, 4240.53, 3717.75, 14190.00, 15688.95, 9238.89, 7640.58, 9265.81, 9418.03, 6043.03, 11269.84, 12527.83, 10664.58
    };

    private static final String[] ARRAY_MONTH_2024 = {"2024-01", "2024-02", "2024-03", "2024-04", "2024-05",
            "2024-05", "2024-06"};

    private static final double[] ARRAY_SALARY_2024 = {55275.86, 55275.86, 55266.64, 55271.25, 144000.00, 63266.64, 55275.86};

    private static final double[] ARRAY_TAX_2024 = {1066.60, 3524.05, 3554.40, 3554.86, 14190.00, 8528.70, 7110.64};


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
        int year = intent.getIntExtra("year", 2023);
        if (year == 2023) {
            initData2023();
        } else if (year == 2024) {
            initData2024();
        }

        ItemAdapter adapter = new ItemAdapter(this, dataList);
        listView.setAdapter(adapter);
    }


    private void initData2023() {
        mTotalSalary.setText("885656.30元");
        mTotalTax.setText("115229.93元");
        Detail detail;
        for (int i = 12; i >= 0; i--) {
            detail = new Detail();
            detail.company = MY_COMPANY;
            detail.month = ARRAY_MONTH[i];
            if (i == 3) {
                detail.littleCat = "全年一次性奖金收入";
            } else {
                detail.littleCat = MY_NORMAL_CAT;
            }
            detail.salary = ARRAY_SALARY[i];
            detail.tax = ARRAY_TAX[i];
            dataList.add(detail);
        }
    }

    private void initData2024() {
        mTotalSalary.setText("483632.11元");
        mTotalTax.setText("41529.25元");
        Detail detail;
        for (int i = 6; i >= 0; i--) {
            detail = new Detail();
            detail.company = MY_COMPANY;
            detail.month = ARRAY_MONTH_2024[i];
            if (i == 4) {
                detail.littleCat = "全年一次性奖金收入";
            } else {
                detail.littleCat = MY_NORMAL_CAT;
            }
            detail.salary = ARRAY_SALARY_2024[i];
            detail.tax = ARRAY_TAX_2024[i];
            dataList.add(detail);
        }
    }
}