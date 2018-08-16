package com.example.admin.asm_zlud.fragment.fragmentIncome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.asm_zlud.R;
import com.example.admin.asm_zlud.UpdelIn;
import com.example.admin.asm_zlud.fragment.fragmentStatic;
import com.example.admin.asm_zlud.purepackagesupport.Adapter.InAdapter.CustomAdapter;
import com.example.admin.asm_zlud.purepackagesupport.Model.InModel;
import com.example.admin.asm_zlud.purepackagesupport.Model.enums.InReasonType;
import com.example.admin.asm_zlud.purepackagesupport.Repository.InDAO;

import java.util.ArrayList;

public class fragNumb extends Fragment {
    ArrayList<InModel> listAll;
    InDAO inDAO;
    InModel temp;
    ListView listview;
    ArrayAdapter<InModel> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listfrag1, container, false);

        listview = view.findViewById(R.id.frag1_lv1);
        inDAO = InDAO.getInstance(getContext());
        listAll = inDAO.getAllItem();
        final ArrayAdapter<InModel> adapter = new CustomAdapter(getActivity(), R.layout.listviewitemnumber, listAll);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                temp = listAll.get(position);
                InReasonType type = temp.getType();
                Update(temp, type);
            }
        });


        return view;
    }

    public void Update(InModel model, InReasonType type) {
        Intent i = new Intent(getActivity(), UpdelIn.class);
        i.putExtra("income", model);
        i.putExtra("enum", type);
        startActivityForResult(i, 10001);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if((requestCode==10001)&&(resultCode== -1))
        {
            fragmentStatic frag=new fragmentStatic();
            FragmentTransaction ft= getFragmentManager().beginTransaction();
            ft.replace(R.id.framelayout,frag);
            Toast.makeText(getActivity(),"Updated",Toast.LENGTH_LONG);

            ft.commit();
        }
    }
}