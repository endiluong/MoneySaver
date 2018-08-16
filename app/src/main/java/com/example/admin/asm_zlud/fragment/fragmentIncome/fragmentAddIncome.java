package com.example.admin.asm_zlud.fragment.fragmentIncome;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.admin.asm_zlud.Mainager;
import com.example.admin.asm_zlud.R;
import com.example.admin.asm_zlud.purepackagesupport.Model.InModel;
import com.example.admin.asm_zlud.purepackagesupport.Model.enums.InReasonType;
import com.example.admin.asm_zlud.purepackagesupport.Repository.InDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class fragmentAddIncome extends Fragment implements View.OnClickListener {
    Spinner spinner;
    EditText etAmount,etNote;
    Button btnAdd,btnCancel,etDate;
    InDAO inDAO;
    InModel temp;
    int mYear,mMonth,mDay;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.fragmentaddincome,container,false);
        spinner=view.findViewById(R.id.spnType);
        etAmount=view.findViewById(R.id.etAmount);
        etDate=view.findViewById(R.id.etDate);
        etNote=view.findViewById(R.id.etNote);
        btnAdd=view.findViewById(R.id.btnAddIn);
        btnCancel=view.findViewById(R.id.btnCancelIn);

        //set enumlist to spinner
        spinner.setAdapter(new ArrayAdapter<InReasonType>(getActivity(),android.R.layout.simple_list_item_1,InReasonType.values()));
      btnAdd.setOnClickListener(this);
      btnCancel.setOnClickListener(this);
      etDate.setOnClickListener(this);
      //
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        etDate.setText(mDay+"-"+mMonth+"-"+mYear);

    initial();
    return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case(R.id.btnAddIn):
                addItemToList();
                break;
            case(R.id.etDate):


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                etDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

                break;
            case(R.id.btnCancelIn):
                getActivity().finish();
                break;
        }
    }

    public void addItemToList(){
        String amount,date,notes,reasons;
        amount=etAmount.getText().toString();
        date=etDate.getText().toString();
        notes=etNote.getText().toString();
        reasons=spinner.getSelectedItem().toString();
        try {

            temp = new InModel();
            temp.setId(null);
            temp.setAmount(Double.parseDouble(amount));
            temp.setDate(date);
            temp.setType(InReasonType.valueOf(reasons));
            temp.setNote(notes);
            inDAO.insertIncome(temp);
            getActivity().setResult(10001);
            getActivity().finish();
        }catch (NumberFormatException e){
            Log.i("ERRORRRRRRRRRORRR","Loi roi hihi");}
    }

    private void initial(){
        inDAO= InDAO.getInstance(getActivity());
    }
}
