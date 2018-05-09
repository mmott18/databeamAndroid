package databeam.databeamui;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import dataClasses.BasicInfo;
import dataClasses.DirectDeposit;

public class DDForm extends AppCompatActivity {
    public static String allInfo = "John/Nobody/Doe/123 Green Street/The City/The State/12345";

    public static DirectDeposit DDMethods = new DirectDeposit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddform);

        final EditText inputFirst = (EditText) findViewById(R.id.getFirst);
        final EditText inputMiddle = (EditText) findViewById(R.id.getMiddle);
        final EditText inputLast = (EditText) findViewById(R.id.getLast);
        final EditText address = (EditText) findViewById(R.id.getAddress);
        final EditText city = (EditText) findViewById(R.id.getCity);
        final EditText state = (EditText) findViewById(R.id.getState);
        final EditText zip = (EditText) findViewById(R.id.getZipCode);
        final EditText routingNumber = (EditText) findViewById(R.id.getRoutNum);
        final EditText checkingNumber = (EditText) findViewById(R.id.getCheckAcct);
        final EditText savingsNumber = (EditText) findViewById(R.id.getSaveAcct);

        Button storeText = (Button) findViewById(R.id.storeText);
        storeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean empty = false;
                String typedFirst = inputFirst.getText().toString();
                if (TextUtils.isEmpty(typedFirst)) {
                    inputFirst.setError("This field is required.");
                    empty = true;
                }
                String typedMiddle = inputMiddle.getText().toString();
                if(typedMiddle.matches("")) {
                    typedMiddle = "None";
                }
                String typedLast = inputLast.getText().toString();
                if (TextUtils.isEmpty(typedLast)) {
                    inputLast.setError("This field is required.");
                    empty = true;
                }
                String typedAddr = address.getText().toString();
                if (TextUtils.isEmpty(typedAddr)) {
                    address.setError("This field is required.");
                    empty = true;
                }
                String typedCity = city.getText().toString();
                if (TextUtils.isEmpty(typedCity)) {
                    city.setError("This field is required.");
                    empty = true;
                }
                String typedState = state.getText().toString();
                if (TextUtils.isEmpty(typedState)) {
                    state.setError("This field is required.");
                    empty = true;
                }
                String typedZip = zip.getText().toString();
                if (TextUtils.isEmpty(typedZip)) {
                    zip.setError("This field is required.");
                    empty = true;
                }
                String typedRouting = routingNumber.getText().toString();
                if (TextUtils.isEmpty(typedRouting)) {
                    routingNumber.setError("This field is required.");
                    empty = true;
                }

                String typedChecking = checkingNumber.getText().toString();
                String typedSavings = savingsNumber.getText().toString();

                if ((TextUtils.isEmpty(typedSavings)) && (TextUtils.isEmpty(typedChecking))) {
                    savingsNumber.setError("One of these fields must be filled.");
                    checkingNumber.setError("One of these fields must be fielled.");
                    empty = true;
                }
                else if (!(TextUtils.isEmpty(typedSavings)) && !(TextUtils.isEmpty(typedChecking))) {
                    savingsNumber.setError("Cannot use both of these fields.");
                    checkingNumber.setError("Cannot use both of these fields.");
                    empty = true;
                }
                else if (typedChecking.matches("")) {
                    typedChecking = "None";
                    empty = false;
                }
                else if (typedSavings.matches("")) {
                    typedSavings = "None";
                    empty = false;
                }
                if (!empty) {
//                    allInfo = typedFirst + "/" + typedMiddle + "/" + typedLast + "/" + typedAddr
//                    + "/" + typedCity + "/" + typedState + "/" + typedZip + "/" + typedRouting +
//                    "/" + typedChecking + "/" + typedSaving;
                    savingsNumber.setError(null);
                    checkingNumber.setError(null);

                    DDMethods.setFirstName(typedFirst);
                    DDMethods.setMiddleName(typedMiddle);
                    DDMethods.setLastName(typedLast);
                    DDMethods.setAddress(typedAddr);
                    DDMethods.setCity(typedCity);
                    DDMethods.setState(typedState);
                    DDMethods.setZipCode(typedZip);
                    DDMethods.setCheckAcct(typedChecking);
                    DDMethods.setSaveAcct(typedSavings);
                    DDMethods.setRoutNum(typedRouting);
                }
            }
        });

    }
}
