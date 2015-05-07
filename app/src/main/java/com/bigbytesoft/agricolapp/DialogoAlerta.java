package com.bigbytesoft.agricolapp;


import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by JuanCa on 10/04/2015.
 */
public class DialogoAlerta extends DialogFragment {

    private int id;
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Builder builder =
                new Builder(getActivity());
        switch (id){
            case 1: builder.setMessage("No pueden haber espacios en blanco.")
                    .setTitle("Información")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });break;
            case 2:builder.setMessage("Registro existoso.")
                    .setTitle("Información")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });break;
        }


        return builder.create();
    }

    public void show(FragmentManager fragmentManager, String tagAlerta, int id) {
        super.show(fragmentManager, tagAlerta);
        this.id=id;
    }
}
