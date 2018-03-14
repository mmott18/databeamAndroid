package databeam.databeamui;

import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;

import databeam.databeamui.R;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class DatabeamDisplayForm2 extends AppCompatActivity {

    public static final String ERROR_DETECTED = "No NFC tag detected!";
    public static final String WRITE_SUCCESS = "Text written to the NFC tag successfully!";
    public static final String WRITE_ERROR = "Error during writing, is the NFC tag close enough to your device?";
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter writeTagFilters[];
    boolean writeMode;
    Tag myTag;
    Context context;
    static final int PICK_PDF_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databeam_display_form2);
        PDFBoxResourceLoader.init(getApplicationContext());
        openPDF();

        ToggleButton nfcButton = (ToggleButton) findViewById(R.id.toggleNFC);
        nfcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    String dir = "/Download";

    //    public void displaypdf() {
//
//        File file = null;
//        file = new File(Environment.getExternalStorageDirectory() + dir + "/directdeposit.pdf");
//        Toast.makeText(getApplicationContext(), file.toString(), Toast.LENGTH_LONG).show();
//        if (file.exists()) {
//            Intent target = new Intent(Intent.ACTION_VIEW);
//            target.setDataAndType(Uri.fromFile(file), "application/pdf");
//            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//
//            Intent intent = Intent.createChooser(target, "Open File");
//            try {
//                startActivity(intent);
//            } catch (ActivityNotFoundException e) {
//                // Instruct the user to install a PDF reader here, or something
//            }
//        } else
//            Toast.makeText(getApplicationContext(), "File path is incorrect.", Toast.LENGTH_LONG).show();
//    }



    private void openPDF(){
       //This method does not require a verbose file path
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        try {
            startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_REQUEST);

        } catch(ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(),"There is no File Browser", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override //Handles what to do once the User selects the PDF to read
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_PDF_REQUEST){
            if(resultCode == RESULT_OK){
                //The user picked a PDF
                //TODO: Send the PDF to be parsed by PDFBox
                Uri pdfUri = data.getData(); //The Uri to the PDF
                try{
                    Toast.makeText(getApplicationContext(),"Selected PDF at " + data.toString(), Toast.LENGTH_SHORT).show();
                    pdfReader formRead = new pdfReader();
                    formRead.readForms(pdfUri);
                } catch(IOException e){
                    e.printStackTrace();
                }

            }
        }
    }
    /******************************************************************************
     **********************************Read From NFC Tag***************************
     ******************************************************************************/
    private void readFromIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs = null;
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }
            buildTagViews(msgs);
        }
    }

    private void buildTagViews(NdefMessage[] msgs) {
        if (msgs == null || msgs.length == 0) return;

        String text = "";
        byte[] payload = msgs[0].getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16"; // Get the Text Encoding
        int languageCodeLength = payload[0] & 0063; // Get the Language Code, e.g. "en"

        try {
            // Get the Text
            text = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncoding", e.toString());
        }

        //tvNFCContent.setText("NFC Content: " + text);
    }
}
