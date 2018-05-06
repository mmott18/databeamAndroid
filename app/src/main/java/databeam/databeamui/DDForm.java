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

public class DDForm extends AppCompatActivity {
    public static String fullName = "John Nobody Doe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        final EditText inputFirst = (EditText) findViewById(R.id.getFirst);
        final EditText inputMiddle = (EditText) findViewById(R.id.getMiddle);
        final EditText inputLast = (EditText) findViewById(R.id.getLast);
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
                if (!empty) {
                    fullName = typedFirst + " " + typedMiddle + " " + typedLast;
                }
                //System.out.println(fullName);
            }
        });

    }
}
