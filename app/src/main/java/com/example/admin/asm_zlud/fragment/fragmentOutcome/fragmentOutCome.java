package com.example.admin.asm_zlud.fragment.fragmentOutcome;

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
import com.example.admin.asm_zlud.fragment.fragmentOutcome.fragNumb;
import com.example.admin.asm_zlud.fragment.fragmentOutcome.fragText;
import com.example.admin.asm_zlud.purepackagesupport.Repository.OutDAO;

public class fragmentOutCome extends Fragment{
    ViewPager viewPager;
    TabLayout tabLayout;
    TextView tvSum;
    OutDAO outDao;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragoutcome,container,false);
        viewPager=view.findViewById(R.id.viewPagerOut);
        tabLayout=view.findViewById(R.id.tabLayoutOut);
        tvSum=view.findViewById(R.id.tvSumOut);

        myFragmentAdapter adapter= new myFragmentAdapter(getFragmentManager());
        fragNumb fragNumb=new fragNumb();
        fragText fragtext=new fragText();

        adapter.addFrag(fragNumb,"Outcome");
        adapter.addFrag(fragtext,"Type");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        outDao=OutDAO.getInstance(getContext());
        Double Total=outDao.getTotalMoney();
        tvSum.setText(Total.toString());
        return view;
    }
}
