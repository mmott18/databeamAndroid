package databeam.databeamui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.Charset;

public class TestNFC1 extends Activity implements NfcAdapter.CreateNdefMessageCallback {
    NfcAdapter mNfcAdapter;
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_nfc1);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String id = pref.getString("userid", "null");

        TextView textView = (TextView) findViewById(R.id.card_account_field);
        textView.setText(id);
        // Check for available NFC Adapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        } else if (mNfcAdapter != null)
            //This will refer back to createNdefMessage for what it will send
            mNfcAdapter.setNdefPushMessageCallback(this, this);
        // Register callback
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        //String text = ("Beam me up, Android!\n\n" + "Beam Time: " + System.currentTimeMillis());
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String id = pref.getString("userid", "null");

        String text = id;
        Log.d("get account", "Account Id is: " + text);
        NdefMessage msg = new NdefMessage(
                new NdefRecord[]{createMimeRecord(
                        "application/chris.com.myfirstapp", text.getBytes())
                        //,NdefRecord.createApplicationRecord("chris.com.myfirstapp")
                });
        return msg;
    }

    /**
     * Creates a custom MIME type encapsulated in an NDEF record
     *
     * @param mimeType
     */
    public NdefRecord createMimeRecord(String mimeType, byte[] payload) {
        byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
        NdefRecord mimeRecord = new NdefRecord(
                NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload);
        return mimeRecord;
    }
}
