package com.example.admin.asm_zlud.fragment.fragmentIncome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.admin.asm_zlud.R;
import com.example.admin.asm_zlud.purepackagesupport.Adapter.InAdapter.CustomAdapter2;
import com.example.admin.asm_zlud.purepackagesupport.Model.InModel;
import com.example.admin.asm_zlud.purepackagesupport.Repository.InDAO;

import java.util.ArrayList;

public class fragText extends Fragment {

    ArrayList<InModel> listAll;
    InDAO inDAO;
    InModel temp;
    ListView listview;
    ArrayAdapter<InModel>adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.lisfrag2,container,false);


        listview=view.findViewById(R.id.frag2_lv2);
        inDAO= InDAO.getInstance(getContext());
        listAll=inDAO.getByName();
        ArrayAdapter<InModel> adapter= new CustomAdapter2(getActivity(),R.layout.textfragoneline,listAll);
        listview.setAdapter(adapter);

        return view;
    }
}
