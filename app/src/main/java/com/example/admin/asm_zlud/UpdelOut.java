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
import com.example.admin.asm_zlud.purepackagesupport.Model.OutModel;
import com.example.admin.asm_zlud.purepackagesupport.Model.enums.InReasonType;
import com.example.admin.asm_zlud.purepackagesupport.Model.enums.OutReasonType;
import com.example.admin.asm_zlud.purepackagesupport.Repository.InDAO;
import com.example.admin.asm_zlud.purepackagesupport.Repository.OutDAO;

import java.util.Calendar;

public class UpdelOut extends AppCompatActivity implements View.OnClickListener {
    Intent i;
    OutModel model;
    Button btnUpdate, btnDel, etDate;
    EditText etAmount, etNote;
    Spinner spnType;
    OutDAO outDAO;
    int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updel_out);

        etAmount = findViewById(R.id.etAmountUDO);
        etDate = findViewById(R.id.etDateUDO);
        etNote = findViewById(R.id.etNoteUDO);
        spnType = findViewById(R.id.spnTypeUDO);
        spnType.setAdapter(new ArrayAdapter<OutReasonType>(this, android.R.layout.simple_list_item_1, OutReasonType.values()));

        model=getIntent().getParcelableExtra("outcome");

        OutReasonType type = (OutReasonType) getIntent().getSerializableExtra("enum");


        etAmount.setText(model.getAmount().toString());
        etDate.setText(model.getDate().toString());
        etNote.setText(model.getNote().toString());

//        reason=model.getType().toString();
        spnType.setSelection(type.ordinal());


        btnUpdate = findViewById(R.id.btnAddInUDO);
        btnDel = findViewById(R.id.btnCancelInUDO);
        btnUpdate.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        etDate.setOnClickListener(this);
        initial();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.btnAddInUDO):
                OutModel update = new OutModel();
                int success;
                if (model != null) {
                    update.setId(model.getId());
                    update.setAmount(Double.parseDouble(etAmount.getText().toString()));
                    update.setDate(etDate.getText().toString());
                    update.setNote(etNote.getText().toString());
                    update.setType(OutReasonType.valueOf(spnType.getSelectedItem().toString()));

                    success = outDAO.updateOutcome(update);
                    if (success > 0) {
                        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                }
                break;
            case (R.id.btnCancelInUDO):
                if (model != null) {
                    new AlertDialog.Builder(this)
                            .setIcon(R.drawable.ic_delete_forever_black_24dp)
                            .setTitle("Delete")
                            .setMessage("Are you sure to Delete this")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    outDAO.deleteIncome(model);
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
            case (R.id.etDateUDO):
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
        outDAO = OutDAO.getInstance(this);
    }
}
