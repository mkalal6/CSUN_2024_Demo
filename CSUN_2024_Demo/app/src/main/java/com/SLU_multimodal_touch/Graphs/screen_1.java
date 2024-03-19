package com.SLU_multimodal_touch.Graphs;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;
import com.SLU_multimodal_touch.Graphs.R;

import in.championswimmer.sfg.lib.SimpleFingerGestures;

public class screen_1 extends AppCompatActivity {

    // Initiate the TextToSpeech object
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_1);

        /////////////////////////////////////////////////////////////////////////
        ///////////////////////// MULTITOUCH LIBRARY ////////////////////////////
        /////////////////////////////////////////////////////////////////////////

        // Taken from: https://github.com/championswimmer/SimpleFingerGestures_Android_Library/blob/master/sample/src/main/java/in/championswimmer/sfg/sample/MainActivity.java
        ImageView mv = (ImageView) findViewById(R.id.mt_view);
        final TextView grtv = (TextView) findViewById(R.id.gestureResultTextView);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        SimpleFingerGestures sfg = new SimpleFingerGestures();
        sfg.setDebug(true);
        sfg.setConsumeTouchEvents(true);

        sfg.setOnFingerGestureListener(new SimpleFingerGestures.OnFingerGestureListener() {
            @Override
            public boolean onSwipeUp(int fingers, long gestureDuration, double gestureDistance) {
                if (fingers == 1) {
                    //Write your code here for swiping DOWN with one finger
                    grtv.setText("Swiping with 1 finger does nothing. Double tap for navigation instructions.");
                }
                else if (fingers == 2) {
                    // Write your code here for swiping DOWN with 2 fingers
                    grtv.setText("That does nothing. Double tap for navigation instructions.");
                }
                else if (fingers == 3) {
                    // Write your code here for swiping DOWN with 3 fingers
                    // Create an Intent that takes us from this activity to another
//                    Intent go_to_next_screen = new Intent(screen_1.this, bar_graph.class);
                    // Activate the intent to go to that screen
//                    startActivity(go_to_next_screen);
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                }
                else if (fingers == 4) {
                    // Write your code here for swiping DOWN with 4 fingers
                    grtv.setText("That does nothing. Double tap for navigation instructions.");
                }
                return false;
            }

            @Override
            public boolean onSwipeDown(int fingers, long gestureDuration, double gestureDistance) {
                if (fingers == 1) {
                    //Write your code here for swiping DOWN with one finger
                    grtv.setText("Swiping with 1 finger does nothing. Double tap for navigation instructions.");
                }
                else if (fingers == 2) {
                    // Write your code here for swiping DOWN with 2 fingers
                    grtv.setText("That does nothing. Double tap for navigation instructions.");
                }
                else if (fingers == 3) {
                    // Write your code here for swiping DOWN with 3 fingers
                    grtv.setText("That does nothing. Double tap for navigation instructions.");
                }
                else if (fingers == 4) {
                    // Write your code here for swiping DOWN with 4 fingers
                    // Create an Intent that takes us from this activity to another
//                    Intent go_to_next_screen = new Intent(screen_1.this, v_next_week.class);
//                    // Activate the intent to go to that screen
//                    startActivity(go_to_next_screen);
//                    overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
                }
                return false;
            }

            @Override
            public boolean onSwipeLeft(int fingers, long gestureDuration, double gestureDistance) {
                if (fingers == 1) {
                    //Write your code here for swiping LEFT with one finger
                    grtv.setText("Swiping with 1 finger does nothing. Double tap for navigation instructions.");
                }
                else if (fingers == 2) {
                    // Write your code here for swiping LEFT with 2 fingers
                    // Create an Intent that takes us from this activity to another
//                    Intent go_to_next_screen = new Intent(screen_1.this, iii_goal1.class);
//                    // Activate the intent to go to that screen
//                    startActivity(go_to_next_screen);
//                    overridePendingTransition(0,0);
                }
                else if (fingers == 3) {
                    // Write your code here for swiping LEFT with 3 fingers
                    grtv.setText("That does nothing. Double tap for navigation instructions.");
                }
                else if (fingers == 4) {
                    // Write your code here for swiping LEFT with 4 fingers
                    grtv.setText("That does nothing. Double tap for navigation instructions.");
                }
                return false;
            }

            @Override
            public boolean onSwipeRight(int fingers, long gestureDuration, double gestureDistance) {
                if (fingers == 1) {
                    //Write your code here for swiping RIGHT with one finger
                    grtv.setText("Swiping with 1 finger does nothing. Double tap for navigation instructions.");
                }
                else if (fingers == 2) {
                    // Write your code here for swiping RIGHT with 2 fingers
                    // Create an Intent that takes us from this activity to another
//                    Intent go_to_next_screen = new Intent(screen_1.this, bar_graph.class);
                    // Activate the intent to go to that screen
//                    startActivity(go_to_next_screen);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                }
                else if (fingers == 3) {
                    // Write your code here for swiping RIGHT with 3 fingers
                    grtv.setText("That does nothing. Double tap for navigation instructions.");
                }
                else if (fingers == 4) {
                    // Write your code here for swiping RIGHT with 4 fingers
                    grtv.setText("That does nothing. Double tap for navigation instructions.");
                }
                return false;
            }

            @Override
            public boolean onPinch(int fingers, long gestureDuration, double gestureDistance) {
                grtv.setText("You pinched " + fingers + " fingers.\nStop it though. This does nothing. You are welcome :)");
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
            public boolean onUnpinch(int fingers, long gestureDuration, double gestureDistance) {
                grtv.setText("You unpinched " + fingers + " fingers.\nStop it though. This does nothing. You are welcome :)");
                if (fingers == 2) {
                    // Write your code here for UNPINCHING with 2 fingers
                }
                else if (fingers == 3) {
                    // Write your code here for UNPINCHING with 3 fingers
                }
                else if (fingers == 4) {
                    // Write your code here for UNPINCHING with 4 fingers
                }
                return false;
            }

            @Override
            public boolean onDoubleTap(int fingers) {
                grtv.setText("2-finger-swipe left = Previous slide.\n2-finger-swipe right = Next slide.\n3-finger-swipe up = Back to title.\n4-finger-swipe-down = To final slide.");
                // Double tap only works with one finger!
                return false;
            }
        });

        mv.setOnTouchListener(sfg);

        /////////////////////////////////////////////////////////////////////////
        /////////////////////// END MULTITOUCH LIBRARY /////////////////////////
        /////////////////////////////////////////////////////////////////////////

    }
}