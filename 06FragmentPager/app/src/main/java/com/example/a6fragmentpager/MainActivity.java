package com.example.a6fragmentpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.a6fragmentpager.ui.adapter.CatPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabs;
    ViewPager pager;
    CatPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.cat_pager);

        pagerAdapter = new CatPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(pager);

        pager.setCurrentItem(1); // Second item
    }
}