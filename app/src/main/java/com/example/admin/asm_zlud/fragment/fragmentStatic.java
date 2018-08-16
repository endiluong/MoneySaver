package com.example.admin.asm_zlud.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.asm_zlud.R;
import com.example.admin.asm_zlud.purepackagesupport.Repository.InDAO;
import com.example.admin.asm_zlud.purepackagesupport.Repository.OutDAO;


public class fragmentStatic extends Fragment {
    TextView totalIn,totalOut,tvTotal;
    InDAO inDAO;
    OutDAO outDAO;
    Double in,out,total;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragstatic,container,false);
        totalIn= view.findViewById(R.id.tvStaIn);
        totalOut= view.findViewById(R.id.tvStatOut);
        tvTotal= view.findViewById(R.id.tvStaTotal);

        inDAO=InDAO.getInstance(getContext());
        outDAO=OutDAO.getInstance(getContext());
        in=inDAO.getTotalMoney();
        out=outDAO.getTotalMoney();
        total= processTotal(in,out);

        totalIn.setText(in.toString());
        totalOut.setText(out.toString());
        tvTotal.setText(total.toString());

        return view;
    }
    private double processTotal(Double in,Double out){
        Double total=0d;
        total=in-out;
        if(total>0){
            tvTotal.setTextColor(this.getResources().getColor(R.color.mygreen));
        }else{
            tvTotal.setTextColor(this.getResources().getColor(R.color.myred));
        }
        return total;
    }
}
