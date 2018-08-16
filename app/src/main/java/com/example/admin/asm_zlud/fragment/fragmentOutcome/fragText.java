package com.example.admin.asm_zlud.fragment.fragmentOutcome;

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
import com.example.admin.asm_zlud.purepackagesupport.OutAdapter.CustomAdapter2;
import com.example.admin.asm_zlud.purepackagesupport.Model.OutModel;
import com.example.admin.asm_zlud.purepackagesupport.Repository.OutDAO;

import java.util.ArrayList;

public class fragText extends Fragment {

    ArrayList<OutModel> listAll;
    OutDAO outDAO;
    OutModel temp;
    ListView listview;
    ArrayAdapter<OutModel>adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.lisfrag2,container,false);


        listview=view.findViewById(R.id.frag2_lv2);
        outDAO= OutDAO.getInstance(getContext());
        listAll=outDAO.getByName();
        ArrayAdapter<OutModel> adapter= new CustomAdapter2(getActivity(),R.layout.textfragonelineout,listAll);
        listview.setAdapter(adapter);

        return view;
    }
}
