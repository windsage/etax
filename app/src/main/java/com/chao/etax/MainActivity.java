package com.chao.etax;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String MY_COMPANY = "上海铂星科技有限公司";
    private static final String MY_COMPANY2 = "上海传音信息技术有限公司";
    private static final String MY_NORMAL_CAT = "正常工资薪金";
    private static final String[] ARRAY_MONTH = {"2023-01", "2023-02", "2023-03", "2023-04", "2023-04",
            "2023-05", "2023-06", "2023-07", "2023-08", "2023-09", "2023-10", "2023-11", "2023-12"};

    //    private static final double[] ARRAY_SALARY = {54133.00, 61097.18, 54369.44, 144000.00, 108624.67, 63386.34, 55394.78, 55484.34, 55394.78, 55394.78, 61623.65, 61482.09, 55271.25
//    };
    private static final double[] ARRAY_SALARY = {64133.00, 71097.18, 64369.44, 230878.00, 64624.67, 73386.34, 65394.78, 65484.34, 65394.78, 65394.78, 71623.65, 71482.09, 65271.25};

    //    private static final double[] ARRAY_TAX = {1324.11, 4240.53, 3717.75, 14190.00, 15688.95, 9238.89, 7640.58, 9265.81, 9418.03, 6043.03, 11269.84, 12527.83, 10664.58
//    };
    private static final double[] ARRAY_TAX = {2324.11, 5240.53, 5120.15, 44765.60, 9486.55, 11238.89, 9640.58, 12065.81, 11918.03, 9042.57, 15570.30, 15527.83, 13664.58};

    private static final String[] ARRAY_MONTH_2024 = {"2024-01", "2024-02", "2024-03", "2024-04", "2024-05",
            "2024-05", "2024-06", "2024-07", "2024-08", "2024-08", "2024-09", "2024-10", "2024-11", "2024-12"};

    private static final String[] ARRAY_MONTH_2025 = {"2025-01", "2025-01", "2025-02"};
    //    private static final double[] ARRAY_SALARY_2024 = {55275.86, 55275.86, 55266.64, 55271.25, 144000.00, 63266.64, 55275.86};
    private static final double[] ARRAY_SALARY_2024 = {65275.86, 65275.86, 65266.64, 65271.25, 221258.00, 65266.64, 75275.86, 65271.25, 327500.00, 38200.00, 40000.00, 80000.00, 80000.00, 80000.00};

    //    private static final double[] ARRAY_TAX_2024 = {1066.60, 3524.05, 3554.40, 3554.86, 14190.00, 8528.70, 7110.64};
    private static final double[] ARRAY_TAX_2024 = {2035.32, 4555.32, 4554.40, 8374.76, 42841.60, 9108.80, 11110.64, 10551.91, 0.00, 4598.32, 605.80, 4912.86, 6019.33, 11696.65};

    private static final double[] ARRAY_SALARY_2025 = {80000.00, 120916.03, 80000};//200916.03

    private static final double[] ARRAY_TAX_2025 = {3499.33, 11881.60, 6019.33};//15380.93

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
        } else if (year == 2025) {
            initData2025();
        }
        ItemAdapter adapter = new ItemAdapter(this, dataList);
        listView.setAdapter(adapter);
    }


    private void initData2023() {
//        mTotalSalary.setText("885656.30元");
        mTotalSalary.setText("1038534.30元");
//        mTotalTax.setText("115229.93元");
        mTotalTax.setText("165605.53元");
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
//        mTotalSalary.setText("483632.11元");
//        mTotalTax.setText("41529.25元");
        mTotalSalary.setText("1333861.36元");
        mTotalTax.setText("120965.71元");
        Detail detail;
        for (int i = 13; i >= 0; i--) {
            detail = new Detail();
            if (i >= 10) {
                detail.company = MY_COMPANY2;
            } else {
                detail.company = MY_COMPANY;
            }
            detail.month = ARRAY_MONTH_2024[i];
            if (i == 4) {
                detail.littleCat = "全年一次性奖金收入";
            } else if (i == 8) {
                detail.littleCat = "解除劳动合同一次性补偿";
            } else {
                detail.littleCat = MY_NORMAL_CAT;
            }
            detail.salary = ARRAY_SALARY_2024[i];
            detail.tax = ARRAY_TAX_2024[i];
            dataList.add(detail);
        }
    }


    private void initData2025() {
        mTotalSalary.setText("280916.03元");
        mTotalTax.setText("21400.26元");
        Detail detail;
        for (int i = 2; i >= 0; i--) {
            detail = new Detail();
            if (i == 1) {
                detail.littleCat = "全年一次性奖金收入";
            } else {
                detail.littleCat = MY_NORMAL_CAT;
            }
            detail.month = ARRAY_MONTH_2025[i];
            detail.company = MY_COMPANY2;
            detail.salary = ARRAY_SALARY_2025[i];
            detail.tax = ARRAY_TAX_2025[i];
            dataList.add(detail);
        }
    }
}