package com.SLU_multimodal_touch.Graphs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GraphSelector extends AppCompatActivity {

    /**************************************************************************************
     * GLOBAL VARIABLES
     **************************************************************************************/
    String TAG = "GraphSelector: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_selector);

        /**************************************************************************************
         * LOCK THE ORIENTATION, HIDE NAVIGATION BUTTONS
         **************************************************************************************/
        // Lock the orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Hide the navigation buttons
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        /**************************************************************************************
         * HISTOGRAM BUTTONS
         **************************************************************************************/


        Button allergy_graph = findViewById(R.id.allergy_graph);
        allergy_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move to the next activity
                Intent next_activity = new Intent(GraphSelector.this, allergy_graph.class);
                startActivity(next_activity);
            }
        });

        Button scatter_100_normal = findViewById(R.id.scatter_100_normal);
        scatter_100_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move to the next activity
                Intent next_activity = new Intent(GraphSelector.this, scatter_100_normal.class);
                startActivity(next_activity);
            }
        });

        Button snowy_days = findViewById(R.id.snowy_days);
        snowy_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                // Move to the next activity
                Intent next_activity = new Intent(GraphSelector.this, snowy_days.class);
                startActivity(next_activity);
            }
        });
        Button art_scores = findViewById(R.id.art_scores);
        art_scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                // Move to the next activity
                Intent next_activity = new Intent(GraphSelector.this, art_scores.class);
                startActivity(next_activity);
            }
        });

        Button fruit_preference = findViewById(R.id.fruit_preference);
        fruit_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Move to the next activity
                Intent next_activity = new Intent(GraphSelector.this, fruit_preference.class);
                startActivity(next_activity);
            }
        });
        Button histo_15_spatial = findViewById(R.id.histo_15_spatial);
        histo_15_spatial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Move to the next activity
                Intent next_activity = new Intent(GraphSelector.this, histo_15_spatial.class);
                startActivity(next_activity);
            }
        });

        /**************************************************************************************
         * SCATTER PLOT BUTTONS
         **************************************************************************************/
        Button scatter_100_spatial = findViewById(R.id.scatter_100_spatial);
        scatter_100_spatial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move to the next activity
                Intent next_activity = new Intent(GraphSelector.this, scatter_100_spatial.class);
                startActivity(next_activity);
            }
        });
        Button flooded_days = findViewById(R.id.flooded_days);
        flooded_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move to the next activity
                Intent next_activity = new Intent(GraphSelector.this, flooded_days.class);
                startActivity(next_activity);
            }
        });
        Button histo_15_normal = findViewById(R.id.histo_15_normal);
        histo_15_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Move to the next activity
                Intent next_activity = new Intent(GraphSelector.this, histo_15_normal.class);
                startActivity(next_activity);
            }
        });
        Button subject_scores = findViewById(R.id.subject_scores);
        subject_scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move to the next activity
                Intent next_activity = new Intent(GraphSelector.this, subject_scores.class);
                startActivity(next_activity);
            }
        });
        Button fruit_types = findViewById(R.id.fruit_types);
        fruit_types.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move to the next activity
                Intent next_activity = new Intent(GraphSelector.this, fruit_types.class);
                startActivity(next_activity);
            }
        });
        Button popcorn_flavors = findViewById(R.id.popcorn_flavors);
        popcorn_flavors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move to the next activity
                Intent next_activity = new Intent(GraphSelector.this, popcorn_flavors.class);
                startActivity(next_activity);
            }
        });






        /**************************************************************************************
         * NAVIGATION BUTTONS
         **************************************************************************************/
        Button back_button = findViewById(R.id.back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go Back to Participant Activity
                Intent back = new Intent(GraphSelector.this, participant.class);
                startActivity(back);
            }
        });

    }
}