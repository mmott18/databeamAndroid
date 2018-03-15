package databeam.databeamui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by justin on 3/15/18.
 */

public class AlertBoxes extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState, String path){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("The path to the PDF is "+ path)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();

    }
}
