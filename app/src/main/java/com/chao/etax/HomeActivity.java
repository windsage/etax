package com.chao.etax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private FirstTabFragment firstTabFragment = new FirstTabFragment();
    private SecondTabFragment secondTabFragment = new SecondTabFragment();
    private ThirdTabFragment thirdTabFragment = new ThirdTabFragment();
    private FourthTabFragment fourthTabFragment = new FourthTabFragment();
    private FifthTabFragment fifthTabFragment = new FifthTabFragment();

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Button btn1, btn2, btn3, btn4, btn5, btnQuery2023, btnQuery2024, btnQuery2025;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btnQuery2023 = findViewById(R.id.btn_query_2023);
        btnQuery2024 = findViewById(R.id.btn_query_2024);
        btnQuery2025 = findViewById(R.id.btn_query_2025);
        btnQuery2023.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, QueryActivity.class);
            intent.putExtra("year", 2023);
            startActivity(intent);
        });

        btnQuery2024.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, QueryActivity.class);
            intent.putExtra("year", 2024);
            startActivity(intent);
        });
        btnQuery2025.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, QueryActivity.class);
            intent.putExtra("year", 2025);
            startActivity(intent);
        });
        switchFragment(firstTabFragment);
        btn1.setOnClickListener(v -> switchFragment(firstTabFragment));
        btn2.setOnClickListener(v -> switchFragment(secondTabFragment));
        btn3.setOnClickListener(v -> switchFragment(thirdTabFragment));
        btn4.setOnClickListener(v -> switchFragment(fourthTabFragment));
        btn5.setOnClickListener(v -> switchFragment(fifthTabFragment));
    }


    private void switchFragment(Fragment fg) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.main_layout_content, fg);
        transaction.commit();
    }
}