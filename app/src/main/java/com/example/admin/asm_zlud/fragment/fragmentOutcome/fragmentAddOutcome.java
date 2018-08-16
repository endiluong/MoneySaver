package com.example.admin.asm_zlud.fragment.fragmentOutcome;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.example.admin.asm_zlud.R;
import com.example.admin.asm_zlud.purepackagesupport.Model.OutModel;
import com.example.admin.asm_zlud.purepackagesupport.Model.enums.OutReasonType;
import com.example.admin.asm_zlud.purepackagesupport.Repository.OutDAO;

import java.util.Calendar;


public class fragmentAddOutcome extends Fragment implements View.OnClickListener {
    Spinner spinner;
    EditText etAmount, etNote;
    Button btnAdd, btnCancel, etDate;
    OutDAO outDAO;
    OutModel temp;
    int mYear, mMonth, mDay;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentaddoutcome, container, false);
        spinner = view.findViewById(R.id.spnTypeO);
        etAmount = view.findViewById(R.id.etAmountO);
        etDate = view.findViewById(R.id.etDateO);
        etNote = view.findViewById(R.id.etNoteO);
        btnAdd = view.findViewById(R.id.btnAddInO);
        btnCancel = view.findViewById(R.id.btnCancelInO);

        //set enumlist to spinner
        spinner.setAdapter(new ArrayAdapter<OutReasonType>(getActivity(), android.R.layout.simple_list_item_1, OutReasonType.values()));
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
        switch (view.getId()) {
            case (R.id.btnAddInO):
                addItemToList();
                break;
            case (R.id.etDateO):
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
            case (R.id.btnCancelInO):
                getActivity().finish();
                break;
        }
    }

    public void addItemToList() {
        String amount, date, notes, reasons;
        amount = etAmount.getText().toString();
        date = etDate.getText().toString();
        notes = etNote.getText().toString();
        reasons = spinner.getSelectedItem().toString();
        try {
            temp = new OutModel();
            temp.setId(null);
            temp.setAmount(Double.parseDouble(amount));
            temp.setDate(date);
            temp.setType(OutReasonType.valueOf(reasons));
            temp.setNote(notes);
            outDAO.insertOutcome(temp);
            getActivity().setResult(10001);
            getActivity().finish();
        } catch (NumberFormatException e) {
            Log.i("ERRORRRORRRR", "Loi Roi Ahihi");
        }
    }

    private void initial() {
        outDAO = OutDAO.getInstance(getActivity());
    }

}
