package com.example.admin.asm_zlud.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.example.admin.asm_zlud.R;

public class DialogBuilder extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Cài đặt các thuộc tính
        builder.setTitle("Warning!");
        builder.setMessage("Wrong ID or Password");

        // Cài đặt button Yes Dismiss ẩn Dialog
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        return builder.create();
    }
}
