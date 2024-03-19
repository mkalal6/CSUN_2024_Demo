package com.SLU_multimodal_touch.Graphs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.Manifest;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class shapes extends AppCompatActivity {

    /**************************************************************************************
     * DISABLE THE BACK BUTTON
     **************************************************************************************/
    @Override
    public void onBackPressed() {
        // Do nothing
    }

    /**************************************************************************************
     * Global Variables
     **************************************************************************************/
    // TTS and the multi-finger gesture tracker
    TextToSpeech tts; // Initiate the TextToSpeech object
    SimpleFingerGestures_Mod sfg = new SimpleFingerGestures_Mod(); //SimpleFingerGestures_Mod object

    // To get screen dimensions, just in case
    int screen_width = Resources.getSystem().getDisplayMetrics().widthPixels;
    int screen_height = Resources.getSystem().getDisplayMetrics().heightPixels;

    // Define area colors
    int inside_red = 237;
    int inside_green = 125;
    int inside_blue = 49;
    int vertex_red = 0;
    int vertex_green = 176;
    int vertex_blue = 240;
    int line_red = 0;
    int line_green = 0;
    int line_blue = 0;


    // For Vibration Control
    VibrationManager vib = new VibrationManager();
    int vib_freq = 0;
    final int vib_freq_1_vertex = 25;
    final int vib_freq_2_vertex = 10;
    final int vib_freq_3_vertex = 5;
    final int vib_freq_4_vertex = 1;


    // To track WHICH finger is on screen
    int f0_index = -1;
    int f1_index = -1;
    int f2_index = -1;
    int f3_index = -1;
    int f4_index = -1;

    // Media Player control
    private MediaPlayer empty_sound;
    private MediaPlayer inside_sound;
    private MediaPlayer line_sound;
    private MediaPlayer victory_sound;

    // For Logging Info
    // Get the participant number from the shared preferences
    String participant_number;
    String TAG = "Shapes: ";
    String file_name;
    FileWriter writer;
    Boolean writer_active = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Specify which layout this activity uses
        setContentView(R.layout.shapes);

        /**************************************************************************************
         * GET THE PARTICIPANT NUMBER AND FILE NAME
         **************************************************************************************/
        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        participant_number = sharedPref.getString("participant_number", "69420");
        file_name = participant_number + ".csv";

        /**************************************************************************************
         * SET THE BACKGROUND IMAGE FOR EACH ORIENTATION
         **************************************************************************************/

        // Get a reference to the ImageView, based on the phone orientation
        ImageView mv = findViewById(R.id.mt_view);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            mv.setImageResource(R.drawable.triangle);
        } else {
            // In portrait
            mv.setImageResource(R.drawable.triangle_ver);
        }

        /**************************************************************************************
         * LOCK THE ORIENTATION, HIDE NAVIGATION BUTTONS, AND START LOCK TASK MODE
         **************************************************************************************/
        // Lock the orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Hide the navigation buttons
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Start Lock Task mode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startLockTask();
        }

        /**************************************************************************************
         * TEXT TO SPEECH (TTS) CONFIGURATION
         **************************************************************************************/
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });
        // Set the speed of the TTS
        tts.setSpeechRate(1);

        /**************************************************************************************
         * VIBRATION MANAGER
         **************************************************************************************/
        //Tell the VibrationManager that THIS is the activity we are referencing
        vib.setActivity(this); // "this" references this entire activity


        /**************************************************************************************
         * MULTI-TOUCH LIBRARY
         **************************************************************************************/
        // Taken from: https://github.com/championswimmer/SimpleFingerGestures_Android_Library/blob/master/sample/src/main/java/in/championswimmer/sfg/sample/MainActivity.java
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        final TextView grtv = findViewById(R.id.gestureResultTextView); // Initiate the GestureResultTextView object

        sfg.setDebug(false);
        sfg.setConsumeTouchEvents(true);

        sfg.setOnFingerGestureListener(new SimpleFingerGestures_Mod.OnFingerGestureListener() {
            @Override
            public boolean onSwipeUp(int fingers, long gestureDuration, double gestureDistance) {
//                grtv.setText("You swiped up with " + fingers + " finger(s).");
                if (fingers == 1) {
                    //Write your code here for swiping UP with one finger
                }
                else if (fingers == 2) {
                    // Write your code here for swiping UP with 2 fingers
                }
                else if (fingers == 3) {
                    // Write your code here for swiping UP with 3 fingers
                }
                else if (fingers == 4) {
                    // Write your code here for swiping UP with 4 fingers
                }
                else if (fingers == 5) {
                    // Write your code here for swiping UP with 4 fingers
                }
                return false;
            }

            @Override
            public boolean onSwipeDown(int fingers, long gestureDuration, double gestureDistance) {
                grtv.setText("You swiped down with " + fingers + " finger(s).");
                if (fingers == 1) {
                    //Write your code here for swiping DOWN with one finger
                }
                else if (fingers == 2) {
                    // Write your code here for swiping DOWN with 2 fingers
                }
                else if (fingers == 3) {
                    // Write your code here for swiping DOWN with 3 fingers
                }
                else if (fingers == 4) {
                    // Write your code here for swiping DOWN with 4 fingers
                    // Create an Intent that takes us from this activity to another
//                    Intent go_to_next_screen = new Intent(screen_0.this, v_next_week.class);
//                    // Activate the intent to go to that screen
//                    startActivity(go_to_next_screen);
//                    overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
                }
                return false;
            }

            @Override
            public boolean onSwipeLeft(int fingers, long gestureDuration, double gestureDistance) {
                grtv.setText("You swiped left with " + fingers + " finger(s).");
                if (fingers == 1) {
                    //Write your code here for swiping LEFT with one finger
                }
                else if (fingers == 2) {
                    // Write your code here for swiping LEFT with 2 fingers
                    // Create an Intent that takes us from this activity to another
//                    Intent go_to_next_screen = new Intent(shapes.this, screen_1.class);
                    // Activate the intent to go to that screen
//                    startActivity(go_to_next_screen);
//                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                }
                else if (fingers == 3) {
                    // Write your code here for swiping LEFT with 3 fingers
                }
                else if (fingers == 4) {
                    // Write your code here for swiping LEFT with 4 fingers
                }
                return false;
            }

            @Override
            public boolean onSwipeRight(int fingers, long gestureDuration, double gestureDistance) {
                grtv.setText("You swiped right with " + fingers + " finger(s).");
                if (fingers == 1) {
                    //Write your code here for swiping RIGHT with one finger
                }
                else if (fingers == 2) {
                    // Write your code here for swiping RIGHT with 2 fingers
                }
                else if (fingers == 3) {
                    // Write your code here for swiping RIGHT with 3 fingers
                }
                else if (fingers == 4) {
                    // Write your code here for swiping RIGHT with 4 fingers
                }
                return false;
            }

            @Override
            public boolean onPinch(int fingers, long gestureDuration, double gestureDistance) {
                grtv.setText("You pinched " + fingers + " fingers.");
                if (fingers == 2) {
                    // Write your code here for PINCHING with 2 fingers
//                    tts.speak("There is a certain number of small regions on screen in different locations. They all produce a different vibration pattern. Can you identify how many regions are on screen?", TextToSpeech.QUEUE_FLUSH, null);
                }
                else if (fingers == 3) {
                    // Write your code here for PINCHING with 3 fingers
                }
                else if (fingers == 4) {
                    // Write your code here for PINCHING with 4 fingers
                    Intent back_to_graph_selector = new Intent(shapes.this, GraphSelector.class);
                    startActivity(back_to_graph_selector);

                    // Close the FileWriter
                    try {
                        if (writer_active) {
                            writer_active = false;
                            writer.close();
                        }
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                return false;
            }

            @Override
            public boolean onUnpinch(int fingers, long gestureDuration, double gestureDistance) {
                grtv.setText("You unpinched " + fingers + " fingers.");
                if (fingers == 2) {
                    // Write your code here for PINCHING with 2 fingers
                }
                else if (fingers == 3) {
                    // Write your code here for PINCHING with 3 fingers
                }
                else if (fingers == 4) {
                    // Write your code here for PINCHING with 4 fingers
                }
                return false;
            }

            @Override
            public boolean onDoubleTap(int fingers) {
                // Double tap only works with one finger!
                grtv.setText("You double tapped!");
                tts.speak("Screen is turned on!", TextToSpeech.QUEUE_FLUSH, null);
                return false;
            }

        });

        // Set the OnTouch (no TalkBack) and onHover (with TalkBack) listeners
        mv.setOnTouchListener(sfg);
        mv.setOnHoverListener(new HoverToTouchAdapter(sfg));

        // Initiate the textView objects for the screen and for each finger
        TextView coord_view = findViewById(R.id.coordinate_view);
        TextView output_view = findViewById(R.id.output_view);

        // Now for the code to be executed constantly during the duration of this activity
        monitor(mv, coord_view, output_view);

        // Log the required information, by getting the number from the participant activity
        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            participant_number = extras.getString("participant_number");
//            file_name = participant_number + ".csv";
//        }
        try {
            logger(file_name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**************************************************************************************
     * CONSTANTLY RUNNING CODE
     **************************************************************************************/
    public void monitor(ImageView mv, TextView coord_view, TextView output_view) {
        // Variables to be used in any part of this function
        final Handler handler = new Handler();

        /**************************************************************************************
         * MEDIAPLAYER CONFIGURATION
         **************************************************************************************/
        empty_sound = MediaPlayer.create(this, R.raw.waves_trim);
        AssetFileDescriptor afd_empty_sound = getResources().openRawResourceFd(R.raw.waves_trim);
        FileDescriptor fd_empty_sound = afd_empty_sound.getFileDescriptor();

        inside_sound = MediaPlayer.create(this, R.raw.fancy_beep);
        AssetFileDescriptor afd_inside_sound = getResources().openRawResourceFd(R.raw.fancy_beep);
        FileDescriptor fd_inside_sound = afd_inside_sound.getFileDescriptor();

        line_sound = MediaPlayer.create(this, R.raw.knock_wood_trim);
        AssetFileDescriptor afd_line_sound = getResources().openRawResourceFd(R.raw.knock_wood_trim);
        FileDescriptor fd_line_sound = afd_inside_sound.getFileDescriptor();

        victory_sound = MediaPlayer.create(this, R.raw.pokemon_victory_1);
        AssetFileDescriptor afd_victory_sound = getResources().openRawResourceFd(R.raw.pokemon_victory_1);
        FileDescriptor fd_victory_sound = afd_inside_sound.getFileDescriptor();

        // Set the MediaPlayer objects to loop
        empty_sound.setLooping(true);
        inside_sound.setLooping(true);
        line_sound.setLooping(true);
        victory_sound.setLooping(true);

        // Write your constantly running code here
        handler.post(new Runnable() {
            @Override
            public void run() {
                // Your constantly running code here:

                // Finger status/location indicator
                coord_view.setText("There are " + sfg.finger_count + " finger(s) on screen.\nTheir X coordinates are " + Arrays.toString(sfg.X_coords) + "\nTheir Y coordinates are " + Arrays.toString(sfg.Y_coords));


                /**************************************************************************************
                 * IMAGE SCALING PART 1. Based on: https://stackoverflow.com/questions/67078759/imageview-get-color-of-touched-pixel
                 **************************************************************************************/
                // Part 2 is done in later sections of the code, depending on the amount of fingers on screen
                // Obtain the VIEW and IMAGE dimensions. This will be used later to obtain the color of a pixel each finger is touching
                // Get the VIEW width and height
                double viewWidth = mv.getWidth();
                double viewHeight = mv.getHeight();
                // Get the IMAGE info as a bitmap
                Bitmap image = ((BitmapDrawable)mv.getDrawable()).getBitmap();
                // Get the IMAGE width and height
                double imageWidth = image.getWidth();
                double imageHeight = image.getHeight();

                /**************************************************************************************
                 * ZERO FINGERS
                 **************************************************************************************/
                // Reset all finder indexes
                f0_index = -1;
                f1_index = -1;
                f2_index = -1;
                f3_index = -1;
                f4_index = -1;
                if (sfg.finger_count == 0){
                    // Stop any ongoing vibrations
                    if (vib.isVibrating()) {
                        vib.stop();
                    }

                    // Clean the output view
                    output_view.setText("");

                    // Stop any sounds from being played
                    if (empty_sound.isPlaying()) {
                        empty_sound.stop();
                    }
                    if (inside_sound.isPlaying()) {
                        inside_sound.stop();
                    }
                    if (line_sound.isPlaying()) {
                        line_sound.stop();
                    }
                    if (victory_sound.isPlaying()) {
                        victory_sound.stop();
                    }
                }

                /**************************************************************************************
                 * ONE FINGER
                 **************************************************************************************/
                if (sfg.finger_count == 1){

                    // Get the coordinates and pixel color the fingers are touching, considering the different scaling factors of the VIEW and the IMAGE itself.

                    //First check WHICH FINGER is on screen!
                    // Scan the X_coords array to try and find which finger is on screen.
                    if (f0_index == -1) {
                        for (int i = 0; i < sfg.X_coords.length; i++) {
                            if (sfg.X_coords[i] != 0) {
                                f0_index = i;
                                break;
                            }
                        }
                    }
                    // If the finger was in X = 0.0, which means that finger_index is still -1, then scan  the Y_coords array to try and find which finger is on screen.
                    if (f0_index == -1) {
                        for (int i = 0; i < sfg.Y_coords.length; i++) {
                            if (sfg.Y_coords[i] != 0) {
                                f0_index = i;
                                break;
                            }
                        }
                    }
                    // IF the finger was found, THEN continue with the rest of the code. Otherwise, do nothing. The finger is in EXACTLY (0.0 , 0.0).
                    if (f0_index != -1) {
                        // Get the VIEW X and Y coords for FINGER0
                        double viewX = sfg.X_coords[f0_index];
                        double viewY = sfg.Y_coords[f0_index];
                        // Rule of 3 to scale the pixel values
                        double imageX = (viewX * (imageWidth / viewWidth));
                        double imageY = (viewY * (imageHeight / viewHeight));
                        // Limit the X and Y values to the bitmap max dimensions to avoid IllegalArgumentException when providing the pixel coordinate
                        if (imageX >= imageWidth) {
                            imageX = imageWidth - 0.01; // Modify it by 1/100 of a pixel to meet the imageX < bitmap.width condition!
                        }
                        else if (imageX < 0) {
                            imageX = 0; // Account for negative X values (bezels!)
                        }
                        if (imageY >= imageHeight) {
                            imageY = imageHeight - 0.01; // Modify it by 1/100 of a pixel to meet the imageY < bitmap.height condition!
                        }
                        else if (imageY < 0) {
                            imageY = 0; // Account for negative X values (bezels!)
                        }
                        // Get the pixel based on the IMAGE X and Y coordinates!
                        int pixel = image.getPixel((int)imageX, (int)imageY);
                        // Save the RGB values
                        int pixel_red = Color.red(pixel);
                        int pixel_green = Color.green(pixel);
                        int pixel_blue = Color.blue(pixel);

                        // For AUDIO CHANNEL proportional control based on X axis coordinate
                        float audio_x = (float) (imageX / imageWidth);

                        // For AUDIO PITCH proportional control based on Y axis coordinate
                        float audio_y = (float) (imageY / imageHeight);
                        float pitch = (float) (1 - audio_y); // <1 = lower pitch, >1 = higher pitch. We use 1 - audio_y because y = 0 is the TOP of the screen, not the BOTTOM of the screen, and we want lower pitches at the BOTTOM of the screen
                        // Keep the pitch parameter as a positive value, otherwise we get an IllegalArgumentException
                        if (pitch < 0.05) {
                            pitch = (float) 0.05;
                        }

                        // If finger is INSIDE the figure
                        if (pixel_red == inside_red && pixel_green == inside_green && pixel_blue == inside_blue) {
                            // Stop all other sounds or vibrations
                            empty_sound.stop();
                            line_sound.stop();
                            vib.stop();
                            output_view.setText("");

                            // Play the inside sound
                            if (inside_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.

                                // Modify the SPEAKER VOLUME according to the X location proportion
                                inside_sound.setVolume((1 - audio_x), audio_x);

                                // IF it is not playing already, then give it the start command
                                if (!inside_sound.isPlaying()) {
                                    inside_sound.start();
                                    }

                                //Modify the PITCH according to the Y location proportion
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    PlaybackParams params = new PlaybackParams();
                                    params.setPitch(pitch);
                                    inside_sound.setPlaybackParams(params);
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                inside_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    inside_sound.setDataSource(fd_inside_sound, afd_inside_sound.getStartOffset(), afd_inside_sound.getLength());
                                    inside_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        // If finger is ON THE LINE
                        else if (pixel_red == line_red && pixel_green == line_green && pixel_blue == line_blue) {
                            // Stop all other sounds or vibrations
                            empty_sound.stop();
                            inside_sound.stop();
                            vib.stop();
                            output_view.setText("");

                            // Start the LINE sound
                            if (line_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.
                                if (!line_sound.isPlaying()) {
                                    line_sound.start();
                                }
                                // Modify the speaker volume according to the X location proportion
                                line_sound.setVolume((1 - audio_x), audio_x);

                                //Modify the PITCH according to the Y location proportion
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    PlaybackParams params = new PlaybackParams();
                                    params.setPitch(pitch);
                                    line_sound.setPlaybackParams(params);
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                line_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    line_sound.setDataSource(fd_line_sound, afd_line_sound.getStartOffset(), afd_line_sound.getLength());
                                    line_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        // If finger is ON A VERTEX
                        else if (pixel_red == vertex_red && pixel_green == vertex_green && pixel_blue == vertex_blue) {
                            // Stop all other sounds or vibrations
                            empty_sound.stop();
                            inside_sound.stop();
                            line_sound.stop();

                            // Start vibrating
                            if (vib_freq != vib_freq_1_vertex) {
                                // Stop the previous vibration if it is different from the one we are supposed to do
                                vib.stop();
                                // Update the vibration frequency
                                vib_freq = vib_freq_1_vertex;
                                // This only happens ONCE when the vibration frequency CHANGES value, to avoid the motor having to STOPGOSTOPGOSTOPGOSTOPGO
                            }
                            if (!vib.isVibrating()) {
                                vib.vibrateAtFrequencyForever(vib_freq);
                            }
                            output_view.setText("Vibrating at " + vib_freq + " Hz");
                        }
                        // If finger is on EMPTY space
                        else {
                            // Stop all other sounds or vibrations
                            inside_sound.stop();
                            line_sound.stop();
                            vib.stop();
                            output_view.setText("");

                            // Start the EMPTY sound
                            if (empty_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.

                                // IF it is not playing already, then give it the start command
                                if (!empty_sound.isPlaying()) {
                                    empty_sound.start();
                                }

                                // Modify the SPEAKER VOLUME according to the X location proportion
                                empty_sound.setVolume((1 - audio_x), audio_x);

                                //Modify the PITCH according to the Y location proportion
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    PlaybackParams params = new PlaybackParams();
                                    params.setPitch(pitch);
                                    empty_sound.setPlaybackParams(params);
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                empty_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    empty_sound.setDataSource(fd_empty_sound, afd_empty_sound.getStartOffset(), afd_empty_sound.getLength());
                                    empty_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }

                /**************************************************************************************
                 * TWO FINGERS
                 **************************************************************************************/
                if (sfg.finger_count == 2){

                    /**************************************************************************************
                     * GET THE COORDINATES AND INDEXES FOR EACH FINGER
                     **************************************************************************************/

                    //First check WHICH FINGERS are on screen!
                    // Scan the X_coords and Y_coords arrays to find the first finger
                    if (f0_index == -1) {
                        for (int i = 0; i < sfg.X_coords.length; i++) {
                            if (sfg.X_coords[i] != 0) {
                                f0_index = i;
                                break;
                            }
                        }
                    }
                    // If the finger was in X = 0.0, which means that finger_index is still -1, then scan  the Y_coords array to try and find which finger is on screen.
                    if (f0_index == -1) {
                        for (int i = 0; i < sfg.Y_coords.length; i++) {
                            if (sfg.Y_coords[i] != 0) {
                                f0_index = i;
                                break;
                            }
                        }
                    }
                    // Scan the X_coords and Y_coords arrays to find the second finger
                    if (f1_index == -1) {
                        for (int i = 0; i < sfg.X_coords.length; i++) {
                            if (sfg.X_coords[i] != 0 && sfg.X_coords[i] != sfg.X_coords[f0_index]) {
                                f1_index = i;
                                break;
                            }
                        }
                    }
                    // If the finger was in X = 0.0, which means that finger_index is still -1, then scan  the Y_coords array to try and find which finger is on screen.
                    if (f1_index == -1) {
                        for (int i = 0; i < sfg.Y_coords.length; i++) {
                            if (sfg.Y_coords[i] != 0 && sfg.Y_coords[i] != sfg.Y_coords[f0_index]) {
                                f1_index = i;
                                break;
                            }
                        }
                    }

                    /**************************************************************************************
                     * FINGERS FOUND! PROCEED WITH THE REST OF THE CODE
                     **************************************************************************************/

                    // IF the fingers were found, THEN continue with the rest of the code. Otherwise, do nothing. The finger is in EXACTLY (0.0 , 0.0).
                    if (f0_index != -1 && f1_index != -1) {

                        /**************************************************************************************
                         * GET THE PIXELS UNDER THE FINGERS
                         **************************************************************************************/

                        // Get the VIEW X and Y coords for FINGER0
                        double viewX0 = sfg.X_coords[f0_index];
                        double viewY0 = sfg.Y_coords[f0_index];
                        // Rule of 3 to scale the pixel values
                        double imageX0 = (viewX0 * (imageWidth / viewWidth));
                        double imageY0 = (viewY0 * (imageHeight / viewHeight));
                        // Limit the X and Y values to the bitmap max dimensions to avoid IllegalArgumentException when providing the pixel coordinate
                        if (imageX0 >= imageWidth) {
                            imageX0 = imageWidth - 0.01; // Modify it by 1/100 of a pixel to meet the imageX < bitmap.width condition!
                        }
                        else if (imageX0 < 0) {
                            imageX0 = 0; // Account for negative X values (bezels!)
                        }
                        if (imageY0 >= imageHeight) {
                            imageY0 = imageHeight - 0.01; // Modify it by 1/100 of a pixel to meet the imageY < bitmap.height condition!
                        }
                        else if (imageY0 < 0) {
                            imageY0 = 0; // Account for negative X values (bezels!)
                        }
                        // Get the pixel based on the IMAGE X and Y coordinates!
                        int pixel0 = image.getPixel((int)imageX0, (int)imageY0);
                        // Save the RGB values
                        int pixel0_red = Color.red(pixel0);
                        int pixel0_green = Color.green(pixel0);
                        int pixel0_blue = Color.blue(pixel0);

                        // Get the VIEW X and Y coords for FINGER1
                        double viewX1 = sfg.X_coords[f1_index];
                        double viewY1 = sfg.Y_coords[f1_index];
                        // Rule of 3 to scale the pixel values
                        double imageX1 = (viewX1 * (imageWidth / viewWidth));
                        double imageY1 = (viewY1 * (imageHeight / viewHeight));
                        // Limit the X and Y values to the bitmap max dimensions to avoid IllegalArgumentException when providing the pixel coordinate
                        if (imageX1 >= imageWidth) {
                            imageX1 = imageWidth - 0.01; // Modify it by 1/100 of a pixel to meet the imageX < bitmap.width condition!
                        }
                        else if (imageX1 < 0) {
                            imageX1 = 0; // Account for negative X values (bezels!)
                        }
                        if (imageY1 >= imageHeight) {
                            imageY1 = imageHeight - 0.01; // Modify it by 1/100 of a pixel to meet the imageY < bitmap.height condition!
                        }
                        else if (imageY1 < 0) {
                            imageY1 = 0; // Account for negative X values (bezels!)
                        }
                        // Get the pixel based on the IMAGE X and Y coordinates!
                        int pixel1 = image.getPixel((int)imageX1, (int)imageY1);
                        // Save the RGB values
                        int pixel1_red = Color.red(pixel1);
                        int pixel1_green = Color.green(pixel1);
                        int pixel1_blue = Color.blue(pixel1);

                        /**************************************************************************************
                         * REACT BASED ON FINGER LOCATION AND PIXEL COLOR
                         **************************************************************************************/

                        // If both fingers are in a vertex
                        if ((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) &&
                            (pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue)) {
                            if (vib_freq != vib_freq_2_vertex) {
                                // Stop the previous vibration if it is different from the one we are supposed to do
                                vib.stop();
                                // Update the vibration frequency
                                vib_freq = vib_freq_2_vertex;
                                // This only happens ONCE when the vibration frequency CHANGES value, to avoid the motor having to STOPGOSTOPGOSTOPGOSTOPGO
                            }
                            if (!vib.isVibrating()) {
                                vib.vibrateAtFrequencyForever(vib_freq);
                            }
                            output_view.setText("Vibrating at " + vib_freq + " Hz");
                            // Stop other sounds
                            empty_sound.stop();
                            inside_sound.stop();
                            line_sound.stop();
                        }
                        // If ONE finger is on a vertex
                        else if ((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) ||
                                 (pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue)) {
                            if (vib_freq != vib_freq_1_vertex) {
                                // Stop the previous vibration if it is different from the one we are supposed to do
                                vib.stop();
                                // Update the vibration frequency
                                vib_freq = vib_freq_1_vertex;
                                // This only happens ONCE when the vibration frequency CHANGES value, to avoid the motor having to STOPGOSTOPGOSTOPGOSTOPGO
                            }
                            if (!vib.isVibrating()) {
                                vib.vibrateAtFrequencyForever(vib_freq);
                            }
                            output_view.setText("Vibrating at " + vib_freq + " Hz");
                        }
                        // If NO fingers are on a vertex
                        else {
                            vib.stop();
                            output_view.setText("");
                        }
                        // If one OR both fingers are on a line
                        if ((pixel0_red == line_red && pixel0_green == line_green && pixel0_blue == line_blue) ||
                            (pixel1_red == line_red && pixel1_green == line_green && pixel1_blue == line_blue)) {
                            // Stop other sounds
                            empty_sound.stop();
                            inside_sound.stop();
                            // Play line sound
                            if (line_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.
                                if (!line_sound.isPlaying()) {
                                    line_sound.start();
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                line_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    line_sound.setDataSource(fd_line_sound, afd_line_sound.getStartOffset(), afd_line_sound.getLength());
                                    line_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        // If one OR both fingers are on the INSIDE
                        else if ((pixel0_red == inside_red && pixel0_green == inside_green && pixel0_blue == inside_blue) ||
                                 (pixel1_red == inside_red && pixel1_green == inside_green && pixel1_blue == inside_blue)) {
                            // Stop other sounds
                            empty_sound.stop();
                            line_sound.stop();
                            // Play INSIDE sound
                            if (inside_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.
                                if (!inside_sound.isPlaying()) {
                                    inside_sound.start();
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                inside_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    inside_sound.setDataSource(fd_inside_sound, afd_inside_sound.getStartOffset(), afd_inside_sound.getLength());
                                    inside_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        // If NOT on lines, NOT on inside, then if at least one finger is on empty space
                        else if (((pixel0_red != inside_red && pixel0_green != inside_green && pixel0_blue != inside_blue) &&
                                 (pixel0_red != line_red && pixel0_green != line_green && pixel0_blue != line_blue) &&
                                 (pixel0_red != vertex_red && pixel0_green != vertex_green && pixel0_blue != vertex_blue)) ||
                                ((pixel1_red != inside_red && pixel1_green != inside_green && pixel1_blue != inside_blue) &&
                                 (pixel1_red != line_red && pixel1_green != line_green && pixel1_blue != line_blue) &&
                                 (pixel1_red != vertex_red && pixel1_green != vertex_green && pixel1_blue != vertex_blue))) {
                            // Stop other sounds
                            inside_sound.stop();
                            line_sound.stop();
                            // Play EMPTY sound
                            if (empty_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.
                                if (!empty_sound.isPlaying()) {
                                    empty_sound.start();
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                empty_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    empty_sound.setDataSource(fd_empty_sound, afd_empty_sound.getStartOffset(), afd_empty_sound.getLength());
                                    empty_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }

                /**************************************************************************************
                 * THREE FINGERS
                 **************************************************************************************/
                if (sfg.finger_count == 3){

                    /**************************************************************************************
                     * GET FINGER INDEXES
                     **************************************************************************************/

                    // Get the coordinates and pixel color the fingers are touching, considering the different scaling factors of the VIEW and the IMAGE itself.

                    // Scan the X_coords and Y_coords arrays to find the FIRST finger
                    if (f0_index == -1) {
                        for (int i = 0; i < sfg.X_coords.length; i++) {
                            if (sfg.X_coords[i] != 0) {
                                f0_index = i;
                                break;
                            }
                        }
                    }
                    // If the finger was in X = 0.0, which means that finger_index is still -1, then scan  the Y_coords array to try and find which finger is on screen.
                    if (f0_index == -1) {
                        for (int i = 0; i < sfg.Y_coords.length; i++) {
                            if (sfg.Y_coords[i] != 0) {
                                f0_index = i;
                                break;
                            }
                        }
                    }


                    // Scan the X_coords and Y_coords arrays to find the SECOND finger
                    if (f1_index == -1) {
                        for (int i = 0; i < sfg.X_coords.length; i++) {
                            if (sfg.X_coords[i] != 0 && sfg.X_coords[i] != sfg.X_coords[f0_index]) {
                                f1_index = i;
                                break;
                            }
                        }
                    }
                    // If the finger was in X = 0.0, which means that finger_index is still -1, then scan  the Y_coords array to try and find which finger is on screen.
                    if (f1_index == -1) {
                        for (int i = 0; i < sfg.Y_coords.length; i++) {
                            if (sfg.Y_coords[i] != 0 && sfg.Y_coords[i] != sfg.Y_coords[f0_index]) {
                                f1_index = i;
                                break;
                            }
                        }
                    }

                    // Scan the X_coords and Y_coords arrays to find the THIRD finger
                    if (f2_index == -1) {
                        for (int i = 0; i < sfg.X_coords.length; i++) {
                            if (sfg.X_coords[i] != 0 && sfg.X_coords[i] != sfg.X_coords[f0_index] && sfg.X_coords[i] != sfg.X_coords[f1_index]) {
                                f2_index = i;
                                break;
                            }
                        }
                    }
                    // If the finger was in X = 0.0, which means that finger_index is still -1, then scan  the Y_coords array to try and find which finger is on screen.
                    if (f2_index == -1) {
                        for (int i = 0; i < sfg.Y_coords.length; i++) {
                            if (sfg.Y_coords[i] != 0 && sfg.Y_coords[i] != sfg.Y_coords[f0_index] && sfg.Y_coords[i] != sfg.Y_coords[f1_index]) {
                                f2_index = i;
                                break;
                            }
                        }
                    }

                    /**************************************************************************************
                     * FINGERS FOUND! PROCEED WITH THE REST OF THE CODE
                     **************************************************************************************/

                    // IF the fingers were found, THEN continue with the rest of the code. Otherwise, do nothing. The finger is in EXACTLY (0.0 , 0.0).
                    if (f0_index != -1 && f1_index != -1 && f2_index != -1) {

                        /**************************************************************************************
                         * GET PIXELS UNDER EACH FINGER
                         **************************************************************************************/

                        // Get the VIEW X and Y coords for FINGER0
                        double viewX0 = sfg.X_coords[f0_index];
                        double viewY0 = sfg.Y_coords[f0_index];
                        // Rule of 3 to scale the pixel values
                        double imageX0 = (viewX0 * (imageWidth / viewWidth));
                        double imageY0 = (viewY0 * (imageHeight / viewHeight));
                        // Limit the X and Y values to the bitmap max dimensions to avoid IllegalArgumentException when providing the pixel coordinate
                        if (imageX0 >= imageWidth) {
                            imageX0 = imageWidth - 0.01; // Modify it by 1/100 of a pixel to meet the imageX < bitmap.width condition!
                        }
                        else if (imageX0 < 0) {
                            imageX0 = 0; // Account for negative X values (bezels!)
                        }
                        if (imageY0 >= imageHeight) {
                            imageY0 = imageHeight - 0.01; // Modify it by 1/100 of a pixel to meet the imageY < bitmap.height condition!
                        }
                        else if (imageY0 < 0) {
                            imageY0 = 0; // Account for negative X values (bezels!)
                        }
                        // Get the pixel based on the IMAGE X and Y coordinates!
                        int pixel0 = image.getPixel((int)imageX0, (int)imageY0);
                        // Save the RGB values
                        int pixel0_red = Color.red(pixel0);
                        int pixel0_green = Color.green(pixel0);
                        int pixel0_blue = Color.blue(pixel0);

                        // Get the VIEW X and Y coords for FINGER1
                        double viewX1 = sfg.X_coords[f1_index];
                        double viewY1 = sfg.Y_coords[f1_index];
                        // Rule of 3 to scale the pixel values
                        double imageX1 = (viewX1 * (imageWidth / viewWidth));
                        double imageY1 = (viewY1 * (imageHeight / viewHeight));
                        // Limit the X and Y values to the bitmap max dimensions to avoid IllegalArgumentException when providing the pixel coordinate
                        if (imageX1 >= imageWidth) {
                            imageX1 = imageWidth - 0.01; // Modify it by 1/100 of a pixel to meet the imageX < bitmap.width condition!
                        }
                        else if (imageX1 < 0) {
                            imageX1 = 0; // Account for negative X values (bezels!)
                        }
                        if (imageY1 >= imageHeight) {
                            imageY1 = imageHeight - 0.01; // Modify it by 1/100 of a pixel to meet the imageY < bitmap.height condition!
                        }
                        else if (imageY1 < 0) {
                            imageY1 = 0; // Account for negative X values (bezels!)
                        }
                        // Get the pixel based on the IMAGE X and Y coordinates!
                        int pixel1 = image.getPixel((int)imageX1, (int)imageY1);
                        // Save the RGB values
                        int pixel1_red = Color.red(pixel1);
                        int pixel1_green = Color.green(pixel1);
                        int pixel1_blue = Color.blue(pixel1);

                        // Get the VIEW X and Y coords for FINGER2
                        double viewX2 = sfg.X_coords[f2_index];
                        double viewY2 = sfg.Y_coords[f2_index];
                        // Rule of 3 to scale the pixel values
                        double imageX2 = (viewX2 * (imageWidth / viewWidth));
                        double imageY2 = (viewY2 * (imageHeight / viewHeight));
                        // Limit the X and Y values to the bitmap max dimensions to avoid IllegalArgumentException when providing the pixel coordinate
                        if (imageX2 >= imageWidth) {
                            imageX2 = imageWidth - 0.01; // Modify it by 1/100 of a pixel to meet the imageX < bitmap.width condition!
                        }
                        else if (imageX2 < 0) {
                            imageX2 = 0; // Account for negative X values (bezels!)
                        }
                        if (imageY2 >= imageHeight) {
                            imageY2 = imageHeight - 0.01; // Modify it by 1/100 of a pixel to meet the imageY < bitmap.height condition!
                        }
                        else if (imageY2 < 0) {
                            imageY2 = 0; // Account for negative X values (bezels!)
                        }
                        // Get the pixel based on the IMAGE X and Y coordinates!
                        int pixel2 = image.getPixel((int)imageX2, (int)imageY2);
                        // Save the RGB values
                        int pixel2_red = Color.red(pixel2);
                        int pixel2_green = Color.green(pixel2);
                        int pixel2_blue = Color.blue(pixel2);

                        /**************************************************************************************
                         * REACT BASED ON FINGER LOCATIONS AND PIXELS
                         **************************************************************************************/

                        // If ALL fingers are in a vertex
                        if ((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) &&
                            (pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue) &&
                            (pixel2_red == vertex_red && pixel2_green == vertex_green && pixel2_blue == vertex_blue)) {

                            // Stop other sounds
                            empty_sound.stop();
                            inside_sound.stop();
                            line_sound.stop();
                            if (vib_freq != vib_freq_3_vertex) {
                                // Stop the previous vibration if it is different from the one we are supposed to do
                                vib.stop();
                                // Update the vibration frequency
                                vib_freq = vib_freq_3_vertex;
                                // This only happens ONCE when the vibration frequency CHANGES value, to avoid the motor having to STOPGOSTOPGOSTOPGOSTOPGO
                            }
                            if (!vib.isVibrating()) {
                                vib.vibrateAtFrequencyForever(vib_freq);
                            }
                            output_view.setText("Vibrating at " + vib_freq + " Hz");

                            // Play the victory sound
                            if (victory_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.
                                if (!victory_sound.isPlaying()) {
                                    victory_sound.start();
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                victory_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    victory_sound.setDataSource(fd_victory_sound, afd_victory_sound.getStartOffset(), afd_victory_sound.getLength());
                                    victory_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        // If TWO fingers are on a vertex
                        else if (((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) &&
                                 (pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue)) ||
                                 ((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) &&
                                 (pixel2_red == vertex_red && pixel2_green == vertex_green && pixel2_blue == vertex_blue)) ||
                                 ((pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue) &&
                                 (pixel2_red == vertex_red && pixel2_green == vertex_green && pixel2_blue == vertex_blue))) {

                            // Stop victory sound, just in case it is running
                            victory_sound.stop();

                            //Vibrate
                            if (vib_freq != vib_freq_2_vertex) {
                                // Stop the previous vibration if it is different from the one we are supposed to do
                                vib.stop();
                                // Update the vibration frequency
                                vib_freq = vib_freq_2_vertex;
                                // This only happens ONCE when the vibration frequency CHANGES value, to avoid the motor having to STOPGOSTOPGOSTOPGOSTOPGO
                            }
                            if (!vib.isVibrating()) {
                                vib.vibrateAtFrequencyForever(vib_freq);
                            }
                            output_view.setText("Vibrating at " + vib_freq + " Hz");
                        }
                        // If ONE finger is on a vertex
                        else if ((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) ||
                                (pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue) ||
                                (pixel2_red == vertex_red && pixel2_green == vertex_green && pixel2_blue == vertex_blue)) {

                            // Stop victory sound, just in case it is running
                            victory_sound.stop();

                            // Vibrate
                            if (vib_freq != vib_freq_1_vertex) {
                                // Stop the previous vibration if it is different from the one we are supposed to do
                                vib.stop();
                                // Update the vibration frequency
                                vib_freq = vib_freq_1_vertex;
                                // This only happens ONCE when the vibration frequency CHANGES value, to avoid the motor having to STOPGOSTOPGOSTOPGOSTOPGO
                            }
                            if (!vib.isVibrating()) {
                                vib.vibrateAtFrequencyForever(vib_freq);
                            }
                            output_view.setText("Vibrating at " + vib_freq + " Hz");
                        }
                        // If NO fingers are on a vertex
                        else {
                            // Stop victory sound, just in case it is running
                            victory_sound.stop();
                            // Stop any vertex vibration
                            vib.stop();
                            output_view.setText("");
                        }
                        // If ANY finger is on a line
                        if ((pixel0_red == line_red && pixel0_green == line_green && pixel0_blue == line_blue) ||
                            (pixel1_red == line_red && pixel1_green == line_green && pixel1_blue == line_blue) ||
                            (pixel2_red == line_red && pixel2_green == line_green && pixel2_blue == line_blue)) {
                            // Stop other sounds
                            empty_sound.stop();
                            inside_sound.stop();
                            // Play line sound
                            if (line_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.
                                if (!line_sound.isPlaying()) {
                                    line_sound.start();
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                line_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    line_sound.setDataSource(fd_line_sound, afd_line_sound.getStartOffset(), afd_line_sound.getLength());
                                    line_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        // If ANY of the three fingers are on the INSIDE
                        else if ((pixel0_red == inside_red && pixel0_green == inside_green && pixel0_blue == inside_blue) ||
                                (pixel1_red == inside_red && pixel1_green == inside_green && pixel1_blue == inside_blue) ||
                                (pixel2_red == inside_red && pixel2_green == inside_green && pixel2_blue == inside_blue)) {
                            // Stop other sounds
                            empty_sound.stop();
                            line_sound.stop();
                            // Play INSIDE sound
                            if (inside_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.
                                if (!inside_sound.isPlaying()) {
                                    inside_sound.start();
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                inside_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    inside_sound.setDataSource(fd_inside_sound, afd_inside_sound.getStartOffset(), afd_inside_sound.getLength());
                                    inside_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        // If NOT on lines, NOT on inside, then if at least one finger is on empty space
                        else if (((pixel0_red != inside_red && pixel0_green != inside_green && pixel0_blue != inside_blue) &&
                                (pixel0_red != line_red && pixel0_green != line_green && pixel0_blue != line_blue) &&
                                (pixel0_red != vertex_red && pixel0_green != vertex_green && pixel0_blue != vertex_blue)) ||
                                ((pixel1_red != inside_red && pixel1_green != inside_green && pixel1_blue != inside_blue) &&
                                (pixel1_red != line_red && pixel1_green != line_green && pixel1_blue != line_blue) &&
                                (pixel1_red != vertex_red && pixel1_green != vertex_green && pixel1_blue != vertex_blue)) ||
                                ((pixel2_red != inside_red && pixel2_green != inside_green && pixel2_blue != inside_blue) &&
                                (pixel2_red != line_red && pixel2_green != line_green && pixel2_blue != line_blue) &&
                                (pixel2_red != vertex_red && pixel2_green != vertex_green && pixel2_blue != vertex_blue))) {
                            // Stop other sounds
                            inside_sound.stop();
                            line_sound.stop();
                            // Play EMPTY sound
                            if (empty_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.
                                if (!empty_sound.isPlaying()) {
                                    empty_sound.start();
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                empty_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    empty_sound.setDataSource(fd_empty_sound, afd_empty_sound.getStartOffset(), afd_empty_sound.getLength());
                                    empty_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }

                /**************************************************************************************
                 * FOUR FINGERS
                 **************************************************************************************/
                if (sfg.finger_count == 4){

                    /**************************************************************************************
                     * GET FINGER INDEXES
                     **************************************************************************************/

                    // Get the coordinates and pixel color the fingers are touching, considering the different scaling factors of the VIEW and the IMAGE itself.

                    // Scan the X_coords and Y_coords arrays to find the FIRST finger
                    if (f0_index == -1) {
                        for (int i = 0; i < sfg.X_coords.length; i++) {
                            if (sfg.X_coords[i] != 0) {
                                f0_index = i;
                                break;
                            }
                        }
                    }
                    // If the finger was in X = 0.0, which means that finger_index is still -1, then scan  the Y_coords array to try and find which finger is on screen.
                    if (f0_index == -1) {
                        for (int i = 0; i < sfg.Y_coords.length; i++) {
                            if (sfg.Y_coords[i] != 0) {
                                f0_index = i;
                                break;
                            }
                        }
                    }


                    // Scan the X_coords and Y_coords arrays to find the SECOND finger
                    if (f1_index == -1) {
                        for (int i = 0; i < sfg.X_coords.length; i++) {
                            if (sfg.X_coords[i] != 0 && sfg.X_coords[i] != sfg.X_coords[f0_index]) {
                                f1_index = i;
                                break;
                            }
                        }
                    }
                    // If the finger was in X = 0.0, which means that finger_index is still -1, then scan  the Y_coords array to try and find which finger is on screen.
                    if (f1_index == -1) {
                        for (int i = 0; i < sfg.Y_coords.length; i++) {
                            if (sfg.Y_coords[i] != 0 && sfg.Y_coords[i] != sfg.Y_coords[f0_index]) {
                                f1_index = i;
                                break;
                            }
                        }
                    }

                    // Scan the X_coords and Y_coords arrays to find the THIRD finger
                    if (f2_index == -1) {
                        for (int i = 0; i < sfg.X_coords.length; i++) {
                            if (sfg.X_coords[i] != 0 && sfg.X_coords[i] != sfg.X_coords[f0_index] && sfg.X_coords[i] != sfg.X_coords[f1_index]) {
                                f2_index = i;
                                break;
                            }
                        }
                    }
                    // If the finger was in X = 0.0, which means that finger_index is still -1, then scan  the Y_coords array to try and find which finger is on screen.
                    if (f2_index == -1) {
                        for (int i = 0; i < sfg.Y_coords.length; i++) {
                            if (sfg.Y_coords[i] != 0 && sfg.Y_coords[i] != sfg.Y_coords[f0_index] && sfg.Y_coords[i] != sfg.Y_coords[f1_index]) {
                                f2_index = i;
                                break;
                            }
                        }
                    }

                    // Scan the X_coords and Y_coords arrays to find the FOURTH finger
                    if (f3_index == -1) {
                        for (int i = 0; i < sfg.X_coords.length; i++) {
                            if (sfg.X_coords[i] != 0 && sfg.X_coords[i] != sfg.X_coords[f0_index] && sfg.X_coords[i] != sfg.X_coords[f1_index] && sfg.X_coords[i] != sfg.X_coords[f2_index]) {
                                f3_index = i;
                                break;
                            }
                        }
                    }
                    // If the finger was in X = 0.0, which means that finger_index is still -1, then scan  the Y_coords array to try and find which finger is on screen.
                    if (f3_index == -1) {
                        for (int i = 0; i < sfg.Y_coords.length; i++) {
                            if (sfg.Y_coords[i] != 0 && sfg.Y_coords[i] != sfg.Y_coords[f0_index] && sfg.Y_coords[i] != sfg.Y_coords[f1_index] && sfg.Y_coords[i] != sfg.Y_coords[f2_index]) {
                                f3_index = i;
                                break;
                            }
                        }
                    }

                    /**************************************************************************************
                     * FINGERS FOUND! PROCEED WITH THE REST OF THE CODE
                     **************************************************************************************/

                    // IF the fingers were found, THEN continue with the rest of the code. Otherwise, do nothing. The finger is in EXACTLY (0.0 , 0.0).
                    if (f0_index != -1 && f1_index != -1 && f2_index != -1 && f3_index != -1) {

                        /**************************************************************************************
                         * GET PIXELS UNDER EACH FINGER
                         **************************************************************************************/

                        //////////////////////////////////////////
                        // Get the VIEW X and Y coords for FINGER0
                        //////////////////////////////////////////
                        double viewX0 = sfg.X_coords[f0_index];
                        double viewY0 = sfg.Y_coords[f0_index];
                        // Rule of 3 to scale the pixel values
                        double imageX0 = (viewX0 * (imageWidth / viewWidth));
                        double imageY0 = (viewY0 * (imageHeight / viewHeight));
                        // Limit the X and Y values to the bitmap max dimensions to avoid IllegalArgumentException when providing the pixel coordinate
                        if (imageX0 >= imageWidth) {
                            imageX0 = imageWidth - 0.01; // Modify it by 1/100 of a pixel to meet the imageX < bitmap.width condition!
                        }
                        else if (imageX0 < 0) {
                            imageX0 = 0; // Account for negative X values (bezels!)
                        }
                        if (imageY0 >= imageHeight) {
                            imageY0 = imageHeight - 0.01; // Modify it by 1/100 of a pixel to meet the imageY < bitmap.height condition!
                        }
                        else if (imageY0 < 0) {
                            imageY0 = 0; // Account for negative X values (bezels!)
                        }
                        // Get the pixel based on the IMAGE X and Y coordinates!
                        int pixel0 = image.getPixel((int)imageX0, (int)imageY0);
                        // Save the RGB values
                        int pixel0_red = Color.red(pixel0);
                        int pixel0_green = Color.green(pixel0);
                        int pixel0_blue = Color.blue(pixel0);

                        //////////////////////////////////////////
                        // Get the VIEW X and Y coords for FINGER1
                        //////////////////////////////////////////
                        double viewX1 = sfg.X_coords[f1_index];
                        double viewY1 = sfg.Y_coords[f1_index];
                        // Rule of 3 to scale the pixel values
                        double imageX1 = (viewX1 * (imageWidth / viewWidth));
                        double imageY1 = (viewY1 * (imageHeight / viewHeight));
                        // Limit the X and Y values to the bitmap max dimensions to avoid IllegalArgumentException when providing the pixel coordinate
                        if (imageX1 >= imageWidth) {
                            imageX1 = imageWidth - 0.01; // Modify it by 1/100 of a pixel to meet the imageX < bitmap.width condition!
                        }
                        else if (imageX1 < 0) {
                            imageX1 = 0; // Account for negative X values (bezels!)
                        }
                        if (imageY1 >= imageHeight) {
                            imageY1 = imageHeight - 0.01; // Modify it by 1/100 of a pixel to meet the imageY < bitmap.height condition!
                        }
                        else if (imageY1 < 0) {
                            imageY1 = 0; // Account for negative X values (bezels!)
                        }
                        // Get the pixel based on the IMAGE X and Y coordinates!
                        int pixel1 = image.getPixel((int)imageX1, (int)imageY1);
                        // Save the RGB values
                        int pixel1_red = Color.red(pixel1);
                        int pixel1_green = Color.green(pixel1);
                        int pixel1_blue = Color.blue(pixel1);

                        //////////////////////////////////////////
                        // Get the VIEW X and Y coords for FINGER2
                        //////////////////////////////////////////
                        double viewX2 = sfg.X_coords[f2_index];
                        double viewY2 = sfg.Y_coords[f2_index];
                        // Rule of 3 to scale the pixel values
                        double imageX2 = (viewX2 * (imageWidth / viewWidth));
                        double imageY2 = (viewY2 * (imageHeight / viewHeight));
                        // Limit the X and Y values to the bitmap max dimensions to avoid IllegalArgumentException when providing the pixel coordinate
                        if (imageX2 >= imageWidth) {
                            imageX2 = imageWidth - 0.01; // Modify it by 1/100 of a pixel to meet the imageX < bitmap.width condition!
                        }
                        else if (imageX2 < 0) {
                            imageX2 = 0; // Account for negative X values (bezels!)
                        }
                        if (imageY2 >= imageHeight) {
                            imageY2 = imageHeight - 0.01; // Modify it by 1/100 of a pixel to meet the imageY < bitmap.height condition!
                        }
                        else if (imageY2 < 0) {
                            imageY2 = 0; // Account for negative X values (bezels!)
                        }
                        // Get the pixel based on the IMAGE X and Y coordinates!
                        int pixel2 = image.getPixel((int)imageX2, (int)imageY2);
                        // Save the RGB values
                        int pixel2_red = Color.red(pixel2);
                        int pixel2_green = Color.green(pixel2);
                        int pixel2_blue = Color.blue(pixel2);

                        //////////////////////////////////////////
                        // Get the VIEW X and Y coords for FINGER3
                        //////////////////////////////////////////
                        double viewX3 = sfg.X_coords[f3_index];
                        double viewY3 = sfg.Y_coords[f3_index];
                        // Rule of 3 to scale the pixel values
                        double imageX3 = (viewX3 * (imageWidth / viewWidth));
                        double imageY3 = (viewY3 * (imageHeight / viewHeight));
                        // Limit the X and Y values to the bitmap max dimensions to avoid IllegalArgumentException when providing the pixel coordinate
                        if (imageX3 >= imageWidth) {
                            imageX3 = imageWidth - 0.01; // Modify it by 1/100 of a pixel to meet the imageX < bitmap.width condition!
                        }
                        else if (imageX3 < 0) {
                            imageX3 = 0; // Account for negative X values (bezels!)
                        }
                        if (imageY3 >= imageHeight) {
                            imageY3 = imageHeight - 0.01; // Modify it by 1/100 of a pixel to meet the imageY < bitmap.height condition!
                        }
                        else if (imageY3 < 0) {
                            imageY3 = 0; // Account for negative X values (bezels!)
                        }
                        // Get the pixel based on the IMAGE X and Y coordinates!
                        int pixel3 = image.getPixel((int)imageX3, (int)imageY3);
                        // Save the RGB values
                        int pixel3_red = Color.red(pixel3);
                        int pixel3_green = Color.green(pixel3);
                        int pixel3_blue = Color.blue(pixel3);

                        /**************************************************************************************
                         * REACT BASED ON FINGER LOCATIONS AND PIXELS
                         **************************************************************************************/

                        // If ALL fingers are in a vertex
                        if ((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) &&
                            (pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue) &&
                            (pixel2_red == vertex_red && pixel2_green == vertex_green && pixel2_blue == vertex_blue) &&
                            (pixel3_red == vertex_red && pixel3_green == vertex_green && pixel3_blue == vertex_blue)) {

                            // Stop other sounds
                            empty_sound.stop();
                            inside_sound.stop();
                            line_sound.stop();
                            int vib_freq_4_vertex = 1;
                            if (vib_freq != vib_freq_4_vertex) {
                                // Stop the previous vibration if it is different from the one we are supposed to do
                                vib.stop();
                                // Update the vibration frequency
                                vib_freq = vib_freq_4_vertex;
                                // This only happens ONCE when the vibration frequency CHANGES value, to avoid the motor having to STOPGOSTOPGOSTOPGOSTOPGO
                            }
                            if (!vib.isVibrating()) {
                                vib.vibrateAtFrequencyForever(vib_freq);
                            }
                            output_view.setText("Vibrating at " + vib_freq + " Hz");

                            // Play the victory sound
                            if (victory_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.
                                if (!victory_sound.isPlaying()) {
                                    victory_sound.start();
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                victory_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    victory_sound.setDataSource(fd_victory_sound, afd_victory_sound.getStartOffset(), afd_victory_sound.getLength());
                                    victory_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }

                        // If THREE fingers are on a vertex
                        else if (((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) &&
                                (pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue) &&
                                (pixel2_red == vertex_red && pixel2_green == vertex_green && pixel2_blue == vertex_blue)) ||
                                ((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) && (pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue) && (pixel3_red == vertex_red && pixel3_green == vertex_green && pixel3_blue == vertex_blue)) ||
                                ((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) && (pixel2_red == vertex_red && pixel2_green == vertex_green && pixel2_blue == vertex_blue) && (pixel3_red == vertex_red && pixel3_green == vertex_green && pixel3_blue == vertex_blue)) ||
                                ((pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue) && (pixel2_red == vertex_red && pixel2_green == vertex_green && pixel2_blue == vertex_blue) && (pixel3_red == vertex_red && pixel3_green == vertex_green && pixel3_blue == vertex_blue))) {

                            // Stop victory sound, just in case it is running
                            victory_sound.stop();

                            //Vibrate
                            if (vib_freq != vib_freq_3_vertex) {
                                // Stop the previous vibration if it is different from the one we are supposed to do
                                vib.stop();
                                // Update the vibration frequency
                                vib_freq = vib_freq_3_vertex;
                            }
                            if (!vib.isVibrating()) {
                                vib.vibrateAtFrequencyForever(vib_freq);
                            }
                            output_view.setText("Vibrating at " + vib_freq + " Hz");
                        }

                        // If TWO fingers are on a vertex
                        else if (((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) &&
                                (pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue)) ||
                                ((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) && (pixel2_red == vertex_red && pixel2_green == vertex_green && pixel2_blue == vertex_blue)) ||
                                ((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) && (pixel3_red == vertex_red && pixel3_green == vertex_green && pixel3_blue == vertex_blue)) ||
                                ((pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue) && (pixel2_red == vertex_red && pixel2_green == vertex_green && pixel2_blue == vertex_blue)) ||
                                ((pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue) && (pixel3_red == vertex_red && pixel3_green == vertex_green && pixel3_blue == vertex_blue)) ||
                                ((pixel2_red == vertex_red && pixel2_green == vertex_green && pixel2_blue == vertex_blue) && (pixel3_red == vertex_red && pixel3_green == vertex_green && pixel3_blue == vertex_blue))) {

                            // Stop victory sound, just in case it is running
                            victory_sound.stop();

                            //Vibrate
                            if (vib_freq != vib_freq_2_vertex) {
                                // Stop the previous vibration if it is different from the one we are supposed to do
                                vib.stop();
                                // Update the vibration frequency
                                vib_freq = vib_freq_2_vertex;
                                // This only happens ONCE when the vibration frequency CHANGES value, to avoid the motor having to STOPGOSTOPGOSTOPGOSTOPGO
                            }
                            if (!vib.isVibrating()) {
                                vib.vibrateAtFrequencyForever(vib_freq);
                            }
                            output_view.setText("Vibrating at " + vib_freq + " Hz");
                        }

                        // If ONE finger is on a vertex
                        else if ((pixel0_red == vertex_red && pixel0_green == vertex_green && pixel0_blue == vertex_blue) ||
                                (pixel1_red == vertex_red && pixel1_green == vertex_green && pixel1_blue == vertex_blue) ||
                                (pixel2_red == vertex_red && pixel2_green == vertex_green && pixel2_blue == vertex_blue) ||
                                (pixel3_red == vertex_red && pixel3_green == vertex_green && pixel3_blue == vertex_blue)) {

                            // Stop victory sound, just in case it is running
                            victory_sound.stop();

                            // Vibrate
                            if (vib_freq != vib_freq_1_vertex) {
                                // Stop the previous vibration if it is different from the one we are supposed to do
                                vib.stop();
                                // Update the vibration frequency
                                vib_freq = vib_freq_1_vertex;
                            }
                            if (!vib.isVibrating()) {
                                vib.vibrateAtFrequencyForever(vib_freq);
                            }
                            output_view.setText("Vibrating at " + vib_freq + " Hz");
                        }
                        // If NO fingers are on a vertex
                        else {
                            // Stop victory sound, just in case it is running
                            victory_sound.stop();
                            // Stop any vertex vibration
                            vib.stop();
                            output_view.setText("");
                        }
                        // If ANY finger is on a line
                        if ((pixel0_red == line_red && pixel0_green == line_green && pixel0_blue == line_blue) ||
                            (pixel1_red == line_red && pixel1_green == line_green && pixel1_blue == line_blue) ||
                            (pixel2_red == line_red && pixel2_green == line_green && pixel2_blue == line_blue) ||
                            (pixel3_red == line_red && pixel3_green == line_green && pixel3_blue == line_blue)) {
                            // Stop other sounds
                            empty_sound.stop();
                            inside_sound.stop();
                            // Play line sound
                            if (line_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.
                                if (!line_sound.isPlaying()) {
                                    line_sound.start();
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                line_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    line_sound.setDataSource(fd_line_sound, afd_line_sound.getStartOffset(), afd_line_sound.getLength());
                                    line_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        // If ANY of the four fingers are on the INSIDE
                        else if ((pixel0_red == inside_red && pixel0_green == inside_green && pixel0_blue == inside_blue) ||
                                (pixel1_red == inside_red && pixel1_green == inside_green && pixel1_blue == inside_blue) ||
                                (pixel2_red == inside_red && pixel2_green == inside_green && pixel2_blue == inside_blue) ||
                                (pixel3_red == inside_red && pixel3_green == inside_green && pixel3_blue == inside_blue)) {
                            // Stop other sounds
                            empty_sound.stop();
                            line_sound.stop();
                            // Play INSIDE sound
                            if (inside_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.
                                if (!inside_sound.isPlaying()) {
                                    inside_sound.start();
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                inside_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    inside_sound.setDataSource(fd_inside_sound, afd_inside_sound.getStartOffset(), afd_inside_sound.getLength());
                                    inside_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        // If at least ONE finer is on empty space (NOT on lines, NOT on inside)
                        else if (((pixel0_red != inside_red && pixel0_green != inside_green && pixel0_blue != inside_blue) &&
                                (pixel0_red != line_red && pixel0_green != line_green && pixel0_blue != line_blue) &&
                                (pixel0_red != vertex_red && pixel0_green != vertex_green && pixel0_blue != vertex_blue)) ||
                                ((pixel1_red != inside_red && pixel1_green != inside_green && pixel1_blue != inside_blue) &&
                                (pixel1_red != line_red && pixel1_green != line_green && pixel1_blue != line_blue) &&
                                (pixel1_red != vertex_red && pixel1_green != vertex_green && pixel1_blue != vertex_blue)) ||
                                ((pixel2_red != inside_red && pixel2_green != inside_green && pixel2_blue != inside_blue) &&
                                (pixel2_red != line_red && pixel2_green != line_green && pixel2_blue != line_blue) &&
                                (pixel2_red != vertex_red && pixel2_green != vertex_green && pixel2_blue != vertex_blue)) ||
                                ((pixel3_red != inside_red && pixel3_green != inside_green && pixel3_blue != inside_blue) &&
                                (pixel3_red != line_red && pixel3_green != line_green && pixel3_blue != line_blue) &&
                                (pixel3_red != vertex_red && pixel3_green != vertex_green && pixel3_blue != vertex_blue))) {
                            // Stop other sounds
                            inside_sound.stop();
                            line_sound.stop();
                            // Play EMPTY sound
                            if (empty_sound.getDuration() > 0 ) {
                                // The MediaPlayer has been initialized. Give it your commands.
                                if (!empty_sound.isPlaying()) {
                                    empty_sound.start();
                                }
                            }
                            else {
                                // The media player has NOT been initialized, because getDuration() returns -1. Initialize it!
                                empty_sound.reset();
                                try {
                                    // Get the path to the desired sound
                                    empty_sound.setDataSource(fd_empty_sound, afd_empty_sound.getStartOffset(), afd_empty_sound.getLength());
                                    empty_sound.prepare();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }

                handler.postDelayed(this, 0);
            }
        });
    }

    /**************************************************************************************
     * LOGGER CODE
     **************************************************************************************/
    public void logger(String file_name) throws IOException {
        final Handler handler = new Handler();

        /**************************************************************************************
         * LOGGER CONFIGURATION
         **************************************************************************************/
        // Set the file paths, delay, and timestamp format
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS); //Point to the Documents folder
        File output_file_dir = new File(path + "/SOAR_2023/"); // Folder where CSV file will be saved inside the Documents folder
        Log.e(TAG, "logger: output_file_dir = " + output_file_dir);
        Log.e(TAG, "logger: file_name = " + file_name);
        File output_file = new File(output_file_dir, file_name); //Point to the output file
        String header = "time,x0,x1,x2,x3,x4,y0,y1,y2,y3,y4\n"; // header for CSV file
        int logger_delay = 500; //Logger Delay in ms
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS"); // Set the date for later constant updating

        // Initiate the FileWriter
        writer = new FileWriter(output_file);

        // Make the output directory in case it does not exist yet, and write the header for the file
        try{
            if (!output_file_dir.exists()) {
                output_file_dir.mkdir();
            }
            writer.append(header);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                // Write your constantly running code here

                /**************************************************************************************
                 * OUTPUT FILE CONFIGURATION
                 **************************************************************************************/
                //Ask for permission to manage External Storage
                if (ContextCompat.checkSelfPermission(shapes.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(shapes.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }
                else {
                    // Permission has already been granted
                    // Perform the action that requires the permission

                    // Get the current time
                    String current_time = sdf.format(new Date());

                    // Write the time + finger coordinates to the file and remove the square brackets
                    String file_content = current_time + "," + Arrays.toString(sfg.X_coords).replace("[", "").replace("]", "")
                            + "," + Arrays.toString(sfg.Y_coords).replace("[", "").replace("]", "") + "\n";
                    try {
                        if (writer_active) { // This section will always be accessed UNTIL the gesture for leaving the activity is called!
                            writer.append(file_content);
                            writer.flush();
                            Log.e("TAG", "Wrote to file: "+ file_name);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                handler.postDelayed(this, logger_delay);
            }
        };
        // Start the Runnable
        handler.post(runnable);
    }

}