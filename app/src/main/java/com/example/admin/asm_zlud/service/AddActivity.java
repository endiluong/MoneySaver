package com.example.admin.asm_zlud.service;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.asm_zlud.Adapter.myFragmentAdapter;
import com.example.admin.asm_zlud.R;
import com.example.admin.asm_zlud.fragment.fragmentIncome.fragmentAddIncome;
import com.example.admin.asm_zlud.fragment.fragmentOutcome.fragmentAddOutcome;

public class AddActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        viewPager = findViewById(R.id.viewPagerAdd);
        tabLayout = findViewById(R.id.tabLayoutAdd);

        myFragmentAdapter adapter = new myFragmentAdapter(getSupportFragmentManager());
        fragmentAddIncome fragmentAddIncome=new fragmentAddIncome();
        fragmentAddOutcome fragmentAddOutcome= new fragmentAddOutcome();


        adapter.addFrag(fragmentAddIncome, "Income");
        adapter.addFrag(fragmentAddOutcome, "Outcome");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }
}
