package databeam.databeamui;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class Testing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        final EditText inputFirst = (EditText) findViewById(R.id.getFirst);
        final EditText inputMiddle = (EditText) findViewById(R.id.getMiddle);
        final EditText inputLast = (EditText) findViewById(R.id.getLast);
        ToggleButton storeText = (ToggleButton) findViewById(R.id.storeText);
        storeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String typedFirst = inputFirst.getText().toString();
                String typedMiddle = inputMiddle.getText().toString();
                String typedLast = inputLast.getText().toString();
                String fullName = typedFirst + " " + typedMiddle + " " + typedLast;
                System.out.println(fullName);
            }
        });
    }
}
