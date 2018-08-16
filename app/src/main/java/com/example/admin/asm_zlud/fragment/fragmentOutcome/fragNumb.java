package com.example.admin.asm_zlud.fragment.fragmentOutcome;

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
import com.example.admin.asm_zlud.UpdelOut;
import com.example.admin.asm_zlud.fragment.fragmentStatic;
import com.example.admin.asm_zlud.purepackagesupport.Model.enums.OutReasonType;
import com.example.admin.asm_zlud.purepackagesupport.OutAdapter.CustomAdapter;
import com.example.admin.asm_zlud.purepackagesupport.Model.OutModel;
import com.example.admin.asm_zlud.purepackagesupport.Repository.OutDAO;

import java.util.ArrayList;

public class fragNumb extends Fragment {
    ArrayList<OutModel> listAll;
    OutDAO outDAO;
    OutModel temp;
    ListView listview;
    ArrayAdapter<OutModel> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listfrag1, container, false);

        listview = view.findViewById(R.id.frag1_lv1);
        outDAO = OutDAO.getInstance(getContext());
        listAll = outDAO.getAllItem();
        ArrayAdapter<OutModel> adapter = new CustomAdapter(getActivity(), R.layout.listviewitemnumberout, listAll);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                temp = listAll.get(position);
                OutReasonType type = temp.getType();
                Update(temp, type);
            }
        });
        return view;
    }
    public void Update(OutModel model, OutReasonType type) {
        Intent i = new Intent(getActivity(), UpdelOut.class);
        i.putExtra("outcome", model);
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
