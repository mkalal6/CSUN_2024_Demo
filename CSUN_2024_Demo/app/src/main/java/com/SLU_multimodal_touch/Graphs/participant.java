package com.SLU_multimodal_touch.Graphs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

public class participant extends AppCompatActivity {

    /**************************************************************************************
     * GLOBAL VARIABLES
     **************************************************************************************/
    String participant_number = "";
    String TAG = "Participant Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participant);

        /**************************************************************************************
         * LOCK THE ORIENTATION, HIDE NAVIGATION BUTTONS, ASK FOR PERMISSIONS
         **************************************************************************************/
        // Lock the orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Hide the navigation buttons
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //Ask for permission to manage External Storage
        if (ContextCompat.checkSelfPermission(participant.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(participant.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

        /**************************************************************************************
         * PARTICIPANT NUMBER EDIT TEXT BOX
         **************************************************************************************/
        EditText participant_number_box = findViewById(R.id.participant_number);

        // Save the participant number into the participant_number variable
        participant_number_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Store the value in our variable
                participant_number = s.toString();
            }
            @Override
            public void afterTextChanged(Editable editable) {
                // Do nothing
            }
        });

        // Stop the text editing if the Enter key is pressed
        participant_number_box.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    // hide the keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(participant_number_box.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });




        /**************************************************************************************
         * PARTICIPANT BUTTON
         **************************************************************************************/
        Button button = findViewById(R.id.participant_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Save the participant name to the SHARED PREFERENCES section of the app
                SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("participant_number", participant_number);
                editor.apply();

                // Make sure the participant number was saved correctly:
                Map<String, ?> allEntries = sharedPref.getAll();
                for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                    Log.e("SharedPreferences", entry.getKey() + ": " + entry.getValue().toString());
                }

                // Move to the next activity
                Intent next_activity = new Intent(participant.this, GraphSelector.class);
                startActivity(next_activity);
            }
        });

    }
}