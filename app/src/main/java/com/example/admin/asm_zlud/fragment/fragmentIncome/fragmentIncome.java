package com.example.admin.asm_zlud.fragment.fragmentIncome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.asm_zlud.Adapter.myFragmentAdapter;
import com.example.admin.asm_zlud.R;
import com.example.admin.asm_zlud.purepackagesupport.Repository.InDAO;

public class fragmentIncome extends Fragment{
    ViewPager viewPager;
    TabLayout tabLayout;
    InDAO inDAO;
    TextView tvTotal;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragincome,container,false);
        viewPager=view.findViewById(R.id.viewPagerIncome);
        tabLayout=view.findViewById(R.id.tabLayoutIncome);
        tvTotal=view.findViewById(R.id.tvSumIncome);

        myFragmentAdapter adapter= new myFragmentAdapter(getFragmentManager());
        fragNumb fragNumb=new fragNumb();
        fragText fragtext=new fragText();

        adapter.addFrag(fragNumb,"Income");
        adapter.addFrag(fragtext,"Type");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        inDAO=InDAO.getInstance(getContext());
        Double total=inDAO.getTotalMoney();
        tvTotal.setText(total.toString());

        return view;
    }


}
