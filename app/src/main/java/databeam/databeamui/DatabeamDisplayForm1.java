package databeam.databeamui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;

import databeam.databeamui.R;

import java.io.File;

public class DatabeamDisplayForm1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PDFBoxResourceLoader.init(getApplicationContext());
        setContentView(R.layout.activity_databeam_display_form1);
        pdfReader newReader = new pdfReader();
        newReader.stripText(findViewById(R.id.directdeposit));
    }


}
