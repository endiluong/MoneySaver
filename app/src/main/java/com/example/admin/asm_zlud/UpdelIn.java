package com.example.admin.asm_zlud;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.asm_zlud.purepackagesupport.Model.InModel;
import com.example.admin.asm_zlud.purepackagesupport.Model.enums.InReasonType;
import com.example.admin.asm_zlud.purepackagesupport.Repository.InDAO;

import java.util.Calendar;

public class UpdelIn extends AppCompatActivity implements View.OnClickListener {
    Intent i;
    InModel model;
    Button btnUpdate, btnDel, etDate;
    EditText etAmount, etNote;
    Spinner spnType;
    InDAO inDAO;
    int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updel_in);
        etAmount = findViewById(R.id.etAmountUD);
        etDate = findViewById(R.id.etDateUD);
        etNote = findViewById(R.id.etNoteUD);
        spnType = findViewById(R.id.spnTypeUD);
        spnType.setAdapter(new ArrayAdapter<InReasonType>(this, android.R.layout.simple_list_item_1, InReasonType.values()));
//        model = getIntent().getParcelableExtra("income");
        model = getIntent().getParcelableExtra("income");

        InReasonType type = (InReasonType) getIntent().getSerializableExtra("enum");


        etAmount.setText(model.getAmount().toString());
        etDate.setText(model.getDate().toString());
        etNote.setText(model.getNote().toString());

//        reason=model.getType().toString();
        spnType.setSelection(type.ordinal());


        btnUpdate = findViewById(R.id.btnAddInUD);
        btnDel = findViewById(R.id.btnCancelInUD);
        btnUpdate.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        etDate.setOnClickListener(this);
        initial();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.btnAddInUD):
                InModel update = new InModel();
                int success;
                if (model != null) {
                    update.setId(model.getId());
                    update.setAmount(Double.parseDouble(etAmount.getText().toString()));
                    update.setDate(etDate.getText().toString());
                    update.setNote(etNote.getText().toString());
                    update.setType(InReasonType.valueOf(spnType.getSelectedItem().toString()));

                    success = inDAO.updateIncome(update);
                    if (success > 0) {
                        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                }
                break;
            case (R.id.btnCancelInUD):
                if (model != null) {
                    new AlertDialog.Builder(this)
                            .setIcon(R.drawable.ic_delete_forever_black_24dp)
                            .setTitle("Delete")
                            .setMessage("Are you sure to Delete this")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    inDAO.deleteIncome(model);
                                    Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                                    setResult(Activity.RESULT_OK);
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                }
                break;
            case (R.id.etDateUD):
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                etDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

                break;
        }
    }

    private void initial() {
        inDAO = InDAO.getInstance(this);
    }
}
